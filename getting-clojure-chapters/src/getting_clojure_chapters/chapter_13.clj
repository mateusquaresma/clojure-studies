(ns getting-clojure-chapters.chapter-13)

(defrecord FictionalCharacter [name appears-in author])

(def watson (->FictionalCharacter "John Watson" "Sign Of The Four" "Doyle"))
(def elisabeth (map->FictionalCharacter {:name "Elisabeth Bennet"
                                         :appears-in "Pride and Prejudice"
                                         :author "Austen"}))

(:name elisabeth)
(:appears-in watson)

(defrecord Employee [first-name last-name department])
(def alice (->Employee "Alice" "Smith" "Engineering"))

(defprotocol Person
  (full-name [this])
  (greeting [this msg])
  (description [this]))

(defrecord FictionalCharacter [name appears-in author]
  Person
  (full-name [this] (:name this))
  (greeting [this msg]
    (str msg " " (:name this)))
  (description [this]
    (str (:name this) " is a character and appears in " (:appears-in this))))

(defrecord Employee [first-name last-name department]
  Person
  (full-name [this] (str first-name " " last-name))
  (greeting [this msg] (str " " first-name))
  (description [this] (str (:first-name this) " works in " (:department this))))

(def sofia (->Employee "Sofia" "Diego" "Finance"))
(def sam (->FictionalCharacter "Sam Weller" "The Pickwick Papers" "Dickens"))

(full-name sofia)
(description sam)
(greeting sofia "Hello")

(defprotocol Marketable
  (make-slogan [this]))

(extend-protocol Marketable
  Employee
  (make-slogan [e] (str (:first-name e) " is the best employee!")))

(extend-protocol Marketable
  FictionalCharacter
  (make-slogan [fc] (str (:name fc) " is the greatest character!")))