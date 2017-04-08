(ns tasks-api.crud)

(def ^:private tasks (atom '{
  1 {:id 1, :name "do something", :description "I've got something important to do"},
  2 {:id 2, :name "business meeting", :description "We're talking about important business stuff"},
  3 {:id 3, :name "ice cream", :description "Remember to buy ice cream for your dog"}
}))

(defn get-task 
  [task_id]
  (@tasks task_id))

(defn get-tasks [] (vals @tasks))

(defn remove-task [task_id]
  (reset! tasks 
    (dissoc @tasks task_id)))

(defn add-task [task]
  (reset! tasks
    (assoc @tasks (:id task) task)))
