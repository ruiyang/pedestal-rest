(ns pedestal-frontend.pages.item.new-item-view
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require
   [re-frame.core :refer [subscribe]]
   [pedestal-frontend.form :as f]
   [pedestal-frontend.pages.main :refer [main-template]]
   [reagent.core :as r]))

(defn- add-item-form []
  (let [model (subscribe [:model])
        item-name (reaction (:item-name @model))
        code (reaction (:code @model))
        price (reaction (:price @model))
        stock (reaction (:stock @model))]
    (fn []
      [:div.form-horizontal {:role "form"}
       (f/form-input-with-label "Name:" item-name [:model :item-name] {})
       (f/form-input code [:model :code] {})
       (f/form-input price [:model :price] {})
       (f/form-input stock [:model :stock] {})
       ])))

(defn add-item []
  (let [item (subscribe [:model])]
    (fn []
      [:div.container-fluid
       [:h1 "Add Item"]
       [add-item-form]
       [:div
        [:button.btn.btn-success.pulrightht.col-sm-3
         {:style {:max-width "100px"}
          :on-click #(js/alert @item)}
         "Add Item"]]]
      )))

(defn new-item-view []
  [main-template add-item])
