(ns pedestal-rest.db.seed
  (:require [buddy.hashers :as hasers]
            [pedestal-rest.db.core :as db]
            [buddy.hashers :as hashers]))

(defn seed []
  (do
    (db/create-user! {:first_name "david" :last_name "hudson" :email "dev", :password (hashers/encrypt "1234")})
    (db/create-user! {:first_name "david2" :last_name "hudson" :email "dev2", :password (hashers/encrypt "1234")})
    ))

(comment
  (do
    (db/get-user-by-login {:email "dev2"})
    (db/get-user "dev")
    (db/get-all-users))
