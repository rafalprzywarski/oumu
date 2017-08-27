(ns oumu.cpu.instructions-test
  (:require [clojure.test :refer :all]
            [oumu.cpu.instructions :refer :all :as i]
            [oumu.cpu.registers :as r]))


(defn safe-merge [& maps]
  {:post [(= (count %) (apply + (map count maps)))]}
  (apply merge maps))


(def numeric-examples
  {[0 0x00] {::i/args [[::r/bx ::r/si] ::r/al]}
   [0 0x01] {::i/args [[::r/bx ::r/di] ::r/al]}
   [0 0x02] {::i/args [[::r/bp ::r/si] ::r/al]}
   [0 0x03] {::i/args [[::r/bp ::r/di] ::r/al]}
   [0 0x04] {::i/args [[::r/si] ::r/al]}
   [0 0x05] {::i/args [[::r/di] ::r/al]}
   [0 0x06 0xf0 0x0f] {::i/args [[0x0ff0] ::r/al]}
   [0 0x06 0x0f 0xf0] {::i/args [[0xf00f] ::r/al]}
   [0 0x07] {::i/args [[::r/bx] ::r/al]}
   [0 0x08] {::i/args [[::r/bx ::r/si] ::r/cl]}
   [0 0x09] {::i/args [[::r/bx ::r/di] ::r/cl]}
   [0 0x0a] {::i/args [[::r/bp ::r/si] ::r/cl]}
   [0 0x0b] {::i/args [[::r/bp ::r/di] ::r/cl]}
   [0 0x0c] {::i/args [[::r/si] ::r/cl]}
   [0 0x0d] {::i/args [[::r/di] ::r/cl]}
   [0 0x0e 0xf0 0x0f] {::i/args [[0x0ff0] ::r/cl]}
   [0 0x0e 0x0f 0xf0] {::i/args [[0xf00f] ::r/cl]}
   [0 0x0f] {::i/args [[::r/bx] ::r/cl]}
   [0 0x10] {::i/args [[::r/bx ::r/si] ::r/dl]}
   [0 0x11] {::i/args [[::r/bx ::r/di] ::r/dl]}
   [0 0x12] {::i/args [[::r/bp ::r/si] ::r/dl]}
   [0 0x13] {::i/args [[::r/bp ::r/di] ::r/dl]}
   [0 0x14] {::i/args [[::r/si] ::r/dl]}
   [0 0x15] {::i/args [[::r/di] ::r/dl]}
   [0 0x16 0xf0 0x0f] {::i/args [[0x0ff0] ::r/dl]}
   [0 0x16 0x0f 0xf0] {::i/args [[0xf00f] ::r/dl]}
   [0 0x17] {::i/args [[::r/bx] ::r/dl]}
   [0 0x18] {::i/args [[::r/bx ::r/si] ::r/bl]}
   [0 0x19] {::i/args [[::r/bx ::r/di] ::r/bl]}
   [0 0x1a] {::i/args [[::r/bp ::r/si] ::r/bl]}
   [0 0x1b] {::i/args [[::r/bp ::r/di] ::r/bl]}
   [0 0x1c] {::i/args [[::r/si] ::r/bl]}
   [0 0x1d] {::i/args [[::r/di] ::r/bl]}
   [0 0x1e 0xf0 0x0f] {::i/args [[0x0ff0] ::r/bl]}
   [0 0x1e 0x0f 0xf0] {::i/args [[0xf00f] ::r/bl]}
   [0 0x1f] {::i/args [[::r/bx] ::r/bl]}
   [0 0x20] {::i/args [[::r/bx ::r/si] ::r/ah]}
   [0 0x21] {::i/args [[::r/bx ::r/di] ::r/ah]}
   [0 0x22] {::i/args [[::r/bp ::r/si] ::r/ah]}
   [0 0x23] {::i/args [[::r/bp ::r/di] ::r/ah]}
   [0 0x24] {::i/args [[::r/si] ::r/ah]}
   [0 0x25] {::i/args [[::r/di] ::r/ah]}
   [0 0x26 0xf0 0x0f] {::i/args [[0x0ff0] ::r/ah]}
   [0 0x26 0x0f 0xf0] {::i/args [[0xf00f] ::r/ah]}
   [0 0x27] {::i/args [[::r/bx] ::r/ah]}
   [0 0x28] {::i/args [[::r/bx ::r/si] ::r/ch]}
   [0 0x29] {::i/args [[::r/bx ::r/di] ::r/ch]}
   [0 0x2a] {::i/args [[::r/bp ::r/si] ::r/ch]}
   [0 0x2b] {::i/args [[::r/bp ::r/di] ::r/ch]}
   [0 0x2c] {::i/args [[::r/si] ::r/ch]}
   [0 0x2d] {::i/args [[::r/di] ::r/ch]}
   [0 0x2e 0xf0 0x0f] {::i/args [[0x0ff0] ::r/ch]}
   [0 0x2e 0x0f 0xf0] {::i/args [[0xf00f] ::r/ch]}
   [0 0x2f] {::i/args [[::r/bx] ::r/ch]}
   [0 0x30] {::i/args [[::r/bx ::r/si] ::r/dh]}
   [0 0x31] {::i/args [[::r/bx ::r/di] ::r/dh]}
   [0 0x32] {::i/args [[::r/bp ::r/si] ::r/dh]}
   [0 0x33] {::i/args [[::r/bp ::r/di] ::r/dh]}
   [0 0x34] {::i/args [[::r/si] ::r/dh]}
   [0 0x35] {::i/args [[::r/di] ::r/dh]}
   [0 0x36 0xf0 0x0f] {::i/args [[0x0ff0] ::r/dh]}
   [0 0x36 0x0f 0xf0] {::i/args [[0xf00f] ::r/dh]}
   [0 0x37] {::i/args [[::r/bx] ::r/dh]}
   [0 0x38] {::i/args [[::r/bx ::r/si] ::r/bh]}
   [0 0x39] {::i/args [[::r/bx ::r/di] ::r/bh]}
   [0 0x3a] {::i/args [[::r/bp ::r/si] ::r/bh]}
   [0 0x3b] {::i/args [[::r/bp ::r/di] ::r/bh]}
   [0 0x3c] {::i/args [[::r/si] ::r/bh]}
   [0 0x3d] {::i/args [[::r/di] ::r/bh]}
   [0 0x3e 0xf0 0x0f] {::i/args [[0x0ff0] ::r/bh]}
   [0 0x3e 0x0f 0xf0] {::i/args [[0xf00f] ::r/bh]}
   [0 0x3f] {::i/args [[::r/bx] ::r/bh]}
   [0 0x40 0x00] {::i/args [[::r/bx ::r/si] ::r/al]}
   [0 0x40 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/al]}
   [0 0x40 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/al]}
   [0 0x41 0x00] {::i/args [[::r/bx ::r/di] ::r/al]}
   [0 0x41 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/al]}
   [0 0x41 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/al]}
   [0 0x42 0x00] {::i/args [[::r/bp ::r/si] ::r/al]}
   [0 0x42 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/al]}
   [0 0x42 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/al]}
   [0 0x43 0x00] {::i/args [[::r/bp ::r/di] ::r/al]}
   [0 0x43 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/al]}
   [0 0x43 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/al]}
   [0 0x44 0x00] {::i/args [[::r/si] ::r/al]}
   [0 0x44 0xf0] {::i/args [[::r/si -16] ::r/al]}
   [0 0x44 0x0f] {::i/args [[::r/si 15] ::r/al]}
   [0 0x45 0x00] {::i/args [[::r/di] ::r/al]}
   [0 0x45 0xf0] {::i/args [[::r/di -16] ::r/al]}
   [0 0x45 0x0f] {::i/args [[::r/di 15] ::r/al]}
   [0 0x46 0x00] {::i/args [[::r/bp] ::r/al]}
   [0 0x46 0xf0] {::i/args [[::r/bp -16] ::r/al]}
   [0 0x46 0x0f] {::i/args [[::r/bp 15] ::r/al]}
   [0 0x47 0x00] {::i/args [[::r/bx] ::r/al]}
   [0 0x47 0xf0] {::i/args [[::r/bx -16] ::r/al]}
   [0 0x47 0x0f] {::i/args [[::r/bx 15] ::r/al]}
   [0 0x48 0x00] {::i/args [[::r/bx ::r/si] ::r/cl]}
   [0 0x48 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0 0x48 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0 0x49 0x00] {::i/args [[::r/bx ::r/di] ::r/cl]}
   [0 0x49 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0 0x49 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0 0x4a 0x00] {::i/args [[::r/bp ::r/si] ::r/cl]}
   [0 0x4a 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0 0x4a 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0 0x4b 0x00] {::i/args [[::r/bp ::r/di] ::r/cl]}
   [0 0x4b 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0 0x4b 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0 0x4c 0x00] {::i/args [[::r/si] ::r/cl]}
   [0 0x4c 0xf0] {::i/args [[::r/si -16] ::r/cl]}
   [0 0x4c 0x0f] {::i/args [[::r/si 15] ::r/cl]}
   [0 0x4d 0x00] {::i/args [[::r/di] ::r/cl]}
   [0 0x4d 0xf0] {::i/args [[::r/di -16] ::r/cl]}
   [0 0x4d 0x0f] {::i/args [[::r/di 15] ::r/cl]}
   [0 0x4e 0x00] {::i/args [[::r/bp] ::r/cl]}
   [0 0x4e 0xf0] {::i/args [[::r/bp -16] ::r/cl]}
   [0 0x4e 0x0f] {::i/args [[::r/bp 15] ::r/cl]}
   [0 0x4f 0x00] {::i/args [[::r/bx] ::r/cl]}
   [0 0x4f 0xf0] {::i/args [[::r/bx -16] ::r/cl]}
   [0 0x4f 0x0f] {::i/args [[::r/bx 15] ::r/cl]}
   [0 0x50 0x00] {::i/args [[::r/bx ::r/si] ::r/dl]}
   [0 0x50 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/dl]}
   [0 0x50 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/dl]}
   [0 0x51 0x00] {::i/args [[::r/bx ::r/di] ::r/dl]}
   [0 0x51 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/dl]}
   [0 0x51 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/dl]}
   [0 0x52 0x00] {::i/args [[::r/bp ::r/si] ::r/dl]}
   [0 0x52 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/dl]}
   [0 0x52 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/dl]}
   [0 0x53 0x00] {::i/args [[::r/bp ::r/di] ::r/dl]}
   [0 0x53 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/dl]}
   [0 0x53 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/dl]}
   [0 0x54 0x00] {::i/args [[::r/si] ::r/dl]}
   [0 0x54 0xf0] {::i/args [[::r/si -16] ::r/dl]}
   [0 0x54 0x0f] {::i/args [[::r/si 15] ::r/dl]}
   [0 0x55 0x00] {::i/args [[::r/di] ::r/dl]}
   [0 0x55 0xf0] {::i/args [[::r/di -16] ::r/dl]}
   [0 0x55 0x0f] {::i/args [[::r/di 15] ::r/dl]}
   [0 0x56 0x00] {::i/args [[::r/bp] ::r/dl]}
   [0 0x56 0xf0] {::i/args [[::r/bp -16] ::r/dl]}
   [0 0x56 0x0f] {::i/args [[::r/bp 15] ::r/dl]}
   [0 0x57 0x00] {::i/args [[::r/bx] ::r/dl]}
   [0 0x57 0xf0] {::i/args [[::r/bx -16] ::r/dl]}
   [0 0x57 0x0f] {::i/args [[::r/bx 15] ::r/dl]}
   [0 0x58 0x00] {::i/args [[::r/bx ::r/si] ::r/bl]}
   [0 0x58 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/bl]}
   [0 0x58 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/bl]}
   [0 0x59 0x00] {::i/args [[::r/bx ::r/di] ::r/bl]}
   [0 0x59 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/bl]}
   [0 0x59 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/bl]}
   [0 0x5a 0x00] {::i/args [[::r/bp ::r/si] ::r/bl]}
   [0 0x5a 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/bl]}
   [0 0x5a 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/bl]}
   [0 0x5b 0x00] {::i/args [[::r/bp ::r/di] ::r/bl]}
   [0 0x5b 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/bl]}
   [0 0x5b 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/bl]}
   [0 0x5c 0x00] {::i/args [[::r/si] ::r/bl]}
   [0 0x5c 0xf0] {::i/args [[::r/si -16] ::r/bl]}
   [0 0x5c 0x0f] {::i/args [[::r/si 15] ::r/bl]}
   [0 0x5d 0x00] {::i/args [[::r/di] ::r/bl]}
   [0 0x5d 0xf0] {::i/args [[::r/di -16] ::r/bl]}
   [0 0x5d 0x0f] {::i/args [[::r/di 15] ::r/bl]}
   [0 0x5e 0x00] {::i/args [[::r/bp] ::r/bl]}
   [0 0x5e 0xf0] {::i/args [[::r/bp -16] ::r/bl]}
   [0 0x5e 0x0f] {::i/args [[::r/bp 15] ::r/bl]}
   [0 0x5f 0x00] {::i/args [[::r/bx] ::r/bl]}
   [0 0x5f 0xf0] {::i/args [[::r/bx -16] ::r/bl]}
   [0 0x5f 0x0f] {::i/args [[::r/bx 15] ::r/bl]}
   [0 0x60 0x00] {::i/args [[::r/bx ::r/si] ::r/ah]}
   [0 0x60 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/ah]}
   [0 0x60 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/ah]}
   [0 0x61 0x00] {::i/args [[::r/bx ::r/di] ::r/ah]}
   [0 0x61 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/ah]}
   [0 0x61 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/ah]}
   [0 0x62 0x00] {::i/args [[::r/bp ::r/si] ::r/ah]}
   [0 0x62 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/ah]}
   [0 0x62 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/ah]}
   [0 0x63 0x00] {::i/args [[::r/bp ::r/di] ::r/ah]}
   [0 0x63 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/ah]}
   [0 0x63 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/ah]}
   [0 0x64 0x00] {::i/args [[::r/si] ::r/ah]}
   [0 0x64 0xf0] {::i/args [[::r/si -16] ::r/ah]}
   [0 0x64 0x0f] {::i/args [[::r/si 15] ::r/ah]}
   [0 0x65 0x00] {::i/args [[::r/di] ::r/ah]}
   [0 0x65 0xf0] {::i/args [[::r/di -16] ::r/ah]}
   [0 0x65 0x0f] {::i/args [[::r/di 15] ::r/ah]}
   [0 0x66 0x00] {::i/args [[::r/bp] ::r/ah]}
   [0 0x66 0xf0] {::i/args [[::r/bp -16] ::r/ah]}
   [0 0x66 0x0f] {::i/args [[::r/bp 15] ::r/ah]}
   [0 0x67 0x00] {::i/args [[::r/bx] ::r/ah]}
   [0 0x67 0xf0] {::i/args [[::r/bx -16] ::r/ah]}
   [0 0x67 0x0f] {::i/args [[::r/bx 15] ::r/ah]}
   [0 0x68 0x00] {::i/args [[::r/bx ::r/si] ::r/ch]}
   [0 0x68 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/ch]}
   [0 0x68 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/ch]}
   [0 0x69 0x00] {::i/args [[::r/bx ::r/di] ::r/ch]}
   [0 0x69 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/ch]}
   [0 0x69 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/ch]}
   [0 0x6a 0x00] {::i/args [[::r/bp ::r/si] ::r/ch]}
   [0 0x6a 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/ch]}
   [0 0x6a 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/ch]}
   [0 0x6b 0x00] {::i/args [[::r/bp ::r/di] ::r/ch]}
   [0 0x6b 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/ch]}
   [0 0x6b 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/ch]}
   [0 0x6c 0x00] {::i/args [[::r/si] ::r/ch]}
   [0 0x6c 0xf0] {::i/args [[::r/si -16] ::r/ch]}
   [0 0x6c 0x0f] {::i/args [[::r/si 15] ::r/ch]}
   [0 0x6d 0x00] {::i/args [[::r/di] ::r/ch]}
   [0 0x6d 0xf0] {::i/args [[::r/di -16] ::r/ch]}
   [0 0x6d 0x0f] {::i/args [[::r/di 15] ::r/ch]}
   [0 0x6e 0x00] {::i/args [[::r/bp] ::r/ch]}
   [0 0x6e 0xf0] {::i/args [[::r/bp -16] ::r/ch]}
   [0 0x6e 0x0f] {::i/args [[::r/bp 15] ::r/ch]}
   [0 0x6f 0x00] {::i/args [[::r/bx] ::r/ch]}
   [0 0x6f 0xf0] {::i/args [[::r/bx -16] ::r/ch]}
   [0 0x6f 0x0f] {::i/args [[::r/bx 15] ::r/ch]}
   [0 0x70 0x00] {::i/args [[::r/bx ::r/si] ::r/dh]}
   [0 0x70 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/dh]}
   [0 0x70 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/dh]}
   [0 0x71 0x00] {::i/args [[::r/bx ::r/di] ::r/dh]}
   [0 0x71 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/dh]}
   [0 0x71 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/dh]}
   [0 0x72 0x00] {::i/args [[::r/bp ::r/si] ::r/dh]}
   [0 0x72 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/dh]}
   [0 0x72 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/dh]}
   [0 0x73 0x00] {::i/args [[::r/bp ::r/di] ::r/dh]}
   [0 0x73 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/dh]}
   [0 0x73 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/dh]}
   [0 0x74 0x00] {::i/args [[::r/si] ::r/dh]}
   [0 0x74 0xf0] {::i/args [[::r/si -16] ::r/dh]}
   [0 0x74 0x0f] {::i/args [[::r/si 15] ::r/dh]}
   [0 0x75 0x00] {::i/args [[::r/di] ::r/dh]}
   [0 0x75 0xf0] {::i/args [[::r/di -16] ::r/dh]}
   [0 0x75 0x0f] {::i/args [[::r/di 15] ::r/dh]}
   [0 0x76 0x00] {::i/args [[::r/bp] ::r/dh]}
   [0 0x76 0xf0] {::i/args [[::r/bp -16] ::r/dh]}
   [0 0x76 0x0f] {::i/args [[::r/bp 15] ::r/dh]}
   [0 0x77 0x00] {::i/args [[::r/bx] ::r/dh]}
   [0 0x77 0xf0] {::i/args [[::r/bx -16] ::r/dh]}
   [0 0x77 0x0f] {::i/args [[::r/bx 15] ::r/dh]}
   [0 0x78 0x00] {::i/args [[::r/bx ::r/si] ::r/bh]}
   [0 0x78 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/bh]}
   [0 0x78 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/bh]}
   [0 0x79 0x00] {::i/args [[::r/bx ::r/di] ::r/bh]}
   [0 0x79 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/bh]}
   [0 0x79 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/bh]}
   [0 0x7a 0x00] {::i/args [[::r/bp ::r/si] ::r/bh]}
   [0 0x7a 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/bh]}
   [0 0x7a 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/bh]}
   [0 0x7b 0x00] {::i/args [[::r/bp ::r/di] ::r/bh]}
   [0 0x7b 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/bh]}
   [0 0x7b 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/bh]}
   [0 0x7c 0x00] {::i/args [[::r/si] ::r/bh]}
   [0 0x7c 0xf0] {::i/args [[::r/si -16] ::r/bh]}
   [0 0x7c 0x0f] {::i/args [[::r/si 15] ::r/bh]}
   [0 0x7d 0x00] {::i/args [[::r/di] ::r/bh]}
   [0 0x7d 0xf0] {::i/args [[::r/di -16] ::r/bh]}
   [0 0x7d 0x0f] {::i/args [[::r/di 15] ::r/bh]}
   [0 0x7e 0x00] {::i/args [[::r/bp] ::r/bh]}
   [0 0x7e 0xf0] {::i/args [[::r/bp -16] ::r/bh]}
   [0 0x7e 0x0f] {::i/args [[::r/bp 15] ::r/bh]}
   [0 0x7f 0x00] {::i/args [[::r/bx] ::r/bh]}
   [0 0x7f 0xf0] {::i/args [[::r/bx -16] ::r/bh]}
   [0 0x7f 0x0f] {::i/args [[::r/bx 15] ::r/bh]}
   [0 0x80 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/al]}
   [0 0x80 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/al]}
   [0 0x80 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/al]}
   [0 0x81 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/al]}
   [0 0x81 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/al]}
   [0 0x81 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/al]}
   [0 0x82 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/al]}
   [0 0x82 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/al]}
   [0 0x82 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/al]}
   [0 0x83 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/al]}
   [0 0x83 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/al]}
   [0 0x83 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/al]}
   [0 0x84 0x00 0x00] {::i/args [[::r/si] ::r/al]}
   [0 0x84 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/al]}
   [0 0x84 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/al]}
   [0 0x85 0x00 0x00] {::i/args [[::r/di] ::r/al]}
   [0 0x85 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/al]}
   [0 0x85 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/al]}
   [0 0x86 0x00 0x00] {::i/args [[::r/bp] ::r/al]}
   [0 0x86 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/al]}
   [0 0x86 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/al]}
   [0 0x87 0x00 0x00] {::i/args [[::r/bx] ::r/al]}
   [0 0x87 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/al]}
   [0 0x87 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/al]}
   [0 0x88 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/cl]}
   [0 0x88 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0 0x88 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0 0x89 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/cl]}
   [0 0x89 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0 0x89 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0 0x8a 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/cl]}
   [0 0x8a 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0 0x8a 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0 0x8b 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/cl]}
   [0 0x8b 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0 0x8b 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0 0x8c 0x00 0x00] {::i/args [[::r/si] ::r/cl]}
   [0 0x8c 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/cl]}
   [0 0x8c 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/cl]}
   [0 0x8d 0x00 0x00] {::i/args [[::r/di] ::r/cl]}
   [0 0x8d 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/cl]}
   [0 0x8d 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/cl]}
   [0 0x8e 0x00 0x00] {::i/args [[::r/bp] ::r/cl]}
   [0 0x8e 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/cl]}
   [0 0x8e 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/cl]}
   [0 0x8f 0x00 0x00] {::i/args [[::r/bx] ::r/cl]}
   [0 0x8f 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/cl]}
   [0 0x8f 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/cl]}
   [0 0x90 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/dl]}
   [0 0x90 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/dl]}
   [0 0x90 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/dl]}
   [0 0x91 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/dl]}
   [0 0x91 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/dl]}
   [0 0x91 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/dl]}
   [0 0x92 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/dl]}
   [0 0x92 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/dl]}
   [0 0x92 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/dl]}
   [0 0x93 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/dl]}
   [0 0x93 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/dl]}
   [0 0x93 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/dl]}
   [0 0x94 0x00 0x00] {::i/args [[::r/si] ::r/dl]}
   [0 0x94 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/dl]}
   [0 0x94 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/dl]}
   [0 0x95 0x00 0x00] {::i/args [[::r/di] ::r/dl]}
   [0 0x95 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/dl]}
   [0 0x95 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/dl]}
   [0 0x96 0x00 0x00] {::i/args [[::r/bp] ::r/dl]}
   [0 0x96 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/dl]}
   [0 0x96 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/dl]}
   [0 0x97 0x00 0x00] {::i/args [[::r/bx] ::r/dl]}
   [0 0x97 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/dl]}
   [0 0x97 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/dl]}
   [0 0x98 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/bl]}
   [0 0x98 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/bl]}
   [0 0x98 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/bl]}
   [0 0x99 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/bl]}
   [0 0x99 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/bl]}
   [0 0x99 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/bl]}
   [0 0x9a 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/bl]}
   [0 0x9a 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/bl]}
   [0 0x9a 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/bl]}
   [0 0x9b 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/bl]}
   [0 0x9b 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/bl]}
   [0 0x9b 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/bl]}
   [0 0x9c 0x00 0x00] {::i/args [[::r/si] ::r/bl]}
   [0 0x9c 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/bl]}
   [0 0x9c 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/bl]}
   [0 0x9d 0x00 0x00] {::i/args [[::r/di] ::r/bl]}
   [0 0x9d 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/bl]}
   [0 0x9d 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/bl]}
   [0 0x9e 0x00 0x00] {::i/args [[::r/bp] ::r/bl]}
   [0 0x9e 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/bl]}
   [0 0x9e 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/bl]}
   [0 0x9f 0x00 0x00] {::i/args [[::r/bx] ::r/bl]}
   [0 0x9f 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/bl]}
   [0 0x9f 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/bl]}
   [0 0xa0 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/ah]}
   [0 0xa0 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/ah]}
   [0 0xa0 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/ah]}
   [0 0xa1 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/ah]}
   [0 0xa1 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/ah]}
   [0 0xa1 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/ah]}
   [0 0xa2 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/ah]}
   [0 0xa2 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/ah]}
   [0 0xa2 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/ah]}
   [0 0xa3 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/ah]}
   [0 0xa3 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/ah]}
   [0 0xa3 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/ah]}
   [0 0xa4 0x00 0x00] {::i/args [[::r/si] ::r/ah]}
   [0 0xa4 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/ah]}
   [0 0xa4 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/ah]}
   [0 0xa5 0x00 0x00] {::i/args [[::r/di] ::r/ah]}
   [0 0xa5 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/ah]}
   [0 0xa5 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/ah]}
   [0 0xa6 0x00 0x00] {::i/args [[::r/bp] ::r/ah]}
   [0 0xa6 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/ah]}
   [0 0xa6 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/ah]}
   [0 0xa7 0x00 0x00] {::i/args [[::r/bx] ::r/ah]}
   [0 0xa7 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/ah]}
   [0 0xa7 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/ah]}
   [0 0xa8 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/ch]}
   [0 0xa8 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/ch]}
   [0 0xa8 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/ch]}
   [0 0xa9 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/ch]}
   [0 0xa9 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/ch]}
   [0 0xa9 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/ch]}
   [0 0xaa 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/ch]}
   [0 0xaa 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/ch]}
   [0 0xaa 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/ch]}
   [0 0xab 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/ch]}
   [0 0xab 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/ch]}
   [0 0xab 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/ch]}
   [0 0xac 0x00 0x00] {::i/args [[::r/si] ::r/ch]}
   [0 0xac 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/ch]}
   [0 0xac 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/ch]}
   [0 0xad 0x00 0x00] {::i/args [[::r/di] ::r/ch]}
   [0 0xad 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/ch]}
   [0 0xad 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/ch]}
   [0 0xae 0x00 0x00] {::i/args [[::r/bp] ::r/ch]}
   [0 0xae 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/ch]}
   [0 0xae 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/ch]}
   [0 0xaf 0x00 0x00] {::i/args [[::r/bx] ::r/ch]}
   [0 0xaf 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/ch]}
   [0 0xaf 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/ch]}
   [0 0xb0 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/dh]}
   [0 0xb0 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/dh]}
   [0 0xb0 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/dh]}
   [0 0xb1 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/dh]}
   [0 0xb1 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/dh]}
   [0 0xb1 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/dh]}
   [0 0xb2 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/dh]}
   [0 0xb2 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/dh]}
   [0 0xb2 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/dh]}
   [0 0xb3 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/dh]}
   [0 0xb3 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/dh]}
   [0 0xb3 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/dh]}
   [0 0xb4 0x00 0x00] {::i/args [[::r/si] ::r/dh]}
   [0 0xb4 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/dh]}
   [0 0xb4 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/dh]}
   [0 0xb5 0x00 0x00] {::i/args [[::r/di] ::r/dh]}
   [0 0xb5 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/dh]}
   [0 0xb5 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/dh]}
   [0 0xb6 0x00 0x00] {::i/args [[::r/bp] ::r/dh]}
   [0 0xb6 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/dh]}
   [0 0xb6 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/dh]}
   [0 0xb7 0x00 0x00] {::i/args [[::r/bx] ::r/dh]}
   [0 0xb7 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/dh]}
   [0 0xb7 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/dh]}
   [0 0xb8 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/bh]}
   [0 0xb8 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/bh]}
   [0 0xb8 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/bh]}
   [0 0xb9 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/bh]}
   [0 0xb9 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/bh]}
   [0 0xb9 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/bh]}
   [0 0xba 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/bh]}
   [0 0xba 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/bh]}
   [0 0xba 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/bh]}
   [0 0xbb 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/bh]}
   [0 0xbb 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/bh]}
   [0 0xbb 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/bh]}
   [0 0xbc 0x00 0x00] {::i/args [[::r/si] ::r/bh]}
   [0 0xbc 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/bh]}
   [0 0xbc 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/bh]}
   [0 0xbd 0x00 0x00] {::i/args [[::r/di] ::r/bh]}
   [0 0xbd 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/bh]}
   [0 0xbd 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/bh]}
   [0 0xbe 0x00 0x00] {::i/args [[::r/bp] ::r/bh]}
   [0 0xbe 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/bh]}
   [0 0xbe 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/bh]}
   [0 0xbf 0x00 0x00] {::i/args [[::r/bx] ::r/bh]}
   [0 0xbf 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/bh]}
   [0 0xbf 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/bh]}
   [0 0xc0] {::i/args [::r/al ::r/al]}
   [0 0xc1] {::i/args [::r/cl ::r/al]}
   [0 0xc2] {::i/args [::r/dl ::r/al]}
   [0 0xc3] {::i/args [::r/bl ::r/al]}
   [0 0xc4] {::i/args [::r/ah ::r/al]}
   [0 0xc5] {::i/args [::r/ch ::r/al]}
   [0 0xc6] {::i/args [::r/dh ::r/al]}
   [0 0xc7] {::i/args [::r/bh ::r/al]}
   [0 0xc8] {::i/args [::r/al ::r/cl]}
   [0 0xc9] {::i/args [::r/cl ::r/cl]}
   [0 0xca] {::i/args [::r/dl ::r/cl]}
   [0 0xcb] {::i/args [::r/bl ::r/cl]}
   [0 0xcc] {::i/args [::r/ah ::r/cl]}
   [0 0xcd] {::i/args [::r/ch ::r/cl]}
   [0 0xce] {::i/args [::r/dh ::r/cl]}
   [0 0xcf] {::i/args [::r/bh ::r/cl]}
   [0 0xd0] {::i/args [::r/al ::r/dl]}
   [0 0xd1] {::i/args [::r/cl ::r/dl]}
   [0 0xd2] {::i/args [::r/dl ::r/dl]}
   [0 0xd3] {::i/args [::r/bl ::r/dl]}
   [0 0xd4] {::i/args [::r/ah ::r/dl]}
   [0 0xd5] {::i/args [::r/ch ::r/dl]}
   [0 0xd6] {::i/args [::r/dh ::r/dl]}
   [0 0xd7] {::i/args [::r/bh ::r/dl]}
   [0 0xd8] {::i/args [::r/al ::r/bl]}
   [0 0xd9] {::i/args [::r/cl ::r/bl]}
   [0 0xda] {::i/args [::r/dl ::r/bl]}
   [0 0xdb] {::i/args [::r/bl ::r/bl]}
   [0 0xdc] {::i/args [::r/ah ::r/bl]}
   [0 0xdd] {::i/args [::r/ch ::r/bl]}
   [0 0xde] {::i/args [::r/dh ::r/bl]}
   [0 0xdf] {::i/args [::r/bh ::r/bl]}
   [0 0xe0] {::i/args [::r/al ::r/ah]}
   [0 0xe1] {::i/args [::r/cl ::r/ah]}
   [0 0xe2] {::i/args [::r/dl ::r/ah]}
   [0 0xe3] {::i/args [::r/bl ::r/ah]}
   [0 0xe4] {::i/args [::r/ah ::r/ah]}
   [0 0xe5] {::i/args [::r/ch ::r/ah]}
   [0 0xe6] {::i/args [::r/dh ::r/ah]}
   [0 0xe7] {::i/args [::r/bh ::r/ah]}
   [0 0xe8] {::i/args [::r/al ::r/ch]}
   [0 0xe9] {::i/args [::r/cl ::r/ch]}
   [0 0xea] {::i/args [::r/dl ::r/ch]}
   [0 0xeb] {::i/args [::r/bl ::r/ch]}
   [0 0xec] {::i/args [::r/ah ::r/ch]}
   [0 0xed] {::i/args [::r/ch ::r/ch]}
   [0 0xee] {::i/args [::r/dh ::r/ch]}
   [0 0xef] {::i/args [::r/bh ::r/ch]}
   [0 0xf0] {::i/args [::r/al ::r/dh]}
   [0 0xf1] {::i/args [::r/cl ::r/dh]}
   [0 0xf2] {::i/args [::r/dl ::r/dh]}
   [0 0xf3] {::i/args [::r/bl ::r/dh]}
   [0 0xf4] {::i/args [::r/ah ::r/dh]}
   [0 0xf5] {::i/args [::r/ch ::r/dh]}
   [0 0xf6] {::i/args [::r/dh ::r/dh]}
   [0 0xf7] {::i/args [::r/bh ::r/dh]}
   [0 0xf8] {::i/args [::r/al ::r/bh]}
   [0 0xf9] {::i/args [::r/cl ::r/bh]}
   [0 0xfa] {::i/args [::r/dl ::r/bh]}
   [0 0xfb] {::i/args [::r/bl ::r/bh]}
   [0 0xfc] {::i/args [::r/ah ::r/bh]}
   [0 0xfd] {::i/args [::r/ch ::r/bh]}
   [0 0xfe] {::i/args [::r/dh ::r/bh]}
   [0 0xff] {::i/args [::r/bh ::r/bh]}
   [1 0x00] {::i/args [[::r/bx ::r/si] ::r/ax]}
   [1 0x01] {::i/args [[::r/bx ::r/di] ::r/ax]}
   [1 0x02] {::i/args [[::r/bp ::r/si] ::r/ax]}
   [1 0x03] {::i/args [[::r/bp ::r/di] ::r/ax]}
   [1 0x04] {::i/args [[::r/si] ::r/ax]}
   [1 0x05] {::i/args [[::r/di] ::r/ax]}
   [1 0x06 0xf0 0x0f] {::i/args [[0x0ff0] ::r/ax]}
   [1 0x06 0x0f 0xf0] {::i/args [[0xf00f] ::r/ax]}
   [1 0x07] {::i/args [[::r/bx] ::r/ax]}
   [1 0x08] {::i/args [[::r/bx ::r/si] ::r/cx]}
   [1 0x09] {::i/args [[::r/bx ::r/di] ::r/cx]}
   [1 0x0a] {::i/args [[::r/bp ::r/si] ::r/cx]}
   [1 0x0b] {::i/args [[::r/bp ::r/di] ::r/cx]}
   [1 0x0c] {::i/args [[::r/si] ::r/cx]}
   [1 0x0d] {::i/args [[::r/di] ::r/cx]}
   [1 0x0e 0xf0 0x0f] {::i/args [[0x0ff0] ::r/cx]}
   [1 0x0e 0x0f 0xf0] {::i/args [[0xf00f] ::r/cx]}
   [1 0x0f] {::i/args [[::r/bx] ::r/cx]}
   [1 0x10] {::i/args [[::r/bx ::r/si] ::r/dx]}
   [1 0x11] {::i/args [[::r/bx ::r/di] ::r/dx]}
   [1 0x12] {::i/args [[::r/bp ::r/si] ::r/dx]}
   [1 0x13] {::i/args [[::r/bp ::r/di] ::r/dx]}
   [1 0x14] {::i/args [[::r/si] ::r/dx]}
   [1 0x15] {::i/args [[::r/di] ::r/dx]}
   [1 0x16 0xf0 0x0f] {::i/args [[0x0ff0] ::r/dx]}
   [1 0x16 0x0f 0xf0] {::i/args [[0xf00f] ::r/dx]}
   [1 0x17] {::i/args [[::r/bx] ::r/dx]}
   [1 0x18] {::i/args [[::r/bx ::r/si] ::r/bx]}
   [1 0x19] {::i/args [[::r/bx ::r/di] ::r/bx]}
   [1 0x1a] {::i/args [[::r/bp ::r/si] ::r/bx]}
   [1 0x1b] {::i/args [[::r/bp ::r/di] ::r/bx]}
   [1 0x1c] {::i/args [[::r/si] ::r/bx]}
   [1 0x1d] {::i/args [[::r/di] ::r/bx]}
   [1 0x1e 0xf0 0x0f] {::i/args [[0x0ff0] ::r/bx]}
   [1 0x1e 0x0f 0xf0] {::i/args [[0xf00f] ::r/bx]}
   [1 0x1f] {::i/args [[::r/bx] ::r/bx]}
   [1 0x20] {::i/args [[::r/bx ::r/si] ::r/sp]}
   [1 0x21] {::i/args [[::r/bx ::r/di] ::r/sp]}
   [1 0x22] {::i/args [[::r/bp ::r/si] ::r/sp]}
   [1 0x23] {::i/args [[::r/bp ::r/di] ::r/sp]}
   [1 0x24] {::i/args [[::r/si] ::r/sp]}
   [1 0x25] {::i/args [[::r/di] ::r/sp]}
   [1 0x26 0xf0 0x0f] {::i/args [[0x0ff0] ::r/sp]}
   [1 0x26 0x0f 0xf0] {::i/args [[0xf00f] ::r/sp]}
   [1 0x27] {::i/args [[::r/bx] ::r/sp]}
   [1 0x28] {::i/args [[::r/bx ::r/si] ::r/bp]}
   [1 0x29] {::i/args [[::r/bx ::r/di] ::r/bp]}
   [1 0x2a] {::i/args [[::r/bp ::r/si] ::r/bp]}
   [1 0x2b] {::i/args [[::r/bp ::r/di] ::r/bp]}
   [1 0x2c] {::i/args [[::r/si] ::r/bp]}
   [1 0x2d] {::i/args [[::r/di] ::r/bp]}
   [1 0x2e 0xf0 0x0f] {::i/args [[0x0ff0] ::r/bp]}
   [1 0x2e 0x0f 0xf0] {::i/args [[0xf00f] ::r/bp]}
   [1 0x2f] {::i/args [[::r/bx] ::r/bp]}
   [1 0x30] {::i/args [[::r/bx ::r/si] ::r/si]}
   [1 0x31] {::i/args [[::r/bx ::r/di] ::r/si]}
   [1 0x32] {::i/args [[::r/bp ::r/si] ::r/si]}
   [1 0x33] {::i/args [[::r/bp ::r/di] ::r/si]}
   [1 0x34] {::i/args [[::r/si] ::r/si]}
   [1 0x35] {::i/args [[::r/di] ::r/si]}
   [1 0x36 0xf0 0x0f] {::i/args [[0x0ff0] ::r/si]}
   [1 0x36 0x0f 0xf0] {::i/args [[0xf00f] ::r/si]}
   [1 0x37] {::i/args [[::r/bx] ::r/si]}
   [1 0x38] {::i/args [[::r/bx ::r/si] ::r/di]}
   [1 0x39] {::i/args [[::r/bx ::r/di] ::r/di]}
   [1 0x3a] {::i/args [[::r/bp ::r/si] ::r/di]}
   [1 0x3b] {::i/args [[::r/bp ::r/di] ::r/di]}
   [1 0x3c] {::i/args [[::r/si] ::r/di]}
   [1 0x3d] {::i/args [[::r/di] ::r/di]}
   [1 0x3e 0xf0 0x0f] {::i/args [[0x0ff0] ::r/di]}
   [1 0x3e 0x0f 0xf0] {::i/args [[0xf00f] ::r/di]}
   [1 0x3f] {::i/args [[::r/bx] ::r/di]}
   [1 0x40 0x00] {::i/args [[::r/bx ::r/si] ::r/ax]}
   [1 0x40 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/ax]}
   [1 0x40 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/ax]}
   [1 0x41 0x00] {::i/args [[::r/bx ::r/di] ::r/ax]}
   [1 0x41 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/ax]}
   [1 0x41 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/ax]}
   [1 0x42 0x00] {::i/args [[::r/bp ::r/si] ::r/ax]}
   [1 0x42 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/ax]}
   [1 0x42 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/ax]}
   [1 0x43 0x00] {::i/args [[::r/bp ::r/di] ::r/ax]}
   [1 0x43 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/ax]}
   [1 0x43 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/ax]}
   [1 0x44 0x00] {::i/args [[::r/si] ::r/ax]}
   [1 0x44 0xf0] {::i/args [[::r/si -16] ::r/ax]}
   [1 0x44 0x0f] {::i/args [[::r/si 15] ::r/ax]}
   [1 0x45 0x00] {::i/args [[::r/di] ::r/ax]}
   [1 0x45 0xf0] {::i/args [[::r/di -16] ::r/ax]}
   [1 0x45 0x0f] {::i/args [[::r/di 15] ::r/ax]}
   [1 0x46 0x00] {::i/args [[::r/bp] ::r/ax]}
   [1 0x46 0xf0] {::i/args [[::r/bp -16] ::r/ax]}
   [1 0x46 0x0f] {::i/args [[::r/bp 15] ::r/ax]}
   [1 0x47 0x00] {::i/args [[::r/bx] ::r/ax]}
   [1 0x47 0xf0] {::i/args [[::r/bx -16] ::r/ax]}
   [1 0x47 0x0f] {::i/args [[::r/bx 15] ::r/ax]}
   [1 0x48 0x00] {::i/args [[::r/bx ::r/si] ::r/cx]}
   [1 0x48 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/cx]}
   [1 0x48 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/cx]}
   [1 0x49 0x00] {::i/args [[::r/bx ::r/di] ::r/cx]}
   [1 0x49 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/cx]}
   [1 0x49 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/cx]}
   [1 0x4a 0x00] {::i/args [[::r/bp ::r/si] ::r/cx]}
   [1 0x4a 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/cx]}
   [1 0x4a 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/cx]}
   [1 0x4b 0x00] {::i/args [[::r/bp ::r/di] ::r/cx]}
   [1 0x4b 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/cx]}
   [1 0x4b 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/cx]}
   [1 0x4c 0x00] {::i/args [[::r/si] ::r/cx]}
   [1 0x4c 0xf0] {::i/args [[::r/si -16] ::r/cx]}
   [1 0x4c 0x0f] {::i/args [[::r/si 15] ::r/cx]}
   [1 0x4d 0x00] {::i/args [[::r/di] ::r/cx]}
   [1 0x4d 0xf0] {::i/args [[::r/di -16] ::r/cx]}
   [1 0x4d 0x0f] {::i/args [[::r/di 15] ::r/cx]}
   [1 0x4e 0x00] {::i/args [[::r/bp] ::r/cx]}
   [1 0x4e 0xf0] {::i/args [[::r/bp -16] ::r/cx]}
   [1 0x4e 0x0f] {::i/args [[::r/bp 15] ::r/cx]}
   [1 0x4f 0x00] {::i/args [[::r/bx] ::r/cx]}
   [1 0x4f 0xf0] {::i/args [[::r/bx -16] ::r/cx]}
   [1 0x4f 0x0f] {::i/args [[::r/bx 15] ::r/cx]}
   [1 0x50 0x00] {::i/args [[::r/bx ::r/si] ::r/dx]}
   [1 0x50 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/dx]}
   [1 0x50 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/dx]}
   [1 0x51 0x00] {::i/args [[::r/bx ::r/di] ::r/dx]}
   [1 0x51 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/dx]}
   [1 0x51 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/dx]}
   [1 0x52 0x00] {::i/args [[::r/bp ::r/si] ::r/dx]}
   [1 0x52 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/dx]}
   [1 0x52 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/dx]}
   [1 0x53 0x00] {::i/args [[::r/bp ::r/di] ::r/dx]}
   [1 0x53 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/dx]}
   [1 0x53 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/dx]}
   [1 0x54 0x00] {::i/args [[::r/si] ::r/dx]}
   [1 0x54 0xf0] {::i/args [[::r/si -16] ::r/dx]}
   [1 0x54 0x0f] {::i/args [[::r/si 15] ::r/dx]}
   [1 0x55 0x00] {::i/args [[::r/di] ::r/dx]}
   [1 0x55 0xf0] {::i/args [[::r/di -16] ::r/dx]}
   [1 0x55 0x0f] {::i/args [[::r/di 15] ::r/dx]}
   [1 0x56 0x00] {::i/args [[::r/bp] ::r/dx]}
   [1 0x56 0xf0] {::i/args [[::r/bp -16] ::r/dx]}
   [1 0x56 0x0f] {::i/args [[::r/bp 15] ::r/dx]}
   [1 0x57 0x00] {::i/args [[::r/bx] ::r/dx]}
   [1 0x57 0xf0] {::i/args [[::r/bx -16] ::r/dx]}
   [1 0x57 0x0f] {::i/args [[::r/bx 15] ::r/dx]}
   [1 0x58 0x00] {::i/args [[::r/bx ::r/si] ::r/bx]}
   [1 0x58 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/bx]}
   [1 0x58 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/bx]}
   [1 0x59 0x00] {::i/args [[::r/bx ::r/di] ::r/bx]}
   [1 0x59 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/bx]}
   [1 0x59 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/bx]}
   [1 0x5a 0x00] {::i/args [[::r/bp ::r/si] ::r/bx]}
   [1 0x5a 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/bx]}
   [1 0x5a 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/bx]}
   [1 0x5b 0x00] {::i/args [[::r/bp ::r/di] ::r/bx]}
   [1 0x5b 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/bx]}
   [1 0x5b 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/bx]}
   [1 0x5c 0x00] {::i/args [[::r/si] ::r/bx]}
   [1 0x5c 0xf0] {::i/args [[::r/si -16] ::r/bx]}
   [1 0x5c 0x0f] {::i/args [[::r/si 15] ::r/bx]}
   [1 0x5d 0x00] {::i/args [[::r/di] ::r/bx]}
   [1 0x5d 0xf0] {::i/args [[::r/di -16] ::r/bx]}
   [1 0x5d 0x0f] {::i/args [[::r/di 15] ::r/bx]}
   [1 0x5e 0x00] {::i/args [[::r/bp] ::r/bx]}
   [1 0x5e 0xf0] {::i/args [[::r/bp -16] ::r/bx]}
   [1 0x5e 0x0f] {::i/args [[::r/bp 15] ::r/bx]}
   [1 0x5f 0x00] {::i/args [[::r/bx] ::r/bx]}
   [1 0x5f 0xf0] {::i/args [[::r/bx -16] ::r/bx]}
   [1 0x5f 0x0f] {::i/args [[::r/bx 15] ::r/bx]}
   [1 0x60 0x00] {::i/args [[::r/bx ::r/si] ::r/sp]}
   [1 0x60 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/sp]}
   [1 0x60 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/sp]}
   [1 0x61 0x00] {::i/args [[::r/bx ::r/di] ::r/sp]}
   [1 0x61 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/sp]}
   [1 0x61 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/sp]}
   [1 0x62 0x00] {::i/args [[::r/bp ::r/si] ::r/sp]}
   [1 0x62 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/sp]}
   [1 0x62 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/sp]}
   [1 0x63 0x00] {::i/args [[::r/bp ::r/di] ::r/sp]}
   [1 0x63 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/sp]}
   [1 0x63 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/sp]}
   [1 0x64 0x00] {::i/args [[::r/si] ::r/sp]}
   [1 0x64 0xf0] {::i/args [[::r/si -16] ::r/sp]}
   [1 0x64 0x0f] {::i/args [[::r/si 15] ::r/sp]}
   [1 0x65 0x00] {::i/args [[::r/di] ::r/sp]}
   [1 0x65 0xf0] {::i/args [[::r/di -16] ::r/sp]}
   [1 0x65 0x0f] {::i/args [[::r/di 15] ::r/sp]}
   [1 0x66 0x00] {::i/args [[::r/bp] ::r/sp]}
   [1 0x66 0xf0] {::i/args [[::r/bp -16] ::r/sp]}
   [1 0x66 0x0f] {::i/args [[::r/bp 15] ::r/sp]}
   [1 0x67 0x00] {::i/args [[::r/bx] ::r/sp]}
   [1 0x67 0xf0] {::i/args [[::r/bx -16] ::r/sp]}
   [1 0x67 0x0f] {::i/args [[::r/bx 15] ::r/sp]}
   [1 0x68 0x00] {::i/args [[::r/bx ::r/si] ::r/bp]}
   [1 0x68 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/bp]}
   [1 0x68 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/bp]}
   [1 0x69 0x00] {::i/args [[::r/bx ::r/di] ::r/bp]}
   [1 0x69 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/bp]}
   [1 0x69 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/bp]}
   [1 0x6a 0x00] {::i/args [[::r/bp ::r/si] ::r/bp]}
   [1 0x6a 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/bp]}
   [1 0x6a 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/bp]}
   [1 0x6b 0x00] {::i/args [[::r/bp ::r/di] ::r/bp]}
   [1 0x6b 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/bp]}
   [1 0x6b 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/bp]}
   [1 0x6c 0x00] {::i/args [[::r/si] ::r/bp]}
   [1 0x6c 0xf0] {::i/args [[::r/si -16] ::r/bp]}
   [1 0x6c 0x0f] {::i/args [[::r/si 15] ::r/bp]}
   [1 0x6d 0x00] {::i/args [[::r/di] ::r/bp]}
   [1 0x6d 0xf0] {::i/args [[::r/di -16] ::r/bp]}
   [1 0x6d 0x0f] {::i/args [[::r/di 15] ::r/bp]}
   [1 0x6e 0x00] {::i/args [[::r/bp] ::r/bp]}
   [1 0x6e 0xf0] {::i/args [[::r/bp -16] ::r/bp]}
   [1 0x6e 0x0f] {::i/args [[::r/bp 15] ::r/bp]}
   [1 0x6f 0x00] {::i/args [[::r/bx] ::r/bp]}
   [1 0x6f 0xf0] {::i/args [[::r/bx -16] ::r/bp]}
   [1 0x6f 0x0f] {::i/args [[::r/bx 15] ::r/bp]}
   [1 0x70 0x00] {::i/args [[::r/bx ::r/si] ::r/si]}
   [1 0x70 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/si]}
   [1 0x70 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/si]}
   [1 0x71 0x00] {::i/args [[::r/bx ::r/di] ::r/si]}
   [1 0x71 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/si]}
   [1 0x71 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/si]}
   [1 0x72 0x00] {::i/args [[::r/bp ::r/si] ::r/si]}
   [1 0x72 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/si]}
   [1 0x72 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/si]}
   [1 0x73 0x00] {::i/args [[::r/bp ::r/di] ::r/si]}
   [1 0x73 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/si]}
   [1 0x73 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/si]}
   [1 0x74 0x00] {::i/args [[::r/si] ::r/si]}
   [1 0x74 0xf0] {::i/args [[::r/si -16] ::r/si]}
   [1 0x74 0x0f] {::i/args [[::r/si 15] ::r/si]}
   [1 0x75 0x00] {::i/args [[::r/di] ::r/si]}
   [1 0x75 0xf0] {::i/args [[::r/di -16] ::r/si]}
   [1 0x75 0x0f] {::i/args [[::r/di 15] ::r/si]}
   [1 0x76 0x00] {::i/args [[::r/bp] ::r/si]}
   [1 0x76 0xf0] {::i/args [[::r/bp -16] ::r/si]}
   [1 0x76 0x0f] {::i/args [[::r/bp 15] ::r/si]}
   [1 0x77 0x00] {::i/args [[::r/bx] ::r/si]}
   [1 0x77 0xf0] {::i/args [[::r/bx -16] ::r/si]}
   [1 0x77 0x0f] {::i/args [[::r/bx 15] ::r/si]}
   [1 0x78 0x00] {::i/args [[::r/bx ::r/si] ::r/di]}
   [1 0x78 0xf0] {::i/args [[::r/bx ::r/si -16] ::r/di]}
   [1 0x78 0x0f] {::i/args [[::r/bx ::r/si 15] ::r/di]}
   [1 0x79 0x00] {::i/args [[::r/bx ::r/di] ::r/di]}
   [1 0x79 0xf0] {::i/args [[::r/bx ::r/di -16] ::r/di]}
   [1 0x79 0x0f] {::i/args [[::r/bx ::r/di 15] ::r/di]}
   [1 0x7a 0x00] {::i/args [[::r/bp ::r/si] ::r/di]}
   [1 0x7a 0xf0] {::i/args [[::r/bp ::r/si -16] ::r/di]}
   [1 0x7a 0x0f] {::i/args [[::r/bp ::r/si 15] ::r/di]}
   [1 0x7b 0x00] {::i/args [[::r/bp ::r/di] ::r/di]}
   [1 0x7b 0xf0] {::i/args [[::r/bp ::r/di -16] ::r/di]}
   [1 0x7b 0x0f] {::i/args [[::r/bp ::r/di 15] ::r/di]}
   [1 0x7c 0x00] {::i/args [[::r/si] ::r/di]}
   [1 0x7c 0xf0] {::i/args [[::r/si -16] ::r/di]}
   [1 0x7c 0x0f] {::i/args [[::r/si 15] ::r/di]}
   [1 0x7d 0x00] {::i/args [[::r/di] ::r/di]}
   [1 0x7d 0xf0] {::i/args [[::r/di -16] ::r/di]}
   [1 0x7d 0x0f] {::i/args [[::r/di 15] ::r/di]}
   [1 0x7e 0x00] {::i/args [[::r/bp] ::r/di]}
   [1 0x7e 0xf0] {::i/args [[::r/bp -16] ::r/di]}
   [1 0x7e 0x0f] {::i/args [[::r/bp 15] ::r/di]}
   [1 0x7f 0x00] {::i/args [[::r/bx] ::r/di]}
   [1 0x7f 0xf0] {::i/args [[::r/bx -16] ::r/di]}
   [1 0x7f 0x0f] {::i/args [[::r/bx 15] ::r/di]}
   [1 0x80 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/ax]}
   [1 0x80 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/ax]}
   [1 0x80 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/ax]}
   [1 0x81 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/ax]}
   [1 0x81 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/ax]}
   [1 0x81 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/ax]}
   [1 0x82 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/ax]}
   [1 0x82 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/ax]}
   [1 0x82 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/ax]}
   [1 0x83 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/ax]}
   [1 0x83 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/ax]}
   [1 0x83 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/ax]}
   [1 0x84 0x00 0x00] {::i/args [[::r/si] ::r/ax]}
   [1 0x84 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/ax]}
   [1 0x84 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/ax]}
   [1 0x85 0x00 0x00] {::i/args [[::r/di] ::r/ax]}
   [1 0x85 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/ax]}
   [1 0x85 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/ax]}
   [1 0x86 0x00 0x00] {::i/args [[::r/bp] ::r/ax]}
   [1 0x86 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/ax]}
   [1 0x86 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/ax]}
   [1 0x87 0x00 0x00] {::i/args [[::r/bx] ::r/ax]}
   [1 0x87 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/ax]}
   [1 0x87 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/ax]}
   [1 0x88 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/cx]}
   [1 0x88 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/cx]}
   [1 0x88 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/cx]}
   [1 0x89 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/cx]}
   [1 0x89 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/cx]}
   [1 0x89 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/cx]}
   [1 0x8a 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/cx]}
   [1 0x8a 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/cx]}
   [1 0x8a 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/cx]}
   [1 0x8b 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/cx]}
   [1 0x8b 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/cx]}
   [1 0x8b 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/cx]}
   [1 0x8c 0x00 0x00] {::i/args [[::r/si] ::r/cx]}
   [1 0x8c 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/cx]}
   [1 0x8c 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/cx]}
   [1 0x8d 0x00 0x00] {::i/args [[::r/di] ::r/cx]}
   [1 0x8d 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/cx]}
   [1 0x8d 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/cx]}
   [1 0x8e 0x00 0x00] {::i/args [[::r/bp] ::r/cx]}
   [1 0x8e 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/cx]}
   [1 0x8e 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/cx]}
   [1 0x8f 0x00 0x00] {::i/args [[::r/bx] ::r/cx]}
   [1 0x8f 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/cx]}
   [1 0x8f 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/cx]}
   [1 0x90 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/dx]}
   [1 0x90 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/dx]}
   [1 0x90 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/dx]}
   [1 0x91 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/dx]}
   [1 0x91 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/dx]}
   [1 0x91 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/dx]}
   [1 0x92 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/dx]}
   [1 0x92 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/dx]}
   [1 0x92 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/dx]}
   [1 0x93 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/dx]}
   [1 0x93 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/dx]}
   [1 0x93 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/dx]}
   [1 0x94 0x00 0x00] {::i/args [[::r/si] ::r/dx]}
   [1 0x94 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/dx]}
   [1 0x94 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/dx]}
   [1 0x95 0x00 0x00] {::i/args [[::r/di] ::r/dx]}
   [1 0x95 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/dx]}
   [1 0x95 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/dx]}
   [1 0x96 0x00 0x00] {::i/args [[::r/bp] ::r/dx]}
   [1 0x96 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/dx]}
   [1 0x96 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/dx]}
   [1 0x97 0x00 0x00] {::i/args [[::r/bx] ::r/dx]}
   [1 0x97 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/dx]}
   [1 0x97 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/dx]}
   [1 0x98 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/bx]}
   [1 0x98 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/bx]}
   [1 0x98 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/bx]}
   [1 0x99 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/bx]}
   [1 0x99 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/bx]}
   [1 0x99 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/bx]}
   [1 0x9a 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/bx]}
   [1 0x9a 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/bx]}
   [1 0x9a 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/bx]}
   [1 0x9b 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/bx]}
   [1 0x9b 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/bx]}
   [1 0x9b 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/bx]}
   [1 0x9c 0x00 0x00] {::i/args [[::r/si] ::r/bx]}
   [1 0x9c 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/bx]}
   [1 0x9c 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/bx]}
   [1 0x9d 0x00 0x00] {::i/args [[::r/di] ::r/bx]}
   [1 0x9d 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/bx]}
   [1 0x9d 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/bx]}
   [1 0x9e 0x00 0x00] {::i/args [[::r/bp] ::r/bx]}
   [1 0x9e 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/bx]}
   [1 0x9e 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/bx]}
   [1 0x9f 0x00 0x00] {::i/args [[::r/bx] ::r/bx]}
   [1 0x9f 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/bx]}
   [1 0x9f 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/bx]}
   [1 0xa0 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/sp]}
   [1 0xa0 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/sp]}
   [1 0xa0 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/sp]}
   [1 0xa1 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/sp]}
   [1 0xa1 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/sp]}
   [1 0xa1 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/sp]}
   [1 0xa2 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/sp]}
   [1 0xa2 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/sp]}
   [1 0xa2 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/sp]}
   [1 0xa3 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/sp]}
   [1 0xa3 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/sp]}
   [1 0xa3 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/sp]}
   [1 0xa4 0x00 0x00] {::i/args [[::r/si] ::r/sp]}
   [1 0xa4 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/sp]}
   [1 0xa4 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/sp]}
   [1 0xa5 0x00 0x00] {::i/args [[::r/di] ::r/sp]}
   [1 0xa5 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/sp]}
   [1 0xa5 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/sp]}
   [1 0xa6 0x00 0x00] {::i/args [[::r/bp] ::r/sp]}
   [1 0xa6 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/sp]}
   [1 0xa6 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/sp]}
   [1 0xa7 0x00 0x00] {::i/args [[::r/bx] ::r/sp]}
   [1 0xa7 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/sp]}
   [1 0xa7 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/sp]}
   [1 0xa8 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/bp]}
   [1 0xa8 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/bp]}
   [1 0xa8 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/bp]}
   [1 0xa9 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/bp]}
   [1 0xa9 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/bp]}
   [1 0xa9 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/bp]}
   [1 0xaa 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/bp]}
   [1 0xaa 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/bp]}
   [1 0xaa 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/bp]}
   [1 0xab 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/bp]}
   [1 0xab 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/bp]}
   [1 0xab 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/bp]}
   [1 0xac 0x00 0x00] {::i/args [[::r/si] ::r/bp]}
   [1 0xac 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/bp]}
   [1 0xac 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/bp]}
   [1 0xad 0x00 0x00] {::i/args [[::r/di] ::r/bp]}
   [1 0xad 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/bp]}
   [1 0xad 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/bp]}
   [1 0xae 0x00 0x00] {::i/args [[::r/bp] ::r/bp]}
   [1 0xae 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/bp]}
   [1 0xae 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/bp]}
   [1 0xaf 0x00 0x00] {::i/args [[::r/bx] ::r/bp]}
   [1 0xaf 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/bp]}
   [1 0xaf 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/bp]}
   [1 0xb0 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/si]}
   [1 0xb0 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/si]}
   [1 0xb0 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/si]}
   [1 0xb1 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/si]}
   [1 0xb1 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/si]}
   [1 0xb1 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/si]}
   [1 0xb2 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/si]}
   [1 0xb2 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/si]}
   [1 0xb2 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/si]}
   [1 0xb3 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/si]}
   [1 0xb3 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/si]}
   [1 0xb3 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/si]}
   [1 0xb4 0x00 0x00] {::i/args [[::r/si] ::r/si]}
   [1 0xb4 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/si]}
   [1 0xb4 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/si]}
   [1 0xb5 0x00 0x00] {::i/args [[::r/di] ::r/si]}
   [1 0xb5 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/si]}
   [1 0xb5 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/si]}
   [1 0xb6 0x00 0x00] {::i/args [[::r/bp] ::r/si]}
   [1 0xb6 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/si]}
   [1 0xb6 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/si]}
   [1 0xb7 0x00 0x00] {::i/args [[::r/bx] ::r/si]}
   [1 0xb7 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/si]}
   [1 0xb7 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/si]}
   [1 0xb8 0x00 0x00] {::i/args [[::r/bx ::r/si] ::r/di]}
   [1 0xb8 0xf0 0x0f] {::i/args [[::r/bx ::r/si 4080] ::r/di]}
   [1 0xb8 0x0f 0xf0] {::i/args [[::r/bx ::r/si -4081] ::r/di]}
   [1 0xb9 0x00 0x00] {::i/args [[::r/bx ::r/di] ::r/di]}
   [1 0xb9 0xf0 0x0f] {::i/args [[::r/bx ::r/di 4080] ::r/di]}
   [1 0xb9 0x0f 0xf0] {::i/args [[::r/bx ::r/di -4081] ::r/di]}
   [1 0xba 0x00 0x00] {::i/args [[::r/bp ::r/si] ::r/di]}
   [1 0xba 0xf0 0x0f] {::i/args [[::r/bp ::r/si 4080] ::r/di]}
   [1 0xba 0x0f 0xf0] {::i/args [[::r/bp ::r/si -4081] ::r/di]}
   [1 0xbb 0x00 0x00] {::i/args [[::r/bp ::r/di] ::r/di]}
   [1 0xbb 0xf0 0x0f] {::i/args [[::r/bp ::r/di 4080] ::r/di]}
   [1 0xbb 0x0f 0xf0] {::i/args [[::r/bp ::r/di -4081] ::r/di]}
   [1 0xbc 0x00 0x00] {::i/args [[::r/si] ::r/di]}
   [1 0xbc 0xf0 0x0f] {::i/args [[::r/si 4080] ::r/di]}
   [1 0xbc 0x0f 0xf0] {::i/args [[::r/si -4081] ::r/di]}
   [1 0xbd 0x00 0x00] {::i/args [[::r/di] ::r/di]}
   [1 0xbd 0xf0 0x0f] {::i/args [[::r/di 4080] ::r/di]}
   [1 0xbd 0x0f 0xf0] {::i/args [[::r/di -4081] ::r/di]}
   [1 0xbe 0x00 0x00] {::i/args [[::r/bp] ::r/di]}
   [1 0xbe 0xf0 0x0f] {::i/args [[::r/bp 4080] ::r/di]}
   [1 0xbe 0x0f 0xf0] {::i/args [[::r/bp -4081] ::r/di]}
   [1 0xbf 0x00 0x00] {::i/args [[::r/bx] ::r/di]}
   [1 0xbf 0xf0 0x0f] {::i/args [[::r/bx 4080] ::r/di]}
   [1 0xbf 0x0f 0xf0] {::i/args [[::r/bx -4081] ::r/di]}
   [1 0xc0] {::i/args [::r/ax ::r/ax]}
   [1 0xc1] {::i/args [::r/cx ::r/ax]}
   [1 0xc2] {::i/args [::r/dx ::r/ax]}
   [1 0xc3] {::i/args [::r/bx ::r/ax]}
   [1 0xc4] {::i/args [::r/sp ::r/ax]}
   [1 0xc5] {::i/args [::r/bp ::r/ax]}
   [1 0xc6] {::i/args [::r/si ::r/ax]}
   [1 0xc7] {::i/args [::r/di ::r/ax]}
   [1 0xc8] {::i/args [::r/ax ::r/cx]}
   [1 0xc9] {::i/args [::r/cx ::r/cx]}
   [1 0xca] {::i/args [::r/dx ::r/cx]}
   [1 0xcb] {::i/args [::r/bx ::r/cx]}
   [1 0xcc] {::i/args [::r/sp ::r/cx]}
   [1 0xcd] {::i/args [::r/bp ::r/cx]}
   [1 0xce] {::i/args [::r/si ::r/cx]}
   [1 0xcf] {::i/args [::r/di ::r/cx]}
   [1 0xd0] {::i/args [::r/ax ::r/dx]}
   [1 0xd1] {::i/args [::r/cx ::r/dx]}
   [1 0xd2] {::i/args [::r/dx ::r/dx]}
   [1 0xd3] {::i/args [::r/bx ::r/dx]}
   [1 0xd4] {::i/args [::r/sp ::r/dx]}
   [1 0xd5] {::i/args [::r/bp ::r/dx]}
   [1 0xd6] {::i/args [::r/si ::r/dx]}
   [1 0xd7] {::i/args [::r/di ::r/dx]}
   [1 0xd8] {::i/args [::r/ax ::r/bx]}
   [1 0xd9] {::i/args [::r/cx ::r/bx]}
   [1 0xda] {::i/args [::r/dx ::r/bx]}
   [1 0xdb] {::i/args [::r/bx ::r/bx]}
   [1 0xdc] {::i/args [::r/sp ::r/bx]}
   [1 0xdd] {::i/args [::r/bp ::r/bx]}
   [1 0xde] {::i/args [::r/si ::r/bx]}
   [1 0xdf] {::i/args [::r/di ::r/bx]}
   [1 0xe0] {::i/args [::r/ax ::r/sp]}
   [1 0xe1] {::i/args [::r/cx ::r/sp]}
   [1 0xe2] {::i/args [::r/dx ::r/sp]}
   [1 0xe3] {::i/args [::r/bx ::r/sp]}
   [1 0xe4] {::i/args [::r/sp ::r/sp]}
   [1 0xe5] {::i/args [::r/bp ::r/sp]}
   [1 0xe6] {::i/args [::r/si ::r/sp]}
   [1 0xe7] {::i/args [::r/di ::r/sp]}
   [1 0xe8] {::i/args [::r/ax ::r/bp]}
   [1 0xe9] {::i/args [::r/cx ::r/bp]}
   [1 0xea] {::i/args [::r/dx ::r/bp]}
   [1 0xeb] {::i/args [::r/bx ::r/bp]}
   [1 0xec] {::i/args [::r/sp ::r/bp]}
   [1 0xed] {::i/args [::r/bp ::r/bp]}
   [1 0xee] {::i/args [::r/si ::r/bp]}
   [1 0xef] {::i/args [::r/di ::r/bp]}
   [1 0xf0] {::i/args [::r/ax ::r/si]}
   [1 0xf1] {::i/args [::r/cx ::r/si]}
   [1 0xf2] {::i/args [::r/dx ::r/si]}
   [1 0xf3] {::i/args [::r/bx ::r/si]}
   [1 0xf4] {::i/args [::r/sp ::r/si]}
   [1 0xf5] {::i/args [::r/bp ::r/si]}
   [1 0xf6] {::i/args [::r/si ::r/si]}
   [1 0xf7] {::i/args [::r/di ::r/si]}
   [1 0xf8] {::i/args [::r/ax ::r/di]}
   [1 0xf9] {::i/args [::r/cx ::r/di]}
   [1 0xfa] {::i/args [::r/dx ::r/di]}
   [1 0xfb] {::i/args [::r/bx ::r/di]}
   [1 0xfc] {::i/args [::r/sp ::r/di]}
   [1 0xfd] {::i/args [::r/bp ::r/di]}
   [1 0xfe] {::i/args [::r/si ::r/di]}
   [1 0xff] {::i/args [::r/di ::r/di]}
   [2 0x00] {::i/args [::r/al [::r/bx ::r/si]]}
   [2 0x01] {::i/args [::r/al [::r/bx ::r/di]]}
   [2 0x02] {::i/args [::r/al [::r/bp ::r/si]]}
   [2 0x03] {::i/args [::r/al [::r/bp ::r/di]]}
   [2 0x04] {::i/args [::r/al [::r/si]]}
   [2 0x05] {::i/args [::r/al [::r/di]]}
   [2 0x06 0xf0 0x0f] {::i/args [::r/al [0x0ff0]]}
   [2 0x06 0x0f 0xf0] {::i/args [::r/al [0xf00f]]}
   [2 0x07] {::i/args [::r/al [::r/bx]]}
   [2 0x08] {::i/args [::r/cl [::r/bx ::r/si]]}
   [2 0x09] {::i/args [::r/cl [::r/bx ::r/di]]}
   [2 0x0a] {::i/args [::r/cl [::r/bp ::r/si]]}
   [2 0x0b] {::i/args [::r/cl [::r/bp ::r/di]]}
   [2 0x0c] {::i/args [::r/cl [::r/si]]}
   [2 0x0d] {::i/args [::r/cl [::r/di]]}
   [2 0x0e 0xf0 0x0f] {::i/args [::r/cl [0x0ff0]]}
   [2 0x0e 0x0f 0xf0] {::i/args [::r/cl [0xf00f]]}
   [2 0x0f] {::i/args [::r/cl [::r/bx]]}
   [2 0x10] {::i/args [::r/dl [::r/bx ::r/si]]}
   [2 0x11] {::i/args [::r/dl [::r/bx ::r/di]]}
   [2 0x12] {::i/args [::r/dl [::r/bp ::r/si]]}
   [2 0x13] {::i/args [::r/dl [::r/bp ::r/di]]}
   [2 0x14] {::i/args [::r/dl [::r/si]]}
   [2 0x15] {::i/args [::r/dl [::r/di]]}
   [2 0x16 0xf0 0x0f] {::i/args [::r/dl [0x0ff0]]}
   [2 0x16 0x0f 0xf0] {::i/args [::r/dl [0xf00f]]}
   [2 0x17] {::i/args [::r/dl [::r/bx]]}
   [2 0x18] {::i/args [::r/bl [::r/bx ::r/si]]}
   [2 0x19] {::i/args [::r/bl [::r/bx ::r/di]]}
   [2 0x1a] {::i/args [::r/bl [::r/bp ::r/si]]}
   [2 0x1b] {::i/args [::r/bl [::r/bp ::r/di]]}
   [2 0x1c] {::i/args [::r/bl [::r/si]]}
   [2 0x1d] {::i/args [::r/bl [::r/di]]}
   [2 0x1e 0xf0 0x0f] {::i/args [::r/bl [0x0ff0]]}
   [2 0x1e 0x0f 0xf0] {::i/args [::r/bl [0xf00f]]}
   [2 0x1f] {::i/args [::r/bl [::r/bx]]}
   [2 0x20] {::i/args [::r/ah [::r/bx ::r/si]]}
   [2 0x21] {::i/args [::r/ah [::r/bx ::r/di]]}
   [2 0x22] {::i/args [::r/ah [::r/bp ::r/si]]}
   [2 0x23] {::i/args [::r/ah [::r/bp ::r/di]]}
   [2 0x24] {::i/args [::r/ah [::r/si]]}
   [2 0x25] {::i/args [::r/ah [::r/di]]}
   [2 0x26 0xf0 0x0f] {::i/args [::r/ah [0x0ff0]]}
   [2 0x26 0x0f 0xf0] {::i/args [::r/ah [0xf00f]]}
   [2 0x27] {::i/args [::r/ah [::r/bx]]}
   [2 0x28] {::i/args [::r/ch [::r/bx ::r/si]]}
   [2 0x29] {::i/args [::r/ch [::r/bx ::r/di]]}
   [2 0x2a] {::i/args [::r/ch [::r/bp ::r/si]]}
   [2 0x2b] {::i/args [::r/ch [::r/bp ::r/di]]}
   [2 0x2c] {::i/args [::r/ch [::r/si]]}
   [2 0x2d] {::i/args [::r/ch [::r/di]]}
   [2 0x2e 0xf0 0x0f] {::i/args [::r/ch [0x0ff0]]}
   [2 0x2e 0x0f 0xf0] {::i/args [::r/ch [0xf00f]]}
   [2 0x2f] {::i/args [::r/ch [::r/bx]]}
   [2 0x30] {::i/args [::r/dh [::r/bx ::r/si]]}
   [2 0x31] {::i/args [::r/dh [::r/bx ::r/di]]}
   [2 0x32] {::i/args [::r/dh [::r/bp ::r/si]]}
   [2 0x33] {::i/args [::r/dh [::r/bp ::r/di]]}
   [2 0x34] {::i/args [::r/dh [::r/si]]}
   [2 0x35] {::i/args [::r/dh [::r/di]]}
   [2 0x36 0xf0 0x0f] {::i/args [::r/dh [0x0ff0]]}
   [2 0x36 0x0f 0xf0] {::i/args [::r/dh [0xf00f]]}
   [2 0x37] {::i/args [::r/dh [::r/bx]]}
   [2 0x38] {::i/args [::r/bh [::r/bx ::r/si]]}
   [2 0x39] {::i/args [::r/bh [::r/bx ::r/di]]}
   [2 0x3a] {::i/args [::r/bh [::r/bp ::r/si]]}
   [2 0x3b] {::i/args [::r/bh [::r/bp ::r/di]]}
   [2 0x3c] {::i/args [::r/bh [::r/si]]}
   [2 0x3d] {::i/args [::r/bh [::r/di]]}
   [2 0x3e 0xf0 0x0f] {::i/args [::r/bh [0x0ff0]]}
   [2 0x3e 0x0f 0xf0] {::i/args [::r/bh [0xf00f]]}
   [2 0x3f] {::i/args [::r/bh [::r/bx]]}
   [2 0x40 0x00] {::i/args [::r/al [::r/bx ::r/si]]}
   [2 0x40 0xf0] {::i/args [::r/al [::r/bx ::r/si -16]]}
   [2 0x40 0x0f] {::i/args [::r/al [::r/bx ::r/si 15]]}
   [2 0x41 0x00] {::i/args [::r/al [::r/bx ::r/di]]}
   [2 0x41 0xf0] {::i/args [::r/al [::r/bx ::r/di -16]]}
   [2 0x41 0x0f] {::i/args [::r/al [::r/bx ::r/di 15]]}
   [2 0x42 0x00] {::i/args [::r/al [::r/bp ::r/si]]}
   [2 0x42 0xf0] {::i/args [::r/al [::r/bp ::r/si -16]]}
   [2 0x42 0x0f] {::i/args [::r/al [::r/bp ::r/si 15]]}
   [2 0x43 0x00] {::i/args [::r/al [::r/bp ::r/di]]}
   [2 0x43 0xf0] {::i/args [::r/al [::r/bp ::r/di -16]]}
   [2 0x43 0x0f] {::i/args [::r/al [::r/bp ::r/di 15]]}
   [2 0x44 0x00] {::i/args [::r/al [::r/si]]}
   [2 0x44 0xf0] {::i/args [::r/al [::r/si -16]]}
   [2 0x44 0x0f] {::i/args [::r/al [::r/si 15]]}
   [2 0x45 0x00] {::i/args [::r/al [::r/di]]}
   [2 0x45 0xf0] {::i/args [::r/al [::r/di -16]]}
   [2 0x45 0x0f] {::i/args [::r/al [::r/di 15]]}
   [2 0x46 0x00] {::i/args [::r/al [::r/bp]]}
   [2 0x46 0xf0] {::i/args [::r/al [::r/bp -16]]}
   [2 0x46 0x0f] {::i/args [::r/al [::r/bp 15]]}
   [2 0x47 0x00] {::i/args [::r/al [::r/bx]]}
   [2 0x47 0xf0] {::i/args [::r/al [::r/bx -16]]}
   [2 0x47 0x0f] {::i/args [::r/al [::r/bx 15]]}
   [2 0x48 0x00] {::i/args [::r/cl [::r/bx ::r/si]]}
   [2 0x48 0xf0] {::i/args [::r/cl [::r/bx ::r/si -16]]}
   [2 0x48 0x0f] {::i/args [::r/cl [::r/bx ::r/si 15]]}
   [2 0x49 0x00] {::i/args [::r/cl [::r/bx ::r/di]]}
   [2 0x49 0xf0] {::i/args [::r/cl [::r/bx ::r/di -16]]}
   [2 0x49 0x0f] {::i/args [::r/cl [::r/bx ::r/di 15]]}
   [2 0x4a 0x00] {::i/args [::r/cl [::r/bp ::r/si]]}
   [2 0x4a 0xf0] {::i/args [::r/cl [::r/bp ::r/si -16]]}
   [2 0x4a 0x0f] {::i/args [::r/cl [::r/bp ::r/si 15]]}
   [2 0x4b 0x00] {::i/args [::r/cl [::r/bp ::r/di]]}
   [2 0x4b 0xf0] {::i/args [::r/cl [::r/bp ::r/di -16]]}
   [2 0x4b 0x0f] {::i/args [::r/cl [::r/bp ::r/di 15]]}
   [2 0x4c 0x00] {::i/args [::r/cl [::r/si]]}
   [2 0x4c 0xf0] {::i/args [::r/cl [::r/si -16]]}
   [2 0x4c 0x0f] {::i/args [::r/cl [::r/si 15]]}
   [2 0x4d 0x00] {::i/args [::r/cl [::r/di]]}
   [2 0x4d 0xf0] {::i/args [::r/cl [::r/di -16]]}
   [2 0x4d 0x0f] {::i/args [::r/cl [::r/di 15]]}
   [2 0x4e 0x00] {::i/args [::r/cl [::r/bp]]}
   [2 0x4e 0xf0] {::i/args [::r/cl [::r/bp -16]]}
   [2 0x4e 0x0f] {::i/args [::r/cl [::r/bp 15]]}
   [2 0x4f 0x00] {::i/args [::r/cl [::r/bx]]}
   [2 0x4f 0xf0] {::i/args [::r/cl [::r/bx -16]]}
   [2 0x4f 0x0f] {::i/args [::r/cl [::r/bx 15]]}
   [2 0x50 0x00] {::i/args [::r/dl [::r/bx ::r/si]]}
   [2 0x50 0xf0] {::i/args [::r/dl [::r/bx ::r/si -16]]}
   [2 0x50 0x0f] {::i/args [::r/dl [::r/bx ::r/si 15]]}
   [2 0x51 0x00] {::i/args [::r/dl [::r/bx ::r/di]]}
   [2 0x51 0xf0] {::i/args [::r/dl [::r/bx ::r/di -16]]}
   [2 0x51 0x0f] {::i/args [::r/dl [::r/bx ::r/di 15]]}
   [2 0x52 0x00] {::i/args [::r/dl [::r/bp ::r/si]]}
   [2 0x52 0xf0] {::i/args [::r/dl [::r/bp ::r/si -16]]}
   [2 0x52 0x0f] {::i/args [::r/dl [::r/bp ::r/si 15]]}
   [2 0x53 0x00] {::i/args [::r/dl [::r/bp ::r/di]]}
   [2 0x53 0xf0] {::i/args [::r/dl [::r/bp ::r/di -16]]}
   [2 0x53 0x0f] {::i/args [::r/dl [::r/bp ::r/di 15]]}
   [2 0x54 0x00] {::i/args [::r/dl [::r/si]]}
   [2 0x54 0xf0] {::i/args [::r/dl [::r/si -16]]}
   [2 0x54 0x0f] {::i/args [::r/dl [::r/si 15]]}
   [2 0x55 0x00] {::i/args [::r/dl [::r/di]]}
   [2 0x55 0xf0] {::i/args [::r/dl [::r/di -16]]}
   [2 0x55 0x0f] {::i/args [::r/dl [::r/di 15]]}
   [2 0x56 0x00] {::i/args [::r/dl [::r/bp]]}
   [2 0x56 0xf0] {::i/args [::r/dl [::r/bp -16]]}
   [2 0x56 0x0f] {::i/args [::r/dl [::r/bp 15]]}
   [2 0x57 0x00] {::i/args [::r/dl [::r/bx]]}
   [2 0x57 0xf0] {::i/args [::r/dl [::r/bx -16]]}
   [2 0x57 0x0f] {::i/args [::r/dl [::r/bx 15]]}
   [2 0x58 0x00] {::i/args [::r/bl [::r/bx ::r/si]]}
   [2 0x58 0xf0] {::i/args [::r/bl [::r/bx ::r/si -16]]}
   [2 0x58 0x0f] {::i/args [::r/bl [::r/bx ::r/si 15]]}
   [2 0x59 0x00] {::i/args [::r/bl [::r/bx ::r/di]]}
   [2 0x59 0xf0] {::i/args [::r/bl [::r/bx ::r/di -16]]}
   [2 0x59 0x0f] {::i/args [::r/bl [::r/bx ::r/di 15]]}
   [2 0x5a 0x00] {::i/args [::r/bl [::r/bp ::r/si]]}
   [2 0x5a 0xf0] {::i/args [::r/bl [::r/bp ::r/si -16]]}
   [2 0x5a 0x0f] {::i/args [::r/bl [::r/bp ::r/si 15]]}
   [2 0x5b 0x00] {::i/args [::r/bl [::r/bp ::r/di]]}
   [2 0x5b 0xf0] {::i/args [::r/bl [::r/bp ::r/di -16]]}
   [2 0x5b 0x0f] {::i/args [::r/bl [::r/bp ::r/di 15]]}
   [2 0x5c 0x00] {::i/args [::r/bl [::r/si]]}
   [2 0x5c 0xf0] {::i/args [::r/bl [::r/si -16]]}
   [2 0x5c 0x0f] {::i/args [::r/bl [::r/si 15]]}
   [2 0x5d 0x00] {::i/args [::r/bl [::r/di]]}
   [2 0x5d 0xf0] {::i/args [::r/bl [::r/di -16]]}
   [2 0x5d 0x0f] {::i/args [::r/bl [::r/di 15]]}
   [2 0x5e 0x00] {::i/args [::r/bl [::r/bp]]}
   [2 0x5e 0xf0] {::i/args [::r/bl [::r/bp -16]]}
   [2 0x5e 0x0f] {::i/args [::r/bl [::r/bp 15]]}
   [2 0x5f 0x00] {::i/args [::r/bl [::r/bx]]}
   [2 0x5f 0xf0] {::i/args [::r/bl [::r/bx -16]]}
   [2 0x5f 0x0f] {::i/args [::r/bl [::r/bx 15]]}
   [2 0x60 0x00] {::i/args [::r/ah [::r/bx ::r/si]]}
   [2 0x60 0xf0] {::i/args [::r/ah [::r/bx ::r/si -16]]}
   [2 0x60 0x0f] {::i/args [::r/ah [::r/bx ::r/si 15]]}
   [2 0x61 0x00] {::i/args [::r/ah [::r/bx ::r/di]]}
   [2 0x61 0xf0] {::i/args [::r/ah [::r/bx ::r/di -16]]}
   [2 0x61 0x0f] {::i/args [::r/ah [::r/bx ::r/di 15]]}
   [2 0x62 0x00] {::i/args [::r/ah [::r/bp ::r/si]]}
   [2 0x62 0xf0] {::i/args [::r/ah [::r/bp ::r/si -16]]}
   [2 0x62 0x0f] {::i/args [::r/ah [::r/bp ::r/si 15]]}
   [2 0x63 0x00] {::i/args [::r/ah [::r/bp ::r/di]]}
   [2 0x63 0xf0] {::i/args [::r/ah [::r/bp ::r/di -16]]}
   [2 0x63 0x0f] {::i/args [::r/ah [::r/bp ::r/di 15]]}
   [2 0x64 0x00] {::i/args [::r/ah [::r/si]]}
   [2 0x64 0xf0] {::i/args [::r/ah [::r/si -16]]}
   [2 0x64 0x0f] {::i/args [::r/ah [::r/si 15]]}
   [2 0x65 0x00] {::i/args [::r/ah [::r/di]]}
   [2 0x65 0xf0] {::i/args [::r/ah [::r/di -16]]}
   [2 0x65 0x0f] {::i/args [::r/ah [::r/di 15]]}
   [2 0x66 0x00] {::i/args [::r/ah [::r/bp]]}
   [2 0x66 0xf0] {::i/args [::r/ah [::r/bp -16]]}
   [2 0x66 0x0f] {::i/args [::r/ah [::r/bp 15]]}
   [2 0x67 0x00] {::i/args [::r/ah [::r/bx]]}
   [2 0x67 0xf0] {::i/args [::r/ah [::r/bx -16]]}
   [2 0x67 0x0f] {::i/args [::r/ah [::r/bx 15]]}
   [2 0x68 0x00] {::i/args [::r/ch [::r/bx ::r/si]]}
   [2 0x68 0xf0] {::i/args [::r/ch [::r/bx ::r/si -16]]}
   [2 0x68 0x0f] {::i/args [::r/ch [::r/bx ::r/si 15]]}
   [2 0x69 0x00] {::i/args [::r/ch [::r/bx ::r/di]]}
   [2 0x69 0xf0] {::i/args [::r/ch [::r/bx ::r/di -16]]}
   [2 0x69 0x0f] {::i/args [::r/ch [::r/bx ::r/di 15]]}
   [2 0x6a 0x00] {::i/args [::r/ch [::r/bp ::r/si]]}
   [2 0x6a 0xf0] {::i/args [::r/ch [::r/bp ::r/si -16]]}
   [2 0x6a 0x0f] {::i/args [::r/ch [::r/bp ::r/si 15]]}
   [2 0x6b 0x00] {::i/args [::r/ch [::r/bp ::r/di]]}
   [2 0x6b 0xf0] {::i/args [::r/ch [::r/bp ::r/di -16]]}
   [2 0x6b 0x0f] {::i/args [::r/ch [::r/bp ::r/di 15]]}
   [2 0x6c 0x00] {::i/args [::r/ch [::r/si]]}
   [2 0x6c 0xf0] {::i/args [::r/ch [::r/si -16]]}
   [2 0x6c 0x0f] {::i/args [::r/ch [::r/si 15]]}
   [2 0x6d 0x00] {::i/args [::r/ch [::r/di]]}
   [2 0x6d 0xf0] {::i/args [::r/ch [::r/di -16]]}
   [2 0x6d 0x0f] {::i/args [::r/ch [::r/di 15]]}
   [2 0x6e 0x00] {::i/args [::r/ch [::r/bp]]}
   [2 0x6e 0xf0] {::i/args [::r/ch [::r/bp -16]]}
   [2 0x6e 0x0f] {::i/args [::r/ch [::r/bp 15]]}
   [2 0x6f 0x00] {::i/args [::r/ch [::r/bx]]}
   [2 0x6f 0xf0] {::i/args [::r/ch [::r/bx -16]]}
   [2 0x6f 0x0f] {::i/args [::r/ch [::r/bx 15]]}
   [2 0x70 0x00] {::i/args [::r/dh [::r/bx ::r/si]]}
   [2 0x70 0xf0] {::i/args [::r/dh [::r/bx ::r/si -16]]}
   [2 0x70 0x0f] {::i/args [::r/dh [::r/bx ::r/si 15]]}
   [2 0x71 0x00] {::i/args [::r/dh [::r/bx ::r/di]]}
   [2 0x71 0xf0] {::i/args [::r/dh [::r/bx ::r/di -16]]}
   [2 0x71 0x0f] {::i/args [::r/dh [::r/bx ::r/di 15]]}
   [2 0x72 0x00] {::i/args [::r/dh [::r/bp ::r/si]]}
   [2 0x72 0xf0] {::i/args [::r/dh [::r/bp ::r/si -16]]}
   [2 0x72 0x0f] {::i/args [::r/dh [::r/bp ::r/si 15]]}
   [2 0x73 0x00] {::i/args [::r/dh [::r/bp ::r/di]]}
   [2 0x73 0xf0] {::i/args [::r/dh [::r/bp ::r/di -16]]}
   [2 0x73 0x0f] {::i/args [::r/dh [::r/bp ::r/di 15]]}
   [2 0x74 0x00] {::i/args [::r/dh [::r/si]]}
   [2 0x74 0xf0] {::i/args [::r/dh [::r/si -16]]}
   [2 0x74 0x0f] {::i/args [::r/dh [::r/si 15]]}
   [2 0x75 0x00] {::i/args [::r/dh [::r/di]]}
   [2 0x75 0xf0] {::i/args [::r/dh [::r/di -16]]}
   [2 0x75 0x0f] {::i/args [::r/dh [::r/di 15]]}
   [2 0x76 0x00] {::i/args [::r/dh [::r/bp]]}
   [2 0x76 0xf0] {::i/args [::r/dh [::r/bp -16]]}
   [2 0x76 0x0f] {::i/args [::r/dh [::r/bp 15]]}
   [2 0x77 0x00] {::i/args [::r/dh [::r/bx]]}
   [2 0x77 0xf0] {::i/args [::r/dh [::r/bx -16]]}
   [2 0x77 0x0f] {::i/args [::r/dh [::r/bx 15]]}
   [2 0x78 0x00] {::i/args [::r/bh [::r/bx ::r/si]]}
   [2 0x78 0xf0] {::i/args [::r/bh [::r/bx ::r/si -16]]}
   [2 0x78 0x0f] {::i/args [::r/bh [::r/bx ::r/si 15]]}
   [2 0x79 0x00] {::i/args [::r/bh [::r/bx ::r/di]]}
   [2 0x79 0xf0] {::i/args [::r/bh [::r/bx ::r/di -16]]}
   [2 0x79 0x0f] {::i/args [::r/bh [::r/bx ::r/di 15]]}
   [2 0x7a 0x00] {::i/args [::r/bh [::r/bp ::r/si]]}
   [2 0x7a 0xf0] {::i/args [::r/bh [::r/bp ::r/si -16]]}
   [2 0x7a 0x0f] {::i/args [::r/bh [::r/bp ::r/si 15]]}
   [2 0x7b 0x00] {::i/args [::r/bh [::r/bp ::r/di]]}
   [2 0x7b 0xf0] {::i/args [::r/bh [::r/bp ::r/di -16]]}
   [2 0x7b 0x0f] {::i/args [::r/bh [::r/bp ::r/di 15]]}
   [2 0x7c 0x00] {::i/args [::r/bh [::r/si]]}
   [2 0x7c 0xf0] {::i/args [::r/bh [::r/si -16]]}
   [2 0x7c 0x0f] {::i/args [::r/bh [::r/si 15]]}
   [2 0x7d 0x00] {::i/args [::r/bh [::r/di]]}
   [2 0x7d 0xf0] {::i/args [::r/bh [::r/di -16]]}
   [2 0x7d 0x0f] {::i/args [::r/bh [::r/di 15]]}
   [2 0x7e 0x00] {::i/args [::r/bh [::r/bp]]}
   [2 0x7e 0xf0] {::i/args [::r/bh [::r/bp -16]]}
   [2 0x7e 0x0f] {::i/args [::r/bh [::r/bp 15]]}
   [2 0x7f 0x00] {::i/args [::r/bh [::r/bx]]}
   [2 0x7f 0xf0] {::i/args [::r/bh [::r/bx -16]]}
   [2 0x7f 0x0f] {::i/args [::r/bh [::r/bx 15]]}
   [2 0x80 0x00 0x00] {::i/args [::r/al [::r/bx ::r/si]]}
   [2 0x80 0xf0 0x0f] {::i/args [::r/al [::r/bx ::r/si 4080]]}
   [2 0x80 0x0f 0xf0] {::i/args [::r/al [::r/bx ::r/si -4081]]}
   [2 0x81 0x00 0x00] {::i/args [::r/al [::r/bx ::r/di]]}
   [2 0x81 0xf0 0x0f] {::i/args [::r/al [::r/bx ::r/di 4080]]}
   [2 0x81 0x0f 0xf0] {::i/args [::r/al [::r/bx ::r/di -4081]]}
   [2 0x82 0x00 0x00] {::i/args [::r/al [::r/bp ::r/si]]}
   [2 0x82 0xf0 0x0f] {::i/args [::r/al [::r/bp ::r/si 4080]]}
   [2 0x82 0x0f 0xf0] {::i/args [::r/al [::r/bp ::r/si -4081]]}
   [2 0x83 0x00 0x00] {::i/args [::r/al [::r/bp ::r/di]]}
   [2 0x83 0xf0 0x0f] {::i/args [::r/al [::r/bp ::r/di 4080]]}
   [2 0x83 0x0f 0xf0] {::i/args [::r/al [::r/bp ::r/di -4081]]}
   [2 0x84 0x00 0x00] {::i/args [::r/al [::r/si]]}
   [2 0x84 0xf0 0x0f] {::i/args [::r/al [::r/si 4080]]}
   [2 0x84 0x0f 0xf0] {::i/args [::r/al [::r/si -4081]]}
   [2 0x85 0x00 0x00] {::i/args [::r/al [::r/di]]}
   [2 0x85 0xf0 0x0f] {::i/args [::r/al [::r/di 4080]]}
   [2 0x85 0x0f 0xf0] {::i/args [::r/al [::r/di -4081]]}
   [2 0x86 0x00 0x00] {::i/args [::r/al [::r/bp]]}
   [2 0x86 0xf0 0x0f] {::i/args [::r/al [::r/bp 4080]]}
   [2 0x86 0x0f 0xf0] {::i/args [::r/al [::r/bp -4081]]}
   [2 0x87 0x00 0x00] {::i/args [::r/al [::r/bx]]}
   [2 0x87 0xf0 0x0f] {::i/args [::r/al [::r/bx 4080]]}
   [2 0x87 0x0f 0xf0] {::i/args [::r/al [::r/bx -4081]]}
   [2 0x88 0x00 0x00] {::i/args [::r/cl [::r/bx ::r/si]]}
   [2 0x88 0xf0 0x0f] {::i/args [::r/cl [::r/bx ::r/si 4080]]}
   [2 0x88 0x0f 0xf0] {::i/args [::r/cl [::r/bx ::r/si -4081]]}
   [2 0x89 0x00 0x00] {::i/args [::r/cl [::r/bx ::r/di]]}
   [2 0x89 0xf0 0x0f] {::i/args [::r/cl [::r/bx ::r/di 4080]]}
   [2 0x89 0x0f 0xf0] {::i/args [::r/cl [::r/bx ::r/di -4081]]}
   [2 0x8a 0x00 0x00] {::i/args [::r/cl [::r/bp ::r/si]]}
   [2 0x8a 0xf0 0x0f] {::i/args [::r/cl [::r/bp ::r/si 4080]]}
   [2 0x8a 0x0f 0xf0] {::i/args [::r/cl [::r/bp ::r/si -4081]]}
   [2 0x8b 0x00 0x00] {::i/args [::r/cl [::r/bp ::r/di]]}
   [2 0x8b 0xf0 0x0f] {::i/args [::r/cl [::r/bp ::r/di 4080]]}
   [2 0x8b 0x0f 0xf0] {::i/args [::r/cl [::r/bp ::r/di -4081]]}
   [2 0x8c 0x00 0x00] {::i/args [::r/cl [::r/si]]}
   [2 0x8c 0xf0 0x0f] {::i/args [::r/cl [::r/si 4080]]}
   [2 0x8c 0x0f 0xf0] {::i/args [::r/cl [::r/si -4081]]}
   [2 0x8d 0x00 0x00] {::i/args [::r/cl [::r/di]]}
   [2 0x8d 0xf0 0x0f] {::i/args [::r/cl [::r/di 4080]]}
   [2 0x8d 0x0f 0xf0] {::i/args [::r/cl [::r/di -4081]]}
   [2 0x8e 0x00 0x00] {::i/args [::r/cl [::r/bp]]}
   [2 0x8e 0xf0 0x0f] {::i/args [::r/cl [::r/bp 4080]]}
   [2 0x8e 0x0f 0xf0] {::i/args [::r/cl [::r/bp -4081]]}
   [2 0x8f 0x00 0x00] {::i/args [::r/cl [::r/bx]]}
   [2 0x8f 0xf0 0x0f] {::i/args [::r/cl [::r/bx 4080]]}
   [2 0x8f 0x0f 0xf0] {::i/args [::r/cl [::r/bx -4081]]}
   [2 0x90 0x00 0x00] {::i/args [::r/dl [::r/bx ::r/si]]}
   [2 0x90 0xf0 0x0f] {::i/args [::r/dl [::r/bx ::r/si 4080]]}
   [2 0x90 0x0f 0xf0] {::i/args [::r/dl [::r/bx ::r/si -4081]]}
   [2 0x91 0x00 0x00] {::i/args [::r/dl [::r/bx ::r/di]]}
   [2 0x91 0xf0 0x0f] {::i/args [::r/dl [::r/bx ::r/di 4080]]}
   [2 0x91 0x0f 0xf0] {::i/args [::r/dl [::r/bx ::r/di -4081]]}
   [2 0x92 0x00 0x00] {::i/args [::r/dl [::r/bp ::r/si]]}
   [2 0x92 0xf0 0x0f] {::i/args [::r/dl [::r/bp ::r/si 4080]]}
   [2 0x92 0x0f 0xf0] {::i/args [::r/dl [::r/bp ::r/si -4081]]}
   [2 0x93 0x00 0x00] {::i/args [::r/dl [::r/bp ::r/di]]}
   [2 0x93 0xf0 0x0f] {::i/args [::r/dl [::r/bp ::r/di 4080]]}
   [2 0x93 0x0f 0xf0] {::i/args [::r/dl [::r/bp ::r/di -4081]]}
   [2 0x94 0x00 0x00] {::i/args [::r/dl [::r/si]]}
   [2 0x94 0xf0 0x0f] {::i/args [::r/dl [::r/si 4080]]}
   [2 0x94 0x0f 0xf0] {::i/args [::r/dl [::r/si -4081]]}
   [2 0x95 0x00 0x00] {::i/args [::r/dl [::r/di]]}
   [2 0x95 0xf0 0x0f] {::i/args [::r/dl [::r/di 4080]]}
   [2 0x95 0x0f 0xf0] {::i/args [::r/dl [::r/di -4081]]}
   [2 0x96 0x00 0x00] {::i/args [::r/dl [::r/bp]]}
   [2 0x96 0xf0 0x0f] {::i/args [::r/dl [::r/bp 4080]]}
   [2 0x96 0x0f 0xf0] {::i/args [::r/dl [::r/bp -4081]]}
   [2 0x97 0x00 0x00] {::i/args [::r/dl [::r/bx]]}
   [2 0x97 0xf0 0x0f] {::i/args [::r/dl [::r/bx 4080]]}
   [2 0x97 0x0f 0xf0] {::i/args [::r/dl [::r/bx -4081]]}
   [2 0x98 0x00 0x00] {::i/args [::r/bl [::r/bx ::r/si]]}
   [2 0x98 0xf0 0x0f] {::i/args [::r/bl [::r/bx ::r/si 4080]]}
   [2 0x98 0x0f 0xf0] {::i/args [::r/bl [::r/bx ::r/si -4081]]}
   [2 0x99 0x00 0x00] {::i/args [::r/bl [::r/bx ::r/di]]}
   [2 0x99 0xf0 0x0f] {::i/args [::r/bl [::r/bx ::r/di 4080]]}
   [2 0x99 0x0f 0xf0] {::i/args [::r/bl [::r/bx ::r/di -4081]]}
   [2 0x9a 0x00 0x00] {::i/args [::r/bl [::r/bp ::r/si]]}
   [2 0x9a 0xf0 0x0f] {::i/args [::r/bl [::r/bp ::r/si 4080]]}
   [2 0x9a 0x0f 0xf0] {::i/args [::r/bl [::r/bp ::r/si -4081]]}
   [2 0x9b 0x00 0x00] {::i/args [::r/bl [::r/bp ::r/di]]}
   [2 0x9b 0xf0 0x0f] {::i/args [::r/bl [::r/bp ::r/di 4080]]}
   [2 0x9b 0x0f 0xf0] {::i/args [::r/bl [::r/bp ::r/di -4081]]}
   [2 0x9c 0x00 0x00] {::i/args [::r/bl [::r/si]]}
   [2 0x9c 0xf0 0x0f] {::i/args [::r/bl [::r/si 4080]]}
   [2 0x9c 0x0f 0xf0] {::i/args [::r/bl [::r/si -4081]]}
   [2 0x9d 0x00 0x00] {::i/args [::r/bl [::r/di]]}
   [2 0x9d 0xf0 0x0f] {::i/args [::r/bl [::r/di 4080]]}
   [2 0x9d 0x0f 0xf0] {::i/args [::r/bl [::r/di -4081]]}
   [2 0x9e 0x00 0x00] {::i/args [::r/bl [::r/bp]]}
   [2 0x9e 0xf0 0x0f] {::i/args [::r/bl [::r/bp 4080]]}
   [2 0x9e 0x0f 0xf0] {::i/args [::r/bl [::r/bp -4081]]}
   [2 0x9f 0x00 0x00] {::i/args [::r/bl [::r/bx]]}
   [2 0x9f 0xf0 0x0f] {::i/args [::r/bl [::r/bx 4080]]}
   [2 0x9f 0x0f 0xf0] {::i/args [::r/bl [::r/bx -4081]]}
   [2 0xa0 0x00 0x00] {::i/args [::r/ah [::r/bx ::r/si]]}
   [2 0xa0 0xf0 0x0f] {::i/args [::r/ah [::r/bx ::r/si 4080]]}
   [2 0xa0 0x0f 0xf0] {::i/args [::r/ah [::r/bx ::r/si -4081]]}
   [2 0xa1 0x00 0x00] {::i/args [::r/ah [::r/bx ::r/di]]}
   [2 0xa1 0xf0 0x0f] {::i/args [::r/ah [::r/bx ::r/di 4080]]}
   [2 0xa1 0x0f 0xf0] {::i/args [::r/ah [::r/bx ::r/di -4081]]}
   [2 0xa2 0x00 0x00] {::i/args [::r/ah [::r/bp ::r/si]]}
   [2 0xa2 0xf0 0x0f] {::i/args [::r/ah [::r/bp ::r/si 4080]]}
   [2 0xa2 0x0f 0xf0] {::i/args [::r/ah [::r/bp ::r/si -4081]]}
   [2 0xa3 0x00 0x00] {::i/args [::r/ah [::r/bp ::r/di]]}
   [2 0xa3 0xf0 0x0f] {::i/args [::r/ah [::r/bp ::r/di 4080]]}
   [2 0xa3 0x0f 0xf0] {::i/args [::r/ah [::r/bp ::r/di -4081]]}
   [2 0xa4 0x00 0x00] {::i/args [::r/ah [::r/si]]}
   [2 0xa4 0xf0 0x0f] {::i/args [::r/ah [::r/si 4080]]}
   [2 0xa4 0x0f 0xf0] {::i/args [::r/ah [::r/si -4081]]}
   [2 0xa5 0x00 0x00] {::i/args [::r/ah [::r/di]]}
   [2 0xa5 0xf0 0x0f] {::i/args [::r/ah [::r/di 4080]]}
   [2 0xa5 0x0f 0xf0] {::i/args [::r/ah [::r/di -4081]]}
   [2 0xa6 0x00 0x00] {::i/args [::r/ah [::r/bp]]}
   [2 0xa6 0xf0 0x0f] {::i/args [::r/ah [::r/bp 4080]]}
   [2 0xa6 0x0f 0xf0] {::i/args [::r/ah [::r/bp -4081]]}
   [2 0xa7 0x00 0x00] {::i/args [::r/ah [::r/bx]]}
   [2 0xa7 0xf0 0x0f] {::i/args [::r/ah [::r/bx 4080]]}
   [2 0xa7 0x0f 0xf0] {::i/args [::r/ah [::r/bx -4081]]}
   [2 0xa8 0x00 0x00] {::i/args [::r/ch [::r/bx ::r/si]]}
   [2 0xa8 0xf0 0x0f] {::i/args [::r/ch [::r/bx ::r/si 4080]]}
   [2 0xa8 0x0f 0xf0] {::i/args [::r/ch [::r/bx ::r/si -4081]]}
   [2 0xa9 0x00 0x00] {::i/args [::r/ch [::r/bx ::r/di]]}
   [2 0xa9 0xf0 0x0f] {::i/args [::r/ch [::r/bx ::r/di 4080]]}
   [2 0xa9 0x0f 0xf0] {::i/args [::r/ch [::r/bx ::r/di -4081]]}
   [2 0xaa 0x00 0x00] {::i/args [::r/ch [::r/bp ::r/si]]}
   [2 0xaa 0xf0 0x0f] {::i/args [::r/ch [::r/bp ::r/si 4080]]}
   [2 0xaa 0x0f 0xf0] {::i/args [::r/ch [::r/bp ::r/si -4081]]}
   [2 0xab 0x00 0x00] {::i/args [::r/ch [::r/bp ::r/di]]}
   [2 0xab 0xf0 0x0f] {::i/args [::r/ch [::r/bp ::r/di 4080]]}
   [2 0xab 0x0f 0xf0] {::i/args [::r/ch [::r/bp ::r/di -4081]]}
   [2 0xac 0x00 0x00] {::i/args [::r/ch [::r/si]]}
   [2 0xac 0xf0 0x0f] {::i/args [::r/ch [::r/si 4080]]}
   [2 0xac 0x0f 0xf0] {::i/args [::r/ch [::r/si -4081]]}
   [2 0xad 0x00 0x00] {::i/args [::r/ch [::r/di]]}
   [2 0xad 0xf0 0x0f] {::i/args [::r/ch [::r/di 4080]]}
   [2 0xad 0x0f 0xf0] {::i/args [::r/ch [::r/di -4081]]}
   [2 0xae 0x00 0x00] {::i/args [::r/ch [::r/bp]]}
   [2 0xae 0xf0 0x0f] {::i/args [::r/ch [::r/bp 4080]]}
   [2 0xae 0x0f 0xf0] {::i/args [::r/ch [::r/bp -4081]]}
   [2 0xaf 0x00 0x00] {::i/args [::r/ch [::r/bx]]}
   [2 0xaf 0xf0 0x0f] {::i/args [::r/ch [::r/bx 4080]]}
   [2 0xaf 0x0f 0xf0] {::i/args [::r/ch [::r/bx -4081]]}
   [2 0xb0 0x00 0x00] {::i/args [::r/dh [::r/bx ::r/si]]}
   [2 0xb0 0xf0 0x0f] {::i/args [::r/dh [::r/bx ::r/si 4080]]}
   [2 0xb0 0x0f 0xf0] {::i/args [::r/dh [::r/bx ::r/si -4081]]}
   [2 0xb1 0x00 0x00] {::i/args [::r/dh [::r/bx ::r/di]]}
   [2 0xb1 0xf0 0x0f] {::i/args [::r/dh [::r/bx ::r/di 4080]]}
   [2 0xb1 0x0f 0xf0] {::i/args [::r/dh [::r/bx ::r/di -4081]]}
   [2 0xb2 0x00 0x00] {::i/args [::r/dh [::r/bp ::r/si]]}
   [2 0xb2 0xf0 0x0f] {::i/args [::r/dh [::r/bp ::r/si 4080]]}
   [2 0xb2 0x0f 0xf0] {::i/args [::r/dh [::r/bp ::r/si -4081]]}
   [2 0xb3 0x00 0x00] {::i/args [::r/dh [::r/bp ::r/di]]}
   [2 0xb3 0xf0 0x0f] {::i/args [::r/dh [::r/bp ::r/di 4080]]}
   [2 0xb3 0x0f 0xf0] {::i/args [::r/dh [::r/bp ::r/di -4081]]}
   [2 0xb4 0x00 0x00] {::i/args [::r/dh [::r/si]]}
   [2 0xb4 0xf0 0x0f] {::i/args [::r/dh [::r/si 4080]]}
   [2 0xb4 0x0f 0xf0] {::i/args [::r/dh [::r/si -4081]]}
   [2 0xb5 0x00 0x00] {::i/args [::r/dh [::r/di]]}
   [2 0xb5 0xf0 0x0f] {::i/args [::r/dh [::r/di 4080]]}
   [2 0xb5 0x0f 0xf0] {::i/args [::r/dh [::r/di -4081]]}
   [2 0xb6 0x00 0x00] {::i/args [::r/dh [::r/bp]]}
   [2 0xb6 0xf0 0x0f] {::i/args [::r/dh [::r/bp 4080]]}
   [2 0xb6 0x0f 0xf0] {::i/args [::r/dh [::r/bp -4081]]}
   [2 0xb7 0x00 0x00] {::i/args [::r/dh [::r/bx]]}
   [2 0xb7 0xf0 0x0f] {::i/args [::r/dh [::r/bx 4080]]}
   [2 0xb7 0x0f 0xf0] {::i/args [::r/dh [::r/bx -4081]]}
   [2 0xb8 0x00 0x00] {::i/args [::r/bh [::r/bx ::r/si]]}
   [2 0xb8 0xf0 0x0f] {::i/args [::r/bh [::r/bx ::r/si 4080]]}
   [2 0xb8 0x0f 0xf0] {::i/args [::r/bh [::r/bx ::r/si -4081]]}
   [2 0xb9 0x00 0x00] {::i/args [::r/bh [::r/bx ::r/di]]}
   [2 0xb9 0xf0 0x0f] {::i/args [::r/bh [::r/bx ::r/di 4080]]}
   [2 0xb9 0x0f 0xf0] {::i/args [::r/bh [::r/bx ::r/di -4081]]}
   [2 0xba 0x00 0x00] {::i/args [::r/bh [::r/bp ::r/si]]}
   [2 0xba 0xf0 0x0f] {::i/args [::r/bh [::r/bp ::r/si 4080]]}
   [2 0xba 0x0f 0xf0] {::i/args [::r/bh [::r/bp ::r/si -4081]]}
   [2 0xbb 0x00 0x00] {::i/args [::r/bh [::r/bp ::r/di]]}
   [2 0xbb 0xf0 0x0f] {::i/args [::r/bh [::r/bp ::r/di 4080]]}
   [2 0xbb 0x0f 0xf0] {::i/args [::r/bh [::r/bp ::r/di -4081]]}
   [2 0xbc 0x00 0x00] {::i/args [::r/bh [::r/si]]}
   [2 0xbc 0xf0 0x0f] {::i/args [::r/bh [::r/si 4080]]}
   [2 0xbc 0x0f 0xf0] {::i/args [::r/bh [::r/si -4081]]}
   [2 0xbd 0x00 0x00] {::i/args [::r/bh [::r/di]]}
   [2 0xbd 0xf0 0x0f] {::i/args [::r/bh [::r/di 4080]]}
   [2 0xbd 0x0f 0xf0] {::i/args [::r/bh [::r/di -4081]]}
   [2 0xbe 0x00 0x00] {::i/args [::r/bh [::r/bp]]}
   [2 0xbe 0xf0 0x0f] {::i/args [::r/bh [::r/bp 4080]]}
   [2 0xbe 0x0f 0xf0] {::i/args [::r/bh [::r/bp -4081]]}
   [2 0xbf 0x00 0x00] {::i/args [::r/bh [::r/bx]]}
   [2 0xbf 0xf0 0x0f] {::i/args [::r/bh [::r/bx 4080]]}
   [2 0xbf 0x0f 0xf0] {::i/args [::r/bh [::r/bx -4081]]}
   [2 0xc0] {::i/args [::r/al ::r/al]}
   [2 0xc1] {::i/args [::r/al ::r/cl]}
   [2 0xc2] {::i/args [::r/al ::r/dl]}
   [2 0xc3] {::i/args [::r/al ::r/bl]}
   [2 0xc4] {::i/args [::r/al ::r/ah]}
   [2 0xc5] {::i/args [::r/al ::r/ch]}
   [2 0xc6] {::i/args [::r/al ::r/dh]}
   [2 0xc7] {::i/args [::r/al ::r/bh]}
   [2 0xc8] {::i/args [::r/cl ::r/al]}
   [2 0xc9] {::i/args [::r/cl ::r/cl]}
   [2 0xca] {::i/args [::r/cl ::r/dl]}
   [2 0xcb] {::i/args [::r/cl ::r/bl]}
   [2 0xcc] {::i/args [::r/cl ::r/ah]}
   [2 0xcd] {::i/args [::r/cl ::r/ch]}
   [2 0xce] {::i/args [::r/cl ::r/dh]}
   [2 0xcf] {::i/args [::r/cl ::r/bh]}
   [2 0xd0] {::i/args [::r/dl ::r/al]}
   [2 0xd1] {::i/args [::r/dl ::r/cl]}
   [2 0xd2] {::i/args [::r/dl ::r/dl]}
   [2 0xd3] {::i/args [::r/dl ::r/bl]}
   [2 0xd4] {::i/args [::r/dl ::r/ah]}
   [2 0xd5] {::i/args [::r/dl ::r/ch]}
   [2 0xd6] {::i/args [::r/dl ::r/dh]}
   [2 0xd7] {::i/args [::r/dl ::r/bh]}
   [2 0xd8] {::i/args [::r/bl ::r/al]}
   [2 0xd9] {::i/args [::r/bl ::r/cl]}
   [2 0xda] {::i/args [::r/bl ::r/dl]}
   [2 0xdb] {::i/args [::r/bl ::r/bl]}
   [2 0xdc] {::i/args [::r/bl ::r/ah]}
   [2 0xdd] {::i/args [::r/bl ::r/ch]}
   [2 0xde] {::i/args [::r/bl ::r/dh]}
   [2 0xdf] {::i/args [::r/bl ::r/bh]}
   [2 0xe0] {::i/args [::r/ah ::r/al]}
   [2 0xe1] {::i/args [::r/ah ::r/cl]}
   [2 0xe2] {::i/args [::r/ah ::r/dl]}
   [2 0xe3] {::i/args [::r/ah ::r/bl]}
   [2 0xe4] {::i/args [::r/ah ::r/ah]}
   [2 0xe5] {::i/args [::r/ah ::r/ch]}
   [2 0xe6] {::i/args [::r/ah ::r/dh]}
   [2 0xe7] {::i/args [::r/ah ::r/bh]}
   [2 0xe8] {::i/args [::r/ch ::r/al]}
   [2 0xe9] {::i/args [::r/ch ::r/cl]}
   [2 0xea] {::i/args [::r/ch ::r/dl]}
   [2 0xeb] {::i/args [::r/ch ::r/bl]}
   [2 0xec] {::i/args [::r/ch ::r/ah]}
   [2 0xed] {::i/args [::r/ch ::r/ch]}
   [2 0xee] {::i/args [::r/ch ::r/dh]}
   [2 0xef] {::i/args [::r/ch ::r/bh]}
   [2 0xf0] {::i/args [::r/dh ::r/al]}
   [2 0xf1] {::i/args [::r/dh ::r/cl]}
   [2 0xf2] {::i/args [::r/dh ::r/dl]}
   [2 0xf3] {::i/args [::r/dh ::r/bl]}
   [2 0xf4] {::i/args [::r/dh ::r/ah]}
   [2 0xf5] {::i/args [::r/dh ::r/ch]}
   [2 0xf6] {::i/args [::r/dh ::r/dh]}
   [2 0xf7] {::i/args [::r/dh ::r/bh]}
   [2 0xf8] {::i/args [::r/bh ::r/al]}
   [2 0xf9] {::i/args [::r/bh ::r/cl]}
   [2 0xfa] {::i/args [::r/bh ::r/dl]}
   [2 0xfb] {::i/args [::r/bh ::r/bl]}
   [2 0xfc] {::i/args [::r/bh ::r/ah]}
   [2 0xfd] {::i/args [::r/bh ::r/ch]}
   [2 0xfe] {::i/args [::r/bh ::r/dh]}
   [2 0xff] {::i/args [::r/bh ::r/bh]}
   [3 0x00] {::i/args [::r/ax [::r/bx ::r/si]]}
   [3 0x01] {::i/args [::r/ax [::r/bx ::r/di]]}
   [3 0x02] {::i/args [::r/ax [::r/bp ::r/si]]}
   [3 0x03] {::i/args [::r/ax [::r/bp ::r/di]]}
   [3 0x04] {::i/args [::r/ax [::r/si]]}
   [3 0x05] {::i/args [::r/ax [::r/di]]}
   [3 0x06 0xf0 0x0f] {::i/args [::r/ax [0x0ff0]]}
   [3 0x06 0x0f 0xf0] {::i/args [::r/ax [0xf00f]]}
   [3 0x07] {::i/args [::r/ax [::r/bx]]}
   [3 0x08] {::i/args [::r/cx [::r/bx ::r/si]]}
   [3 0x09] {::i/args [::r/cx [::r/bx ::r/di]]}
   [3 0x0a] {::i/args [::r/cx [::r/bp ::r/si]]}
   [3 0x0b] {::i/args [::r/cx [::r/bp ::r/di]]}
   [3 0x0c] {::i/args [::r/cx [::r/si]]}
   [3 0x0d] {::i/args [::r/cx [::r/di]]}
   [3 0x0e 0xf0 0x0f] {::i/args [::r/cx [0x0ff0]]}
   [3 0x0e 0x0f 0xf0] {::i/args [::r/cx [0xf00f]]}
   [3 0x0f] {::i/args [::r/cx [::r/bx]]}
   [3 0x10] {::i/args [::r/dx [::r/bx ::r/si]]}
   [3 0x11] {::i/args [::r/dx [::r/bx ::r/di]]}
   [3 0x12] {::i/args [::r/dx [::r/bp ::r/si]]}
   [3 0x13] {::i/args [::r/dx [::r/bp ::r/di]]}
   [3 0x14] {::i/args [::r/dx [::r/si]]}
   [3 0x15] {::i/args [::r/dx [::r/di]]}
   [3 0x16 0xf0 0x0f] {::i/args [::r/dx [0x0ff0]]}
   [3 0x16 0x0f 0xf0] {::i/args [::r/dx [0xf00f]]}
   [3 0x17] {::i/args [::r/dx [::r/bx]]}
   [3 0x18] {::i/args [::r/bx [::r/bx ::r/si]]}
   [3 0x19] {::i/args [::r/bx [::r/bx ::r/di]]}
   [3 0x1a] {::i/args [::r/bx [::r/bp ::r/si]]}
   [3 0x1b] {::i/args [::r/bx [::r/bp ::r/di]]}
   [3 0x1c] {::i/args [::r/bx [::r/si]]}
   [3 0x1d] {::i/args [::r/bx [::r/di]]}
   [3 0x1e 0xf0 0x0f] {::i/args [::r/bx [0x0ff0]]}
   [3 0x1e 0x0f 0xf0] {::i/args [::r/bx [0xf00f]]}
   [3 0x1f] {::i/args [::r/bx [::r/bx]]}
   [3 0x20] {::i/args [::r/sp [::r/bx ::r/si]]}
   [3 0x21] {::i/args [::r/sp [::r/bx ::r/di]]}
   [3 0x22] {::i/args [::r/sp [::r/bp ::r/si]]}
   [3 0x23] {::i/args [::r/sp [::r/bp ::r/di]]}
   [3 0x24] {::i/args [::r/sp [::r/si]]}
   [3 0x25] {::i/args [::r/sp [::r/di]]}
   [3 0x26 0xf0 0x0f] {::i/args [::r/sp [0x0ff0]]}
   [3 0x26 0x0f 0xf0] {::i/args [::r/sp [0xf00f]]}
   [3 0x27] {::i/args [::r/sp [::r/bx]]}
   [3 0x28] {::i/args [::r/bp [::r/bx ::r/si]]}
   [3 0x29] {::i/args [::r/bp [::r/bx ::r/di]]}
   [3 0x2a] {::i/args [::r/bp [::r/bp ::r/si]]}
   [3 0x2b] {::i/args [::r/bp [::r/bp ::r/di]]}
   [3 0x2c] {::i/args [::r/bp [::r/si]]}
   [3 0x2d] {::i/args [::r/bp [::r/di]]}
   [3 0x2e 0xf0 0x0f] {::i/args [::r/bp [0x0ff0]]}
   [3 0x2e 0x0f 0xf0] {::i/args [::r/bp [0xf00f]]}
   [3 0x2f] {::i/args [::r/bp [::r/bx]]}
   [3 0x30] {::i/args [::r/si [::r/bx ::r/si]]}
   [3 0x31] {::i/args [::r/si [::r/bx ::r/di]]}
   [3 0x32] {::i/args [::r/si [::r/bp ::r/si]]}
   [3 0x33] {::i/args [::r/si [::r/bp ::r/di]]}
   [3 0x34] {::i/args [::r/si [::r/si]]}
   [3 0x35] {::i/args [::r/si [::r/di]]}
   [3 0x36 0xf0 0x0f] {::i/args [::r/si [0x0ff0]]}
   [3 0x36 0x0f 0xf0] {::i/args [::r/si [0xf00f]]}
   [3 0x37] {::i/args [::r/si [::r/bx]]}
   [3 0x38] {::i/args [::r/di [::r/bx ::r/si]]}
   [3 0x39] {::i/args [::r/di [::r/bx ::r/di]]}
   [3 0x3a] {::i/args [::r/di [::r/bp ::r/si]]}
   [3 0x3b] {::i/args [::r/di [::r/bp ::r/di]]}
   [3 0x3c] {::i/args [::r/di [::r/si]]}
   [3 0x3d] {::i/args [::r/di [::r/di]]}
   [3 0x3e 0xf0 0x0f] {::i/args [::r/di [0x0ff0]]}
   [3 0x3e 0x0f 0xf0] {::i/args [::r/di [0xf00f]]}
   [3 0x3f] {::i/args [::r/di [::r/bx]]}
   [3 0x40 0x00] {::i/args [::r/ax [::r/bx ::r/si]]}
   [3 0x40 0xf0] {::i/args [::r/ax [::r/bx ::r/si -16]]}
   [3 0x40 0x0f] {::i/args [::r/ax [::r/bx ::r/si 15]]}
   [3 0x41 0x00] {::i/args [::r/ax [::r/bx ::r/di]]}
   [3 0x41 0xf0] {::i/args [::r/ax [::r/bx ::r/di -16]]}
   [3 0x41 0x0f] {::i/args [::r/ax [::r/bx ::r/di 15]]}
   [3 0x42 0x00] {::i/args [::r/ax [::r/bp ::r/si]]}
   [3 0x42 0xf0] {::i/args [::r/ax [::r/bp ::r/si -16]]}
   [3 0x42 0x0f] {::i/args [::r/ax [::r/bp ::r/si 15]]}
   [3 0x43 0x00] {::i/args [::r/ax [::r/bp ::r/di]]}
   [3 0x43 0xf0] {::i/args [::r/ax [::r/bp ::r/di -16]]}
   [3 0x43 0x0f] {::i/args [::r/ax [::r/bp ::r/di 15]]}
   [3 0x44 0x00] {::i/args [::r/ax [::r/si]]}
   [3 0x44 0xf0] {::i/args [::r/ax [::r/si -16]]}
   [3 0x44 0x0f] {::i/args [::r/ax [::r/si 15]]}
   [3 0x45 0x00] {::i/args [::r/ax [::r/di]]}
   [3 0x45 0xf0] {::i/args [::r/ax [::r/di -16]]}
   [3 0x45 0x0f] {::i/args [::r/ax [::r/di 15]]}
   [3 0x46 0x00] {::i/args [::r/ax [::r/bp]]}
   [3 0x46 0xf0] {::i/args [::r/ax [::r/bp -16]]}
   [3 0x46 0x0f] {::i/args [::r/ax [::r/bp 15]]}
   [3 0x47 0x00] {::i/args [::r/ax [::r/bx]]}
   [3 0x47 0xf0] {::i/args [::r/ax [::r/bx -16]]}
   [3 0x47 0x0f] {::i/args [::r/ax [::r/bx 15]]}
   [3 0x48 0x00] {::i/args [::r/cx [::r/bx ::r/si]]}
   [3 0x48 0xf0] {::i/args [::r/cx [::r/bx ::r/si -16]]}
   [3 0x48 0x0f] {::i/args [::r/cx [::r/bx ::r/si 15]]}
   [3 0x49 0x00] {::i/args [::r/cx [::r/bx ::r/di]]}
   [3 0x49 0xf0] {::i/args [::r/cx [::r/bx ::r/di -16]]}
   [3 0x49 0x0f] {::i/args [::r/cx [::r/bx ::r/di 15]]}
   [3 0x4a 0x00] {::i/args [::r/cx [::r/bp ::r/si]]}
   [3 0x4a 0xf0] {::i/args [::r/cx [::r/bp ::r/si -16]]}
   [3 0x4a 0x0f] {::i/args [::r/cx [::r/bp ::r/si 15]]}
   [3 0x4b 0x00] {::i/args [::r/cx [::r/bp ::r/di]]}
   [3 0x4b 0xf0] {::i/args [::r/cx [::r/bp ::r/di -16]]}
   [3 0x4b 0x0f] {::i/args [::r/cx [::r/bp ::r/di 15]]}
   [3 0x4c 0x00] {::i/args [::r/cx [::r/si]]}
   [3 0x4c 0xf0] {::i/args [::r/cx [::r/si -16]]}
   [3 0x4c 0x0f] {::i/args [::r/cx [::r/si 15]]}
   [3 0x4d 0x00] {::i/args [::r/cx [::r/di]]}
   [3 0x4d 0xf0] {::i/args [::r/cx [::r/di -16]]}
   [3 0x4d 0x0f] {::i/args [::r/cx [::r/di 15]]}
   [3 0x4e 0x00] {::i/args [::r/cx [::r/bp]]}
   [3 0x4e 0xf0] {::i/args [::r/cx [::r/bp -16]]}
   [3 0x4e 0x0f] {::i/args [::r/cx [::r/bp 15]]}
   [3 0x4f 0x00] {::i/args [::r/cx [::r/bx]]}
   [3 0x4f 0xf0] {::i/args [::r/cx [::r/bx -16]]}
   [3 0x4f 0x0f] {::i/args [::r/cx [::r/bx 15]]}
   [3 0x50 0x00] {::i/args [::r/dx [::r/bx ::r/si]]}
   [3 0x50 0xf0] {::i/args [::r/dx [::r/bx ::r/si -16]]}
   [3 0x50 0x0f] {::i/args [::r/dx [::r/bx ::r/si 15]]}
   [3 0x51 0x00] {::i/args [::r/dx [::r/bx ::r/di]]}
   [3 0x51 0xf0] {::i/args [::r/dx [::r/bx ::r/di -16]]}
   [3 0x51 0x0f] {::i/args [::r/dx [::r/bx ::r/di 15]]}
   [3 0x52 0x00] {::i/args [::r/dx [::r/bp ::r/si]]}
   [3 0x52 0xf0] {::i/args [::r/dx [::r/bp ::r/si -16]]}
   [3 0x52 0x0f] {::i/args [::r/dx [::r/bp ::r/si 15]]}
   [3 0x53 0x00] {::i/args [::r/dx [::r/bp ::r/di]]}
   [3 0x53 0xf0] {::i/args [::r/dx [::r/bp ::r/di -16]]}
   [3 0x53 0x0f] {::i/args [::r/dx [::r/bp ::r/di 15]]}
   [3 0x54 0x00] {::i/args [::r/dx [::r/si]]}
   [3 0x54 0xf0] {::i/args [::r/dx [::r/si -16]]}
   [3 0x54 0x0f] {::i/args [::r/dx [::r/si 15]]}
   [3 0x55 0x00] {::i/args [::r/dx [::r/di]]}
   [3 0x55 0xf0] {::i/args [::r/dx [::r/di -16]]}
   [3 0x55 0x0f] {::i/args [::r/dx [::r/di 15]]}
   [3 0x56 0x00] {::i/args [::r/dx [::r/bp]]}
   [3 0x56 0xf0] {::i/args [::r/dx [::r/bp -16]]}
   [3 0x56 0x0f] {::i/args [::r/dx [::r/bp 15]]}
   [3 0x57 0x00] {::i/args [::r/dx [::r/bx]]}
   [3 0x57 0xf0] {::i/args [::r/dx [::r/bx -16]]}
   [3 0x57 0x0f] {::i/args [::r/dx [::r/bx 15]]}
   [3 0x58 0x00] {::i/args [::r/bx [::r/bx ::r/si]]}
   [3 0x58 0xf0] {::i/args [::r/bx [::r/bx ::r/si -16]]}
   [3 0x58 0x0f] {::i/args [::r/bx [::r/bx ::r/si 15]]}
   [3 0x59 0x00] {::i/args [::r/bx [::r/bx ::r/di]]}
   [3 0x59 0xf0] {::i/args [::r/bx [::r/bx ::r/di -16]]}
   [3 0x59 0x0f] {::i/args [::r/bx [::r/bx ::r/di 15]]}
   [3 0x5a 0x00] {::i/args [::r/bx [::r/bp ::r/si]]}
   [3 0x5a 0xf0] {::i/args [::r/bx [::r/bp ::r/si -16]]}
   [3 0x5a 0x0f] {::i/args [::r/bx [::r/bp ::r/si 15]]}
   [3 0x5b 0x00] {::i/args [::r/bx [::r/bp ::r/di]]}
   [3 0x5b 0xf0] {::i/args [::r/bx [::r/bp ::r/di -16]]}
   [3 0x5b 0x0f] {::i/args [::r/bx [::r/bp ::r/di 15]]}
   [3 0x5c 0x00] {::i/args [::r/bx [::r/si]]}
   [3 0x5c 0xf0] {::i/args [::r/bx [::r/si -16]]}
   [3 0x5c 0x0f] {::i/args [::r/bx [::r/si 15]]}
   [3 0x5d 0x00] {::i/args [::r/bx [::r/di]]}
   [3 0x5d 0xf0] {::i/args [::r/bx [::r/di -16]]}
   [3 0x5d 0x0f] {::i/args [::r/bx [::r/di 15]]}
   [3 0x5e 0x00] {::i/args [::r/bx [::r/bp]]}
   [3 0x5e 0xf0] {::i/args [::r/bx [::r/bp -16]]}
   [3 0x5e 0x0f] {::i/args [::r/bx [::r/bp 15]]}
   [3 0x5f 0x00] {::i/args [::r/bx [::r/bx]]}
   [3 0x5f 0xf0] {::i/args [::r/bx [::r/bx -16]]}
   [3 0x5f 0x0f] {::i/args [::r/bx [::r/bx 15]]}
   [3 0x60 0x00] {::i/args [::r/sp [::r/bx ::r/si]]}
   [3 0x60 0xf0] {::i/args [::r/sp [::r/bx ::r/si -16]]}
   [3 0x60 0x0f] {::i/args [::r/sp [::r/bx ::r/si 15]]}
   [3 0x61 0x00] {::i/args [::r/sp [::r/bx ::r/di]]}
   [3 0x61 0xf0] {::i/args [::r/sp [::r/bx ::r/di -16]]}
   [3 0x61 0x0f] {::i/args [::r/sp [::r/bx ::r/di 15]]}
   [3 0x62 0x00] {::i/args [::r/sp [::r/bp ::r/si]]}
   [3 0x62 0xf0] {::i/args [::r/sp [::r/bp ::r/si -16]]}
   [3 0x62 0x0f] {::i/args [::r/sp [::r/bp ::r/si 15]]}
   [3 0x63 0x00] {::i/args [::r/sp [::r/bp ::r/di]]}
   [3 0x63 0xf0] {::i/args [::r/sp [::r/bp ::r/di -16]]}
   [3 0x63 0x0f] {::i/args [::r/sp [::r/bp ::r/di 15]]}
   [3 0x64 0x00] {::i/args [::r/sp [::r/si]]}
   [3 0x64 0xf0] {::i/args [::r/sp [::r/si -16]]}
   [3 0x64 0x0f] {::i/args [::r/sp [::r/si 15]]}
   [3 0x65 0x00] {::i/args [::r/sp [::r/di]]}
   [3 0x65 0xf0] {::i/args [::r/sp [::r/di -16]]}
   [3 0x65 0x0f] {::i/args [::r/sp [::r/di 15]]}
   [3 0x66 0x00] {::i/args [::r/sp [::r/bp]]}
   [3 0x66 0xf0] {::i/args [::r/sp [::r/bp -16]]}
   [3 0x66 0x0f] {::i/args [::r/sp [::r/bp 15]]}
   [3 0x67 0x00] {::i/args [::r/sp [::r/bx]]}
   [3 0x67 0xf0] {::i/args [::r/sp [::r/bx -16]]}
   [3 0x67 0x0f] {::i/args [::r/sp [::r/bx 15]]}
   [3 0x68 0x00] {::i/args [::r/bp [::r/bx ::r/si]]}
   [3 0x68 0xf0] {::i/args [::r/bp [::r/bx ::r/si -16]]}
   [3 0x68 0x0f] {::i/args [::r/bp [::r/bx ::r/si 15]]}
   [3 0x69 0x00] {::i/args [::r/bp [::r/bx ::r/di]]}
   [3 0x69 0xf0] {::i/args [::r/bp [::r/bx ::r/di -16]]}
   [3 0x69 0x0f] {::i/args [::r/bp [::r/bx ::r/di 15]]}
   [3 0x6a 0x00] {::i/args [::r/bp [::r/bp ::r/si]]}
   [3 0x6a 0xf0] {::i/args [::r/bp [::r/bp ::r/si -16]]}
   [3 0x6a 0x0f] {::i/args [::r/bp [::r/bp ::r/si 15]]}
   [3 0x6b 0x00] {::i/args [::r/bp [::r/bp ::r/di]]}
   [3 0x6b 0xf0] {::i/args [::r/bp [::r/bp ::r/di -16]]}
   [3 0x6b 0x0f] {::i/args [::r/bp [::r/bp ::r/di 15]]}
   [3 0x6c 0x00] {::i/args [::r/bp [::r/si]]}
   [3 0x6c 0xf0] {::i/args [::r/bp [::r/si -16]]}
   [3 0x6c 0x0f] {::i/args [::r/bp [::r/si 15]]}
   [3 0x6d 0x00] {::i/args [::r/bp [::r/di]]}
   [3 0x6d 0xf0] {::i/args [::r/bp [::r/di -16]]}
   [3 0x6d 0x0f] {::i/args [::r/bp [::r/di 15]]}
   [3 0x6e 0x00] {::i/args [::r/bp [::r/bp]]}
   [3 0x6e 0xf0] {::i/args [::r/bp [::r/bp -16]]}
   [3 0x6e 0x0f] {::i/args [::r/bp [::r/bp 15]]}
   [3 0x6f 0x00] {::i/args [::r/bp [::r/bx]]}
   [3 0x6f 0xf0] {::i/args [::r/bp [::r/bx -16]]}
   [3 0x6f 0x0f] {::i/args [::r/bp [::r/bx 15]]}
   [3 0x70 0x00] {::i/args [::r/si [::r/bx ::r/si]]}
   [3 0x70 0xf0] {::i/args [::r/si [::r/bx ::r/si -16]]}
   [3 0x70 0x0f] {::i/args [::r/si [::r/bx ::r/si 15]]}
   [3 0x71 0x00] {::i/args [::r/si [::r/bx ::r/di]]}
   [3 0x71 0xf0] {::i/args [::r/si [::r/bx ::r/di -16]]}
   [3 0x71 0x0f] {::i/args [::r/si [::r/bx ::r/di 15]]}
   [3 0x72 0x00] {::i/args [::r/si [::r/bp ::r/si]]}
   [3 0x72 0xf0] {::i/args [::r/si [::r/bp ::r/si -16]]}
   [3 0x72 0x0f] {::i/args [::r/si [::r/bp ::r/si 15]]}
   [3 0x73 0x00] {::i/args [::r/si [::r/bp ::r/di]]}
   [3 0x73 0xf0] {::i/args [::r/si [::r/bp ::r/di -16]]}
   [3 0x73 0x0f] {::i/args [::r/si [::r/bp ::r/di 15]]}
   [3 0x74 0x00] {::i/args [::r/si [::r/si]]}
   [3 0x74 0xf0] {::i/args [::r/si [::r/si -16]]}
   [3 0x74 0x0f] {::i/args [::r/si [::r/si 15]]}
   [3 0x75 0x00] {::i/args [::r/si [::r/di]]}
   [3 0x75 0xf0] {::i/args [::r/si [::r/di -16]]}
   [3 0x75 0x0f] {::i/args [::r/si [::r/di 15]]}
   [3 0x76 0x00] {::i/args [::r/si [::r/bp]]}
   [3 0x76 0xf0] {::i/args [::r/si [::r/bp -16]]}
   [3 0x76 0x0f] {::i/args [::r/si [::r/bp 15]]}
   [3 0x77 0x00] {::i/args [::r/si [::r/bx]]}
   [3 0x77 0xf0] {::i/args [::r/si [::r/bx -16]]}
   [3 0x77 0x0f] {::i/args [::r/si [::r/bx 15]]}
   [3 0x78 0x00] {::i/args [::r/di [::r/bx ::r/si]]}
   [3 0x78 0xf0] {::i/args [::r/di [::r/bx ::r/si -16]]}
   [3 0x78 0x0f] {::i/args [::r/di [::r/bx ::r/si 15]]}
   [3 0x79 0x00] {::i/args [::r/di [::r/bx ::r/di]]}
   [3 0x79 0xf0] {::i/args [::r/di [::r/bx ::r/di -16]]}
   [3 0x79 0x0f] {::i/args [::r/di [::r/bx ::r/di 15]]}
   [3 0x7a 0x00] {::i/args [::r/di [::r/bp ::r/si]]}
   [3 0x7a 0xf0] {::i/args [::r/di [::r/bp ::r/si -16]]}
   [3 0x7a 0x0f] {::i/args [::r/di [::r/bp ::r/si 15]]}
   [3 0x7b 0x00] {::i/args [::r/di [::r/bp ::r/di]]}
   [3 0x7b 0xf0] {::i/args [::r/di [::r/bp ::r/di -16]]}
   [3 0x7b 0x0f] {::i/args [::r/di [::r/bp ::r/di 15]]}
   [3 0x7c 0x00] {::i/args [::r/di [::r/si]]}
   [3 0x7c 0xf0] {::i/args [::r/di [::r/si -16]]}
   [3 0x7c 0x0f] {::i/args [::r/di [::r/si 15]]}
   [3 0x7d 0x00] {::i/args [::r/di [::r/di]]}
   [3 0x7d 0xf0] {::i/args [::r/di [::r/di -16]]}
   [3 0x7d 0x0f] {::i/args [::r/di [::r/di 15]]}
   [3 0x7e 0x00] {::i/args [::r/di [::r/bp]]}
   [3 0x7e 0xf0] {::i/args [::r/di [::r/bp -16]]}
   [3 0x7e 0x0f] {::i/args [::r/di [::r/bp 15]]}
   [3 0x7f 0x00] {::i/args [::r/di [::r/bx]]}
   [3 0x7f 0xf0] {::i/args [::r/di [::r/bx -16]]}
   [3 0x7f 0x0f] {::i/args [::r/di [::r/bx 15]]}
   [3 0x80 0x00 0x00] {::i/args [::r/ax [::r/bx ::r/si]]}
   [3 0x80 0xf0 0x0f] {::i/args [::r/ax [::r/bx ::r/si 4080]]}
   [3 0x80 0x0f 0xf0] {::i/args [::r/ax [::r/bx ::r/si -4081]]}
   [3 0x81 0x00 0x00] {::i/args [::r/ax [::r/bx ::r/di]]}
   [3 0x81 0xf0 0x0f] {::i/args [::r/ax [::r/bx ::r/di 4080]]}
   [3 0x81 0x0f 0xf0] {::i/args [::r/ax [::r/bx ::r/di -4081]]}
   [3 0x82 0x00 0x00] {::i/args [::r/ax [::r/bp ::r/si]]}
   [3 0x82 0xf0 0x0f] {::i/args [::r/ax [::r/bp ::r/si 4080]]}
   [3 0x82 0x0f 0xf0] {::i/args [::r/ax [::r/bp ::r/si -4081]]}
   [3 0x83 0x00 0x00] {::i/args [::r/ax [::r/bp ::r/di]]}
   [3 0x83 0xf0 0x0f] {::i/args [::r/ax [::r/bp ::r/di 4080]]}
   [3 0x83 0x0f 0xf0] {::i/args [::r/ax [::r/bp ::r/di -4081]]}
   [3 0x84 0x00 0x00] {::i/args [::r/ax [::r/si]]}
   [3 0x84 0xf0 0x0f] {::i/args [::r/ax [::r/si 4080]]}
   [3 0x84 0x0f 0xf0] {::i/args [::r/ax [::r/si -4081]]}
   [3 0x85 0x00 0x00] {::i/args [::r/ax [::r/di]]}
   [3 0x85 0xf0 0x0f] {::i/args [::r/ax [::r/di 4080]]}
   [3 0x85 0x0f 0xf0] {::i/args [::r/ax [::r/di -4081]]}
   [3 0x86 0x00 0x00] {::i/args [::r/ax [::r/bp]]}
   [3 0x86 0xf0 0x0f] {::i/args [::r/ax [::r/bp 4080]]}
   [3 0x86 0x0f 0xf0] {::i/args [::r/ax [::r/bp -4081]]}
   [3 0x87 0x00 0x00] {::i/args [::r/ax [::r/bx]]}
   [3 0x87 0xf0 0x0f] {::i/args [::r/ax [::r/bx 4080]]}
   [3 0x87 0x0f 0xf0] {::i/args [::r/ax [::r/bx -4081]]}
   [3 0x88 0x00 0x00] {::i/args [::r/cx [::r/bx ::r/si]]}
   [3 0x88 0xf0 0x0f] {::i/args [::r/cx [::r/bx ::r/si 4080]]}
   [3 0x88 0x0f 0xf0] {::i/args [::r/cx [::r/bx ::r/si -4081]]}
   [3 0x89 0x00 0x00] {::i/args [::r/cx [::r/bx ::r/di]]}
   [3 0x89 0xf0 0x0f] {::i/args [::r/cx [::r/bx ::r/di 4080]]}
   [3 0x89 0x0f 0xf0] {::i/args [::r/cx [::r/bx ::r/di -4081]]}
   [3 0x8a 0x00 0x00] {::i/args [::r/cx [::r/bp ::r/si]]}
   [3 0x8a 0xf0 0x0f] {::i/args [::r/cx [::r/bp ::r/si 4080]]}
   [3 0x8a 0x0f 0xf0] {::i/args [::r/cx [::r/bp ::r/si -4081]]}
   [3 0x8b 0x00 0x00] {::i/args [::r/cx [::r/bp ::r/di]]}
   [3 0x8b 0xf0 0x0f] {::i/args [::r/cx [::r/bp ::r/di 4080]]}
   [3 0x8b 0x0f 0xf0] {::i/args [::r/cx [::r/bp ::r/di -4081]]}
   [3 0x8c 0x00 0x00] {::i/args [::r/cx [::r/si]]}
   [3 0x8c 0xf0 0x0f] {::i/args [::r/cx [::r/si 4080]]}
   [3 0x8c 0x0f 0xf0] {::i/args [::r/cx [::r/si -4081]]}
   [3 0x8d 0x00 0x00] {::i/args [::r/cx [::r/di]]}
   [3 0x8d 0xf0 0x0f] {::i/args [::r/cx [::r/di 4080]]}
   [3 0x8d 0x0f 0xf0] {::i/args [::r/cx [::r/di -4081]]}
   [3 0x8e 0x00 0x00] {::i/args [::r/cx [::r/bp]]}
   [3 0x8e 0xf0 0x0f] {::i/args [::r/cx [::r/bp 4080]]}
   [3 0x8e 0x0f 0xf0] {::i/args [::r/cx [::r/bp -4081]]}
   [3 0x8f 0x00 0x00] {::i/args [::r/cx [::r/bx]]}
   [3 0x8f 0xf0 0x0f] {::i/args [::r/cx [::r/bx 4080]]}
   [3 0x8f 0x0f 0xf0] {::i/args [::r/cx [::r/bx -4081]]}
   [3 0x90 0x00 0x00] {::i/args [::r/dx [::r/bx ::r/si]]}
   [3 0x90 0xf0 0x0f] {::i/args [::r/dx [::r/bx ::r/si 4080]]}
   [3 0x90 0x0f 0xf0] {::i/args [::r/dx [::r/bx ::r/si -4081]]}
   [3 0x91 0x00 0x00] {::i/args [::r/dx [::r/bx ::r/di]]}
   [3 0x91 0xf0 0x0f] {::i/args [::r/dx [::r/bx ::r/di 4080]]}
   [3 0x91 0x0f 0xf0] {::i/args [::r/dx [::r/bx ::r/di -4081]]}
   [3 0x92 0x00 0x00] {::i/args [::r/dx [::r/bp ::r/si]]}
   [3 0x92 0xf0 0x0f] {::i/args [::r/dx [::r/bp ::r/si 4080]]}
   [3 0x92 0x0f 0xf0] {::i/args [::r/dx [::r/bp ::r/si -4081]]}
   [3 0x93 0x00 0x00] {::i/args [::r/dx [::r/bp ::r/di]]}
   [3 0x93 0xf0 0x0f] {::i/args [::r/dx [::r/bp ::r/di 4080]]}
   [3 0x93 0x0f 0xf0] {::i/args [::r/dx [::r/bp ::r/di -4081]]}
   [3 0x94 0x00 0x00] {::i/args [::r/dx [::r/si]]}
   [3 0x94 0xf0 0x0f] {::i/args [::r/dx [::r/si 4080]]}
   [3 0x94 0x0f 0xf0] {::i/args [::r/dx [::r/si -4081]]}
   [3 0x95 0x00 0x00] {::i/args [::r/dx [::r/di]]}
   [3 0x95 0xf0 0x0f] {::i/args [::r/dx [::r/di 4080]]}
   [3 0x95 0x0f 0xf0] {::i/args [::r/dx [::r/di -4081]]}
   [3 0x96 0x00 0x00] {::i/args [::r/dx [::r/bp]]}
   [3 0x96 0xf0 0x0f] {::i/args [::r/dx [::r/bp 4080]]}
   [3 0x96 0x0f 0xf0] {::i/args [::r/dx [::r/bp -4081]]}
   [3 0x97 0x00 0x00] {::i/args [::r/dx [::r/bx]]}
   [3 0x97 0xf0 0x0f] {::i/args [::r/dx [::r/bx 4080]]}
   [3 0x97 0x0f 0xf0] {::i/args [::r/dx [::r/bx -4081]]}
   [3 0x98 0x00 0x00] {::i/args [::r/bx [::r/bx ::r/si]]}
   [3 0x98 0xf0 0x0f] {::i/args [::r/bx [::r/bx ::r/si 4080]]}
   [3 0x98 0x0f 0xf0] {::i/args [::r/bx [::r/bx ::r/si -4081]]}
   [3 0x99 0x00 0x00] {::i/args [::r/bx [::r/bx ::r/di]]}
   [3 0x99 0xf0 0x0f] {::i/args [::r/bx [::r/bx ::r/di 4080]]}
   [3 0x99 0x0f 0xf0] {::i/args [::r/bx [::r/bx ::r/di -4081]]}
   [3 0x9a 0x00 0x00] {::i/args [::r/bx [::r/bp ::r/si]]}
   [3 0x9a 0xf0 0x0f] {::i/args [::r/bx [::r/bp ::r/si 4080]]}
   [3 0x9a 0x0f 0xf0] {::i/args [::r/bx [::r/bp ::r/si -4081]]}
   [3 0x9b 0x00 0x00] {::i/args [::r/bx [::r/bp ::r/di]]}
   [3 0x9b 0xf0 0x0f] {::i/args [::r/bx [::r/bp ::r/di 4080]]}
   [3 0x9b 0x0f 0xf0] {::i/args [::r/bx [::r/bp ::r/di -4081]]}
   [3 0x9c 0x00 0x00] {::i/args [::r/bx [::r/si]]}
   [3 0x9c 0xf0 0x0f] {::i/args [::r/bx [::r/si 4080]]}
   [3 0x9c 0x0f 0xf0] {::i/args [::r/bx [::r/si -4081]]}
   [3 0x9d 0x00 0x00] {::i/args [::r/bx [::r/di]]}
   [3 0x9d 0xf0 0x0f] {::i/args [::r/bx [::r/di 4080]]}
   [3 0x9d 0x0f 0xf0] {::i/args [::r/bx [::r/di -4081]]}
   [3 0x9e 0x00 0x00] {::i/args [::r/bx [::r/bp]]}
   [3 0x9e 0xf0 0x0f] {::i/args [::r/bx [::r/bp 4080]]}
   [3 0x9e 0x0f 0xf0] {::i/args [::r/bx [::r/bp -4081]]}
   [3 0x9f 0x00 0x00] {::i/args [::r/bx [::r/bx]]}
   [3 0x9f 0xf0 0x0f] {::i/args [::r/bx [::r/bx 4080]]}
   [3 0x9f 0x0f 0xf0] {::i/args [::r/bx [::r/bx -4081]]}
   [3 0xa0 0x00 0x00] {::i/args [::r/sp [::r/bx ::r/si]]}
   [3 0xa0 0xf0 0x0f] {::i/args [::r/sp [::r/bx ::r/si 4080]]}
   [3 0xa0 0x0f 0xf0] {::i/args [::r/sp [::r/bx ::r/si -4081]]}
   [3 0xa1 0x00 0x00] {::i/args [::r/sp [::r/bx ::r/di]]}
   [3 0xa1 0xf0 0x0f] {::i/args [::r/sp [::r/bx ::r/di 4080]]}
   [3 0xa1 0x0f 0xf0] {::i/args [::r/sp [::r/bx ::r/di -4081]]}
   [3 0xa2 0x00 0x00] {::i/args [::r/sp [::r/bp ::r/si]]}
   [3 0xa2 0xf0 0x0f] {::i/args [::r/sp [::r/bp ::r/si 4080]]}
   [3 0xa2 0x0f 0xf0] {::i/args [::r/sp [::r/bp ::r/si -4081]]}
   [3 0xa3 0x00 0x00] {::i/args [::r/sp [::r/bp ::r/di]]}
   [3 0xa3 0xf0 0x0f] {::i/args [::r/sp [::r/bp ::r/di 4080]]}
   [3 0xa3 0x0f 0xf0] {::i/args [::r/sp [::r/bp ::r/di -4081]]}
   [3 0xa4 0x00 0x00] {::i/args [::r/sp [::r/si]]}
   [3 0xa4 0xf0 0x0f] {::i/args [::r/sp [::r/si 4080]]}
   [3 0xa4 0x0f 0xf0] {::i/args [::r/sp [::r/si -4081]]}
   [3 0xa5 0x00 0x00] {::i/args [::r/sp [::r/di]]}
   [3 0xa5 0xf0 0x0f] {::i/args [::r/sp [::r/di 4080]]}
   [3 0xa5 0x0f 0xf0] {::i/args [::r/sp [::r/di -4081]]}
   [3 0xa6 0x00 0x00] {::i/args [::r/sp [::r/bp]]}
   [3 0xa6 0xf0 0x0f] {::i/args [::r/sp [::r/bp 4080]]}
   [3 0xa6 0x0f 0xf0] {::i/args [::r/sp [::r/bp -4081]]}
   [3 0xa7 0x00 0x00] {::i/args [::r/sp [::r/bx]]}
   [3 0xa7 0xf0 0x0f] {::i/args [::r/sp [::r/bx 4080]]}
   [3 0xa7 0x0f 0xf0] {::i/args [::r/sp [::r/bx -4081]]}
   [3 0xa8 0x00 0x00] {::i/args [::r/bp [::r/bx ::r/si]]}
   [3 0xa8 0xf0 0x0f] {::i/args [::r/bp [::r/bx ::r/si 4080]]}
   [3 0xa8 0x0f 0xf0] {::i/args [::r/bp [::r/bx ::r/si -4081]]}
   [3 0xa9 0x00 0x00] {::i/args [::r/bp [::r/bx ::r/di]]}
   [3 0xa9 0xf0 0x0f] {::i/args [::r/bp [::r/bx ::r/di 4080]]}
   [3 0xa9 0x0f 0xf0] {::i/args [::r/bp [::r/bx ::r/di -4081]]}
   [3 0xaa 0x00 0x00] {::i/args [::r/bp [::r/bp ::r/si]]}
   [3 0xaa 0xf0 0x0f] {::i/args [::r/bp [::r/bp ::r/si 4080]]}
   [3 0xaa 0x0f 0xf0] {::i/args [::r/bp [::r/bp ::r/si -4081]]}
   [3 0xab 0x00 0x00] {::i/args [::r/bp [::r/bp ::r/di]]}
   [3 0xab 0xf0 0x0f] {::i/args [::r/bp [::r/bp ::r/di 4080]]}
   [3 0xab 0x0f 0xf0] {::i/args [::r/bp [::r/bp ::r/di -4081]]}
   [3 0xac 0x00 0x00] {::i/args [::r/bp [::r/si]]}
   [3 0xac 0xf0 0x0f] {::i/args [::r/bp [::r/si 4080]]}
   [3 0xac 0x0f 0xf0] {::i/args [::r/bp [::r/si -4081]]}
   [3 0xad 0x00 0x00] {::i/args [::r/bp [::r/di]]}
   [3 0xad 0xf0 0x0f] {::i/args [::r/bp [::r/di 4080]]}
   [3 0xad 0x0f 0xf0] {::i/args [::r/bp [::r/di -4081]]}
   [3 0xae 0x00 0x00] {::i/args [::r/bp [::r/bp]]}
   [3 0xae 0xf0 0x0f] {::i/args [::r/bp [::r/bp 4080]]}
   [3 0xae 0x0f 0xf0] {::i/args [::r/bp [::r/bp -4081]]}
   [3 0xaf 0x00 0x00] {::i/args [::r/bp [::r/bx]]}
   [3 0xaf 0xf0 0x0f] {::i/args [::r/bp [::r/bx 4080]]}
   [3 0xaf 0x0f 0xf0] {::i/args [::r/bp [::r/bx -4081]]}
   [3 0xb0 0x00 0x00] {::i/args [::r/si [::r/bx ::r/si]]}
   [3 0xb0 0xf0 0x0f] {::i/args [::r/si [::r/bx ::r/si 4080]]}
   [3 0xb0 0x0f 0xf0] {::i/args [::r/si [::r/bx ::r/si -4081]]}
   [3 0xb1 0x00 0x00] {::i/args [::r/si [::r/bx ::r/di]]}
   [3 0xb1 0xf0 0x0f] {::i/args [::r/si [::r/bx ::r/di 4080]]}
   [3 0xb1 0x0f 0xf0] {::i/args [::r/si [::r/bx ::r/di -4081]]}
   [3 0xb2 0x00 0x00] {::i/args [::r/si [::r/bp ::r/si]]}
   [3 0xb2 0xf0 0x0f] {::i/args [::r/si [::r/bp ::r/si 4080]]}
   [3 0xb2 0x0f 0xf0] {::i/args [::r/si [::r/bp ::r/si -4081]]}
   [3 0xb3 0x00 0x00] {::i/args [::r/si [::r/bp ::r/di]]}
   [3 0xb3 0xf0 0x0f] {::i/args [::r/si [::r/bp ::r/di 4080]]}
   [3 0xb3 0x0f 0xf0] {::i/args [::r/si [::r/bp ::r/di -4081]]}
   [3 0xb4 0x00 0x00] {::i/args [::r/si [::r/si]]}
   [3 0xb4 0xf0 0x0f] {::i/args [::r/si [::r/si 4080]]}
   [3 0xb4 0x0f 0xf0] {::i/args [::r/si [::r/si -4081]]}
   [3 0xb5 0x00 0x00] {::i/args [::r/si [::r/di]]}
   [3 0xb5 0xf0 0x0f] {::i/args [::r/si [::r/di 4080]]}
   [3 0xb5 0x0f 0xf0] {::i/args [::r/si [::r/di -4081]]}
   [3 0xb6 0x00 0x00] {::i/args [::r/si [::r/bp]]}
   [3 0xb6 0xf0 0x0f] {::i/args [::r/si [::r/bp 4080]]}
   [3 0xb6 0x0f 0xf0] {::i/args [::r/si [::r/bp -4081]]}
   [3 0xb7 0x00 0x00] {::i/args [::r/si [::r/bx]]}
   [3 0xb7 0xf0 0x0f] {::i/args [::r/si [::r/bx 4080]]}
   [3 0xb7 0x0f 0xf0] {::i/args [::r/si [::r/bx -4081]]}
   [3 0xb8 0x00 0x00] {::i/args [::r/di [::r/bx ::r/si]]}
   [3 0xb8 0xf0 0x0f] {::i/args [::r/di [::r/bx ::r/si 4080]]}
   [3 0xb8 0x0f 0xf0] {::i/args [::r/di [::r/bx ::r/si -4081]]}
   [3 0xb9 0x00 0x00] {::i/args [::r/di [::r/bx ::r/di]]}
   [3 0xb9 0xf0 0x0f] {::i/args [::r/di [::r/bx ::r/di 4080]]}
   [3 0xb9 0x0f 0xf0] {::i/args [::r/di [::r/bx ::r/di -4081]]}
   [3 0xba 0x00 0x00] {::i/args [::r/di [::r/bp ::r/si]]}
   [3 0xba 0xf0 0x0f] {::i/args [::r/di [::r/bp ::r/si 4080]]}
   [3 0xba 0x0f 0xf0] {::i/args [::r/di [::r/bp ::r/si -4081]]}
   [3 0xbb 0x00 0x00] {::i/args [::r/di [::r/bp ::r/di]]}
   [3 0xbb 0xf0 0x0f] {::i/args [::r/di [::r/bp ::r/di 4080]]}
   [3 0xbb 0x0f 0xf0] {::i/args [::r/di [::r/bp ::r/di -4081]]}
   [3 0xbc 0x00 0x00] {::i/args [::r/di [::r/si]]}
   [3 0xbc 0xf0 0x0f] {::i/args [::r/di [::r/si 4080]]}
   [3 0xbc 0x0f 0xf0] {::i/args [::r/di [::r/si -4081]]}
   [3 0xbd 0x00 0x00] {::i/args [::r/di [::r/di]]}
   [3 0xbd 0xf0 0x0f] {::i/args [::r/di [::r/di 4080]]}
   [3 0xbd 0x0f 0xf0] {::i/args [::r/di [::r/di -4081]]}
   [3 0xbe 0x00 0x00] {::i/args [::r/di [::r/bp]]}
   [3 0xbe 0xf0 0x0f] {::i/args [::r/di [::r/bp 4080]]}
   [3 0xbe 0x0f 0xf0] {::i/args [::r/di [::r/bp -4081]]}
   [3 0xbf 0x00 0x00] {::i/args [::r/di [::r/bx]]}
   [3 0xbf 0xf0 0x0f] {::i/args [::r/di [::r/bx 4080]]}
   [3 0xbf 0x0f 0xf0] {::i/args [::r/di [::r/bx -4081]]}
   [3 0xc0] {::i/args [::r/ax ::r/ax]}
   [3 0xc1] {::i/args [::r/ax ::r/cx]}
   [3 0xc2] {::i/args [::r/ax ::r/dx]}
   [3 0xc3] {::i/args [::r/ax ::r/bx]}
   [3 0xc4] {::i/args [::r/ax ::r/sp]}
   [3 0xc5] {::i/args [::r/ax ::r/bp]}
   [3 0xc6] {::i/args [::r/ax ::r/si]}
   [3 0xc7] {::i/args [::r/ax ::r/di]}
   [3 0xc8] {::i/args [::r/cx ::r/ax]}
   [3 0xc9] {::i/args [::r/cx ::r/cx]}
   [3 0xca] {::i/args [::r/cx ::r/dx]}
   [3 0xcb] {::i/args [::r/cx ::r/bx]}
   [3 0xcc] {::i/args [::r/cx ::r/sp]}
   [3 0xcd] {::i/args [::r/cx ::r/bp]}
   [3 0xce] {::i/args [::r/cx ::r/si]}
   [3 0xcf] {::i/args [::r/cx ::r/di]}
   [3 0xd0] {::i/args [::r/dx ::r/ax]}
   [3 0xd1] {::i/args [::r/dx ::r/cx]}
   [3 0xd2] {::i/args [::r/dx ::r/dx]}
   [3 0xd3] {::i/args [::r/dx ::r/bx]}
   [3 0xd4] {::i/args [::r/dx ::r/sp]}
   [3 0xd5] {::i/args [::r/dx ::r/bp]}
   [3 0xd6] {::i/args [::r/dx ::r/si]}
   [3 0xd7] {::i/args [::r/dx ::r/di]}
   [3 0xd8] {::i/args [::r/bx ::r/ax]}
   [3 0xd9] {::i/args [::r/bx ::r/cx]}
   [3 0xda] {::i/args [::r/bx ::r/dx]}
   [3 0xdb] {::i/args [::r/bx ::r/bx]}
   [3 0xdc] {::i/args [::r/bx ::r/sp]}
   [3 0xdd] {::i/args [::r/bx ::r/bp]}
   [3 0xde] {::i/args [::r/bx ::r/si]}
   [3 0xdf] {::i/args [::r/bx ::r/di]}
   [3 0xe0] {::i/args [::r/sp ::r/ax]}
   [3 0xe1] {::i/args [::r/sp ::r/cx]}
   [3 0xe2] {::i/args [::r/sp ::r/dx]}
   [3 0xe3] {::i/args [::r/sp ::r/bx]}
   [3 0xe4] {::i/args [::r/sp ::r/sp]}
   [3 0xe5] {::i/args [::r/sp ::r/bp]}
   [3 0xe6] {::i/args [::r/sp ::r/si]}
   [3 0xe7] {::i/args [::r/sp ::r/di]}
   [3 0xe8] {::i/args [::r/bp ::r/ax]}
   [3 0xe9] {::i/args [::r/bp ::r/cx]}
   [3 0xea] {::i/args [::r/bp ::r/dx]}
   [3 0xeb] {::i/args [::r/bp ::r/bx]}
   [3 0xec] {::i/args [::r/bp ::r/sp]}
   [3 0xed] {::i/args [::r/bp ::r/bp]}
   [3 0xee] {::i/args [::r/bp ::r/si]}
   [3 0xef] {::i/args [::r/bp ::r/di]}
   [3 0xf0] {::i/args [::r/si ::r/ax]}
   [3 0xf1] {::i/args [::r/si ::r/cx]}
   [3 0xf2] {::i/args [::r/si ::r/dx]}
   [3 0xf3] {::i/args [::r/si ::r/bx]}
   [3 0xf4] {::i/args [::r/si ::r/sp]}
   [3 0xf5] {::i/args [::r/si ::r/bp]}
   [3 0xf6] {::i/args [::r/si ::r/si]}
   [3 0xf7] {::i/args [::r/si ::r/di]}
   [3 0xf8] {::i/args [::r/di ::r/ax]}
   [3 0xf9] {::i/args [::r/di ::r/cx]}
   [3 0xfa] {::i/args [::r/di ::r/dx]}
   [3 0xfb] {::i/args [::r/di ::r/bx]}
   [3 0xfc] {::i/args [::r/di ::r/sp]}
   [3 0xfd] {::i/args [::r/di ::r/bp]}
   [3 0xfe] {::i/args [::r/di ::r/si]}
   [3 0xff] {::i/args [::r/di ::r/di]}
   [4 0xf0] {::i/args [::r/al 0xf0]}
   [4 0x0f] {::i/args [::r/al 0x0f]}
   [5 0xf0 0x0f] {::i/args [::r/ax 0x0ff0]}
   [5 0x0f 0xf0] {::i/args [::r/ax 0xf00f]}})


