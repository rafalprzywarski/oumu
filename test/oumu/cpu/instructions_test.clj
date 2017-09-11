(ns oumu.cpu.instructions-test
  (:require [clojure.test :refer :all]
            [oumu.cpu.instructions :refer :all :as i]
            [oumu.cpu.registers :as r]
            (oumu.cpu.instructions-test [numeric-examples :refer :all]
                                        [imul-examples :refer :all]
                                        [simple-examples :refer :all]
                                        [bound-examples :refer :all]
                                        [arpl-examples :refer :all]
                                        [i-8x-examples :refer :all]
                                        [x87-examples :refer :all]
                                        [i-dx-examples :refer :all]
                                        [test-examples :refer :all]
                                        [xchg-examples :refer :all]
                                        [mov-examples :refer :all]
                                        [lea-examples :refer :all]
                                        [pop-examples :refer :all]
                                        [i-cx-examples :refer :all]
                                        [les-examples :refer :all]
                                        [lds-examples :refer :all]
                                        [i-fx-examples :refer :all]
                                        [i-fe-examples :refer :all]
                                        [i-ff-examples :refer :all]
                                        [i-000f-examples :refer :all]
                                        [i-010f-examples :refer :all]
                                        [lar-lsl-examples :refer :all]
                                        [setcc-examples :refer :all]
                                        [bt-examples :refer :all]
                                        [shld-examples :refer :all]
                                        [bts-examples :refer :all]
                                        [shrd-examples :refer :all]
                                        [btr-examples :refer :all]
                                        [i-ba0f-examples :refer :all]
                                        [btc-examples :refer :all]
                                        [lss-examples :refer :all])))


(defn safe-merge [& maps]
  {:post [(= (count %) (apply + (map count maps)))]}
  (apply merge maps))


(def decode-lfs-examples
  {[0x0f 0xb4 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xb4 0x01] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xb4 0x02] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xb4 0x03] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xb4 0x04] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xb4 0x05] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xb4 0x06 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [0x0ff0]]}
   [0x0f 0xb4 0x06 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [0xf00f]]}
   [0x0f 0xb4 0x07] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xb4 0x08] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xb4 0x09] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xb4 0x0a] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xb4 0x0b] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xb4 0x0c] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xb4 0x0d] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xb4 0x0e 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [0x0ff0]]}
   [0x0f 0xb4 0x0e 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [0xf00f]]}
   [0x0f 0xb4 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xb4 0x10] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xb4 0x11] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xb4 0x12] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xb4 0x13] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xb4 0x14] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xb4 0x15] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xb4 0x16 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [0x0ff0]]}
   [0x0f 0xb4 0x16 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [0xf00f]]}
   [0x0f 0xb4 0x17] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xb4 0x18] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xb4 0x19] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xb4 0x1a] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xb4 0x1b] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xb4 0x1c] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xb4 0x1d] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xb4 0x1e 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [0x0ff0]]}
   [0x0f 0xb4 0x1e 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [0xf00f]]}
   [0x0f 0xb4 0x1f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xb4 0x20] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xb4 0x21] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xb4 0x22] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xb4 0x23] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xb4 0x24] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xb4 0x25] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xb4 0x26 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [0x0ff0]]}
   [0x0f 0xb4 0x26 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [0xf00f]]}
   [0x0f 0xb4 0x27] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xb4 0x28] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xb4 0x29] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xb4 0x2a] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xb4 0x2b] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xb4 0x2c] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xb4 0x2d] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xb4 0x2e 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [0x0ff0]]}
   [0x0f 0xb4 0x2e 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [0xf00f]]}
   [0x0f 0xb4 0x2f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xb4 0x30] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xb4 0x31] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xb4 0x32] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xb4 0x33] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xb4 0x34] {::i/tag ::i/lfs, ::i/args [::r/si [::r/si]]}
   [0x0f 0xb4 0x35] {::i/tag ::i/lfs, ::i/args [::r/si [::r/di]]}
   [0x0f 0xb4 0x36 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [0x0ff0]]}
   [0x0f 0xb4 0x36 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [0xf00f]]}
   [0x0f 0xb4 0x37] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xb4 0x38] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xb4 0x39] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xb4 0x3a] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xb4 0x3b] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xb4 0x3c] {::i/tag ::i/lfs, ::i/args [::r/di [::r/si]]}
   [0x0f 0xb4 0x3d] {::i/tag ::i/lfs, ::i/args [::r/di [::r/di]]}
   [0x0f 0xb4 0x3e 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [0x0ff0]]}
   [0x0f 0xb4 0x3e 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [0xf00f]]}
   [0x0f 0xb4 0x3f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xb4 0x40 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xb4 0x40 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/si -16]]}
   [0x0f 0xb4 0x40 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/si 15]]}
   [0x0f 0xb4 0x41 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xb4 0x41 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/di -16]]}
   [0x0f 0xb4 0x41 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/di 15]]}
   [0x0f 0xb4 0x42 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xb4 0x42 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/si -16]]}
   [0x0f 0xb4 0x42 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/si 15]]}
   [0x0f 0xb4 0x43 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xb4 0x43 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/di -16]]}
   [0x0f 0xb4 0x43 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/di 15]]}
   [0x0f 0xb4 0x44 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xb4 0x44 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/si -16]]}
   [0x0f 0xb4 0x44 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/si 15]]}
   [0x0f 0xb4 0x45 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xb4 0x45 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/di -16]]}
   [0x0f 0xb4 0x45 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/di 15]]}
   [0x0f 0xb4 0x46 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp]]}
   [0x0f 0xb4 0x46 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp -16]]}
   [0x0f 0xb4 0x46 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp 15]]}
   [0x0f 0xb4 0x47 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xb4 0x47 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx -16]]}
   [0x0f 0xb4 0x47 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx 15]]}
   [0x0f 0xb4 0x48 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xb4 0x48 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/si -16]]}
   [0x0f 0xb4 0x48 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/si 15]]}
   [0x0f 0xb4 0x49 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xb4 0x49 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/di -16]]}
   [0x0f 0xb4 0x49 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/di 15]]}
   [0x0f 0xb4 0x4a 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xb4 0x4a 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/si -16]]}
   [0x0f 0xb4 0x4a 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/si 15]]}
   [0x0f 0xb4 0x4b 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xb4 0x4b 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/di -16]]}
   [0x0f 0xb4 0x4b 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/di 15]]}
   [0x0f 0xb4 0x4c 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xb4 0x4c 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/si -16]]}
   [0x0f 0xb4 0x4c 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/si 15]]}
   [0x0f 0xb4 0x4d 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xb4 0x4d 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/di -16]]}
   [0x0f 0xb4 0x4d 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/di 15]]}
   [0x0f 0xb4 0x4e 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp]]}
   [0x0f 0xb4 0x4e 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp -16]]}
   [0x0f 0xb4 0x4e 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp 15]]}
   [0x0f 0xb4 0x4f 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xb4 0x4f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx -16]]}
   [0x0f 0xb4 0x4f 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx 15]]}
   [0x0f 0xb4 0x50 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xb4 0x50 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/si -16]]}
   [0x0f 0xb4 0x50 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/si 15]]}
   [0x0f 0xb4 0x51 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xb4 0x51 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/di -16]]}
   [0x0f 0xb4 0x51 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/di 15]]}
   [0x0f 0xb4 0x52 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xb4 0x52 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/si -16]]}
   [0x0f 0xb4 0x52 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/si 15]]}
   [0x0f 0xb4 0x53 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xb4 0x53 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/di -16]]}
   [0x0f 0xb4 0x53 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/di 15]]}
   [0x0f 0xb4 0x54 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xb4 0x54 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/si -16]]}
   [0x0f 0xb4 0x54 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/si 15]]}
   [0x0f 0xb4 0x55 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xb4 0x55 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/di -16]]}
   [0x0f 0xb4 0x55 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/di 15]]}
   [0x0f 0xb4 0x56 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp]]}
   [0x0f 0xb4 0x56 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp -16]]}
   [0x0f 0xb4 0x56 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp 15]]}
   [0x0f 0xb4 0x57 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xb4 0x57 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx -16]]}
   [0x0f 0xb4 0x57 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx 15]]}
   [0x0f 0xb4 0x58 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xb4 0x58 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/si -16]]}
   [0x0f 0xb4 0x58 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/si 15]]}
   [0x0f 0xb4 0x59 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xb4 0x59 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/di -16]]}
   [0x0f 0xb4 0x59 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/di 15]]}
   [0x0f 0xb4 0x5a 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xb4 0x5a 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/si -16]]}
   [0x0f 0xb4 0x5a 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/si 15]]}
   [0x0f 0xb4 0x5b 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xb4 0x5b 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/di -16]]}
   [0x0f 0xb4 0x5b 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/di 15]]}
   [0x0f 0xb4 0x5c 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xb4 0x5c 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/si -16]]}
   [0x0f 0xb4 0x5c 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/si 15]]}
   [0x0f 0xb4 0x5d 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xb4 0x5d 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/di -16]]}
   [0x0f 0xb4 0x5d 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/di 15]]}
   [0x0f 0xb4 0x5e 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp]]}
   [0x0f 0xb4 0x5e 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp -16]]}
   [0x0f 0xb4 0x5e 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp 15]]}
   [0x0f 0xb4 0x5f 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xb4 0x5f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx -16]]}
   [0x0f 0xb4 0x5f 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx 15]]}
   [0x0f 0xb4 0x60 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xb4 0x60 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/si -16]]}
   [0x0f 0xb4 0x60 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/si 15]]}
   [0x0f 0xb4 0x61 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xb4 0x61 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/di -16]]}
   [0x0f 0xb4 0x61 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/di 15]]}
   [0x0f 0xb4 0x62 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xb4 0x62 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/si -16]]}
   [0x0f 0xb4 0x62 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/si 15]]}
   [0x0f 0xb4 0x63 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xb4 0x63 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/di -16]]}
   [0x0f 0xb4 0x63 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/di 15]]}
   [0x0f 0xb4 0x64 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xb4 0x64 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/si -16]]}
   [0x0f 0xb4 0x64 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/si 15]]}
   [0x0f 0xb4 0x65 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xb4 0x65 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/di -16]]}
   [0x0f 0xb4 0x65 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/di 15]]}
   [0x0f 0xb4 0x66 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp]]}
   [0x0f 0xb4 0x66 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp -16]]}
   [0x0f 0xb4 0x66 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp 15]]}
   [0x0f 0xb4 0x67 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xb4 0x67 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx -16]]}
   [0x0f 0xb4 0x67 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx 15]]}
   [0x0f 0xb4 0x68 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xb4 0x68 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/si -16]]}
   [0x0f 0xb4 0x68 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/si 15]]}
   [0x0f 0xb4 0x69 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xb4 0x69 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/di -16]]}
   [0x0f 0xb4 0x69 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/di 15]]}
   [0x0f 0xb4 0x6a 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xb4 0x6a 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/si -16]]}
   [0x0f 0xb4 0x6a 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/si 15]]}
   [0x0f 0xb4 0x6b 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xb4 0x6b 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/di -16]]}
   [0x0f 0xb4 0x6b 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/di 15]]}
   [0x0f 0xb4 0x6c 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xb4 0x6c 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/si -16]]}
   [0x0f 0xb4 0x6c 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/si 15]]}
   [0x0f 0xb4 0x6d 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xb4 0x6d 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/di -16]]}
   [0x0f 0xb4 0x6d 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/di 15]]}
   [0x0f 0xb4 0x6e 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp]]}
   [0x0f 0xb4 0x6e 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp -16]]}
   [0x0f 0xb4 0x6e 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp 15]]}
   [0x0f 0xb4 0x6f 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xb4 0x6f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx -16]]}
   [0x0f 0xb4 0x6f 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx 15]]}
   [0x0f 0xb4 0x70 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xb4 0x70 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/si -16]]}
   [0x0f 0xb4 0x70 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/si 15]]}
   [0x0f 0xb4 0x71 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xb4 0x71 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/di -16]]}
   [0x0f 0xb4 0x71 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/di 15]]}
   [0x0f 0xb4 0x72 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xb4 0x72 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/si -16]]}
   [0x0f 0xb4 0x72 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/si 15]]}
   [0x0f 0xb4 0x73 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xb4 0x73 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/di -16]]}
   [0x0f 0xb4 0x73 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/di 15]]}
   [0x0f 0xb4 0x74 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/si]]}
   [0x0f 0xb4 0x74 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/si -16]]}
   [0x0f 0xb4 0x74 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/si 15]]}
   [0x0f 0xb4 0x75 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/di]]}
   [0x0f 0xb4 0x75 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/di -16]]}
   [0x0f 0xb4 0x75 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/di 15]]}
   [0x0f 0xb4 0x76 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp]]}
   [0x0f 0xb4 0x76 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp -16]]}
   [0x0f 0xb4 0x76 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp 15]]}
   [0x0f 0xb4 0x77 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xb4 0x77 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx -16]]}
   [0x0f 0xb4 0x77 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx 15]]}
   [0x0f 0xb4 0x78 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xb4 0x78 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/si -16]]}
   [0x0f 0xb4 0x78 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/si 15]]}
   [0x0f 0xb4 0x79 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xb4 0x79 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/di -16]]}
   [0x0f 0xb4 0x79 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/di 15]]}
   [0x0f 0xb4 0x7a 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xb4 0x7a 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/si -16]]}
   [0x0f 0xb4 0x7a 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/si 15]]}
   [0x0f 0xb4 0x7b 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xb4 0x7b 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/di -16]]}
   [0x0f 0xb4 0x7b 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/di 15]]}
   [0x0f 0xb4 0x7c 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/si]]}
   [0x0f 0xb4 0x7c 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/si -16]]}
   [0x0f 0xb4 0x7c 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/si 15]]}
   [0x0f 0xb4 0x7d 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/di]]}
   [0x0f 0xb4 0x7d 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/di -16]]}
   [0x0f 0xb4 0x7d 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/di 15]]}
   [0x0f 0xb4 0x7e 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp]]}
   [0x0f 0xb4 0x7e 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp -16]]}
   [0x0f 0xb4 0x7e 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp 15]]}
   [0x0f 0xb4 0x7f 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xb4 0x7f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx -16]]}
   [0x0f 0xb4 0x7f 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx 15]]}
   [0x0f 0xb4 0x80 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xb4 0x80 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/si 4080]]}
   [0x0f 0xb4 0x80 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/si -4081]]}
   [0x0f 0xb4 0x81 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xb4 0x81 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/di 4080]]}
   [0x0f 0xb4 0x81 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx ::r/di -4081]]}
   [0x0f 0xb4 0x82 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xb4 0x82 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/si 4080]]}
   [0x0f 0xb4 0x82 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/si -4081]]}
   [0x0f 0xb4 0x83 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xb4 0x83 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/di 4080]]}
   [0x0f 0xb4 0x83 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp ::r/di -4081]]}
   [0x0f 0xb4 0x84 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xb4 0x84 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/si 4080]]}
   [0x0f 0xb4 0x84 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/si -4081]]}
   [0x0f 0xb4 0x85 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xb4 0x85 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/di 4080]]}
   [0x0f 0xb4 0x85 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/di -4081]]}
   [0x0f 0xb4 0x86 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp]]}
   [0x0f 0xb4 0x86 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp 4080]]}
   [0x0f 0xb4 0x86 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bp -4081]]}
   [0x0f 0xb4 0x87 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xb4 0x87 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx 4080]]}
   [0x0f 0xb4 0x87 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/ax [::r/bx -4081]]}
   [0x0f 0xb4 0x88 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xb4 0x88 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/si 4080]]}
   [0x0f 0xb4 0x88 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/si -4081]]}
   [0x0f 0xb4 0x89 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xb4 0x89 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/di 4080]]}
   [0x0f 0xb4 0x89 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx ::r/di -4081]]}
   [0x0f 0xb4 0x8a 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xb4 0x8a 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/si 4080]]}
   [0x0f 0xb4 0x8a 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/si -4081]]}
   [0x0f 0xb4 0x8b 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xb4 0x8b 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/di 4080]]}
   [0x0f 0xb4 0x8b 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp ::r/di -4081]]}
   [0x0f 0xb4 0x8c 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xb4 0x8c 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/si 4080]]}
   [0x0f 0xb4 0x8c 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/si -4081]]}
   [0x0f 0xb4 0x8d 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xb4 0x8d 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/di 4080]]}
   [0x0f 0xb4 0x8d 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/di -4081]]}
   [0x0f 0xb4 0x8e 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp]]}
   [0x0f 0xb4 0x8e 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp 4080]]}
   [0x0f 0xb4 0x8e 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bp -4081]]}
   [0x0f 0xb4 0x8f 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xb4 0x8f 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx 4080]]}
   [0x0f 0xb4 0x8f 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/cx [::r/bx -4081]]}
   [0x0f 0xb4 0x90 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xb4 0x90 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/si 4080]]}
   [0x0f 0xb4 0x90 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/si -4081]]}
   [0x0f 0xb4 0x91 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xb4 0x91 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/di 4080]]}
   [0x0f 0xb4 0x91 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx ::r/di -4081]]}
   [0x0f 0xb4 0x92 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xb4 0x92 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/si 4080]]}
   [0x0f 0xb4 0x92 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/si -4081]]}
   [0x0f 0xb4 0x93 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xb4 0x93 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/di 4080]]}
   [0x0f 0xb4 0x93 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp ::r/di -4081]]}
   [0x0f 0xb4 0x94 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xb4 0x94 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/si 4080]]}
   [0x0f 0xb4 0x94 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/si -4081]]}
   [0x0f 0xb4 0x95 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xb4 0x95 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/di 4080]]}
   [0x0f 0xb4 0x95 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/di -4081]]}
   [0x0f 0xb4 0x96 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp]]}
   [0x0f 0xb4 0x96 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp 4080]]}
   [0x0f 0xb4 0x96 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bp -4081]]}
   [0x0f 0xb4 0x97 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xb4 0x97 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx 4080]]}
   [0x0f 0xb4 0x97 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/dx [::r/bx -4081]]}
   [0x0f 0xb4 0x98 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xb4 0x98 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/si 4080]]}
   [0x0f 0xb4 0x98 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/si -4081]]}
   [0x0f 0xb4 0x99 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xb4 0x99 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/di 4080]]}
   [0x0f 0xb4 0x99 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx ::r/di -4081]]}
   [0x0f 0xb4 0x9a 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xb4 0x9a 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/si 4080]]}
   [0x0f 0xb4 0x9a 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/si -4081]]}
   [0x0f 0xb4 0x9b 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xb4 0x9b 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/di 4080]]}
   [0x0f 0xb4 0x9b 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp ::r/di -4081]]}
   [0x0f 0xb4 0x9c 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xb4 0x9c 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/si 4080]]}
   [0x0f 0xb4 0x9c 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/si -4081]]}
   [0x0f 0xb4 0x9d 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xb4 0x9d 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/di 4080]]}
   [0x0f 0xb4 0x9d 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/di -4081]]}
   [0x0f 0xb4 0x9e 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp]]}
   [0x0f 0xb4 0x9e 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp 4080]]}
   [0x0f 0xb4 0x9e 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bp -4081]]}
   [0x0f 0xb4 0x9f 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xb4 0x9f 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx 4080]]}
   [0x0f 0xb4 0x9f 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bx [::r/bx -4081]]}
   [0x0f 0xb4 0xa0 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xb4 0xa0 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/si 4080]]}
   [0x0f 0xb4 0xa0 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/si -4081]]}
   [0x0f 0xb4 0xa1 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xb4 0xa1 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/di 4080]]}
   [0x0f 0xb4 0xa1 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx ::r/di -4081]]}
   [0x0f 0xb4 0xa2 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xb4 0xa2 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/si 4080]]}
   [0x0f 0xb4 0xa2 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/si -4081]]}
   [0x0f 0xb4 0xa3 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xb4 0xa3 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/di 4080]]}
   [0x0f 0xb4 0xa3 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp ::r/di -4081]]}
   [0x0f 0xb4 0xa4 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xb4 0xa4 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/si 4080]]}
   [0x0f 0xb4 0xa4 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/si -4081]]}
   [0x0f 0xb4 0xa5 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xb4 0xa5 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/di 4080]]}
   [0x0f 0xb4 0xa5 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/di -4081]]}
   [0x0f 0xb4 0xa6 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp]]}
   [0x0f 0xb4 0xa6 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp 4080]]}
   [0x0f 0xb4 0xa6 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bp -4081]]}
   [0x0f 0xb4 0xa7 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xb4 0xa7 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx 4080]]}
   [0x0f 0xb4 0xa7 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/sp [::r/bx -4081]]}
   [0x0f 0xb4 0xa8 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xb4 0xa8 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/si 4080]]}
   [0x0f 0xb4 0xa8 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/si -4081]]}
   [0x0f 0xb4 0xa9 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xb4 0xa9 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/di 4080]]}
   [0x0f 0xb4 0xa9 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx ::r/di -4081]]}
   [0x0f 0xb4 0xaa 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xb4 0xaa 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/si 4080]]}
   [0x0f 0xb4 0xaa 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/si -4081]]}
   [0x0f 0xb4 0xab 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xb4 0xab 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/di 4080]]}
   [0x0f 0xb4 0xab 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp ::r/di -4081]]}
   [0x0f 0xb4 0xac 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xb4 0xac 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/si 4080]]}
   [0x0f 0xb4 0xac 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/si -4081]]}
   [0x0f 0xb4 0xad 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xb4 0xad 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/di 4080]]}
   [0x0f 0xb4 0xad 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/di -4081]]}
   [0x0f 0xb4 0xae 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp]]}
   [0x0f 0xb4 0xae 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp 4080]]}
   [0x0f 0xb4 0xae 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bp -4081]]}
   [0x0f 0xb4 0xaf 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xb4 0xaf 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx 4080]]}
   [0x0f 0xb4 0xaf 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/bp [::r/bx -4081]]}
   [0x0f 0xb4 0xb0 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xb4 0xb0 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/si 4080]]}
   [0x0f 0xb4 0xb0 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/si -4081]]}
   [0x0f 0xb4 0xb1 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xb4 0xb1 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/di 4080]]}
   [0x0f 0xb4 0xb1 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx ::r/di -4081]]}
   [0x0f 0xb4 0xb2 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xb4 0xb2 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/si 4080]]}
   [0x0f 0xb4 0xb2 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/si -4081]]}
   [0x0f 0xb4 0xb3 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xb4 0xb3 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/di 4080]]}
   [0x0f 0xb4 0xb3 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp ::r/di -4081]]}
   [0x0f 0xb4 0xb4 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/si]]}
   [0x0f 0xb4 0xb4 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/si 4080]]}
   [0x0f 0xb4 0xb4 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/si -4081]]}
   [0x0f 0xb4 0xb5 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/di]]}
   [0x0f 0xb4 0xb5 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/di 4080]]}
   [0x0f 0xb4 0xb5 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/di -4081]]}
   [0x0f 0xb4 0xb6 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp]]}
   [0x0f 0xb4 0xb6 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp 4080]]}
   [0x0f 0xb4 0xb6 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bp -4081]]}
   [0x0f 0xb4 0xb7 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xb4 0xb7 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx 4080]]}
   [0x0f 0xb4 0xb7 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/si [::r/bx -4081]]}
   [0x0f 0xb4 0xb8 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xb4 0xb8 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/si 4080]]}
   [0x0f 0xb4 0xb8 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/si -4081]]}
   [0x0f 0xb4 0xb9 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xb4 0xb9 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/di 4080]]}
   [0x0f 0xb4 0xb9 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx ::r/di -4081]]}
   [0x0f 0xb4 0xba 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xb4 0xba 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/si 4080]]}
   [0x0f 0xb4 0xba 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/si -4081]]}
   [0x0f 0xb4 0xbb 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xb4 0xbb 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/di 4080]]}
   [0x0f 0xb4 0xbb 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp ::r/di -4081]]}
   [0x0f 0xb4 0xbc 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/si]]}
   [0x0f 0xb4 0xbc 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/si 4080]]}
   [0x0f 0xb4 0xbc 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/si -4081]]}
   [0x0f 0xb4 0xbd 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/di]]}
   [0x0f 0xb4 0xbd 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/di 4080]]}
   [0x0f 0xb4 0xbd 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/di -4081]]}
   [0x0f 0xb4 0xbe 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp]]}
   [0x0f 0xb4 0xbe 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp 4080]]}
   [0x0f 0xb4 0xbe 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bp -4081]]}
   [0x0f 0xb4 0xbf 0x00 0x00] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xb4 0xbf 0xf0 0x0f] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx 4080]]}
   [0x0f 0xb4 0xbf 0x0f 0xf0] {::i/tag ::i/lfs, ::i/args [::r/di [::r/bx -4081]]}
   [0x0f 0xb4 0xc0] nil
   [0x0f 0xb4 0xc1] nil
   [0x0f 0xb4 0xc2] nil
   [0x0f 0xb4 0xc3] nil
   [0x0f 0xb4 0xc4] nil
   [0x0f 0xb4 0xc5] nil
   [0x0f 0xb4 0xc6] nil
   [0x0f 0xb4 0xc7] nil
   [0x0f 0xb4 0xc8] nil
   [0x0f 0xb4 0xc9] nil
   [0x0f 0xb4 0xca] nil
   [0x0f 0xb4 0xcb] nil
   [0x0f 0xb4 0xcc] nil
   [0x0f 0xb4 0xcd] nil
   [0x0f 0xb4 0xce] nil
   [0x0f 0xb4 0xcf] nil
   [0x0f 0xb4 0xd0] nil
   [0x0f 0xb4 0xd1] nil
   [0x0f 0xb4 0xd2] nil
   [0x0f 0xb4 0xd3] nil
   [0x0f 0xb4 0xd4] nil
   [0x0f 0xb4 0xd5] nil
   [0x0f 0xb4 0xd6] nil
   [0x0f 0xb4 0xd7] nil
   [0x0f 0xb4 0xd8] nil
   [0x0f 0xb4 0xd9] nil
   [0x0f 0xb4 0xda] nil
   [0x0f 0xb4 0xdb] nil
   [0x0f 0xb4 0xdc] nil
   [0x0f 0xb4 0xdd] nil
   [0x0f 0xb4 0xde] nil
   [0x0f 0xb4 0xdf] nil
   [0x0f 0xb4 0xe0] nil
   [0x0f 0xb4 0xe1] nil
   [0x0f 0xb4 0xe2] nil
   [0x0f 0xb4 0xe3] nil
   [0x0f 0xb4 0xe4] nil
   [0x0f 0xb4 0xe5] nil
   [0x0f 0xb4 0xe6] nil
   [0x0f 0xb4 0xe7] nil
   [0x0f 0xb4 0xe8] nil
   [0x0f 0xb4 0xe9] nil
   [0x0f 0xb4 0xea] nil
   [0x0f 0xb4 0xeb] nil
   [0x0f 0xb4 0xec] nil
   [0x0f 0xb4 0xed] nil
   [0x0f 0xb4 0xee] nil
   [0x0f 0xb4 0xef] nil
   [0x0f 0xb4 0xf0] nil
   [0x0f 0xb4 0xf1] nil
   [0x0f 0xb4 0xf2] nil
   [0x0f 0xb4 0xf3] nil
   [0x0f 0xb4 0xf4] nil
   [0x0f 0xb4 0xf5] nil
   [0x0f 0xb4 0xf6] nil
   [0x0f 0xb4 0xf7] nil
   [0x0f 0xb4 0xf8] nil
   [0x0f 0xb4 0xf9] nil
   [0x0f 0xb4 0xfa] nil
   [0x0f 0xb4 0xfb] nil
   [0x0f 0xb4 0xfc] nil
   [0x0f 0xb4 0xfd] nil
   [0x0f 0xb4 0xfe] nil
   [0x0f 0xb4 0xff] nil})


