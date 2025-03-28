(ns interrobench6-test
  (:require [clojure.test :refer [deftest is run-tests]]
            [interrobench6 :refer [problems]]))

;; we need some helper functions to extract the function and call it
(defn find-map-by-key-value [key value coll]
  (some #(when (= (key %) value) %) coll))

(defn eval-fn [fn-name & args]
  (apply (:function (find-map-by-key-value :name- fn-name problems)) args))

;; Helper for floating-point comparison
(defn float= [a b]
  (cond 
    (and (string? a) (string? b)) (= a b)
    :else (< (Math/abs (- (double a) (double b))) 1e-6)))

(deftest test-is-odd
  (is (= true (eval-fn "is odd" 5)))
  (is (= false (eval-fn "is odd" 8)))
  (is (= false (eval-fn "is odd" 0))))

(deftest test-subtraction
  (is (= 8 (eval-fn "subtraction" 10 2)))
  (is (= 2 (eval-fn "subtraction" 7 5)))
  (is (= 1 (eval-fn "subtraction" 1 0))))

(deftest test-find-higher-number
  (is (= 10 (eval-fn "find higher number" 10 5)))
  (is (= 8 (eval-fn "find higher number" 3 8)))
  (is (= -2 (eval-fn "find higher number" -2 -5)))
  (is (= 7 (eval-fn "find higher number" 7 7))))

(deftest test-pythagorean-theorem
  (is (float= 5.0 (eval-fn "pythagorean theorem" 3 4)))
  (is (float= 13.0 (eval-fn "pythagorean theorem" 5 12)))
  (is (float= 17.0 (eval-fn "pythagorean theorem" 8 15))))

(deftest test-float-division
  (is (float= 0.5 (eval-fn "float division" 1 2)))
  (is (float= 0.8 (eval-fn "float division" 4 5)))
  (is (float= 2.8125 (eval-fn "float division" 45 16)))
  (is (= "Undefined" (eval-fn "float division" 10 0))))

(deftest test-triangle-third-angle
  (is (= 60 (eval-fn "triangle third angle" 60 60)))
  (is (= 80 (eval-fn "triangle third angle" 45 55)))
  (is (= 30 (eval-fn "triangle third angle" 120 30))))

(deftest test-divide-by-4
  (is (float= 2 (eval-fn "divide by 4" 8)))
  (is (float= 4 (eval-fn "divide by 4" 16)))
  (is (float= 11 (eval-fn "divide by 4" 44))))

(deftest test-remainder
  (is (= 1 (eval-fn "remainder" 3 2)))
  (is (= 13 (eval-fn "remainder" 45 16)))
  (is (= 0 (eval-fn "remainder" 13 13)))
  (is (= "Undefined" (eval-fn "remainder" 10 0))))

(deftest test-median-of-5
  (is (= 3 (eval-fn "median of 5" 1 2 3 4 5)))
  (is (= 15 (eval-fn "median of 5" 40 15 60 3 13)))
  (is (= 0 (eval-fn "median of 5" 0 0 0 4 400))))

(deftest test-circle-circumference
  (is (float= (* 2 Math/PI 1) (eval-fn "circle circumference" 1)))
  (is (float= (* 2 Math/PI 5) (eval-fn "circle circumference" 5)))
  (is (float= (* 2 Math/PI 7) (eval-fn "circle circumference" 7))))

(deftest test-is-prime
  (is (= true (eval-fn "is prime" 2)))
  (is (= true (eval-fn "is prime" 7)))
  (is (= false (eval-fn "is prime" 4)))
  (is (= false (eval-fn "is prime" 1)))
  (is (= true (eval-fn "is prime" 13)))
  (is (= false (eval-fn "is prime" 25))))

(deftest test-concatenate-numbers
  (is (= 123 (eval-fn "concatenate numbers" 1 2 3)))
  (is (= 51015 (eval-fn "concatenate numbers" 5 10 15)))
  (is (= 4207 (eval-fn "concatenate numbers" 42 0 7)))
  (is (= 123 (eval-fn "concatenate numbers" -1 -2 -3))))

(deftest test-ignore-one-argument
  (is (= 27 (eval-fn "ignore one argument" 3 2 9)))
  (is (= 8 (eval-fn "ignore one argument" 4 5 2)))
  (is (= 0 (eval-fn "ignore one argument" 0 9 3))))

(deftest test-difference-of-closest-pair
  (is (= 3 (eval-fn "difference of closest pair" 1 5 8)))
  (is (= 1 (eval-fn "difference of closest pair" 10 11 15)))
  (is (= 2 (eval-fn "difference of closest pair" 0 7 2)))
  (is (= 0 (eval-fn "difference of closest pair" 4 4 8))))

(deftest test-subtract-and-divide
  (is (= 4 (eval-fn "subtract and divide" 10 2 2)))
  (is (= 4 (eval-fn "subtract and divide" 35 16 4)))
  (is (= 2 (eval-fn "subtract and divide" 13 3 5)))
  (is (= "Undefined" (eval-fn "subtract and divide" 10 5 0))))

(deftest test-times-3-round-to-nearest-5
  (is (= 30 (eval-fn "times 3 round to nearest 5" 10)))
  (is (= 20 (eval-fn "times 3 round to nearest 5" 7)))
  (is (= 10 (eval-fn "times 3 round to nearest 5" 3)))
  (is (= 0 (eval-fn "times 3 round to nearest 5" 0)))
  (is (= 45 (eval-fn "times 3 round to nearest 5" 15)))
  (is (= -10 (eval-fn "times 3 round to nearest 5" -4))))

(deftest test-greatest-common-factor
  (is (= 6 (eval-fn "greatest common factor" 12 18)))
  (is (= 6 (eval-fn "greatest common factor" 54 24)))
  (is (= 1 (eval-fn "greatest common factor" 7 13)))
  (is (= 25 (eval-fn "greatest common factor" 100 25))))

(deftest test-modulus-3-as-string
  (is (= "apple" (eval-fn "modulus 3 as string" 7)))
  (is (= "orange" (eval-fn "modulus 3 as string" 5)))
  (is (= "banana" (eval-fn "modulus 3 as string" 0)))
  (is (= "banana" (eval-fn "modulus 3 as string" 15))))

(deftest test-set-heading
  (is (= "East" (eval-fn "set heading" 12 18)))
  (is (= "South" (eval-fn "set heading" 54 144)))
  (is (= "North" (eval-fn "set heading" 30 144)))
  (is (= "West" (eval-fn "set heading" 30 150)))
  (is (= "You've arrived!" (eval-fn "set heading" 36 140))))

(deftest test-reverse-string
  (is (= "lacismihw" (eval-fn "reverse string" "whimsical")))
  (is (= "xetrov" (eval-fn "reverse string" "vortex")))
  (is (= "edcba" (eval-fn "reverse string" "abcde"))))

(deftest test-count-vowels
  (is (= 3 (eval-fn "count vowels" "whimsical")))
  (is (= 3 (eval-fn "count vowels" "aardvark")))
  (is (= 0 (eval-fn "count vowels" "rhythm")))
  (is (= 1 (eval-fn "count vowels" "Python"))))

(deftest test-delete-second-letter-and-reverse
  (is (= "lacismiw" (eval-fn "delete second letter and reverse" "whimsical")))
  (is (= "nohtp" (eval-fn "delete second letter and reverse" "python")))
  (is (= "tst" (eval-fn "delete second letter and reverse" "test")))
  (is (= "a" (eval-fn "delete second letter and reverse" "a"))))

(deftest test-longest-common-substring
  (is (= "l" (eval-fn "longest common substring" "whimsical" "world")))
  (is (= "on" (eval-fn "longest common substring" "python" "typhoon")))
  (is (= "cde" (eval-fn "longest common substring" "abcde" "cdefg")))
  (is (= "at" (eval-fn "longest common substring" "cat" "hat")))
  (is (= "" (eval-fn "longest common substring" "" "test"))))

(deftest test-contains-substring
  (is (= true (eval-fn "contains substring" "fresh potato" "pot")))
  (is (= false (eval-fn "contains substring" "ear" "earth")))
  (is (= false (eval-fn "contains substring" "abc" "d"))))

(deftest test-crack-lock
  (is (= 14 (eval-fn "crack lock" 1 9 7 7)))
  (is (= 11 (eval-fn "crack lock" 9 5 9 8)))
  (is (= 13 (eval-fn "crack lock" 4 2 1 0)))
  (is (= "You got it!" (eval-fn "crack lock" 5 3 8 4))))

(comment
  (run-tests)
  )
