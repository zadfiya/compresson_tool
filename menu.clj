(ns menu
  (:require [clojure.java.io :as io]
            [compress :refer :all]
            [clojure.string :as str]))

(defn list-files []
  (map #(.getName %) (file-seq (io/file "."))))

(defn display-file [filename]
  (try
    (println (slurp filename))
    (catch Exception e
      (println "Oops: An error occurred during file display."))))

(defn start-menu []
  (println "*** Compression Menu ***")
  (println "1. Display list of files")
  (println "2. Display file contents")
  (println "3. Compress a file")
  (println "4. Uncompress a file")
  (println "5. Exit")
  (println "Enter an option?")
  (let [option (read-line)]
    (cond
      (= option "1") (do (println (str/join "\n" (list-files))) (start-menu))
      (= option "2") (do (println "Please enter a file name:")
                          (display-file (read-line))
                          (start-menu))
      (= option "3") (do (println "Please enter a file name:")
                          (println (compress-file (read-line)))
                          (start-menu))
      (= option "4") (do (println "Please enter a file name:")
                          (println (uncompress-file (read-line)))
                          (start-menu))
      (= option "5") (println "Exiting...")
      :else (do (println "Invalid option.") (start-menu)))))

(defn -main
  "The entry point for the application."
  [& args]
  (start-menu))
