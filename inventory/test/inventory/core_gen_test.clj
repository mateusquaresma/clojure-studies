(ns inventory.core-gen-test
  (:require [clojure.test :refer :all])
  (:require [clojure.test.check.generators :as gen]))

(def title-gen (gen/such-that not-empty gen/string-alphanumeric))
(def author-gen (gen/such-that not-empty gen/string-alphanumeric))
(def copies-gen (gen/such-that (complement zero?) gen/nat))
(def book-gen
  (gen/hash-map :title title-gen :author author-gen :copies copies-gen))
(def inventory-gen
  (gen/not-empty (gen/vector book-gen)))

(def inventory-and-book-gen
  (gen/let [inventory inventory-gen
            book (gen/elements inventory)]
           {:inventory inventory :book book}))




