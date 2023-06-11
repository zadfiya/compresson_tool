(ns menu
  (:require [compress :as c])) ; Alias compress namespace as c

(defn read-option
  []
  (println "1. Compress file")
  (println "2. Decompress file")
  (println "3. Exit")
  (let [opt (read-line)]
    (case opt
      "1" (c/compress-data) ; Call functions from compress namespace with alias
      "2" (c/decompress-data)
      "3" (println "Exiting...")
      (println "Invalid option, try again."))))

(defn -main
  [& args]
  (loop []
    (read-option)
    (recur)))

(defn read-filename
  []
  (println "Please enter a filename: ")
  (read-line))
