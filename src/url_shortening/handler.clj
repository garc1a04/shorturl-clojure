(ns url-shortening.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body]]
            [url-shortening.routes :as routes]))

(def app
  (-> 
   (wrap-defaults routes/app-routes api-defaults) 
   (wrap-json-body)))