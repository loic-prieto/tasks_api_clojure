(ns tasks-api.api
  (:use 
    [tasks-api.router :refer [ create-router ]]
    [tasks-api.crud   :refer [ get-task get-tasks ]]))

(defn get-api-router 
  []
  (create-router `(
    { :path "/tasks"    , :method "GET", :handler ~get-tasks},
    { :path "/tasks/{1}", :method "GET", :handler ~get-task })))
