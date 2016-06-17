(ns pedestal-frontend.handlers
  (:require-macros [reagent.ratom :refer [reaction]])  ;; reaction is a macro
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

(re-frame/register-handler
 :update-model
 (fn [db [_ keys value]]
   (assoc-in db keys value)))
