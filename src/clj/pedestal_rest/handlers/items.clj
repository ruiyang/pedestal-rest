(ns pedestal-rest.handlers.items
  (:require
   [pedestal-rest.db.core :as db]
   [io.pedestal.interceptor.helpers :as ph]
   [clojure.tools.logging :as log])
  (:import (java.util Date)))

(ph/defhandler add-item
  [{:keys [json-params path-params] :as request}]
  (let [bid  (get-in request [:identity :user :bid])]
    (db/add-item! (merge json-params
                         {:business_id bid
                          :create_date (Date.)
                          :last_modify_date (Date.) }))))

(ph/defhandler list-item
  [request]
  (let [bid (get-in request [:identity :user :bid])]
    (db/get-items-by-business {:business_id bid})))
