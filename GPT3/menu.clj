(ns menu
  (:gen-class)
  (:require [compress :as comp]))

(defn read-filename []
  (println "Enter filename:")
  (read-line))

(defn main []
  (println "Menu:")
  (println "1. Compress")
  (println "2. Decompress")
  (let [choice (Integer/parseInt (read-line))]
    (cond
      (= choice 1) (comp/compress (read-filename))
      (= choice 2) (comp/decompress (read-filename))
      :else (println "Invalid option selected. Try again."))))

(defn -main []
  "I don't do a whole lot ... yet."
  []
  (main))

(-main)
