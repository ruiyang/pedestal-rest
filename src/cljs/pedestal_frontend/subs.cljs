(ns pedestal-frontend.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))

(re-frame/register-sub
 :model-data
  (fn [db [_ & keys]]       ;; the handler for the subscription
   (reaction
    (get-in @db (into [:model :data] keys)))))
