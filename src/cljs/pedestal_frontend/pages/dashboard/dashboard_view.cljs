(ns pedestal-frontend.pages.dashboard.dashboard-view
  (:require [re-frame.core :as re-frame]
            [pedestal-frontend.pages.main :refer [main-template]]))

(defn order-list []
  (let [orders [{:to "def"}]]
    (fn []
      [:div.orders (map (fn [item]
                   ^{:key item} [:div (:to item)]) orders)])))

(defn order-management-view []
  (fn []
    [main-template order-list]))

;;
(defn home-view []
  [:div "Dashboard"])

(defn home-panel []
  (let [name (re-frame/subscribe [:name])
        orders [{:id 1 :to "david"}]]
    [main-template home-view]))
