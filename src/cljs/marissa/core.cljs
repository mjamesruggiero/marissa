(ns marissa.core
  (:require [reagent.core :as reagent :refer [atom]]
            [ajax.core :refer [GET POST]]))

(defn send-message! [fields]
  (POST "/add-message"
      {:params @fields
       :handler #(.log js/console (str "response:" %))
       :error-handler #(.error js.console (str "error:" %))}))

(defn home []
  [:div.row
   [:div.span12
    [message-form]]])

(defn message-form []
  (let [fields (atom {})]
    (fn []
      [:div.content
       [:div.form-group
        [:p "Name:"
         [:input.form-control
          {:type :text
           :name :name
           :on-change #(swap! fields assoc :name (-> % .-target .-value))
           :value (:name @fields)}]]]
       [:p "Message"
        [:textarea.form-control
         {:rows 4
          :cols 50
          :name :message
          :on-change #(swap! fields assoc :message (-> % .-target .-value))}
         (:message @fields)]]
       [:input.btn.btn-primary
        {:type :submit
         :on-click #(send-message! fields)
         :value "comment"}]])))

(reagent/render
 [home]
 (.getElementById js/document "content"))

