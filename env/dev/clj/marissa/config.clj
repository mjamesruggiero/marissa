(ns marissa.config
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [marissa.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[marissa started successfully using the development profile]=-"))
   :middleware wrap-dev})
