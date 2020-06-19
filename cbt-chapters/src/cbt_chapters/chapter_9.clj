(ns cbt-chapters.chapter-9)

(future (Thread/sleep 2000)
        (println "I'll print after 2 seconds!"))
(println "I'll print immediately!")

(let [result (future (println "This prints once") (+ 1 1))]
  (println "deref: " (deref result))
  (println "@: " @result))

(deref (future (Thread/sleep 1000) 0) 10 5)
(realized? (future (Thread/sleep 1000)))

(let [f (future)]
  @f
  (realized? f))

(def jackson-5-delay
  (delay (let [message "Just call me and I'll be there"]
           (println "first deref: " message)
           message)))

(def gimli-headshots ["serious.jpg" "fun.jpg" "playful.jpg"])

(defn email-user [email-address]
  (println "Sending headshot notification to " email-address))

(defn upload-document
  "Needs to be implemented"
  [headshot]
  true)

(let [notify (delay (email-user "and-my-axe@gmail.com"))]
  (doseq [headshot gimli-headshots]
    (future (upload-document headshot)
            (force notify))))

(def my-promise (promise))
(deliver my-promise (+ 1 2))
@my-promise

(def yak-butter-international
  {:store "Yak Butter International"
   :price 90
   :smoothness 90})

(def butter-than-nothing
  {:store "Butter Than Nothing"
   :price 150
   :smoothness 83})

(def baby-got-yak
  {:store "Baby Got Yak"
   :price 94
   :smoothness 99})

(defn mock-api-call [result]
  (Thread/sleep 1000)
  result)

(defn satisfactory?
  "If the butter meets our criteria, return the butter, else return false"
  [butter]
  (and
    (<= (:price butter) 100)
    (>= (:smoothness butter) 97)
    butter))

(time (some
  (comp satisfactory? mock-api-call)
  [yak-butter-international butter-than-nothing baby-got-yak]))

(time
  (let [butter-promise (promise)]
    (doseq [butter [yak-butter-international butter-than-nothing baby-got-yak]]
      (future (if-let [satisfactory-butter (satisfactory? (mock-api-call butter))]
                (deliver butter-promise satisfactory-butter))))
    (println "The winner is: "  (deref butter-promise 1500 "Time out"))))

(let [ferengi-wisdom-promise (promise)]
  (future (println "Here is some Ferengi wisdom: " @ferengi-wisdom-promise))
  (Thread/sleep 100)
  (deliver ferengi-wisdom-promise "Whisper your way to success!"))
