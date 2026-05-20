(ns url-shortening.db.config
  (:require [datomic.client.api :as d]
            [url-shortening.db.schema-db :as schema]))

(def client (d/client {:server-type :dev-local
                       :system "dev"
                       :storage-dir "C:/Users/guiga/documents/datomic-local"}))

(d/delete-database client {:db-name "urls"})

(d/create-database client {:db-name "urls"})

(def conn (d/connect client {:db-name "urls"}))

(d/transact conn {:tx-data schema/url-schema})