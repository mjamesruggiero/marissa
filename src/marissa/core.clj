(ns marissa.core
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :as compojure]
            [ring.util.http-response :as response]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn response-handler [request]
  (response/ok
   (str
    "<html><body>Your IP is: "
    (:remote-addr request)
    "</body></html>")))

(def handler
  (compojure/routes
   (compojure/GET "/" request response-handler)
   (compojure/GET "/:id" [id] (str "<p>the id is: " id "</p>"))
   (compojure/POST "/json" [id] (response/ok {:result id}))))

(defn wrap-nocache [handler]
  (fn [request]
    (-> request
        handler
        (assoc-in [:headers "Pragma"] "no-cache"))))

(defn -main []
  (jetty/run-jetty
   (-> #'handler wrap-nocache wrap-reload)
   {:port 3000
    :join? false}))
