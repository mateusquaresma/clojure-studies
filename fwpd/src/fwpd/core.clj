(ns fwpd.core)
(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Converts a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(mapify (parse (slurp filename)))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(defn glitter-filter-list
  [minimum-glitter records]
  (map #(:name %) (filter #(>= (:glitter-index %) minimum-glitter) records)))

;; Exercises

;; Exercise 1
(defn listfy
  [map-records]
  (reduce (fn [acc row]
            (conj acc (:name row)))
          []
          map-records))

;; Exercise 2
(defn append
  "Appends a new suspect to a list of suspects"
  [suspects new-suspect]
  (conj suspects new-suspect))

;; Exercise 3

(defn valid-name?
  [name]
  (and (not (nil? name)) (> (count name) 0)))

(defn valid-glitter-index?
  [glitter-index]
  (not (nil? glitter-index)))

(def validations {:name valid-name? :glitter-index valid-glitter-index?})

(defn validate
  [validations record]
  (and ((:name validations) (:name record))
       ((:glitter-index validations) (:glitter-index record))))

(defn append
  "Appends a new suspect to a list of suspects"
  [validations suspects new-suspect]
  (if (validate validations new-suspect)
    (conj suspects new-suspect)
    suspects))


(defn stringfy
  "Turns a record in the format {:name 'Dracula' :glitter-index 0} into Dracula,0"
  [record]
  (clojure.string/join "," (map #(second %) record)))

(defn back-to-str
  [records]
  (clojure.string/join
    "\n"
    (reduce (fn [acc record]
              (conj acc (stringfy record)))
            []
            records)))

(def data (mapify (parse (slurp filename))))
(mapify (parse (back-to-str data)))