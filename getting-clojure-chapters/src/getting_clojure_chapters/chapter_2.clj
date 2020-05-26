(ns getting-clojure-chapters.chapter-2)

(def novels ["Emma" "Coma" "War and Peace"])
(count novels)
(first novels)
(rest novels)                                               ; gives all but the first element
(nth novels 2)
(novels 2)
(conj novels "Carrie")
(cons "Carrie" novels)

(def poems '("Iliad" "Odyssey" "Now We Are Six"))
(count poems)
(first poems)
(rest poems)
(nth poems 2)

(conj poems "Jabberwocky")