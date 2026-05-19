(ns url-shortening.encurtar
  (:require [url-shortening.db.config :as config]
            [url-shortening.db.commands-db :as db]))

(defn valid? [requisicao]
  (and (contains? requisicao :url)
       (string? (:url requisicao))))

(defn- letter-random []
  (let [number (.intValue (rand 25))]
    (char (+ number 65))))

(defn- generate-code [numbers]
  (reduce str (repeatedly numbers letter-random)))

(defn register-url [requisicao api]
  (let [url (:url requisicao)
        code  (generate-code 5)
        format {:code code :url url}]
    (db/upsert-one! config/conn format)
    {:data {:code code
            :url url
            :url-short (str api code)
            :url-redirect (str api "redirect/" code)}}))

(defn return-url-existed [requisicao api]
  (let [url (:url requisicao)
        code (:code (first (db/find-by-url url)))]
    (when (not (nil? code))
      {:data {:code code
              :url url
              :url-short (str api code)
              :url-redirect (str api "redirect/" code)}})))