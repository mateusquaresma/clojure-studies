(ns cbt-chapters.chapter-5)

;; unrelated

(defn e
  [n]
  (Math/pow (+ 1.0 (/ 1.0  n)) n))

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)
(c-str character)
(c-dex character)

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

(def spell-slots-comp (comp int inc #(/ % 2) c-int))

(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

(def c-dex-2 (two-comp :dexterity :attributes))

;; memoization

(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)

(sleepy-identity "Mr Fantastico")
(def memo-sleepy-identity (memoize sleepy-identity))

;; Exercises

;; Exercise 1

(defn attr
  "Retrieves the attribute specified by attr-name"
  [attr-name]
  (fn [character]
    (get-in character [:attributes attr-name])))

(def c-int (attr :intelligence))

;; Exercise 2
(defn my-comp
  "Takes a set of functions and returns a fn that is the composition of those fns"
  [& fns]
  (fn [& args]
    (reduce (fn [result-so-far next-fun]
              (next-fun result-so-far))
            (apply (last fns) args)
            (rest (reverse fns)))))

;; Exercise 3
;(defn my-assoc-in
;  [m [k & ks] v]
;  (if (empty? ks)
;    (assoc {} k v)
;    (assoc m k (my-assoc-in m ks v) )))
;;
(defn my-assoc-in
  [m [k & ks] v]
  (if ks
    (assoc m k (my-assoc-in (get m k) ks v))
    (assoc m k v)))

;; Exercise 4 and 5

;(defn my-update-in
;  [m ks f & args]
;  (let [new-args (get-in m ks)]
;    (println(f new-args))))

(defn my-update-in
  [m ks f & args]
  (let [up (fn up [m ks f args]
             (let [[k & ks] ks]
               (if ks
                 (assoc m k (up (get m k) ks f args))
                 (assoc m k (apply f (get m k) args)))))]
    (up m ks f args)))









