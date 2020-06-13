(ns getting-clojure-chapters.chapter-16
  (:import (java.io File)
           (java.awt Rectangle)
           (com.google.gson Gson)
           (java.util ArrayList)))

(def authors (File. "authors.txt"))
(if (.exists authors)
  (println "Our authors file is there")
  (println "Our authors file is missing"))

;; accessing fields has an odd sintax
(def rect (Rectangle. 0 0 10 20))
(println "Width:" (.-width rect))
(println "Height:" (.-height rect))

(def temp-authors-file (File/createTempFile "authors_list" ".txt"))

(def gson-obj (Gson.))
(.toJson gson-obj 44)
(.toJson gson-obj {:title "1984" :author "Orwell"})

;; turning methods into functions

(def count-fn (memfn .count))
(def files [(File. "authors.txt") (File. "titles.txt")])
(map (memfn exists) files)

(def jv-favorite-books (ArrayList.))
(.add jv-favorite-books "Emma")
(.add jv-favorite-books "Andromeda Strain")
(.add jv-favorite-books "2001")

(def immutable-books (vec jv-favorite-books))

