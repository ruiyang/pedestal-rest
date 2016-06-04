(ns pedestal-frontend.handlers
  (:import goog.History)
  (:require [re-frame.core :as re-frame]
            [pedestal-frontend.db :as db]
            [ajax.core :as ajax]
            [pedestal-frontend.pages.login.login-handlers]
            [secretary.core :as secretary]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
