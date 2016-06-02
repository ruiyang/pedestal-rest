(ns pedestal-frontend.handlers
  (:import goog.History)
  (:require [re-frame.core :as re-frame]
            [pedestal-frontend.db :as db]
            [ajax.core :as ajax]
            [secretary.core :as secretary]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(def h (History.))

(defn goto [token]
  (.setToken h token))

(re-frame/register-handler
 :login
 (fn [db [_ a b]]
   (ajax/GET "http://localhost:8080"
             {:handler #(goto "/about")
              :error-handler  #(goto "/login")})
   db))
