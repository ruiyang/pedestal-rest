(ns pedestal-frontend.pages.item.item-handlers
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]
            [pedestal-frontend.pages.utils :as utils]))

(re-frame/register-handler
 :initialize-item-list
 (fn [db [_]]
   (ajax/GET "http://localhost:8080/items"
             {:response-format :json
              :keywords? true
              :handler #(re-frame/dispatch [:set-model %])
              })
   (assoc-in db [:model] {:url "http://localhost:8080/items"
                          :data {}})))


(re-frame/register-handler
 :initialize-new-item
 (fn [db [_]]
  (assoc-in db [:model] {:url "http://localhost:8080/items"
                                         :data {}})))

(re-frame/register-handler
 :create-model
 (fn [db [_]]
   (ajax/POST (get-in db [:model :url])
              {:params (get-in db [:model :data])
               :format (ajax/json-request-format)
               :response-format :json
               :keywords? true
               :handler #(utils/navigate-to "/items")
               })
   db))
