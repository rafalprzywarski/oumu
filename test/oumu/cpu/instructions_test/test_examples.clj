(ns oumu.cpu.instructions-test.test-examples
    (:require [oumu.cpu.instructions :as i]
              [oumu.cpu.registers :as r]))


(def decode-test-examples
  {[0x84 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/al]}
   [0x84 0x01] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/al]}
   [0x84 0x02] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/al]}
   [0x84 0x03] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/al]}
   [0x84 0x04] {::i/tag ::i/test, ::i/args [[::r/si] ::r/al]}
   [0x84 0x05] {::i/tag ::i/test, ::i/args [[::r/di] ::r/al]}
   [0x84 0x06 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/al]}
   [0x84 0x06 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/al]}
   [0x84 0x07] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/al]}
   [0x84 0x08] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0x84 0x09] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0x84 0x0a] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0x84 0x0b] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0x84 0x0c] {::i/tag ::i/test, ::i/args [[::r/si] ::r/cl]}
   [0x84 0x0d] {::i/tag ::i/test, ::i/args [[::r/di] ::r/cl]}
   [0x84 0x0e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/cl]}
   [0x84 0x0e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/cl]}
   [0x84 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/cl]}
   [0x84 0x10] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/dl]}
   [0x84 0x11] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/dl]}
   [0x84 0x12] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/dl]}
   [0x84 0x13] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/dl]}
   [0x84 0x14] {::i/tag ::i/test, ::i/args [[::r/si] ::r/dl]}
   [0x84 0x15] {::i/tag ::i/test, ::i/args [[::r/di] ::r/dl]}
   [0x84 0x16 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/dl]}
   [0x84 0x16 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/dl]}
   [0x84 0x17] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/dl]}
   [0x84 0x18] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bl]}
   [0x84 0x19] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bl]}
   [0x84 0x1a] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bl]}
   [0x84 0x1b] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bl]}
   [0x84 0x1c] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bl]}
   [0x84 0x1d] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bl]}
   [0x84 0x1e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/bl]}
   [0x84 0x1e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/bl]}
   [0x84 0x1f] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bl]}
   [0x84 0x20] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/ah]}
   [0x84 0x21] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/ah]}
   [0x84 0x22] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/ah]}
   [0x84 0x23] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/ah]}
   [0x84 0x24] {::i/tag ::i/test, ::i/args [[::r/si] ::r/ah]}
   [0x84 0x25] {::i/tag ::i/test, ::i/args [[::r/di] ::r/ah]}
   [0x84 0x26 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/ah]}
   [0x84 0x26 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/ah]}
   [0x84 0x27] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/ah]}
   [0x84 0x28] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/ch]}
   [0x84 0x29] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/ch]}
   [0x84 0x2a] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/ch]}
   [0x84 0x2b] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/ch]}
   [0x84 0x2c] {::i/tag ::i/test, ::i/args [[::r/si] ::r/ch]}
   [0x84 0x2d] {::i/tag ::i/test, ::i/args [[::r/di] ::r/ch]}
   [0x84 0x2e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/ch]}
   [0x84 0x2e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/ch]}
   [0x84 0x2f] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/ch]}
   [0x84 0x30] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/dh]}
   [0x84 0x31] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/dh]}
   [0x84 0x32] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/dh]}
   [0x84 0x33] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/dh]}
   [0x84 0x34] {::i/tag ::i/test, ::i/args [[::r/si] ::r/dh]}
   [0x84 0x35] {::i/tag ::i/test, ::i/args [[::r/di] ::r/dh]}
   [0x84 0x36 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/dh]}
   [0x84 0x36 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/dh]}
   [0x84 0x37] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/dh]}
   [0x84 0x38] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bh]}
   [0x84 0x39] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bh]}
   [0x84 0x3a] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bh]}
   [0x84 0x3b] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bh]}
   [0x84 0x3c] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bh]}
   [0x84 0x3d] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bh]}
   [0x84 0x3e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/bh]}
   [0x84 0x3e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/bh]}
   [0x84 0x3f] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bh]}
   [0x84 0x40 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/al]}
   [0x84 0x40 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/al]}
   [0x84 0x40 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/al]}
   [0x84 0x41 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/al]}
   [0x84 0x41 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/al]}
   [0x84 0x41 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/al]}
   [0x84 0x42 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/al]}
   [0x84 0x42 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/al]}
   [0x84 0x42 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/al]}
   [0x84 0x43 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/al]}
   [0x84 0x43 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/al]}
   [0x84 0x43 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/al]}
   [0x84 0x44 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/al]}
   [0x84 0x44 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/al]}
   [0x84 0x44 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/al]}
   [0x84 0x45 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/al]}
   [0x84 0x45 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/al]}
   [0x84 0x45 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/al]}
   [0x84 0x46 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/al]}
   [0x84 0x46 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/al]}
   [0x84 0x46 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/al]}
   [0x84 0x47 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/al]}
   [0x84 0x47 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/al]}
   [0x84 0x47 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/al]}
   [0x84 0x48 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0x84 0x48 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0x84 0x48 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0x84 0x49 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0x84 0x49 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0x84 0x49 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0x84 0x4a 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0x84 0x4a 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0x84 0x4a 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0x84 0x4b 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0x84 0x4b 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0x84 0x4b 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0x84 0x4c 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/cl]}
   [0x84 0x4c 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/cl]}
   [0x84 0x4c 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/cl]}
   [0x84 0x4d 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/cl]}
   [0x84 0x4d 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/cl]}
   [0x84 0x4d 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/cl]}
   [0x84 0x4e 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/cl]}
   [0x84 0x4e 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/cl]}
   [0x84 0x4e 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/cl]}
   [0x84 0x4f 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/cl]}
   [0x84 0x4f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/cl]}
   [0x84 0x4f 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/cl]}
   [0x84 0x50 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/dl]}
   [0x84 0x50 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/dl]}
   [0x84 0x50 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/dl]}
   [0x84 0x51 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/dl]}
   [0x84 0x51 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/dl]}
   [0x84 0x51 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/dl]}
   [0x84 0x52 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/dl]}
   [0x84 0x52 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/dl]}
   [0x84 0x52 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/dl]}
   [0x84 0x53 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/dl]}
   [0x84 0x53 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/dl]}
   [0x84 0x53 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/dl]}
   [0x84 0x54 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/dl]}
   [0x84 0x54 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/dl]}
   [0x84 0x54 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/dl]}
   [0x84 0x55 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/dl]}
   [0x84 0x55 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/dl]}
   [0x84 0x55 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/dl]}
   [0x84 0x56 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/dl]}
   [0x84 0x56 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/dl]}
   [0x84 0x56 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/dl]}
   [0x84 0x57 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/dl]}
   [0x84 0x57 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/dl]}
   [0x84 0x57 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/dl]}
   [0x84 0x58 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bl]}
   [0x84 0x58 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/bl]}
   [0x84 0x58 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/bl]}
   [0x84 0x59 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bl]}
   [0x84 0x59 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/bl]}
   [0x84 0x59 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/bl]}
   [0x84 0x5a 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bl]}
   [0x84 0x5a 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/bl]}
   [0x84 0x5a 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/bl]}
   [0x84 0x5b 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bl]}
   [0x84 0x5b 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/bl]}
   [0x84 0x5b 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/bl]}
   [0x84 0x5c 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bl]}
   [0x84 0x5c 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/bl]}
   [0x84 0x5c 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/bl]}
   [0x84 0x5d 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bl]}
   [0x84 0x5d 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/bl]}
   [0x84 0x5d 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/bl]}
   [0x84 0x5e 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/bl]}
   [0x84 0x5e 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/bl]}
   [0x84 0x5e 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/bl]}
   [0x84 0x5f 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bl]}
   [0x84 0x5f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/bl]}
   [0x84 0x5f 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/bl]}
   [0x84 0x60 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/ah]}
   [0x84 0x60 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/ah]}
   [0x84 0x60 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/ah]}
   [0x84 0x61 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/ah]}
   [0x84 0x61 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/ah]}
   [0x84 0x61 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/ah]}
   [0x84 0x62 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/ah]}
   [0x84 0x62 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/ah]}
   [0x84 0x62 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/ah]}
   [0x84 0x63 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/ah]}
   [0x84 0x63 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/ah]}
   [0x84 0x63 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/ah]}
   [0x84 0x64 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/ah]}
   [0x84 0x64 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/ah]}
   [0x84 0x64 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/ah]}
   [0x84 0x65 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/ah]}
   [0x84 0x65 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/ah]}
   [0x84 0x65 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/ah]}
   [0x84 0x66 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/ah]}
   [0x84 0x66 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/ah]}
   [0x84 0x66 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/ah]}
   [0x84 0x67 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/ah]}
   [0x84 0x67 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/ah]}
   [0x84 0x67 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/ah]}
   [0x84 0x68 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/ch]}
   [0x84 0x68 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/ch]}
   [0x84 0x68 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/ch]}
   [0x84 0x69 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/ch]}
   [0x84 0x69 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/ch]}
   [0x84 0x69 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/ch]}
   [0x84 0x6a 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/ch]}
   [0x84 0x6a 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/ch]}
   [0x84 0x6a 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/ch]}
   [0x84 0x6b 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/ch]}
   [0x84 0x6b 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/ch]}
   [0x84 0x6b 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/ch]}
   [0x84 0x6c 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/ch]}
   [0x84 0x6c 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/ch]}
   [0x84 0x6c 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/ch]}
   [0x84 0x6d 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/ch]}
   [0x84 0x6d 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/ch]}
   [0x84 0x6d 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/ch]}
   [0x84 0x6e 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/ch]}
   [0x84 0x6e 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/ch]}
   [0x84 0x6e 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/ch]}
   [0x84 0x6f 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/ch]}
   [0x84 0x6f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/ch]}
   [0x84 0x6f 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/ch]}
   [0x84 0x70 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/dh]}
   [0x84 0x70 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/dh]}
   [0x84 0x70 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/dh]}
   [0x84 0x71 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/dh]}
   [0x84 0x71 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/dh]}
   [0x84 0x71 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/dh]}
   [0x84 0x72 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/dh]}
   [0x84 0x72 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/dh]}
   [0x84 0x72 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/dh]}
   [0x84 0x73 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/dh]}
   [0x84 0x73 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/dh]}
   [0x84 0x73 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/dh]}
   [0x84 0x74 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/dh]}
   [0x84 0x74 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/dh]}
   [0x84 0x74 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/dh]}
   [0x84 0x75 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/dh]}
   [0x84 0x75 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/dh]}
   [0x84 0x75 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/dh]}
   [0x84 0x76 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/dh]}
   [0x84 0x76 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/dh]}
   [0x84 0x76 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/dh]}
   [0x84 0x77 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/dh]}
   [0x84 0x77 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/dh]}
   [0x84 0x77 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/dh]}
   [0x84 0x78 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bh]}
   [0x84 0x78 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/bh]}
   [0x84 0x78 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/bh]}
   [0x84 0x79 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bh]}
   [0x84 0x79 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/bh]}
   [0x84 0x79 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/bh]}
   [0x84 0x7a 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bh]}
   [0x84 0x7a 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/bh]}
   [0x84 0x7a 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/bh]}
   [0x84 0x7b 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bh]}
   [0x84 0x7b 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/bh]}
   [0x84 0x7b 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/bh]}
   [0x84 0x7c 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bh]}
   [0x84 0x7c 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/bh]}
   [0x84 0x7c 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/bh]}
   [0x84 0x7d 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bh]}
   [0x84 0x7d 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/bh]}
   [0x84 0x7d 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/bh]}
   [0x84 0x7e 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/bh]}
   [0x84 0x7e 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/bh]}
   [0x84 0x7e 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/bh]}
   [0x84 0x7f 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bh]}
   [0x84 0x7f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/bh]}
   [0x84 0x7f 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/bh]}
   [0x84 0x80 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/al]}
   [0x84 0x80 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/al]}
   [0x84 0x80 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/al]}
   [0x84 0x81 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/al]}
   [0x84 0x81 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/al]}
   [0x84 0x81 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/al]}
   [0x84 0x82 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/al]}
   [0x84 0x82 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/al]}
   [0x84 0x82 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/al]}
   [0x84 0x83 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/al]}
   [0x84 0x83 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/al]}
   [0x84 0x83 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/al]}
   [0x84 0x84 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/al]}
   [0x84 0x84 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/al]}
   [0x84 0x84 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/al]}
   [0x84 0x85 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/al]}
   [0x84 0x85 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/al]}
   [0x84 0x85 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/al]}
   [0x84 0x86 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/al]}
   [0x84 0x86 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/al]}
   [0x84 0x86 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/al]}
   [0x84 0x87 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/al]}
   [0x84 0x87 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/al]}
   [0x84 0x87 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/al]}
   [0x84 0x88 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0x84 0x88 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0x84 0x88 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0x84 0x89 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0x84 0x89 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0x84 0x89 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0x84 0x8a 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0x84 0x8a 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0x84 0x8a 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0x84 0x8b 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0x84 0x8b 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0x84 0x8b 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0x84 0x8c 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/cl]}
   [0x84 0x8c 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/cl]}
   [0x84 0x8c 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/cl]}
   [0x84 0x8d 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/cl]}
   [0x84 0x8d 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/cl]}
   [0x84 0x8d 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/cl]}
   [0x84 0x8e 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/cl]}
   [0x84 0x8e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/cl]}
   [0x84 0x8e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/cl]}
   [0x84 0x8f 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/cl]}
   [0x84 0x8f 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/cl]}
   [0x84 0x8f 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/cl]}
   [0x84 0x90 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/dl]}
   [0x84 0x90 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/dl]}
   [0x84 0x90 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/dl]}
   [0x84 0x91 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/dl]}
   [0x84 0x91 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/dl]}
   [0x84 0x91 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/dl]}
   [0x84 0x92 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/dl]}
   [0x84 0x92 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/dl]}
   [0x84 0x92 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/dl]}
   [0x84 0x93 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/dl]}
   [0x84 0x93 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/dl]}
   [0x84 0x93 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/dl]}
   [0x84 0x94 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/dl]}
   [0x84 0x94 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/dl]}
   [0x84 0x94 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/dl]}
   [0x84 0x95 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/dl]}
   [0x84 0x95 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/dl]}
   [0x84 0x95 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/dl]}
   [0x84 0x96 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/dl]}
   [0x84 0x96 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/dl]}
   [0x84 0x96 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/dl]}
   [0x84 0x97 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/dl]}
   [0x84 0x97 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/dl]}
   [0x84 0x97 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/dl]}
   [0x84 0x98 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bl]}
   [0x84 0x98 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/bl]}
   [0x84 0x98 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/bl]}
   [0x84 0x99 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bl]}
   [0x84 0x99 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/bl]}
   [0x84 0x99 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/bl]}
   [0x84 0x9a 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bl]}
   [0x84 0x9a 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/bl]}
   [0x84 0x9a 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/bl]}
   [0x84 0x9b 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bl]}
   [0x84 0x9b 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/bl]}
   [0x84 0x9b 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/bl]}
   [0x84 0x9c 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bl]}
   [0x84 0x9c 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/bl]}
   [0x84 0x9c 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/bl]}
   [0x84 0x9d 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bl]}
   [0x84 0x9d 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/bl]}
   [0x84 0x9d 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/bl]}
   [0x84 0x9e 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/bl]}
   [0x84 0x9e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/bl]}
   [0x84 0x9e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/bl]}
   [0x84 0x9f 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bl]}
   [0x84 0x9f 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/bl]}
   [0x84 0x9f 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/bl]}
   [0x84 0xa0 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/ah]}
   [0x84 0xa0 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/ah]}
   [0x84 0xa0 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/ah]}
   [0x84 0xa1 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/ah]}
   [0x84 0xa1 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/ah]}
   [0x84 0xa1 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/ah]}
   [0x84 0xa2 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/ah]}
   [0x84 0xa2 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/ah]}
   [0x84 0xa2 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/ah]}
   [0x84 0xa3 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/ah]}
   [0x84 0xa3 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/ah]}
   [0x84 0xa3 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/ah]}
   [0x84 0xa4 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/ah]}
   [0x84 0xa4 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/ah]}
   [0x84 0xa4 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/ah]}
   [0x84 0xa5 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/ah]}
   [0x84 0xa5 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/ah]}
   [0x84 0xa5 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/ah]}
   [0x84 0xa6 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/ah]}
   [0x84 0xa6 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/ah]}
   [0x84 0xa6 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/ah]}
   [0x84 0xa7 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/ah]}
   [0x84 0xa7 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/ah]}
   [0x84 0xa7 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/ah]}
   [0x84 0xa8 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/ch]}
   [0x84 0xa8 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/ch]}
   [0x84 0xa8 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/ch]}
   [0x84 0xa9 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/ch]}
   [0x84 0xa9 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/ch]}
   [0x84 0xa9 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/ch]}
   [0x84 0xaa 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/ch]}
   [0x84 0xaa 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/ch]}
   [0x84 0xaa 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/ch]}
   [0x84 0xab 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/ch]}
   [0x84 0xab 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/ch]}
   [0x84 0xab 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/ch]}
   [0x84 0xac 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/ch]}
   [0x84 0xac 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/ch]}
   [0x84 0xac 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/ch]}
   [0x84 0xad 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/ch]}
   [0x84 0xad 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/ch]}
   [0x84 0xad 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/ch]}
   [0x84 0xae 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/ch]}
   [0x84 0xae 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/ch]}
   [0x84 0xae 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/ch]}
   [0x84 0xaf 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/ch]}
   [0x84 0xaf 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/ch]}
   [0x84 0xaf 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/ch]}
   [0x84 0xb0 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/dh]}
   [0x84 0xb0 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/dh]}
   [0x84 0xb0 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/dh]}
   [0x84 0xb1 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/dh]}
   [0x84 0xb1 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/dh]}
   [0x84 0xb1 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/dh]}
   [0x84 0xb2 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/dh]}
   [0x84 0xb2 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/dh]}
   [0x84 0xb2 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/dh]}
   [0x84 0xb3 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/dh]}
   [0x84 0xb3 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/dh]}
   [0x84 0xb3 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/dh]}
   [0x84 0xb4 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/dh]}
   [0x84 0xb4 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/dh]}
   [0x84 0xb4 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/dh]}
   [0x84 0xb5 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/dh]}
   [0x84 0xb5 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/dh]}
   [0x84 0xb5 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/dh]}
   [0x84 0xb6 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/dh]}
   [0x84 0xb6 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/dh]}
   [0x84 0xb6 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/dh]}
   [0x84 0xb7 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/dh]}
   [0x84 0xb7 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/dh]}
   [0x84 0xb7 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/dh]}
   [0x84 0xb8 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bh]}
   [0x84 0xb8 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/bh]}
   [0x84 0xb8 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/bh]}
   [0x84 0xb9 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bh]}
   [0x84 0xb9 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/bh]}
   [0x84 0xb9 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/bh]}
   [0x84 0xba 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bh]}
   [0x84 0xba 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/bh]}
   [0x84 0xba 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/bh]}
   [0x84 0xbb 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bh]}
   [0x84 0xbb 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/bh]}
   [0x84 0xbb 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/bh]}
   [0x84 0xbc 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bh]}
   [0x84 0xbc 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/bh]}
   [0x84 0xbc 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/bh]}
   [0x84 0xbd 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bh]}
   [0x84 0xbd 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/bh]}
   [0x84 0xbd 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/bh]}
   [0x84 0xbe 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/bh]}
   [0x84 0xbe 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/bh]}
   [0x84 0xbe 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/bh]}
   [0x84 0xbf 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bh]}
   [0x84 0xbf 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/bh]}
   [0x84 0xbf 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/bh]}
   [0x84 0xc0] {::i/tag ::i/test, ::i/args [::r/al ::r/al]}
   [0x84 0xc1] {::i/tag ::i/test, ::i/args [::r/cl ::r/al]}
   [0x84 0xc2] {::i/tag ::i/test, ::i/args [::r/dl ::r/al]}
   [0x84 0xc3] {::i/tag ::i/test, ::i/args [::r/bl ::r/al]}
   [0x84 0xc4] {::i/tag ::i/test, ::i/args [::r/ah ::r/al]}
   [0x84 0xc5] {::i/tag ::i/test, ::i/args [::r/ch ::r/al]}
   [0x84 0xc6] {::i/tag ::i/test, ::i/args [::r/dh ::r/al]}
   [0x84 0xc7] {::i/tag ::i/test, ::i/args [::r/bh ::r/al]}
   [0x84 0xc8] {::i/tag ::i/test, ::i/args [::r/al ::r/cl]}
   [0x84 0xc9] {::i/tag ::i/test, ::i/args [::r/cl ::r/cl]}
   [0x84 0xca] {::i/tag ::i/test, ::i/args [::r/dl ::r/cl]}
   [0x84 0xcb] {::i/tag ::i/test, ::i/args [::r/bl ::r/cl]}
   [0x84 0xcc] {::i/tag ::i/test, ::i/args [::r/ah ::r/cl]}
   [0x84 0xcd] {::i/tag ::i/test, ::i/args [::r/ch ::r/cl]}
   [0x84 0xce] {::i/tag ::i/test, ::i/args [::r/dh ::r/cl]}
   [0x84 0xcf] {::i/tag ::i/test, ::i/args [::r/bh ::r/cl]}
   [0x84 0xd0] {::i/tag ::i/test, ::i/args [::r/al ::r/dl]}
   [0x84 0xd1] {::i/tag ::i/test, ::i/args [::r/cl ::r/dl]}
   [0x84 0xd2] {::i/tag ::i/test, ::i/args [::r/dl ::r/dl]}
   [0x84 0xd3] {::i/tag ::i/test, ::i/args [::r/bl ::r/dl]}
   [0x84 0xd4] {::i/tag ::i/test, ::i/args [::r/ah ::r/dl]}
   [0x84 0xd5] {::i/tag ::i/test, ::i/args [::r/ch ::r/dl]}
   [0x84 0xd6] {::i/tag ::i/test, ::i/args [::r/dh ::r/dl]}
   [0x84 0xd7] {::i/tag ::i/test, ::i/args [::r/bh ::r/dl]}
   [0x84 0xd8] {::i/tag ::i/test, ::i/args [::r/al ::r/bl]}
   [0x84 0xd9] {::i/tag ::i/test, ::i/args [::r/cl ::r/bl]}
   [0x84 0xda] {::i/tag ::i/test, ::i/args [::r/dl ::r/bl]}
   [0x84 0xdb] {::i/tag ::i/test, ::i/args [::r/bl ::r/bl]}
   [0x84 0xdc] {::i/tag ::i/test, ::i/args [::r/ah ::r/bl]}
   [0x84 0xdd] {::i/tag ::i/test, ::i/args [::r/ch ::r/bl]}
   [0x84 0xde] {::i/tag ::i/test, ::i/args [::r/dh ::r/bl]}
   [0x84 0xdf] {::i/tag ::i/test, ::i/args [::r/bh ::r/bl]}
   [0x84 0xe0] {::i/tag ::i/test, ::i/args [::r/al ::r/ah]}
   [0x84 0xe1] {::i/tag ::i/test, ::i/args [::r/cl ::r/ah]}
   [0x84 0xe2] {::i/tag ::i/test, ::i/args [::r/dl ::r/ah]}
   [0x84 0xe3] {::i/tag ::i/test, ::i/args [::r/bl ::r/ah]}
   [0x84 0xe4] {::i/tag ::i/test, ::i/args [::r/ah ::r/ah]}
   [0x84 0xe5] {::i/tag ::i/test, ::i/args [::r/ch ::r/ah]}
   [0x84 0xe6] {::i/tag ::i/test, ::i/args [::r/dh ::r/ah]}
   [0x84 0xe7] {::i/tag ::i/test, ::i/args [::r/bh ::r/ah]}
   [0x84 0xe8] {::i/tag ::i/test, ::i/args [::r/al ::r/ch]}
   [0x84 0xe9] {::i/tag ::i/test, ::i/args [::r/cl ::r/ch]}
   [0x84 0xea] {::i/tag ::i/test, ::i/args [::r/dl ::r/ch]}
   [0x84 0xeb] {::i/tag ::i/test, ::i/args [::r/bl ::r/ch]}
   [0x84 0xec] {::i/tag ::i/test, ::i/args [::r/ah ::r/ch]}
   [0x84 0xed] {::i/tag ::i/test, ::i/args [::r/ch ::r/ch]}
   [0x84 0xee] {::i/tag ::i/test, ::i/args [::r/dh ::r/ch]}
   [0x84 0xef] {::i/tag ::i/test, ::i/args [::r/bh ::r/ch]}
   [0x84 0xf0] {::i/tag ::i/test, ::i/args [::r/al ::r/dh]}
   [0x84 0xf1] {::i/tag ::i/test, ::i/args [::r/cl ::r/dh]}
   [0x84 0xf2] {::i/tag ::i/test, ::i/args [::r/dl ::r/dh]}
   [0x84 0xf3] {::i/tag ::i/test, ::i/args [::r/bl ::r/dh]}
   [0x84 0xf4] {::i/tag ::i/test, ::i/args [::r/ah ::r/dh]}
   [0x84 0xf5] {::i/tag ::i/test, ::i/args [::r/ch ::r/dh]}
   [0x84 0xf6] {::i/tag ::i/test, ::i/args [::r/dh ::r/dh]}
   [0x84 0xf7] {::i/tag ::i/test, ::i/args [::r/bh ::r/dh]}
   [0x84 0xf8] {::i/tag ::i/test, ::i/args [::r/al ::r/bh]}
   [0x84 0xf9] {::i/tag ::i/test, ::i/args [::r/cl ::r/bh]}
   [0x84 0xfa] {::i/tag ::i/test, ::i/args [::r/dl ::r/bh]}
   [0x84 0xfb] {::i/tag ::i/test, ::i/args [::r/bl ::r/bh]}
   [0x84 0xfc] {::i/tag ::i/test, ::i/args [::r/ah ::r/bh]}
   [0x84 0xfd] {::i/tag ::i/test, ::i/args [::r/ch ::r/bh]}
   [0x84 0xfe] {::i/tag ::i/test, ::i/args [::r/dh ::r/bh]}
   [0x84 0xff] {::i/tag ::i/test, ::i/args [::r/bh ::r/bh]}
   [0x85 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/ax]}
   [0x85 0x01] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/ax]}
   [0x85 0x02] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/ax]}
   [0x85 0x03] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/ax]}
   [0x85 0x04] {::i/tag ::i/test, ::i/args [[::r/si] ::r/ax]}
   [0x85 0x05] {::i/tag ::i/test, ::i/args [[::r/di] ::r/ax]}
   [0x85 0x06 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/ax]}
   [0x85 0x06 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/ax]}
   [0x85 0x07] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/ax]}
   [0x85 0x08] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/cx]}
   [0x85 0x09] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/cx]}
   [0x85 0x0a] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/cx]}
   [0x85 0x0b] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/cx]}
   [0x85 0x0c] {::i/tag ::i/test, ::i/args [[::r/si] ::r/cx]}
   [0x85 0x0d] {::i/tag ::i/test, ::i/args [[::r/di] ::r/cx]}
   [0x85 0x0e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/cx]}
   [0x85 0x0e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/cx]}
   [0x85 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/cx]}
   [0x85 0x10] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/dx]}
   [0x85 0x11] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/dx]}
   [0x85 0x12] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/dx]}
   [0x85 0x13] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/dx]}
   [0x85 0x14] {::i/tag ::i/test, ::i/args [[::r/si] ::r/dx]}
   [0x85 0x15] {::i/tag ::i/test, ::i/args [[::r/di] ::r/dx]}
   [0x85 0x16 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/dx]}
   [0x85 0x16 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/dx]}
   [0x85 0x17] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/dx]}
   [0x85 0x18] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bx]}
   [0x85 0x19] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bx]}
   [0x85 0x1a] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bx]}
   [0x85 0x1b] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bx]}
   [0x85 0x1c] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bx]}
   [0x85 0x1d] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bx]}
   [0x85 0x1e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/bx]}
   [0x85 0x1e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/bx]}
   [0x85 0x1f] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bx]}
   [0x85 0x20] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/sp]}
   [0x85 0x21] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/sp]}
   [0x85 0x22] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/sp]}
   [0x85 0x23] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/sp]}
   [0x85 0x24] {::i/tag ::i/test, ::i/args [[::r/si] ::r/sp]}
   [0x85 0x25] {::i/tag ::i/test, ::i/args [[::r/di] ::r/sp]}
   [0x85 0x26 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/sp]}
   [0x85 0x26 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/sp]}
   [0x85 0x27] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/sp]}
   [0x85 0x28] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bp]}
   [0x85 0x29] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bp]}
   [0x85 0x2a] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bp]}
   [0x85 0x2b] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bp]}
   [0x85 0x2c] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bp]}
   [0x85 0x2d] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bp]}
   [0x85 0x2e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/bp]}
   [0x85 0x2e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/bp]}
   [0x85 0x2f] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bp]}
   [0x85 0x30] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/si]}
   [0x85 0x31] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/si]}
   [0x85 0x32] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/si]}
   [0x85 0x33] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/si]}
   [0x85 0x34] {::i/tag ::i/test, ::i/args [[::r/si] ::r/si]}
   [0x85 0x35] {::i/tag ::i/test, ::i/args [[::r/di] ::r/si]}
   [0x85 0x36 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/si]}
   [0x85 0x36 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/si]}
   [0x85 0x37] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/si]}
   [0x85 0x38] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/di]}
   [0x85 0x39] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/di]}
   [0x85 0x3a] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/di]}
   [0x85 0x3b] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/di]}
   [0x85 0x3c] {::i/tag ::i/test, ::i/args [[::r/si] ::r/di]}
   [0x85 0x3d] {::i/tag ::i/test, ::i/args [[::r/di] ::r/di]}
   [0x85 0x3e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[0x0ff0] ::r/di]}
   [0x85 0x3e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[0xf00f] ::r/di]}
   [0x85 0x3f] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/di]}
   [0x85 0x40 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/ax]}
   [0x85 0x40 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/ax]}
   [0x85 0x40 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/ax]}
   [0x85 0x41 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/ax]}
   [0x85 0x41 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/ax]}
   [0x85 0x41 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/ax]}
   [0x85 0x42 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/ax]}
   [0x85 0x42 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/ax]}
   [0x85 0x42 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/ax]}
   [0x85 0x43 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/ax]}
   [0x85 0x43 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/ax]}
   [0x85 0x43 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/ax]}
   [0x85 0x44 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/ax]}
   [0x85 0x44 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/ax]}
   [0x85 0x44 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/ax]}
   [0x85 0x45 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/ax]}
   [0x85 0x45 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/ax]}
   [0x85 0x45 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/ax]}
   [0x85 0x46 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/ax]}
   [0x85 0x46 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/ax]}
   [0x85 0x46 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/ax]}
   [0x85 0x47 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/ax]}
   [0x85 0x47 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/ax]}
   [0x85 0x47 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/ax]}
   [0x85 0x48 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/cx]}
   [0x85 0x48 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/cx]}
   [0x85 0x48 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/cx]}
   [0x85 0x49 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/cx]}
   [0x85 0x49 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/cx]}
   [0x85 0x49 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/cx]}
   [0x85 0x4a 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/cx]}
   [0x85 0x4a 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/cx]}
   [0x85 0x4a 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/cx]}
   [0x85 0x4b 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/cx]}
   [0x85 0x4b 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/cx]}
   [0x85 0x4b 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/cx]}
   [0x85 0x4c 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/cx]}
   [0x85 0x4c 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/cx]}
   [0x85 0x4c 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/cx]}
   [0x85 0x4d 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/cx]}
   [0x85 0x4d 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/cx]}
   [0x85 0x4d 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/cx]}
   [0x85 0x4e 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/cx]}
   [0x85 0x4e 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/cx]}
   [0x85 0x4e 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/cx]}
   [0x85 0x4f 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/cx]}
   [0x85 0x4f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/cx]}
   [0x85 0x4f 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/cx]}
   [0x85 0x50 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/dx]}
   [0x85 0x50 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/dx]}
   [0x85 0x50 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/dx]}
   [0x85 0x51 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/dx]}
   [0x85 0x51 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/dx]}
   [0x85 0x51 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/dx]}
   [0x85 0x52 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/dx]}
   [0x85 0x52 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/dx]}
   [0x85 0x52 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/dx]}
   [0x85 0x53 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/dx]}
   [0x85 0x53 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/dx]}
   [0x85 0x53 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/dx]}
   [0x85 0x54 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/dx]}
   [0x85 0x54 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/dx]}
   [0x85 0x54 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/dx]}
   [0x85 0x55 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/dx]}
   [0x85 0x55 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/dx]}
   [0x85 0x55 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/dx]}
   [0x85 0x56 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/dx]}
   [0x85 0x56 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/dx]}
   [0x85 0x56 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/dx]}
   [0x85 0x57 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/dx]}
   [0x85 0x57 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/dx]}
   [0x85 0x57 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/dx]}
   [0x85 0x58 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bx]}
   [0x85 0x58 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/bx]}
   [0x85 0x58 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/bx]}
   [0x85 0x59 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bx]}
   [0x85 0x59 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/bx]}
   [0x85 0x59 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/bx]}
   [0x85 0x5a 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bx]}
   [0x85 0x5a 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/bx]}
   [0x85 0x5a 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/bx]}
   [0x85 0x5b 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bx]}
   [0x85 0x5b 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/bx]}
   [0x85 0x5b 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/bx]}
   [0x85 0x5c 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bx]}
   [0x85 0x5c 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/bx]}
   [0x85 0x5c 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/bx]}
   [0x85 0x5d 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bx]}
   [0x85 0x5d 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/bx]}
   [0x85 0x5d 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/bx]}
   [0x85 0x5e 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/bx]}
   [0x85 0x5e 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/bx]}
   [0x85 0x5e 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/bx]}
   [0x85 0x5f 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bx]}
   [0x85 0x5f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/bx]}
   [0x85 0x5f 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/bx]}
   [0x85 0x60 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/sp]}
   [0x85 0x60 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/sp]}
   [0x85 0x60 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/sp]}
   [0x85 0x61 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/sp]}
   [0x85 0x61 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/sp]}
   [0x85 0x61 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/sp]}
   [0x85 0x62 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/sp]}
   [0x85 0x62 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/sp]}
   [0x85 0x62 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/sp]}
   [0x85 0x63 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/sp]}
   [0x85 0x63 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/sp]}
   [0x85 0x63 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/sp]}
   [0x85 0x64 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/sp]}
   [0x85 0x64 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/sp]}
   [0x85 0x64 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/sp]}
   [0x85 0x65 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/sp]}
   [0x85 0x65 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/sp]}
   [0x85 0x65 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/sp]}
   [0x85 0x66 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/sp]}
   [0x85 0x66 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/sp]}
   [0x85 0x66 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/sp]}
   [0x85 0x67 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/sp]}
   [0x85 0x67 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/sp]}
   [0x85 0x67 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/sp]}
   [0x85 0x68 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bp]}
   [0x85 0x68 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/bp]}
   [0x85 0x68 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/bp]}
   [0x85 0x69 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bp]}
   [0x85 0x69 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/bp]}
   [0x85 0x69 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/bp]}
   [0x85 0x6a 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bp]}
   [0x85 0x6a 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/bp]}
   [0x85 0x6a 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/bp]}
   [0x85 0x6b 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bp]}
   [0x85 0x6b 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/bp]}
   [0x85 0x6b 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/bp]}
   [0x85 0x6c 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bp]}
   [0x85 0x6c 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/bp]}
   [0x85 0x6c 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/bp]}
   [0x85 0x6d 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bp]}
   [0x85 0x6d 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/bp]}
   [0x85 0x6d 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/bp]}
   [0x85 0x6e 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/bp]}
   [0x85 0x6e 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/bp]}
   [0x85 0x6e 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/bp]}
   [0x85 0x6f 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bp]}
   [0x85 0x6f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/bp]}
   [0x85 0x6f 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/bp]}
   [0x85 0x70 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/si]}
   [0x85 0x70 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/si]}
   [0x85 0x70 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/si]}
   [0x85 0x71 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/si]}
   [0x85 0x71 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/si]}
   [0x85 0x71 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/si]}
   [0x85 0x72 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/si]}
   [0x85 0x72 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/si]}
   [0x85 0x72 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/si]}
   [0x85 0x73 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/si]}
   [0x85 0x73 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/si]}
   [0x85 0x73 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/si]}
   [0x85 0x74 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/si]}
   [0x85 0x74 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/si]}
   [0x85 0x74 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/si]}
   [0x85 0x75 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/si]}
   [0x85 0x75 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/si]}
   [0x85 0x75 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/si]}
   [0x85 0x76 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/si]}
   [0x85 0x76 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/si]}
   [0x85 0x76 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/si]}
   [0x85 0x77 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/si]}
   [0x85 0x77 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/si]}
   [0x85 0x77 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/si]}
   [0x85 0x78 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/di]}
   [0x85 0x78 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -16] ::r/di]}
   [0x85 0x78 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 15] ::r/di]}
   [0x85 0x79 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/di]}
   [0x85 0x79 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -16] ::r/di]}
   [0x85 0x79 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 15] ::r/di]}
   [0x85 0x7a 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/di]}
   [0x85 0x7a 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -16] ::r/di]}
   [0x85 0x7a 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 15] ::r/di]}
   [0x85 0x7b 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/di]}
   [0x85 0x7b 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -16] ::r/di]}
   [0x85 0x7b 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 15] ::r/di]}
   [0x85 0x7c 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/di]}
   [0x85 0x7c 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -16] ::r/di]}
   [0x85 0x7c 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 15] ::r/di]}
   [0x85 0x7d 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/di]}
   [0x85 0x7d 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -16] ::r/di]}
   [0x85 0x7d 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 15] ::r/di]}
   [0x85 0x7e 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/di]}
   [0x85 0x7e 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -16] ::r/di]}
   [0x85 0x7e 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 15] ::r/di]}
   [0x85 0x7f 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/di]}
   [0x85 0x7f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -16] ::r/di]}
   [0x85 0x7f 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 15] ::r/di]}
   [0x85 0x80 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/ax]}
   [0x85 0x80 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/ax]}
   [0x85 0x80 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/ax]}
   [0x85 0x81 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/ax]}
   [0x85 0x81 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/ax]}
   [0x85 0x81 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/ax]}
   [0x85 0x82 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/ax]}
   [0x85 0x82 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/ax]}
   [0x85 0x82 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/ax]}
   [0x85 0x83 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/ax]}
   [0x85 0x83 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/ax]}
   [0x85 0x83 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/ax]}
   [0x85 0x84 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/ax]}
   [0x85 0x84 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/ax]}
   [0x85 0x84 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/ax]}
   [0x85 0x85 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/ax]}
   [0x85 0x85 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/ax]}
   [0x85 0x85 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/ax]}
   [0x85 0x86 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/ax]}
   [0x85 0x86 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/ax]}
   [0x85 0x86 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/ax]}
   [0x85 0x87 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/ax]}
   [0x85 0x87 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/ax]}
   [0x85 0x87 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/ax]}
   [0x85 0x88 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/cx]}
   [0x85 0x88 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/cx]}
   [0x85 0x88 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/cx]}
   [0x85 0x89 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/cx]}
   [0x85 0x89 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/cx]}
   [0x85 0x89 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/cx]}
   [0x85 0x8a 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/cx]}
   [0x85 0x8a 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/cx]}
   [0x85 0x8a 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/cx]}
   [0x85 0x8b 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/cx]}
   [0x85 0x8b 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/cx]}
   [0x85 0x8b 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/cx]}
   [0x85 0x8c 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/cx]}
   [0x85 0x8c 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/cx]}
   [0x85 0x8c 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/cx]}
   [0x85 0x8d 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/cx]}
   [0x85 0x8d 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/cx]}
   [0x85 0x8d 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/cx]}
   [0x85 0x8e 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/cx]}
   [0x85 0x8e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/cx]}
   [0x85 0x8e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/cx]}
   [0x85 0x8f 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/cx]}
   [0x85 0x8f 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/cx]}
   [0x85 0x8f 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/cx]}
   [0x85 0x90 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/dx]}
   [0x85 0x90 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/dx]}
   [0x85 0x90 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/dx]}
   [0x85 0x91 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/dx]}
   [0x85 0x91 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/dx]}
   [0x85 0x91 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/dx]}
   [0x85 0x92 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/dx]}
   [0x85 0x92 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/dx]}
   [0x85 0x92 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/dx]}
   [0x85 0x93 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/dx]}
   [0x85 0x93 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/dx]}
   [0x85 0x93 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/dx]}
   [0x85 0x94 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/dx]}
   [0x85 0x94 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/dx]}
   [0x85 0x94 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/dx]}
   [0x85 0x95 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/dx]}
   [0x85 0x95 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/dx]}
   [0x85 0x95 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/dx]}
   [0x85 0x96 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/dx]}
   [0x85 0x96 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/dx]}
   [0x85 0x96 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/dx]}
   [0x85 0x97 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/dx]}
   [0x85 0x97 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/dx]}
   [0x85 0x97 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/dx]}
   [0x85 0x98 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bx]}
   [0x85 0x98 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/bx]}
   [0x85 0x98 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/bx]}
   [0x85 0x99 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bx]}
   [0x85 0x99 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/bx]}
   [0x85 0x99 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/bx]}
   [0x85 0x9a 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bx]}
   [0x85 0x9a 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/bx]}
   [0x85 0x9a 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/bx]}
   [0x85 0x9b 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bx]}
   [0x85 0x9b 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/bx]}
   [0x85 0x9b 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/bx]}
   [0x85 0x9c 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bx]}
   [0x85 0x9c 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/bx]}
   [0x85 0x9c 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/bx]}
   [0x85 0x9d 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bx]}
   [0x85 0x9d 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/bx]}
   [0x85 0x9d 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/bx]}
   [0x85 0x9e 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/bx]}
   [0x85 0x9e 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/bx]}
   [0x85 0x9e 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/bx]}
   [0x85 0x9f 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bx]}
   [0x85 0x9f 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/bx]}
   [0x85 0x9f 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/bx]}
   [0x85 0xa0 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/sp]}
   [0x85 0xa0 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/sp]}
   [0x85 0xa0 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/sp]}
   [0x85 0xa1 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/sp]}
   [0x85 0xa1 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/sp]}
   [0x85 0xa1 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/sp]}
   [0x85 0xa2 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/sp]}
   [0x85 0xa2 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/sp]}
   [0x85 0xa2 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/sp]}
   [0x85 0xa3 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/sp]}
   [0x85 0xa3 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/sp]}
   [0x85 0xa3 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/sp]}
   [0x85 0xa4 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/sp]}
   [0x85 0xa4 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/sp]}
   [0x85 0xa4 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/sp]}
   [0x85 0xa5 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/sp]}
   [0x85 0xa5 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/sp]}
   [0x85 0xa5 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/sp]}
   [0x85 0xa6 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/sp]}
   [0x85 0xa6 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/sp]}
   [0x85 0xa6 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/sp]}
   [0x85 0xa7 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/sp]}
   [0x85 0xa7 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/sp]}
   [0x85 0xa7 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/sp]}
   [0x85 0xa8 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/bp]}
   [0x85 0xa8 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/bp]}
   [0x85 0xa8 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/bp]}
   [0x85 0xa9 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/bp]}
   [0x85 0xa9 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/bp]}
   [0x85 0xa9 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/bp]}
   [0x85 0xaa 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/bp]}
   [0x85 0xaa 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/bp]}
   [0x85 0xaa 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/bp]}
   [0x85 0xab 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/bp]}
   [0x85 0xab 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/bp]}
   [0x85 0xab 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/bp]}
   [0x85 0xac 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/bp]}
   [0x85 0xac 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/bp]}
   [0x85 0xac 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/bp]}
   [0x85 0xad 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/bp]}
   [0x85 0xad 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/bp]}
   [0x85 0xad 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/bp]}
   [0x85 0xae 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/bp]}
   [0x85 0xae 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/bp]}
   [0x85 0xae 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/bp]}
   [0x85 0xaf 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/bp]}
   [0x85 0xaf 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/bp]}
   [0x85 0xaf 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/bp]}
   [0x85 0xb0 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/si]}
   [0x85 0xb0 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/si]}
   [0x85 0xb0 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/si]}
   [0x85 0xb1 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/si]}
   [0x85 0xb1 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/si]}
   [0x85 0xb1 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/si]}
   [0x85 0xb2 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/si]}
   [0x85 0xb2 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/si]}
   [0x85 0xb2 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/si]}
   [0x85 0xb3 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/si]}
   [0x85 0xb3 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/si]}
   [0x85 0xb3 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/si]}
   [0x85 0xb4 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/si]}
   [0x85 0xb4 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/si]}
   [0x85 0xb4 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/si]}
   [0x85 0xb5 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/si]}
   [0x85 0xb5 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/si]}
   [0x85 0xb5 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/si]}
   [0x85 0xb6 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/si]}
   [0x85 0xb6 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/si]}
   [0x85 0xb6 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/si]}
   [0x85 0xb7 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/si]}
   [0x85 0xb7 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/si]}
   [0x85 0xb7 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/si]}
   [0x85 0xb8 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si] ::r/di]}
   [0x85 0xb8 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si 4080] ::r/di]}
   [0x85 0xb8 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/si -4081] ::r/di]}
   [0x85 0xb9 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di] ::r/di]}
   [0x85 0xb9 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di 4080] ::r/di]}
   [0x85 0xb9 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx ::r/di -4081] ::r/di]}
   [0x85 0xba 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si] ::r/di]}
   [0x85 0xba 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si 4080] ::r/di]}
   [0x85 0xba 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/si -4081] ::r/di]}
   [0x85 0xbb 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di] ::r/di]}
   [0x85 0xbb 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di 4080] ::r/di]}
   [0x85 0xbb 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp ::r/di -4081] ::r/di]}
   [0x85 0xbc 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/si] ::r/di]}
   [0x85 0xbc 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/si 4080] ::r/di]}
   [0x85 0xbc 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/si -4081] ::r/di]}
   [0x85 0xbd 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/di] ::r/di]}
   [0x85 0xbd 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/di 4080] ::r/di]}
   [0x85 0xbd 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/di -4081] ::r/di]}
   [0x85 0xbe 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bp] ::r/di]}
   [0x85 0xbe 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bp 4080] ::r/di]}
   [0x85 0xbe 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bp -4081] ::r/di]}
   [0x85 0xbf 0x00 0x00] {::i/tag ::i/test, ::i/args [[::r/bx] ::r/di]}
   [0x85 0xbf 0xf0 0x0f] {::i/tag ::i/test, ::i/args [[::r/bx 4080] ::r/di]}
   [0x85 0xbf 0x0f 0xf0] {::i/tag ::i/test, ::i/args [[::r/bx -4081] ::r/di]}
   [0x85 0xc0] {::i/tag ::i/test, ::i/args [::r/ax ::r/ax]}
   [0x85 0xc1] {::i/tag ::i/test, ::i/args [::r/cx ::r/ax]}
   [0x85 0xc2] {::i/tag ::i/test, ::i/args [::r/dx ::r/ax]}
   [0x85 0xc3] {::i/tag ::i/test, ::i/args [::r/bx ::r/ax]}
   [0x85 0xc4] {::i/tag ::i/test, ::i/args [::r/sp ::r/ax]}
   [0x85 0xc5] {::i/tag ::i/test, ::i/args [::r/bp ::r/ax]}
   [0x85 0xc6] {::i/tag ::i/test, ::i/args [::r/si ::r/ax]}
   [0x85 0xc7] {::i/tag ::i/test, ::i/args [::r/di ::r/ax]}
   [0x85 0xc8] {::i/tag ::i/test, ::i/args [::r/ax ::r/cx]}
   [0x85 0xc9] {::i/tag ::i/test, ::i/args [::r/cx ::r/cx]}
   [0x85 0xca] {::i/tag ::i/test, ::i/args [::r/dx ::r/cx]}
   [0x85 0xcb] {::i/tag ::i/test, ::i/args [::r/bx ::r/cx]}
   [0x85 0xcc] {::i/tag ::i/test, ::i/args [::r/sp ::r/cx]}
   [0x85 0xcd] {::i/tag ::i/test, ::i/args [::r/bp ::r/cx]}
   [0x85 0xce] {::i/tag ::i/test, ::i/args [::r/si ::r/cx]}
   [0x85 0xcf] {::i/tag ::i/test, ::i/args [::r/di ::r/cx]}
   [0x85 0xd0] {::i/tag ::i/test, ::i/args [::r/ax ::r/dx]}
   [0x85 0xd1] {::i/tag ::i/test, ::i/args [::r/cx ::r/dx]}
   [0x85 0xd2] {::i/tag ::i/test, ::i/args [::r/dx ::r/dx]}
   [0x85 0xd3] {::i/tag ::i/test, ::i/args [::r/bx ::r/dx]}
   [0x85 0xd4] {::i/tag ::i/test, ::i/args [::r/sp ::r/dx]}
   [0x85 0xd5] {::i/tag ::i/test, ::i/args [::r/bp ::r/dx]}
   [0x85 0xd6] {::i/tag ::i/test, ::i/args [::r/si ::r/dx]}
   [0x85 0xd7] {::i/tag ::i/test, ::i/args [::r/di ::r/dx]}
   [0x85 0xd8] {::i/tag ::i/test, ::i/args [::r/ax ::r/bx]}
   [0x85 0xd9] {::i/tag ::i/test, ::i/args [::r/cx ::r/bx]}
   [0x85 0xda] {::i/tag ::i/test, ::i/args [::r/dx ::r/bx]}
   [0x85 0xdb] {::i/tag ::i/test, ::i/args [::r/bx ::r/bx]}
   [0x85 0xdc] {::i/tag ::i/test, ::i/args [::r/sp ::r/bx]}
   [0x85 0xdd] {::i/tag ::i/test, ::i/args [::r/bp ::r/bx]}
   [0x85 0xde] {::i/tag ::i/test, ::i/args [::r/si ::r/bx]}
   [0x85 0xdf] {::i/tag ::i/test, ::i/args [::r/di ::r/bx]}
   [0x85 0xe0] {::i/tag ::i/test, ::i/args [::r/ax ::r/sp]}
   [0x85 0xe1] {::i/tag ::i/test, ::i/args [::r/cx ::r/sp]}
   [0x85 0xe2] {::i/tag ::i/test, ::i/args [::r/dx ::r/sp]}
   [0x85 0xe3] {::i/tag ::i/test, ::i/args [::r/bx ::r/sp]}
   [0x85 0xe4] {::i/tag ::i/test, ::i/args [::r/sp ::r/sp]}
   [0x85 0xe5] {::i/tag ::i/test, ::i/args [::r/bp ::r/sp]}
   [0x85 0xe6] {::i/tag ::i/test, ::i/args [::r/si ::r/sp]}
   [0x85 0xe7] {::i/tag ::i/test, ::i/args [::r/di ::r/sp]}
   [0x85 0xe8] {::i/tag ::i/test, ::i/args [::r/ax ::r/bp]}
   [0x85 0xe9] {::i/tag ::i/test, ::i/args [::r/cx ::r/bp]}
   [0x85 0xea] {::i/tag ::i/test, ::i/args [::r/dx ::r/bp]}
   [0x85 0xeb] {::i/tag ::i/test, ::i/args [::r/bx ::r/bp]}
   [0x85 0xec] {::i/tag ::i/test, ::i/args [::r/sp ::r/bp]}
   [0x85 0xed] {::i/tag ::i/test, ::i/args [::r/bp ::r/bp]}
   [0x85 0xee] {::i/tag ::i/test, ::i/args [::r/si ::r/bp]}
   [0x85 0xef] {::i/tag ::i/test, ::i/args [::r/di ::r/bp]}
   [0x85 0xf0] {::i/tag ::i/test, ::i/args [::r/ax ::r/si]}
   [0x85 0xf1] {::i/tag ::i/test, ::i/args [::r/cx ::r/si]}
   [0x85 0xf2] {::i/tag ::i/test, ::i/args [::r/dx ::r/si]}
   [0x85 0xf3] {::i/tag ::i/test, ::i/args [::r/bx ::r/si]}
   [0x85 0xf4] {::i/tag ::i/test, ::i/args [::r/sp ::r/si]}
   [0x85 0xf5] {::i/tag ::i/test, ::i/args [::r/bp ::r/si]}
   [0x85 0xf6] {::i/tag ::i/test, ::i/args [::r/si ::r/si]}
   [0x85 0xf7] {::i/tag ::i/test, ::i/args [::r/di ::r/si]}
   [0x85 0xf8] {::i/tag ::i/test, ::i/args [::r/ax ::r/di]}
   [0x85 0xf9] {::i/tag ::i/test, ::i/args [::r/cx ::r/di]}
   [0x85 0xfa] {::i/tag ::i/test, ::i/args [::r/dx ::r/di]}
   [0x85 0xfb] {::i/tag ::i/test, ::i/args [::r/bx ::r/di]}
   [0x85 0xfc] {::i/tag ::i/test, ::i/args [::r/sp ::r/di]}
   [0x85 0xfd] {::i/tag ::i/test, ::i/args [::r/bp ::r/di]}
   [0x85 0xfe] {::i/tag ::i/test, ::i/args [::r/si ::r/di]}
   [0x85 0xff] {::i/tag ::i/test, ::i/args [::r/di ::r/di]}})
