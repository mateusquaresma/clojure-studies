(ns getting-clojure-chapters.chapter-11)

(def jack "All work and no play makes Jack a dull boy.")
(def repeated-text (repeat jack))
(take 7 (cycle [1 2 3]))
(def numbers (iterate inc 1))

(def titles (map #(str "Wheel of Time " %) numbers))

(def first-names ["Bob" "Jane" "Chuck" "Leo"])
(def last-names ["Jordan" "Austen" "Dickens" "Tolstoy" "Poe"])
(defn combine-names [fname lname]
  (str fname " " lname))

(def authors
  (map combine-names
       (cycle first-names)
       (cycle last-names)))

(defn make-book [title author]
  {:title title :author author})

(def test-books
  (map make-book titles authors))

(lazy-seq [1 2 3])

(defn chatty-vector []
  (println "Here we go!")
  [1 2 3])

(def lazy (lazy-seq (chatty-vector)))