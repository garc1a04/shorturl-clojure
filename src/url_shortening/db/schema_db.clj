(ns url-shortening.db.schema-db)

(def url-schema
  [{:db/ident       :url/code
    :db/unique      :db.unique/identity
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "The code of url"}
   {:db/ident       :url/original_url
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "The url original"}])