(ns cbt-chapters.chapter-8)

(defmacro infix-2
  [[operand-1 op operand-2]]
  (list op operand-1 operand-2))

(defmacro my-print
  [expression]
  (list 'let ['result expression]
        (list 'println 'result)
        'result))

;; Syntax quoting and unquoting

(defmacro code-critic
  [bad good]
  (list 'do
        (list
          'println
          "Great squid of Madrid, this is bad code:"
          (list 'quote bad))
        (list
          'println
          "Sweet gorilla of Manila, this is good code:"
          (list 'quote good))))

(code-critic (1 + 1) (+ 1 1))

(defmacro code-critic-2
  [bad good]
  `(do (println "Great squid of Madrid, this is bad code:"
                (quote ~bad))
       (println "Sweet gorilla of Manila, this is good code:"
                (quote ~good))))

(code-critic-2 (1 + 1) (+ 1 1))

(defn criticize-code
  [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-critic-3
  [bad good]
  `(do ~(criticize-code "Great squid of Madrid, this is bad code:" bad)
       ~(criticize-code "Sweet gorilla of Manila, this is good code:" good )))

(code-critic-3 (1 + 1) (+ 1 1))

(defmacro code-critic-4
  [bad good]
  `(do ~@(map #(apply criticize-code %)
             [["Great squid of Madrid, this is bad code:" bad]
              ["Sweet gorilla of Manila, this is good code:" good]])))

(code-critic-4 (1 + 1) (+ 1 1))

(def order-details
  {:name "Mitchard Blimmons"
   :email "mbgmail.com"})

(def order-details-validation
  {:name ["Please enter a name" not-empty]
   :email [
           "Please enter an e-mail address" not-empty

           "Your e-mail doesn't look like an e-mail address"
           #(or (empty? %) (re-seq #"@" %))]})

(defn error-messages-for
  "Return a map of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))

(error-messages-for "" ["Please enter a name" not-empty])

(defn validate
  "Returns a map with a vector of errors for each key"
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-groups)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

(validate order-details order-details-validation)
