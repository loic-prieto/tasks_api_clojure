(ns tasks-api.api
  (:require 
    [tasks-api.router :refer [ add-route handle ]]
    [tasks-api.crud   :refer [ get-task get-tasks ]]
  )
)

(tasks-api.router/add-route "/tasks" "GET" tasks-api.crud/get-tasks)
(tasks-api.router/add-route "/tasks/{id}" "GET" tasks-api.crud/get-task)

(defn process [env] (
  (let [ {method :REQUEST_METHOD uri :PATH_INFO} env]
    (task-api.router/handle uri method)
  )
))
