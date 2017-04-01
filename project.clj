(defproject tasks_api "0.1.0-SNAPSHOT"
  :description "A tasks API done in clojure"
  :url "https://github.com/loic-prieto/tasks_api_clojure"
  :license {:name "AGPL"
            :url "https://www.gnu.org/licenses/agpl.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot tasks-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
