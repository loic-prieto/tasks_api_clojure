(ns tasks-api.core
  (:require [tasks-api.api :refer [process]])
  (:gen-class))

(defn -main
  "Acts as a cli tester for the API"
  [url method & other_arguments]
  (
    println (str "The asked url is: " url)
  )
)
