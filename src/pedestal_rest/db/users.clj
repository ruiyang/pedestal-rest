(ns pedestal-rest.db.users)

(def users [{:name "admin" :pwd "admin"}
            {:name "test" :pwd "test"}
            ])

(defn get-user [name]
  (filter #(= (:name %) name)users))
