(ns tasks-api.router
  (:require [clojure.string :as str]))

(defn- normalize-uri
  "Generalize a given uri to path and args"
  [uri]
  (let [[root entity entity-id] (str/split uri #"/")]
    (if (not (nil? entity-id))
      `(~(str "/" entity "/{1}") ~entity-id)
      `(~(str "/" entity)))))

(defn- normalize-routes
  "Given a dsl-formatted routes data structure, 
  converts it to a normalized view for better handling"
  ([routes-dsl] (normalize-routes routes-dsl {}))
  ([routes-dsl normalized-routes]
    (let [route (first routes-dsl)]
      (if (nil? route)
        normalized-routes
        (normalize-routes 
          (rest routes-dsl)
          (assoc-in normalized-routes [(route :method) (route :path)] (route :handler) ))))))

(defn- execute-handler
  "Execute the handler associated to a route"
  [method uri routes]
  (let [method-routes (routes method)
        [normalized-uri id] (normalize-uri uri)]
    (let [handler (method-routes normalized-uri)]
      (when-not (nil? handler)
        (if (nil? id)
          (handler)
          (handler (read-string id)))))))

(defn create-router
  "Builds a router from a route definition list.
  The router is a function that for a given environment
  hash, will execute the appropriate handler"
  [route-list]
  (fn [env] 
    (let [method (env :REQUEST_METHOD)
          uri (env :REQUEST_URI)
          normalized-routes (normalize-routes route-list)]
      (execute-handler method uri normalized-routes))))
