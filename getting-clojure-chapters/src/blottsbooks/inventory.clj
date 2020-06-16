(ns blottsbooks.inventory)

(def by-title (ref {}))
(def total-copies (ref 0))

(defn add-book [{title :title :as book}]
  (do sync
      (alter by-title #(assoc % title book))
      (alter total-copies + (:copies book))))

(defn del-book [title]
  (swap! by-title #(dissoc % title)))

(defn find-book [title]
  (get @by-title title))

;; Testing our inventory

(find-book "Emma")
(add-book {:title "1984" :copies 1948})
(add-book {:title "Emma" :copies 100})

(del-book "1984")

(find-book "Emma")
(find-book "1984")

;; Using agents

(def by-title (agent {}))
(def total-copies (ref 0))

(defn add-book [{title :title :as book}]
  (send by-title
        (fn [by-title-map]
          (assoc by-title-map title book))))

(defn del-book [title]
  (swap! by-title #(dissoc % title)))

(defn find-book [title]
  (get @by-title title))

(add-book {:title "1984" :copies 1954})
(find-book "1984")