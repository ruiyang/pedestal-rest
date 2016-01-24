(ns pedestal-rest.auth
  (:require [pedestal-rest.db.users :as u]
            [io.pedestal.impl.interceptor :refer [terminate]]
            [io.pedestal.interceptor.helpers :refer [defbefore defhandler]]
            [clj-time.core :refer [hours from-now]]
            [buddy.auth.protocols :as proto]
            [buddy.sign.jwe :as jwe]
            [buddy.core.nonce :as nonce]
            [buddy.auth.backends.token :refer [jwe-backend]]
            [buddy.auth.middleware :refer [wrap-authentication]]))

(defonce secret (nonce/random-bytes 32))
(def encryption {:alg :a256kw :enc :a128gcm})
(def auth-backend
  (jwe-backend {:secret secret
                :options encryption}))

(defhandler login
  [{:keys [json-params] :as request}]
  (let [{:keys [username password]} json-params
        [user] (u/get-user username)
        valid? (= (:pwd user) password)]
    (if-not valid?
      {:status 401 :body {:message "Wrong credentials"}}
      (let [info user
            claims {:user info
                    :exp (-> 3 hours from-now)}
            token (jwe/encrypt claims secret encryption)]
        {:status 200 :body {:token token}}))))

(defn- fake-handler
  [request]
  request)

(def ^:private buddy-check-auth
  (wrap-authentication fake-handler auth-backend))

(defbefore check-auth
  [{:keys [request] :as context}]
  (let [req-with-auth (buddy-check-auth request)]
    (if (:identity req-with-auth)
      (assoc context :request req-with-auth)
      (-> context
          terminate
          (assoc :response {:status 401 :body {:message (:identity "Unauthorized")}})))))
