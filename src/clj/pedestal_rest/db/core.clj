(ns pedestal-rest.db.core
  (:require
   [yesql.core :refer [defqueries]]
   [config.core :refer [env]]
   [mount.core :as mount]
   [conman.core :as conman]
   ;;   [clojure.java.jdbc :as jdbc]
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

;; (jdbc/with-db-transaction [test-tran @*db*]
;;   (prn (jdbc/db-is-rollback-only test-tran))
;;   (jdbc/db-set-rollback-only! test-tran)
;;   (prn test-tran)
;;   (create-user! {:first_name "david5" :last_name "hudson5" :email "david5@hudson.com", :password "1234"} test-tran)
;;   (count (get-all-users)))

(defn get-user [uname]
  (let [user (first
              (get-user-by-login {:email uname}))]
    (if user
      (m/map->user user)
      nil)))

(defn get-business-by-user-id [user_id]
  (first
   (get-business-by-user {:user_id user_id})))

(defn get-items [business_id]
  (get-items-by-business {:business_id business_id}))
