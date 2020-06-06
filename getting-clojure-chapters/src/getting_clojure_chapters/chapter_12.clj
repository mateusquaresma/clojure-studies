(ns getting-clojure-chapters.chapter-12)

(def artists [:monet :austen :beethoven :dickinson])

;; First approach using functions
(let [painter (first artists)
      novelist (second artists)]
  (println "The painter is" painter
           "and the novelist is" novelist))

;; Second approach
(let [[painter novelist] artists]
  (println "The painter is" painter
           "and the novelist is" novelist))

;; dealing with unwanted values
(let [[_ _ composer poet] artists]
  (println "The composer is" composer
           "and the poet is" poet))

(def pairs [[:monet :austen] [:beethoven :dickinson]])
(let [[[painter] [composer]] pairs]
  (println "The painter is" painter
           "and the composer is" composer))

;; Destructuring function arguments
(defn artist-description
  ([[novelist poet]]
   (artist-description false [novelist poet]))
  ([shout [novelist poet]]
   (let [msg (str "The novelist is " novelist " and the poet is " poet)]
     (if shout (.toUpperCase msg) msg))))

(artist-description artists)

(def artist-map {:painter :monet :novelist :austen})

(let [{painter :painter writer :novelist} artist-map]
  (println "The painter is" painter
           "and the novelist is" writer))

;; Nested maps

(def austen {:name "Jane Austen"
             :parents {:father "George" :mother "Cassandra"}
             :dates {:born 1775 :died 1817}})
(let [{{dad :father mom :mother} :parents} austen]
  (println "Jane Austen's dad's name was" dad
           "Jane Austen's mom's name was" mom))

(def author {:name "Jane Austen"
             :books [{:title "Sense and Sensibility" :published 1811}
                     {:title "Emma"                  :published 1815}]})

(let [{name :name [_ book] :books} author]
  (println "Author's name is" name)
  (println "One of the author's book is" book))

(defn add-greeting [character]
  (let [{:keys [name age]} character]
    (assoc character :greeting (str "Hello, my name is " name " and I am " age "."))))

(defn add-greeting [{:keys [name age] :as character}]
  (assoc character :greeting (str "Hello, my name is " name " and I am " age ".")))