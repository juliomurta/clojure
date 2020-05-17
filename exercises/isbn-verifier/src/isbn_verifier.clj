(ns isbn-verifier)

;(def invalid-char #{"a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k" "l" "m" "n" "o" "p" "q" "r" "s" "t" "u" "v" "w" "y" "z"})

(defn- sum 
  [isbn]
  (reduce + (for [x [10 9 8 7 6 5 4 3 2 1]]
    (* (read-string (str (get isbn (- 10 x)))) x))))

;(defn- is-valid 
;  [value]
;  (contains? value invalid-char))

(defn isbn? [isbn] 
  (let [value (clojure.string/replace isbn "-" "")]
    (if (not (= (count value) 10))
      (false)
      (= (mod (sum value) 11) 0))))
 ;     (if (not (is-valid isbn))
 ;     (false)
  
