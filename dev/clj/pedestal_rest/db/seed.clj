(ns pedestal-rest.db.seed
  (:require [buddy.hashers :as hasers]
            [pedestal-rest.db.core :as db]
            [buddy.hashers :as hashers]
            [clojure.java.jdbc :as jdbc]))

(defn seed []
  (do
    (db/create-user! {:first_name "david" :last_name "hudson" :user_name "dev" :user_type 1 :email "dev", :password (hashers/encrypt "")})
    (db/create-user! {:first_name "david2" :last_name "hudson" :user_name "dev2" :user_type 2 :email "dev2", :password (hashers/encrypt "")})
    (db/add-business! {:user_id (:id (db/get-user "dev")) :business_name "dev's business"})
    (db/add-business! {:user_id (:id (db/get-user "dev2")) :business_name "dev2's business"})
    (let [u1 (db/get-user "dev")
          u2 (db/get-user "dev2")
          b1 (db/get-business-by-user-id (:id (db/get-user "dev")))
          b2 (db/get-business-by-user-id (:id u2))
          u1-i1 (db/add-item! {:business_id (:id b1)
                               :description "item1 desc"
                               :code "1234"
                               :price 50
                               :stock 20
                               :create_date "2016-05-05"
                               :last_modify_date "2016-05-05"
                               })
          u1-i2 (db/add-item! {:business_id (:id b1)
                               :description "item2 desc"
                               :code "2345"
                               :price 30
                               :stock 60
                               :create_date "2016-05-05"
                               :last_modify_date "2016-05-05"
                               })
          ]
      )))

(defn clean-db []
  (jdbc/delete! @db/*db* :userbusiness "")
  (jdbc/delete! @db/*db* :business "")
  (jdbc/delete! @db/*db* :item "")
  (jdbc/delete! @db/*db* :user ""))

(comment
  (do
    (db/get-user-by-login {:email "dev2"})
    (db/get-user "dev")
    (db/get-all-users)))


(comment
  {:user
   {
    :user_name "dev"
    :business "business"
    }})
