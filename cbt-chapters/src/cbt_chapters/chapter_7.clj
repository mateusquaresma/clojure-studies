(ns cbt-chapters.chapter-7)

(defmacro backwards
  [form]
  (reverse form))

(backwards (" backwards" " am" "I" str))

((eval (read-string "#(+ 1 %)")) 1)

;; The REPL
;; Reads your text to get a data structure
;; Evaluates the given data structure
;; Prints the result as a text
;; Loops back

(type (read-string "+"))

;;
(eval (read-string "(1 + 1)"))
(let [infix (read-string "(1 + 1)")]
  (list (second infix) (first infix) (last infix)))

(defmacro ignore-last-operand
  [function-call]
  (butlast function-call))

(ignore-last-operand (+ 1 2 10))

(macroexpand '(ignore-last-operand (+ 1 2 10)))

(defmacro infix
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed)))

(infix (1 + 2))

;; These two constructs below implements the same logic

(defn read-resource
  "Read a resource into a string"
  [path]
  (read-string (slurp (clojure.java.io/resource path))))

(defn read-resource
  [path]
  (-> path
      clojure.java.io/resource
      slurp
      read-string))

;; Exercise 1
(defmacro fav-movie
  [name movie]
  (list 'str "I'm " name " and I liked the movie " movie))

