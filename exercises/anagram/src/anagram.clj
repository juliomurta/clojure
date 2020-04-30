(ns anagram)

(defn- is-anagram? 
  [word prospect]
  (if (and (not (= word prospect)) (every? true? (map #(.contains word (str %)) prospect)))
    prospect))

(defn anagrams-for [word prospect-list] 
  (into [] (filter #(if (and (= (count word) (count %))) (is-anagram? word %)) prospect-list)))
