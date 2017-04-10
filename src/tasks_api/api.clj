(ns tasks-api.api
  (:use 
    [tasks-api.router :refer [create-router route]]
    [tasks-api.crud   :refer [get-task get-tasks]]))

(defn get-api-router
  []
  (create-router
   (route GET "/tasks" get-tasks)
   (route GET "/tasks/{1}" get-task)))
