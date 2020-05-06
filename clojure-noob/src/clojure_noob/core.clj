(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a teapot"))

; The following code is supposed to by typed in a repl session
(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))

(when true
  (println "Success!")
  "Abra Cadabra")

; Checking for nil (null) values
(nil? 1)
(nil? nil)

(if "bears eat beets"
  "bears beets battlestar galactica")

(if nil
  "this won't be the result because nil is falsey"
  "nil is falsey")

; Testing equality
(= 1 1)
(= nil nil)
(= 1 2)

; or
(or false nil :large_I_mean_venti :why_cant_I_just_say_large)
(or (= 0 1) (= "yes" "no"))
(or nil)

; and
(and :free_wifi :hot_coffee)
(and :feeling_super_cool nil false)

; Naming values with def
(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

; Maps
{:name "Mateus" :lastname "Quaresma"}

(get ["a" {:name "pugs winter"} "c"] 1)

; Vectors
(def my-vector
  (vector "creepy" "full" "moon"))
; elements are added at the end of the list
(conj my-vector "tonight")

; Lists
'(1 2 3)
(nth '(:a :b :c) 0)
(nth '(:a :b :c) 2)

(list 1 "two" {3 4})
; elements are added at the beginning of the list
(conj (list 1 "two" {3 4}) 4)

; Sets
#{1 2 3 4 }
(hash-set 1 1 2 2)

(contains? #{:a :b} :a)
(contains? #{:a :b} 3)

; Using keyword lookup
(:a #{:a :b})

(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "Oh. My. God." name " You are most definitely like the best"
       "man slash woman ever. I love you and we should run away somewhere"))

(defn x-chop
  "Describe the kind of chop you are inflicting on someone"
  ([name chop-type]
   (str "I " chop-type " chop " name ". Take that!"))
  ([name]
   (x-chop name "karate")))

(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")











































