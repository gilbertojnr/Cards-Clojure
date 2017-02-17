(ns cards-clojure.core
  (:gen-class))

(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 1 14))

(defn create-deck []
  (set
    (for [suit suits 
          rank ranks]
      {:suit suit
       :rank rank})))


(defn create-hands [deck]
  (set
    (for [c1 deck
          c2 (disj deck c1)
          c3 (disj deck c1 c2)
          c4 (disj deck c1 c2 c3)]
      #{c1 c2 c3 c4})))

(defn flush? [hand]
  (= 1 (count (set (map :suit hand)))))

(defn straight? [hand]
  (let [ranks (map :rank hand)
        ranks (sort ranks)
        [a b c d]ranks]
    (and (=b(+ a 1))
         (=c(+ b 1))
         (=d(+ c 1)))))

(defn straight-flush? [hand]
  (and (= 1 (count (set (map :suit hand))))
       (let [ranks (map :rank hand)
             ranks (sort ranks)
             [a b c d] ranks]
         (and (= b (+ a 1))
              (= c (+ b 1))
              (= d (+ c 1))))))

(defn three-of-a-kind? [hand]
  (let [ranks (map :rank hand)
        [a b c d] ranks]
    (or (= a b c)
        (= a b d)
        (= a c d)
        (= b c d))))

(defn four-of-a-kind? [hand]
  (= 1 (count (set (map :rank hand)))))

(defn two-pair? [hand]
  (let [ranks (map :rank hand)
        [a b c d] ranks]
    (or (and (=a b)(= c d))
        (and (=a c)(= b d))
        (and (=a d)(= b c))
        (and (=b d)(= a c))
        (and (=b c)(= a d))
        (and (=c d)(= a b)))))
           

(defn -main []
  (let [deck (create-deck)       
        hands (create-hands deck)
        flush-hands (filter flush? hands)]
    (println (count flush-hands))))

