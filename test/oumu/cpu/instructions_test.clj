(ns oumu.cpu.instructions-test
  (:require [clojure.test :refer :all]
            [oumu.cpu.instructions :refer :all :as i]
            [oumu.cpu.registers :as r]
            (oumu.cpu.instructions-test.examples [numeric :refer :all]
                                                 [imul :refer :all]
                                                 [simple :refer :all]
                                                 [bound :refer :all]
                                                 [arpl :refer :all]
                                                 [i-8x :refer :all]
                                                 [x87 :refer :all]
                                                 [i-dx :refer :all]
                                                 [test :refer :all]
                                                 [xchg :refer :all]
                                                 [mov :refer :all]
                                                 [lea :refer :all]
                                                 [pop :refer :all]
                                                 [i-cx :refer :all]
                                                 [les :refer :all]
                                                 [lds :refer :all]
                                                 [i-fx :refer :all]
                                                 [i-fe :refer :all]
                                                 [i-ff :refer :all]
                                                 [i-000f :refer :all]
                                                 [i-010f :refer :all]
                                                 [lar-lsl :refer :all]
                                                 [setcc :refer :all]
                                                 [bt :refer :all]
                                                 [shld :refer :all]
                                                 [bts :refer :all]
                                                 [shrd :refer :all]
                                                 [btr :refer :all]
                                                 [i-ba0f :refer :all]
                                                 [btc :refer :all]
                                                 [lss :refer :all]
                                                 [lfs :refer :all]
                                                 [lgs :refer :all]
                                                 [movzx :refer :all]
                                                 [bsf :refer :all]
                                                 [bsr :refer :all]
                                                 [movsx :refer :all])))


(defn safe-merge [& maps]
  {:post [(= (count %) (apply + (map count maps)))]}
  (apply merge maps))


(def all-decode-examples
  (safe-merge
   decode-simple-examples
   decode-bound-examples
   decode-arpl-examples
   decode-examples-0x80
   decode-examples-0x81
   decode-examples-0x82
   decode-examples-0x83
   decode-imul-examples
   decode-test-examples
   decode-xchg-examples
   decode-mov-examples
   decode-lea-examples
   decode-pop-examples
   decode-examples-0xc0
   decode-examples-0xc1
   decode-les-examples
   decode-lds-examples
   decode-examples-0xd0
   decode-examples-0xd1
   decode-examples-0xd2
   decode-examples-0xd3
   decode-examples-0xd8
   decode-examples-0xd9
   decode-examples-0xda
   decode-examples-0xdb
   decode-examples-0xdc-0xdd
   decode-examples-0xde-0xdf
   decode-examples-0xf6
   decode-examples-0xf7
   decode-examples-0xfe
   decode-examples-0xff
   decode-examples-0x000f
   decode-examples-0x010f
   decode-examples-lar-lsl
   decode-examples-setcc
   decode-examples-bt
   decode-examples-shld
   decode-examples-bts
   decode-examples-shrd
   decode-lss-examples
   decode-examples-btr
   decode-lfs-examples
   decode-lgs-examples
   decode-examples-movzx
   decode-examples-0xba0f
   decode-examples-btc
   decode-examples-bsf
   decode-examples-bsr
   decode-examples-movsx
   (gen-numeric-examples 0x00 ::i/add)
   (gen-numeric-examples 0x08 ::i/or)
   (gen-numeric-examples 0x10 ::i/adc)
   (gen-numeric-examples 0x18 ::i/sbb)
   (gen-numeric-examples 0x20 ::i/and)
   (gen-numeric-examples 0x28 ::i/sub)
   (gen-numeric-examples 0x30 ::i/xor)
   (gen-numeric-examples 0x38 ::i/cmp)))


(defn find-gaps []
  (let [used (set (concat (map first (keys all-decode-examples)) (map #(vector (first %) (second %)) (keys all-decode-examples))))]
    (concat (mapv #(format "%02x" %) (filter #(not (used %)) (range 256)))
            (mapv #(format "%02x %02x" (first %) (second %)) (filter #(not (used %)) (map #(vector 0x0f %) (range 256)))))))


(deftest signed-byte-test
  (is (= 0 (signed-byte 0)))
  (is (= 0x7f (signed-byte 0x7f)))
  (is (= -0x80 (signed-byte 0x80)))
  (is (= -1 (signed-byte 0xff))))


(deftest signed-word-test
  (is (= 0 (signed-word 0)))
  (is (= 0x7fff (signed-word 0x7fff)))
  (is (= -0x8000 (signed-word 0x8000)))
  (is (= -1 (signed-word 0xffff))))


(deftest byte-to-word-test
  (is (= 0 (byte-to-word 0)))
  (is (= 0x7f (byte-to-word 0x7f)))
  (is (= 0xff80 (byte-to-word 0x80)))
  (is (= 0xffff (byte-to-word 0xff))))


(deftest decode-test
  (let [test (fn [instr bytes]
               (is (= instr (decode bytes)) (mapv #(format "%02x" %) bytes)))]
    (doseq [[bytes instr] (sort-by (comp ::i/tag second) all-decode-examples)]
      (let [instr (when instr
                    (assoc instr ::i/length (count bytes)))]
        (test instr (conj bytes 0xff 0x00 0xff))
        (test instr (conj bytes 0xff 0x00))
        (test instr (conj bytes 0xff))
        (test instr bytes)
        (doseq [len (range 1 (count bytes))]
          (test nil (subvec bytes 0 len)))))
    (test nil [])))
