(ns oumu.cpu.instructions-test
  (:require [clojure.test :refer :all]
            [oumu.cpu.instructions :refer :all :as i]
            [oumu.cpu.registers :as r]))


(def decode-examples {[0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/al]}
                      [0x00 0x01] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/al]}
                      [0x00 0x02] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/al]}
                      [0x00 0x03] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/al]}
                      [0x00 0x04] {::i/tag ::i/add, ::i/args [[::r/si] ::r/al]}
                      [0x00 0x05] {::i/tag ::i/add, ::i/args [[::r/di] ::r/al]}
                      [0x00 0x06 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/al]}
                      [0x00 0x06 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/al]}
                      [0x00 0x07] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/al]}
                      [0x00 0x08] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/cl]}
                      [0x00 0x09] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/cl]}
                      [0x00 0x0a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/cl]}
                      [0x00 0x0b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/cl]}
                      [0x00 0x0c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/cl]}
                      [0x00 0x0d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/cl]}
                      [0x00 0x0e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/cl]}
                      [0x00 0x0e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/cl]}
                      [0x00 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/cl]}
                      [0x00 0x10] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dl]}
                      [0x00 0x11] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dl]}
                      [0x00 0x12] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dl]}
                      [0x00 0x13] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dl]}
                      [0x00 0x14] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dl]}
                      [0x00 0x15] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dl]}
                      [0x00 0x16 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/dl]}
                      [0x00 0x16 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/dl]}
                      [0x00 0x17] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dl]}
                      [0x00 0x18] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bl]}
                      [0x00 0x19] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bl]}
                      [0x00 0x1a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bl]}
                      [0x00 0x1b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bl]}
                      [0x00 0x1c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bl]}
                      [0x00 0x1d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bl]}
                      [0x00 0x1e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/bl]}
                      [0x00 0x1e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/bl]}
                      [0x00 0x1f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bl]}
                      [0x00 0x20] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ah]}
                      [0x00 0x21] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ah]}
                      [0x00 0x22] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ah]}
                      [0x00 0x23] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ah]}
                      [0x00 0x24] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ah]}
                      [0x00 0x25] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ah]}
                      [0x00 0x26 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/ah]}
                      [0x00 0x26 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/ah]}
                      [0x00 0x27] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ah]}
                      [0x00 0x28] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ch]}
                      [0x00 0x29] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ch]}
                      [0x00 0x2a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ch]}
                      [0x00 0x2b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ch]}
                      [0x00 0x2c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ch]}
                      [0x00 0x2d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ch]}
                      [0x00 0x2e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/ch]}
                      [0x00 0x2e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/ch]}
                      [0x00 0x2f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ch]}
                      [0x00 0x30] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dh]}
                      [0x00 0x31] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dh]}
                      [0x00 0x32] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dh]}
                      [0x00 0x33] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dh]}
                      [0x00 0x34] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dh]}
                      [0x00 0x35] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dh]}
                      [0x00 0x36 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/dh]}
                      [0x00 0x36 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/dh]}
                      [0x00 0x37] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dh]}
                      [0x00 0x38] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bh]}
                      [0x00 0x39] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bh]}
                      [0x00 0x3a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bh]}
                      [0x00 0x3b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bh]}
                      [0x00 0x3c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bh]}
                      [0x00 0x3d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bh]}
                      [0x00 0x3e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/bh]}
                      [0x00 0x3e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/bh]}
                      [0x00 0x3f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bh]}
                      [0x00 0x40 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/al]}
                      [0x00 0x40 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/al]}
                      [0x00 0x40 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/al]}
                      [0x00 0x41 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/al]}
                      [0x00 0x41 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/al]}
                      [0x00 0x41 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/al]}
                      [0x00 0x42 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/al]}
                      [0x00 0x42 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/al]}
                      [0x00 0x42 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/al]}
                      [0x00 0x43 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/al]}
                      [0x00 0x43 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/al]}
                      [0x00 0x43 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/al]}
                      [0x00 0x44 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/al]}
                      [0x00 0x44 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/al]}
                      [0x00 0x44 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/al]}
                      [0x00 0x45 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/al]}
                      [0x00 0x45 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/al]}
                      [0x00 0x45 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/al]}
                      [0x00 0x46 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/al]}
                      [0x00 0x46 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/al]}
                      [0x00 0x46 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/al]}
                      [0x00 0x47 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/al]}
                      [0x00 0x47 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/al]}
                      [0x00 0x47 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/al]}
                      [0x00 0x48 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/cl]}
                      [0x00 0x48 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
                      [0x00 0x48 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
                      [0x00 0x49 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/cl]}
                      [0x00 0x49 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
                      [0x00 0x49 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
                      [0x00 0x4a 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/cl]}
                      [0x00 0x4a 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
                      [0x00 0x4a 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
                      [0x00 0x4b 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/cl]}
                      [0x00 0x4b 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
                      [0x00 0x4b 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
                      [0x00 0x4c 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/cl]}
                      [0x00 0x4c 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/cl]}
                      [0x00 0x4c 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/cl]}
                      [0x00 0x4d 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/cl]}
                      [0x00 0x4d 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/cl]}
                      [0x00 0x4d 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/cl]}
                      [0x00 0x4e 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/cl]}
                      [0x00 0x4e 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/cl]}
                      [0x00 0x4e 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/cl]}
                      [0x00 0x4f 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/cl]}
                      [0x00 0x4f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/cl]}
                      [0x00 0x4f 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/cl]}
                      [0x00 0x50 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dl]}
                      [0x00 0x50 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/dl]}
                      [0x00 0x50 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/dl]}
                      [0x00 0x51 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dl]}
                      [0x00 0x51 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/dl]}
                      [0x00 0x51 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/dl]}
                      [0x00 0x52 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dl]}
                      [0x00 0x52 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/dl]}
                      [0x00 0x52 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/dl]}
                      [0x00 0x53 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dl]}
                      [0x00 0x53 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/dl]}
                      [0x00 0x53 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/dl]}
                      [0x00 0x54 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dl]}
                      [0x00 0x54 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/dl]}
                      [0x00 0x54 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/dl]}
                      [0x00 0x55 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dl]}
                      [0x00 0x55 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/dl]}
                      [0x00 0x55 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/dl]}
                      [0x00 0x56 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/dl]}
                      [0x00 0x56 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/dl]}
                      [0x00 0x56 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/dl]}
                      [0x00 0x57 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dl]}
                      [0x00 0x57 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/dl]}
                      [0x00 0x57 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/dl]}
                      [0x00 0x58 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bl]}
                      [0x00 0x58 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/bl]}
                      [0x00 0x58 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/bl]}
                      [0x00 0x59 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bl]}
                      [0x00 0x59 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/bl]}
                      [0x00 0x59 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/bl]}
                      [0x00 0x5a 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bl]}
                      [0x00 0x5a 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/bl]}
                      [0x00 0x5a 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/bl]}
                      [0x00 0x5b 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bl]}
                      [0x00 0x5b 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/bl]}
                      [0x00 0x5b 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/bl]}
                      [0x00 0x5c 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bl]}
                      [0x00 0x5c 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/bl]}
                      [0x00 0x5c 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/bl]}
                      [0x00 0x5d 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bl]}
                      [0x00 0x5d 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/bl]}
                      [0x00 0x5d 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/bl]}
                      [0x00 0x5e 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/bl]}
                      [0x00 0x5e 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/bl]}
                      [0x00 0x5e 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/bl]}
                      [0x00 0x5f 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bl]}
                      [0x00 0x5f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/bl]}
                      [0x00 0x5f 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/bl]}
                      [0x00 0x60 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ah]}
                      [0x00 0x60 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/ah]}
                      [0x00 0x60 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/ah]}
                      [0x00 0x61 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ah]}
                      [0x00 0x61 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/ah]}
                      [0x00 0x61 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/ah]}
                      [0x00 0x62 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ah]}
                      [0x00 0x62 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/ah]}
                      [0x00 0x62 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/ah]}
                      [0x00 0x63 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ah]}
                      [0x00 0x63 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/ah]}
                      [0x00 0x63 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/ah]}
                      [0x00 0x64 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ah]}
                      [0x00 0x64 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/ah]}
                      [0x00 0x64 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/ah]}
                      [0x00 0x65 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ah]}
                      [0x00 0x65 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/ah]}
                      [0x00 0x65 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/ah]}
                      [0x00 0x66 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/ah]}
                      [0x00 0x66 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/ah]}
                      [0x00 0x66 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/ah]}
                      [0x00 0x67 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ah]}
                      [0x00 0x67 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/ah]}
                      [0x00 0x67 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/ah]}
                      [0x00 0x68 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ch]}
                      [0x00 0x68 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/ch]}
                      [0x00 0x68 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/ch]}
                      [0x00 0x69 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ch]}
                      [0x00 0x69 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/ch]}
                      [0x00 0x69 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/ch]}
                      [0x00 0x6a 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ch]}
                      [0x00 0x6a 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/ch]}
                      [0x00 0x6a 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/ch]}
                      [0x00 0x6b 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ch]}
                      [0x00 0x6b 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/ch]}
                      [0x00 0x6b 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/ch]}
                      [0x00 0x6c 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ch]}
                      [0x00 0x6c 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/ch]}
                      [0x00 0x6c 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/ch]}
                      [0x00 0x6d 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ch]}
                      [0x00 0x6d 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/ch]}
                      [0x00 0x6d 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/ch]}
                      [0x00 0x6e 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/ch]}
                      [0x00 0x6e 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/ch]}
                      [0x00 0x6e 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/ch]}
                      [0x00 0x6f 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ch]}
                      [0x00 0x6f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/ch]}
                      [0x00 0x6f 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/ch]}
                      [0x00 0x70 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dh]}
                      [0x00 0x70 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/dh]}
                      [0x00 0x70 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/dh]}
                      [0x00 0x71 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dh]}
                      [0x00 0x71 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/dh]}
                      [0x00 0x71 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/dh]}
                      [0x00 0x72 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dh]}
                      [0x00 0x72 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/dh]}
                      [0x00 0x72 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/dh]}
                      [0x00 0x73 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dh]}
                      [0x00 0x73 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/dh]}
                      [0x00 0x73 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/dh]}
                      [0x00 0x74 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dh]}
                      [0x00 0x74 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/dh]}
                      [0x00 0x74 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/dh]}
                      [0x00 0x75 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dh]}
                      [0x00 0x75 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/dh]}
                      [0x00 0x75 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/dh]}
                      [0x00 0x76 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/dh]}
                      [0x00 0x76 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/dh]}
                      [0x00 0x76 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/dh]}
                      [0x00 0x77 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dh]}
                      [0x00 0x77 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/dh]}
                      [0x00 0x77 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/dh]}
                      [0x00 0x78 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bh]}
                      [0x00 0x78 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/bh]}
                      [0x00 0x78 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/bh]}
                      [0x00 0x79 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bh]}
                      [0x00 0x79 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/bh]}
                      [0x00 0x79 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/bh]}
                      [0x00 0x7a 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bh]}
                      [0x00 0x7a 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/bh]}
                      [0x00 0x7a 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/bh]}
                      [0x00 0x7b 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bh]}
                      [0x00 0x7b 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/bh]}
                      [0x00 0x7b 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/bh]}
                      [0x00 0x7c 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bh]}
                      [0x00 0x7c 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/bh]}
                      [0x00 0x7c 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/bh]}
                      [0x00 0x7d 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bh]}
                      [0x00 0x7d 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/bh]}
                      [0x00 0x7d 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/bh]}
                      [0x00 0x7e 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/bh]}
                      [0x00 0x7e 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/bh]}
                      [0x00 0x7e 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/bh]}
                      [0x00 0x7f 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bh]}
                      [0x00 0x7f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/bh]}
                      [0x00 0x7f 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/bh]}
                      [0x00 0x80 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/al]}
                      [0x00 0x80 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/al]}
                      [0x00 0x80 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/al]}
                      [0x00 0x81 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/al]}
                      [0x00 0x81 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/al]}
                      [0x00 0x81 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/al]}
                      [0x00 0x82 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/al]}
                      [0x00 0x82 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/al]}
                      [0x00 0x82 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/al]}
                      [0x00 0x83 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/al]}
                      [0x00 0x83 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/al]}
                      [0x00 0x83 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/al]}
                      [0x00 0x84 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/al]}
                      [0x00 0x84 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/al]}
                      [0x00 0x84 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/al]}
                      [0x00 0x85 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/al]}
                      [0x00 0x85 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/al]}
                      [0x00 0x85 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/al]}
                      [0x00 0x86 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/al]}
                      [0x00 0x86 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/al]}
                      [0x00 0x86 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/al]}
                      [0x00 0x87 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/al]}
                      [0x00 0x87 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/al]}
                      [0x00 0x87 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/al]}
                      [0x00 0x88 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/cl]}
                      [0x00 0x88 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
                      [0x00 0x88 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
                      [0x00 0x89 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/cl]}
                      [0x00 0x89 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
                      [0x00 0x89 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
                      [0x00 0x8a 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/cl]}
                      [0x00 0x8a 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
                      [0x00 0x8a 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
                      [0x00 0x8b 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/cl]}
                      [0x00 0x8b 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
                      [0x00 0x8b 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
                      [0x00 0x8c 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/cl]}
                      [0x00 0x8c 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/cl]}
                      [0x00 0x8c 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/cl]}
                      [0x00 0x8d 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/cl]}
                      [0x00 0x8d 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/cl]}
                      [0x00 0x8d 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/cl]}
                      [0x00 0x8e 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/cl]}
                      [0x00 0x8e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/cl]}
                      [0x00 0x8e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/cl]}
                      [0x00 0x8f 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/cl]}
                      [0x00 0x8f 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/cl]}
                      [0x00 0x8f 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/cl]}
                      [0x00 0x90 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dl]}
                      [0x00 0x90 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/dl]}
                      [0x00 0x90 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/dl]}
                      [0x00 0x91 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dl]}
                      [0x00 0x91 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/dl]}
                      [0x00 0x91 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/dl]}
                      [0x00 0x92 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dl]}
                      [0x00 0x92 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/dl]}
                      [0x00 0x92 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/dl]}
                      [0x00 0x93 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dl]}
                      [0x00 0x93 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/dl]}
                      [0x00 0x93 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/dl]}
                      [0x00 0x94 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dl]}
                      [0x00 0x94 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/dl]}
                      [0x00 0x94 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/dl]}
                      [0x00 0x95 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dl]}
                      [0x00 0x95 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/dl]}
                      [0x00 0x95 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/dl]}
                      [0x00 0x96 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/dl]}
                      [0x00 0x96 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/dl]}
                      [0x00 0x96 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/dl]}
                      [0x00 0x97 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dl]}
                      [0x00 0x97 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/dl]}
                      [0x00 0x97 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/dl]}
                      [0x00 0x98 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bl]}
                      [0x00 0x98 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/bl]}
                      [0x00 0x98 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/bl]}
                      [0x00 0x99 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bl]}
                      [0x00 0x99 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/bl]}
                      [0x00 0x99 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/bl]}
                      [0x00 0x9a 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bl]}
                      [0x00 0x9a 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/bl]}
                      [0x00 0x9a 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/bl]}
                      [0x00 0x9b 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bl]}
                      [0x00 0x9b 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/bl]}
                      [0x00 0x9b 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/bl]}
                      [0x00 0x9c 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bl]}
                      [0x00 0x9c 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/bl]}
                      [0x00 0x9c 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/bl]}
                      [0x00 0x9d 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bl]}
                      [0x00 0x9d 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/bl]}
                      [0x00 0x9d 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/bl]}
                      [0x00 0x9e 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/bl]}
                      [0x00 0x9e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/bl]}
                      [0x00 0x9e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/bl]}
                      [0x00 0x9f 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bl]}
                      [0x00 0x9f 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/bl]}
                      [0x00 0x9f 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/bl]}
                      [0x00 0xa0 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ah]}
                      [0x00 0xa0 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/ah]}
                      [0x00 0xa0 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/ah]}
                      [0x00 0xa1 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ah]}
                      [0x00 0xa1 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/ah]}
                      [0x00 0xa1 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/ah]}
                      [0x00 0xa2 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ah]}
                      [0x00 0xa2 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/ah]}
                      [0x00 0xa2 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/ah]}
                      [0x00 0xa3 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ah]}
                      [0x00 0xa3 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/ah]}
                      [0x00 0xa3 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/ah]}
                      [0x00 0xa4 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ah]}
                      [0x00 0xa4 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/ah]}
                      [0x00 0xa4 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/ah]}
                      [0x00 0xa5 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ah]}
                      [0x00 0xa5 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/ah]}
                      [0x00 0xa5 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/ah]}
                      [0x00 0xa6 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/ah]}
                      [0x00 0xa6 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/ah]}
                      [0x00 0xa6 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/ah]}
                      [0x00 0xa7 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ah]}
                      [0x00 0xa7 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/ah]}
                      [0x00 0xa7 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/ah]}
                      [0x00 0xa8 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ch]}
                      [0x00 0xa8 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/ch]}
                      [0x00 0xa8 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/ch]}
                      [0x00 0xa9 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ch]}
                      [0x00 0xa9 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/ch]}
                      [0x00 0xa9 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/ch]}
                      [0x00 0xaa 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ch]}
                      [0x00 0xaa 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/ch]}
                      [0x00 0xaa 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/ch]}
                      [0x00 0xab 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ch]}
                      [0x00 0xab 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/ch]}
                      [0x00 0xab 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/ch]}
                      [0x00 0xac 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ch]}
                      [0x00 0xac 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/ch]}
                      [0x00 0xac 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/ch]}
                      [0x00 0xad 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ch]}
                      [0x00 0xad 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/ch]}
                      [0x00 0xad 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/ch]}
                      [0x00 0xae 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/ch]}
                      [0x00 0xae 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/ch]}
                      [0x00 0xae 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/ch]}
                      [0x00 0xaf 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ch]}
                      [0x00 0xaf 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/ch]}
                      [0x00 0xaf 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/ch]}
                      [0x00 0xb0 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dh]}
                      [0x00 0xb0 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/dh]}
                      [0x00 0xb0 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/dh]}
                      [0x00 0xb1 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dh]}
                      [0x00 0xb1 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/dh]}
                      [0x00 0xb1 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/dh]}
                      [0x00 0xb2 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dh]}
                      [0x00 0xb2 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/dh]}
                      [0x00 0xb2 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/dh]}
                      [0x00 0xb3 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dh]}
                      [0x00 0xb3 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/dh]}
                      [0x00 0xb3 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/dh]}
                      [0x00 0xb4 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dh]}
                      [0x00 0xb4 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/dh]}
                      [0x00 0xb4 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/dh]}
                      [0x00 0xb5 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dh]}
                      [0x00 0xb5 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/dh]}
                      [0x00 0xb5 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/dh]}
                      [0x00 0xb6 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/dh]}
                      [0x00 0xb6 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/dh]}
                      [0x00 0xb6 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/dh]}
                      [0x00 0xb7 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dh]}
                      [0x00 0xb7 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/dh]}
                      [0x00 0xb7 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/dh]}
                      [0x00 0xb8 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bh]}
                      [0x00 0xb8 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/bh]}
                      [0x00 0xb8 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/bh]}
                      [0x00 0xb9 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bh]}
                      [0x00 0xb9 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/bh]}
                      [0x00 0xb9 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/bh]}
                      [0x00 0xba 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bh]}
                      [0x00 0xba 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/bh]}
                      [0x00 0xba 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/bh]}
                      [0x00 0xbb 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bh]}
                      [0x00 0xbb 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/bh]}
                      [0x00 0xbb 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/bh]}
                      [0x00 0xbc 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bh]}
                      [0x00 0xbc 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/bh]}
                      [0x00 0xbc 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/bh]}
                      [0x00 0xbd 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bh]}
                      [0x00 0xbd 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/bh]}
                      [0x00 0xbd 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/bh]}
                      [0x00 0xbe 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/bh]}
                      [0x00 0xbe 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/bh]}
                      [0x00 0xbe 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/bh]}
                      [0x00 0xbf 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bh]}
                      [0x00 0xbf 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/bh]}
                      [0x00 0xbf 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/bh]}
                      [0x00 0xc0] {::i/tag ::i/add, ::i/args [::r/al ::r/al]}
                      [0x00 0xc1] {::i/tag ::i/add, ::i/args [::r/cl ::r/al]}
                      [0x00 0xc2] {::i/tag ::i/add, ::i/args [::r/dl ::r/al]}
                      [0x00 0xc3] {::i/tag ::i/add, ::i/args [::r/bl ::r/al]}
                      [0x00 0xc4] {::i/tag ::i/add, ::i/args [::r/ah ::r/al]}
                      [0x00 0xc5] {::i/tag ::i/add, ::i/args [::r/ch ::r/al]}
                      [0x00 0xc6] {::i/tag ::i/add, ::i/args [::r/dh ::r/al]}
                      [0x00 0xc7] {::i/tag ::i/add, ::i/args [::r/bh ::r/al]}
                      [0x00 0xc8] {::i/tag ::i/add, ::i/args [::r/al ::r/cl]}
                      [0x00 0xc9] {::i/tag ::i/add, ::i/args [::r/cl ::r/cl]}
                      [0x00 0xca] {::i/tag ::i/add, ::i/args [::r/dl ::r/cl]}
                      [0x00 0xcb] {::i/tag ::i/add, ::i/args [::r/bl ::r/cl]}
                      [0x00 0xcc] {::i/tag ::i/add, ::i/args [::r/ah ::r/cl]}
                      [0x00 0xcd] {::i/tag ::i/add, ::i/args [::r/ch ::r/cl]}
                      [0x00 0xce] {::i/tag ::i/add, ::i/args [::r/dh ::r/cl]}
                      [0x00 0xcf] {::i/tag ::i/add, ::i/args [::r/bh ::r/cl]}
                      [0x00 0xd0] {::i/tag ::i/add, ::i/args [::r/al ::r/dl]}
                      [0x00 0xd1] {::i/tag ::i/add, ::i/args [::r/cl ::r/dl]}
                      [0x00 0xd2] {::i/tag ::i/add, ::i/args [::r/dl ::r/dl]}
                      [0x00 0xd3] {::i/tag ::i/add, ::i/args [::r/bl ::r/dl]}
                      [0x00 0xd4] {::i/tag ::i/add, ::i/args [::r/ah ::r/dl]}
                      [0x00 0xd5] {::i/tag ::i/add, ::i/args [::r/ch ::r/dl]}
                      [0x00 0xd6] {::i/tag ::i/add, ::i/args [::r/dh ::r/dl]}
                      [0x00 0xd7] {::i/tag ::i/add, ::i/args [::r/bh ::r/dl]}
                      [0x00 0xd8] {::i/tag ::i/add, ::i/args [::r/al ::r/bl]}
                      [0x00 0xd9] {::i/tag ::i/add, ::i/args [::r/cl ::r/bl]}
                      [0x00 0xda] {::i/tag ::i/add, ::i/args [::r/dl ::r/bl]}
                      [0x00 0xdb] {::i/tag ::i/add, ::i/args [::r/bl ::r/bl]}
                      [0x00 0xdc] {::i/tag ::i/add, ::i/args [::r/ah ::r/bl]}
                      [0x00 0xdd] {::i/tag ::i/add, ::i/args [::r/ch ::r/bl]}
                      [0x00 0xde] {::i/tag ::i/add, ::i/args [::r/dh ::r/bl]}
                      [0x00 0xdf] {::i/tag ::i/add, ::i/args [::r/bh ::r/bl]}
                      [0x00 0xe0] {::i/tag ::i/add, ::i/args [::r/al ::r/ah]}
                      [0x00 0xe1] {::i/tag ::i/add, ::i/args [::r/cl ::r/ah]}
                      [0x00 0xe2] {::i/tag ::i/add, ::i/args [::r/dl ::r/ah]}
                      [0x00 0xe3] {::i/tag ::i/add, ::i/args [::r/bl ::r/ah]}
                      [0x00 0xe4] {::i/tag ::i/add, ::i/args [::r/ah ::r/ah]}
                      [0x00 0xe5] {::i/tag ::i/add, ::i/args [::r/ch ::r/ah]}
                      [0x00 0xe6] {::i/tag ::i/add, ::i/args [::r/dh ::r/ah]}
                      [0x00 0xe7] {::i/tag ::i/add, ::i/args [::r/bh ::r/ah]}
                      [0x00 0xe8] {::i/tag ::i/add, ::i/args [::r/al ::r/ch]}
                      [0x00 0xe9] {::i/tag ::i/add, ::i/args [::r/cl ::r/ch]}
                      [0x00 0xea] {::i/tag ::i/add, ::i/args [::r/dl ::r/ch]}
                      [0x00 0xeb] {::i/tag ::i/add, ::i/args [::r/bl ::r/ch]}
                      [0x00 0xec] {::i/tag ::i/add, ::i/args [::r/ah ::r/ch]}
                      [0x00 0xed] {::i/tag ::i/add, ::i/args [::r/ch ::r/ch]}
                      [0x00 0xee] {::i/tag ::i/add, ::i/args [::r/dh ::r/ch]}
                      [0x00 0xef] {::i/tag ::i/add, ::i/args [::r/bh ::r/ch]}
                      [0x00 0xf0] {::i/tag ::i/add, ::i/args [::r/al ::r/dh]}
                      [0x00 0xf1] {::i/tag ::i/add, ::i/args [::r/cl ::r/dh]}
                      [0x00 0xf2] {::i/tag ::i/add, ::i/args [::r/dl ::r/dh]}
                      [0x00 0xf3] {::i/tag ::i/add, ::i/args [::r/bl ::r/dh]}
                      [0x00 0xf4] {::i/tag ::i/add, ::i/args [::r/ah ::r/dh]}
                      [0x00 0xf5] {::i/tag ::i/add, ::i/args [::r/ch ::r/dh]}
                      [0x00 0xf6] {::i/tag ::i/add, ::i/args [::r/dh ::r/dh]}
                      [0x00 0xf7] {::i/tag ::i/add, ::i/args [::r/bh ::r/dh]}
                      [0x00 0xf8] {::i/tag ::i/add, ::i/args [::r/al ::r/bh]}
                      [0x00 0xf9] {::i/tag ::i/add, ::i/args [::r/cl ::r/bh]}
                      [0x00 0xfa] {::i/tag ::i/add, ::i/args [::r/dl ::r/bh]}
                      [0x00 0xfb] {::i/tag ::i/add, ::i/args [::r/bl ::r/bh]}
                      [0x00 0xfc] {::i/tag ::i/add, ::i/args [::r/ah ::r/bh]}
                      [0x00 0xfd] {::i/tag ::i/add, ::i/args [::r/ch ::r/bh]}
                      [0x00 0xfe] {::i/tag ::i/add, ::i/args [::r/dh ::r/bh]}
                      [0x00 0xff] {::i/tag ::i/add, ::i/args [::r/bh ::r/bh]}
                      [0x01 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ax]}
                      [0x01 0x01] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ax]}
                      [0x01 0x02] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ax]}
                      [0x01 0x03] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ax]}
                      [0x01 0x04] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ax]}
                      [0x01 0x05] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ax]}
                      [0x01 0x06 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/ax]}
                      [0x01 0x06 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/ax]}
                      [0x01 0x07] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ax]}
                      [0x01 0x08] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/cx]}
                      [0x01 0x09] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/cx]}
                      [0x01 0x0a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/cx]}
                      [0x01 0x0b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/cx]}
                      [0x01 0x0c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/cx]}
                      [0x01 0x0d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/cx]}
                      [0x01 0x0e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/cx]}
                      [0x01 0x0e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/cx]}
                      [0x01 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/cx]}
                      [0x01 0x10] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dx]}
                      [0x01 0x11] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dx]}
                      [0x01 0x12] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dx]}
                      [0x01 0x13] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dx]}
                      [0x01 0x14] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dx]}
                      [0x01 0x15] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dx]}
                      [0x01 0x16 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/dx]}
                      [0x01 0x16 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/dx]}
                      [0x01 0x17] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dx]}
                      [0x01 0x18] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bx]}
                      [0x01 0x19] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bx]}
                      [0x01 0x1a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bx]}
                      [0x01 0x1b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bx]}
                      [0x01 0x1c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bx]}
                      [0x01 0x1d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bx]}
                      [0x01 0x1e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/bx]}
                      [0x01 0x1e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/bx]}
                      [0x01 0x1f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bx]}
                      [0x01 0x20] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/sp]}
                      [0x01 0x21] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/sp]}
                      [0x01 0x22] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/sp]}
                      [0x01 0x23] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/sp]}
                      [0x01 0x24] {::i/tag ::i/add, ::i/args [[::r/si] ::r/sp]}
                      [0x01 0x25] {::i/tag ::i/add, ::i/args [[::r/di] ::r/sp]}
                      [0x01 0x26 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/sp]}
                      [0x01 0x26 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/sp]}
                      [0x01 0x27] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/sp]}
                      [0x01 0x28] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bp]}
                      [0x01 0x29] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bp]}
                      [0x01 0x2a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bp]}
                      [0x01 0x2b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bp]}
                      [0x01 0x2c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bp]}
                      [0x01 0x2d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bp]}
                      [0x01 0x2e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/bp]}
                      [0x01 0x2e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/bp]}
                      [0x01 0x2f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bp]}
                      [0x01 0x30] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/si]}
                      [0x01 0x31] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/si]}
                      [0x01 0x32] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/si]}
                      [0x01 0x33] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/si]}
                      [0x01 0x34] {::i/tag ::i/add, ::i/args [[::r/si] ::r/si]}
                      [0x01 0x35] {::i/tag ::i/add, ::i/args [[::r/di] ::r/si]}
                      [0x01 0x36 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/si]}
                      [0x01 0x36 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/si]}
                      [0x01 0x37] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/si]}
                      [0x01 0x38] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/di]}
                      [0x01 0x39] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/di]}
                      [0x01 0x3a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/di]}
                      [0x01 0x3b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/di]}
                      [0x01 0x3c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/di]}
                      [0x01 0x3d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/di]}
                      [0x01 0x3e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/di]}
                      [0x01 0x3e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/di]}
                      [0x01 0x3f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/di]}
                      [0x01 0x40 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ax]}
                      [0x01 0x40 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/ax]}
                      [0x01 0x40 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/ax]}
                      [0x01 0x41 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ax]}
                      [0x01 0x41 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/ax]}
                      [0x01 0x41 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/ax]}
                      [0x01 0x42 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ax]}
                      [0x01 0x42 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/ax]}
                      [0x01 0x42 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/ax]}
                      [0x01 0x43 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ax]}
                      [0x01 0x43 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/ax]}
                      [0x01 0x43 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/ax]}
                      [0x01 0x44 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ax]}
                      [0x01 0x44 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/ax]}
                      [0x01 0x44 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/ax]}
                      [0x01 0x45 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ax]}
                      [0x01 0x45 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/ax]}
                      [0x01 0x45 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/ax]}
                      [0x01 0x46 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/ax]}
                      [0x01 0x46 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/ax]}
                      [0x01 0x46 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/ax]}
                      [0x01 0x47 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ax]}
                      [0x01 0x47 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/ax]}
                      [0x01 0x47 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/ax]}
                      [0x01 0x48 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/cx]}
                      [0x01 0x48 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/cx]}
                      [0x01 0x48 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/cx]}
                      [0x01 0x49 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/cx]}
                      [0x01 0x49 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/cx]}
                      [0x01 0x49 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/cx]}
                      [0x01 0x4a 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/cx]}
                      [0x01 0x4a 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/cx]}
                      [0x01 0x4a 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/cx]}
                      [0x01 0x4b 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/cx]}
                      [0x01 0x4b 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/cx]}
                      [0x01 0x4b 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/cx]}
                      [0x01 0x4c 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/cx]}
                      [0x01 0x4c 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/cx]}
                      [0x01 0x4c 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/cx]}
                      [0x01 0x4d 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/cx]}
                      [0x01 0x4d 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/cx]}
                      [0x01 0x4d 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/cx]}
                      [0x01 0x4e 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/cx]}
                      [0x01 0x4e 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/cx]}
                      [0x01 0x4e 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/cx]}
                      [0x01 0x4f 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/cx]}
                      [0x01 0x4f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/cx]}
                      [0x01 0x4f 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/cx]}
                      [0x01 0x50 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dx]}
                      [0x01 0x50 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/dx]}
                      [0x01 0x50 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/dx]}
                      [0x01 0x51 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dx]}
                      [0x01 0x51 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/dx]}
                      [0x01 0x51 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/dx]}
                      [0x01 0x52 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dx]}
                      [0x01 0x52 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/dx]}
                      [0x01 0x52 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/dx]}
                      [0x01 0x53 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dx]}
                      [0x01 0x53 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/dx]}
                      [0x01 0x53 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/dx]}
                      [0x01 0x54 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dx]}
                      [0x01 0x54 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/dx]}
                      [0x01 0x54 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/dx]}
                      [0x01 0x55 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dx]}
                      [0x01 0x55 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/dx]}
                      [0x01 0x55 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/dx]}
                      [0x01 0x56 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/dx]}
                      [0x01 0x56 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/dx]}
                      [0x01 0x56 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/dx]}
                      [0x01 0x57 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dx]}
                      [0x01 0x57 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/dx]}
                      [0x01 0x57 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/dx]}
                      [0x01 0x58 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bx]}
                      [0x01 0x58 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/bx]}
                      [0x01 0x58 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/bx]}
                      [0x01 0x59 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bx]}
                      [0x01 0x59 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/bx]}
                      [0x01 0x59 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/bx]}
                      [0x01 0x5a 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bx]}
                      [0x01 0x5a 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/bx]}
                      [0x01 0x5a 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/bx]}
                      [0x01 0x5b 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bx]}
                      [0x01 0x5b 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/bx]}
                      [0x01 0x5b 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/bx]}
                      [0x01 0x5c 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bx]}
                      [0x01 0x5c 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/bx]}
                      [0x01 0x5c 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/bx]}
                      [0x01 0x5d 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bx]}
                      [0x01 0x5d 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/bx]}
                      [0x01 0x5d 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/bx]}
                      [0x01 0x5e 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/bx]}
                      [0x01 0x5e 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/bx]}
                      [0x01 0x5e 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/bx]}
                      [0x01 0x5f 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bx]}
                      [0x01 0x5f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/bx]}
                      [0x01 0x5f 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/bx]}
                      [0x01 0x60 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/sp]}
                      [0x01 0x60 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/sp]}
                      [0x01 0x60 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/sp]}
                      [0x01 0x61 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/sp]}
                      [0x01 0x61 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/sp]}
                      [0x01 0x61 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/sp]}
                      [0x01 0x62 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/sp]}
                      [0x01 0x62 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/sp]}
                      [0x01 0x62 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/sp]}
                      [0x01 0x63 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/sp]}
                      [0x01 0x63 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/sp]}
                      [0x01 0x63 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/sp]}
                      [0x01 0x64 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/sp]}
                      [0x01 0x64 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/sp]}
                      [0x01 0x64 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/sp]}
                      [0x01 0x65 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/sp]}
                      [0x01 0x65 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/sp]}
                      [0x01 0x65 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/sp]}
                      [0x01 0x66 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/sp]}
                      [0x01 0x66 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/sp]}
                      [0x01 0x66 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/sp]}
                      [0x01 0x67 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/sp]}
                      [0x01 0x67 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/sp]}
                      [0x01 0x67 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/sp]}
                      [0x01 0x68 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bp]}
                      [0x01 0x68 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/bp]}
                      [0x01 0x68 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/bp]}
                      [0x01 0x69 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bp]}
                      [0x01 0x69 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/bp]}
                      [0x01 0x69 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/bp]}
                      [0x01 0x6a 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bp]}
                      [0x01 0x6a 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/bp]}
                      [0x01 0x6a 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/bp]}
                      [0x01 0x6b 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bp]}
                      [0x01 0x6b 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/bp]}
                      [0x01 0x6b 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/bp]}
                      [0x01 0x6c 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bp]}
                      [0x01 0x6c 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/bp]}
                      [0x01 0x6c 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/bp]}
                      [0x01 0x6d 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bp]}
                      [0x01 0x6d 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/bp]}
                      [0x01 0x6d 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/bp]}
                      [0x01 0x6e 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/bp]}
                      [0x01 0x6e 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/bp]}
                      [0x01 0x6e 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/bp]}
                      [0x01 0x6f 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bp]}
                      [0x01 0x6f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/bp]}
                      [0x01 0x6f 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/bp]}
                      [0x01 0x70 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/si]}
                      [0x01 0x70 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/si]}
                      [0x01 0x70 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/si]}
                      [0x01 0x71 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/si]}
                      [0x01 0x71 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/si]}
                      [0x01 0x71 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/si]}
                      [0x01 0x72 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/si]}
                      [0x01 0x72 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/si]}
                      [0x01 0x72 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/si]}
                      [0x01 0x73 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/si]}
                      [0x01 0x73 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/si]}
                      [0x01 0x73 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/si]}
                      [0x01 0x74 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/si]}
                      [0x01 0x74 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/si]}
                      [0x01 0x74 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/si]}
                      [0x01 0x75 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/si]}
                      [0x01 0x75 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/si]}
                      [0x01 0x75 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/si]}
                      [0x01 0x76 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/si]}
                      [0x01 0x76 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/si]}
                      [0x01 0x76 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/si]}
                      [0x01 0x77 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/si]}
                      [0x01 0x77 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/si]}
                      [0x01 0x77 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/si]}
                      [0x01 0x78 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/di]}
                      [0x01 0x78 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -16] ::r/di]}
                      [0x01 0x78 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 15] ::r/di]}
                      [0x01 0x79 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/di]}
                      [0x01 0x79 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -16] ::r/di]}
                      [0x01 0x79 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 15] ::r/di]}
                      [0x01 0x7a 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/di]}
                      [0x01 0x7a 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -16] ::r/di]}
                      [0x01 0x7a 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 15] ::r/di]}
                      [0x01 0x7b 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/di]}
                      [0x01 0x7b 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -16] ::r/di]}
                      [0x01 0x7b 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 15] ::r/di]}
                      [0x01 0x7c 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/di]}
                      [0x01 0x7c 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -16] ::r/di]}
                      [0x01 0x7c 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 15] ::r/di]}
                      [0x01 0x7d 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/di]}
                      [0x01 0x7d 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -16] ::r/di]}
                      [0x01 0x7d 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 15] ::r/di]}
                      [0x01 0x7e 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/di]}
                      [0x01 0x7e 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -16] ::r/di]}
                      [0x01 0x7e 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 15] ::r/di]}
                      [0x01 0x7f 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/di]}
                      [0x01 0x7f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -16] ::r/di]}
                      [0x01 0x7f 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 15] ::r/di]}
                      [0x01 0x80 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ax]}
                      [0x01 0x80 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/ax]}
                      [0x01 0x80 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/ax]}
                      [0x01 0x81 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ax]}
                      [0x01 0x81 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/ax]}
                      [0x01 0x81 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/ax]}
                      [0x01 0x82 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ax]}
                      [0x01 0x82 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/ax]}
                      [0x01 0x82 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/ax]}
                      [0x01 0x83 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ax]}
                      [0x01 0x83 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/ax]}
                      [0x01 0x83 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/ax]}
                      [0x01 0x84 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ax]}
                      [0x01 0x84 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/ax]}
                      [0x01 0x84 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/ax]}
                      [0x01 0x85 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ax]}
                      [0x01 0x85 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/ax]}
                      [0x01 0x85 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/ax]}
                      [0x01 0x86 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/ax]}
                      [0x01 0x86 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/ax]}
                      [0x01 0x86 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/ax]}
                      [0x01 0x87 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ax]}
                      [0x01 0x87 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/ax]}
                      [0x01 0x87 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/ax]}
                      [0x01 0x88 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/cx]}
                      [0x01 0x88 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/cx]}
                      [0x01 0x88 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/cx]}
                      [0x01 0x89 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/cx]}
                      [0x01 0x89 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/cx]}
                      [0x01 0x89 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/cx]}
                      [0x01 0x8a 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/cx]}
                      [0x01 0x8a 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/cx]}
                      [0x01 0x8a 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/cx]}
                      [0x01 0x8b 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/cx]}
                      [0x01 0x8b 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/cx]}
                      [0x01 0x8b 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/cx]}
                      [0x01 0x8c 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/cx]}
                      [0x01 0x8c 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/cx]}
                      [0x01 0x8c 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/cx]}
                      [0x01 0x8d 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/cx]}
                      [0x01 0x8d 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/cx]}
                      [0x01 0x8d 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/cx]}
                      [0x01 0x8e 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/cx]}
                      [0x01 0x8e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/cx]}
                      [0x01 0x8e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/cx]}
                      [0x01 0x8f 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/cx]}
                      [0x01 0x8f 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/cx]}
                      [0x01 0x8f 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/cx]}
                      [0x01 0x90 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dx]}
                      [0x01 0x90 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/dx]}
                      [0x01 0x90 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/dx]}
                      [0x01 0x91 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dx]}
                      [0x01 0x91 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/dx]}
                      [0x01 0x91 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/dx]}
                      [0x01 0x92 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dx]}
                      [0x01 0x92 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/dx]}
                      [0x01 0x92 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/dx]}
                      [0x01 0x93 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dx]}
                      [0x01 0x93 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/dx]}
                      [0x01 0x93 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/dx]}
                      [0x01 0x94 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dx]}
                      [0x01 0x94 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/dx]}
                      [0x01 0x94 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/dx]}
                      [0x01 0x95 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dx]}
                      [0x01 0x95 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/dx]}
                      [0x01 0x95 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/dx]}
                      [0x01 0x96 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/dx]}
                      [0x01 0x96 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/dx]}
                      [0x01 0x96 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/dx]}
                      [0x01 0x97 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dx]}
                      [0x01 0x97 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/dx]}
                      [0x01 0x97 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/dx]}
                      [0x01 0x98 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bx]}
                      [0x01 0x98 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/bx]}
                      [0x01 0x98 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/bx]}
                      [0x01 0x99 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bx]}
                      [0x01 0x99 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/bx]}
                      [0x01 0x99 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/bx]}
                      [0x01 0x9a 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bx]}
                      [0x01 0x9a 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/bx]}
                      [0x01 0x9a 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/bx]}
                      [0x01 0x9b 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bx]}
                      [0x01 0x9b 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/bx]}
                      [0x01 0x9b 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/bx]}
                      [0x01 0x9c 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bx]}
                      [0x01 0x9c 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/bx]}
                      [0x01 0x9c 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/bx]}
                      [0x01 0x9d 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bx]}
                      [0x01 0x9d 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/bx]}
                      [0x01 0x9d 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/bx]}
                      [0x01 0x9e 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/bx]}
                      [0x01 0x9e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/bx]}
                      [0x01 0x9e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/bx]}
                      [0x01 0x9f 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bx]}
                      [0x01 0x9f 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/bx]}
                      [0x01 0x9f 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/bx]}
                      [0x01 0xa0 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/sp]}
                      [0x01 0xa0 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/sp]}
                      [0x01 0xa0 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/sp]}
                      [0x01 0xa1 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/sp]}
                      [0x01 0xa1 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/sp]}
                      [0x01 0xa1 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/sp]}
                      [0x01 0xa2 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/sp]}
                      [0x01 0xa2 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/sp]}
                      [0x01 0xa2 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/sp]}
                      [0x01 0xa3 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/sp]}
                      [0x01 0xa3 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/sp]}
                      [0x01 0xa3 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/sp]}
                      [0x01 0xa4 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/sp]}
                      [0x01 0xa4 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/sp]}
                      [0x01 0xa4 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/sp]}
                      [0x01 0xa5 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/sp]}
                      [0x01 0xa5 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/sp]}
                      [0x01 0xa5 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/sp]}
                      [0x01 0xa6 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/sp]}
                      [0x01 0xa6 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/sp]}
                      [0x01 0xa6 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/sp]}
                      [0x01 0xa7 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/sp]}
                      [0x01 0xa7 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/sp]}
                      [0x01 0xa7 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/sp]}
                      [0x01 0xa8 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bp]}
                      [0x01 0xa8 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/bp]}
                      [0x01 0xa8 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/bp]}
                      [0x01 0xa9 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bp]}
                      [0x01 0xa9 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/bp]}
                      [0x01 0xa9 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/bp]}
                      [0x01 0xaa 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bp]}
                      [0x01 0xaa 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/bp]}
                      [0x01 0xaa 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/bp]}
                      [0x01 0xab 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bp]}
                      [0x01 0xab 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/bp]}
                      [0x01 0xab 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/bp]}
                      [0x01 0xac 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bp]}
                      [0x01 0xac 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/bp]}
                      [0x01 0xac 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/bp]}
                      [0x01 0xad 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bp]}
                      [0x01 0xad 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/bp]}
                      [0x01 0xad 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/bp]}
                      [0x01 0xae 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/bp]}
                      [0x01 0xae 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/bp]}
                      [0x01 0xae 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/bp]}
                      [0x01 0xaf 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bp]}
                      [0x01 0xaf 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/bp]}
                      [0x01 0xaf 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/bp]}
                      [0x01 0xb0 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/si]}
                      [0x01 0xb0 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/si]}
                      [0x01 0xb0 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/si]}
                      [0x01 0xb1 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/si]}
                      [0x01 0xb1 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/si]}
                      [0x01 0xb1 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/si]}
                      [0x01 0xb2 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/si]}
                      [0x01 0xb2 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/si]}
                      [0x01 0xb2 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/si]}
                      [0x01 0xb3 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/si]}
                      [0x01 0xb3 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/si]}
                      [0x01 0xb3 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/si]}
                      [0x01 0xb4 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/si]}
                      [0x01 0xb4 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/si]}
                      [0x01 0xb4 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/si]}
                      [0x01 0xb5 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/si]}
                      [0x01 0xb5 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/si]}
                      [0x01 0xb5 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/si]}
                      [0x01 0xb6 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/si]}
                      [0x01 0xb6 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/si]}
                      [0x01 0xb6 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/si]}
                      [0x01 0xb7 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/si]}
                      [0x01 0xb7 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/si]}
                      [0x01 0xb7 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/si]}
                      [0x01 0xb8 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/di]}
                      [0x01 0xb8 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si 4080] ::r/di]}
                      [0x01 0xb8 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si -4081] ::r/di]}
                      [0x01 0xb9 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/di]}
                      [0x01 0xb9 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di 4080] ::r/di]}
                      [0x01 0xb9 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di -4081] ::r/di]}
                      [0x01 0xba 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/di]}
                      [0x01 0xba 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si 4080] ::r/di]}
                      [0x01 0xba 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si -4081] ::r/di]}
                      [0x01 0xbb 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/di]}
                      [0x01 0xbb 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di 4080] ::r/di]}
                      [0x01 0xbb 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di -4081] ::r/di]}
                      [0x01 0xbc 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/si] ::r/di]}
                      [0x01 0xbc 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/si 4080] ::r/di]}
                      [0x01 0xbc 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/si -4081] ::r/di]}
                      [0x01 0xbd 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/di] ::r/di]}
                      [0x01 0xbd 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/di 4080] ::r/di]}
                      [0x01 0xbd 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/di -4081] ::r/di]}
                      [0x01 0xbe 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bp] ::r/di]}
                      [0x01 0xbe 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bp 4080] ::r/di]}
                      [0x01 0xbe 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bp -4081] ::r/di]}
                      [0x01 0xbf 0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/di]}
                      [0x01 0xbf 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx 4080] ::r/di]}
                      [0x01 0xbf 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[::r/bx -4081] ::r/di]}
                      [0x01 0xc0] {::i/tag ::i/add, ::i/args [::r/ax ::r/ax]}
                      [0x01 0xc1] {::i/tag ::i/add, ::i/args [::r/cx ::r/ax]}
                      [0x01 0xc2] {::i/tag ::i/add, ::i/args [::r/dx ::r/ax]}
                      [0x01 0xc3] {::i/tag ::i/add, ::i/args [::r/bx ::r/ax]}
                      [0x01 0xc4] {::i/tag ::i/add, ::i/args [::r/sp ::r/ax]}
                      [0x01 0xc5] {::i/tag ::i/add, ::i/args [::r/bp ::r/ax]}
                      [0x01 0xc6] {::i/tag ::i/add, ::i/args [::r/si ::r/ax]}
                      [0x01 0xc7] {::i/tag ::i/add, ::i/args [::r/di ::r/ax]}
                      [0x01 0xc8] {::i/tag ::i/add, ::i/args [::r/ax ::r/cx]}
                      [0x01 0xc9] {::i/tag ::i/add, ::i/args [::r/cx ::r/cx]}
                      [0x01 0xca] {::i/tag ::i/add, ::i/args [::r/dx ::r/cx]}
                      [0x01 0xcb] {::i/tag ::i/add, ::i/args [::r/bx ::r/cx]}
                      [0x01 0xcc] {::i/tag ::i/add, ::i/args [::r/sp ::r/cx]}
                      [0x01 0xcd] {::i/tag ::i/add, ::i/args [::r/bp ::r/cx]}
                      [0x01 0xce] {::i/tag ::i/add, ::i/args [::r/si ::r/cx]}
                      [0x01 0xcf] {::i/tag ::i/add, ::i/args [::r/di ::r/cx]}
                      [0x01 0xd0] {::i/tag ::i/add, ::i/args [::r/ax ::r/dx]}
                      [0x01 0xd1] {::i/tag ::i/add, ::i/args [::r/cx ::r/dx]}
                      [0x01 0xd2] {::i/tag ::i/add, ::i/args [::r/dx ::r/dx]}
                      [0x01 0xd3] {::i/tag ::i/add, ::i/args [::r/bx ::r/dx]}
                      [0x01 0xd4] {::i/tag ::i/add, ::i/args [::r/sp ::r/dx]}
                      [0x01 0xd5] {::i/tag ::i/add, ::i/args [::r/bp ::r/dx]}
                      [0x01 0xd6] {::i/tag ::i/add, ::i/args [::r/si ::r/dx]}
                      [0x01 0xd7] {::i/tag ::i/add, ::i/args [::r/di ::r/dx]}
                      [0x01 0xd8] {::i/tag ::i/add, ::i/args [::r/ax ::r/bx]}
                      [0x01 0xd9] {::i/tag ::i/add, ::i/args [::r/cx ::r/bx]}
                      [0x01 0xda] {::i/tag ::i/add, ::i/args [::r/dx ::r/bx]}
                      [0x01 0xdb] {::i/tag ::i/add, ::i/args [::r/bx ::r/bx]}
                      [0x01 0xdc] {::i/tag ::i/add, ::i/args [::r/sp ::r/bx]}
                      [0x01 0xdd] {::i/tag ::i/add, ::i/args [::r/bp ::r/bx]}
                      [0x01 0xde] {::i/tag ::i/add, ::i/args [::r/si ::r/bx]}
                      [0x01 0xdf] {::i/tag ::i/add, ::i/args [::r/di ::r/bx]}
                      [0x01 0xe0] {::i/tag ::i/add, ::i/args [::r/ax ::r/sp]}
                      [0x01 0xe1] {::i/tag ::i/add, ::i/args [::r/cx ::r/sp]}
                      [0x01 0xe2] {::i/tag ::i/add, ::i/args [::r/dx ::r/sp]}
                      [0x01 0xe3] {::i/tag ::i/add, ::i/args [::r/bx ::r/sp]}
                      [0x01 0xe4] {::i/tag ::i/add, ::i/args [::r/sp ::r/sp]}
                      [0x01 0xe5] {::i/tag ::i/add, ::i/args [::r/bp ::r/sp]}
                      [0x01 0xe6] {::i/tag ::i/add, ::i/args [::r/si ::r/sp]}
                      [0x01 0xe7] {::i/tag ::i/add, ::i/args [::r/di ::r/sp]}
                      [0x01 0xe8] {::i/tag ::i/add, ::i/args [::r/ax ::r/bp]}
                      [0x01 0xe9] {::i/tag ::i/add, ::i/args [::r/cx ::r/bp]}
                      [0x01 0xea] {::i/tag ::i/add, ::i/args [::r/dx ::r/bp]}
                      [0x01 0xeb] {::i/tag ::i/add, ::i/args [::r/bx ::r/bp]}
                      [0x01 0xec] {::i/tag ::i/add, ::i/args [::r/sp ::r/bp]}
                      [0x01 0xed] {::i/tag ::i/add, ::i/args [::r/bp ::r/bp]}
                      [0x01 0xee] {::i/tag ::i/add, ::i/args [::r/si ::r/bp]}
                      [0x01 0xef] {::i/tag ::i/add, ::i/args [::r/di ::r/bp]}
                      [0x01 0xf0] {::i/tag ::i/add, ::i/args [::r/ax ::r/si]}
                      [0x01 0xf1] {::i/tag ::i/add, ::i/args [::r/cx ::r/si]}
                      [0x01 0xf2] {::i/tag ::i/add, ::i/args [::r/dx ::r/si]}
                      [0x01 0xf3] {::i/tag ::i/add, ::i/args [::r/bx ::r/si]}
                      [0x01 0xf4] {::i/tag ::i/add, ::i/args [::r/sp ::r/si]}
                      [0x01 0xf5] {::i/tag ::i/add, ::i/args [::r/bp ::r/si]}
                      [0x01 0xf6] {::i/tag ::i/add, ::i/args [::r/si ::r/si]}
                      [0x01 0xf7] {::i/tag ::i/add, ::i/args [::r/di ::r/si]}
                      [0x01 0xf8] {::i/tag ::i/add, ::i/args [::r/ax ::r/di]}
                      [0x01 0xf9] {::i/tag ::i/add, ::i/args [::r/cx ::r/di]}
                      [0x01 0xfa] {::i/tag ::i/add, ::i/args [::r/dx ::r/di]}
                      [0x01 0xfb] {::i/tag ::i/add, ::i/args [::r/bx ::r/di]}
                      [0x01 0xfc] {::i/tag ::i/add, ::i/args [::r/sp ::r/di]}
                      [0x01 0xfd] {::i/tag ::i/add, ::i/args [::r/bp ::r/di]}
                      [0x01 0xfe] {::i/tag ::i/add, ::i/args [::r/si ::r/di]}
                      [0x01 0xff] {::i/tag ::i/add, ::i/args [::r/di ::r/di]}
                      [0x02 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/si]]}
                      [0x02 0x01] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/di]]}
                      [0x02 0x02] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/si]]}
                      [0x02 0x03] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/di]]}
                      [0x02 0x04] {::i/tag ::i/add, ::i/args [::r/al [::r/si]]}
                      [0x02 0x05] {::i/tag ::i/add, ::i/args [::r/al [::r/di]]}
                      [0x02 0x06 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/al [0x0ff0]]}
                      [0x02 0x06 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/al [0xf00f]]}
                      [0x02 0x07] {::i/tag ::i/add, ::i/args [::r/al [::r/bx]]}
                      [0x02 0x08] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/si]]}
                      [0x02 0x09] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/di]]}
                      [0x02 0x0a] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/si]]}
                      [0x02 0x0b] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/di]]}
                      [0x02 0x0c] {::i/tag ::i/add, ::i/args [::r/cl [::r/si]]}
                      [0x02 0x0d] {::i/tag ::i/add, ::i/args [::r/cl [::r/di]]}
                      [0x02 0x0e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [0x0ff0]]}
                      [0x02 0x0e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [0xf00f]]}
                      [0x02 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx]]}
                      [0x02 0x10] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/si]]}
                      [0x02 0x11] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/di]]}
                      [0x02 0x12] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/si]]}
                      [0x02 0x13] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/di]]}
                      [0x02 0x14] {::i/tag ::i/add, ::i/args [::r/dl [::r/si]]}
                      [0x02 0x15] {::i/tag ::i/add, ::i/args [::r/dl [::r/di]]}
                      [0x02 0x16 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [0x0ff0]]}
                      [0x02 0x16 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [0xf00f]]}
                      [0x02 0x17] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx]]}
                      [0x02 0x18] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/si]]}
                      [0x02 0x19] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/di]]}
                      [0x02 0x1a] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/si]]}
                      [0x02 0x1b] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/di]]}
                      [0x02 0x1c] {::i/tag ::i/add, ::i/args [::r/bl [::r/si]]}
                      [0x02 0x1d] {::i/tag ::i/add, ::i/args [::r/bl [::r/di]]}
                      [0x02 0x1e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [0x0ff0]]}
                      [0x02 0x1e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [0xf00f]]}
                      [0x02 0x1f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx]]}
                      [0x02 0x20] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/si]]}
                      [0x02 0x21] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/di]]}
                      [0x02 0x22] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/si]]}
                      [0x02 0x23] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/di]]}
                      [0x02 0x24] {::i/tag ::i/add, ::i/args [::r/ah [::r/si]]}
                      [0x02 0x25] {::i/tag ::i/add, ::i/args [::r/ah [::r/di]]}
                      [0x02 0x26 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [0x0ff0]]}
                      [0x02 0x26 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [0xf00f]]}
                      [0x02 0x27] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx]]}
                      [0x02 0x28] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/si]]}
                      [0x02 0x29] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/di]]}
                      [0x02 0x2a] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/si]]}
                      [0x02 0x2b] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/di]]}
                      [0x02 0x2c] {::i/tag ::i/add, ::i/args [::r/ch [::r/si]]}
                      [0x02 0x2d] {::i/tag ::i/add, ::i/args [::r/ch [::r/di]]}
                      [0x02 0x2e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [0x0ff0]]}
                      [0x02 0x2e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [0xf00f]]}
                      [0x02 0x2f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx]]}
                      [0x02 0x30] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/si]]}
                      [0x02 0x31] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/di]]}
                      [0x02 0x32] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/si]]}
                      [0x02 0x33] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/di]]}
                      [0x02 0x34] {::i/tag ::i/add, ::i/args [::r/dh [::r/si]]}
                      [0x02 0x35] {::i/tag ::i/add, ::i/args [::r/dh [::r/di]]}
                      [0x02 0x36 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [0x0ff0]]}
                      [0x02 0x36 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [0xf00f]]}
                      [0x02 0x37] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx]]}
                      [0x02 0x38] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/si]]}
                      [0x02 0x39] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/di]]}
                      [0x02 0x3a] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/si]]}
                      [0x02 0x3b] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/di]]}
                      [0x02 0x3c] {::i/tag ::i/add, ::i/args [::r/bh [::r/si]]}
                      [0x02 0x3d] {::i/tag ::i/add, ::i/args [::r/bh [::r/di]]}
                      [0x02 0x3e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [0x0ff0]]}
                      [0x02 0x3e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [0xf00f]]}
                      [0x02 0x3f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx]]}
                      [0x02 0x40 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/si]]}
                      [0x02 0x40 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/si -16]]}
                      [0x02 0x40 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/si 15]]}
                      [0x02 0x41 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/di]]}
                      [0x02 0x41 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/di -16]]}
                      [0x02 0x41 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/di 15]]}
                      [0x02 0x42 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/si]]}
                      [0x02 0x42 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/si -16]]}
                      [0x02 0x42 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/si 15]]}
                      [0x02 0x43 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/di]]}
                      [0x02 0x43 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/di -16]]}
                      [0x02 0x43 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/di 15]]}
                      [0x02 0x44 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/si]]}
                      [0x02 0x44 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/si -16]]}
                      [0x02 0x44 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/si 15]]}
                      [0x02 0x45 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/di]]}
                      [0x02 0x45 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/di -16]]}
                      [0x02 0x45 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/di 15]]}
                      [0x02 0x46 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bp]]}
                      [0x02 0x46 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bp -16]]}
                      [0x02 0x46 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bp 15]]}
                      [0x02 0x47 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bx]]}
                      [0x02 0x47 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bx -16]]}
                      [0x02 0x47 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bx 15]]}
                      [0x02 0x48 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/si]]}
                      [0x02 0x48 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/si -16]]}
                      [0x02 0x48 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/si 15]]}
                      [0x02 0x49 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/di]]}
                      [0x02 0x49 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/di -16]]}
                      [0x02 0x49 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/di 15]]}
                      [0x02 0x4a 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/si]]}
                      [0x02 0x4a 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/si -16]]}
                      [0x02 0x4a 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/si 15]]}
                      [0x02 0x4b 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/di]]}
                      [0x02 0x4b 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/di -16]]}
                      [0x02 0x4b 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/di 15]]}
                      [0x02 0x4c 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/si]]}
                      [0x02 0x4c 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/si -16]]}
                      [0x02 0x4c 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/si 15]]}
                      [0x02 0x4d 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/di]]}
                      [0x02 0x4d 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/di -16]]}
                      [0x02 0x4d 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/di 15]]}
                      [0x02 0x4e 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp]]}
                      [0x02 0x4e 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp -16]]}
                      [0x02 0x4e 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp 15]]}
                      [0x02 0x4f 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx]]}
                      [0x02 0x4f 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx -16]]}
                      [0x02 0x4f 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx 15]]}
                      [0x02 0x50 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/si]]}
                      [0x02 0x50 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/si -16]]}
                      [0x02 0x50 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/si 15]]}
                      [0x02 0x51 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/di]]}
                      [0x02 0x51 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/di -16]]}
                      [0x02 0x51 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/di 15]]}
                      [0x02 0x52 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/si]]}
                      [0x02 0x52 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/si -16]]}
                      [0x02 0x52 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/si 15]]}
                      [0x02 0x53 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/di]]}
                      [0x02 0x53 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/di -16]]}
                      [0x02 0x53 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/di 15]]}
                      [0x02 0x54 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/si]]}
                      [0x02 0x54 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/si -16]]}
                      [0x02 0x54 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/si 15]]}
                      [0x02 0x55 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/di]]}
                      [0x02 0x55 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/di -16]]}
                      [0x02 0x55 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/di 15]]}
                      [0x02 0x56 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp]]}
                      [0x02 0x56 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp -16]]}
                      [0x02 0x56 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp 15]]}
                      [0x02 0x57 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx]]}
                      [0x02 0x57 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx -16]]}
                      [0x02 0x57 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx 15]]}
                      [0x02 0x58 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/si]]}
                      [0x02 0x58 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/si -16]]}
                      [0x02 0x58 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/si 15]]}
                      [0x02 0x59 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/di]]}
                      [0x02 0x59 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/di -16]]}
                      [0x02 0x59 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/di 15]]}
                      [0x02 0x5a 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/si]]}
                      [0x02 0x5a 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/si -16]]}
                      [0x02 0x5a 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/si 15]]}
                      [0x02 0x5b 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/di]]}
                      [0x02 0x5b 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/di -16]]}
                      [0x02 0x5b 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/di 15]]}
                      [0x02 0x5c 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/si]]}
                      [0x02 0x5c 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/si -16]]}
                      [0x02 0x5c 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/si 15]]}
                      [0x02 0x5d 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/di]]}
                      [0x02 0x5d 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/di -16]]}
                      [0x02 0x5d 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/di 15]]}
                      [0x02 0x5e 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp]]}
                      [0x02 0x5e 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp -16]]}
                      [0x02 0x5e 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp 15]]}
                      [0x02 0x5f 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx]]}
                      [0x02 0x5f 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx -16]]}
                      [0x02 0x5f 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx 15]]}
                      [0x02 0x60 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/si]]}
                      [0x02 0x60 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/si -16]]}
                      [0x02 0x60 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/si 15]]}
                      [0x02 0x61 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/di]]}
                      [0x02 0x61 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/di -16]]}
                      [0x02 0x61 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/di 15]]}
                      [0x02 0x62 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/si]]}
                      [0x02 0x62 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/si -16]]}
                      [0x02 0x62 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/si 15]]}
                      [0x02 0x63 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/di]]}
                      [0x02 0x63 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/di -16]]}
                      [0x02 0x63 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/di 15]]}
                      [0x02 0x64 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/si]]}
                      [0x02 0x64 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/si -16]]}
                      [0x02 0x64 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/si 15]]}
                      [0x02 0x65 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/di]]}
                      [0x02 0x65 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/di -16]]}
                      [0x02 0x65 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/di 15]]}
                      [0x02 0x66 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp]]}
                      [0x02 0x66 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp -16]]}
                      [0x02 0x66 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp 15]]}
                      [0x02 0x67 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx]]}
                      [0x02 0x67 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx -16]]}
                      [0x02 0x67 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx 15]]}
                      [0x02 0x68 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/si]]}
                      [0x02 0x68 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/si -16]]}
                      [0x02 0x68 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/si 15]]}
                      [0x02 0x69 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/di]]}
                      [0x02 0x69 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/di -16]]}
                      [0x02 0x69 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/di 15]]}
                      [0x02 0x6a 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/si]]}
                      [0x02 0x6a 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/si -16]]}
                      [0x02 0x6a 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/si 15]]}
                      [0x02 0x6b 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/di]]}
                      [0x02 0x6b 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/di -16]]}
                      [0x02 0x6b 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/di 15]]}
                      [0x02 0x6c 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/si]]}
                      [0x02 0x6c 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/si -16]]}
                      [0x02 0x6c 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/si 15]]}
                      [0x02 0x6d 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/di]]}
                      [0x02 0x6d 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/di -16]]}
                      [0x02 0x6d 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/di 15]]}
                      [0x02 0x6e 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp]]}
                      [0x02 0x6e 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp -16]]}
                      [0x02 0x6e 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp 15]]}
                      [0x02 0x6f 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx]]}
                      [0x02 0x6f 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx -16]]}
                      [0x02 0x6f 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx 15]]}
                      [0x02 0x70 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/si]]}
                      [0x02 0x70 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/si -16]]}
                      [0x02 0x70 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/si 15]]}
                      [0x02 0x71 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/di]]}
                      [0x02 0x71 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/di -16]]}
                      [0x02 0x71 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/di 15]]}
                      [0x02 0x72 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/si]]}
                      [0x02 0x72 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/si -16]]}
                      [0x02 0x72 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/si 15]]}
                      [0x02 0x73 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/di]]}
                      [0x02 0x73 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/di -16]]}
                      [0x02 0x73 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/di 15]]}
                      [0x02 0x74 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/si]]}
                      [0x02 0x74 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/si -16]]}
                      [0x02 0x74 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/si 15]]}
                      [0x02 0x75 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/di]]}
                      [0x02 0x75 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/di -16]]}
                      [0x02 0x75 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/di 15]]}
                      [0x02 0x76 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp]]}
                      [0x02 0x76 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp -16]]}
                      [0x02 0x76 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp 15]]}
                      [0x02 0x77 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx]]}
                      [0x02 0x77 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx -16]]}
                      [0x02 0x77 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx 15]]}
                      [0x02 0x78 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/si]]}
                      [0x02 0x78 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/si -16]]}
                      [0x02 0x78 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/si 15]]}
                      [0x02 0x79 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/di]]}
                      [0x02 0x79 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/di -16]]}
                      [0x02 0x79 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/di 15]]}
                      [0x02 0x7a 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/si]]}
                      [0x02 0x7a 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/si -16]]}
                      [0x02 0x7a 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/si 15]]}
                      [0x02 0x7b 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/di]]}
                      [0x02 0x7b 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/di -16]]}
                      [0x02 0x7b 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/di 15]]}
                      [0x02 0x7c 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/si]]}
                      [0x02 0x7c 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/si -16]]}
                      [0x02 0x7c 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/si 15]]}
                      [0x02 0x7d 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/di]]}
                      [0x02 0x7d 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/di -16]]}
                      [0x02 0x7d 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/di 15]]}
                      [0x02 0x7e 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp]]}
                      [0x02 0x7e 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp -16]]}
                      [0x02 0x7e 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp 15]]}
                      [0x02 0x7f 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx]]}
                      [0x02 0x7f 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx -16]]}
                      [0x02 0x7f 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx 15]]}
                      [0x02 0x80 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/si]]}
                      [0x02 0x80 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/si 4080]]}
                      [0x02 0x80 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/si -4081]]}
                      [0x02 0x81 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/di]]}
                      [0x02 0x81 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/di 4080]]}
                      [0x02 0x81 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bx ::r/di -4081]]}
                      [0x02 0x82 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/si]]}
                      [0x02 0x82 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/si 4080]]}
                      [0x02 0x82 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/si -4081]]}
                      [0x02 0x83 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/di]]}
                      [0x02 0x83 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/di 4080]]}
                      [0x02 0x83 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bp ::r/di -4081]]}
                      [0x02 0x84 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/si]]}
                      [0x02 0x84 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/si 4080]]}
                      [0x02 0x84 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/si -4081]]}
                      [0x02 0x85 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/di]]}
                      [0x02 0x85 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/di 4080]]}
                      [0x02 0x85 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/di -4081]]}
                      [0x02 0x86 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bp]]}
                      [0x02 0x86 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bp 4080]]}
                      [0x02 0x86 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bp -4081]]}
                      [0x02 0x87 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/al [::r/bx]]}
                      [0x02 0x87 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/al [::r/bx 4080]]}
                      [0x02 0x87 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/al [::r/bx -4081]]}
                      [0x02 0x88 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/si]]}
                      [0x02 0x88 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/si 4080]]}
                      [0x02 0x88 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/si -4081]]}
                      [0x02 0x89 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/di]]}
                      [0x02 0x89 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/di 4080]]}
                      [0x02 0x89 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx ::r/di -4081]]}
                      [0x02 0x8a 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/si]]}
                      [0x02 0x8a 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/si 4080]]}
                      [0x02 0x8a 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/si -4081]]}
                      [0x02 0x8b 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/di]]}
                      [0x02 0x8b 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/di 4080]]}
                      [0x02 0x8b 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp ::r/di -4081]]}
                      [0x02 0x8c 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/si]]}
                      [0x02 0x8c 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/si 4080]]}
                      [0x02 0x8c 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/si -4081]]}
                      [0x02 0x8d 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/di]]}
                      [0x02 0x8d 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/di 4080]]}
                      [0x02 0x8d 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/di -4081]]}
                      [0x02 0x8e 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp]]}
                      [0x02 0x8e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp 4080]]}
                      [0x02 0x8e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bp -4081]]}
                      [0x02 0x8f 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx]]}
                      [0x02 0x8f 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx 4080]]}
                      [0x02 0x8f 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/cl [::r/bx -4081]]}
                      [0x02 0x90 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/si]]}
                      [0x02 0x90 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/si 4080]]}
                      [0x02 0x90 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/si -4081]]}
                      [0x02 0x91 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/di]]}
                      [0x02 0x91 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/di 4080]]}
                      [0x02 0x91 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx ::r/di -4081]]}
                      [0x02 0x92 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/si]]}
                      [0x02 0x92 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/si 4080]]}
                      [0x02 0x92 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/si -4081]]}
                      [0x02 0x93 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/di]]}
                      [0x02 0x93 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/di 4080]]}
                      [0x02 0x93 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp ::r/di -4081]]}
                      [0x02 0x94 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/si]]}
                      [0x02 0x94 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/si 4080]]}
                      [0x02 0x94 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/si -4081]]}
                      [0x02 0x95 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/di]]}
                      [0x02 0x95 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/di 4080]]}
                      [0x02 0x95 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/di -4081]]}
                      [0x02 0x96 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp]]}
                      [0x02 0x96 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp 4080]]}
                      [0x02 0x96 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bp -4081]]}
                      [0x02 0x97 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx]]}
                      [0x02 0x97 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx 4080]]}
                      [0x02 0x97 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dl [::r/bx -4081]]}
                      [0x02 0x98 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/si]]}
                      [0x02 0x98 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/si 4080]]}
                      [0x02 0x98 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/si -4081]]}
                      [0x02 0x99 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/di]]}
                      [0x02 0x99 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/di 4080]]}
                      [0x02 0x99 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx ::r/di -4081]]}
                      [0x02 0x9a 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/si]]}
                      [0x02 0x9a 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/si 4080]]}
                      [0x02 0x9a 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/si -4081]]}
                      [0x02 0x9b 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/di]]}
                      [0x02 0x9b 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/di 4080]]}
                      [0x02 0x9b 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp ::r/di -4081]]}
                      [0x02 0x9c 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/si]]}
                      [0x02 0x9c 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/si 4080]]}
                      [0x02 0x9c 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/si -4081]]}
                      [0x02 0x9d 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/di]]}
                      [0x02 0x9d 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/di 4080]]}
                      [0x02 0x9d 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/di -4081]]}
                      [0x02 0x9e 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp]]}
                      [0x02 0x9e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp 4080]]}
                      [0x02 0x9e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bp -4081]]}
                      [0x02 0x9f 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx]]}
                      [0x02 0x9f 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx 4080]]}
                      [0x02 0x9f 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bl [::r/bx -4081]]}
                      [0x02 0xa0 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/si]]}
                      [0x02 0xa0 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/si 4080]]}
                      [0x02 0xa0 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/si -4081]]}
                      [0x02 0xa1 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/di]]}
                      [0x02 0xa1 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/di 4080]]}
                      [0x02 0xa1 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx ::r/di -4081]]}
                      [0x02 0xa2 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/si]]}
                      [0x02 0xa2 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/si 4080]]}
                      [0x02 0xa2 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/si -4081]]}
                      [0x02 0xa3 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/di]]}
                      [0x02 0xa3 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/di 4080]]}
                      [0x02 0xa3 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp ::r/di -4081]]}
                      [0x02 0xa4 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/si]]}
                      [0x02 0xa4 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/si 4080]]}
                      [0x02 0xa4 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/si -4081]]}
                      [0x02 0xa5 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/di]]}
                      [0x02 0xa5 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/di 4080]]}
                      [0x02 0xa5 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/di -4081]]}
                      [0x02 0xa6 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp]]}
                      [0x02 0xa6 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp 4080]]}
                      [0x02 0xa6 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bp -4081]]}
                      [0x02 0xa7 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx]]}
                      [0x02 0xa7 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx 4080]]}
                      [0x02 0xa7 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ah [::r/bx -4081]]}
                      [0x02 0xa8 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/si]]}
                      [0x02 0xa8 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/si 4080]]}
                      [0x02 0xa8 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/si -4081]]}
                      [0x02 0xa9 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/di]]}
                      [0x02 0xa9 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/di 4080]]}
                      [0x02 0xa9 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx ::r/di -4081]]}
                      [0x02 0xaa 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/si]]}
                      [0x02 0xaa 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/si 4080]]}
                      [0x02 0xaa 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/si -4081]]}
                      [0x02 0xab 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/di]]}
                      [0x02 0xab 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/di 4080]]}
                      [0x02 0xab 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp ::r/di -4081]]}
                      [0x02 0xac 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/si]]}
                      [0x02 0xac 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/si 4080]]}
                      [0x02 0xac 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/si -4081]]}
                      [0x02 0xad 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/di]]}
                      [0x02 0xad 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/di 4080]]}
                      [0x02 0xad 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/di -4081]]}
                      [0x02 0xae 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp]]}
                      [0x02 0xae 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp 4080]]}
                      [0x02 0xae 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bp -4081]]}
                      [0x02 0xaf 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx]]}
                      [0x02 0xaf 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx 4080]]}
                      [0x02 0xaf 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/ch [::r/bx -4081]]}
                      [0x02 0xb0 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/si]]}
                      [0x02 0xb0 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/si 4080]]}
                      [0x02 0xb0 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/si -4081]]}
                      [0x02 0xb1 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/di]]}
                      [0x02 0xb1 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/di 4080]]}
                      [0x02 0xb1 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx ::r/di -4081]]}
                      [0x02 0xb2 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/si]]}
                      [0x02 0xb2 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/si 4080]]}
                      [0x02 0xb2 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/si -4081]]}
                      [0x02 0xb3 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/di]]}
                      [0x02 0xb3 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/di 4080]]}
                      [0x02 0xb3 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp ::r/di -4081]]}
                      [0x02 0xb4 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/si]]}
                      [0x02 0xb4 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/si 4080]]}
                      [0x02 0xb4 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/si -4081]]}
                      [0x02 0xb5 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/di]]}
                      [0x02 0xb5 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/di 4080]]}
                      [0x02 0xb5 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/di -4081]]}
                      [0x02 0xb6 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp]]}
                      [0x02 0xb6 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp 4080]]}
                      [0x02 0xb6 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bp -4081]]}
                      [0x02 0xb7 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx]]}
                      [0x02 0xb7 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx 4080]]}
                      [0x02 0xb7 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/dh [::r/bx -4081]]}
                      [0x02 0xb8 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/si]]}
                      [0x02 0xb8 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/si 4080]]}
                      [0x02 0xb8 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/si -4081]]}
                      [0x02 0xb9 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/di]]}
                      [0x02 0xb9 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/di 4080]]}
                      [0x02 0xb9 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx ::r/di -4081]]}
                      [0x02 0xba 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/si]]}
                      [0x02 0xba 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/si 4080]]}
                      [0x02 0xba 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/si -4081]]}
                      [0x02 0xbb 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/di]]}
                      [0x02 0xbb 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/di 4080]]}
                      [0x02 0xbb 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp ::r/di -4081]]}
                      [0x02 0xbc 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/si]]}
                      [0x02 0xbc 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/si 4080]]}
                      [0x02 0xbc 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/si -4081]]}
                      [0x02 0xbd 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/di]]}
                      [0x02 0xbd 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/di 4080]]}
                      [0x02 0xbd 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/di -4081]]}
                      [0x02 0xbe 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp]]}
                      [0x02 0xbe 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp 4080]]}
                      [0x02 0xbe 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bp -4081]]}
                      [0x02 0xbf 0x00 0x00] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx]]}
                      [0x02 0xbf 0xf0 0x0f] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx 4080]]}
                      [0x02 0xbf 0x0f 0xf0] {::i/tag ::i/add, ::i/args [::r/bh [::r/bx -4081]]}
                      [0x02 0xc0] {::i/tag ::i/add, ::i/args [::r/al ::r/al]}
                      [0x02 0xc1] {::i/tag ::i/add, ::i/args [::r/al ::r/cl]}
                      [0x02 0xc2] {::i/tag ::i/add, ::i/args [::r/al ::r/dl]}
                      [0x02 0xc3] {::i/tag ::i/add, ::i/args [::r/al ::r/bl]}
                      [0x02 0xc4] {::i/tag ::i/add, ::i/args [::r/al ::r/ah]}
                      [0x02 0xc5] {::i/tag ::i/add, ::i/args [::r/al ::r/ch]}
                      [0x02 0xc6] {::i/tag ::i/add, ::i/args [::r/al ::r/dh]}
                      [0x02 0xc7] {::i/tag ::i/add, ::i/args [::r/al ::r/bh]}
                      [0x02 0xc8] {::i/tag ::i/add, ::i/args [::r/cl ::r/al]}
                      [0x02 0xc9] {::i/tag ::i/add, ::i/args [::r/cl ::r/cl]}
                      [0x02 0xca] {::i/tag ::i/add, ::i/args [::r/cl ::r/dl]}
                      [0x02 0xcb] {::i/tag ::i/add, ::i/args [::r/cl ::r/bl]}
                      [0x02 0xcc] {::i/tag ::i/add, ::i/args [::r/cl ::r/ah]}
                      [0x02 0xcd] {::i/tag ::i/add, ::i/args [::r/cl ::r/ch]}
                      [0x02 0xce] {::i/tag ::i/add, ::i/args [::r/cl ::r/dh]}
                      [0x02 0xcf] {::i/tag ::i/add, ::i/args [::r/cl ::r/bh]}
                      [0x02 0xd0] {::i/tag ::i/add, ::i/args [::r/dl ::r/al]}
                      [0x02 0xd1] {::i/tag ::i/add, ::i/args [::r/dl ::r/cl]}
                      [0x02 0xd2] {::i/tag ::i/add, ::i/args [::r/dl ::r/dl]}
                      [0x02 0xd3] {::i/tag ::i/add, ::i/args [::r/dl ::r/bl]}
                      [0x02 0xd4] {::i/tag ::i/add, ::i/args [::r/dl ::r/ah]}
                      [0x02 0xd5] {::i/tag ::i/add, ::i/args [::r/dl ::r/ch]}
                      [0x02 0xd6] {::i/tag ::i/add, ::i/args [::r/dl ::r/dh]}
                      [0x02 0xd7] {::i/tag ::i/add, ::i/args [::r/dl ::r/bh]}
                      [0x02 0xd8] {::i/tag ::i/add, ::i/args [::r/bl ::r/al]}
                      [0x02 0xd9] {::i/tag ::i/add, ::i/args [::r/bl ::r/cl]}
                      [0x02 0xda] {::i/tag ::i/add, ::i/args [::r/bl ::r/dl]}
                      [0x02 0xdb] {::i/tag ::i/add, ::i/args [::r/bl ::r/bl]}
                      [0x02 0xdc] {::i/tag ::i/add, ::i/args [::r/bl ::r/ah]}
                      [0x02 0xdd] {::i/tag ::i/add, ::i/args [::r/bl ::r/ch]}
                      [0x02 0xde] {::i/tag ::i/add, ::i/args [::r/bl ::r/dh]}
                      [0x02 0xdf] {::i/tag ::i/add, ::i/args [::r/bl ::r/bh]}
                      [0x02 0xe0] {::i/tag ::i/add, ::i/args [::r/ah ::r/al]}
                      [0x02 0xe1] {::i/tag ::i/add, ::i/args [::r/ah ::r/cl]}
                      [0x02 0xe2] {::i/tag ::i/add, ::i/args [::r/ah ::r/dl]}
                      [0x02 0xe3] {::i/tag ::i/add, ::i/args [::r/ah ::r/bl]}
                      [0x02 0xe4] {::i/tag ::i/add, ::i/args [::r/ah ::r/ah]}
                      [0x02 0xe5] {::i/tag ::i/add, ::i/args [::r/ah ::r/ch]}
                      [0x02 0xe6] {::i/tag ::i/add, ::i/args [::r/ah ::r/dh]}
                      [0x02 0xe7] {::i/tag ::i/add, ::i/args [::r/ah ::r/bh]}
                      [0x02 0xe8] {::i/tag ::i/add, ::i/args [::r/ch ::r/al]}
                      [0x02 0xe9] {::i/tag ::i/add, ::i/args [::r/ch ::r/cl]}
                      [0x02 0xea] {::i/tag ::i/add, ::i/args [::r/ch ::r/dl]}
                      [0x02 0xeb] {::i/tag ::i/add, ::i/args [::r/ch ::r/bl]}
                      [0x02 0xec] {::i/tag ::i/add, ::i/args [::r/ch ::r/ah]}
                      [0x02 0xed] {::i/tag ::i/add, ::i/args [::r/ch ::r/ch]}
                      [0x02 0xee] {::i/tag ::i/add, ::i/args [::r/ch ::r/dh]}
                      [0x02 0xef] {::i/tag ::i/add, ::i/args [::r/ch ::r/bh]}
                      [0x02 0xf0] {::i/tag ::i/add, ::i/args [::r/dh ::r/al]}
                      [0x02 0xf1] {::i/tag ::i/add, ::i/args [::r/dh ::r/cl]}
                      [0x02 0xf2] {::i/tag ::i/add, ::i/args [::r/dh ::r/dl]}
                      [0x02 0xf3] {::i/tag ::i/add, ::i/args [::r/dh ::r/bl]}
                      [0x02 0xf4] {::i/tag ::i/add, ::i/args [::r/dh ::r/ah]}
                      [0x02 0xf5] {::i/tag ::i/add, ::i/args [::r/dh ::r/ch]}
                      [0x02 0xf6] {::i/tag ::i/add, ::i/args [::r/dh ::r/dh]}
                      [0x02 0xf7] {::i/tag ::i/add, ::i/args [::r/dh ::r/bh]}
                      [0x02 0xf8] {::i/tag ::i/add, ::i/args [::r/bh ::r/al]}
                      [0x02 0xf9] {::i/tag ::i/add, ::i/args [::r/bh ::r/cl]}
                      [0x02 0xfa] {::i/tag ::i/add, ::i/args [::r/bh ::r/dl]}
                      [0x02 0xfb] {::i/tag ::i/add, ::i/args [::r/bh ::r/bl]}
                      [0x02 0xfc] {::i/tag ::i/add, ::i/args [::r/bh ::r/ah]}
                      [0x02 0xfd] {::i/tag ::i/add, ::i/args [::r/bh ::r/ch]}
                      [0x02 0xfe] {::i/tag ::i/add, ::i/args [::r/bh ::r/dh]}
                      [0x02 0xff] {::i/tag ::i/add, ::i/args [::r/bh ::r/bh]}

                      [0x04 0xf0] {::i/tag ::i/add, ::i/args [::r/al 0xf0]}
                      [0x04 0x0f] {::i/tag ::i/add, ::i/args [::r/al 0x0f]}
                      [0x05 0xf0 0x0f] {::i/tag ::i/add ::i/args [::r/ax 0x0ff0]}
                      [0x05 0x0f 0xf0] {::i/tag ::i/add ::i/args [::r/ax 0xf00f]}
                      [0x06] {::i/tag ::i/push, ::i/args [::r/es]}
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


(defn find-gaps []
  (let [used (set (map first (keys decode-examples)))]
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
  (doseq [br (seq decode-examples)]
    (is (= (br 1)
           (decode (br 0)))
        (mapv #(format "%02x" %) (br 0)))))
