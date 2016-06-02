(ns pedestal-frontend.views
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]))

;; login
(defn login-panel []
  [:div.container.container-table

   [:div.login.form-horizontal
    [:div.form-group
     [:input.form-control {:type "email" :id "username" :placeholder "Enter user name"}]]
    [:div.form-group
     [:input.form-control {:placeholder "Password"}]]
    [:div.form-group
     [:button.btn.btn-lg.btn-primary.btn-block
      {:on-click #(re-frame/dispatch [:login "abc" "def"])}
      "Login"]]]])

;; home

(defn home-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div (str "Hello from " @name ". This is the Home Page.")
       [:div [:a {:href "#/about"} "go to About Page"]]])))


;; about

(defn handle-request-failure []
  (re-frame/dispatch [:set-active-panel :login-panel]))

(defn about-panel []
  (fn []
    [:div "This is the About Page."
     [:div [:a {:on-click #(ajax/GET "http://localhost:8080/abcd" {:error-handler handle-request-failure})} "go to Home Page"]]]))


;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :login-panel [] [login-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      (panels @active-panel))))
