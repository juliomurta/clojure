(ns isbn-verifier)

(def invalid-char #{"a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k" "l" "m" "n" "o" "p" "q" "r" "s" "t" "u" "v" "w" "y" "z"})

(defn- if-x-change-to-10
  [x]
  (if (= (clojure.string/lower-case x) "x") "10" x))

(defn- get-item
  [isbn position]
  (read-string (if-x-change-to-10 (str (get isbn (- 10 position))))))

(defn- sum 
  [isbn]
  (reduce + (for [x [10 9 8 7 6 5 4 3 2 1]]
    (* (get-item isbn x) x))))

(defn- has-invalid-char
  [isbn]
  (if (not (= (some true? (map #(clojure.string/includes? isbn (clojure.string/lower-case %)) invalid-char)) nil)) 
    true 
    false))

(defn isbn? 
  [isbn] 
  (let [value (clojure.string/replace isbn "-" "")]
    (if (or (not (= (count value) 10)) (has-invalid-char isbn))
      false
      (= (mod (sum value) 11) 0))))
  
