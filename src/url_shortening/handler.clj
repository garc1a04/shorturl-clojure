(ns url-shortening.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [url-shortening.routes :as routes]))


(defn docs [handler]
  (println "Documentation running on port http://localhost:3000/docs")
  handler)

(def app
  (-> routes/app-routes
   (wrap-defaults api-defaults)))