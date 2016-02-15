(ns pedestal-rest.service
  (:require [io.pedestal.http :as bootstrap]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [io.pedestal.interceptor.helpers :as ph]
            [ring.util.response :as ring-resp]
            [pedestal-rest.auth :as auth]
            [pedestal-rest.db.core :as db]
            [buddy.hashers :as hashers]))

(defn about-page
  [request]
  {:message "pedestal-rest: a single page app backend."})

(defn home-page
  [request]
  {:message "Hello World!"})

(ph/defhandler get-user-info
  [request]
  (first
   (db/get-user-by-login
    {:email (get-in request [:identity :user :name])})))

(ph/defon-response wrap-json-response [resp]
  (bootstrap/json-response resp))

(ph/defhandler register-user
  [{:keys [json-params] :as request}]
  (let [{:keys [email password]} json-params]
    (db/create-user! {:email email
                      :pass (hashers/encrypt password)
                      :first_name ""
                      :last_name ""})))

(defroutes routes
  [[["/" {:get home-page}
     ^:interceptors [(body-params/body-params) wrap-json-response]
     ["/login" {:post auth/login}]
     ["/user" {:post register-user}]
     ["/user/:id" ^:interceptors [auth/check-auth auth/check-permission]
      ["/info" {:get get-user-info}]]
     ["/about" {:get about-page}]]]])

;; Consumed by pedestal-rest.server/create-server
;; See bootstrap/default-interceptors for additional options you can configure
(def service {:env :prod
              ;; You can bring your own non-default interceptors. Make
              ;; sure you include routing and set it up right for
              ;; dev-mode. If you do, many other keys for configuring
              ;; default interceptors will be ignored.
              ;; ::bootstrap/interceptors []
              ::bootstrap/routes routes

              ;; Uncomment next line to enable CORS support, add
              ;; string(s) specifying scheme, host and port for
              ;; allowed source(s):
              ;;
              ;; "http://localhost:8080"
              ;;
              ;;::bootstrap/allowed-origins ["scheme://host:port"]

              ;; Root for resource interceptor that is available by default.
              ::bootstrap/resource-path "/public"

              ;; Either :jetty, :immutant or :tomcat (see comments in project.clj)
              ::bootstrap/type :jetty
              ;;::bootstrap/host "localhost"
              ::bootstrap/port 8080})

