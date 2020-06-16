(ns getting-clojure-chapters.chapter-19)


(def a-data-structure '(+ 2 2))
(eval a-data-structure)

(def fn-name 'print-greeting)
(def args (vector 'preferred-customer))
(def the-println (list 'println "Welcome Back!"))
(def body (list 'if 'preferred-customer the-println))

(eval (list 'defn fn-name args body))
(eval (list 'print-greeting true))