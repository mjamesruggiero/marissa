(ns marissa.core)

(-> (.getElementById js/document "content")
    (.-innerHTML)
    (set! "Hello World!"))
