(ns pedestal-frontend.pages.login.login-handlers
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]
            [pedestal-frontend.pages.utils :as utils]))

(re-frame/register-handler
 :login
 (fn [db [_]]
   (ajax/POST "http://localhost:8080/login"
              {:params {:username (get-in db [:model :data :username])
                        :password (or (get-in db [:model :data :password]) "")}
               :format (ajax/json-request-format)
               :response-format :json
               :keywords? true
               :handler #(utils/navigate-to "/")
               :error-handler  #(utils/navigate-to "/login")})
   db))
