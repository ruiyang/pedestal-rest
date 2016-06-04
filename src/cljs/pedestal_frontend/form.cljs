(ns pedestal-frontend.form)

(defn form-input
  "An input element which updates its value on change"
  [value options]
  [:input.form-control (merge options
                 {:value @value
                  :on-change #(reset! value (-> % .-target .-value))})])
