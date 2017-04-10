(ns tasks-api.core
  (:use [tasks-api.api :refer [get-api-router]])
  (:gen-class))

(defn env-from-params [ method uri ]
  {:REQUEST_METHOD method :REQUEST_URI uri})

(defn print-task [task]
  (let [{:keys [id name]} task]
    (println (str "Task " id ": " name))))

(defn print-results
  [results]
  (if (seq? results)
    (when-not (empty? results)
      (do
        (print-results (first results))
        (print-results (rest results))))
    (print-task results)))

(defn- eval-api [input router]
  (let [[method uri] (clojure.string/split input #" ")]
    (router (env-from-params method uri))))

(defn- read-api []
  (print "api> " )
  (flush)
  (read-line))

(defn- loop-api []
  (let [ input (read-api)
         router (get-api-router)]
    (if (or (= "quit" input) (= "exit" input))
      (print "bye\n")
      (do
        (print-results (eval-api input router))
        (recur)))))

(defn api-repl []
  (loop-api))

(defn test-api
  "Acts as a cli tester for the API"
  [method uri]
  (let [router (get-api-router)]
    (print-results (router (env-from-params method uri)))))

(defn -main
  "Acts as a cli tester for the API"
  []
  (api-repl))
