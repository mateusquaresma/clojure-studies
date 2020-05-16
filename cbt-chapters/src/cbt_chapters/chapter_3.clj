(ns cbt-chapters.chapter-3)

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
#{1 2 3 4}
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

(favorite-things "Doreen" "gum" "shoes" "karate")

; Destructuring a map
(defn announce-treasure-location
  [{:keys [lat lon]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lon: " lon)))

(announce-treasure-location {:lat 123 :lon 456})

;; Anonymous functions

(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])

((fn [x] (* x 3)) 8)

(def my-special-multiplier (fn [x] (* x 3)))
(my-special-multiplier 12)

;; Weird anonymous function definition
#(* % 3)
(#(* % 3) 8)

(map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])

(#(str %1 " and " %2) "apples" "bananas")

;; Returning functions

(defn inc-maker
  "Creates a custom incrementor"
  [inc-by]
  #(+ % inc-by))

((inc-maker 3) 1)

;;
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and a :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

;; Breaking it down

;; Understanding let

(let [x 3] x)
(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)

;; let introduces a new scope

(def x 0)
(let [x 1] x)                                               ;; returns 1
(println x)                                                 ;; prints 0

(let [x (inc x)] x)                                         ;; (inc x) diff from returned x

(let [[pongo & dalmatians] dalmatian-list]
  [pongo dalmatians])

(into [] (set [:a :b]))

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))

;; Better symmetrizer with reduce

(reduce + [1 2 3 4])

(defn better-symmetrizer-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrizer-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    ;;(println target body-part-size-sum)
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      ;;(println accumulated-size)
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

;; Exercises

;; Exercise 1
(str "My name is " "Clojure programmer")
(defn print-names
  [names]
  (hash-set names))

(defn add10
  [x]
  (+ 10 x))

(defn dec-maker
  [x]
  #(- % x))

(defn mapset
  "Should work as the map function but the return type must be a set"
  [f coll]
  (loop [[head & tail] coll
         out-coll #{}]
    (if (nil? head)
      out-coll
      (recur tail (into out-coll [(f head)])))))

(defn better-mapset
  [f in-coll]
  (reduce
    (fn [acc arg]
      (into acc #{(f arg)}))
    #{}
    in-coll))

;; Exercise 5

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn gen-part
  [part prefix]
  {:name (clojure.string/replace (:name part) #"^left-" (str prefix "-"))
   :size (:size part)})

(defn generate-parts
  [part amount]
  (loop [[head & tail] (map inc (range amount))
         generated-parts (set [])]
    (if (nil? head)
      generated-parts
      (recur tail
             (conj generated-parts (gen-part part head))))))

(defn generic-symmetrizer-body-parts
  "Expects a seq of maps that have a :name and :size"
  ([asym-body-parts amount]
   (reduce (fn [final-body-parts part]
             (into final-body-parts (generate-parts part amount)))
           []
           asym-body-parts))
  ([asym-body-parts]
   (generic-symmetrizer-body-parts asym-body-parts 2)))












