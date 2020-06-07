(ns inventory.core
  (:gen-class))

(defn find-by-title [title books]
  "Search for a book by title,
  where title is a string and books is a collection
  of book maps, each of which must have a :title entry"
  (some #(when (= (:title %) title) %) books))

(defn numbers-of-copies-of
  "Return the number copies in inventory of the given title,
  where title is string and books is a collection of book maps
  each of which must have a :title entry"
  [title books]
  (:copies (find-by-title title books)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
