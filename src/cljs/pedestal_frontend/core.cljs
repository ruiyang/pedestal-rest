(ns pedestal-frontend.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [pedestal-frontend.handlers]
              [pedestal-frontend.subs]
              [pedestal-frontend.routes :as routes]
              [pedestal-frontend.views :as views]
              [pedestal-frontend.config :as config]
              [figwheel.client :as fw]))

(fw/watch-and-reload
 :websocket-url "ws://localhost:3449/figwheel-ws"
 :jsload-callback
 (fn []
   (println "reloaded")))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
