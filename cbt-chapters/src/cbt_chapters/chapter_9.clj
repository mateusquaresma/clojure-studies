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