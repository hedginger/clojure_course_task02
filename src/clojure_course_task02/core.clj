(ns clojure-course-task02.core
  (:import java.io.File)
  (:gen-class))

(defn find-files [file-name path]
  "TODO: Implement searching for a file using his name as a regexp."
   ;get all the file names into the list 
   ;filter out the names who don't match pattern
   (->> (filter #(.isFile %) (file-seq (File. path)))
        (pmap #(re-matches (re-pattern file-name) (.getName %)))
        (filter #(not (nil? %)))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
