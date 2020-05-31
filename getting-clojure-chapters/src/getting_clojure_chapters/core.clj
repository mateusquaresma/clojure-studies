(ns getting-clojure-chapters.core
  (:require [getting-clojure-chapters.chapter-9 :as chapter-9])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (chapter-9/discount-price {:title "Emma" :price 9.99}) )
  (println "Hello, World!"))
