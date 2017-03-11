(ns marissa.config
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[marissa started successfully]=-"))
   :middleware identity})
