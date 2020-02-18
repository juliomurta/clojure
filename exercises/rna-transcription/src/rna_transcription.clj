(ns rna-transcription)

(defn get-gene [gene]
  (def dna-to-rna { :G "C" :C "G" :T "A" :A "U"})
  (let [result (get dna-to-rna (keyword (str gene)))]
    (if (= result nil)
      (throw (AssertionError. "invalid"))
      result
    )
  )
)

(defn to-rna [dna]   
  (apply str (map get-gene dna))
)
