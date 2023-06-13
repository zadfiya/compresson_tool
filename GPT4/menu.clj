(ns menu
  (:require [clojure.string :as string]
            [clojure.java.io :as io]
            [compress :refer [compress-file uncompress-file]]))

(defn display-file-contents []
  ;; Function to display the contents of a file
  (println "Please enter a file name => ")
  (let [file-name (read-line)]
    (if (io/file file-name)
      (do
        (println "File contents:")
        (let [content (slurp file-name)]
          (println content)))
      (println "Oops: specified file does not exist"))))

(defn display-file-list []
  ;; Function to display a list of files in the current folder
  (let [files (file-seq (io/file "."))]
    (doseq [file files]
      (println (str " * " (.getPath file))))
    (println)))

(defn main-menu []
  ;; Main menu function to display options and handle user input
  (println "*** Compression Menu ***")
  (println "1. Display list of files")
  (println "2. Display file contents")
  (println "3. Compress a file")
  (println "4. Uncompress a file")
  (println "5. Exit")
  (println "Enter an option? ")
  (let [option (read-line)]
    (cond
      (= option "1") (display-file-list)
      (= option "2") (display-file-contents)
      (= option "3") (do
                       (println "Please enter a file name => ")
                       (let [file-name (read-line)]
                         (if (io/file file-name)
                           (compress-file file-name)
                           (println "Oops: specified file does not exist"))))
      (= option "4") (do
                       (println "Please enter a file name => ")
                       (let [file-name (read-line)]
                         (if (io/file file-name)
                           (uncompress-file file-name)
                           (println "Oops: specified file does not exist"))))
      (= option "5") (println "Goodbye!")
      :else (do
              (println "Invalid option. Please try again.")
              (main-menu)))))

(main-menu) ; Call the main menu function to start the application
