(ns inventory.core-test
  (:require [clojure.test :refer :all]
            [inventory.core :as i]))

(def books
  [{:title "2001" :author "Clarke" :copies 21}
   {:title "Emma" :author "Austen" :copies 10}
   {:title "Misery" :author "Kings" :copies 101}])

(deftest test-finding-books
  (is (not (nil? (i/find-by-title "Emma" books))))
  (is (nil? (i/find-by-title "Some book" books))))

(deftest test-basic-inventory
  (testing "Finding books"
    (is (not (nil? (i/find-by-title "Emma" books))))
    (is (nil? (i/find-by-title "Some book" books))))
  (testing "Copies of inventory"
    (is (= 10 (i/numbers-of-copies-of "Emma" books)))))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))
