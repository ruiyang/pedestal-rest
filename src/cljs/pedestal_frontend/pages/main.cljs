(ns pedestal-frontend.pages.main)

(defn main-template [content]
  [:div
   [:nav.navbar.navbar-inverse
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
       [:li [:a { :href "#/items" } "Items"]]
       [:li [:a { :href "#/clients" } "Client"]]
       [:li [:a { :href "#" } "Help"]]]]]]
   [:div.container-fluid
    [content]]])
