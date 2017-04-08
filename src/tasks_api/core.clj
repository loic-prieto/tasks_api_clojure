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
