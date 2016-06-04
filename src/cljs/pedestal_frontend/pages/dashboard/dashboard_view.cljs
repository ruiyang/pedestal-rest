(ns pedestal-frontend.pages.dashboard.dashboard-view
  (:require [re-frame.core :as re-frame]))

(defn home-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div (str "Hello from " @name ". This is the Home Page.")
       [:div [:a {:href "#/about"} "go to About Page"]]
       [:div [:a {:href "#/login"} "go to Login Page"]]])))
