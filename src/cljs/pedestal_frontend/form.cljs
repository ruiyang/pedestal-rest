(ns pedestal-frontend.form
  (:require [re-frame.core :as re-frame]))

(defn form-input
  "An input element which updates its value on change"
  [value keys & options]
  [:input.form-control (merge
                        {:value @value
                         :on-change #(re-frame/dispatch [:update-model keys (-> % .-target .-value)])}
                        (first options))])

(defn form-input-with-label
  "An input with label"
  [label value keys & options]
  [:div.form-group
   [:lable label]
    [form-input value keys (first options)]])
