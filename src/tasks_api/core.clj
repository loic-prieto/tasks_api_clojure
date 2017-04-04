(ns tasks-api.core
  (:gen-class))

(defn -main
  "Acts as a cli tester for the API"
  [url method & other_arguments]
  (
    println (str "The asked url is: " url)
  )
)
