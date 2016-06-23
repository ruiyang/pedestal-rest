(ns pedestal-frontend.config
  (:require [pedestal-frontend.db :as db]
            [ajax.core :as ajax]))

(def debug?
  ^boolean js/goog.DEBUG)

(when debug?
  (enable-console-print!))

(def token-interceptor
     (ajax/to-interceptor {:name "Token Interceptor"
                           :request #(assoc-in % [:headers :Authorization] (str "Token "(:auth @db/local-store)))}))

(swap! ajax/default-interceptors (partial cons token-interceptor))
