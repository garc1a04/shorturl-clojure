(ns url-shortening.db.commands-db
  (:require [datomic.client.api :as d]
            [url-shortening.db.config :as config]))

(defn upsert-one!
  [conn {:keys [code url]}]
  (println code url)
  (d/transact conn {:tx-data [[:db/add "temporary-new-db-id" :url/code code]
                              [:db/add "temporary-new-db-id" :url/original_url url]]}))

(defn find-url []
  (d/q '[:find ?code ?url
         :keys code url
         :where
         [?e :url/code ?code]
         [?e :url/original_url ?url]]
       (d/db config/conn)))

(defn find-by-code [code]
  (d/q '[:find ?url
         :keys url
         :in $ ?send_code
         :where
         [?e :url/code ?send_code]
         [?e :url/original_url ?url]]
       (d/db config/conn)
       code))

(defn find-by-url [url]
  (d/q '[:find ?code
         :keys code
         :in $ ?send_url
         :where
         [?e :url/code ?code]
         [?e :url/original_url ?send_url]]
       (d/db config/conn)
       url))