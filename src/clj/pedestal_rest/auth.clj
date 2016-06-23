(ns pedestal-rest.auth
  (:require  [clojure.tools.logging :as log]
             [pedestal-rest.db.core :as db]
             [ring.util.response :as resp]
             [io.pedestal.impl.interceptor :refer [terminate]]
             [io.pedestal.interceptor.helpers :refer [defbefore defhandler]]
             [io.pedestal.http :as ph]
             [clj-time.core :refer [hours from-now]]
             [buddy.auth.protocols :as proto]
             [buddy.sign.jwe :as jwe]
             [buddy.core.nonce :as nonce]
             [buddy.auth.backends.token :refer [jwe-backend]]
             [buddy.auth.middleware :refer [wrap-authentication]]
             [buddy.hashers :as hashers]))

(defonce secret (nonce/random-bytes 32))
(def encryption {:alg :a256kw :enc :a128gcm})
(def auth-backend
  (jwe-backend {:secret secret
                :options encryption}))

(defhandler login
  [{:keys [json-params] :as request}]
  (let [{:keys [username password]} json-params
        user (db/get-user username)
        valid? (hashers/check password (:password user))]
    (if-not valid?
      (-> {:message "Wrong username password combination"}
          ph/json-response
          (resp/status 401))
      (let [business (first (db/get-business-by-user-id (:id user)))
            info {:name (:email user)
                  :bid (:id business)}
            claims {:user info
                    :exp (-> 3 hours from-now)}
            token (jwe/encrypt claims secret encryption)]
        (-> {:token token}
            (ph/json-response))))))

(defn- fake-handler
  [request]
  request)

(def ^:private buddy-check-auth
  (wrap-authentication fake-handler auth-backend))

(defbefore check-auth
  [{:keys [request] :as context}]
  (let [req-with-auth (buddy-check-auth request)]
    (if (contains? req-with-auth :identity)
      (assoc context :request req-with-auth)
      (-> context
          terminate
          (assoc :response {:status 401 :body {:message "Unauthorized"}})))))
