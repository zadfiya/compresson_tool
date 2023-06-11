(ns compress
  (:require [clojure.string :as str]))

(defn read-filename
  []
  (println "Please enter a filename: ")
  (read-line))

(defn compress-data
  []
  (let [filename (read-filename)]
    (println (str "Compressing file: " filename))))

(defn decompress-data
  []
  (let [filename (read-filename)]
    (println (str "Decompressing file: " filename))))

