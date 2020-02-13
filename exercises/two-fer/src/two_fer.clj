(ns two-fer)

(defn two-fer
  ([]
  "One for you, one for me.")
([name] 
  (if (empty? name) (two-fer) (str "One for " name ", one for me."))))
