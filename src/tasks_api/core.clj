(ns tasks-api.core
  (:use [tasks-api.api :refer [get-api-router]])
  (:gen-class))

(defn env-from-params [ method uri ]
  {:REQUEST_METHOD method :REQUEST_URI uri})

(defn print-task [task]
  (let [{:keys [id name]} task]
    (println (str "Task " id ": " name))))

(defn print-results
  [result]
  (if (seq? result)
    (when-not (empty? result)
      (do
        (print-results (first result))
        (print-results (rest result))))
    (print-task result)))

(defn -main
  "Acts as a cli tester for the API"
  [method uri]
  (let [router (get-api-router)]
    (print-results (router (env-from-params method uri)))))

(defn- eval-api [input router]
  (let [[method uri] (clojure.string/split input #" ")]
    (router (env-from-params method uri))))

(defn- print-api [results]
  (print-results results))

(defn- read-api []
  (print "api> " )
  (flush)
  (read-line))

(defn- loop-api []
  (let [input (read-api)]
    (if (= "quit" input)
      (print-api "bye")
      (do
        (-> input (eval-api (get-api-router)) print-api)
        (recur)))))

(defn api-repl []
  (loop-api))

(defn test-api
  "Acts as a cli tester for the API"
  [method uri]
  (let [router (get-api-router)]
    (print-results (router (env-from-params method uri)))))
