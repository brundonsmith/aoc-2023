(ns day1part2
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is]]
            [day1part1 :refer [input]]))

(def digits-by-name
  {"0" 0
   "1" 1
   "2" 2
   "3" 3
   "4" 4
   "5" 5
   "6" 6
   "7" 7
   "8" 8
   "9" 9
   "zero" 0
   "one" 1
   "two" 2
   "three" 3
   "four" 4
   "five" 5
   "six" 6
   "seven" 7
   "eight" 8
   "nine" 9})

(defn line-digits [line]
  (->>
   line
   (map-indexed (fn [index, _]
                  (->>
                   digits-by-name
                   (map (fn [[digit-name, digit-value]]
                          (if (string/starts-with? (subs line index) digit-name)
                            digit-value
                            nil)))
                   (filter some?))))
   flatten))

(defn line-value [line]
  (let [found-digits (line-digits line)]
    (->>
     [(first found-digits) (last found-digits)]
     string/join
     parse-long)))

(defn total-value [input]
  (->>
   input
   string/split-lines
   (map string/trim)
   (map line-value)
   (reduce +)))

(deftest day-1-part-2
  (is (= 54591 (total-value input))))