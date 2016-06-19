(ns pedestal-rest.handlers.items
  (:require
   [pedestal-rest.db.core :as db]
   [io.pedestal.interceptor.helpers :as ph])
  (:import (java.util Date)))

(ph/defhandler add-item
  [{:keys [json-params path-params] :as request}]
  (let [bid (:id path-params)]
    (db/add-item! (merge json-params
                         {:business_id bid
                          :create_date (Date.)
                          :last_modify_date (Date.) }))))

(ph/defhandler list-item
  [{:keys [json-params path-params] :as request}]
  (let [bid (:id path-params)]
    (db/get-items-by-business {:business_id bid})))
