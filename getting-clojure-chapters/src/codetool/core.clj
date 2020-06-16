(ns codetool.core
  (:require [clojure.java.io :as io])
  (:import (java.io PushbackReader)))

(declare reval)

(defn read-source [path]
  (with-open [r (PushbackReader. (io/reader path))]
    (loop [result []]
      (let [expr (read r false :eof)]
        (if (= expr :eof)
          result
          (recur (conj result expr)))))))

(defn eval-symbol [expr]
  (.get (ns-resolve *ns* expr)))

(defn eval-vector [expr]
  (vec (map reval expr)))

(defn eval-list [expr]
  (let [evaled-expr (map reval expr)
        fn (first evaled-expr)
        args (rest evaled-expr)]
    (apply fn args)))

(defn reval [expr]
  (cond
    (string? expr) expr
    (keyword? expr) expr
    (number? expr) expr
    (symbol? expr) (eval-symbol expr)
    (vector? expr) (eval-vector expr)
    (list? expr) (eval-list expr)
    :else :completely-confused))