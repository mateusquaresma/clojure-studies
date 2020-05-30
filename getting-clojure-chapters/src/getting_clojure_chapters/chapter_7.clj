(ns getting-clojure-chapters.chapter-7)

(defn compute-discount-amount [amount discount-percentage min-charge]
  (let [discount (* amount discount-percentage)
        discounted-amount (- amount discount)]
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)))

(def user-discounts
  {"Nicholas" 0.10 "Jonathan" 0.07 "Felicia" 0.05})

(defn compute-discount-amount [amount user-name user-discounts min-charge]
  (let [discount-percent (user-discounts user-name)
        discount (* amount discount-percent)
        discounted-amount (- amount discount)]
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)))

(defn mk-discount-price-f [user-name user-discounts min-charge]
  (let [discount-percent (user-discounts user-name)]
    (fn [amount]
      (let [discount (* amount discount-percent)
            discounted-amount (- amount discount)]
        (if (> discounted-amount min-charge)
          discounted-amount
          min-charge)))))

(def compute-felicia-price
  (mk-discount-price-f "Felicia" user-discounts 10.0))

(compute-felicia-price 20.0)

(defn testing-let []
  (let [title "Pride and Prejudice"
        title (str title " and Zombies")]
    title))
(testing-let)