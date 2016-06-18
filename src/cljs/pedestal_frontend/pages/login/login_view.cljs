(ns pedestal-frontend.pages.login.login-view
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]
            [reagent.core  :as reagent]
            [pedestal-frontend.form :as f]))

;; login
(defn login-panel []
  (let [model (re-frame/subscribe [:model-data])
        username (reaction (:username @model))
        password  (reaction (:password @model))]
    (fn []
      [:div.container.container-table
       [:div.login.form-horizontal
        [:div.form-group
         (f/form-input username [:username] {:type "email" :id "username" :placeholder "Enter user name"})]
        [:div.form-group
         (f/form-input password [:password] {:type "text" :id "password" :placeholder "Password"})]]
        [:div.form-group
         [:button.btn.btn-lg.btn-primary.btn-block
          {:on-click #(re-frame/dispatch [:login])}
          "Login"]]])))
