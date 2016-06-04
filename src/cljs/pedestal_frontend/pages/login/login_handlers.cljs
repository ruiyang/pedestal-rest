(ns pedestal-frontend.pages.login.login-handlers
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]
            [pedestal-frontend.pages.utils :as utils]))

(re-frame/register-handler
 :login
 (fn [db [_ a b]]
   (.log js/console a b)
   (ajax/GET "http://localhost:8080"
             {:handler #(utils/navigate-to "/")
              :error-handler  #(utils/navigate-to "/login")})
   db))
