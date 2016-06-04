(ns pedestal-frontend.pages.utils
  (:import goog.History))

(defonce browserHistory (History.))

(defn navigate-to [token]
  (.setToken browserHistory token))

(defn handle-request-failure []
  (navigate-to "/login"))
