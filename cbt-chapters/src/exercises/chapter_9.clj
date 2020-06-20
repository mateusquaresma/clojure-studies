(ns exercises.chapter-9
  (:require [clj-http.client :as client]))

;; Write a function that takes a string as an argument and searches
;; for it on Bing and Google using the slurp function.
;; Your function should return the HTML of the first page returned by the search.

(defn search-on-google [term]
  (let [raw-query-string "https://www.google.com/search?ei=oTTuXsSJI8y95OUP3eqL2Ao&q=%s&oq=%s&gs_lcp=CgZwc3ktYWIQAzIKCAAQsQMQRhCBAjICCAAyAggAMgIIADICCAAyAggAMgIIADICCAAyAggAMgIIADoECAAQRzoECAAQQzoFCAAQgwE6BQgAELEDUMv8eljnkntgqJR7aAFwAXgDgAF1iAGkFJIBBDYuMTmYAQCgAQGqAQdnd3Mtd2l6sAEA&sclient=psy-ab&ved=0ahUKEwiE3tnU45DqAhXMHrkGHV31AqsQ4dUDCAs&uact=5"
        query-string (format raw-query-string term term)
        response (client/get query-string)]
    (:body response)))

(defn search-on-bing [term]
  (let [raw-query-string "https://www.bing.com/search?q=%s"
        query-string (format raw-query-string term)
        response (client/get query-string)]
    (:body response)))

(defn search-on-bing-and-google [term]
  (let [bing-result (future (search-on-bing term))
        google-result (future (search-on-google term))]
    (println (deref bing-result 1000 "bing timed out"))
    (println (deref google-result 1000 "google timed out"))))


;; Update your function so it takes a second argument
;; consisting of the search engines to use.

(defn search [term search-engine]
  (cond
    (= :bing search-engine) (search-on-bing term)
    (= :google search-engine) (search-on-google term)
    :else "invalid search engine"))

;; Create a new function that takes a search term and search engines as arguments,
;; and returns a vector of the URLs from the first page of search results
;; from each search engine.

(defn fetch-urls [content]
  (re-seq #"http://[\w\.]+|https://[\w\.]+" content))

;; One-liner
(defn search-for-urls [term search-engines]
  (vec (apply sorted-set (flatten (map #(fetch-urls (search term %)) search-engines)))))

;; Reasonable implementation
(defn search-for-url [term search-engines]
  (let [urls (map #(fetch-urls (search term %)) search-engines)
        flattened-urls (flatten urls)]
    (vec (apply sorted-set flattened-urls))))