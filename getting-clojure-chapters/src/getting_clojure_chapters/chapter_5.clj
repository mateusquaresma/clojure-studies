(ns getting-clojure-chapters.chapter-5)

(defn greet
  ([to-whom] (greet "Welcome to Blotts Books" to-whom))
  ([message to-whom] (println message to-whom)))

(defn print-any-args
  [& args]
  (println "My arguments are: " args))

(defn first-argument
  [args]
  (first args))

(defn new-first-argument
  [x & args] x)

(def book1 ["1984" "Orwell"])
(def book2 {:title "War and Peace" :author "Tolstoy"})
(def book3 {:book "Emma" :by "Austen"})

;; Multi-methods

(defn dispatch-book-format
  [book]
  (cond
    (vector? book) :vector-book
    (contains? book :title) :standard-map
    (contains? book :book) :alternative-map))

(defmulti normalize-book dispatch-book-format)
(defmethod normalize-book :vector-book [book]
  {:title (first book) :author (second book)})
(defmethod normalize-book :standard-map [book]
  book)
(defmethod normalize-book :alternative-map [book]
  {:title (:book book) :author (:by book)})

;; using the multi-method

(normalize-book book1)
(normalize-book book2)
(normalize-book book3)

;; Another example
(defn dispatch-published [book]
  (cond
    (< (:published book) 1928) :public-domain
    (< (:published book) 1978) :old-copyright
    :else                      :new-copyright))

(def books
  [{:title "Jaws" :copies-sold 2000000}
   {:title "Emma" :copies-sold 3000000}
   {:title "2001" :copies-sold 4000000}])

(defn sum-copies-1
  ([books] (sum-copies-1 books 0))
  ([books acc]
   (if (empty? books)
     acc
     (sum-copies-1 (rest books) (+ acc (:copies-sold books))))))

;; Same as above but uses tail call optimization through "recur"
(defn sum-copies-2
  ([books] (sum-copies-2 books 0))
  ([books acc]
   (if (empty? books)
     acc
     (recur (rest books) (+ acc (:copies-sold books))))))

;; Same as above but doesn't need to declare a new function arity
(defn sum-copies-3
  (loop [books books
         acc 0]
   (if (empty? books)
     acc
     (recur (rest books) (+ acc (:copies-sold books))))))

;; The Clojure way
(defn sum-copies [books]
  (apply + (map :copies-sold books)))

(defn print-book [book]
  (println "Printing book" book))

(defn ship-book [book]
  (println "Shipping book" book))

(defn publish-book [book]
  {:pre [(:title book) (:author book)]}
  (print-book book)
  (ship-book book))
