(ns mail-proc.core
  (:import [com.pff PSTFile])
  (:gen-class))

;; package example;
;; import com.pff.*;
;; import java.util.*;

(def filename "/home/tassos/desktop/outlook-files/manganaris.2@buckeyemail.osu.edu.ost")

(defn printDepth
  "docstring"
  [depth]
  (apply print (repeat (- depth 1) " |"))
  (print " |- "))

(defn processFolder
  "docstring"
  [depth folder]
  (when (> depth 0)
    (printDepth depth)
    (println (.getDisplayName folder)))

  (when (.hasSubfolders folder)
    (doseq [f (.getSubFolders folder)]
      (processFolder (+ depth 1) f)))

  (when (> (.getContentCount folder) 10)
    (map (fn [node]
           (printDepth (+ depth 1))
           (println (str "Type: " (.getNodeType node))))
         (.getChildren folder 10))))

(defn -main
  [& args]
  (let [pstfile (PSTFile. filename)]
    (println (.getDisplayName (.getMessageStore pstfile)))
    (processFolder 0 (.getRootFolder pstfile))
    (println "Done")))
