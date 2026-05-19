(ns url-shortening.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json]
            [ring.util.response :as response]
            [url-shortening.encurtar :as encurtar]
            [url-shortening.db.commands-db :as db]
            [reitit.ring :as ring]
            [reitit.swagger :as swagger]
            [reitit.swagger-ui :as swagger-ui]
            [reitit.ring.coercion :as coercion]
            [reitit.coercion.malli :as malli]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]))

(def api "http://localhost:3000/api/")

(defn json [conteudo & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/generate-string conteudo)})

(defn- redirect [code]
  (let [url (:url (first (db/find-by-code code)))]
    (if (nil? url)
      (json {:message "Code not found"} 404)
      (response/redirect url))))

(defn- encurtar [requisicao]
  (let [req (:body requisicao)]
    (if (encurtar/valid? req)
      (json (or (encurtar/return-url-existed req api)
                (encurtar/register-url req api)))
      (json {:mensagem "Requisição inválida"} 422))))

(defroutes app-routes
  (GET "/api/health" []
    (json {:ok true}))

  (GET "/api" []
    (json {:data (db/find-url)}))

  (GET "/api/:code" [code]
    (json {:data
           (db/find-by-code code)}))

  (GET "/api/redirect/:code" [code]
    (redirect code))

  (POST "/api" requisicao
    (encurtar requisicao))

  (route/not-found (json {:mensagem "not found"} 404)))
