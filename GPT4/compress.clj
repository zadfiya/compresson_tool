(ns compress
  (:require [clojure.string :as string]
            [clojure.java.io :as io]))

(def frequency-file "frequency.txt")

(defn read-frequency-map []
  ;; Function to read the frequency.txt file and construct a frequency map
  (with-open [reader (io/reader frequency-file)]
    (let [lines (line-seq reader)
          frequency-list (map #(string/split % #" ") lines)
          words-frequencies (map (fn [[word _]] word) frequency-list)]
      (zipmap words-frequencies (range)))))

(defn compress-file [file]
  ;; Function to compress a file
  (let [content (slurp file)
        words (string/split content #"[\s.,!?:;()\[\]{}]") ;; split on whitespace and punctuation
        frequency-map (read-frequency-map)
        compressed-words (map #(if-let [index (frequency-map %)] (str index) %) words)
        compressed-content (string/join " " compressed-words)
        output-file (str (clojure.string/replace file #"\..*" "") ".ct")]
    (spit output-file compressed-content)
    (println (str "File compressed successfully. Compressed file saved as " output-file))))

(defn uncompress-file [file]
  ;; Function to uncompress a file
  (let [compressed-content (slurp file)
        compressed-words (string/split compressed-content #" ")
        frequency-map (read-frequency-map)
        uncompressed-words (map #(if (re-find #"\D" %) % (get (invert frequency-map) %)) compressed-words)
        uncompressed-content (string/join " " uncompressed-words)]
    (println "Decompressed content:")
    (println uncompressed-content)))
