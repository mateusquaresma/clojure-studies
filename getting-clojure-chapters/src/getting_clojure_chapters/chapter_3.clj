(ns getting-clojure-chapters.chapter-3)

(def book {:title "Oliver Twist"
           :author "Dickens"
           :published 1838})
(get book :published)
(book :published)
(:published book)
(book :copyright)                                           ; should return nil

(def book (assoc book :page-count 362))
(dissoc book :page-count)

(assoc [:title :by :published] 1 :author)                   ; switches value of index 1

(keys book)
(vals book)

(def genres #{:sci-fi :romance :mystery})
(def authors #{"Dickens" "Austen" "King"})

;; contains? returns true or false
(contains? authors "Austen")
(contains? genres "Austen")

;; using set like a function returns the value or nil
;; if the value is not present
(authors "Austen")
(genres :historical)

(def more-authors (conj authors "Clarke"))
(def authors (disj more-authors "Clarke"))








