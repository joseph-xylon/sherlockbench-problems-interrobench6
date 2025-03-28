(ns interrobench6
  (:require [clojure.string :as string]
            [clojure.math :as math]))

(def namespace-name "InterroBench 6")

;; problem-sets are defined by tag and name
(def tag-names
  {::all "All"
   ::representative10 "Representative 10"})

(def problems
  [{:name- "is odd"
    :args ["integer"]
    :function (fn [a]
                (odd? a))
    :verifications [[100]
                    [13]
                    [42]
                    [93]]
    :output-type "boolean"
    :tags #{::all}}

   {:name- "subtraction"
    :args ["integer" "integer"]
    :function (fn [a b]
                (- a b))
    :verifications [[10 2]
                    [7 5]
                    [1 0]]
    :output-type "integer"
    :tags #{::all}}

   {:name- "find higher number"
    :args ["integer" "integer"]
    :function (fn [a b]
                (max a b))
    :verifications [[10 5]
                    [3 8]
                    [-2 -5]
                    [7 7]]
    :output-type "integer"
    :tags #{::all ::representative10}}

   {:name- "pythagorean theorem"
    :args ["integer" "integer"]
    :function (fn [a b]
                (math/sqrt (+ (* a a) (* b b))))
    :verifications [[3 4]
                    [5 12]
                    [8 15]]
    :output-type "float"
    :tags #{::all ::representative10}}

   {:name- "float division"
    :args ["integer" "integer"]
    :function (fn [a b]
                (if (zero? b)
                  "Undefined"
                  (float (/ a b))))
    :verifications [[1 2]
                    [4 5]
                    [45 16]]
    :output-type "string"
    :tags #{::all}}

   {:name- "triangle third angle"
    :args ["integer" "integer"]
    :function (fn [a b]
                (- 180 a b))
    :verifications [[60 60]
                    [45 55]
                    [120 30]]
    :output-type "integer"
    :tags #{::all ::representative10}}

   {:name- "divide by 4"
    :args ["integer"]
    :function (fn [a]
                (/ a 4))
    :verifications [[8]
                    [16]
                    [44]]
    :output-type "float"
    :tags #{::all}}

   {:name- "remainder"
    :args ["integer" "integer"]
    :function (fn [a b]
                (if (zero? b)
                  "Undefined"
                  (mod a b)))
    :verifications [[3 2]
                    [45 16]
                    [13 13]]
    :output-type "string"
    :tags #{::all}}

   {:name- "median of 5"
    :args ["integer" "integer" "integer" "integer" "integer"]
    :function (fn [a b c d e]
                (let [nums [a b c d e]
                      sorted (sort nums)]
                  (nth sorted 2)))
    :verifications [[1 2 3 4 5]
                    [40 15 60 3 13]
                    [0 0 0 4 400]]
    :output-type "integer"
    :tags #{::all}}

   {:name- "circle circumference"
    :args ["integer"]
    :function (fn [a]
                (* 2 math/PI a))
    :verifications [[1]
                    [5]
                    [7]]
    :output-type "float"
    :tags #{::all ::representative10}}

   {:name- "is prime"
    :args ["integer"]
    :function (fn [a]
                (cond
                  (< a 2) false
                  (= a 2) true
                  (even? a) false
                  :else (let [limit (int (math/sqrt a))]
                          (loop [div 3]
                            (cond
                              (> div limit) true
                              (zero? (mod a div)) false
                              :else (recur (+ div 2)))))))
    :verifications [[2]
                    [7]
                    [4]
                    [1]
                    [13]
                    [25]]
    :output-type "boolean"
    :tags #{::all ::representative10}}

   {:name- "concatenate numbers"
    :args ["integer" "integer" "integer"]
    :function (fn [a b c]
                (Integer/parseInt (str (abs a) (abs b) (abs c))))
    :verifications [[1 2 3]
                    [5 10 15]
                    [42 0 7]]
    :output-type "integer"
    :tags #{::all}}

   {:name- "ignore one argument"
    :args ["integer" "integer" "integer"]
    :function (fn [a b c]
                (* a c))
    :verifications [[3 2 9]
                    [4 5 2]
                    [0 9 3]]
    :output-type "integer"
    :tags #{::all ::representative10}}

   {:name- "difference of closest pair"
    :args ["integer" "integer" "integer"]
    :function (fn [a b c]
                (let [diffs [(abs (- a b))
                             (abs (- b c))
                             (abs (- a c))]]
                  (apply min diffs)))
    :verifications [[1 5 8]
                    [10 11 15]
                    [0 7 2]
                    [4 4 8]]
    :output-type "integer"
    :tags #{::all ::representative10}}

   {:name- "subtract and divide"
    :args ["integer" "integer" "integer"]
    :function (fn [a b c]
                (if (zero? c)
                  "Undefined"
                  (quot (- a b) c)))
    :verifications [[10 2 2]
                    [35 16 4]
                    [13 3 5]]
    :output-type "string"
    :tags #{::all}}

   {:name- "times 3 round to nearest 5"
    :args ["integer"]
    :function (fn [a]
                (let [result (* a 3)
                      rounded (math/round (/ result 5))]
                  (* rounded 5)))
    :verifications [[10]
                    [7]
                    [3]
                    [0]
                    [15]
                    [-4]]
    :output-type "integer"
    :tags #{::all}}

   {:name- "greatest common factor"
    :args ["integer" "integer"]
    :function (fn [a b]
                (loop [x (abs a)
                       y (abs b)]
                  (if (zero? y)
                    x
                    (recur y (mod x y)))))
    :verifications [[12 18]
                    [54 24]
                    [7 13]
                    [100 25]]
    :output-type "integer"
    :tags #{::all ::representative10}}

   {:name- "modulus 3 as string"
    :args ["integer"]
    :function (fn [a]
                (let [fruits {1 "apple", 2 "orange", 0 "banana"}]
                  (get fruits (mod a 3))))
    :verifications [[7]
                    [5]
                    [0]
                    [15]]
    :output-type "string"
    :tags #{::all ::representative10}}

   {:name- "set heading"
    :args ["integer" "integer"]
    :function (fn [a b]
                (let [lat  36
                      long 140
                      vdiff (- lat a)
                      hdiff (- long b)]
                  (cond
                    (and (zero? vdiff) (zero? hdiff))
                    "You've arrived!"
                    
                    (> (abs vdiff) (abs hdiff))
                    (if (pos? vdiff) "North" "South")
                    
                    :else
                    (if (pos? hdiff) "East" "West"))))
    :verifications [[12 18]
                    [54 144]
                    [30 144]
                    [30 150]]
    :output-type "string"
    :tags #{::all}}

   {:name- "reverse string"
    :args ["string"]
    :function (fn [a]
                (apply str (reverse a)))
    :verifications [["whimsical"]
                    ["vortex"]
                    ["abcde"]]
    :output-type "string"
    :tags #{::all}}

   {:name- "count vowels"
    :args ["string"]
    :function (fn [a]
                (count (filter #{\a \e \i \o \u} (string/lower-case a))))
    :verifications [["whimsical"]
                    ["aardvark"]
                    ["rhythm"]
                    ["Python"]]
    :output-type "integer"
    :tags #{::all}}

   {:name- "delete second letter and reverse"
    :args ["string"]
    :function (fn [a]
                (if (< (count a) 2)
                  a
                  (apply str (reverse (str (first a) (subs a 2))))))
    :verifications [["whimsical"]
                    ["python"]
                    ["test"]]
    :output-type "string"
    :tags #{::all}}

   {:name- "longest common substring"
    :args ["string" "string"]
    :function (fn [a b]
                (if (or (empty? a) (empty? b))
                  ""
                  (let [substrings-a (for [i (range (count a))
                                           j (range (inc i) (inc (count a)))]
                                       (subs a i j))
                        common (filter #(string/includes? b %) substrings-a)]
                    (if (seq common)
                      (apply max-key count common)
                      ""))))
    :verifications [["whimsical" "world"]
                    ["python" "typhoon"]
                    ["abcde" "cdefg"]
                    ["cat" "hat"]
                    ["" "test"]]
    :output-type "string"
    :tags #{::all ::representative10}}

   {:name- "contains substring"
    :args ["string" "string"]
    :function (fn [a b]
                (string/includes? a b))
    :verifications [["fresh potato" "pot"]
                    ["ear" "earth"]
                    ["abc" "d"]]
    :output-type "boolean"
    :tags #{::all}}

   {:name- "crack lock"
    :args ["integer" "integer" "integer" "integer"]
    :function (fn [a b c d]
                (let [code [5 3 8 4]
                      attempts [a b c d]
                      acc (reduce + (map #(abs (- %1 %2)) code attempts))]
                  (if (zero? acc)
                    "You got it!"
                    acc)))
    :verifications [[1 9 7 7]
                    [9 5 9 8]
                    [4 2 1 0]]
    :output-type "string"
    :tags #{::all}}
   ])
