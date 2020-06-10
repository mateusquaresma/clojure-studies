(ns getting-clojure-chapters.chapter-15
  (:require [clojure.spec.alpha :as s])
  (:require [clojure.spec.test.alpha :as st]))

(def book {:title "Getting Clojure" :author "Olsen" :copies 1000000})

(s/valid? number? 44)
(s/valid? number? :hello)

(def n-gt-10 (s/and number? #(> % 10)))

(s/valid? n-gt-10 1)
(s/valid? n-gt-10 10)
(s/valid? n-gt-10 11)

(def n-or-s (s/or :a-number number? :a-string string?))

;; Something like '("Alice" "in" "Wonderland")
(def coll-of-strings (s/coll-of string?))

;; Or a collection of numbers or strings, perhaps ["Emma" 1815 "Jaws" 1974]
(def coll-of-n-or-s (s/coll-of n-or-s))

(def s-n-s-n (s/cat :s1 string? :n1 number? :s2 string? :n2 number?))
(s/valid? s-n-s-n ["Emma" 1815 "Jaws" 1974])

(def book-s
  (s/keys :req-un [:inventory.core/title
                   :inventory.core/author
                   :inventory.core/copies]))

(s/valid? book-s {:title "Emma" :author "Austen" :copies 10})
(s/valid? book-s {:title "Arabian Nights" :copies 17})
(s/valid? book-s {:title "2001" :author "Clarke" :copies 25 :published 1968})

(s/def ::title string?)
(s/def ::author string?)
(s/def ::copies int?)
(s/def ::book (s/keys :req-un [::title ::author ::copies]))

(s/explain n-gt-10 44)
(s/explain ::book {:title :emma :author :austen})
(s/conform s-n-s-n ["Emma" 1815 "Jaws" 1974])

(s/def :inventory.core/inventory
  (s/coll-of ::book))

;; defining a spec inside a function
(defn find-by-title
  [title inventory]
  {:pre [(s/valid? ::title title)
         (s/valid? ::inventory inventory)]}
  (some #(when (= (:title %) title) %) inventory))

;; defining a spec outside of a function
(defn find-by-title [title inventory]
  (some #(when (= (:title %) title) %) inventory))

(s/fdef find-by-title
        :args (s/cat :title ::title
                     :inventory ::inventory))
(st/instrument 'find-by-title)