(def decode-lgs-examples
  {[0x0f 0xb5 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xb5 0x01] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xb5 0x02] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xb5 0x03] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xb5 0x04] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xb5 0x05] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xb5 0x06 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [0x0ff0]]}
   [0x0f 0xb5 0x06 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [0xf00f]]}
   [0x0f 0xb5 0x07] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xb5 0x08] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xb5 0x09] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xb5 0x0a] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xb5 0x0b] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xb5 0x0c] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xb5 0x0d] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xb5 0x0e 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [0x0ff0]]}
   [0x0f 0xb5 0x0e 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [0xf00f]]}
   [0x0f 0xb5 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xb5 0x10] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xb5 0x11] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xb5 0x12] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xb5 0x13] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xb5 0x14] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xb5 0x15] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xb5 0x16 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [0x0ff0]]}
   [0x0f 0xb5 0x16 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [0xf00f]]}
   [0x0f 0xb5 0x17] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xb5 0x18] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xb5 0x19] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xb5 0x1a] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xb5 0x1b] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xb5 0x1c] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xb5 0x1d] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xb5 0x1e 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [0x0ff0]]}
   [0x0f 0xb5 0x1e 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [0xf00f]]}
   [0x0f 0xb5 0x1f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xb5 0x20] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xb5 0x21] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xb5 0x22] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xb5 0x23] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xb5 0x24] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xb5 0x25] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xb5 0x26 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [0x0ff0]]}
   [0x0f 0xb5 0x26 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [0xf00f]]}
   [0x0f 0xb5 0x27] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xb5 0x28] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xb5 0x29] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xb5 0x2a] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xb5 0x2b] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xb5 0x2c] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xb5 0x2d] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xb5 0x2e 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [0x0ff0]]}
   [0x0f 0xb5 0x2e 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [0xf00f]]}
   [0x0f 0xb5 0x2f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xb5 0x30] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xb5 0x31] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xb5 0x32] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xb5 0x33] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xb5 0x34] {::i/tag ::i/lgs, ::i/args [::r/si [::r/si]]}
   [0x0f 0xb5 0x35] {::i/tag ::i/lgs, ::i/args [::r/si [::r/di]]}
   [0x0f 0xb5 0x36 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [0x0ff0]]}
   [0x0f 0xb5 0x36 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [0xf00f]]}
   [0x0f 0xb5 0x37] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xb5 0x38] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xb5 0x39] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xb5 0x3a] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xb5 0x3b] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xb5 0x3c] {::i/tag ::i/lgs, ::i/args [::r/di [::r/si]]}
   [0x0f 0xb5 0x3d] {::i/tag ::i/lgs, ::i/args [::r/di [::r/di]]}
   [0x0f 0xb5 0x3e 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [0x0ff0]]}
   [0x0f 0xb5 0x3e 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [0xf00f]]}
   [0x0f 0xb5 0x3f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xb5 0x40 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xb5 0x40 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/si -16]]}
   [0x0f 0xb5 0x40 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/si 15]]}
   [0x0f 0xb5 0x41 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xb5 0x41 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/di -16]]}
   [0x0f 0xb5 0x41 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/di 15]]}
   [0x0f 0xb5 0x42 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xb5 0x42 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/si -16]]}
   [0x0f 0xb5 0x42 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/si 15]]}
   [0x0f 0xb5 0x43 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xb5 0x43 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/di -16]]}
   [0x0f 0xb5 0x43 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/di 15]]}
   [0x0f 0xb5 0x44 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xb5 0x44 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/si -16]]}
   [0x0f 0xb5 0x44 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/si 15]]}
   [0x0f 0xb5 0x45 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xb5 0x45 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/di -16]]}
   [0x0f 0xb5 0x45 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/di 15]]}
   [0x0f 0xb5 0x46 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp]]}
   [0x0f 0xb5 0x46 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp -16]]}
   [0x0f 0xb5 0x46 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp 15]]}
   [0x0f 0xb5 0x47 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xb5 0x47 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx -16]]}
   [0x0f 0xb5 0x47 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx 15]]}
   [0x0f 0xb5 0x48 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xb5 0x48 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/si -16]]}
   [0x0f 0xb5 0x48 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/si 15]]}
   [0x0f 0xb5 0x49 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xb5 0x49 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/di -16]]}
   [0x0f 0xb5 0x49 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/di 15]]}
   [0x0f 0xb5 0x4a 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xb5 0x4a 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/si -16]]}
   [0x0f 0xb5 0x4a 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/si 15]]}
   [0x0f 0xb5 0x4b 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xb5 0x4b 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/di -16]]}
   [0x0f 0xb5 0x4b 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/di 15]]}
   [0x0f 0xb5 0x4c 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xb5 0x4c 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/si -16]]}
   [0x0f 0xb5 0x4c 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/si 15]]}
   [0x0f 0xb5 0x4d 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xb5 0x4d 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/di -16]]}
   [0x0f 0xb5 0x4d 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/di 15]]}
   [0x0f 0xb5 0x4e 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp]]}
   [0x0f 0xb5 0x4e 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp -16]]}
   [0x0f 0xb5 0x4e 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp 15]]}
   [0x0f 0xb5 0x4f 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xb5 0x4f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx -16]]}
   [0x0f 0xb5 0x4f 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx 15]]}
   [0x0f 0xb5 0x50 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xb5 0x50 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/si -16]]}
   [0x0f 0xb5 0x50 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/si 15]]}
   [0x0f 0xb5 0x51 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xb5 0x51 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/di -16]]}
   [0x0f 0xb5 0x51 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/di 15]]}
   [0x0f 0xb5 0x52 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xb5 0x52 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/si -16]]}
   [0x0f 0xb5 0x52 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/si 15]]}
   [0x0f 0xb5 0x53 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xb5 0x53 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/di -16]]}
   [0x0f 0xb5 0x53 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/di 15]]}
   [0x0f 0xb5 0x54 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xb5 0x54 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/si -16]]}
   [0x0f 0xb5 0x54 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/si 15]]}
   [0x0f 0xb5 0x55 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xb5 0x55 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/di -16]]}
   [0x0f 0xb5 0x55 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/di 15]]}
   [0x0f 0xb5 0x56 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp]]}
   [0x0f 0xb5 0x56 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp -16]]}
   [0x0f 0xb5 0x56 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp 15]]}
   [0x0f 0xb5 0x57 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xb5 0x57 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx -16]]}
   [0x0f 0xb5 0x57 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx 15]]}
   [0x0f 0xb5 0x58 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xb5 0x58 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/si -16]]}
   [0x0f 0xb5 0x58 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/si 15]]}
   [0x0f 0xb5 0x59 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xb5 0x59 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/di -16]]}
   [0x0f 0xb5 0x59 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/di 15]]}
   [0x0f 0xb5 0x5a 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xb5 0x5a 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/si -16]]}
   [0x0f 0xb5 0x5a 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/si 15]]}
   [0x0f 0xb5 0x5b 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xb5 0x5b 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/di -16]]}
   [0x0f 0xb5 0x5b 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/di 15]]}
   [0x0f 0xb5 0x5c 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xb5 0x5c 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/si -16]]}
   [0x0f 0xb5 0x5c 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/si 15]]}
   [0x0f 0xb5 0x5d 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xb5 0x5d 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/di -16]]}
   [0x0f 0xb5 0x5d 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/di 15]]}
   [0x0f 0xb5 0x5e 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp]]}
   [0x0f 0xb5 0x5e 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp -16]]}
   [0x0f 0xb5 0x5e 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp 15]]}
   [0x0f 0xb5 0x5f 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xb5 0x5f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx -16]]}
   [0x0f 0xb5 0x5f 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx 15]]}
   [0x0f 0xb5 0x60 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xb5 0x60 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/si -16]]}
   [0x0f 0xb5 0x60 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/si 15]]}
   [0x0f 0xb5 0x61 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xb5 0x61 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/di -16]]}
   [0x0f 0xb5 0x61 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/di 15]]}
   [0x0f 0xb5 0x62 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xb5 0x62 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/si -16]]}
   [0x0f 0xb5 0x62 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/si 15]]}
   [0x0f 0xb5 0x63 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xb5 0x63 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/di -16]]}
   [0x0f 0xb5 0x63 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/di 15]]}
   [0x0f 0xb5 0x64 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xb5 0x64 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/si -16]]}
   [0x0f 0xb5 0x64 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/si 15]]}
   [0x0f 0xb5 0x65 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xb5 0x65 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/di -16]]}
   [0x0f 0xb5 0x65 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/di 15]]}
   [0x0f 0xb5 0x66 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp]]}
   [0x0f 0xb5 0x66 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp -16]]}
   [0x0f 0xb5 0x66 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp 15]]}
   [0x0f 0xb5 0x67 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xb5 0x67 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx -16]]}
   [0x0f 0xb5 0x67 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx 15]]}
   [0x0f 0xb5 0x68 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xb5 0x68 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/si -16]]}
   [0x0f 0xb5 0x68 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/si 15]]}
   [0x0f 0xb5 0x69 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xb5 0x69 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/di -16]]}
   [0x0f 0xb5 0x69 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/di 15]]}
   [0x0f 0xb5 0x6a 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xb5 0x6a 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/si -16]]}
   [0x0f 0xb5 0x6a 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/si 15]]}
   [0x0f 0xb5 0x6b 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xb5 0x6b 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/di -16]]}
   [0x0f 0xb5 0x6b 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/di 15]]}
   [0x0f 0xb5 0x6c 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xb5 0x6c 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/si -16]]}
   [0x0f 0xb5 0x6c 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/si 15]]}
   [0x0f 0xb5 0x6d 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xb5 0x6d 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/di -16]]}
   [0x0f 0xb5 0x6d 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/di 15]]}
   [0x0f 0xb5 0x6e 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp]]}
   [0x0f 0xb5 0x6e 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp -16]]}
   [0x0f 0xb5 0x6e 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp 15]]}
   [0x0f 0xb5 0x6f 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xb5 0x6f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx -16]]}
   [0x0f 0xb5 0x6f 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx 15]]}
   [0x0f 0xb5 0x70 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xb5 0x70 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/si -16]]}
   [0x0f 0xb5 0x70 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/si 15]]}
   [0x0f 0xb5 0x71 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xb5 0x71 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/di -16]]}
   [0x0f 0xb5 0x71 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/di 15]]}
   [0x0f 0xb5 0x72 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xb5 0x72 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/si -16]]}
   [0x0f 0xb5 0x72 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/si 15]]}
   [0x0f 0xb5 0x73 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xb5 0x73 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/di -16]]}
   [0x0f 0xb5 0x73 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/di 15]]}
   [0x0f 0xb5 0x74 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/si]]}
   [0x0f 0xb5 0x74 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/si -16]]}
   [0x0f 0xb5 0x74 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/si 15]]}
   [0x0f 0xb5 0x75 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/di]]}
   [0x0f 0xb5 0x75 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/di -16]]}
   [0x0f 0xb5 0x75 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/di 15]]}
   [0x0f 0xb5 0x76 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp]]}
   [0x0f 0xb5 0x76 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp -16]]}
   [0x0f 0xb5 0x76 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp 15]]}
   [0x0f 0xb5 0x77 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xb5 0x77 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx -16]]}
   [0x0f 0xb5 0x77 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx 15]]}
   [0x0f 0xb5 0x78 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xb5 0x78 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/si -16]]}
   [0x0f 0xb5 0x78 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/si 15]]}
   [0x0f 0xb5 0x79 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xb5 0x79 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/di -16]]}
   [0x0f 0xb5 0x79 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/di 15]]}
   [0x0f 0xb5 0x7a 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xb5 0x7a 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/si -16]]}
   [0x0f 0xb5 0x7a 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/si 15]]}
   [0x0f 0xb5 0x7b 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xb5 0x7b 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/di -16]]}
   [0x0f 0xb5 0x7b 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/di 15]]}
   [0x0f 0xb5 0x7c 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/si]]}
   [0x0f 0xb5 0x7c 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/si -16]]}
   [0x0f 0xb5 0x7c 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/si 15]]}
   [0x0f 0xb5 0x7d 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/di]]}
   [0x0f 0xb5 0x7d 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/di -16]]}
   [0x0f 0xb5 0x7d 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/di 15]]}
   [0x0f 0xb5 0x7e 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp]]}
   [0x0f 0xb5 0x7e 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp -16]]}
   [0x0f 0xb5 0x7e 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp 15]]}
   [0x0f 0xb5 0x7f 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xb5 0x7f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx -16]]}
   [0x0f 0xb5 0x7f 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx 15]]}
   [0x0f 0xb5 0x80 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xb5 0x80 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/si 4080]]}
   [0x0f 0xb5 0x80 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/si -4081]]}
   [0x0f 0xb5 0x81 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xb5 0x81 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/di 4080]]}
   [0x0f 0xb5 0x81 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx ::r/di -4081]]}
   [0x0f 0xb5 0x82 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xb5 0x82 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/si 4080]]}
   [0x0f 0xb5 0x82 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/si -4081]]}
   [0x0f 0xb5 0x83 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xb5 0x83 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/di 4080]]}
   [0x0f 0xb5 0x83 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp ::r/di -4081]]}
   [0x0f 0xb5 0x84 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xb5 0x84 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/si 4080]]}
   [0x0f 0xb5 0x84 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/si -4081]]}
   [0x0f 0xb5 0x85 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xb5 0x85 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/di 4080]]}
   [0x0f 0xb5 0x85 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/di -4081]]}
   [0x0f 0xb5 0x86 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp]]}
   [0x0f 0xb5 0x86 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp 4080]]}
   [0x0f 0xb5 0x86 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bp -4081]]}
   [0x0f 0xb5 0x87 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xb5 0x87 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx 4080]]}
   [0x0f 0xb5 0x87 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/ax [::r/bx -4081]]}
   [0x0f 0xb5 0x88 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xb5 0x88 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/si 4080]]}
   [0x0f 0xb5 0x88 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/si -4081]]}
   [0x0f 0xb5 0x89 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xb5 0x89 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/di 4080]]}
   [0x0f 0xb5 0x89 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx ::r/di -4081]]}
   [0x0f 0xb5 0x8a 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xb5 0x8a 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/si 4080]]}
   [0x0f 0xb5 0x8a 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/si -4081]]}
   [0x0f 0xb5 0x8b 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xb5 0x8b 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/di 4080]]}
   [0x0f 0xb5 0x8b 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp ::r/di -4081]]}
   [0x0f 0xb5 0x8c 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xb5 0x8c 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/si 4080]]}
   [0x0f 0xb5 0x8c 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/si -4081]]}
   [0x0f 0xb5 0x8d 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xb5 0x8d 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/di 4080]]}
   [0x0f 0xb5 0x8d 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/di -4081]]}
   [0x0f 0xb5 0x8e 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp]]}
   [0x0f 0xb5 0x8e 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp 4080]]}
   [0x0f 0xb5 0x8e 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bp -4081]]}
   [0x0f 0xb5 0x8f 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xb5 0x8f 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx 4080]]}
   [0x0f 0xb5 0x8f 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/cx [::r/bx -4081]]}
   [0x0f 0xb5 0x90 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xb5 0x90 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/si 4080]]}
   [0x0f 0xb5 0x90 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/si -4081]]}
   [0x0f 0xb5 0x91 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xb5 0x91 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/di 4080]]}
   [0x0f 0xb5 0x91 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx ::r/di -4081]]}
   [0x0f 0xb5 0x92 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xb5 0x92 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/si 4080]]}
   [0x0f 0xb5 0x92 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/si -4081]]}
   [0x0f 0xb5 0x93 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xb5 0x93 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/di 4080]]}
   [0x0f 0xb5 0x93 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp ::r/di -4081]]}
   [0x0f 0xb5 0x94 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xb5 0x94 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/si 4080]]}
   [0x0f 0xb5 0x94 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/si -4081]]}
   [0x0f 0xb5 0x95 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xb5 0x95 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/di 4080]]}
   [0x0f 0xb5 0x95 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/di -4081]]}
   [0x0f 0xb5 0x96 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp]]}
   [0x0f 0xb5 0x96 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp 4080]]}
   [0x0f 0xb5 0x96 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bp -4081]]}
   [0x0f 0xb5 0x97 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xb5 0x97 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx 4080]]}
   [0x0f 0xb5 0x97 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/dx [::r/bx -4081]]}
   [0x0f 0xb5 0x98 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xb5 0x98 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/si 4080]]}
   [0x0f 0xb5 0x98 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/si -4081]]}
   [0x0f 0xb5 0x99 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xb5 0x99 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/di 4080]]}
   [0x0f 0xb5 0x99 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx ::r/di -4081]]}
   [0x0f 0xb5 0x9a 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xb5 0x9a 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/si 4080]]}
   [0x0f 0xb5 0x9a 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/si -4081]]}
   [0x0f 0xb5 0x9b 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xb5 0x9b 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/di 4080]]}
   [0x0f 0xb5 0x9b 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp ::r/di -4081]]}
   [0x0f 0xb5 0x9c 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xb5 0x9c 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/si 4080]]}
   [0x0f 0xb5 0x9c 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/si -4081]]}
   [0x0f 0xb5 0x9d 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xb5 0x9d 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/di 4080]]}
   [0x0f 0xb5 0x9d 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/di -4081]]}
   [0x0f 0xb5 0x9e 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp]]}
   [0x0f 0xb5 0x9e 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp 4080]]}
   [0x0f 0xb5 0x9e 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bp -4081]]}
   [0x0f 0xb5 0x9f 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xb5 0x9f 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx 4080]]}
   [0x0f 0xb5 0x9f 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bx [::r/bx -4081]]}
   [0x0f 0xb5 0xa0 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xb5 0xa0 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/si 4080]]}
   [0x0f 0xb5 0xa0 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/si -4081]]}
   [0x0f 0xb5 0xa1 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xb5 0xa1 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/di 4080]]}
   [0x0f 0xb5 0xa1 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx ::r/di -4081]]}
   [0x0f 0xb5 0xa2 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xb5 0xa2 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/si 4080]]}
   [0x0f 0xb5 0xa2 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/si -4081]]}
   [0x0f 0xb5 0xa3 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xb5 0xa3 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/di 4080]]}
   [0x0f 0xb5 0xa3 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp ::r/di -4081]]}
   [0x0f 0xb5 0xa4 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xb5 0xa4 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/si 4080]]}
   [0x0f 0xb5 0xa4 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/si -4081]]}
   [0x0f 0xb5 0xa5 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xb5 0xa5 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/di 4080]]}
   [0x0f 0xb5 0xa5 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/di -4081]]}
   [0x0f 0xb5 0xa6 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp]]}
   [0x0f 0xb5 0xa6 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp 4080]]}
   [0x0f 0xb5 0xa6 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bp -4081]]}
   [0x0f 0xb5 0xa7 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xb5 0xa7 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx 4080]]}
   [0x0f 0xb5 0xa7 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/sp [::r/bx -4081]]}
   [0x0f 0xb5 0xa8 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xb5 0xa8 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/si 4080]]}
   [0x0f 0xb5 0xa8 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/si -4081]]}
   [0x0f 0xb5 0xa9 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xb5 0xa9 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/di 4080]]}
   [0x0f 0xb5 0xa9 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx ::r/di -4081]]}
   [0x0f 0xb5 0xaa 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xb5 0xaa 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/si 4080]]}
   [0x0f 0xb5 0xaa 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/si -4081]]}
   [0x0f 0xb5 0xab 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xb5 0xab 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/di 4080]]}
   [0x0f 0xb5 0xab 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp ::r/di -4081]]}
   [0x0f 0xb5 0xac 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xb5 0xac 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/si 4080]]}
   [0x0f 0xb5 0xac 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/si -4081]]}
   [0x0f 0xb5 0xad 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xb5 0xad 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/di 4080]]}
   [0x0f 0xb5 0xad 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/di -4081]]}
   [0x0f 0xb5 0xae 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp]]}
   [0x0f 0xb5 0xae 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp 4080]]}
   [0x0f 0xb5 0xae 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bp -4081]]}
   [0x0f 0xb5 0xaf 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xb5 0xaf 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx 4080]]}
   [0x0f 0xb5 0xaf 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/bp [::r/bx -4081]]}
   [0x0f 0xb5 0xb0 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xb5 0xb0 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/si 4080]]}
   [0x0f 0xb5 0xb0 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/si -4081]]}
   [0x0f 0xb5 0xb1 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xb5 0xb1 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/di 4080]]}
   [0x0f 0xb5 0xb1 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx ::r/di -4081]]}
   [0x0f 0xb5 0xb2 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xb5 0xb2 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/si 4080]]}
   [0x0f 0xb5 0xb2 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/si -4081]]}
   [0x0f 0xb5 0xb3 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xb5 0xb3 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/di 4080]]}
   [0x0f 0xb5 0xb3 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp ::r/di -4081]]}
   [0x0f 0xb5 0xb4 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/si]]}
   [0x0f 0xb5 0xb4 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/si 4080]]}
   [0x0f 0xb5 0xb4 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/si -4081]]}
   [0x0f 0xb5 0xb5 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/di]]}
   [0x0f 0xb5 0xb5 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/di 4080]]}
   [0x0f 0xb5 0xb5 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/di -4081]]}
   [0x0f 0xb5 0xb6 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp]]}
   [0x0f 0xb5 0xb6 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp 4080]]}
   [0x0f 0xb5 0xb6 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bp -4081]]}
   [0x0f 0xb5 0xb7 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xb5 0xb7 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx 4080]]}
   [0x0f 0xb5 0xb7 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/si [::r/bx -4081]]}
   [0x0f 0xb5 0xb8 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xb5 0xb8 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/si 4080]]}
   [0x0f 0xb5 0xb8 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/si -4081]]}
   [0x0f 0xb5 0xb9 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xb5 0xb9 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/di 4080]]}
   [0x0f 0xb5 0xb9 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx ::r/di -4081]]}
   [0x0f 0xb5 0xba 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xb5 0xba 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/si 4080]]}
   [0x0f 0xb5 0xba 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/si -4081]]}
   [0x0f 0xb5 0xbb 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xb5 0xbb 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/di 4080]]}
   [0x0f 0xb5 0xbb 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp ::r/di -4081]]}
   [0x0f 0xb5 0xbc 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/si]]}
   [0x0f 0xb5 0xbc 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/si 4080]]}
   [0x0f 0xb5 0xbc 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/si -4081]]}
   [0x0f 0xb5 0xbd 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/di]]}
   [0x0f 0xb5 0xbd 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/di 4080]]}
   [0x0f 0xb5 0xbd 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/di -4081]]}
   [0x0f 0xb5 0xbe 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp]]}
   [0x0f 0xb5 0xbe 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp 4080]]}
   [0x0f 0xb5 0xbe 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bp -4081]]}
   [0x0f 0xb5 0xbf 0x00 0x00] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xb5 0xbf 0xf0 0x0f] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx 4080]]}
   [0x0f 0xb5 0xbf 0x0f 0xf0] {::i/tag ::i/lgs, ::i/args [::r/di [::r/bx -4081]]}
   [0x0f 0xb5 0xc0] nil
   [0x0f 0xb5 0xc1] nil
   [0x0f 0xb5 0xc2] nil
   [0x0f 0xb5 0xc3] nil
   [0x0f 0xb5 0xc4] nil
   [0x0f 0xb5 0xc5] nil
   [0x0f 0xb5 0xc6] nil
   [0x0f 0xb5 0xc7] nil
   [0x0f 0xb5 0xc8] nil
   [0x0f 0xb5 0xc9] nil
   [0x0f 0xb5 0xca] nil
   [0x0f 0xb5 0xcb] nil
   [0x0f 0xb5 0xcc] nil
   [0x0f 0xb5 0xcd] nil
   [0x0f 0xb5 0xce] nil
   [0x0f 0xb5 0xcf] nil
   [0x0f 0xb5 0xd0] nil
   [0x0f 0xb5 0xd1] nil
   [0x0f 0xb5 0xd2] nil
   [0x0f 0xb5 0xd3] nil
   [0x0f 0xb5 0xd4] nil
   [0x0f 0xb5 0xd5] nil
   [0x0f 0xb5 0xd6] nil
   [0x0f 0xb5 0xd7] nil
   [0x0f 0xb5 0xd8] nil
   [0x0f 0xb5 0xd9] nil
   [0x0f 0xb5 0xda] nil
   [0x0f 0xb5 0xdb] nil
   [0x0f 0xb5 0xdc] nil
   [0x0f 0xb5 0xdd] nil
   [0x0f 0xb5 0xde] nil
   [0x0f 0xb5 0xdf] nil
   [0x0f 0xb5 0xe0] nil
   [0x0f 0xb5 0xe1] nil
   [0x0f 0xb5 0xe2] nil
   [0x0f 0xb5 0xe3] nil
   [0x0f 0xb5 0xe4] nil
   [0x0f 0xb5 0xe5] nil
   [0x0f 0xb5 0xe6] nil
   [0x0f 0xb5 0xe7] nil
   [0x0f 0xb5 0xe8] nil
   [0x0f 0xb5 0xe9] nil
   [0x0f 0xb5 0xea] nil
   [0x0f 0xb5 0xeb] nil
   [0x0f 0xb5 0xec] nil
   [0x0f 0xb5 0xed] nil
   [0x0f 0xb5 0xee] nil
   [0x0f 0xb5 0xef] nil
   [0x0f 0xb5 0xf0] nil
   [0x0f 0xb5 0xf1] nil
   [0x0f 0xb5 0xf2] nil
   [0x0f 0xb5 0xf3] nil
   [0x0f 0xb5 0xf4] nil
   [0x0f 0xb5 0xf5] nil
   [0x0f 0xb5 0xf6] nil
   [0x0f 0xb5 0xf7] nil
   [0x0f 0xb5 0xf8] nil
   [0x0f 0xb5 0xf9] nil
   [0x0f 0xb5 0xfa] nil
   [0x0f 0xb5 0xfb] nil
   [0x0f 0xb5 0xfc] nil
   [0x0f 0xb5 0xfd] nil
   [0x0f 0xb5 0xfe] nil
   [0x0f 0xb5 0xff] nil})


