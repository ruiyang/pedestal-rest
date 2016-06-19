(ns pedestal-frontend.pages.item.item-view
  (:require [pedestal-frontend.pages.main :refer [main-template]]
            [re-frame.core :refer [subscribe]]))

(defn- item-row [item]
  [:tr.row
   [:td (:item_name item)]
   [:td (:code item)]
   [:td (:price item)]
   [:td (:stock item)]
   [:td (:description item)]])

(defn- item-list []
  (let [items (subscribe [:model-data])]
    [:div.container
     [:h1 "My Stock List"]
     [:a {:href "#/items/new"} "Add New"]
     [:table.table
      [:theader
       [:tr.row
        [:th "Name"]
        [:th "Code"]
        [:th "Price"]
        [:th "Stock"]
        [:th "Description"]]]
      [:tbody
       (map item-row @items)]]]))

(defn item-list-view []
  [main-template item-list])
