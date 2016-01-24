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

;; (create-user! {:first_name "first3" :last_name "last3" :email "abc@gmail.com", :pass "pass"})

;; (def all (get-all-users))

;; (:first_name (last all))
