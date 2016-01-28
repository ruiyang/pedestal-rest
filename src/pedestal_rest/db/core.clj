(ns pedestal-rest.db.core
  (:require
   [yesql.core :refer [defqueries]]))

(def conn
  {:classname   "org.h2.Driver"
   :connection-uri "jdbc:h2:./db/my-webapp"
   :make-pool?     true
   :naming         {:keys   clojure.string/lower-case
                    :fields clojure.string/upper-case}})

(defqueries "sql/queries.sql" {:connection conn})

(defn get-user
  [uname]
  (first
   (get-user-by-login {:email uname})))

(comment

  (do
    (create-user! {:first_name "david" :last_name "hudson" :email "david@hudson.com", :pass "1234"})
    (create-user! {:first_name "david2" :last_name "hudson2" :email "david2@hudson.com", :pass "1234"})
    (create-user! {:first_name "david3" :last_name "hudson3" :email "david3@hudson.com", :pass "1234"}))
  (get-user-by-login {:email "david@hudson.com"})

  )
