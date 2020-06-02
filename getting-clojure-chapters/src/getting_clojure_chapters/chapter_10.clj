(ns getting-clojure-chapters.chapter-10)

;; First attempt to count

(defn flavor [x]
  (cond
    (list? x) :list
    (vector? x) :vector
    (set? x) :set
    (map? x) :map
    (string? x) :string
    :else :unknown))

(defmulti my-count flavor)
(defmethod my-count :list [x] (count x))                    ; should be a list specific count
(defmethod my-count :vector [x] (count x))                  ; should be a vector specific count

(def books (seq '("Emma" "Oliver Twist" "Robinson Crusoe")))
(first books)
(rest books)
(cons "Possession" books)

;; my-count using the sequence interface

(defn my-count [col]
  (let [the-seq (seq col)]
    (loop [n 0
           s the-seq]
      (if (seq s)
        (recur (inc n) (rest s))
        n))))

(def titles ["Jaws" "Emma" "2001" "Dracula"])
(sort titles)

(def titles-and-authors ["Jaws" "Benchley" "2001" "Clarke"])
(partition 2 titles-and-authors)

(def titles-1 [ "Jaws" "2001"])
(def authors ["Benchley" "Clarke"])
(interleave titles-1 authors)

(def scary-animals ["Lions" "Tigers" "Bears"])
(interpose "and" scary-animals)

(for [b books]
  (count (:title b)))

(def numbers [10 20 30 40 50])
(reduce #(+ %1 %2) 0 numbers)
(reduce + 0 numbers)
(reduce + numbers)

(defn hi-price [hi book]
  (let [book-price (:price book)]
    (if (> hi (:price book))
      hi
      book-price)))

(reduce hi-price 0 [{:title "Emma" :price 9.99} {:title "Dracula" :price 19.99}])