(def decode-examples-movzx
  {[0x0f 0xb6 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x01] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x02] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x03] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x04] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x05] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x06 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xb6 0x06 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xb6 0x07] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x08] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x09] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x0a] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x0b] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x0c] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x0d] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x0e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xb6 0x0e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xb6 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x10] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x11] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x12] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x13] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x14] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x15] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x16 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xb6 0x16 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xb6 0x17] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x18] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x19] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x1a] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x1b] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x1c] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x1d] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x1e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xb6 0x1e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xb6 0x1f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x20] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x21] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x22] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x23] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x24] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x25] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x26 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xb6 0x26 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xb6 0x27] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x28] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x29] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x2a] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x2b] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x2c] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x2d] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x2e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xb6 0x2e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xb6 0x2f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x30] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x31] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x32] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x33] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x34] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x35] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x36 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xb6 0x36 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xb6 0x37] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x38] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x39] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x3a] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x3b] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x3c] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x3d] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x3e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xb6 0x3e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xb6 0x3f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x40 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x40 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x40 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x41 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x41 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x41 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x42 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x42 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x42 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x43 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x43 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x43 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x44 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x44 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x44 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x45 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x45 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x45 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x46 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x46 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x46 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x47 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x47 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x47 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x48 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x48 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x48 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x49 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x49 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x49 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4a 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4a 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4a 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4b 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4b 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4b 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4c 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4c 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4c 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4d 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4d 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4d 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4e 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4e 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4e 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4f 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x4f 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x50 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x50 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x50 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x51 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x51 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x51 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x52 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x52 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x52 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x53 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x53 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x53 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x54 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x54 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x54 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x55 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x55 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x55 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x56 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x56 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x56 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x57 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x57 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x57 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x58 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x58 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x58 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x59 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x59 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x59 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5a 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5a 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5a 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5b 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5b 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5b 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5c 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5c 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5c 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5d 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5d 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5d 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5e 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5e 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5e 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5f 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x5f 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x60 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x60 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x60 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x61 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x61 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x61 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x62 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x62 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x62 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x63 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x63 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x63 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x64 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x64 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x64 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x65 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x65 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x65 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x66 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x66 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x66 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x67 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x67 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x67 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x68 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x68 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x68 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x69 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x69 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x69 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6a 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6a 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6a 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6b 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6b 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6b 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6c 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6c 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6c 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6d 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6d 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6d 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6e 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6e 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6e 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6f 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x6f 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x70 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x70 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x70 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x71 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x71 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x71 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x72 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x72 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x72 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x73 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x73 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x73 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x74 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x74 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x74 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x75 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x75 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x75 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x76 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x76 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x76 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x77 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x77 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x77 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x78 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x78 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x78 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x79 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x79 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x79 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7a 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7a 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7a 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7b 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7b 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7b 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7c 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7c 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7c 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7d 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7d 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7d 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7e 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7e 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7e 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7f 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xb6 0x7f 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xb6 0x80 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x80 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x80 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x81 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x81 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x81 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x82 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x82 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x82 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x83 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x83 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x83 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x84 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x84 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x84 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x85 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x85 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x85 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x86 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x86 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x86 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x87 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x87 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x87 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x88 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x88 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x88 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x89 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x89 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x89 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8a 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8a 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8a 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8b 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8b 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8b 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8c 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8c 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8c 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8d 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8d 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8d 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8e 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8f 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8f 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x8f 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x90 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x90 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x90 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x91 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x91 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x91 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x92 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x92 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x92 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x93 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x93 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x93 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x94 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x94 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x94 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x95 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x95 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x95 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x96 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x96 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x96 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x97 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x97 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x97 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x98 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x98 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x98 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x99 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x99 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x99 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9a 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9a 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9a 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9b 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9b 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9b 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9c 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9c 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9c 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9d 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9d 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9d 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9e 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9f 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9f 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0x9f 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa0 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa0 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa0 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa1 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa1 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa1 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa2 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa2 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa2 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa3 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa3 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa3 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa4 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa4 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa4 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa5 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa5 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa5 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa6 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa6 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa6 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa7 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa7 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa7 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa8 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa8 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa8 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa9 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa9 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xa9 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xaa 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xaa 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xaa 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xab 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xab 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xab 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xac 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xac 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xac 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xad 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xad 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xad 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xae 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0xae 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xae 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xaf 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0xaf 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xaf 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb0 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb0 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb0 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb1 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb1 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb1 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb2 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb2 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb2 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb3 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb3 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb3 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb4 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb4 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb4 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb5 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb5 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb5 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb6 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb6 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb6 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb7 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb7 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb7 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb8 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb8 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb8 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb9 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb9 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xb9 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xba 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xba 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xba 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbb 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbb 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbb 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbc 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbc 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbc 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbd 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbd 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbd 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbe 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbe 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbe 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbf 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbf 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xb6 0xbf 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xb6 0xc0] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/al]}
   [0x0f 0xb6 0xc1] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/cl]}
   [0x0f 0xb6 0xc2] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/dl]}
   [0x0f 0xb6 0xc3] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/bl]}
   [0x0f 0xb6 0xc4] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/ah]}
   [0x0f 0xb6 0xc5] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/ch]}
   [0x0f 0xb6 0xc6] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/dh]}
   [0x0f 0xb6 0xc7] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/bh]}
   [0x0f 0xb6 0xc8] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/al]}
   [0x0f 0xb6 0xc9] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/cl]}
   [0x0f 0xb6 0xca] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/dl]}
   [0x0f 0xb6 0xcb] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/bl]}
   [0x0f 0xb6 0xcc] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/ah]}
   [0x0f 0xb6 0xcd] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/ch]}
   [0x0f 0xb6 0xce] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/dh]}
   [0x0f 0xb6 0xcf] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/bh]}
   [0x0f 0xb6 0xd0] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/al]}
   [0x0f 0xb6 0xd1] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/cl]}
   [0x0f 0xb6 0xd2] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/dl]}
   [0x0f 0xb6 0xd3] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/bl]}
   [0x0f 0xb6 0xd4] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/ah]}
   [0x0f 0xb6 0xd5] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/ch]}
   [0x0f 0xb6 0xd6] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/dh]}
   [0x0f 0xb6 0xd7] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/bh]}
   [0x0f 0xb6 0xd8] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/al]}
   [0x0f 0xb6 0xd9] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/cl]}
   [0x0f 0xb6 0xda] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/dl]}
   [0x0f 0xb6 0xdb] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/bl]}
   [0x0f 0xb6 0xdc] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/ah]}
   [0x0f 0xb6 0xdd] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/ch]}
   [0x0f 0xb6 0xde] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/dh]}
   [0x0f 0xb6 0xdf] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/bh]}
   [0x0f 0xb6 0xe0] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/al]}
   [0x0f 0xb6 0xe1] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/cl]}
   [0x0f 0xb6 0xe2] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/dl]}
   [0x0f 0xb6 0xe3] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/bl]}
   [0x0f 0xb6 0xe4] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/ah]}
   [0x0f 0xb6 0xe5] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/ch]}
   [0x0f 0xb6 0xe6] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/dh]}
   [0x0f 0xb6 0xe7] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/bh]}
   [0x0f 0xb6 0xe8] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/al]}
   [0x0f 0xb6 0xe9] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/cl]}
   [0x0f 0xb6 0xea] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/dl]}
   [0x0f 0xb6 0xeb] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/bl]}
   [0x0f 0xb6 0xec] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/ah]}
   [0x0f 0xb6 0xed] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/ch]}
   [0x0f 0xb6 0xee] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/dh]}
   [0x0f 0xb6 0xef] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/bh]}
   [0x0f 0xb6 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si ::r/al]}
   [0x0f 0xb6 0xf1] {::i/tag ::i/movzx, ::i/args [::r/si ::r/cl]}
   [0x0f 0xb6 0xf2] {::i/tag ::i/movzx, ::i/args [::r/si ::r/dl]}
   [0x0f 0xb6 0xf3] {::i/tag ::i/movzx, ::i/args [::r/si ::r/bl]}
   [0x0f 0xb6 0xf4] {::i/tag ::i/movzx, ::i/args [::r/si ::r/ah]}
   [0x0f 0xb6 0xf5] {::i/tag ::i/movzx, ::i/args [::r/si ::r/ch]}
   [0x0f 0xb6 0xf6] {::i/tag ::i/movzx, ::i/args [::r/si ::r/dh]}
   [0x0f 0xb6 0xf7] {::i/tag ::i/movzx, ::i/args [::r/si ::r/bh]}
   [0x0f 0xb6 0xf8] {::i/tag ::i/movzx, ::i/args [::r/di ::r/al]}
   [0x0f 0xb6 0xf9] {::i/tag ::i/movzx, ::i/args [::r/di ::r/cl]}
   [0x0f 0xb6 0xfa] {::i/tag ::i/movzx, ::i/args [::r/di ::r/dl]}
   [0x0f 0xb6 0xfb] {::i/tag ::i/movzx, ::i/args [::r/di ::r/bl]}
   [0x0f 0xb6 0xfc] {::i/tag ::i/movzx, ::i/args [::r/di ::r/ah]}
   [0x0f 0xb6 0xfd] {::i/tag ::i/movzx, ::i/args [::r/di ::r/ch]}
   [0x0f 0xb6 0xfe] {::i/tag ::i/movzx, ::i/args [::r/di ::r/dh]}
   [0x0f 0xb6 0xff] {::i/tag ::i/movzx, ::i/args [::r/di ::r/bh]}
   [0x0f 0xb7 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x01] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x02] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x03] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x04] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x05] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x06 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xb7 0x06 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [0xf00f]], ::i/type ::i/word}
   [0x0f 0xb7 0x07] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x08] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x09] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x0a] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x0b] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x0c] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x0d] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x0e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xb7 0x0e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [0xf00f]], ::i/type ::i/word}
   [0x0f 0xb7 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x10] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x11] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x12] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x13] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x14] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x15] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x16 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xb7 0x16 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [0xf00f]], ::i/type ::i/word}
   [0x0f 0xb7 0x17] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x18] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x19] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x1a] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x1b] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x1c] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x1d] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x1e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xb7 0x1e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [0xf00f]], ::i/type ::i/word}
   [0x0f 0xb7 0x1f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x20] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x21] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x22] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x23] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x24] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x25] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x26 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xb7 0x26 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [0xf00f]], ::i/type ::i/word}
   [0x0f 0xb7 0x27] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x28] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x29] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x2a] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x2b] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x2c] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x2d] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x2e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xb7 0x2e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [0xf00f]], ::i/type ::i/word}
   [0x0f 0xb7 0x2f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x30] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x31] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x32] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x33] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x34] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x35] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x36 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xb7 0x36 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [0xf00f]], ::i/type ::i/word}
   [0x0f 0xb7 0x37] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x38] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x39] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x3a] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x3b] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x3c] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x3d] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x3e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xb7 0x3e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [0xf00f]], ::i/type ::i/word}
   [0x0f 0xb7 0x3f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x40 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x40 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x40 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x41 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x41 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x41 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x42 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x42 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x42 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x43 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x43 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x43 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x44 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x44 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x44 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x45 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x45 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x45 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x46 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x46 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x46 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x47 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x47 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x47 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x48 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x48 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x48 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x49 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x49 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x49 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x4a 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x4a 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x4a 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x4b 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x4b 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x4b 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x4c 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x4c 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x4c 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x4d 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x4d 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x4d 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x4e 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x4e 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x4e 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x4f 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x4f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x4f 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x50 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x50 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x50 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x51 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x51 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x51 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x52 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x52 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x52 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x53 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x53 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x53 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x54 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x54 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x54 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x55 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x55 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x55 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x56 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x56 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x56 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x57 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x57 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x57 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x58 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x58 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x58 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x59 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x59 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x59 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x5a 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x5a 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x5a 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x5b 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x5b 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x5b 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x5c 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x5c 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x5c 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x5d 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x5d 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x5d 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x5e 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x5e 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x5e 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x5f 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x5f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x5f 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x60 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x60 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x60 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x61 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x61 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x61 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x62 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x62 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x62 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x63 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x63 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x63 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x64 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x64 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x64 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x65 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x65 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x65 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x66 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x66 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x66 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x67 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x67 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x67 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x68 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x68 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x68 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x69 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x69 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x69 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x6a 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x6a 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x6a 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x6b 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x6b 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x6b 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x6c 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x6c 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x6c 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x6d 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x6d 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x6d 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x6e 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x6e 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x6e 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x6f 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x6f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x6f 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x70 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x70 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x70 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x71 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x71 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x71 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x72 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x72 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x72 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x73 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x73 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x73 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x74 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x74 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x74 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x75 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x75 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x75 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x76 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x76 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x76 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x77 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x77 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x77 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x78 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x78 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x78 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x79 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x79 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x79 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x7a 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x7a 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x7a 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x7b 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x7b 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x7b 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x7c 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x7c 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x7c 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x7d 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x7d 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x7d 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x7e 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x7e 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x7e 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x7f 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x7f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xb7 0x7f 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xb7 0x80 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x80 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x80 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x81 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x81 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x81 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x82 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x82 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x82 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x83 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x83 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x83 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x84 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x84 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x84 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x85 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x85 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x85 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x86 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x86 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x86 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x87 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x87 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x87 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/ax [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x88 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x88 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x88 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x89 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x89 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x89 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x8a 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x8a 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x8a 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x8b 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x8b 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x8b 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x8c 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x8c 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x8c 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x8d 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x8d 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x8d 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x8e 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x8e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x8e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x8f 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x8f 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x8f 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/cx [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x90 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x90 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x90 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x91 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x91 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x91 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x92 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x92 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x92 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x93 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x93 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x93 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x94 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x94 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x94 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x95 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x95 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x95 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x96 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x96 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x96 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x97 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x97 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x97 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/dx [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x98 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x98 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x98 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x99 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x99 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x99 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x9a 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x9a 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x9a 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x9b 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x9b 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x9b 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x9c 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0x9c 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x9c 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x9d 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0x9d 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x9d 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x9e 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0x9e 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x9e 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0x9f 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0x9f 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0x9f 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bx [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xa0 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xa0 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xa0 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xa1 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xa1 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xa1 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xa2 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xa2 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xa2 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xa3 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xa3 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xa3 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xa4 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xa4 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xa4 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xa5 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xa5 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xa5 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xa6 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0xa6 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xa6 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xa7 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0xa7 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xa7 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/sp [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xa8 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xa8 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xa8 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xa9 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xa9 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xa9 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xaa 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xaa 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xaa 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xab 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xab 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xab 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xac 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xac 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xac 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xad 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xad 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xad 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xae 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0xae 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xae 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xaf 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0xaf 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xaf 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/bp [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xb0 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xb0 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xb0 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xb1 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xb1 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xb1 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xb2 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xb2 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xb2 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xb3 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xb3 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xb3 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xb4 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xb4 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xb4 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xb5 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xb5 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xb5 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xb6 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0xb6 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xb6 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xb7 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0xb7 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xb7 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xb8 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xb8 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xb8 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xb9 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xb9 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xb9 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xba 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xba 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xba 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xbb 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xbb 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xbb 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xbc 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si]], ::i/type ::i/word}
   [0x0f 0xb7 0xbc 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xbc 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xbd 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di]], ::i/type ::i/word}
   [0x0f 0xb7 0xbd 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xbd 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xbe 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp]], ::i/type ::i/word}
   [0x0f 0xb7 0xbe 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xbe 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xbf 0x00 0x00] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx]], ::i/type ::i/word}
   [0x0f 0xb7 0xbf 0xf0 0x0f] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xb7 0xbf 0x0f 0xf0] {::i/tag ::i/movzx, ::i/args [::r/di [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xb7 0xc0] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/ax]}
   [0x0f 0xb7 0xc1] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/cx]}
   [0x0f 0xb7 0xc2] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/dx]}
   [0x0f 0xb7 0xc3] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/bx]}
   [0x0f 0xb7 0xc4] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/sp]}
   [0x0f 0xb7 0xc5] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/bp]}
   [0x0f 0xb7 0xc6] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/si]}
   [0x0f 0xb7 0xc7] {::i/tag ::i/movzx, ::i/args [::r/ax ::r/di]}
   [0x0f 0xb7 0xc8] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/ax]}
   [0x0f 0xb7 0xc9] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/cx]}
   [0x0f 0xb7 0xca] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/dx]}
   [0x0f 0xb7 0xcb] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/bx]}
   [0x0f 0xb7 0xcc] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/sp]}
   [0x0f 0xb7 0xcd] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/bp]}
   [0x0f 0xb7 0xce] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/si]}
   [0x0f 0xb7 0xcf] {::i/tag ::i/movzx, ::i/args [::r/cx ::r/di]}
   [0x0f 0xb7 0xd0] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/ax]}
   [0x0f 0xb7 0xd1] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/cx]}
   [0x0f 0xb7 0xd2] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/dx]}
   [0x0f 0xb7 0xd3] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/bx]}
   [0x0f 0xb7 0xd4] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/sp]}
   [0x0f 0xb7 0xd5] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/bp]}
   [0x0f 0xb7 0xd6] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/si]}
   [0x0f 0xb7 0xd7] {::i/tag ::i/movzx, ::i/args [::r/dx ::r/di]}
   [0x0f 0xb7 0xd8] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/ax]}
   [0x0f 0xb7 0xd9] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/cx]}
   [0x0f 0xb7 0xda] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/dx]}
   [0x0f 0xb7 0xdb] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/bx]}
   [0x0f 0xb7 0xdc] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/sp]}
   [0x0f 0xb7 0xdd] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/bp]}
   [0x0f 0xb7 0xde] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/si]}
   [0x0f 0xb7 0xdf] {::i/tag ::i/movzx, ::i/args [::r/bx ::r/di]}
   [0x0f 0xb7 0xe0] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/ax]}
   [0x0f 0xb7 0xe1] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/cx]}
   [0x0f 0xb7 0xe2] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/dx]}
   [0x0f 0xb7 0xe3] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/bx]}
   [0x0f 0xb7 0xe4] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/sp]}
   [0x0f 0xb7 0xe5] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/bp]}
   [0x0f 0xb7 0xe6] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/si]}
   [0x0f 0xb7 0xe7] {::i/tag ::i/movzx, ::i/args [::r/sp ::r/di]}
   [0x0f 0xb7 0xe8] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/ax]}
   [0x0f 0xb7 0xe9] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/cx]}
   [0x0f 0xb7 0xea] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/dx]}
   [0x0f 0xb7 0xeb] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/bx]}
   [0x0f 0xb7 0xec] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/sp]}
   [0x0f 0xb7 0xed] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/bp]}
   [0x0f 0xb7 0xee] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/si]}
   [0x0f 0xb7 0xef] {::i/tag ::i/movzx, ::i/args [::r/bp ::r/di]}
   [0x0f 0xb7 0xf0] {::i/tag ::i/movzx, ::i/args [::r/si ::r/ax]}
   [0x0f 0xb7 0xf1] {::i/tag ::i/movzx, ::i/args [::r/si ::r/cx]}
   [0x0f 0xb7 0xf2] {::i/tag ::i/movzx, ::i/args [::r/si ::r/dx]}
   [0x0f 0xb7 0xf3] {::i/tag ::i/movzx, ::i/args [::r/si ::r/bx]}
   [0x0f 0xb7 0xf4] {::i/tag ::i/movzx, ::i/args [::r/si ::r/sp]}
   [0x0f 0xb7 0xf5] {::i/tag ::i/movzx, ::i/args [::r/si ::r/bp]}
   [0x0f 0xb7 0xf6] {::i/tag ::i/movzx, ::i/args [::r/si ::r/si]}
   [0x0f 0xb7 0xf7] {::i/tag ::i/movzx, ::i/args [::r/si ::r/di]}
   [0x0f 0xb7 0xf8] {::i/tag ::i/movzx, ::i/args [::r/di ::r/ax]}
   [0x0f 0xb7 0xf9] {::i/tag ::i/movzx, ::i/args [::r/di ::r/cx]}
   [0x0f 0xb7 0xfa] {::i/tag ::i/movzx, ::i/args [::r/di ::r/dx]}
   [0x0f 0xb7 0xfb] {::i/tag ::i/movzx, ::i/args [::r/di ::r/bx]}
   [0x0f 0xb7 0xfc] {::i/tag ::i/movzx, ::i/args [::r/di ::r/sp]}
   [0x0f 0xb7 0xfd] {::i/tag ::i/movzx, ::i/args [::r/di ::r/bp]}
   [0x0f 0xb7 0xfe] {::i/tag ::i/movzx, ::i/args [::r/di ::r/si]}
   [0x0f 0xb7 0xff] {::i/tag ::i/movzx, ::i/args [::r/di ::r/di]}})

