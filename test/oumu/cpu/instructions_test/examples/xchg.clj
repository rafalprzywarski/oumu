(ns oumu.cpu.instructions-test.examples.xchg
    (:require [oumu.cpu.instructions :as i]
              [oumu.cpu.registers :as r]))


(def decode-xchg-examples
  {[0x86 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/si]]}
   [0x86 0x01] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/di]]}
   [0x86 0x02] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/si]]}
   [0x86 0x03] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/di]]}
   [0x86 0x04] {::i/tag ::i/xchg, ::i/args [::r/al [::r/si]]}
   [0x86 0x05] {::i/tag ::i/xchg, ::i/args [::r/al [::r/di]]}
   [0x86 0x06 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [0x0ff0]]}
   [0x86 0x06 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [0xf00f]]}
   [0x86 0x07] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx]]}
   [0x86 0x08] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/si]]}
   [0x86 0x09] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/di]]}
   [0x86 0x0a] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/si]]}
   [0x86 0x0b] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/di]]}
   [0x86 0x0c] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/si]]}
   [0x86 0x0d] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/di]]}
   [0x86 0x0e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [0x0ff0]]}
   [0x86 0x0e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [0xf00f]]}
   [0x86 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx]]}
   [0x86 0x10] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/si]]}
   [0x86 0x11] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/di]]}
   [0x86 0x12] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/si]]}
   [0x86 0x13] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/di]]}
   [0x86 0x14] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/si]]}
   [0x86 0x15] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/di]]}
   [0x86 0x16 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [0x0ff0]]}
   [0x86 0x16 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [0xf00f]]}
   [0x86 0x17] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx]]}
   [0x86 0x18] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/si]]}
   [0x86 0x19] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/di]]}
   [0x86 0x1a] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/si]]}
   [0x86 0x1b] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/di]]}
   [0x86 0x1c] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/si]]}
   [0x86 0x1d] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/di]]}
   [0x86 0x1e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [0x0ff0]]}
   [0x86 0x1e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [0xf00f]]}
   [0x86 0x1f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx]]}
   [0x86 0x20] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/si]]}
   [0x86 0x21] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/di]]}
   [0x86 0x22] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/si]]}
   [0x86 0x23] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/di]]}
   [0x86 0x24] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/si]]}
   [0x86 0x25] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/di]]}
   [0x86 0x26 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [0x0ff0]]}
   [0x86 0x26 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [0xf00f]]}
   [0x86 0x27] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx]]}
   [0x86 0x28] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/si]]}
   [0x86 0x29] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/di]]}
   [0x86 0x2a] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/si]]}
   [0x86 0x2b] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/di]]}
   [0x86 0x2c] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/si]]}
   [0x86 0x2d] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/di]]}
   [0x86 0x2e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [0x0ff0]]}
   [0x86 0x2e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [0xf00f]]}
   [0x86 0x2f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx]]}
   [0x86 0x30] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/si]]}
   [0x86 0x31] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/di]]}
   [0x86 0x32] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/si]]}
   [0x86 0x33] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/di]]}
   [0x86 0x34] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/si]]}
   [0x86 0x35] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/di]]}
   [0x86 0x36 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [0x0ff0]]}
   [0x86 0x36 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [0xf00f]]}
   [0x86 0x37] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx]]}
   [0x86 0x38] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/si]]}
   [0x86 0x39] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/di]]}
   [0x86 0x3a] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/si]]}
   [0x86 0x3b] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/di]]}
   [0x86 0x3c] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/si]]}
   [0x86 0x3d] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/di]]}
   [0x86 0x3e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [0x0ff0]]}
   [0x86 0x3e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [0xf00f]]}
   [0x86 0x3f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx]]}
   [0x86 0x40 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/si]]}
   [0x86 0x40 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/si -16]]}
   [0x86 0x40 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/si 15]]}
   [0x86 0x41 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/di]]}
   [0x86 0x41 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/di -16]]}
   [0x86 0x41 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/di 15]]}
   [0x86 0x42 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/si]]}
   [0x86 0x42 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/si -16]]}
   [0x86 0x42 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/si 15]]}
   [0x86 0x43 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/di]]}
   [0x86 0x43 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/di -16]]}
   [0x86 0x43 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/di 15]]}
   [0x86 0x44 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/si]]}
   [0x86 0x44 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/si -16]]}
   [0x86 0x44 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/si 15]]}
   [0x86 0x45 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/di]]}
   [0x86 0x45 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/di -16]]}
   [0x86 0x45 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/di 15]]}
   [0x86 0x46 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp]]}
   [0x86 0x46 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp -16]]}
   [0x86 0x46 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp 15]]}
   [0x86 0x47 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx]]}
   [0x86 0x47 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx -16]]}
   [0x86 0x47 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx 15]]}
   [0x86 0x48 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/si]]}
   [0x86 0x48 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/si -16]]}
   [0x86 0x48 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/si 15]]}
   [0x86 0x49 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/di]]}
   [0x86 0x49 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/di -16]]}
   [0x86 0x49 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/di 15]]}
   [0x86 0x4a 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/si]]}
   [0x86 0x4a 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/si -16]]}
   [0x86 0x4a 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/si 15]]}
   [0x86 0x4b 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/di]]}
   [0x86 0x4b 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/di -16]]}
   [0x86 0x4b 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/di 15]]}
   [0x86 0x4c 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/si]]}
   [0x86 0x4c 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/si -16]]}
   [0x86 0x4c 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/si 15]]}
   [0x86 0x4d 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/di]]}
   [0x86 0x4d 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/di -16]]}
   [0x86 0x4d 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/di 15]]}
   [0x86 0x4e 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp]]}
   [0x86 0x4e 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp -16]]}
   [0x86 0x4e 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp 15]]}
   [0x86 0x4f 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx]]}
   [0x86 0x4f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx -16]]}
   [0x86 0x4f 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx 15]]}
   [0x86 0x50 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/si]]}
   [0x86 0x50 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/si -16]]}
   [0x86 0x50 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/si 15]]}
   [0x86 0x51 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/di]]}
   [0x86 0x51 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/di -16]]}
   [0x86 0x51 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/di 15]]}
   [0x86 0x52 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/si]]}
   [0x86 0x52 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/si -16]]}
   [0x86 0x52 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/si 15]]}
   [0x86 0x53 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/di]]}
   [0x86 0x53 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/di -16]]}
   [0x86 0x53 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/di 15]]}
   [0x86 0x54 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/si]]}
   [0x86 0x54 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/si -16]]}
   [0x86 0x54 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/si 15]]}
   [0x86 0x55 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/di]]}
   [0x86 0x55 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/di -16]]}
   [0x86 0x55 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/di 15]]}
   [0x86 0x56 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp]]}
   [0x86 0x56 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp -16]]}
   [0x86 0x56 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp 15]]}
   [0x86 0x57 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx]]}
   [0x86 0x57 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx -16]]}
   [0x86 0x57 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx 15]]}
   [0x86 0x58 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/si]]}
   [0x86 0x58 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/si -16]]}
   [0x86 0x58 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/si 15]]}
   [0x86 0x59 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/di]]}
   [0x86 0x59 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/di -16]]}
   [0x86 0x59 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/di 15]]}
   [0x86 0x5a 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/si]]}
   [0x86 0x5a 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/si -16]]}
   [0x86 0x5a 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/si 15]]}
   [0x86 0x5b 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/di]]}
   [0x86 0x5b 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/di -16]]}
   [0x86 0x5b 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/di 15]]}
   [0x86 0x5c 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/si]]}
   [0x86 0x5c 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/si -16]]}
   [0x86 0x5c 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/si 15]]}
   [0x86 0x5d 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/di]]}
   [0x86 0x5d 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/di -16]]}
   [0x86 0x5d 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/di 15]]}
   [0x86 0x5e 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp]]}
   [0x86 0x5e 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp -16]]}
   [0x86 0x5e 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp 15]]}
   [0x86 0x5f 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx]]}
   [0x86 0x5f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx -16]]}
   [0x86 0x5f 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx 15]]}
   [0x86 0x60 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/si]]}
   [0x86 0x60 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/si -16]]}
   [0x86 0x60 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/si 15]]}
   [0x86 0x61 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/di]]}
   [0x86 0x61 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/di -16]]}
   [0x86 0x61 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/di 15]]}
   [0x86 0x62 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/si]]}
   [0x86 0x62 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/si -16]]}
   [0x86 0x62 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/si 15]]}
   [0x86 0x63 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/di]]}
   [0x86 0x63 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/di -16]]}
   [0x86 0x63 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/di 15]]}
   [0x86 0x64 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/si]]}
   [0x86 0x64 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/si -16]]}
   [0x86 0x64 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/si 15]]}
   [0x86 0x65 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/di]]}
   [0x86 0x65 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/di -16]]}
   [0x86 0x65 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/di 15]]}
   [0x86 0x66 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp]]}
   [0x86 0x66 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp -16]]}
   [0x86 0x66 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp 15]]}
   [0x86 0x67 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx]]}
   [0x86 0x67 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx -16]]}
   [0x86 0x67 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx 15]]}
   [0x86 0x68 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/si]]}
   [0x86 0x68 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/si -16]]}
   [0x86 0x68 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/si 15]]}
   [0x86 0x69 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/di]]}
   [0x86 0x69 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/di -16]]}
   [0x86 0x69 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/di 15]]}
   [0x86 0x6a 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/si]]}
   [0x86 0x6a 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/si -16]]}
   [0x86 0x6a 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/si 15]]}
   [0x86 0x6b 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/di]]}
   [0x86 0x6b 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/di -16]]}
   [0x86 0x6b 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/di 15]]}
   [0x86 0x6c 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/si]]}
   [0x86 0x6c 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/si -16]]}
   [0x86 0x6c 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/si 15]]}
   [0x86 0x6d 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/di]]}
   [0x86 0x6d 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/di -16]]}
   [0x86 0x6d 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/di 15]]}
   [0x86 0x6e 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp]]}
   [0x86 0x6e 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp -16]]}
   [0x86 0x6e 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp 15]]}
   [0x86 0x6f 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx]]}
   [0x86 0x6f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx -16]]}
   [0x86 0x6f 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx 15]]}
   [0x86 0x70 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/si]]}
   [0x86 0x70 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/si -16]]}
   [0x86 0x70 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/si 15]]}
   [0x86 0x71 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/di]]}
   [0x86 0x71 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/di -16]]}
   [0x86 0x71 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/di 15]]}
   [0x86 0x72 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/si]]}
   [0x86 0x72 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/si -16]]}
   [0x86 0x72 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/si 15]]}
   [0x86 0x73 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/di]]}
   [0x86 0x73 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/di -16]]}
   [0x86 0x73 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/di 15]]}
   [0x86 0x74 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/si]]}
   [0x86 0x74 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/si -16]]}
   [0x86 0x74 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/si 15]]}
   [0x86 0x75 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/di]]}
   [0x86 0x75 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/di -16]]}
   [0x86 0x75 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/di 15]]}
   [0x86 0x76 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp]]}
   [0x86 0x76 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp -16]]}
   [0x86 0x76 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp 15]]}
   [0x86 0x77 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx]]}
   [0x86 0x77 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx -16]]}
   [0x86 0x77 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx 15]]}
   [0x86 0x78 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/si]]}
   [0x86 0x78 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/si -16]]}
   [0x86 0x78 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/si 15]]}
   [0x86 0x79 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/di]]}
   [0x86 0x79 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/di -16]]}
   [0x86 0x79 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/di 15]]}
   [0x86 0x7a 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/si]]}
   [0x86 0x7a 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/si -16]]}
   [0x86 0x7a 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/si 15]]}
   [0x86 0x7b 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/di]]}
   [0x86 0x7b 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/di -16]]}
   [0x86 0x7b 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/di 15]]}
   [0x86 0x7c 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/si]]}
   [0x86 0x7c 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/si -16]]}
   [0x86 0x7c 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/si 15]]}
   [0x86 0x7d 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/di]]}
   [0x86 0x7d 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/di -16]]}
   [0x86 0x7d 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/di 15]]}
   [0x86 0x7e 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp]]}
   [0x86 0x7e 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp -16]]}
   [0x86 0x7e 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp 15]]}
   [0x86 0x7f 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx]]}
   [0x86 0x7f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx -16]]}
   [0x86 0x7f 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx 15]]}
   [0x86 0x80 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/si]]}
   [0x86 0x80 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/si 4080]]}
   [0x86 0x80 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/si -4081]]}
   [0x86 0x81 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/di]]}
   [0x86 0x81 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/di 4080]]}
   [0x86 0x81 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx ::r/di -4081]]}
   [0x86 0x82 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/si]]}
   [0x86 0x82 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/si 4080]]}
   [0x86 0x82 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/si -4081]]}
   [0x86 0x83 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/di]]}
   [0x86 0x83 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/di 4080]]}
   [0x86 0x83 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp ::r/di -4081]]}
   [0x86 0x84 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/si]]}
   [0x86 0x84 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/si 4080]]}
   [0x86 0x84 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/si -4081]]}
   [0x86 0x85 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/di]]}
   [0x86 0x85 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/di 4080]]}
   [0x86 0x85 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/di -4081]]}
   [0x86 0x86 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp]]}
   [0x86 0x86 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp 4080]]}
   [0x86 0x86 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bp -4081]]}
   [0x86 0x87 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx]]}
   [0x86 0x87 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx 4080]]}
   [0x86 0x87 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/al [::r/bx -4081]]}
   [0x86 0x88 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/si]]}
   [0x86 0x88 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/si 4080]]}
   [0x86 0x88 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/si -4081]]}
   [0x86 0x89 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/di]]}
   [0x86 0x89 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/di 4080]]}
   [0x86 0x89 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx ::r/di -4081]]}
   [0x86 0x8a 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/si]]}
   [0x86 0x8a 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/si 4080]]}
   [0x86 0x8a 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/si -4081]]}
   [0x86 0x8b 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/di]]}
   [0x86 0x8b 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/di 4080]]}
   [0x86 0x8b 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp ::r/di -4081]]}
   [0x86 0x8c 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/si]]}
   [0x86 0x8c 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/si 4080]]}
   [0x86 0x8c 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/si -4081]]}
   [0x86 0x8d 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/di]]}
   [0x86 0x8d 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/di 4080]]}
   [0x86 0x8d 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/di -4081]]}
   [0x86 0x8e 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp]]}
   [0x86 0x8e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp 4080]]}
   [0x86 0x8e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bp -4081]]}
   [0x86 0x8f 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx]]}
   [0x86 0x8f 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx 4080]]}
   [0x86 0x8f 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cl [::r/bx -4081]]}
   [0x86 0x90 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/si]]}
   [0x86 0x90 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/si 4080]]}
   [0x86 0x90 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/si -4081]]}
   [0x86 0x91 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/di]]}
   [0x86 0x91 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/di 4080]]}
   [0x86 0x91 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx ::r/di -4081]]}
   [0x86 0x92 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/si]]}
   [0x86 0x92 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/si 4080]]}
   [0x86 0x92 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/si -4081]]}
   [0x86 0x93 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/di]]}
   [0x86 0x93 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/di 4080]]}
   [0x86 0x93 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp ::r/di -4081]]}
   [0x86 0x94 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/si]]}
   [0x86 0x94 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/si 4080]]}
   [0x86 0x94 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/si -4081]]}
   [0x86 0x95 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/di]]}
   [0x86 0x95 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/di 4080]]}
   [0x86 0x95 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/di -4081]]}
   [0x86 0x96 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp]]}
   [0x86 0x96 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp 4080]]}
   [0x86 0x96 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bp -4081]]}
   [0x86 0x97 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx]]}
   [0x86 0x97 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx 4080]]}
   [0x86 0x97 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dl [::r/bx -4081]]}
   [0x86 0x98 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/si]]}
   [0x86 0x98 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/si 4080]]}
   [0x86 0x98 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/si -4081]]}
   [0x86 0x99 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/di]]}
   [0x86 0x99 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/di 4080]]}
   [0x86 0x99 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx ::r/di -4081]]}
   [0x86 0x9a 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/si]]}
   [0x86 0x9a 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/si 4080]]}
   [0x86 0x9a 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/si -4081]]}
   [0x86 0x9b 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/di]]}
   [0x86 0x9b 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/di 4080]]}
   [0x86 0x9b 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp ::r/di -4081]]}
   [0x86 0x9c 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/si]]}
   [0x86 0x9c 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/si 4080]]}
   [0x86 0x9c 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/si -4081]]}
   [0x86 0x9d 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/di]]}
   [0x86 0x9d 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/di 4080]]}
   [0x86 0x9d 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/di -4081]]}
   [0x86 0x9e 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp]]}
   [0x86 0x9e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp 4080]]}
   [0x86 0x9e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bp -4081]]}
   [0x86 0x9f 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx]]}
   [0x86 0x9f 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx 4080]]}
   [0x86 0x9f 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bl [::r/bx -4081]]}
   [0x86 0xa0 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/si]]}
   [0x86 0xa0 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/si 4080]]}
   [0x86 0xa0 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/si -4081]]}
   [0x86 0xa1 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/di]]}
   [0x86 0xa1 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/di 4080]]}
   [0x86 0xa1 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx ::r/di -4081]]}
   [0x86 0xa2 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/si]]}
   [0x86 0xa2 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/si 4080]]}
   [0x86 0xa2 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/si -4081]]}
   [0x86 0xa3 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/di]]}
   [0x86 0xa3 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/di 4080]]}
   [0x86 0xa3 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp ::r/di -4081]]}
   [0x86 0xa4 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/si]]}
   [0x86 0xa4 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/si 4080]]}
   [0x86 0xa4 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/si -4081]]}
   [0x86 0xa5 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/di]]}
   [0x86 0xa5 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/di 4080]]}
   [0x86 0xa5 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/di -4081]]}
   [0x86 0xa6 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp]]}
   [0x86 0xa6 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp 4080]]}
   [0x86 0xa6 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bp -4081]]}
   [0x86 0xa7 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx]]}
   [0x86 0xa7 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx 4080]]}
   [0x86 0xa7 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ah [::r/bx -4081]]}
   [0x86 0xa8 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/si]]}
   [0x86 0xa8 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/si 4080]]}
   [0x86 0xa8 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/si -4081]]}
   [0x86 0xa9 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/di]]}
   [0x86 0xa9 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/di 4080]]}
   [0x86 0xa9 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx ::r/di -4081]]}
   [0x86 0xaa 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/si]]}
   [0x86 0xaa 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/si 4080]]}
   [0x86 0xaa 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/si -4081]]}
   [0x86 0xab 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/di]]}
   [0x86 0xab 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/di 4080]]}
   [0x86 0xab 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp ::r/di -4081]]}
   [0x86 0xac 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/si]]}
   [0x86 0xac 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/si 4080]]}
   [0x86 0xac 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/si -4081]]}
   [0x86 0xad 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/di]]}
   [0x86 0xad 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/di 4080]]}
   [0x86 0xad 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/di -4081]]}
   [0x86 0xae 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp]]}
   [0x86 0xae 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp 4080]]}
   [0x86 0xae 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bp -4081]]}
   [0x86 0xaf 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx]]}
   [0x86 0xaf 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx 4080]]}
   [0x86 0xaf 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ch [::r/bx -4081]]}
   [0x86 0xb0 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/si]]}
   [0x86 0xb0 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/si 4080]]}
   [0x86 0xb0 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/si -4081]]}
   [0x86 0xb1 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/di]]}
   [0x86 0xb1 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/di 4080]]}
   [0x86 0xb1 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx ::r/di -4081]]}
   [0x86 0xb2 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/si]]}
   [0x86 0xb2 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/si 4080]]}
   [0x86 0xb2 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/si -4081]]}
   [0x86 0xb3 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/di]]}
   [0x86 0xb3 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/di 4080]]}
   [0x86 0xb3 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp ::r/di -4081]]}
   [0x86 0xb4 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/si]]}
   [0x86 0xb4 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/si 4080]]}
   [0x86 0xb4 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/si -4081]]}
   [0x86 0xb5 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/di]]}
   [0x86 0xb5 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/di 4080]]}
   [0x86 0xb5 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/di -4081]]}
   [0x86 0xb6 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp]]}
   [0x86 0xb6 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp 4080]]}
   [0x86 0xb6 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bp -4081]]}
   [0x86 0xb7 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx]]}
   [0x86 0xb7 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx 4080]]}
   [0x86 0xb7 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh [::r/bx -4081]]}
   [0x86 0xb8 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/si]]}
   [0x86 0xb8 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/si 4080]]}
   [0x86 0xb8 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/si -4081]]}
   [0x86 0xb9 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/di]]}
   [0x86 0xb9 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/di 4080]]}
   [0x86 0xb9 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx ::r/di -4081]]}
   [0x86 0xba 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/si]]}
   [0x86 0xba 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/si 4080]]}
   [0x86 0xba 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/si -4081]]}
   [0x86 0xbb 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/di]]}
   [0x86 0xbb 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/di 4080]]}
   [0x86 0xbb 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp ::r/di -4081]]}
   [0x86 0xbc 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/si]]}
   [0x86 0xbc 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/si 4080]]}
   [0x86 0xbc 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/si -4081]]}
   [0x86 0xbd 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/di]]}
   [0x86 0xbd 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/di 4080]]}
   [0x86 0xbd 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/di -4081]]}
   [0x86 0xbe 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp]]}
   [0x86 0xbe 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp 4080]]}
   [0x86 0xbe 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bp -4081]]}
   [0x86 0xbf 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx]]}
   [0x86 0xbf 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx 4080]]}
   [0x86 0xbf 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bh [::r/bx -4081]]}
   [0x86 0xc0] {::i/tag ::i/xchg, ::i/args [::r/al ::r/al]}
   [0x86 0xc1] {::i/tag ::i/xchg, ::i/args [::r/al ::r/cl]}
   [0x86 0xc2] {::i/tag ::i/xchg, ::i/args [::r/al ::r/dl]}
   [0x86 0xc3] {::i/tag ::i/xchg, ::i/args [::r/al ::r/bl]}
   [0x86 0xc4] {::i/tag ::i/xchg, ::i/args [::r/al ::r/ah]}
   [0x86 0xc5] {::i/tag ::i/xchg, ::i/args [::r/al ::r/ch]}
   [0x86 0xc6] {::i/tag ::i/xchg, ::i/args [::r/al ::r/dh]}
   [0x86 0xc7] {::i/tag ::i/xchg, ::i/args [::r/al ::r/bh]}
   [0x86 0xc8] {::i/tag ::i/xchg, ::i/args [::r/cl ::r/al]}
   [0x86 0xc9] {::i/tag ::i/xchg, ::i/args [::r/cl ::r/cl]}
   [0x86 0xca] {::i/tag ::i/xchg, ::i/args [::r/cl ::r/dl]}
   [0x86 0xcb] {::i/tag ::i/xchg, ::i/args [::r/cl ::r/bl]}
   [0x86 0xcc] {::i/tag ::i/xchg, ::i/args [::r/cl ::r/ah]}
   [0x86 0xcd] {::i/tag ::i/xchg, ::i/args [::r/cl ::r/ch]}
   [0x86 0xce] {::i/tag ::i/xchg, ::i/args [::r/cl ::r/dh]}
   [0x86 0xcf] {::i/tag ::i/xchg, ::i/args [::r/cl ::r/bh]}
   [0x86 0xd0] {::i/tag ::i/xchg, ::i/args [::r/dl ::r/al]}
   [0x86 0xd1] {::i/tag ::i/xchg, ::i/args [::r/dl ::r/cl]}
   [0x86 0xd2] {::i/tag ::i/xchg, ::i/args [::r/dl ::r/dl]}
   [0x86 0xd3] {::i/tag ::i/xchg, ::i/args [::r/dl ::r/bl]}
   [0x86 0xd4] {::i/tag ::i/xchg, ::i/args [::r/dl ::r/ah]}
   [0x86 0xd5] {::i/tag ::i/xchg, ::i/args [::r/dl ::r/ch]}
   [0x86 0xd6] {::i/tag ::i/xchg, ::i/args [::r/dl ::r/dh]}
   [0x86 0xd7] {::i/tag ::i/xchg, ::i/args [::r/dl ::r/bh]}
   [0x86 0xd8] {::i/tag ::i/xchg, ::i/args [::r/bl ::r/al]}
   [0x86 0xd9] {::i/tag ::i/xchg, ::i/args [::r/bl ::r/cl]}
   [0x86 0xda] {::i/tag ::i/xchg, ::i/args [::r/bl ::r/dl]}
   [0x86 0xdb] {::i/tag ::i/xchg, ::i/args [::r/bl ::r/bl]}
   [0x86 0xdc] {::i/tag ::i/xchg, ::i/args [::r/bl ::r/ah]}
   [0x86 0xdd] {::i/tag ::i/xchg, ::i/args [::r/bl ::r/ch]}
   [0x86 0xde] {::i/tag ::i/xchg, ::i/args [::r/bl ::r/dh]}
   [0x86 0xdf] {::i/tag ::i/xchg, ::i/args [::r/bl ::r/bh]}
   [0x86 0xe0] {::i/tag ::i/xchg, ::i/args [::r/ah ::r/al]}
   [0x86 0xe1] {::i/tag ::i/xchg, ::i/args [::r/ah ::r/cl]}
   [0x86 0xe2] {::i/tag ::i/xchg, ::i/args [::r/ah ::r/dl]}
   [0x86 0xe3] {::i/tag ::i/xchg, ::i/args [::r/ah ::r/bl]}
   [0x86 0xe4] {::i/tag ::i/xchg, ::i/args [::r/ah ::r/ah]}
   [0x86 0xe5] {::i/tag ::i/xchg, ::i/args [::r/ah ::r/ch]}
   [0x86 0xe6] {::i/tag ::i/xchg, ::i/args [::r/ah ::r/dh]}
   [0x86 0xe7] {::i/tag ::i/xchg, ::i/args [::r/ah ::r/bh]}
   [0x86 0xe8] {::i/tag ::i/xchg, ::i/args [::r/ch ::r/al]}
   [0x86 0xe9] {::i/tag ::i/xchg, ::i/args [::r/ch ::r/cl]}
   [0x86 0xea] {::i/tag ::i/xchg, ::i/args [::r/ch ::r/dl]}
   [0x86 0xeb] {::i/tag ::i/xchg, ::i/args [::r/ch ::r/bl]}
   [0x86 0xec] {::i/tag ::i/xchg, ::i/args [::r/ch ::r/ah]}
   [0x86 0xed] {::i/tag ::i/xchg, ::i/args [::r/ch ::r/ch]}
   [0x86 0xee] {::i/tag ::i/xchg, ::i/args [::r/ch ::r/dh]}
   [0x86 0xef] {::i/tag ::i/xchg, ::i/args [::r/ch ::r/bh]}
   [0x86 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dh ::r/al]}
   [0x86 0xf1] {::i/tag ::i/xchg, ::i/args [::r/dh ::r/cl]}
   [0x86 0xf2] {::i/tag ::i/xchg, ::i/args [::r/dh ::r/dl]}
   [0x86 0xf3] {::i/tag ::i/xchg, ::i/args [::r/dh ::r/bl]}
   [0x86 0xf4] {::i/tag ::i/xchg, ::i/args [::r/dh ::r/ah]}
   [0x86 0xf5] {::i/tag ::i/xchg, ::i/args [::r/dh ::r/ch]}
   [0x86 0xf6] {::i/tag ::i/xchg, ::i/args [::r/dh ::r/dh]}
   [0x86 0xf7] {::i/tag ::i/xchg, ::i/args [::r/dh ::r/bh]}
   [0x86 0xf8] {::i/tag ::i/xchg, ::i/args [::r/bh ::r/al]}
   [0x86 0xf9] {::i/tag ::i/xchg, ::i/args [::r/bh ::r/cl]}
   [0x86 0xfa] {::i/tag ::i/xchg, ::i/args [::r/bh ::r/dl]}
   [0x86 0xfb] {::i/tag ::i/xchg, ::i/args [::r/bh ::r/bl]}
   [0x86 0xfc] {::i/tag ::i/xchg, ::i/args [::r/bh ::r/ah]}
   [0x86 0xfd] {::i/tag ::i/xchg, ::i/args [::r/bh ::r/ch]}
   [0x86 0xfe] {::i/tag ::i/xchg, ::i/args [::r/bh ::r/dh]}
   [0x86 0xff] {::i/tag ::i/xchg, ::i/args [::r/bh ::r/bh]}
   [0x87 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x87 0x01] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x87 0x02] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x87 0x03] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x87 0x04] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/si]]}
   [0x87 0x05] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/di]]}
   [0x87 0x06 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [0x0ff0]]}
   [0x87 0x06 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [0xf00f]]}
   [0x87 0x07] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx]]}
   [0x87 0x08] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x87 0x09] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x87 0x0a] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x87 0x0b] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x87 0x0c] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/si]]}
   [0x87 0x0d] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/di]]}
   [0x87 0x0e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [0x0ff0]]}
   [0x87 0x0e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [0xf00f]]}
   [0x87 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx]]}
   [0x87 0x10] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x87 0x11] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x87 0x12] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x87 0x13] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x87 0x14] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/si]]}
   [0x87 0x15] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/di]]}
   [0x87 0x16 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [0x0ff0]]}
   [0x87 0x16 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [0xf00f]]}
   [0x87 0x17] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx]]}
   [0x87 0x18] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x87 0x19] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x87 0x1a] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x87 0x1b] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x87 0x1c] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/si]]}
   [0x87 0x1d] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/di]]}
   [0x87 0x1e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [0x0ff0]]}
   [0x87 0x1e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [0xf00f]]}
   [0x87 0x1f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx]]}
   [0x87 0x20] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x87 0x21] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x87 0x22] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x87 0x23] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x87 0x24] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/si]]}
   [0x87 0x25] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/di]]}
   [0x87 0x26 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [0x0ff0]]}
   [0x87 0x26 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [0xf00f]]}
   [0x87 0x27] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx]]}
   [0x87 0x28] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x87 0x29] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x87 0x2a] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x87 0x2b] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x87 0x2c] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/si]]}
   [0x87 0x2d] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/di]]}
   [0x87 0x2e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [0x0ff0]]}
   [0x87 0x2e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [0xf00f]]}
   [0x87 0x2f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx]]}
   [0x87 0x30] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x87 0x31] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x87 0x32] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x87 0x33] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x87 0x34] {::i/tag ::i/xchg, ::i/args [::r/si [::r/si]]}
   [0x87 0x35] {::i/tag ::i/xchg, ::i/args [::r/si [::r/di]]}
   [0x87 0x36 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [0x0ff0]]}
   [0x87 0x36 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [0xf00f]]}
   [0x87 0x37] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx]]}
   [0x87 0x38] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x87 0x39] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x87 0x3a] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x87 0x3b] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x87 0x3c] {::i/tag ::i/xchg, ::i/args [::r/di [::r/si]]}
   [0x87 0x3d] {::i/tag ::i/xchg, ::i/args [::r/di [::r/di]]}
   [0x87 0x3e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [0x0ff0]]}
   [0x87 0x3e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [0xf00f]]}
   [0x87 0x3f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx]]}
   [0x87 0x40 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x87 0x40 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/si -16]]}
   [0x87 0x40 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/si 15]]}
   [0x87 0x41 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x87 0x41 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/di -16]]}
   [0x87 0x41 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/di 15]]}
   [0x87 0x42 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x87 0x42 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/si -16]]}
   [0x87 0x42 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/si 15]]}
   [0x87 0x43 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x87 0x43 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/di -16]]}
   [0x87 0x43 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/di 15]]}
   [0x87 0x44 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/si]]}
   [0x87 0x44 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/si -16]]}
   [0x87 0x44 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/si 15]]}
   [0x87 0x45 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/di]]}
   [0x87 0x45 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/di -16]]}
   [0x87 0x45 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/di 15]]}
   [0x87 0x46 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp]]}
   [0x87 0x46 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp -16]]}
   [0x87 0x46 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp 15]]}
   [0x87 0x47 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx]]}
   [0x87 0x47 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx -16]]}
   [0x87 0x47 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx 15]]}
   [0x87 0x48 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x87 0x48 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/si -16]]}
   [0x87 0x48 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/si 15]]}
   [0x87 0x49 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x87 0x49 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/di -16]]}
   [0x87 0x49 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/di 15]]}
   [0x87 0x4a 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x87 0x4a 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/si -16]]}
   [0x87 0x4a 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/si 15]]}
   [0x87 0x4b 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x87 0x4b 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/di -16]]}
   [0x87 0x4b 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/di 15]]}
   [0x87 0x4c 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/si]]}
   [0x87 0x4c 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/si -16]]}
   [0x87 0x4c 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/si 15]]}
   [0x87 0x4d 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/di]]}
   [0x87 0x4d 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/di -16]]}
   [0x87 0x4d 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/di 15]]}
   [0x87 0x4e 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp]]}
   [0x87 0x4e 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp -16]]}
   [0x87 0x4e 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp 15]]}
   [0x87 0x4f 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx]]}
   [0x87 0x4f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx -16]]}
   [0x87 0x4f 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx 15]]}
   [0x87 0x50 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x87 0x50 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/si -16]]}
   [0x87 0x50 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/si 15]]}
   [0x87 0x51 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x87 0x51 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/di -16]]}
   [0x87 0x51 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/di 15]]}
   [0x87 0x52 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x87 0x52 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/si -16]]}
   [0x87 0x52 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/si 15]]}
   [0x87 0x53 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x87 0x53 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/di -16]]}
   [0x87 0x53 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/di 15]]}
   [0x87 0x54 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/si]]}
   [0x87 0x54 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/si -16]]}
   [0x87 0x54 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/si 15]]}
   [0x87 0x55 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/di]]}
   [0x87 0x55 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/di -16]]}
   [0x87 0x55 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/di 15]]}
   [0x87 0x56 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp]]}
   [0x87 0x56 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp -16]]}
   [0x87 0x56 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp 15]]}
   [0x87 0x57 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx]]}
   [0x87 0x57 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx -16]]}
   [0x87 0x57 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx 15]]}
   [0x87 0x58 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x87 0x58 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/si -16]]}
   [0x87 0x58 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/si 15]]}
   [0x87 0x59 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x87 0x59 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/di -16]]}
   [0x87 0x59 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/di 15]]}
   [0x87 0x5a 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x87 0x5a 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/si -16]]}
   [0x87 0x5a 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/si 15]]}
   [0x87 0x5b 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x87 0x5b 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/di -16]]}
   [0x87 0x5b 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/di 15]]}
   [0x87 0x5c 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/si]]}
   [0x87 0x5c 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/si -16]]}
   [0x87 0x5c 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/si 15]]}
   [0x87 0x5d 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/di]]}
   [0x87 0x5d 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/di -16]]}
   [0x87 0x5d 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/di 15]]}
   [0x87 0x5e 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp]]}
   [0x87 0x5e 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp -16]]}
   [0x87 0x5e 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp 15]]}
   [0x87 0x5f 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx]]}
   [0x87 0x5f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx -16]]}
   [0x87 0x5f 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx 15]]}
   [0x87 0x60 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x87 0x60 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/si -16]]}
   [0x87 0x60 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/si 15]]}
   [0x87 0x61 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x87 0x61 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/di -16]]}
   [0x87 0x61 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/di 15]]}
   [0x87 0x62 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x87 0x62 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/si -16]]}
   [0x87 0x62 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/si 15]]}
   [0x87 0x63 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x87 0x63 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/di -16]]}
   [0x87 0x63 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/di 15]]}
   [0x87 0x64 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/si]]}
   [0x87 0x64 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/si -16]]}
   [0x87 0x64 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/si 15]]}
   [0x87 0x65 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/di]]}
   [0x87 0x65 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/di -16]]}
   [0x87 0x65 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/di 15]]}
   [0x87 0x66 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp]]}
   [0x87 0x66 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp -16]]}
   [0x87 0x66 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp 15]]}
   [0x87 0x67 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx]]}
   [0x87 0x67 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx -16]]}
   [0x87 0x67 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx 15]]}
   [0x87 0x68 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x87 0x68 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/si -16]]}
   [0x87 0x68 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/si 15]]}
   [0x87 0x69 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x87 0x69 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/di -16]]}
   [0x87 0x69 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/di 15]]}
   [0x87 0x6a 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x87 0x6a 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/si -16]]}
   [0x87 0x6a 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/si 15]]}
   [0x87 0x6b 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x87 0x6b 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/di -16]]}
   [0x87 0x6b 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/di 15]]}
   [0x87 0x6c 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/si]]}
   [0x87 0x6c 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/si -16]]}
   [0x87 0x6c 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/si 15]]}
   [0x87 0x6d 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/di]]}
   [0x87 0x6d 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/di -16]]}
   [0x87 0x6d 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/di 15]]}
   [0x87 0x6e 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp]]}
   [0x87 0x6e 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp -16]]}
   [0x87 0x6e 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp 15]]}
   [0x87 0x6f 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx]]}
   [0x87 0x6f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx -16]]}
   [0x87 0x6f 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx 15]]}
   [0x87 0x70 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x87 0x70 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/si -16]]}
   [0x87 0x70 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/si 15]]}
   [0x87 0x71 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x87 0x71 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/di -16]]}
   [0x87 0x71 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/di 15]]}
   [0x87 0x72 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x87 0x72 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/si -16]]}
   [0x87 0x72 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/si 15]]}
   [0x87 0x73 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x87 0x73 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/di -16]]}
   [0x87 0x73 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/di 15]]}
   [0x87 0x74 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/si]]}
   [0x87 0x74 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/si -16]]}
   [0x87 0x74 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/si 15]]}
   [0x87 0x75 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/di]]}
   [0x87 0x75 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/di -16]]}
   [0x87 0x75 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/di 15]]}
   [0x87 0x76 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp]]}
   [0x87 0x76 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp -16]]}
   [0x87 0x76 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp 15]]}
   [0x87 0x77 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx]]}
   [0x87 0x77 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx -16]]}
   [0x87 0x77 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx 15]]}
   [0x87 0x78 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x87 0x78 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/si -16]]}
   [0x87 0x78 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/si 15]]}
   [0x87 0x79 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x87 0x79 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/di -16]]}
   [0x87 0x79 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/di 15]]}
   [0x87 0x7a 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x87 0x7a 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/si -16]]}
   [0x87 0x7a 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/si 15]]}
   [0x87 0x7b 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x87 0x7b 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/di -16]]}
   [0x87 0x7b 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/di 15]]}
   [0x87 0x7c 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/si]]}
   [0x87 0x7c 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/si -16]]}
   [0x87 0x7c 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/si 15]]}
   [0x87 0x7d 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/di]]}
   [0x87 0x7d 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/di -16]]}
   [0x87 0x7d 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/di 15]]}
   [0x87 0x7e 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp]]}
   [0x87 0x7e 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp -16]]}
   [0x87 0x7e 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp 15]]}
   [0x87 0x7f 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx]]}
   [0x87 0x7f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx -16]]}
   [0x87 0x7f 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx 15]]}
   [0x87 0x80 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x87 0x80 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/si 4080]]}
   [0x87 0x80 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/si -4081]]}
   [0x87 0x81 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x87 0x81 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/di 4080]]}
   [0x87 0x81 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx ::r/di -4081]]}
   [0x87 0x82 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x87 0x82 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/si 4080]]}
   [0x87 0x82 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/si -4081]]}
   [0x87 0x83 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x87 0x83 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/di 4080]]}
   [0x87 0x83 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp ::r/di -4081]]}
   [0x87 0x84 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/si]]}
   [0x87 0x84 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/si 4080]]}
   [0x87 0x84 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/si -4081]]}
   [0x87 0x85 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/di]]}
   [0x87 0x85 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/di 4080]]}
   [0x87 0x85 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/di -4081]]}
   [0x87 0x86 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp]]}
   [0x87 0x86 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp 4080]]}
   [0x87 0x86 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bp -4081]]}
   [0x87 0x87 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx]]}
   [0x87 0x87 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx 4080]]}
   [0x87 0x87 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/ax [::r/bx -4081]]}
   [0x87 0x88 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x87 0x88 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/si 4080]]}
   [0x87 0x88 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/si -4081]]}
   [0x87 0x89 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x87 0x89 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/di 4080]]}
   [0x87 0x89 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx ::r/di -4081]]}
   [0x87 0x8a 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x87 0x8a 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/si 4080]]}
   [0x87 0x8a 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/si -4081]]}
   [0x87 0x8b 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x87 0x8b 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/di 4080]]}
   [0x87 0x8b 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp ::r/di -4081]]}
   [0x87 0x8c 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/si]]}
   [0x87 0x8c 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/si 4080]]}
   [0x87 0x8c 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/si -4081]]}
   [0x87 0x8d 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/di]]}
   [0x87 0x8d 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/di 4080]]}
   [0x87 0x8d 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/di -4081]]}
   [0x87 0x8e 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp]]}
   [0x87 0x8e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp 4080]]}
   [0x87 0x8e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bp -4081]]}
   [0x87 0x8f 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx]]}
   [0x87 0x8f 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx 4080]]}
   [0x87 0x8f 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/cx [::r/bx -4081]]}
   [0x87 0x90 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x87 0x90 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/si 4080]]}
   [0x87 0x90 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/si -4081]]}
   [0x87 0x91 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x87 0x91 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/di 4080]]}
   [0x87 0x91 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx ::r/di -4081]]}
   [0x87 0x92 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x87 0x92 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/si 4080]]}
   [0x87 0x92 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/si -4081]]}
   [0x87 0x93 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x87 0x93 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/di 4080]]}
   [0x87 0x93 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp ::r/di -4081]]}
   [0x87 0x94 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/si]]}
   [0x87 0x94 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/si 4080]]}
   [0x87 0x94 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/si -4081]]}
   [0x87 0x95 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/di]]}
   [0x87 0x95 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/di 4080]]}
   [0x87 0x95 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/di -4081]]}
   [0x87 0x96 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp]]}
   [0x87 0x96 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp 4080]]}
   [0x87 0x96 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bp -4081]]}
   [0x87 0x97 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx]]}
   [0x87 0x97 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx 4080]]}
   [0x87 0x97 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/dx [::r/bx -4081]]}
   [0x87 0x98 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x87 0x98 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/si 4080]]}
   [0x87 0x98 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/si -4081]]}
   [0x87 0x99 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x87 0x99 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/di 4080]]}
   [0x87 0x99 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx ::r/di -4081]]}
   [0x87 0x9a 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x87 0x9a 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/si 4080]]}
   [0x87 0x9a 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/si -4081]]}
   [0x87 0x9b 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x87 0x9b 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/di 4080]]}
   [0x87 0x9b 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp ::r/di -4081]]}
   [0x87 0x9c 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/si]]}
   [0x87 0x9c 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/si 4080]]}
   [0x87 0x9c 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/si -4081]]}
   [0x87 0x9d 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/di]]}
   [0x87 0x9d 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/di 4080]]}
   [0x87 0x9d 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/di -4081]]}
   [0x87 0x9e 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp]]}
   [0x87 0x9e 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp 4080]]}
   [0x87 0x9e 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bp -4081]]}
   [0x87 0x9f 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx]]}
   [0x87 0x9f 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx 4080]]}
   [0x87 0x9f 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bx [::r/bx -4081]]}
   [0x87 0xa0 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x87 0xa0 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/si 4080]]}
   [0x87 0xa0 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/si -4081]]}
   [0x87 0xa1 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x87 0xa1 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/di 4080]]}
   [0x87 0xa1 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx ::r/di -4081]]}
   [0x87 0xa2 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x87 0xa2 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/si 4080]]}
   [0x87 0xa2 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/si -4081]]}
   [0x87 0xa3 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x87 0xa3 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/di 4080]]}
   [0x87 0xa3 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp ::r/di -4081]]}
   [0x87 0xa4 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/si]]}
   [0x87 0xa4 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/si 4080]]}
   [0x87 0xa4 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/si -4081]]}
   [0x87 0xa5 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/di]]}
   [0x87 0xa5 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/di 4080]]}
   [0x87 0xa5 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/di -4081]]}
   [0x87 0xa6 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp]]}
   [0x87 0xa6 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp 4080]]}
   [0x87 0xa6 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bp -4081]]}
   [0x87 0xa7 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx]]}
   [0x87 0xa7 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx 4080]]}
   [0x87 0xa7 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/sp [::r/bx -4081]]}
   [0x87 0xa8 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x87 0xa8 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/si 4080]]}
   [0x87 0xa8 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/si -4081]]}
   [0x87 0xa9 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x87 0xa9 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/di 4080]]}
   [0x87 0xa9 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx ::r/di -4081]]}
   [0x87 0xaa 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x87 0xaa 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/si 4080]]}
   [0x87 0xaa 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/si -4081]]}
   [0x87 0xab 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x87 0xab 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/di 4080]]}
   [0x87 0xab 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp ::r/di -4081]]}
   [0x87 0xac 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/si]]}
   [0x87 0xac 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/si 4080]]}
   [0x87 0xac 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/si -4081]]}
   [0x87 0xad 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/di]]}
   [0x87 0xad 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/di 4080]]}
   [0x87 0xad 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/di -4081]]}
   [0x87 0xae 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp]]}
   [0x87 0xae 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp 4080]]}
   [0x87 0xae 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bp -4081]]}
   [0x87 0xaf 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx]]}
   [0x87 0xaf 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx 4080]]}
   [0x87 0xaf 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/bp [::r/bx -4081]]}
   [0x87 0xb0 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x87 0xb0 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/si 4080]]}
   [0x87 0xb0 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/si -4081]]}
   [0x87 0xb1 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x87 0xb1 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/di 4080]]}
   [0x87 0xb1 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx ::r/di -4081]]}
   [0x87 0xb2 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x87 0xb2 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/si 4080]]}
   [0x87 0xb2 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/si -4081]]}
   [0x87 0xb3 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x87 0xb3 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/di 4080]]}
   [0x87 0xb3 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp ::r/di -4081]]}
   [0x87 0xb4 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/si]]}
   [0x87 0xb4 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/si 4080]]}
   [0x87 0xb4 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/si -4081]]}
   [0x87 0xb5 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/di]]}
   [0x87 0xb5 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/di 4080]]}
   [0x87 0xb5 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/di -4081]]}
   [0x87 0xb6 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp]]}
   [0x87 0xb6 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp 4080]]}
   [0x87 0xb6 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bp -4081]]}
   [0x87 0xb7 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx]]}
   [0x87 0xb7 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx 4080]]}
   [0x87 0xb7 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si [::r/bx -4081]]}
   [0x87 0xb8 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x87 0xb8 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/si 4080]]}
   [0x87 0xb8 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/si -4081]]}
   [0x87 0xb9 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x87 0xb9 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/di 4080]]}
   [0x87 0xb9 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx ::r/di -4081]]}
   [0x87 0xba 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x87 0xba 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/si 4080]]}
   [0x87 0xba 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/si -4081]]}
   [0x87 0xbb 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x87 0xbb 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/di 4080]]}
   [0x87 0xbb 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp ::r/di -4081]]}
   [0x87 0xbc 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/si]]}
   [0x87 0xbc 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/si 4080]]}
   [0x87 0xbc 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/si -4081]]}
   [0x87 0xbd 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/di]]}
   [0x87 0xbd 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/di 4080]]}
   [0x87 0xbd 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/di -4081]]}
   [0x87 0xbe 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp]]}
   [0x87 0xbe 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp 4080]]}
   [0x87 0xbe 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bp -4081]]}
   [0x87 0xbf 0x00 0x00] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx]]}
   [0x87 0xbf 0xf0 0x0f] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx 4080]]}
   [0x87 0xbf 0x0f 0xf0] {::i/tag ::i/xchg, ::i/args [::r/di [::r/bx -4081]]}
   [0x87 0xc0] {::i/tag ::i/xchg, ::i/args [::r/ax ::r/ax]}
   [0x87 0xc1] {::i/tag ::i/xchg, ::i/args [::r/ax ::r/cx]}
   [0x87 0xc2] {::i/tag ::i/xchg, ::i/args [::r/ax ::r/dx]}
   [0x87 0xc3] {::i/tag ::i/xchg, ::i/args [::r/ax ::r/bx]}
   [0x87 0xc4] {::i/tag ::i/xchg, ::i/args [::r/ax ::r/sp]}
   [0x87 0xc5] {::i/tag ::i/xchg, ::i/args [::r/ax ::r/bp]}
   [0x87 0xc6] {::i/tag ::i/xchg, ::i/args [::r/ax ::r/si]}
   [0x87 0xc7] {::i/tag ::i/xchg, ::i/args [::r/ax ::r/di]}
   [0x87 0xc8] {::i/tag ::i/xchg, ::i/args [::r/cx ::r/ax]}
   [0x87 0xc9] {::i/tag ::i/xchg, ::i/args [::r/cx ::r/cx]}
   [0x87 0xca] {::i/tag ::i/xchg, ::i/args [::r/cx ::r/dx]}
   [0x87 0xcb] {::i/tag ::i/xchg, ::i/args [::r/cx ::r/bx]}
   [0x87 0xcc] {::i/tag ::i/xchg, ::i/args [::r/cx ::r/sp]}
   [0x87 0xcd] {::i/tag ::i/xchg, ::i/args [::r/cx ::r/bp]}
   [0x87 0xce] {::i/tag ::i/xchg, ::i/args [::r/cx ::r/si]}
   [0x87 0xcf] {::i/tag ::i/xchg, ::i/args [::r/cx ::r/di]}
   [0x87 0xd0] {::i/tag ::i/xchg, ::i/args [::r/dx ::r/ax]}
   [0x87 0xd1] {::i/tag ::i/xchg, ::i/args [::r/dx ::r/cx]}
   [0x87 0xd2] {::i/tag ::i/xchg, ::i/args [::r/dx ::r/dx]}
   [0x87 0xd3] {::i/tag ::i/xchg, ::i/args [::r/dx ::r/bx]}
   [0x87 0xd4] {::i/tag ::i/xchg, ::i/args [::r/dx ::r/sp]}
   [0x87 0xd5] {::i/tag ::i/xchg, ::i/args [::r/dx ::r/bp]}
   [0x87 0xd6] {::i/tag ::i/xchg, ::i/args [::r/dx ::r/si]}
   [0x87 0xd7] {::i/tag ::i/xchg, ::i/args [::r/dx ::r/di]}
   [0x87 0xd8] {::i/tag ::i/xchg, ::i/args [::r/bx ::r/ax]}
   [0x87 0xd9] {::i/tag ::i/xchg, ::i/args [::r/bx ::r/cx]}
   [0x87 0xda] {::i/tag ::i/xchg, ::i/args [::r/bx ::r/dx]}
   [0x87 0xdb] {::i/tag ::i/xchg, ::i/args [::r/bx ::r/bx]}
   [0x87 0xdc] {::i/tag ::i/xchg, ::i/args [::r/bx ::r/sp]}
   [0x87 0xdd] {::i/tag ::i/xchg, ::i/args [::r/bx ::r/bp]}
   [0x87 0xde] {::i/tag ::i/xchg, ::i/args [::r/bx ::r/si]}
   [0x87 0xdf] {::i/tag ::i/xchg, ::i/args [::r/bx ::r/di]}
   [0x87 0xe0] {::i/tag ::i/xchg, ::i/args [::r/sp ::r/ax]}
   [0x87 0xe1] {::i/tag ::i/xchg, ::i/args [::r/sp ::r/cx]}
   [0x87 0xe2] {::i/tag ::i/xchg, ::i/args [::r/sp ::r/dx]}
   [0x87 0xe3] {::i/tag ::i/xchg, ::i/args [::r/sp ::r/bx]}
   [0x87 0xe4] {::i/tag ::i/xchg, ::i/args [::r/sp ::r/sp]}
   [0x87 0xe5] {::i/tag ::i/xchg, ::i/args [::r/sp ::r/bp]}
   [0x87 0xe6] {::i/tag ::i/xchg, ::i/args [::r/sp ::r/si]}
   [0x87 0xe7] {::i/tag ::i/xchg, ::i/args [::r/sp ::r/di]}
   [0x87 0xe8] {::i/tag ::i/xchg, ::i/args [::r/bp ::r/ax]}
   [0x87 0xe9] {::i/tag ::i/xchg, ::i/args [::r/bp ::r/cx]}
   [0x87 0xea] {::i/tag ::i/xchg, ::i/args [::r/bp ::r/dx]}
   [0x87 0xeb] {::i/tag ::i/xchg, ::i/args [::r/bp ::r/bx]}
   [0x87 0xec] {::i/tag ::i/xchg, ::i/args [::r/bp ::r/sp]}
   [0x87 0xed] {::i/tag ::i/xchg, ::i/args [::r/bp ::r/bp]}
   [0x87 0xee] {::i/tag ::i/xchg, ::i/args [::r/bp ::r/si]}
   [0x87 0xef] {::i/tag ::i/xchg, ::i/args [::r/bp ::r/di]}
   [0x87 0xf0] {::i/tag ::i/xchg, ::i/args [::r/si ::r/ax]}
   [0x87 0xf1] {::i/tag ::i/xchg, ::i/args [::r/si ::r/cx]}
   [0x87 0xf2] {::i/tag ::i/xchg, ::i/args [::r/si ::r/dx]}
   [0x87 0xf3] {::i/tag ::i/xchg, ::i/args [::r/si ::r/bx]}
   [0x87 0xf4] {::i/tag ::i/xchg, ::i/args [::r/si ::r/sp]}
   [0x87 0xf5] {::i/tag ::i/xchg, ::i/args [::r/si ::r/bp]}
   [0x87 0xf6] {::i/tag ::i/xchg, ::i/args [::r/si ::r/si]}
   [0x87 0xf7] {::i/tag ::i/xchg, ::i/args [::r/si ::r/di]}
   [0x87 0xf8] {::i/tag ::i/xchg, ::i/args [::r/di ::r/ax]}
   [0x87 0xf9] {::i/tag ::i/xchg, ::i/args [::r/di ::r/cx]}
   [0x87 0xfa] {::i/tag ::i/xchg, ::i/args [::r/di ::r/dx]}
   [0x87 0xfb] {::i/tag ::i/xchg, ::i/args [::r/di ::r/bx]}
   [0x87 0xfc] {::i/tag ::i/xchg, ::i/args [::r/di ::r/sp]}
   [0x87 0xfd] {::i/tag ::i/xchg, ::i/args [::r/di ::r/bp]}
   [0x87 0xfe] {::i/tag ::i/xchg, ::i/args [::r/di ::r/si]}
   [0x87 0xff] {::i/tag ::i/xchg, ::i/args [::r/di ::r/di]}})
