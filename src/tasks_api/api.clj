(ns tasks-api.api
  (:use 
    [tasks-api.router :refer [create-router GET]]
    [tasks-api.crud   :refer [get-task get-tasks]]))

(defn get-api-router
  []
  (create-router
   (GET "/tasks" get-tasks)
   (GET "/tasks/{1}" get-task)))
