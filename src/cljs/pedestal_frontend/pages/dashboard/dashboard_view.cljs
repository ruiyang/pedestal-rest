(ns pedestal-frontend.pages.dashboard.dashboard-view
  (:require [re-frame.core :as re-frame]))

(defn main-template [content]
  [:div
   [:nav.navbar.navbar-inverse.navbar-fixed-top
    [:div.container-fluid
     [:div.navbar-header
      [:button.navbar-toggle.collapsed {:type "button" :data-toggle "collapse" :data-target "#navbar" :aria-expanded "false" :aria-controls "navbar"}
       [:span.sr-only "Toggle navigation"]
       [:span.icon-bar]
       [:span.icon-bar]
       [:span.icon-bar]       ]
      [:a.navbar-brand {:href "#" } "Project name"]      ]
     [:div.navbar-collapse.collapse {:id "navbar" :aria-expanded "false" :style {:height "1px"}}
      [:ul.nav.navbar-nav.navbar-right
       [:li [:a { :href "#/orders" } "Orders"]]
       [:li [:a { :href "#/stock" } "Stock"]]
       [:li [:a { :href "#/client" } "Client"]]
       [:li [:a { :href "#" } "Help"]]]]]]
   [:div.container-fluid
    [content]]])

(defn order-list []
  (let [orders [{:to "def"}]]
    (fn []
      [:div (map (fn [item]
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
