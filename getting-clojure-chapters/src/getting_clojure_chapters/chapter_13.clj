(ns getting-clojure-chapters.chapter-13)

(defrecord FictionalCharacter [name appears-in author])

(def watson (->FictionalCharacter "John Watson" "Sign Of The Four" "Doyle"))
(def elisabeth (map->FictionalCharacter {:name "Elisabeth Bennet"
                                         :appears-in "Pride and Prejudice"
                                         :author "Austen"}))

(:name elisabeth)
(:appears-in watson)