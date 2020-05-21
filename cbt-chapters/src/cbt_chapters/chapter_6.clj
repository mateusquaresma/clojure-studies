(ns cbt-chapters.chapter-6)

(def great-books ["East of Eden" "The Glass Bead Game"])

(deref #'great-books)

(create-ns 'cheese.taxonomy)
(ns-name (create-ns 'cheese.taxonomy))

(in-ns 'cheese.taxonomy)
(def cheddars ["mild" "medium" "strong" "sharp" "extra sharp"])
(def bries ["Wisconsin" "Somerset" "Brie de Meaux" "Brie de Melun"])
(in-ns 'cheese.analysis)
(clojure.core/refer 'cheese.taxonomy)

(defn- private-function
  "Just an example of a function that does nothing"
  [])

