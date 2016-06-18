(ns pedestal-rest.handlers.items
  (:require
   [pedestal-rest.db.core :as db]
   [io.pedestal.interceptor.helpers :as ph]))

(ph/defhandler add-item
  [{:keys [json-params] :as request}]
  (db/add-item! json-params))
