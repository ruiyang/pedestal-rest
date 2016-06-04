(ns pedestal-frontend.pages.login.login-view
  (:require [re-frame.core :as re-frame]
            [reagent.core  :as reagent]
            [pedestal-frontend.form :as f]))

;; login
(defn login-panel []
  (let [username (reagent/atom nil)
        password (reagent/atom nil)]
    (fn []
      [:div.container.container-table
       [:div.login.form-horizontal
        [:div.form-group
         (f/form-input username {:type "email" :id "username" :placeholder "Enter user name"})]
        [:div.form-group
         (f/form-input password {:type "text" :id "password" :placeholder "Password"})]]
        [:div.form-group
         [:button.btn.btn-lg.btn-primary.btn-block
          {:on-click #(re-frame/dispatch [:login @username @password])}
          "Login"]]])))