(def decode-examples-bsf
  {[0x0f 0xbc 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xbc 0x01] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xbc 0x02] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xbc 0x03] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xbc 0x04] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xbc 0x05] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xbc 0x06 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [0x0ff0]]}
   [0x0f 0xbc 0x06 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [0xf00f]]}
   [0x0f 0xbc 0x07] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xbc 0x08] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xbc 0x09] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xbc 0x0a] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xbc 0x0b] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xbc 0x0c] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xbc 0x0d] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xbc 0x0e 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [0x0ff0]]}
   [0x0f 0xbc 0x0e 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [0xf00f]]}
   [0x0f 0xbc 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xbc 0x10] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xbc 0x11] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xbc 0x12] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xbc 0x13] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xbc 0x14] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xbc 0x15] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xbc 0x16 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [0x0ff0]]}
   [0x0f 0xbc 0x16 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [0xf00f]]}
   [0x0f 0xbc 0x17] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xbc 0x18] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xbc 0x19] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xbc 0x1a] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xbc 0x1b] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xbc 0x1c] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xbc 0x1d] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xbc 0x1e 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [0x0ff0]]}
   [0x0f 0xbc 0x1e 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [0xf00f]]}
   [0x0f 0xbc 0x1f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xbc 0x20] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xbc 0x21] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xbc 0x22] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xbc 0x23] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xbc 0x24] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xbc 0x25] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xbc 0x26 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [0x0ff0]]}
   [0x0f 0xbc 0x26 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [0xf00f]]}
   [0x0f 0xbc 0x27] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xbc 0x28] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xbc 0x29] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xbc 0x2a] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xbc 0x2b] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xbc 0x2c] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xbc 0x2d] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xbc 0x2e 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [0x0ff0]]}
   [0x0f 0xbc 0x2e 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [0xf00f]]}
   [0x0f 0xbc 0x2f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xbc 0x30] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xbc 0x31] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xbc 0x32] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xbc 0x33] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xbc 0x34] {::i/tag ::i/bsf, ::i/args [::r/si [::r/si]]}
   [0x0f 0xbc 0x35] {::i/tag ::i/bsf, ::i/args [::r/si [::r/di]]}
   [0x0f 0xbc 0x36 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [0x0ff0]]}
   [0x0f 0xbc 0x36 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [0xf00f]]}
   [0x0f 0xbc 0x37] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xbc 0x38] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xbc 0x39] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xbc 0x3a] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xbc 0x3b] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xbc 0x3c] {::i/tag ::i/bsf, ::i/args [::r/di [::r/si]]}
   [0x0f 0xbc 0x3d] {::i/tag ::i/bsf, ::i/args [::r/di [::r/di]]}
   [0x0f 0xbc 0x3e 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [0x0ff0]]}
   [0x0f 0xbc 0x3e 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [0xf00f]]}
   [0x0f 0xbc 0x3f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xbc 0x40 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xbc 0x40 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/si -16]]}
   [0x0f 0xbc 0x40 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/si 15]]}
   [0x0f 0xbc 0x41 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xbc 0x41 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/di -16]]}
   [0x0f 0xbc 0x41 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/di 15]]}
   [0x0f 0xbc 0x42 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xbc 0x42 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/si -16]]}
   [0x0f 0xbc 0x42 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/si 15]]}
   [0x0f 0xbc 0x43 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xbc 0x43 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/di -16]]}
   [0x0f 0xbc 0x43 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/di 15]]}
   [0x0f 0xbc 0x44 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xbc 0x44 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/si -16]]}
   [0x0f 0xbc 0x44 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/si 15]]}
   [0x0f 0xbc 0x45 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xbc 0x45 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/di -16]]}
   [0x0f 0xbc 0x45 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/di 15]]}
   [0x0f 0xbc 0x46 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp]]}
   [0x0f 0xbc 0x46 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp -16]]}
   [0x0f 0xbc 0x46 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp 15]]}
   [0x0f 0xbc 0x47 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xbc 0x47 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx -16]]}
   [0x0f 0xbc 0x47 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx 15]]}
   [0x0f 0xbc 0x48 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xbc 0x48 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/si -16]]}
   [0x0f 0xbc 0x48 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/si 15]]}
   [0x0f 0xbc 0x49 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xbc 0x49 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/di -16]]}
   [0x0f 0xbc 0x49 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/di 15]]}
   [0x0f 0xbc 0x4a 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xbc 0x4a 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/si -16]]}
   [0x0f 0xbc 0x4a 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/si 15]]}
   [0x0f 0xbc 0x4b 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xbc 0x4b 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/di -16]]}
   [0x0f 0xbc 0x4b 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/di 15]]}
   [0x0f 0xbc 0x4c 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xbc 0x4c 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/si -16]]}
   [0x0f 0xbc 0x4c 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/si 15]]}
   [0x0f 0xbc 0x4d 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xbc 0x4d 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/di -16]]}
   [0x0f 0xbc 0x4d 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/di 15]]}
   [0x0f 0xbc 0x4e 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp]]}
   [0x0f 0xbc 0x4e 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp -16]]}
   [0x0f 0xbc 0x4e 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp 15]]}
   [0x0f 0xbc 0x4f 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xbc 0x4f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx -16]]}
   [0x0f 0xbc 0x4f 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx 15]]}
   [0x0f 0xbc 0x50 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xbc 0x50 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/si -16]]}
   [0x0f 0xbc 0x50 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/si 15]]}
   [0x0f 0xbc 0x51 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xbc 0x51 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/di -16]]}
   [0x0f 0xbc 0x51 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/di 15]]}
   [0x0f 0xbc 0x52 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xbc 0x52 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/si -16]]}
   [0x0f 0xbc 0x52 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/si 15]]}
   [0x0f 0xbc 0x53 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xbc 0x53 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/di -16]]}
   [0x0f 0xbc 0x53 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/di 15]]}
   [0x0f 0xbc 0x54 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xbc 0x54 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/si -16]]}
   [0x0f 0xbc 0x54 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/si 15]]}
   [0x0f 0xbc 0x55 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xbc 0x55 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/di -16]]}
   [0x0f 0xbc 0x55 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/di 15]]}
   [0x0f 0xbc 0x56 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp]]}
   [0x0f 0xbc 0x56 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp -16]]}
   [0x0f 0xbc 0x56 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp 15]]}
   [0x0f 0xbc 0x57 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xbc 0x57 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx -16]]}
   [0x0f 0xbc 0x57 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx 15]]}
   [0x0f 0xbc 0x58 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xbc 0x58 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/si -16]]}
   [0x0f 0xbc 0x58 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/si 15]]}
   [0x0f 0xbc 0x59 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xbc 0x59 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/di -16]]}
   [0x0f 0xbc 0x59 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/di 15]]}
   [0x0f 0xbc 0x5a 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xbc 0x5a 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/si -16]]}
   [0x0f 0xbc 0x5a 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/si 15]]}
   [0x0f 0xbc 0x5b 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xbc 0x5b 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/di -16]]}
   [0x0f 0xbc 0x5b 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/di 15]]}
   [0x0f 0xbc 0x5c 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xbc 0x5c 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/si -16]]}
   [0x0f 0xbc 0x5c 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/si 15]]}
   [0x0f 0xbc 0x5d 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xbc 0x5d 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/di -16]]}
   [0x0f 0xbc 0x5d 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/di 15]]}
   [0x0f 0xbc 0x5e 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp]]}
   [0x0f 0xbc 0x5e 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp -16]]}
   [0x0f 0xbc 0x5e 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp 15]]}
   [0x0f 0xbc 0x5f 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xbc 0x5f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx -16]]}
   [0x0f 0xbc 0x5f 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx 15]]}
   [0x0f 0xbc 0x60 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xbc 0x60 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/si -16]]}
   [0x0f 0xbc 0x60 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/si 15]]}
   [0x0f 0xbc 0x61 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xbc 0x61 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/di -16]]}
   [0x0f 0xbc 0x61 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/di 15]]}
   [0x0f 0xbc 0x62 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xbc 0x62 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/si -16]]}
   [0x0f 0xbc 0x62 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/si 15]]}
   [0x0f 0xbc 0x63 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xbc 0x63 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/di -16]]}
   [0x0f 0xbc 0x63 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/di 15]]}
   [0x0f 0xbc 0x64 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xbc 0x64 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/si -16]]}
   [0x0f 0xbc 0x64 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/si 15]]}
   [0x0f 0xbc 0x65 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xbc 0x65 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/di -16]]}
   [0x0f 0xbc 0x65 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/di 15]]}
   [0x0f 0xbc 0x66 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp]]}
   [0x0f 0xbc 0x66 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp -16]]}
   [0x0f 0xbc 0x66 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp 15]]}
   [0x0f 0xbc 0x67 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xbc 0x67 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx -16]]}
   [0x0f 0xbc 0x67 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx 15]]}
   [0x0f 0xbc 0x68 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xbc 0x68 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/si -16]]}
   [0x0f 0xbc 0x68 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/si 15]]}
   [0x0f 0xbc 0x69 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xbc 0x69 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/di -16]]}
   [0x0f 0xbc 0x69 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/di 15]]}
   [0x0f 0xbc 0x6a 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xbc 0x6a 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/si -16]]}
   [0x0f 0xbc 0x6a 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/si 15]]}
   [0x0f 0xbc 0x6b 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xbc 0x6b 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/di -16]]}
   [0x0f 0xbc 0x6b 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/di 15]]}
   [0x0f 0xbc 0x6c 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xbc 0x6c 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/si -16]]}
   [0x0f 0xbc 0x6c 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/si 15]]}
   [0x0f 0xbc 0x6d 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xbc 0x6d 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/di -16]]}
   [0x0f 0xbc 0x6d 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/di 15]]}
   [0x0f 0xbc 0x6e 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp]]}
   [0x0f 0xbc 0x6e 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp -16]]}
   [0x0f 0xbc 0x6e 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp 15]]}
   [0x0f 0xbc 0x6f 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xbc 0x6f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx -16]]}
   [0x0f 0xbc 0x6f 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx 15]]}
   [0x0f 0xbc 0x70 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xbc 0x70 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/si -16]]}
   [0x0f 0xbc 0x70 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/si 15]]}
   [0x0f 0xbc 0x71 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xbc 0x71 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/di -16]]}
   [0x0f 0xbc 0x71 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/di 15]]}
   [0x0f 0xbc 0x72 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xbc 0x72 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/si -16]]}
   [0x0f 0xbc 0x72 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/si 15]]}
   [0x0f 0xbc 0x73 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xbc 0x73 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/di -16]]}
   [0x0f 0xbc 0x73 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/di 15]]}
   [0x0f 0xbc 0x74 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/si]]}
   [0x0f 0xbc 0x74 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/si -16]]}
   [0x0f 0xbc 0x74 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/si 15]]}
   [0x0f 0xbc 0x75 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/di]]}
   [0x0f 0xbc 0x75 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/di -16]]}
   [0x0f 0xbc 0x75 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/di 15]]}
   [0x0f 0xbc 0x76 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp]]}
   [0x0f 0xbc 0x76 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp -16]]}
   [0x0f 0xbc 0x76 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp 15]]}
   [0x0f 0xbc 0x77 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xbc 0x77 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx -16]]}
   [0x0f 0xbc 0x77 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx 15]]}
   [0x0f 0xbc 0x78 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xbc 0x78 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/si -16]]}
   [0x0f 0xbc 0x78 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/si 15]]}
   [0x0f 0xbc 0x79 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xbc 0x79 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/di -16]]}
   [0x0f 0xbc 0x79 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/di 15]]}
   [0x0f 0xbc 0x7a 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xbc 0x7a 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/si -16]]}
   [0x0f 0xbc 0x7a 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/si 15]]}
   [0x0f 0xbc 0x7b 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xbc 0x7b 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/di -16]]}
   [0x0f 0xbc 0x7b 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/di 15]]}
   [0x0f 0xbc 0x7c 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/si]]}
   [0x0f 0xbc 0x7c 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/si -16]]}
   [0x0f 0xbc 0x7c 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/si 15]]}
   [0x0f 0xbc 0x7d 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/di]]}
   [0x0f 0xbc 0x7d 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/di -16]]}
   [0x0f 0xbc 0x7d 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/di 15]]}
   [0x0f 0xbc 0x7e 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp]]}
   [0x0f 0xbc 0x7e 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp -16]]}
   [0x0f 0xbc 0x7e 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp 15]]}
   [0x0f 0xbc 0x7f 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xbc 0x7f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx -16]]}
   [0x0f 0xbc 0x7f 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx 15]]}
   [0x0f 0xbc 0x80 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xbc 0x80 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/si 4080]]}
   [0x0f 0xbc 0x80 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/si -4081]]}
   [0x0f 0xbc 0x81 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xbc 0x81 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/di 4080]]}
   [0x0f 0xbc 0x81 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx ::r/di -4081]]}
   [0x0f 0xbc 0x82 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xbc 0x82 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/si 4080]]}
   [0x0f 0xbc 0x82 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/si -4081]]}
   [0x0f 0xbc 0x83 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xbc 0x83 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/di 4080]]}
   [0x0f 0xbc 0x83 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp ::r/di -4081]]}
   [0x0f 0xbc 0x84 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xbc 0x84 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/si 4080]]}
   [0x0f 0xbc 0x84 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/si -4081]]}
   [0x0f 0xbc 0x85 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xbc 0x85 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/di 4080]]}
   [0x0f 0xbc 0x85 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/di -4081]]}
   [0x0f 0xbc 0x86 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp]]}
   [0x0f 0xbc 0x86 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp 4080]]}
   [0x0f 0xbc 0x86 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bp -4081]]}
   [0x0f 0xbc 0x87 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xbc 0x87 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx 4080]]}
   [0x0f 0xbc 0x87 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/ax [::r/bx -4081]]}
   [0x0f 0xbc 0x88 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xbc 0x88 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/si 4080]]}
   [0x0f 0xbc 0x88 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/si -4081]]}
   [0x0f 0xbc 0x89 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xbc 0x89 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/di 4080]]}
   [0x0f 0xbc 0x89 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx ::r/di -4081]]}
   [0x0f 0xbc 0x8a 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xbc 0x8a 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/si 4080]]}
   [0x0f 0xbc 0x8a 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/si -4081]]}
   [0x0f 0xbc 0x8b 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xbc 0x8b 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/di 4080]]}
   [0x0f 0xbc 0x8b 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp ::r/di -4081]]}
   [0x0f 0xbc 0x8c 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xbc 0x8c 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/si 4080]]}
   [0x0f 0xbc 0x8c 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/si -4081]]}
   [0x0f 0xbc 0x8d 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xbc 0x8d 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/di 4080]]}
   [0x0f 0xbc 0x8d 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/di -4081]]}
   [0x0f 0xbc 0x8e 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp]]}
   [0x0f 0xbc 0x8e 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp 4080]]}
   [0x0f 0xbc 0x8e 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bp -4081]]}
   [0x0f 0xbc 0x8f 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xbc 0x8f 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx 4080]]}
   [0x0f 0xbc 0x8f 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/cx [::r/bx -4081]]}
   [0x0f 0xbc 0x90 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xbc 0x90 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/si 4080]]}
   [0x0f 0xbc 0x90 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/si -4081]]}
   [0x0f 0xbc 0x91 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xbc 0x91 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/di 4080]]}
   [0x0f 0xbc 0x91 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx ::r/di -4081]]}
   [0x0f 0xbc 0x92 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xbc 0x92 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/si 4080]]}
   [0x0f 0xbc 0x92 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/si -4081]]}
   [0x0f 0xbc 0x93 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xbc 0x93 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/di 4080]]}
   [0x0f 0xbc 0x93 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp ::r/di -4081]]}
   [0x0f 0xbc 0x94 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xbc 0x94 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/si 4080]]}
   [0x0f 0xbc 0x94 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/si -4081]]}
   [0x0f 0xbc 0x95 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xbc 0x95 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/di 4080]]}
   [0x0f 0xbc 0x95 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/di -4081]]}
   [0x0f 0xbc 0x96 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp]]}
   [0x0f 0xbc 0x96 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp 4080]]}
   [0x0f 0xbc 0x96 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bp -4081]]}
   [0x0f 0xbc 0x97 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xbc 0x97 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx 4080]]}
   [0x0f 0xbc 0x97 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/dx [::r/bx -4081]]}
   [0x0f 0xbc 0x98 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xbc 0x98 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/si 4080]]}
   [0x0f 0xbc 0x98 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/si -4081]]}
   [0x0f 0xbc 0x99 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xbc 0x99 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/di 4080]]}
   [0x0f 0xbc 0x99 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx ::r/di -4081]]}
   [0x0f 0xbc 0x9a 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xbc 0x9a 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/si 4080]]}
   [0x0f 0xbc 0x9a 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/si -4081]]}
   [0x0f 0xbc 0x9b 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xbc 0x9b 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/di 4080]]}
   [0x0f 0xbc 0x9b 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp ::r/di -4081]]}
   [0x0f 0xbc 0x9c 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xbc 0x9c 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/si 4080]]}
   [0x0f 0xbc 0x9c 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/si -4081]]}
   [0x0f 0xbc 0x9d 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xbc 0x9d 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/di 4080]]}
   [0x0f 0xbc 0x9d 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/di -4081]]}
   [0x0f 0xbc 0x9e 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp]]}
   [0x0f 0xbc 0x9e 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp 4080]]}
   [0x0f 0xbc 0x9e 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bp -4081]]}
   [0x0f 0xbc 0x9f 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xbc 0x9f 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx 4080]]}
   [0x0f 0xbc 0x9f 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bx [::r/bx -4081]]}
   [0x0f 0xbc 0xa0 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xbc 0xa0 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/si 4080]]}
   [0x0f 0xbc 0xa0 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/si -4081]]}
   [0x0f 0xbc 0xa1 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xbc 0xa1 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/di 4080]]}
   [0x0f 0xbc 0xa1 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx ::r/di -4081]]}
   [0x0f 0xbc 0xa2 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xbc 0xa2 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/si 4080]]}
   [0x0f 0xbc 0xa2 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/si -4081]]}
   [0x0f 0xbc 0xa3 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xbc 0xa3 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/di 4080]]}
   [0x0f 0xbc 0xa3 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp ::r/di -4081]]}
   [0x0f 0xbc 0xa4 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xbc 0xa4 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/si 4080]]}
   [0x0f 0xbc 0xa4 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/si -4081]]}
   [0x0f 0xbc 0xa5 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xbc 0xa5 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/di 4080]]}
   [0x0f 0xbc 0xa5 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/di -4081]]}
   [0x0f 0xbc 0xa6 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp]]}
   [0x0f 0xbc 0xa6 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp 4080]]}
   [0x0f 0xbc 0xa6 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bp -4081]]}
   [0x0f 0xbc 0xa7 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xbc 0xa7 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx 4080]]}
   [0x0f 0xbc 0xa7 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/sp [::r/bx -4081]]}
   [0x0f 0xbc 0xa8 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xbc 0xa8 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/si 4080]]}
   [0x0f 0xbc 0xa8 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/si -4081]]}
   [0x0f 0xbc 0xa9 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xbc 0xa9 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/di 4080]]}
   [0x0f 0xbc 0xa9 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx ::r/di -4081]]}
   [0x0f 0xbc 0xaa 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xbc 0xaa 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/si 4080]]}
   [0x0f 0xbc 0xaa 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/si -4081]]}
   [0x0f 0xbc 0xab 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xbc 0xab 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/di 4080]]}
   [0x0f 0xbc 0xab 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp ::r/di -4081]]}
   [0x0f 0xbc 0xac 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xbc 0xac 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/si 4080]]}
   [0x0f 0xbc 0xac 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/si -4081]]}
   [0x0f 0xbc 0xad 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xbc 0xad 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/di 4080]]}
   [0x0f 0xbc 0xad 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/di -4081]]}
   [0x0f 0xbc 0xae 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp]]}
   [0x0f 0xbc 0xae 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp 4080]]}
   [0x0f 0xbc 0xae 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bp -4081]]}
   [0x0f 0xbc 0xaf 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xbc 0xaf 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx 4080]]}
   [0x0f 0xbc 0xaf 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/bp [::r/bx -4081]]}
   [0x0f 0xbc 0xb0 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xbc 0xb0 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/si 4080]]}
   [0x0f 0xbc 0xb0 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/si -4081]]}
   [0x0f 0xbc 0xb1 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xbc 0xb1 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/di 4080]]}
   [0x0f 0xbc 0xb1 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx ::r/di -4081]]}
   [0x0f 0xbc 0xb2 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xbc 0xb2 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/si 4080]]}
   [0x0f 0xbc 0xb2 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/si -4081]]}
   [0x0f 0xbc 0xb3 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xbc 0xb3 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/di 4080]]}
   [0x0f 0xbc 0xb3 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp ::r/di -4081]]}
   [0x0f 0xbc 0xb4 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/si]]}
   [0x0f 0xbc 0xb4 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/si 4080]]}
   [0x0f 0xbc 0xb4 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/si -4081]]}
   [0x0f 0xbc 0xb5 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/di]]}
   [0x0f 0xbc 0xb5 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/di 4080]]}
   [0x0f 0xbc 0xb5 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/di -4081]]}
   [0x0f 0xbc 0xb6 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp]]}
   [0x0f 0xbc 0xb6 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp 4080]]}
   [0x0f 0xbc 0xb6 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bp -4081]]}
   [0x0f 0xbc 0xb7 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xbc 0xb7 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx 4080]]}
   [0x0f 0xbc 0xb7 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si [::r/bx -4081]]}
   [0x0f 0xbc 0xb8 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xbc 0xb8 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/si 4080]]}
   [0x0f 0xbc 0xb8 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/si -4081]]}
   [0x0f 0xbc 0xb9 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xbc 0xb9 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/di 4080]]}
   [0x0f 0xbc 0xb9 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx ::r/di -4081]]}
   [0x0f 0xbc 0xba 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xbc 0xba 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/si 4080]]}
   [0x0f 0xbc 0xba 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/si -4081]]}
   [0x0f 0xbc 0xbb 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xbc 0xbb 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/di 4080]]}
   [0x0f 0xbc 0xbb 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp ::r/di -4081]]}
   [0x0f 0xbc 0xbc 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/si]]}
   [0x0f 0xbc 0xbc 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/si 4080]]}
   [0x0f 0xbc 0xbc 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/si -4081]]}
   [0x0f 0xbc 0xbd 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/di]]}
   [0x0f 0xbc 0xbd 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/di 4080]]}
   [0x0f 0xbc 0xbd 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/di -4081]]}
   [0x0f 0xbc 0xbe 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp]]}
   [0x0f 0xbc 0xbe 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp 4080]]}
   [0x0f 0xbc 0xbe 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bp -4081]]}
   [0x0f 0xbc 0xbf 0x00 0x00] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xbc 0xbf 0xf0 0x0f] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx 4080]]}
   [0x0f 0xbc 0xbf 0x0f 0xf0] {::i/tag ::i/bsf, ::i/args [::r/di [::r/bx -4081]]}
   [0x0f 0xbc 0xc0] {::i/tag ::i/bsf, ::i/args [::r/ax ::r/ax]}
   [0x0f 0xbc 0xc1] {::i/tag ::i/bsf, ::i/args [::r/ax ::r/cx]}
   [0x0f 0xbc 0xc2] {::i/tag ::i/bsf, ::i/args [::r/ax ::r/dx]}
   [0x0f 0xbc 0xc3] {::i/tag ::i/bsf, ::i/args [::r/ax ::r/bx]}
   [0x0f 0xbc 0xc4] {::i/tag ::i/bsf, ::i/args [::r/ax ::r/sp]}
   [0x0f 0xbc 0xc5] {::i/tag ::i/bsf, ::i/args [::r/ax ::r/bp]}
   [0x0f 0xbc 0xc6] {::i/tag ::i/bsf, ::i/args [::r/ax ::r/si]}
   [0x0f 0xbc 0xc7] {::i/tag ::i/bsf, ::i/args [::r/ax ::r/di]}
   [0x0f 0xbc 0xc8] {::i/tag ::i/bsf, ::i/args [::r/cx ::r/ax]}
   [0x0f 0xbc 0xc9] {::i/tag ::i/bsf, ::i/args [::r/cx ::r/cx]}
   [0x0f 0xbc 0xca] {::i/tag ::i/bsf, ::i/args [::r/cx ::r/dx]}
   [0x0f 0xbc 0xcb] {::i/tag ::i/bsf, ::i/args [::r/cx ::r/bx]}
   [0x0f 0xbc 0xcc] {::i/tag ::i/bsf, ::i/args [::r/cx ::r/sp]}
   [0x0f 0xbc 0xcd] {::i/tag ::i/bsf, ::i/args [::r/cx ::r/bp]}
   [0x0f 0xbc 0xce] {::i/tag ::i/bsf, ::i/args [::r/cx ::r/si]}
   [0x0f 0xbc 0xcf] {::i/tag ::i/bsf, ::i/args [::r/cx ::r/di]}
   [0x0f 0xbc 0xd0] {::i/tag ::i/bsf, ::i/args [::r/dx ::r/ax]}
   [0x0f 0xbc 0xd1] {::i/tag ::i/bsf, ::i/args [::r/dx ::r/cx]}
   [0x0f 0xbc 0xd2] {::i/tag ::i/bsf, ::i/args [::r/dx ::r/dx]}
   [0x0f 0xbc 0xd3] {::i/tag ::i/bsf, ::i/args [::r/dx ::r/bx]}
   [0x0f 0xbc 0xd4] {::i/tag ::i/bsf, ::i/args [::r/dx ::r/sp]}
   [0x0f 0xbc 0xd5] {::i/tag ::i/bsf, ::i/args [::r/dx ::r/bp]}
   [0x0f 0xbc 0xd6] {::i/tag ::i/bsf, ::i/args [::r/dx ::r/si]}
   [0x0f 0xbc 0xd7] {::i/tag ::i/bsf, ::i/args [::r/dx ::r/di]}
   [0x0f 0xbc 0xd8] {::i/tag ::i/bsf, ::i/args [::r/bx ::r/ax]}
   [0x0f 0xbc 0xd9] {::i/tag ::i/bsf, ::i/args [::r/bx ::r/cx]}
   [0x0f 0xbc 0xda] {::i/tag ::i/bsf, ::i/args [::r/bx ::r/dx]}
   [0x0f 0xbc 0xdb] {::i/tag ::i/bsf, ::i/args [::r/bx ::r/bx]}
   [0x0f 0xbc 0xdc] {::i/tag ::i/bsf, ::i/args [::r/bx ::r/sp]}
   [0x0f 0xbc 0xdd] {::i/tag ::i/bsf, ::i/args [::r/bx ::r/bp]}
   [0x0f 0xbc 0xde] {::i/tag ::i/bsf, ::i/args [::r/bx ::r/si]}
   [0x0f 0xbc 0xdf] {::i/tag ::i/bsf, ::i/args [::r/bx ::r/di]}
   [0x0f 0xbc 0xe0] {::i/tag ::i/bsf, ::i/args [::r/sp ::r/ax]}
   [0x0f 0xbc 0xe1] {::i/tag ::i/bsf, ::i/args [::r/sp ::r/cx]}
   [0x0f 0xbc 0xe2] {::i/tag ::i/bsf, ::i/args [::r/sp ::r/dx]}
   [0x0f 0xbc 0xe3] {::i/tag ::i/bsf, ::i/args [::r/sp ::r/bx]}
   [0x0f 0xbc 0xe4] {::i/tag ::i/bsf, ::i/args [::r/sp ::r/sp]}
   [0x0f 0xbc 0xe5] {::i/tag ::i/bsf, ::i/args [::r/sp ::r/bp]}
   [0x0f 0xbc 0xe6] {::i/tag ::i/bsf, ::i/args [::r/sp ::r/si]}
   [0x0f 0xbc 0xe7] {::i/tag ::i/bsf, ::i/args [::r/sp ::r/di]}
   [0x0f 0xbc 0xe8] {::i/tag ::i/bsf, ::i/args [::r/bp ::r/ax]}
   [0x0f 0xbc 0xe9] {::i/tag ::i/bsf, ::i/args [::r/bp ::r/cx]}
   [0x0f 0xbc 0xea] {::i/tag ::i/bsf, ::i/args [::r/bp ::r/dx]}
   [0x0f 0xbc 0xeb] {::i/tag ::i/bsf, ::i/args [::r/bp ::r/bx]}
   [0x0f 0xbc 0xec] {::i/tag ::i/bsf, ::i/args [::r/bp ::r/sp]}
   [0x0f 0xbc 0xed] {::i/tag ::i/bsf, ::i/args [::r/bp ::r/bp]}
   [0x0f 0xbc 0xee] {::i/tag ::i/bsf, ::i/args [::r/bp ::r/si]}
   [0x0f 0xbc 0xef] {::i/tag ::i/bsf, ::i/args [::r/bp ::r/di]}
   [0x0f 0xbc 0xf0] {::i/tag ::i/bsf, ::i/args [::r/si ::r/ax]}
   [0x0f 0xbc 0xf1] {::i/tag ::i/bsf, ::i/args [::r/si ::r/cx]}
   [0x0f 0xbc 0xf2] {::i/tag ::i/bsf, ::i/args [::r/si ::r/dx]}
   [0x0f 0xbc 0xf3] {::i/tag ::i/bsf, ::i/args [::r/si ::r/bx]}
   [0x0f 0xbc 0xf4] {::i/tag ::i/bsf, ::i/args [::r/si ::r/sp]}
   [0x0f 0xbc 0xf5] {::i/tag ::i/bsf, ::i/args [::r/si ::r/bp]}
   [0x0f 0xbc 0xf6] {::i/tag ::i/bsf, ::i/args [::r/si ::r/si]}
   [0x0f 0xbc 0xf7] {::i/tag ::i/bsf, ::i/args [::r/si ::r/di]}
   [0x0f 0xbc 0xf8] {::i/tag ::i/bsf, ::i/args [::r/di ::r/ax]}
   [0x0f 0xbc 0xf9] {::i/tag ::i/bsf, ::i/args [::r/di ::r/cx]}
   [0x0f 0xbc 0xfa] {::i/tag ::i/bsf, ::i/args [::r/di ::r/dx]}
   [0x0f 0xbc 0xfb] {::i/tag ::i/bsf, ::i/args [::r/di ::r/bx]}
   [0x0f 0xbc 0xfc] {::i/tag ::i/bsf, ::i/args [::r/di ::r/sp]}
   [0x0f 0xbc 0xfd] {::i/tag ::i/bsf, ::i/args [::r/di ::r/bp]}
   [0x0f 0xbc 0xfe] {::i/tag ::i/bsf, ::i/args [::r/di ::r/si]}
   [0x0f 0xbc 0xff] {::i/tag ::i/bsf, ::i/args [::r/di ::r/di]}})


