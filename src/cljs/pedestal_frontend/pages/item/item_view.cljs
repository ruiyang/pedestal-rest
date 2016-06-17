(ns pedestal-frontend.pages.item.item-view
  (:require [pedestal-frontend.pages.main :refer [main-template]]))

(defn- item-list []
  [:div.container
   [:h1 "My Stock List"]
   [:a {:href "#/items/new"} "Add New"]
   [:table.table
    [:theader
     [:tr.row
      [:th "ID"]
      [:th "Name"]
      [:th "Description"]]]
    [:tbody
     [:tr.row
      [:td "1"]
      [:td "2"]
      [:td "3"]]]]]
  )

(defn item-list-view []
  [main-template item-list])
