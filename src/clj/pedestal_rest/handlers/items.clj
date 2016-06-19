(ns pedestal-rest.handlers.items
  (:require
   [pedestal-rest.db.core :as db]
   [io.pedestal.interceptor.helpers :as ph]))

(ph/defhandler add-item
  [{:keys [json-params path-params] :as request}]
  (let [bid (:id path-params)]
    (db/add-item! (merge json-params
                         {:business_id bid
                          :item_name "name"}))))
