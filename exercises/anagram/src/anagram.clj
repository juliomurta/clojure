(ns anagram)

(defn- to-lower
  [word]
  (clojure.string/lower-case word))

(defn- same-letters-number
  [word prospect]
  (= (frequencies (to-lower word)) (frequencies (to-lower prospect))))

(defn- is-anagram? 
  [word prospect]
  (if (and (not (= (to-lower word) (to-lower prospect))) 
                (every? true? (map #(.contains (to-lower word) (to-lower (str %))) prospect))
                (same-letters-number word prospect))
    prospect))

(defn anagrams-for [word prospect-list] 
  (into [] (filter #(if (and (= (count word) (count %))) (is-anagram? word %)) prospect-list)))
