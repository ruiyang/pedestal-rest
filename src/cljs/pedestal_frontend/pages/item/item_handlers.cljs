(ns pedestal-frontend.pages.item.item-handlers
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]
            [pedestal-frontend.pages.utils :as utils]))

(re-frame/register-handler
 :initialize-new-item
 (fn [db [_]]
   (let [ new-db  (assoc-in db [:model] {:url "http://localhost:8080/business/8/items"
                                         :data {}})]
     new-db)))

 (re-frame/register-handler
  :create-model
  (fn [db [_]]
    (ajax/POST (get-in db [:model :url])
               {:params (get-in db [:model :data])
                :format (ajax/json-request-format)
                :response-format :json
                :keywords? true
                :handler #(utils/navigate-to "/")
                })
    db))
