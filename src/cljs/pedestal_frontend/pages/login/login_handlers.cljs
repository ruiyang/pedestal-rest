(ns pedestal-frontend.pages.login.login-handlers
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]
            [pedestal-frontend.pages.utils :as utils]))

(re-frame/register-handler
 :login
 (fn [db [_ username password]]
   (ajax/POST "http://localhost:8080/login"
              {:params {:username username
                        :password password}
               :format (ajax/json-request-format)
               :response-format :json
               :keywords? true
               :handler #(utils/navigate-to "/")
               :error-handler  #(utils/navigate-to "/login")})
   db))
