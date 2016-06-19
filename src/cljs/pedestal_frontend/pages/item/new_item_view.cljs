(ns pedestal-frontend.pages.item.new-item-view
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [pedestal-frontend.form :as f]
   [pedestal-frontend.pages.main :refer [main-template]]
   [reagent.core :as r]))

(defn- add-item-form []
  (let [model (subscribe [:model-data])
        item-name (reaction (:item_name @model))
        code (reaction (:code @model))
        description (reaction (:description @model))
        price (reaction (:price @model))
        stock (reaction (:stock @model))]
    (fn []
      [:div.form-horizontal {:role "form"}
       (f/form-input-with-label "Name:" item-name [:item_name] {})
       (f/form-input-with-label "Code:" code [:code] {})
       (f/form-input-with-label "Description:" description [:description] {})
       (f/form-input-with-label "Price:" price [:price] {})
       (f/form-input-with-label "Stock:" stock [:stock] {})
       ])))

(defn add-item []
  (fn []
    [:div.container-fluid
     [:h1 "Add Item"]
     [add-item-form]
     [:div
      [:button.btn.btn-success.pulrightht.col-sm-3
       {:style {:max-width "100px"}
        :on-click #(dispatch [:create-model])}
       "Add Item"]]]
    ))

(defn new-item-view []
  [main-template add-item])
