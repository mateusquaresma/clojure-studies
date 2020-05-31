(ns getting-clojure-chapters.chapter-9)

(require 'clojure.data)

(def discount-rate 0.15)

(defn discount-price [book]
  (* (- 1.0 discount-rate) (:price book)))

(discount-price {:title "Emma" :price 9.99})

(def literature ["Emma" "Oliver Twist" "Possession"])
(def horror ["It" "Carry" "Possession"])

(require 'clojure.data)
(clojure.data/diff literature horror)

(ns-map (find-ns 'user))
;; Same as above, but shorter
(ns-map 'user)

(namespace 'getting-clojure-chapters.chapter-9/discount-price)