(def decode-examples-bsr
  {[0x0f 0xbd 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xbd 0x01] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xbd 0x02] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xbd 0x03] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xbd 0x04] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xbd 0x05] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xbd 0x06 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [0x0ff0]]}
   [0x0f 0xbd 0x06 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [0xf00f]]}
   [0x0f 0xbd 0x07] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xbd 0x08] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xbd 0x09] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xbd 0x0a] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xbd 0x0b] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xbd 0x0c] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xbd 0x0d] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xbd 0x0e 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [0x0ff0]]}
   [0x0f 0xbd 0x0e 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [0xf00f]]}
   [0x0f 0xbd 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xbd 0x10] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xbd 0x11] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xbd 0x12] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xbd 0x13] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xbd 0x14] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xbd 0x15] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xbd 0x16 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [0x0ff0]]}
   [0x0f 0xbd 0x16 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [0xf00f]]}
   [0x0f 0xbd 0x17] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xbd 0x18] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xbd 0x19] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xbd 0x1a] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xbd 0x1b] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xbd 0x1c] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xbd 0x1d] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xbd 0x1e 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [0x0ff0]]}
   [0x0f 0xbd 0x1e 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [0xf00f]]}
   [0x0f 0xbd 0x1f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xbd 0x20] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xbd 0x21] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xbd 0x22] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xbd 0x23] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xbd 0x24] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xbd 0x25] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xbd 0x26 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [0x0ff0]]}
   [0x0f 0xbd 0x26 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [0xf00f]]}
   [0x0f 0xbd 0x27] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xbd 0x28] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xbd 0x29] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xbd 0x2a] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xbd 0x2b] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xbd 0x2c] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xbd 0x2d] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xbd 0x2e 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [0x0ff0]]}
   [0x0f 0xbd 0x2e 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [0xf00f]]}
   [0x0f 0xbd 0x2f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xbd 0x30] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xbd 0x31] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xbd 0x32] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xbd 0x33] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xbd 0x34] {::i/tag ::i/bsr, ::i/args [::r/si [::r/si]]}
   [0x0f 0xbd 0x35] {::i/tag ::i/bsr, ::i/args [::r/si [::r/di]]}
   [0x0f 0xbd 0x36 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [0x0ff0]]}
   [0x0f 0xbd 0x36 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [0xf00f]]}
   [0x0f 0xbd 0x37] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xbd 0x38] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xbd 0x39] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xbd 0x3a] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xbd 0x3b] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xbd 0x3c] {::i/tag ::i/bsr, ::i/args [::r/di [::r/si]]}
   [0x0f 0xbd 0x3d] {::i/tag ::i/bsr, ::i/args [::r/di [::r/di]]}
   [0x0f 0xbd 0x3e 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [0x0ff0]]}
   [0x0f 0xbd 0x3e 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [0xf00f]]}
   [0x0f 0xbd 0x3f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xbd 0x40 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xbd 0x40 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/si -16]]}
   [0x0f 0xbd 0x40 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/si 15]]}
   [0x0f 0xbd 0x41 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xbd 0x41 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/di -16]]}
   [0x0f 0xbd 0x41 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/di 15]]}
   [0x0f 0xbd 0x42 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xbd 0x42 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/si -16]]}
   [0x0f 0xbd 0x42 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/si 15]]}
   [0x0f 0xbd 0x43 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xbd 0x43 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/di -16]]}
   [0x0f 0xbd 0x43 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/di 15]]}
   [0x0f 0xbd 0x44 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xbd 0x44 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/si -16]]}
   [0x0f 0xbd 0x44 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/si 15]]}
   [0x0f 0xbd 0x45 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xbd 0x45 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/di -16]]}
   [0x0f 0xbd 0x45 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/di 15]]}
   [0x0f 0xbd 0x46 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp]]}
   [0x0f 0xbd 0x46 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp -16]]}
   [0x0f 0xbd 0x46 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp 15]]}
   [0x0f 0xbd 0x47 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xbd 0x47 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx -16]]}
   [0x0f 0xbd 0x47 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx 15]]}
   [0x0f 0xbd 0x48 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xbd 0x48 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/si -16]]}
   [0x0f 0xbd 0x48 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/si 15]]}
   [0x0f 0xbd 0x49 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xbd 0x49 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/di -16]]}
   [0x0f 0xbd 0x49 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/di 15]]}
   [0x0f 0xbd 0x4a 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xbd 0x4a 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/si -16]]}
   [0x0f 0xbd 0x4a 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/si 15]]}
   [0x0f 0xbd 0x4b 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xbd 0x4b 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/di -16]]}
   [0x0f 0xbd 0x4b 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/di 15]]}
   [0x0f 0xbd 0x4c 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xbd 0x4c 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/si -16]]}
   [0x0f 0xbd 0x4c 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/si 15]]}
   [0x0f 0xbd 0x4d 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xbd 0x4d 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/di -16]]}
   [0x0f 0xbd 0x4d 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/di 15]]}
   [0x0f 0xbd 0x4e 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp]]}
   [0x0f 0xbd 0x4e 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp -16]]}
   [0x0f 0xbd 0x4e 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp 15]]}
   [0x0f 0xbd 0x4f 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xbd 0x4f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx -16]]}
   [0x0f 0xbd 0x4f 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx 15]]}
   [0x0f 0xbd 0x50 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xbd 0x50 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/si -16]]}
   [0x0f 0xbd 0x50 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/si 15]]}
   [0x0f 0xbd 0x51 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xbd 0x51 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/di -16]]}
   [0x0f 0xbd 0x51 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/di 15]]}
   [0x0f 0xbd 0x52 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xbd 0x52 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/si -16]]}
   [0x0f 0xbd 0x52 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/si 15]]}
   [0x0f 0xbd 0x53 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xbd 0x53 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/di -16]]}
   [0x0f 0xbd 0x53 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/di 15]]}
   [0x0f 0xbd 0x54 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xbd 0x54 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/si -16]]}
   [0x0f 0xbd 0x54 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/si 15]]}
   [0x0f 0xbd 0x55 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xbd 0x55 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/di -16]]}
   [0x0f 0xbd 0x55 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/di 15]]}
   [0x0f 0xbd 0x56 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp]]}
   [0x0f 0xbd 0x56 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp -16]]}
   [0x0f 0xbd 0x56 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp 15]]}
   [0x0f 0xbd 0x57 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xbd 0x57 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx -16]]}
   [0x0f 0xbd 0x57 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx 15]]}
   [0x0f 0xbd 0x58 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xbd 0x58 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/si -16]]}
   [0x0f 0xbd 0x58 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/si 15]]}
   [0x0f 0xbd 0x59 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xbd 0x59 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/di -16]]}
   [0x0f 0xbd 0x59 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/di 15]]}
   [0x0f 0xbd 0x5a 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xbd 0x5a 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/si -16]]}
   [0x0f 0xbd 0x5a 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/si 15]]}
   [0x0f 0xbd 0x5b 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xbd 0x5b 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/di -16]]}
   [0x0f 0xbd 0x5b 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/di 15]]}
   [0x0f 0xbd 0x5c 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xbd 0x5c 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/si -16]]}
   [0x0f 0xbd 0x5c 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/si 15]]}
   [0x0f 0xbd 0x5d 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xbd 0x5d 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/di -16]]}
   [0x0f 0xbd 0x5d 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/di 15]]}
   [0x0f 0xbd 0x5e 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp]]}
   [0x0f 0xbd 0x5e 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp -16]]}
   [0x0f 0xbd 0x5e 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp 15]]}
   [0x0f 0xbd 0x5f 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xbd 0x5f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx -16]]}
   [0x0f 0xbd 0x5f 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx 15]]}
   [0x0f 0xbd 0x60 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xbd 0x60 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/si -16]]}
   [0x0f 0xbd 0x60 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/si 15]]}
   [0x0f 0xbd 0x61 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xbd 0x61 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/di -16]]}
   [0x0f 0xbd 0x61 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/di 15]]}
   [0x0f 0xbd 0x62 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xbd 0x62 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/si -16]]}
   [0x0f 0xbd 0x62 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/si 15]]}
   [0x0f 0xbd 0x63 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xbd 0x63 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/di -16]]}
   [0x0f 0xbd 0x63 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/di 15]]}
   [0x0f 0xbd 0x64 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xbd 0x64 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/si -16]]}
   [0x0f 0xbd 0x64 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/si 15]]}
   [0x0f 0xbd 0x65 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xbd 0x65 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/di -16]]}
   [0x0f 0xbd 0x65 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/di 15]]}
   [0x0f 0xbd 0x66 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp]]}
   [0x0f 0xbd 0x66 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp -16]]}
   [0x0f 0xbd 0x66 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp 15]]}
   [0x0f 0xbd 0x67 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xbd 0x67 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx -16]]}
   [0x0f 0xbd 0x67 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx 15]]}
   [0x0f 0xbd 0x68 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xbd 0x68 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/si -16]]}
   [0x0f 0xbd 0x68 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/si 15]]}
   [0x0f 0xbd 0x69 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xbd 0x69 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/di -16]]}
   [0x0f 0xbd 0x69 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/di 15]]}
   [0x0f 0xbd 0x6a 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xbd 0x6a 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/si -16]]}
   [0x0f 0xbd 0x6a 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/si 15]]}
   [0x0f 0xbd 0x6b 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xbd 0x6b 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/di -16]]}
   [0x0f 0xbd 0x6b 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/di 15]]}
   [0x0f 0xbd 0x6c 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xbd 0x6c 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/si -16]]}
   [0x0f 0xbd 0x6c 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/si 15]]}
   [0x0f 0xbd 0x6d 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xbd 0x6d 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/di -16]]}
   [0x0f 0xbd 0x6d 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/di 15]]}
   [0x0f 0xbd 0x6e 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp]]}
   [0x0f 0xbd 0x6e 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp -16]]}
   [0x0f 0xbd 0x6e 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp 15]]}
   [0x0f 0xbd 0x6f 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xbd 0x6f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx -16]]}
   [0x0f 0xbd 0x6f 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx 15]]}
   [0x0f 0xbd 0x70 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xbd 0x70 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/si -16]]}
   [0x0f 0xbd 0x70 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/si 15]]}
   [0x0f 0xbd 0x71 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xbd 0x71 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/di -16]]}
   [0x0f 0xbd 0x71 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/di 15]]}
   [0x0f 0xbd 0x72 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xbd 0x72 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/si -16]]}
   [0x0f 0xbd 0x72 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/si 15]]}
   [0x0f 0xbd 0x73 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xbd 0x73 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/di -16]]}
   [0x0f 0xbd 0x73 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/di 15]]}
   [0x0f 0xbd 0x74 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/si]]}
   [0x0f 0xbd 0x74 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/si -16]]}
   [0x0f 0xbd 0x74 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/si 15]]}
   [0x0f 0xbd 0x75 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/di]]}
   [0x0f 0xbd 0x75 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/di -16]]}
   [0x0f 0xbd 0x75 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/di 15]]}
   [0x0f 0xbd 0x76 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp]]}
   [0x0f 0xbd 0x76 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp -16]]}
   [0x0f 0xbd 0x76 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp 15]]}
   [0x0f 0xbd 0x77 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xbd 0x77 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx -16]]}
   [0x0f 0xbd 0x77 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx 15]]}
   [0x0f 0xbd 0x78 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xbd 0x78 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/si -16]]}
   [0x0f 0xbd 0x78 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/si 15]]}
   [0x0f 0xbd 0x79 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xbd 0x79 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/di -16]]}
   [0x0f 0xbd 0x79 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/di 15]]}
   [0x0f 0xbd 0x7a 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xbd 0x7a 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/si -16]]}
   [0x0f 0xbd 0x7a 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/si 15]]}
   [0x0f 0xbd 0x7b 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xbd 0x7b 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/di -16]]}
   [0x0f 0xbd 0x7b 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/di 15]]}
   [0x0f 0xbd 0x7c 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/si]]}
   [0x0f 0xbd 0x7c 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/si -16]]}
   [0x0f 0xbd 0x7c 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/si 15]]}
   [0x0f 0xbd 0x7d 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/di]]}
   [0x0f 0xbd 0x7d 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/di -16]]}
   [0x0f 0xbd 0x7d 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/di 15]]}
   [0x0f 0xbd 0x7e 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp]]}
   [0x0f 0xbd 0x7e 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp -16]]}
   [0x0f 0xbd 0x7e 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp 15]]}
   [0x0f 0xbd 0x7f 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xbd 0x7f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx -16]]}
   [0x0f 0xbd 0x7f 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx 15]]}
   [0x0f 0xbd 0x80 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x0f 0xbd 0x80 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/si 4080]]}
   [0x0f 0xbd 0x80 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/si -4081]]}
   [0x0f 0xbd 0x81 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x0f 0xbd 0x81 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/di 4080]]}
   [0x0f 0xbd 0x81 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx ::r/di -4081]]}
   [0x0f 0xbd 0x82 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x0f 0xbd 0x82 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/si 4080]]}
   [0x0f 0xbd 0x82 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/si -4081]]}
   [0x0f 0xbd 0x83 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x0f 0xbd 0x83 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/di 4080]]}
   [0x0f 0xbd 0x83 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp ::r/di -4081]]}
   [0x0f 0xbd 0x84 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/si]]}
   [0x0f 0xbd 0x84 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/si 4080]]}
   [0x0f 0xbd 0x84 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/si -4081]]}
   [0x0f 0xbd 0x85 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/di]]}
   [0x0f 0xbd 0x85 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/di 4080]]}
   [0x0f 0xbd 0x85 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/di -4081]]}
   [0x0f 0xbd 0x86 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp]]}
   [0x0f 0xbd 0x86 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp 4080]]}
   [0x0f 0xbd 0x86 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bp -4081]]}
   [0x0f 0xbd 0x87 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx]]}
   [0x0f 0xbd 0x87 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx 4080]]}
   [0x0f 0xbd 0x87 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/ax [::r/bx -4081]]}
   [0x0f 0xbd 0x88 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x0f 0xbd 0x88 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/si 4080]]}
   [0x0f 0xbd 0x88 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/si -4081]]}
   [0x0f 0xbd 0x89 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x0f 0xbd 0x89 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/di 4080]]}
   [0x0f 0xbd 0x89 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx ::r/di -4081]]}
   [0x0f 0xbd 0x8a 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x0f 0xbd 0x8a 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/si 4080]]}
   [0x0f 0xbd 0x8a 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/si -4081]]}
   [0x0f 0xbd 0x8b 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x0f 0xbd 0x8b 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/di 4080]]}
   [0x0f 0xbd 0x8b 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp ::r/di -4081]]}
   [0x0f 0xbd 0x8c 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/si]]}
   [0x0f 0xbd 0x8c 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/si 4080]]}
   [0x0f 0xbd 0x8c 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/si -4081]]}
   [0x0f 0xbd 0x8d 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/di]]}
   [0x0f 0xbd 0x8d 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/di 4080]]}
   [0x0f 0xbd 0x8d 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/di -4081]]}
   [0x0f 0xbd 0x8e 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp]]}
   [0x0f 0xbd 0x8e 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp 4080]]}
   [0x0f 0xbd 0x8e 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bp -4081]]}
   [0x0f 0xbd 0x8f 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx]]}
   [0x0f 0xbd 0x8f 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx 4080]]}
   [0x0f 0xbd 0x8f 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/cx [::r/bx -4081]]}
   [0x0f 0xbd 0x90 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x0f 0xbd 0x90 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/si 4080]]}
   [0x0f 0xbd 0x90 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/si -4081]]}
   [0x0f 0xbd 0x91 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x0f 0xbd 0x91 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/di 4080]]}
   [0x0f 0xbd 0x91 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx ::r/di -4081]]}
   [0x0f 0xbd 0x92 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x0f 0xbd 0x92 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/si 4080]]}
   [0x0f 0xbd 0x92 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/si -4081]]}
   [0x0f 0xbd 0x93 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x0f 0xbd 0x93 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/di 4080]]}
   [0x0f 0xbd 0x93 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp ::r/di -4081]]}
   [0x0f 0xbd 0x94 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/si]]}
   [0x0f 0xbd 0x94 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/si 4080]]}
   [0x0f 0xbd 0x94 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/si -4081]]}
   [0x0f 0xbd 0x95 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/di]]}
   [0x0f 0xbd 0x95 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/di 4080]]}
   [0x0f 0xbd 0x95 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/di -4081]]}
   [0x0f 0xbd 0x96 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp]]}
   [0x0f 0xbd 0x96 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp 4080]]}
   [0x0f 0xbd 0x96 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bp -4081]]}
   [0x0f 0xbd 0x97 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx]]}
   [0x0f 0xbd 0x97 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx 4080]]}
   [0x0f 0xbd 0x97 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/dx [::r/bx -4081]]}
   [0x0f 0xbd 0x98 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x0f 0xbd 0x98 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/si 4080]]}
   [0x0f 0xbd 0x98 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/si -4081]]}
   [0x0f 0xbd 0x99 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x0f 0xbd 0x99 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/di 4080]]}
   [0x0f 0xbd 0x99 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx ::r/di -4081]]}
   [0x0f 0xbd 0x9a 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x0f 0xbd 0x9a 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/si 4080]]}
   [0x0f 0xbd 0x9a 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/si -4081]]}
   [0x0f 0xbd 0x9b 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x0f 0xbd 0x9b 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/di 4080]]}
   [0x0f 0xbd 0x9b 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp ::r/di -4081]]}
   [0x0f 0xbd 0x9c 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/si]]}
   [0x0f 0xbd 0x9c 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/si 4080]]}
   [0x0f 0xbd 0x9c 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/si -4081]]}
   [0x0f 0xbd 0x9d 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/di]]}
   [0x0f 0xbd 0x9d 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/di 4080]]}
   [0x0f 0xbd 0x9d 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/di -4081]]}
   [0x0f 0xbd 0x9e 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp]]}
   [0x0f 0xbd 0x9e 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp 4080]]}
   [0x0f 0xbd 0x9e 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bp -4081]]}
   [0x0f 0xbd 0x9f 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx]]}
   [0x0f 0xbd 0x9f 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx 4080]]}
   [0x0f 0xbd 0x9f 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bx [::r/bx -4081]]}
   [0x0f 0xbd 0xa0 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x0f 0xbd 0xa0 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/si 4080]]}
   [0x0f 0xbd 0xa0 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/si -4081]]}
   [0x0f 0xbd 0xa1 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x0f 0xbd 0xa1 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/di 4080]]}
   [0x0f 0xbd 0xa1 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx ::r/di -4081]]}
   [0x0f 0xbd 0xa2 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x0f 0xbd 0xa2 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/si 4080]]}
   [0x0f 0xbd 0xa2 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/si -4081]]}
   [0x0f 0xbd 0xa3 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x0f 0xbd 0xa3 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/di 4080]]}
   [0x0f 0xbd 0xa3 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp ::r/di -4081]]}
   [0x0f 0xbd 0xa4 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/si]]}
   [0x0f 0xbd 0xa4 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/si 4080]]}
   [0x0f 0xbd 0xa4 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/si -4081]]}
   [0x0f 0xbd 0xa5 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/di]]}
   [0x0f 0xbd 0xa5 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/di 4080]]}
   [0x0f 0xbd 0xa5 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/di -4081]]}
   [0x0f 0xbd 0xa6 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp]]}
   [0x0f 0xbd 0xa6 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp 4080]]}
   [0x0f 0xbd 0xa6 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bp -4081]]}
   [0x0f 0xbd 0xa7 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx]]}
   [0x0f 0xbd 0xa7 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx 4080]]}
   [0x0f 0xbd 0xa7 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/sp [::r/bx -4081]]}
   [0x0f 0xbd 0xa8 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x0f 0xbd 0xa8 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/si 4080]]}
   [0x0f 0xbd 0xa8 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/si -4081]]}
   [0x0f 0xbd 0xa9 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x0f 0xbd 0xa9 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/di 4080]]}
   [0x0f 0xbd 0xa9 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx ::r/di -4081]]}
   [0x0f 0xbd 0xaa 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x0f 0xbd 0xaa 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/si 4080]]}
   [0x0f 0xbd 0xaa 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/si -4081]]}
   [0x0f 0xbd 0xab 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x0f 0xbd 0xab 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/di 4080]]}
   [0x0f 0xbd 0xab 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp ::r/di -4081]]}
   [0x0f 0xbd 0xac 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/si]]}
   [0x0f 0xbd 0xac 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/si 4080]]}
   [0x0f 0xbd 0xac 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/si -4081]]}
   [0x0f 0xbd 0xad 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/di]]}
   [0x0f 0xbd 0xad 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/di 4080]]}
   [0x0f 0xbd 0xad 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/di -4081]]}
   [0x0f 0xbd 0xae 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp]]}
   [0x0f 0xbd 0xae 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp 4080]]}
   [0x0f 0xbd 0xae 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bp -4081]]}
   [0x0f 0xbd 0xaf 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx]]}
   [0x0f 0xbd 0xaf 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx 4080]]}
   [0x0f 0xbd 0xaf 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/bp [::r/bx -4081]]}
   [0x0f 0xbd 0xb0 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x0f 0xbd 0xb0 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/si 4080]]}
   [0x0f 0xbd 0xb0 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/si -4081]]}
   [0x0f 0xbd 0xb1 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x0f 0xbd 0xb1 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/di 4080]]}
   [0x0f 0xbd 0xb1 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx ::r/di -4081]]}
   [0x0f 0xbd 0xb2 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x0f 0xbd 0xb2 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/si 4080]]}
   [0x0f 0xbd 0xb2 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/si -4081]]}
   [0x0f 0xbd 0xb3 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x0f 0xbd 0xb3 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/di 4080]]}
   [0x0f 0xbd 0xb3 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp ::r/di -4081]]}
   [0x0f 0xbd 0xb4 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/si]]}
   [0x0f 0xbd 0xb4 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/si 4080]]}
   [0x0f 0xbd 0xb4 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/si -4081]]}
   [0x0f 0xbd 0xb5 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/di]]}
   [0x0f 0xbd 0xb5 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/di 4080]]}
   [0x0f 0xbd 0xb5 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/di -4081]]}
   [0x0f 0xbd 0xb6 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp]]}
   [0x0f 0xbd 0xb6 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp 4080]]}
   [0x0f 0xbd 0xb6 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bp -4081]]}
   [0x0f 0xbd 0xb7 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx]]}
   [0x0f 0xbd 0xb7 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx 4080]]}
   [0x0f 0xbd 0xb7 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si [::r/bx -4081]]}
   [0x0f 0xbd 0xb8 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x0f 0xbd 0xb8 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/si 4080]]}
   [0x0f 0xbd 0xb8 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/si -4081]]}
   [0x0f 0xbd 0xb9 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x0f 0xbd 0xb9 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/di 4080]]}
   [0x0f 0xbd 0xb9 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx ::r/di -4081]]}
   [0x0f 0xbd 0xba 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x0f 0xbd 0xba 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/si 4080]]}
   [0x0f 0xbd 0xba 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/si -4081]]}
   [0x0f 0xbd 0xbb 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x0f 0xbd 0xbb 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/di 4080]]}
   [0x0f 0xbd 0xbb 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp ::r/di -4081]]}
   [0x0f 0xbd 0xbc 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/si]]}
   [0x0f 0xbd 0xbc 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/si 4080]]}
   [0x0f 0xbd 0xbc 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/si -4081]]}
   [0x0f 0xbd 0xbd 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/di]]}
   [0x0f 0xbd 0xbd 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/di 4080]]}
   [0x0f 0xbd 0xbd 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/di -4081]]}
   [0x0f 0xbd 0xbe 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp]]}
   [0x0f 0xbd 0xbe 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp 4080]]}
   [0x0f 0xbd 0xbe 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bp -4081]]}
   [0x0f 0xbd 0xbf 0x00 0x00] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx]]}
   [0x0f 0xbd 0xbf 0xf0 0x0f] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx 4080]]}
   [0x0f 0xbd 0xbf 0x0f 0xf0] {::i/tag ::i/bsr, ::i/args [::r/di [::r/bx -4081]]}
   [0x0f 0xbd 0xc0] {::i/tag ::i/bsr, ::i/args [::r/ax ::r/ax]}
   [0x0f 0xbd 0xc1] {::i/tag ::i/bsr, ::i/args [::r/ax ::r/cx]}
   [0x0f 0xbd 0xc2] {::i/tag ::i/bsr, ::i/args [::r/ax ::r/dx]}
   [0x0f 0xbd 0xc3] {::i/tag ::i/bsr, ::i/args [::r/ax ::r/bx]}
   [0x0f 0xbd 0xc4] {::i/tag ::i/bsr, ::i/args [::r/ax ::r/sp]}
   [0x0f 0xbd 0xc5] {::i/tag ::i/bsr, ::i/args [::r/ax ::r/bp]}
   [0x0f 0xbd 0xc6] {::i/tag ::i/bsr, ::i/args [::r/ax ::r/si]}
   [0x0f 0xbd 0xc7] {::i/tag ::i/bsr, ::i/args [::r/ax ::r/di]}
   [0x0f 0xbd 0xc8] {::i/tag ::i/bsr, ::i/args [::r/cx ::r/ax]}
   [0x0f 0xbd 0xc9] {::i/tag ::i/bsr, ::i/args [::r/cx ::r/cx]}
   [0x0f 0xbd 0xca] {::i/tag ::i/bsr, ::i/args [::r/cx ::r/dx]}
   [0x0f 0xbd 0xcb] {::i/tag ::i/bsr, ::i/args [::r/cx ::r/bx]}
   [0x0f 0xbd 0xcc] {::i/tag ::i/bsr, ::i/args [::r/cx ::r/sp]}
   [0x0f 0xbd 0xcd] {::i/tag ::i/bsr, ::i/args [::r/cx ::r/bp]}
   [0x0f 0xbd 0xce] {::i/tag ::i/bsr, ::i/args [::r/cx ::r/si]}
   [0x0f 0xbd 0xcf] {::i/tag ::i/bsr, ::i/args [::r/cx ::r/di]}
   [0x0f 0xbd 0xd0] {::i/tag ::i/bsr, ::i/args [::r/dx ::r/ax]}
   [0x0f 0xbd 0xd1] {::i/tag ::i/bsr, ::i/args [::r/dx ::r/cx]}
   [0x0f 0xbd 0xd2] {::i/tag ::i/bsr, ::i/args [::r/dx ::r/dx]}
   [0x0f 0xbd 0xd3] {::i/tag ::i/bsr, ::i/args [::r/dx ::r/bx]}
   [0x0f 0xbd 0xd4] {::i/tag ::i/bsr, ::i/args [::r/dx ::r/sp]}
   [0x0f 0xbd 0xd5] {::i/tag ::i/bsr, ::i/args [::r/dx ::r/bp]}
   [0x0f 0xbd 0xd6] {::i/tag ::i/bsr, ::i/args [::r/dx ::r/si]}
   [0x0f 0xbd 0xd7] {::i/tag ::i/bsr, ::i/args [::r/dx ::r/di]}
   [0x0f 0xbd 0xd8] {::i/tag ::i/bsr, ::i/args [::r/bx ::r/ax]}
   [0x0f 0xbd 0xd9] {::i/tag ::i/bsr, ::i/args [::r/bx ::r/cx]}
   [0x0f 0xbd 0xda] {::i/tag ::i/bsr, ::i/args [::r/bx ::r/dx]}
   [0x0f 0xbd 0xdb] {::i/tag ::i/bsr, ::i/args [::r/bx ::r/bx]}
   [0x0f 0xbd 0xdc] {::i/tag ::i/bsr, ::i/args [::r/bx ::r/sp]}
   [0x0f 0xbd 0xdd] {::i/tag ::i/bsr, ::i/args [::r/bx ::r/bp]}
   [0x0f 0xbd 0xde] {::i/tag ::i/bsr, ::i/args [::r/bx ::r/si]}
   [0x0f 0xbd 0xdf] {::i/tag ::i/bsr, ::i/args [::r/bx ::r/di]}
   [0x0f 0xbd 0xe0] {::i/tag ::i/bsr, ::i/args [::r/sp ::r/ax]}
   [0x0f 0xbd 0xe1] {::i/tag ::i/bsr, ::i/args [::r/sp ::r/cx]}
   [0x0f 0xbd 0xe2] {::i/tag ::i/bsr, ::i/args [::r/sp ::r/dx]}
   [0x0f 0xbd 0xe3] {::i/tag ::i/bsr, ::i/args [::r/sp ::r/bx]}
   [0x0f 0xbd 0xe4] {::i/tag ::i/bsr, ::i/args [::r/sp ::r/sp]}
   [0x0f 0xbd 0xe5] {::i/tag ::i/bsr, ::i/args [::r/sp ::r/bp]}
   [0x0f 0xbd 0xe6] {::i/tag ::i/bsr, ::i/args [::r/sp ::r/si]}
   [0x0f 0xbd 0xe7] {::i/tag ::i/bsr, ::i/args [::r/sp ::r/di]}
   [0x0f 0xbd 0xe8] {::i/tag ::i/bsr, ::i/args [::r/bp ::r/ax]}
   [0x0f 0xbd 0xe9] {::i/tag ::i/bsr, ::i/args [::r/bp ::r/cx]}
   [0x0f 0xbd 0xea] {::i/tag ::i/bsr, ::i/args [::r/bp ::r/dx]}
   [0x0f 0xbd 0xeb] {::i/tag ::i/bsr, ::i/args [::r/bp ::r/bx]}
   [0x0f 0xbd 0xec] {::i/tag ::i/bsr, ::i/args [::r/bp ::r/sp]}
   [0x0f 0xbd 0xed] {::i/tag ::i/bsr, ::i/args [::r/bp ::r/bp]}
   [0x0f 0xbd 0xee] {::i/tag ::i/bsr, ::i/args [::r/bp ::r/si]}
   [0x0f 0xbd 0xef] {::i/tag ::i/bsr, ::i/args [::r/bp ::r/di]}
   [0x0f 0xbd 0xf0] {::i/tag ::i/bsr, ::i/args [::r/si ::r/ax]}
   [0x0f 0xbd 0xf1] {::i/tag ::i/bsr, ::i/args [::r/si ::r/cx]}
   [0x0f 0xbd 0xf2] {::i/tag ::i/bsr, ::i/args [::r/si ::r/dx]}
   [0x0f 0xbd 0xf3] {::i/tag ::i/bsr, ::i/args [::r/si ::r/bx]}
   [0x0f 0xbd 0xf4] {::i/tag ::i/bsr, ::i/args [::r/si ::r/sp]}
   [0x0f 0xbd 0xf5] {::i/tag ::i/bsr, ::i/args [::r/si ::r/bp]}
   [0x0f 0xbd 0xf6] {::i/tag ::i/bsr, ::i/args [::r/si ::r/si]}
   [0x0f 0xbd 0xf7] {::i/tag ::i/bsr, ::i/args [::r/si ::r/di]}
   [0x0f 0xbd 0xf8] {::i/tag ::i/bsr, ::i/args [::r/di ::r/ax]}
   [0x0f 0xbd 0xf9] {::i/tag ::i/bsr, ::i/args [::r/di ::r/cx]}
   [0x0f 0xbd 0xfa] {::i/tag ::i/bsr, ::i/args [::r/di ::r/dx]}
   [0x0f 0xbd 0xfb] {::i/tag ::i/bsr, ::i/args [::r/di ::r/bx]}
   [0x0f 0xbd 0xfc] {::i/tag ::i/bsr, ::i/args [::r/di ::r/sp]}
   [0x0f 0xbd 0xfd] {::i/tag ::i/bsr, ::i/args [::r/di ::r/bp]}
   [0x0f 0xbd 0xfe] {::i/tag ::i/bsr, ::i/args [::r/di ::r/si]}
   [0x0f 0xbd 0xff] {::i/tag ::i/bsr, ::i/args [::r/di ::r/di]}})


