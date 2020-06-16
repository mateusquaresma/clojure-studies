(ns blottsbooks.threads)

(defn -main []
  (println "Coming to you live from the main thread"))

;; Make a thread

(defn do-something-in-a-thread []
  (println "Hello from the thread")
  (Thread/sleep 3000)
  (println "Goodbye from the thread"))

(def the-thread (Thread. do-something-in-a-thread))

(.start the-thread)

(def fav-book "Jaws")
(defn make-emma-favorite []
  (def fav-book "Emma"))
(defn make-2001-favorite []
  (Thread/sleep 100)
  (def fav-book "2001"))

(.start (Thread. make-emma-favorite))
(.start (Thread. make-2001-favorite))
fav-book

(def ^:dynamic *favorite-book* "Oliver Twist")
(def thread-1
  (Thread.
    #(binding [*favorite-book* "Emma"]
       (println "My favorite book is " *favorite-book*))))
(def thread-2
  (Thread.
    #(binding [*favorite-book* "2001"]
       (println "My favorite book is " *favorite-book*))))

(.start thread-1)
(.start thread-2)

;; Promises

(def the-result (promise))
(deliver the-result "Emma")