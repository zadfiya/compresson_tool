(ns compress
  (:require [clojure.string :as str]))

(def frequency-map 
  (let [file-contents (slurp "frequency.txt")
        words (str/split file-contents #"\s")]
    (zipmap words (range))))

(def rank-map 
  (zipmap (vals frequency-map) (keys frequency-map)))

(defn compress-file [filename]
  (try
    (let [content (slurp filename)
          words (str/split content #"\s")
          compressed-words (map #(get frequency-map % %) words)
          compressed-content (str/join " " compressed-words)
          compressed-filename (str filename ".ct")]
      (spit compressed-filename compressed-content)
      "Compression successful.")
    (catch Exception e 
      "Oops: An error occurred during compression.")))

(defn uncompress-file [filename]
  (try
    (let [content (slurp filename)
          words (str/split content #"\s")
          decompressed-words (map #(if (re-matches #"^\d+$" %)
                                     (get rank-map (Integer/parseInt %) %)
                                     %) words)
          decompressed-content (str/join " " decompressed-words)]
      decompressed-content)
    (catch Exception e 
      "Oops: An error occurred during decompression.")))
