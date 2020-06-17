(ns getting-clojure-chapters.chapter-20)

(defn print-rating [rating]
  (cond
    (pos? rating) (println "Good book!")
    (zero? rating) (println "Totally indifferent!")
    :else (println "Run away!")))

(print-rating 10)

(defn arithmetic-if [n pos-fn zero-fn neg-fn]
  (cond
    (pos? n) (pos-fn)
    (zero? n) (zero-fn)
    (neg? n) (neg-fn)))

(defn print-rating [rating]
  (arithmetic-if rating
                 #(println "Good book!")
                 #(println "Totally indifferent")
                 #(println "Run away!")))

;; does not work
(defn arithmetic-if->cond [n pos zero neg]
  (list 'cond
        (list 'pos? n) pos
        (list 'zero? n) zero
        :else neg))

;; This works!
(defmacro arithmetic-if [n pos zero neg]
  (list 'cond
        (list 'pos? n) pos
        (list 'zero? n) zero
        :else neg))

(arithmetic-if 10 :loved-it :meh :hated-it)

(arithmetic-if 10
               (println "Good book!")
               (println "Totally indifferent")
               (println "Run away!"))

(defmacro print-it [something]
  (list 'println "Something is" something))

(print-it (+ 10 20))

`(:a :syntax "quoted" :list 1 2 3 4)

(def n 10)
(def pos "It is positive!")
(def zero "It's zero")
(def neg "It is negative!")

`(cond
   (pos? ~n) ~pos
   (zero? ~n) ~zero
   :else ~neg)

(defmacro arithmetic-if [n pos zero neg]
  `(cond
     (pos? ~n) ~pos
     (zero? ~n) ~zero
     :else ~neg))


(defmacro mark-the-times []
  (println "This is code that runs when the macro is expanded")
  `(println "This is the generated code"))

(defn use-the-macro []
  (mark-the-times))

(use-the-macro)
(use-the-macro)