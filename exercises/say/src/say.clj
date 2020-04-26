(ns say)

(defn- numbers-below-20
  [num]
  (cond 
    (= num 0) "zero"
    (= num 1) "one"
    (= num 2) "two"
    (= num 3) "three"
    (= num 4) "four"
    (= num 5) "five"
    (= num 6) "six"
    (= num 7) "seven"
    (= num 8) "eight"
    (= num 9) "nine"
    (= num 10) "ten"
    (= num 11) "eleven"
    (= num 12) "twelve"
    (= num 13) "thirteen"
    (= num 14) "fourteen"
    (= num 15) "fifteen"
    (= num 16) "sixteen"
    (= num 17) "seventeen"
    (= num 18) "eighteen"
    (= num 19) "nineteen"
  )
)

(defn- between-99-and-21
  [num]
  (cond 
    (= 2 (quot num 10)) "twenty"
    (= 3 (quot num 10)) "thirty"
    (= 4 (quot num 10)) "forty"
    (= 5 (quot num 10)) "fifty"
    (= 6 (quot num 10)) "sixty"
    (= 7 (quot num 10)) "seventy"
    (= 8 (quot num 10)) "eighty"
    (= 9 (quot num 10)) "ninety"
  )
)

(defn- between-999-and-100
  [num]
  (if (and (>= num 100) (< num 999))
    (str (numbers-below-20 (quot num 100)) " hundred")
  )
)

(defn- insert-hifen? 
  [num]
  (if (and (> num 20 )
           (< num 100)
           (not (= num 0))) "-" "")
)

(defn- insert-space? 
  [num limit]
  (if (> num limit) " " "")
)

(defn- extract-mod
  [num divided-by]
  (mod num (* (quot num divided-by) divided-by))
)

(defn- extract-billion
  [num]
  (if (>= num 1000000000000) (extract-mod num 1000000000000) num)
)

(defn- extract-million
  [num]
  (if (>= num 1000000000) (extract-mod num 1000000000) num)
)

(defn- extract-thousand 
  [num]
  (if (>= num 1000000) (extract-mod num 1000000) num)
)

(defn- extract-hundred 
  [num]
  (if (>= num 1000) (extract-mod num 1000) num)
)

(defn- extract-ten 
  [num]
  (if (>= num 100) (extract-mod num 100) num)
)

(defn- extract-unity
  [num]
  (if (>= num 10) (mod num 10) num)
)

(defn- extract-unity-if-more-than-20
  [num]
  (if (> num 20) (extract-unity num) num)
)

(defn- build-string-until-999
  [hundred ten until-20]  
  (str (between-999-and-100 hundred)
       (insert-space? hundred 100)
       (between-99-and-21 ten)        
       (insert-hifen? ten) 
       (if (not (and (= until-20 0) (> (+ hundred (+ ten until-20)) 20)))
          (numbers-below-20 until-20))
  )
)

(defn- between-999999-and-1000
  [num]
  (def bt-hundred (extract-hundred (quot num 1000)))
  (def bt-ten (extract-ten bt-hundred))
  (def bt-until-20 (extract-unity-if-more-than-20 bt-ten))  
  (str (build-string-until-999 bt-hundred bt-ten bt-until-20) " thousand")  
)

(defn- between-999999999-and-1000000
  [num]
  (if (and (>= num 1000000) (< num 999999999))    
    (str (numbers-below-20 (quot num 1000000)) " million")
  )
)

(defn- between-999999999999-and-1000000000
  [num]
  (if (and (>= num 1000000000) (< num 999999999999))
    (str (numbers-below-20 (quot num 1000000000)) " billion")
  )
)

(defn- build-string 
  [num]
  (def billion (extract-billion num))
  (def million (extract-million num))
  (def thousand (extract-thousand num))
  (def hundred (extract-hundred num))
  (def ten (extract-ten num))
  (def until-20 (extract-unity-if-more-than-20 num))
  (str (if (>= num 1000000000) (between-999999999999-and-1000000000 billion))
       (if (> num 1000000000) " ")
       (if (>= num 1000000) (between-999999999-and-1000000 million))
       (if (and (> num 1000000) (<= num 999999999)) " ")       
       (if (and (>= num 1000) (> thousand 0)) (between-999999-and-1000 thousand))
       (if (and (> num 1000) (> thousand 0)) " ")       
       (if (or (not (= 0 (mod num 1000))) (= num 0))
        (build-string-until-999 hundred ten until-20)
       )
  )
)

(defn number 
  [num] 
  (if (or (< num 0) (> num 999999999999))
    (throw (IllegalArgumentException. "Out of range"))    
    (build-string num)
  )
)
