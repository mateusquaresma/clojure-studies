(ns cbt-chapters.core
  (:gen-class))

(defn e
  [n]
  (Math/pow (+ 1 (/ 1 n)) n))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a teapot"))