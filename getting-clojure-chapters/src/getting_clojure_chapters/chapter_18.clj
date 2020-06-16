(ns getting-clojure-chapters.chapter-18)

(def counter 0)
(defn greeting-message [req]
  (if (zero? (mod counter 100))
    (str "Congrats you are the " counter " visitor!")
    (str "Welcome to Blottsbooks")))

;; With atoms

(def counter (atom 0))
(defn greeting-message [req]
  (swap! counter inc)
  (if (= @counter 100)
    (str "Congrats you are the " counter " visitor!")
    (str "Welcome to Blottsbooks")))