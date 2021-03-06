(defproject pedestal-rest "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [io.pedestal/pedestal.service "0.4.1"]
                 [prismatic/schema "1.0.5"]

                 ;; Remove this line and uncomment one of the next lines to
                 ;; use Immutant or Tomcat instead of Jetty:
                 [io.pedestal/pedestal.jetty "0.4.1"]
                 ;; [io.pedestal/pedestal.immutant "0.4.1"]
                 ;; [io.pedestal/pedestal.tomcat "0.4.1"]

                 [ch.qos.logback/logback-classic "1.1.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.12"]
                 [org.slf4j/jcl-over-slf4j "1.7.12"]
                 [org.slf4j/log4j-over-slf4j "1.7.12"]
                 [buddy/buddy-auth "0.9.0"]
                 [buddy/buddy-hashers "0.11.0"]
                 [clj-time "0.11.0"]
                 [conman "0.3.0"]
                 ;;                 [hikari-cp "1.5.0"]
                 ;;                 [yesql "0.5.2"]
                 [luminus/config "0.5"]
                 [mount "0.1.9"]

                 ;; possible dev profile dependency
                 [migratus "0.8.8"]
                 [com.h2database/h2 "1.4.190"]

                 ;; cljs frontend stuff
                 [reagent "0.5.1"]
                 [re-frame "0.7.0"]
                 [secretary "1.2.3"]
                 [cljs-ajax "0.5.5"]
                 [alandipert/storage-atom "1.2.4"]]
  :min-lein-version "2.0.0"
  :plugins [[migratus-lein "0.2.0"]
            [lein-environ "1.0.1"]
            [lein-figwheel "0.5.0-1"]]
  :resource-paths ["config", "resources", "db"] ;; db should be dev resources
  :source-paths ["src/clj"]
  :profiles {:dev
             {:aliases {"run-dev" ["trampoline" "run" "-m" "pedestal-rest.server/run-dev"]}
              :dependencies [[io.pedestal/pedestal.service-tools "0.4.1"]
                             [com.cemerick/piggieback "0.2.1"]
                             [org.clojure/tools.nrepl "0.2.11"]
                             [figwheel-sidecar "0.5.2"]]
              :env {:database-url "jdbc:h2:./db/my-webapp"}
              :source-paths ["src/cljs" "dev/clj"]}
             :uberjar {:aot [pedestal-rest.server]}
             :test {:env
                    {:database-url  "jdbc:h2:./db/my-webapp-test"}}}

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src/cljs"] ;;<--- note this isn't in source-paths above
                :figwheel {:on-jsload "pedestal-frontend.core/mount-root"}
                :compiler {:main pedestal-frontend.core
                           :asset-path "js/out"
                           :output-to "resources/public/js/app.js"
                           :output-dir "resources/public/js/out" }}]}

  :figwheel {:css-dirs ["resources/public/css"]}

  :main ^{:skip-aot true} pedestal-rest.server

  :migratus {:store :database
             :migration-dir "migrations"
             :db  {:classname "org.h2.Driver"
                   :subprotocol "h2:file"
                   :subname "./db/my-webapp"}}
  )