(def decode-examples-movsx
  {[0x0f 0xbe 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x01] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x02] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x03] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x04] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x05] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x06 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xbe 0x06 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xbe 0x07] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x08] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x09] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x0a] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x0b] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x0c] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x0d] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x0e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xbe 0x0e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xbe 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x10] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x11] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x12] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x13] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x14] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x15] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x16 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xbe 0x16 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xbe 0x17] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x18] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x19] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x1a] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x1b] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x1c] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x1d] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x1e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xbe 0x1e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xbe 0x1f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x20] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x21] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x22] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x23] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x24] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x25] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x26 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xbe 0x26 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xbe 0x27] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x28] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x29] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x2a] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x2b] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x2c] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x2d] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x2e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xbe 0x2e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xbe 0x2f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x30] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x31] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x32] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x33] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x34] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x35] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x36 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xbe 0x36 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xbe 0x37] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x38] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x39] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x3a] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x3b] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x3c] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x3d] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x3e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [0x0ff0]], ::i/type ::i/byte}
   [0x0f 0xbe 0x3e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [0xf00f]], ::i/type ::i/byte}
   [0x0f 0xbe 0x3f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x40 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x40 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x40 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x41 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x41 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x41 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x42 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x42 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x42 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x43 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x43 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x43 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x44 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x44 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x44 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x45 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x45 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x45 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x46 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x46 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x46 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x47 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x47 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x47 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x48 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x48 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x48 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x49 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x49 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x49 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4a 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4a 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4a 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4b 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4b 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4b 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4c 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4c 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4c 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4d 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4d 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4d 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4e 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4e 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4e 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4f 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x4f 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x50 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x50 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x50 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x51 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x51 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x51 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x52 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x52 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x52 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x53 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x53 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x53 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x54 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x54 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x54 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x55 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x55 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x55 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x56 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x56 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x56 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x57 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x57 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x57 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x58 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x58 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x58 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x59 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x59 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x59 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5a 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5a 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5a 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5b 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5b 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5b 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5c 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5c 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5c 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5d 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5d 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5d 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5e 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5e 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5e 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5f 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x5f 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x60 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x60 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x60 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x61 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x61 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x61 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x62 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x62 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x62 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x63 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x63 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x63 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x64 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x64 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x64 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x65 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x65 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x65 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x66 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x66 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x66 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x67 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x67 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x67 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x68 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x68 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x68 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x69 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x69 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x69 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6a 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6a 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6a 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6b 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6b 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6b 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6c 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6c 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6c 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6d 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6d 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6d 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6e 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6e 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6e 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6f 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x6f 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x70 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x70 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x70 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x71 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x71 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x71 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x72 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x72 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x72 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x73 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x73 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x73 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x74 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x74 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x74 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x75 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x75 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x75 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x76 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x76 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x76 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x77 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x77 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x77 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x78 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x78 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x78 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x79 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x79 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x79 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7a 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7a 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7a 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7b 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7b 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7b 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7c 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7c 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7c 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7d 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7d 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7d 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7e 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7e 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7e 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7f 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx -16]], ::i/type ::i/byte}
   [0x0f 0xbe 0x7f 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx 15]], ::i/type ::i/byte}
   [0x0f 0xbe 0x80 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x80 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x80 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x81 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x81 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x81 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x82 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x82 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x82 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x83 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x83 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x83 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x84 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x84 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x84 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x85 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x85 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x85 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x86 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x86 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x86 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x87 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x87 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x87 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x88 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x88 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x88 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x89 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x89 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x89 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8a 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8a 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8a 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8b 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8b 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8b 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8c 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8c 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8c 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8d 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8d 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8d 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8e 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8f 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8f 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x8f 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x90 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x90 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x90 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x91 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x91 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x91 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x92 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x92 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x92 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x93 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x93 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x93 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x94 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x94 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x94 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x95 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x95 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x95 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x96 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x96 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x96 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x97 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x97 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x97 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x98 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x98 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x98 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x99 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x99 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x99 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9a 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9a 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9a 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9b 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9b 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9b 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9c 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9c 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9c 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9d 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9d 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9d 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9e 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9f 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9f 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0x9f 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa0 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa0 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa0 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa1 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa1 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa1 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa2 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa2 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa2 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa3 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa3 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa3 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa4 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa4 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa4 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa5 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa5 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa5 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa6 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa6 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa6 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa7 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa7 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa7 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa8 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa8 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa8 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa9 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa9 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xa9 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xaa 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xaa 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xaa 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xab 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xab 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xab 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xac 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xac 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xac 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xad 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xad 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xad 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xae 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0xae 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xae 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xaf 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0xaf 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xaf 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb0 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb0 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb0 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb1 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb1 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb1 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb2 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb2 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb2 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb3 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb3 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb3 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb4 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb4 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb4 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb5 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb5 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb5 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb6 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb6 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb6 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb7 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb7 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb7 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb8 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb8 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb8 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb9 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb9 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xb9 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xba 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xba 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xba 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbb 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbb 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbb 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbc 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbc 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbc 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbd 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbd 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbd 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbe 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbe 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbe 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbf 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbf 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx 4080]], ::i/type ::i/byte}
   [0x0f 0xbe 0xbf 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx -4081]], ::i/type ::i/byte}
   [0x0f 0xbe 0xc0] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/al]}
   [0x0f 0xbe 0xc1] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/cl]}
   [0x0f 0xbe 0xc2] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/dl]}
   [0x0f 0xbe 0xc3] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/bl]}
   [0x0f 0xbe 0xc4] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/ah]}
   [0x0f 0xbe 0xc5] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/ch]}
   [0x0f 0xbe 0xc6] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/dh]}
   [0x0f 0xbe 0xc7] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/bh]}
   [0x0f 0xbe 0xc8] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/al]}
   [0x0f 0xbe 0xc9] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/cl]}
   [0x0f 0xbe 0xca] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/dl]}
   [0x0f 0xbe 0xcb] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/bl]}
   [0x0f 0xbe 0xcc] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/ah]}
   [0x0f 0xbe 0xcd] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/ch]}
   [0x0f 0xbe 0xce] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/dh]}
   [0x0f 0xbe 0xcf] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/bh]}
   [0x0f 0xbe 0xd0] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/al]}
   [0x0f 0xbe 0xd1] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/cl]}
   [0x0f 0xbe 0xd2] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/dl]}
   [0x0f 0xbe 0xd3] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/bl]}
   [0x0f 0xbe 0xd4] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/ah]}
   [0x0f 0xbe 0xd5] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/ch]}
   [0x0f 0xbe 0xd6] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/dh]}
   [0x0f 0xbe 0xd7] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/bh]}
   [0x0f 0xbe 0xd8] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/al]}
   [0x0f 0xbe 0xd9] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/cl]}
   [0x0f 0xbe 0xda] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/dl]}
   [0x0f 0xbe 0xdb] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/bl]}
   [0x0f 0xbe 0xdc] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/ah]}
   [0x0f 0xbe 0xdd] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/ch]}
   [0x0f 0xbe 0xde] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/dh]}
   [0x0f 0xbe 0xdf] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/bh]}
   [0x0f 0xbe 0xe0] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/al]}
   [0x0f 0xbe 0xe1] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/cl]}
   [0x0f 0xbe 0xe2] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/dl]}
   [0x0f 0xbe 0xe3] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/bl]}
   [0x0f 0xbe 0xe4] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/ah]}
   [0x0f 0xbe 0xe5] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/ch]}
   [0x0f 0xbe 0xe6] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/dh]}
   [0x0f 0xbe 0xe7] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/bh]}
   [0x0f 0xbe 0xe8] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/al]}
   [0x0f 0xbe 0xe9] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/cl]}
   [0x0f 0xbe 0xea] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/dl]}
   [0x0f 0xbe 0xeb] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/bl]}
   [0x0f 0xbe 0xec] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/ah]}
   [0x0f 0xbe 0xed] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/ch]}
   [0x0f 0xbe 0xee] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/dh]}
   [0x0f 0xbe 0xef] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/bh]}
   [0x0f 0xbe 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si ::r/al]}
   [0x0f 0xbe 0xf1] {::i/tag ::i/movsx, ::i/args [::r/si ::r/cl]}
   [0x0f 0xbe 0xf2] {::i/tag ::i/movsx, ::i/args [::r/si ::r/dl]}
   [0x0f 0xbe 0xf3] {::i/tag ::i/movsx, ::i/args [::r/si ::r/bl]}
   [0x0f 0xbe 0xf4] {::i/tag ::i/movsx, ::i/args [::r/si ::r/ah]}
   [0x0f 0xbe 0xf5] {::i/tag ::i/movsx, ::i/args [::r/si ::r/ch]}
   [0x0f 0xbe 0xf6] {::i/tag ::i/movsx, ::i/args [::r/si ::r/dh]}
   [0x0f 0xbe 0xf7] {::i/tag ::i/movsx, ::i/args [::r/si ::r/bh]}
   [0x0f 0xbe 0xf8] {::i/tag ::i/movsx, ::i/args [::r/di ::r/al]}
   [0x0f 0xbe 0xf9] {::i/tag ::i/movsx, ::i/args [::r/di ::r/cl]}
   [0x0f 0xbe 0xfa] {::i/tag ::i/movsx, ::i/args [::r/di ::r/dl]}
   [0x0f 0xbe 0xfb] {::i/tag ::i/movsx, ::i/args [::r/di ::r/bl]}
   [0x0f 0xbe 0xfc] {::i/tag ::i/movsx, ::i/args [::r/di ::r/ah]}
   [0x0f 0xbe 0xfd] {::i/tag ::i/movsx, ::i/args [::r/di ::r/ch]}
   [0x0f 0xbe 0xfe] {::i/tag ::i/movsx, ::i/args [::r/di ::r/dh]}
   [0x0f 0xbe 0xff] {::i/tag ::i/movsx, ::i/args [::r/di ::r/bh]}
   [0x0f 0xbf 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x01] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x02] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x03] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x04] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x05] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x06 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xbf 0x06 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [0xf00f]], ::i/type ::i/word}
   [0x0f 0xbf 0x07] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x08] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x09] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x0a] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x0b] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x0c] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x0d] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x0e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xbf 0x0e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [0xf00f]], ::i/type ::i/word}
   [0x0f 0xbf 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x10] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x11] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x12] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x13] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x14] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x15] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x16 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xbf 0x16 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [0xf00f]], ::i/type ::i/word}
   [0x0f 0xbf 0x17] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x18] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x19] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x1a] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x1b] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x1c] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x1d] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x1e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xbf 0x1e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [0xf00f]], ::i/type ::i/word}
   [0x0f 0xbf 0x1f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x20] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x21] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x22] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x23] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x24] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x25] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x26 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xbf 0x26 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [0xf00f]], ::i/type ::i/word}
   [0x0f 0xbf 0x27] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x28] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x29] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x2a] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x2b] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x2c] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x2d] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x2e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xbf 0x2e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [0xf00f]], ::i/type ::i/word}
   [0x0f 0xbf 0x2f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x30] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x31] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x32] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x33] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x34] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x35] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x36 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xbf 0x36 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [0xf00f]], ::i/type ::i/word}
   [0x0f 0xbf 0x37] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x38] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x39] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x3a] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x3b] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x3c] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x3d] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x3e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [0x0ff0]], ::i/type ::i/word}
   [0x0f 0xbf 0x3e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [0xf00f]], ::i/type ::i/word}
   [0x0f 0xbf 0x3f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x40 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x40 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x40 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x41 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x41 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x41 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x42 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x42 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x42 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x43 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x43 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x43 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x44 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x44 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x44 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x45 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x45 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x45 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x46 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x46 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x46 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x47 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x47 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x47 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x48 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x48 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x48 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x49 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x49 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x49 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x4a 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x4a 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x4a 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x4b 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x4b 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x4b 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x4c 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x4c 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x4c 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x4d 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x4d 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x4d 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x4e 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x4e 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x4e 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x4f 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x4f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x4f 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x50 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x50 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x50 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x51 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x51 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x51 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x52 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x52 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x52 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x53 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x53 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x53 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x54 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x54 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x54 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x55 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x55 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x55 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x56 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x56 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x56 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x57 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x57 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x57 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x58 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x58 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x58 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x59 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x59 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x59 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x5a 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x5a 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x5a 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x5b 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x5b 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x5b 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x5c 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x5c 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x5c 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x5d 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x5d 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x5d 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x5e 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x5e 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x5e 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x5f 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x5f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x5f 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x60 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x60 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x60 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x61 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x61 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x61 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x62 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x62 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x62 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x63 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x63 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x63 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x64 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x64 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x64 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x65 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x65 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x65 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x66 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x66 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x66 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x67 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x67 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x67 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x68 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x68 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x68 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x69 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x69 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x69 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x6a 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x6a 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x6a 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x6b 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x6b 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x6b 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x6c 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x6c 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x6c 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x6d 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x6d 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x6d 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x6e 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x6e 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x6e 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x6f 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x6f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x6f 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x70 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x70 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x70 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x71 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x71 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x71 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x72 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x72 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x72 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x73 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x73 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x73 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x74 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x74 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x74 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x75 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x75 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x75 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x76 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x76 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x76 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x77 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x77 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x77 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x78 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x78 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x78 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x79 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x79 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x79 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x7a 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x7a 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x7a 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x7b 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x7b 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x7b 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x7c 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x7c 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x7c 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x7d 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x7d 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x7d 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x7e 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x7e 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x7e 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x7f 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x7f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx -16]], ::i/type ::i/word}
   [0x0f 0xbf 0x7f 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx 15]], ::i/type ::i/word}
   [0x0f 0xbf 0x80 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x80 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x80 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x81 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x81 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x81 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x82 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x82 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x82 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x83 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x83 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x83 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x84 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x84 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x84 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x85 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x85 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x85 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x86 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x86 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x86 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x87 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x87 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x87 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/ax [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x88 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x88 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x88 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x89 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x89 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x89 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x8a 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x8a 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x8a 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x8b 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x8b 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x8b 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x8c 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x8c 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x8c 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x8d 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x8d 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x8d 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x8e 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x8e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x8e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x8f 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x8f 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x8f 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/cx [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x90 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x90 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x90 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x91 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x91 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x91 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x92 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x92 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x92 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x93 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x93 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x93 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x94 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x94 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x94 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x95 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x95 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x95 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x96 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x96 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x96 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x97 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x97 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x97 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/dx [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x98 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x98 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x98 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x99 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x99 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x99 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x9a 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x9a 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x9a 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x9b 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x9b 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x9b 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x9c 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0x9c 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x9c 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x9d 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0x9d 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x9d 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x9e 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0x9e 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x9e 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0x9f 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0x9f 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0x9f 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bx [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xa0 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xa0 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xa0 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xa1 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xa1 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xa1 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xa2 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xa2 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xa2 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xa3 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xa3 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xa3 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xa4 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xa4 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xa4 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xa5 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xa5 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xa5 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xa6 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0xa6 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xa6 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xa7 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0xa7 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xa7 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/sp [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xa8 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xa8 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xa8 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xa9 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xa9 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xa9 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xaa 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xaa 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xaa 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xab 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xab 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xab 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xac 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xac 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xac 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xad 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xad 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xad 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xae 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0xae 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xae 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xaf 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0xaf 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xaf 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/bp [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xb0 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xb0 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xb0 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xb1 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xb1 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xb1 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xb2 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xb2 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xb2 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xb3 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xb3 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xb3 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xb4 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xb4 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xb4 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xb5 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xb5 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xb5 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xb6 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0xb6 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xb6 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xb7 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0xb7 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xb7 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xb8 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xb8 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xb8 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xb9 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xb9 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xb9 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xba 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xba 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xba 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xbb 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xbb 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xbb 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xbc 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si]], ::i/type ::i/word}
   [0x0f 0xbf 0xbc 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xbc 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/si -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xbd 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di]], ::i/type ::i/word}
   [0x0f 0xbf 0xbd 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xbd 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/di -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xbe 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp]], ::i/type ::i/word}
   [0x0f 0xbf 0xbe 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xbe 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bp -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xbf 0x00 0x00] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx]], ::i/type ::i/word}
   [0x0f 0xbf 0xbf 0xf0 0x0f] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx 4080]], ::i/type ::i/word}
   [0x0f 0xbf 0xbf 0x0f 0xf0] {::i/tag ::i/movsx, ::i/args [::r/di [::r/bx -4081]], ::i/type ::i/word}
   [0x0f 0xbf 0xc0] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/ax]}
   [0x0f 0xbf 0xc1] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/cx]}
   [0x0f 0xbf 0xc2] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/dx]}
   [0x0f 0xbf 0xc3] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/bx]}
   [0x0f 0xbf 0xc4] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/sp]}
   [0x0f 0xbf 0xc5] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/bp]}
   [0x0f 0xbf 0xc6] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/si]}
   [0x0f 0xbf 0xc7] {::i/tag ::i/movsx, ::i/args [::r/ax ::r/di]}
   [0x0f 0xbf 0xc8] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/ax]}
   [0x0f 0xbf 0xc9] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/cx]}
   [0x0f 0xbf 0xca] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/dx]}
   [0x0f 0xbf 0xcb] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/bx]}
   [0x0f 0xbf 0xcc] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/sp]}
   [0x0f 0xbf 0xcd] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/bp]}
   [0x0f 0xbf 0xce] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/si]}
   [0x0f 0xbf 0xcf] {::i/tag ::i/movsx, ::i/args [::r/cx ::r/di]}
   [0x0f 0xbf 0xd0] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/ax]}
   [0x0f 0xbf 0xd1] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/cx]}
   [0x0f 0xbf 0xd2] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/dx]}
   [0x0f 0xbf 0xd3] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/bx]}
   [0x0f 0xbf 0xd4] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/sp]}
   [0x0f 0xbf 0xd5] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/bp]}
   [0x0f 0xbf 0xd6] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/si]}
   [0x0f 0xbf 0xd7] {::i/tag ::i/movsx, ::i/args [::r/dx ::r/di]}
   [0x0f 0xbf 0xd8] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/ax]}
   [0x0f 0xbf 0xd9] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/cx]}
   [0x0f 0xbf 0xda] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/dx]}
   [0x0f 0xbf 0xdb] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/bx]}
   [0x0f 0xbf 0xdc] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/sp]}
   [0x0f 0xbf 0xdd] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/bp]}
   [0x0f 0xbf 0xde] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/si]}
   [0x0f 0xbf 0xdf] {::i/tag ::i/movsx, ::i/args [::r/bx ::r/di]}
   [0x0f 0xbf 0xe0] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/ax]}
   [0x0f 0xbf 0xe1] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/cx]}
   [0x0f 0xbf 0xe2] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/dx]}
   [0x0f 0xbf 0xe3] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/bx]}
   [0x0f 0xbf 0xe4] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/sp]}
   [0x0f 0xbf 0xe5] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/bp]}
   [0x0f 0xbf 0xe6] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/si]}
   [0x0f 0xbf 0xe7] {::i/tag ::i/movsx, ::i/args [::r/sp ::r/di]}
   [0x0f 0xbf 0xe8] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/ax]}
   [0x0f 0xbf 0xe9] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/cx]}
   [0x0f 0xbf 0xea] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/dx]}
   [0x0f 0xbf 0xeb] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/bx]}
   [0x0f 0xbf 0xec] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/sp]}
   [0x0f 0xbf 0xed] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/bp]}
   [0x0f 0xbf 0xee] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/si]}
   [0x0f 0xbf 0xef] {::i/tag ::i/movsx, ::i/args [::r/bp ::r/di]}
   [0x0f 0xbf 0xf0] {::i/tag ::i/movsx, ::i/args [::r/si ::r/ax]}
   [0x0f 0xbf 0xf1] {::i/tag ::i/movsx, ::i/args [::r/si ::r/cx]}
   [0x0f 0xbf 0xf2] {::i/tag ::i/movsx, ::i/args [::r/si ::r/dx]}
   [0x0f 0xbf 0xf3] {::i/tag ::i/movsx, ::i/args [::r/si ::r/bx]}
   [0x0f 0xbf 0xf4] {::i/tag ::i/movsx, ::i/args [::r/si ::r/sp]}
   [0x0f 0xbf 0xf5] {::i/tag ::i/movsx, ::i/args [::r/si ::r/bp]}
   [0x0f 0xbf 0xf6] {::i/tag ::i/movsx, ::i/args [::r/si ::r/si]}
   [0x0f 0xbf 0xf7] {::i/tag ::i/movsx, ::i/args [::r/si ::r/di]}
   [0x0f 0xbf 0xf8] {::i/tag ::i/movsx, ::i/args [::r/di ::r/ax]}
   [0x0f 0xbf 0xf9] {::i/tag ::i/movsx, ::i/args [::r/di ::r/cx]}
   [0x0f 0xbf 0xfa] {::i/tag ::i/movsx, ::i/args [::r/di ::r/dx]}
   [0x0f 0xbf 0xfb] {::i/tag ::i/movsx, ::i/args [::r/di ::r/bx]}
   [0x0f 0xbf 0xfc] {::i/tag ::i/movsx, ::i/args [::r/di ::r/sp]}
   [0x0f 0xbf 0xfd] {::i/tag ::i/movsx, ::i/args [::r/di ::r/bp]}
   [0x0f 0xbf 0xfe] {::i/tag ::i/movsx, ::i/args [::r/di ::r/si]}
   [0x0f 0xbf 0xff] {::i/tag ::i/movsx, ::i/args [::r/di ::r/di]}})


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
    (test nil [])
    (test nil nil)))
