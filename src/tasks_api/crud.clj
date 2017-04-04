(ns tasks-api.crud)

(def tasks (atom '(
  {:id 1, :name "do something", :description "I've got something important to do"}
  {:id 2, :name "business meeting", :description "We're talking about important business stuff"}
  {:id 3, :name "ice cream", :description "Remember to buy ice cream for your dog"}
)))

(defn get-task 
  [task_id]
  (reduce #() (filter #(= (% :id) task_id) @tasks))
)

(defn get-tasks [] @tasks)

(defn remove-task [task_id]
  (reset! tasks 
    (remove #(= (% :id) task_id) @tasks)
  )
)

(defn add-task [task]
  (reset! tasks
    (conj @tasks task)
  )
)
