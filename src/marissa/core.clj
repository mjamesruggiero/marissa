(ns marissa.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn handler [request-map]
  (response/response
   (str "<html><body>Hey your IP is: "
        (:remote-addr request-map)
        " and the query string is "
        (:query-string request-map)
        ". <p>The headers are <br /><textarea cols=\"40\" rows=\"20\">"
        (:headers request-map)
        "</textarea></p></body></html>")))

(defn wrap-nocache [header]
  (fn [request]
    (-> request
        handler
        (assoc-in [:headers "Pragma"] "no-cache"))))

(defn -main []
  (jetty/run-jetty
   (-> handler var wrap-nocache wrap-reload)
   {:port 3000
    :join? false}))
