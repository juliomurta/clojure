(ns armstrong-numbers)

(defn parse-bigint [s]
  (BigInteger. (re-find #"[0-9]*" s)))

(defn armstrong? [num] 
  (let [sequence (map #(parse-bigint (str %)) (vec (str num))) 
        exp (count sequence)]  
    (let [calculated-vec (map #(Math/pow % exp) sequence)]
      (let [total (reduce + (map #(parse-bigint (str %)) calculated-vec))]
        (= num total)))))