(defn gen-numeric-examples [first-opcode tag]
  (let [gen-example (fn [[opcodes instr]]
                      [(update opcodes 0 #(+ first-opcode %))
                       (assoc instr ::i/tag tag)])]
    (into {} (map gen-example numeric-examples))))


(def decode-examples
  {[0x06] {::i/tag ::i/push, ::i/args [::r/es]}
   [0x07] {::i/tag ::i/pop, ::i/args [::r/es]}
   [0x0e] {::i/tag ::i/push, ::i/args [::r/cs]}
   [0x16] {::i/tag ::i/push, ::i/args [::r/ss]}
   [0x17] {::i/tag ::i/pop, ::i/args [::r/ss]}
   [0x1e] {::i/tag ::i/push, ::i/args [::r/ds]}
   [0x1f] {::i/tag ::i/pop, ::i/args [::r/ds]}
   [0x37] {::i/tag ::i/aaa}
   [0x27] {::i/tag ::i/daa}
   [0x2f] {::i/tag ::i/das}
   [0x3f] {::i/tag ::i/aas}
   [0x40] {::i/tag ::i/inc, ::i/args [::r/ax]}
   [0x41] {::i/tag ::i/inc, ::i/args [::r/cx]}
   [0x42] {::i/tag ::i/inc, ::i/args [::r/dx]}
   [0x43] {::i/tag ::i/inc, ::i/args [::r/bx]}
   [0x44] {::i/tag ::i/inc, ::i/args [::r/sp]}
   [0x45] {::i/tag ::i/inc, ::i/args [::r/bp]}
   [0x46] {::i/tag ::i/inc, ::i/args [::r/si]}
   [0x47] {::i/tag ::i/inc, ::i/args [::r/di]}
   [0x48] {::i/tag ::i/dec, ::i/args [::r/ax]}
   [0x49] {::i/tag ::i/dec, ::i/args [::r/cx]}
   [0x4a] {::i/tag ::i/dec, ::i/args [::r/dx]}
   [0x4b] {::i/tag ::i/dec, ::i/args [::r/bx]}
   [0x4c] {::i/tag ::i/dec, ::i/args [::r/sp]}
   [0x4d] {::i/tag ::i/dec, ::i/args [::r/bp]}
   [0x4e] {::i/tag ::i/dec, ::i/args [::r/si]}
   [0x4f] {::i/tag ::i/dec, ::i/args [::r/di]}
   [0x50] {::i/tag ::i/push, ::i/args [::r/ax]}
   [0x51] {::i/tag ::i/push, ::i/args [::r/cx]}
   [0x52] {::i/tag ::i/push, ::i/args [::r/dx]}
   [0x53] {::i/tag ::i/push, ::i/args [::r/bx]}
   [0x54] {::i/tag ::i/push, ::i/args [::r/sp]}
   [0x55] {::i/tag ::i/push, ::i/args [::r/bp]}
   [0x56] {::i/tag ::i/push, ::i/args [::r/si]}
   [0x57] {::i/tag ::i/push, ::i/args [::r/di]}
   [0x58] {::i/tag ::i/pop, ::i/args [::r/ax]}
   [0x59] {::i/tag ::i/pop, ::i/args [::r/cx]}
   [0x5a] {::i/tag ::i/pop, ::i/args [::r/dx]}
   [0x5b] {::i/tag ::i/pop, ::i/args [::r/bx]}
   [0x5c] {::i/tag ::i/pop, ::i/args [::r/sp]}
   [0x5d] {::i/tag ::i/pop, ::i/args [::r/bp]}
   [0x5e] {::i/tag ::i/pop, ::i/args [::r/si]}
   [0x5f] {::i/tag ::i/pop, ::i/args [::r/di]}
   [0x60] {::i/tag ::i/pusha}
   [0x61] {::i/tag ::i/popa}
   [0x6c] {::i/tag ::i/insb}
   [0x6d] {::i/tag ::i/insw}
   [0x6e] {::i/tag ::i/outsb}
   [0x6f] {::i/tag ::i/outsw}
   [0x90] {::i/tag ::i/nop}
   [0x98] {::i/tag ::i/cbw}
   [0x99] {::i/tag ::i/cwd}
   [0x9b] {::i/tag ::i/fwait}
   [0x9c] {::i/tag ::i/pushf}
   [0x9d] {::i/tag ::i/popf}
   [0x9e] {::i/tag ::i/sahf}
   [0x9f] {::i/tag ::i/lahf}
   [0xa4] {::i/tag ::i/movsb}
   [0xa5] {::i/tag ::i/movsw}
   [0xa6] {::i/tag ::i/cmpsb}
   [0xa7] {::i/tag ::i/cmpsw}
   [0xaa] {::i/tag ::i/stosb}
   [0xab] {::i/tag ::i/stosw}
   [0xac] {::i/tag ::i/lodsb}
   [0xad] {::i/tag ::i/lodsw}
   [0xae] {::i/tag ::i/scasb}
   [0xaf] {::i/tag ::i/scasw}
   [0xc3] {::i/tag ::i/ret}
   [0xc9] {::i/tag ::i/leave}
   [0xcb] {::i/tag ::i/retf}
   [0xcc] {::i/tag ::i/int, ::i/args [3]}
   [0xce] {::i/tag ::i/into}
   [0xcf] {::i/tag ::i/iret}
   [0xd6] {::i/tag ::i/setalc}
   [0xd7] {::i/tag ::i/xlat}
   [0xec] {::i/tag ::i/in, ::i/args [::r/al ::r/dx]}
   [0xed] {::i/tag ::i/in, ::i/args [::r/ax ::r/dx]}
   [0xee] {::i/tag ::i/out, ::i/args [::r/dx ::r/al]}
   [0xef] {::i/tag ::i/out, ::i/args [::r/dx ::r/ax]}
   [0xf1] {::i/tag ::i/icebp}
   [0xf4] {::i/tag ::i/hlt}
   [0xf5] {::i/tag ::i/cmc}
   [0xf8] {::i/tag ::i/clc}
   [0xf9] {::i/tag ::i/stc}
   [0xfa] {::i/tag ::i/cli}
   [0xfb] {::i/tag ::i/sti}
   [0xfc] {::i/tag ::i/cld}
   [0xfd] {::i/tag ::i/std}})


(def all-decode-examples
  (safe-merge
   decode-examples
   (gen-numeric-examples 0x00 ::i/add)
   (gen-numeric-examples 0x08 ::i/or)
   (gen-numeric-examples 0x10 ::i/adc)
   (gen-numeric-examples 0x18 ::i/sbb)
   (gen-numeric-examples 0x20 ::i/and)))


(defn find-gaps []
  (let [used (set (map first (keys all-decode-examples)))]
    (mapv #(format "%02x" %) (filter #(not (used %)) (range 256)))))


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


(deftest decode-test
  (doseq [br (seq all-decode-examples)]
    (is (= (br 1)
           (decode (br 0)))
        (mapv #(format "%02x" %) (br 0)))))
