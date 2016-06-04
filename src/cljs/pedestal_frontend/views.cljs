(ns pedestal-frontend.views
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]
            [pedestal-frontend.pages.login.login-view :as login]
            [pedestal-frontend.pages.dashboard.dashboard-view :as dashboard]
            [pedestal-frontend.pages.utils :as utils]))

;; about

(defn about-panel []
  (fn []
    [:div "This is the About Page."
     [:div [:a {:on-click #(ajax/GET "http://localhost:8080/abcd" {:error-handler utils/handle-request-failure})} "go to Home Page"]]]))

;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [dashboard/home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :login-panel [] [login/login-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      (panels @active-panel))))
