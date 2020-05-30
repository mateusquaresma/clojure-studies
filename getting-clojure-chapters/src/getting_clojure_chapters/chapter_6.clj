(ns getting-clojure-chapters.chapter-6)

(def dracula {:title "Dracula"
              :author "Stoker"
              :price 1.99
              :genre :horror})

(defn cheap? [book]
  (when (<= (:price book) 9.99)
    book))

(defn pricey? [book]
  (when (> (:price book) 9.99)
    book))

(cheap? dracula)
(pricey? dracula)

(defn horror? [book]
  (when (= (:genre book) :horror)
    book))
(defn adventure? [book]
  (when (= (:genre book) :adventure)
    book))

(horror? dracula)
(adventure? dracula)

;; Combining functions

(defn both? [first-predicate second-predicate book]
  (when (and (first-predicate book) (second-predicate book))
    book))

(both? cheap? horror? dracula)

(defn cheaper-f [max-price]
  (fn [book]
    (when (<= (:price book) max-price)
      book)))

;; anonymous functions
;; Same as adventure?
#(when (= (:genre %1) :adventure) %1)

;; same as inc function
(defn my-inc [n] (+ 1 n))
#(+ 1 %1)
(partial + 1)
(def emma-book {:title "Emma" :copies-sold 1000})
(def new-emma-book (update emma-book :copies-sold inc))
