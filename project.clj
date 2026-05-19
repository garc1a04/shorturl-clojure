(defproject url_shortening "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.11.2"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-core "1.11.0"]
                 [ring/ring-jetty-adapter "1.11.0"]
                 [com.datomic/client-cloud "1.0.131"]
                 [com.datomic/local "1.0.277"]
                 [metosin/reitit "0.7.0"]
                 [metosin/malli "0.13.0"]
                 [cheshire "5.8.1"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler url-shortening.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})