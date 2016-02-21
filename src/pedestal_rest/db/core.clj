(ns pedestal-rest.db.core
  (:require
   [yesql.core :refer [defqueries]]
   [config.core :refer [env]]
   [mount.core :as mount]
   [conman.core :as conman]
   [clojure.java.jdbc :as jdbc]
   [pedestal-rest.db.model :as m]))

(def pool-spec
  {:adapter :h2
   :init-size  1
   :min-idle   1
   :max-idle   4
   :max-active 32
   :jdbc-url (env :database-url)})

(defn connect! []
  (let [conn (atom nil)]
    (conman/connect!
     conn
     pool-spec)
    conn))

(defn disconnect! [conn]
  (conman/disconnect! conn))

(mount/defstate ^:dynamic *db*
  :start (connect!)
  :stop (disconnect! *db*))

(conman/bind-connection *db* "sql/queries.sql")

;; (mount/start)
;; (mount/stop)

;; (def c (connect!))

;; (jdbc/with-db-connection [conn {:datasource c}]
;;     (let [rows (jdbc/insert! conn "user" {:first_name "first"})]
;;       (println rows)))


;; (jdbc/with-db-connection [conn {:datasource c}]
;;     (let [rows (jdbc/query conn "select * from user")]
;;       (println rows)))

(defn get-user
  [uname]
  (m/map->user
   (first
    (get-user-by-login {:email uname}))))

(comment
  (do
    (create-user! {:first_name "david" :last_name "hudson" :email "david@hudson.com", :password "1234"})
    (create-user! {:first_name "david2" :last_name "hudson2" :email "david2@hudson.com", :password "1234"})
    (create-user! {:first_name "david3" :last_name "hudson3" :email "david3@hudson.com", :password "1234"}))
  (get-user-by-login {:email "david@hudson.com"})
  (get-all-users))
