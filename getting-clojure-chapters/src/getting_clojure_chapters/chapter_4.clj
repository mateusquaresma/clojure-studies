(ns getting-clojure-chapters.chapter-4)

(defn pretty-greetings
  [preferred-customer]
  (if preferred-customer
    (println "Welcome back to Blotts Books!")
    (println "Welcome to Blotts Books")))

(not= "Anna Karenina" "Jane Eyre")

(defn shipping-surcharge?
  [preferred-customer express oversized]
  (and (not preferred-customer) (or express oversized)))

(when shipping-surcharge?
  (println "Shipping Surcharge because the customer")
  (println "is not a preferred customer or ")
  (println "we've got an special shipment"))

(defn shipping-charge
  [preferred-customer order-amount]
  (cond
    preferred-customer 0.0
    (< order-amount 50) 5.0
    (< order-amount 100) 10.0
    :else (* order-amount 0.1)))

(defn customer-greeting
  [status]
  (case status
    :gold "Welcome, welcome, welcome back!!!"
    :preferred "Welcome back!"
    "Welcome to Blotts Books"))

(try
  (/ 1 0)
  (catch ArithmeticException e (println "Math problem")))

(defn publish-book
  [book]
  (when (not (:title book))
    (throw (ex-info "A book needs a title" {:book book}))))