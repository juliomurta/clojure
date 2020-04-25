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
    (= 4 (quot num 10)) "fourty"
    (= 5 (quot num 10)) "fifty"
    (= 6 (quot num 10)) "sixty"
    (= 7 (quot num 10)) "seventy"
    (= 8 (quot num 10)) "eighty"
    (= 9 (quot num 10)) "ninety"
  )
)

(defn- between-999-and-100
  [num]
  (cond 
    (= 1 (quot num 100)) "one hundred"
    (= 2 (quot num 100)) "two hundred"
    (= 3 (quot num 100)) "three hundred"
    (= 4 (quot num 100)) "four hundred"
    (= 5 (quot num 100)) "five hundred"
    (= 6 (quot num 100)) "six hundred"
    (= 7 (quot num 100)) "seven hundred"
    (= 8 (quot num 100)) "eight hundred"
    (= 9 (quot num 100)) "nine hundred"
  )
)

(defn- insert-hifen? 
  [num]
  (if (and (> num 20 )
           (< num 100)
           (not (= num ""))) "-" "")
)

(defn- build-string 
  [num]
  (str (between-999-and-100 num) 
       (between-99-and-21 num) 
       (insert-hifen? num) 
       (numbers-below-20 (if (and (< num 100) (> num 20)) (mod num 10) num)))
)

(defn number 
  [num] 
  (if (or (< num 0) (> num 999999999999))
    (throw IllegalArgumentException)
    (build-string num)
  )
)
