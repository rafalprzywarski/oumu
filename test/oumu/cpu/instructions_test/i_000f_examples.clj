(ns oumu.cpu.instructions-test.i-000f-examples
    (:require [oumu.cpu.instructions :as i]
              [oumu.cpu.registers :as r]))


(def decode-examples-0x000f
  {[0x0f 0x00 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x01] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x02] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x03] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x04] {::i/tag ::i/sldt, ::i/args [[::r/si]]}
   [0x0f 0x00 0x05] {::i/tag ::i/sldt, ::i/args [[::r/di]]}
   [0x0f 0x00 0x06 0xf0 0x0f] {::i/tag ::i/sldt, ::i/args [[0x0ff0]]}
   [0x0f 0x00 0x06 0x0f 0xf0] {::i/tag ::i/sldt, ::i/args [[0xf00f]]}
   [0x0f 0x00 0x07] {::i/tag ::i/sldt, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x08] {::i/tag ::i/str, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x09] {::i/tag ::i/str, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x0a] {::i/tag ::i/str, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x0b] {::i/tag ::i/str, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x0c] {::i/tag ::i/str, ::i/args [[::r/si]]}
   [0x0f 0x00 0x0d] {::i/tag ::i/str, ::i/args [[::r/di]]}
   [0x0f 0x00 0x0e 0xf0 0x0f] {::i/tag ::i/str, ::i/args [[0x0ff0]]}
   [0x0f 0x00 0x0e 0x0f 0xf0] {::i/tag ::i/str, ::i/args [[0xf00f]]}
   [0x0f 0x00 0x0f] {::i/tag ::i/str, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x10] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x11] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x12] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x13] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x14] {::i/tag ::i/lldt, ::i/args [[::r/si]]}
   [0x0f 0x00 0x15] {::i/tag ::i/lldt, ::i/args [[::r/di]]}
   [0x0f 0x00 0x16 0xf0 0x0f] {::i/tag ::i/lldt, ::i/args [[0x0ff0]]}
   [0x0f 0x00 0x16 0x0f 0xf0] {::i/tag ::i/lldt, ::i/args [[0xf00f]]}
   [0x0f 0x00 0x17] {::i/tag ::i/lldt, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x18] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x19] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x1a] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x1b] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x1c] {::i/tag ::i/ltr, ::i/args [[::r/si]]}
   [0x0f 0x00 0x1d] {::i/tag ::i/ltr, ::i/args [[::r/di]]}
   [0x0f 0x00 0x1e 0xf0 0x0f] {::i/tag ::i/ltr, ::i/args [[0x0ff0]]}
   [0x0f 0x00 0x1e 0x0f 0xf0] {::i/tag ::i/ltr, ::i/args [[0xf00f]]}
   [0x0f 0x00 0x1f] {::i/tag ::i/ltr, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x20] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x21] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x22] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x23] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x24] {::i/tag ::i/verr, ::i/args [[::r/si]]}
   [0x0f 0x00 0x25] {::i/tag ::i/verr, ::i/args [[::r/di]]}
   [0x0f 0x00 0x26 0xf0 0x0f] {::i/tag ::i/verr, ::i/args [[0x0ff0]]}
   [0x0f 0x00 0x26 0x0f 0xf0] {::i/tag ::i/verr, ::i/args [[0xf00f]]}
   [0x0f 0x00 0x27] {::i/tag ::i/verr, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x28] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x29] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x2a] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x2b] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x2c] {::i/tag ::i/verw, ::i/args [[::r/si]]}
   [0x0f 0x00 0x2d] {::i/tag ::i/verw, ::i/args [[::r/di]]}
   [0x0f 0x00 0x2e 0xf0 0x0f] {::i/tag ::i/verw, ::i/args [[0x0ff0]]}
   [0x0f 0x00 0x2e 0x0f 0xf0] {::i/tag ::i/verw, ::i/args [[0xf00f]]}
   [0x0f 0x00 0x2f] {::i/tag ::i/verw, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x30] nil
   [0x0f 0x00 0x31] nil
   [0x0f 0x00 0x32] nil
   [0x0f 0x00 0x33] nil
   [0x0f 0x00 0x34] nil
   [0x0f 0x00 0x35] nil
   [0x0f 0x00 0x36] nil
   [0x0f 0x00 0x37] nil
   [0x0f 0x00 0x38] nil
   [0x0f 0x00 0x39] nil
   [0x0f 0x00 0x3a] nil
   [0x0f 0x00 0x3b] nil
   [0x0f 0x00 0x3c] nil
   [0x0f 0x00 0x3d] nil
   [0x0f 0x00 0x3e] nil
   [0x0f 0x00 0x3f] nil
   [0x0f 0x00 0x40 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x40 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/si 15]]}
   [0x0f 0x00 0x40 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/si -16]]}
   [0x0f 0x00 0x41 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x41 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/di 15]]}
   [0x0f 0x00 0x41 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/di -16]]}
   [0x0f 0x00 0x42 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x42 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/si 15]]}
   [0x0f 0x00 0x42 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/si -16]]}
   [0x0f 0x00 0x43 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x43 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/di 15]]}
   [0x0f 0x00 0x43 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/di -16]]}
   [0x0f 0x00 0x44 0x00] {::i/tag ::i/sldt, ::i/args [[::r/si]]}
   [0x0f 0x00 0x44 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/si 15]]}
   [0x0f 0x00 0x44 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/si -16]]}
   [0x0f 0x00 0x45 0x00] {::i/tag ::i/sldt, ::i/args [[::r/di]]}
   [0x0f 0x00 0x45 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/di 15]]}
   [0x0f 0x00 0x45 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/di -16]]}
   [0x0f 0x00 0x46 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bp]]}
   [0x0f 0x00 0x46 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bp 15]]}
   [0x0f 0x00 0x46 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bp -16]]}
   [0x0f 0x00 0x47 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x47 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bx 15]]}
   [0x0f 0x00 0x47 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bx -16]]}
   [0x0f 0x00 0x48 0x00] {::i/tag ::i/str, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x48 0x0f] {::i/tag ::i/str, ::i/args [[::r/bx ::r/si 15]]}
   [0x0f 0x00 0x48 0xf0] {::i/tag ::i/str, ::i/args [[::r/bx ::r/si -16]]}
   [0x0f 0x00 0x49 0x00] {::i/tag ::i/str, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x49 0x0f] {::i/tag ::i/str, ::i/args [[::r/bx ::r/di 15]]}
   [0x0f 0x00 0x49 0xf0] {::i/tag ::i/str, ::i/args [[::r/bx ::r/di -16]]}
   [0x0f 0x00 0x4a 0x00] {::i/tag ::i/str, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x4a 0x0f] {::i/tag ::i/str, ::i/args [[::r/bp ::r/si 15]]}
   [0x0f 0x00 0x4a 0xf0] {::i/tag ::i/str, ::i/args [[::r/bp ::r/si -16]]}
   [0x0f 0x00 0x4b 0x00] {::i/tag ::i/str, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x4b 0x0f] {::i/tag ::i/str, ::i/args [[::r/bp ::r/di 15]]}
   [0x0f 0x00 0x4b 0xf0] {::i/tag ::i/str, ::i/args [[::r/bp ::r/di -16]]}
   [0x0f 0x00 0x4c 0x00] {::i/tag ::i/str, ::i/args [[::r/si]]}
   [0x0f 0x00 0x4c 0x0f] {::i/tag ::i/str, ::i/args [[::r/si 15]]}
   [0x0f 0x00 0x4c 0xf0] {::i/tag ::i/str, ::i/args [[::r/si -16]]}
   [0x0f 0x00 0x4d 0x00] {::i/tag ::i/str, ::i/args [[::r/di]]}
   [0x0f 0x00 0x4d 0x0f] {::i/tag ::i/str, ::i/args [[::r/di 15]]}
   [0x0f 0x00 0x4d 0xf0] {::i/tag ::i/str, ::i/args [[::r/di -16]]}
   [0x0f 0x00 0x4e 0x00] {::i/tag ::i/str, ::i/args [[::r/bp]]}
   [0x0f 0x00 0x4e 0x0f] {::i/tag ::i/str, ::i/args [[::r/bp 15]]}
   [0x0f 0x00 0x4e 0xf0] {::i/tag ::i/str, ::i/args [[::r/bp -16]]}
   [0x0f 0x00 0x4f 0x00] {::i/tag ::i/str, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x4f 0x0f] {::i/tag ::i/str, ::i/args [[::r/bx 15]]}
   [0x0f 0x00 0x4f 0xf0] {::i/tag ::i/str, ::i/args [[::r/bx -16]]}
   [0x0f 0x00 0x50 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x50 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/si 15]]}
   [0x0f 0x00 0x50 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/si -16]]}
   [0x0f 0x00 0x51 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x51 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/di 15]]}
   [0x0f 0x00 0x51 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/di -16]]}
   [0x0f 0x00 0x52 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x52 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/si 15]]}
   [0x0f 0x00 0x52 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/si -16]]}
   [0x0f 0x00 0x53 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x53 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/di 15]]}
   [0x0f 0x00 0x53 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/di -16]]}
   [0x0f 0x00 0x54 0x00] {::i/tag ::i/lldt, ::i/args [[::r/si]]}
   [0x0f 0x00 0x54 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/si 15]]}
   [0x0f 0x00 0x54 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/si -16]]}
   [0x0f 0x00 0x55 0x00] {::i/tag ::i/lldt, ::i/args [[::r/di]]}
   [0x0f 0x00 0x55 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/di 15]]}
   [0x0f 0x00 0x55 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/di -16]]}
   [0x0f 0x00 0x56 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bp]]}
   [0x0f 0x00 0x56 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bp 15]]}
   [0x0f 0x00 0x56 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bp -16]]}
   [0x0f 0x00 0x57 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x57 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bx 15]]}
   [0x0f 0x00 0x57 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bx -16]]}
   [0x0f 0x00 0x58 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x58 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/si 15]]}
   [0x0f 0x00 0x58 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/si -16]]}
   [0x0f 0x00 0x59 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x59 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/di 15]]}
   [0x0f 0x00 0x59 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/di -16]]}
   [0x0f 0x00 0x5a 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x5a 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/si 15]]}
   [0x0f 0x00 0x5a 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/si -16]]}
   [0x0f 0x00 0x5b 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x5b 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/di 15]]}
   [0x0f 0x00 0x5b 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/di -16]]}
   [0x0f 0x00 0x5c 0x00] {::i/tag ::i/ltr, ::i/args [[::r/si]]}
   [0x0f 0x00 0x5c 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/si 15]]}
   [0x0f 0x00 0x5c 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/si -16]]}
   [0x0f 0x00 0x5d 0x00] {::i/tag ::i/ltr, ::i/args [[::r/di]]}
   [0x0f 0x00 0x5d 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/di 15]]}
   [0x0f 0x00 0x5d 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/di -16]]}
   [0x0f 0x00 0x5e 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bp]]}
   [0x0f 0x00 0x5e 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bp 15]]}
   [0x0f 0x00 0x5e 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bp -16]]}
   [0x0f 0x00 0x5f 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x5f 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bx 15]]}
   [0x0f 0x00 0x5f 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bx -16]]}
   [0x0f 0x00 0x60 0x00] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x60 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/si 15]]}
   [0x0f 0x00 0x60 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/si -16]]}
   [0x0f 0x00 0x61 0x00] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x61 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/di 15]]}
   [0x0f 0x00 0x61 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/di -16]]}
   [0x0f 0x00 0x62 0x00] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x62 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/si 15]]}
   [0x0f 0x00 0x62 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/si -16]]}
   [0x0f 0x00 0x63 0x00] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x63 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/di 15]]}
   [0x0f 0x00 0x63 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/di -16]]}
   [0x0f 0x00 0x64 0x00] {::i/tag ::i/verr, ::i/args [[::r/si]]}
   [0x0f 0x00 0x64 0x0f] {::i/tag ::i/verr, ::i/args [[::r/si 15]]}
   [0x0f 0x00 0x64 0xf0] {::i/tag ::i/verr, ::i/args [[::r/si -16]]}
   [0x0f 0x00 0x65 0x00] {::i/tag ::i/verr, ::i/args [[::r/di]]}
   [0x0f 0x00 0x65 0x0f] {::i/tag ::i/verr, ::i/args [[::r/di 15]]}
   [0x0f 0x00 0x65 0xf0] {::i/tag ::i/verr, ::i/args [[::r/di -16]]}
   [0x0f 0x00 0x66 0x00] {::i/tag ::i/verr, ::i/args [[::r/bp]]}
   [0x0f 0x00 0x66 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bp 15]]}
   [0x0f 0x00 0x66 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bp -16]]}
   [0x0f 0x00 0x67 0x00] {::i/tag ::i/verr, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x67 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bx 15]]}
   [0x0f 0x00 0x67 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bx -16]]}
   [0x0f 0x00 0x68 0x00] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x68 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/si 15]]}
   [0x0f 0x00 0x68 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/si -16]]}
   [0x0f 0x00 0x69 0x00] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x69 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/di 15]]}
   [0x0f 0x00 0x69 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/di -16]]}
   [0x0f 0x00 0x6a 0x00] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x6a 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/si 15]]}
   [0x0f 0x00 0x6a 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/si -16]]}
   [0x0f 0x00 0x6b 0x00] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x6b 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/di 15]]}
   [0x0f 0x00 0x6b 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/di -16]]}
   [0x0f 0x00 0x6c 0x00] {::i/tag ::i/verw, ::i/args [[::r/si]]}
   [0x0f 0x00 0x6c 0x0f] {::i/tag ::i/verw, ::i/args [[::r/si 15]]}
   [0x0f 0x00 0x6c 0xf0] {::i/tag ::i/verw, ::i/args [[::r/si -16]]}
   [0x0f 0x00 0x6d 0x00] {::i/tag ::i/verw, ::i/args [[::r/di]]}
   [0x0f 0x00 0x6d 0x0f] {::i/tag ::i/verw, ::i/args [[::r/di 15]]}
   [0x0f 0x00 0x6d 0xf0] {::i/tag ::i/verw, ::i/args [[::r/di -16]]}
   [0x0f 0x00 0x6e 0x00] {::i/tag ::i/verw, ::i/args [[::r/bp]]}
   [0x0f 0x00 0x6e 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bp 15]]}
   [0x0f 0x00 0x6e 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bp -16]]}
   [0x0f 0x00 0x6f 0x00] {::i/tag ::i/verw, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x6f 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bx 15]]}
   [0x0f 0x00 0x6f 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bx -16]]}
   [0x0f 0x00 0x70] nil
   [0x0f 0x00 0x71] nil
   [0x0f 0x00 0x72] nil
   [0x0f 0x00 0x73] nil
   [0x0f 0x00 0x74] nil
   [0x0f 0x00 0x75] nil
   [0x0f 0x00 0x76] nil
   [0x0f 0x00 0x77] nil
   [0x0f 0x00 0x78] nil
   [0x0f 0x00 0x79] nil
   [0x0f 0x00 0x7a] nil
   [0x0f 0x00 0x7b] nil
   [0x0f 0x00 0x7c] nil
   [0x0f 0x00 0x7d] nil
   [0x0f 0x00 0x7e] nil
   [0x0f 0x00 0x7f] nil
   [0x0f 0x00 0x80 0x00 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x80 0xf0 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/si 4080]]}
   [0x0f 0x00 0x80 0x0f 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/si -4081]]}
   [0x0f 0x00 0x81 0x00 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x81 0xf0 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/di 4080]]}
   [0x0f 0x00 0x81 0x0f 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bx ::r/di -4081]]}
   [0x0f 0x00 0x82 0x00 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x82 0xf0 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/si 4080]]}
   [0x0f 0x00 0x82 0x0f 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/si -4081]]}
   [0x0f 0x00 0x83 0x00 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x83 0xf0 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/di 4080]]}
   [0x0f 0x00 0x83 0x0f 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bp ::r/di -4081]]}
   [0x0f 0x00 0x84 0x00 0x00] {::i/tag ::i/sldt, ::i/args [[::r/si]]}
   [0x0f 0x00 0x84 0xf0 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/si 4080]]}
   [0x0f 0x00 0x84 0x0f 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/si -4081]]}
   [0x0f 0x00 0x85 0x00 0x00] {::i/tag ::i/sldt, ::i/args [[::r/di]]}
   [0x0f 0x00 0x85 0xf0 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/di 4080]]}
   [0x0f 0x00 0x85 0x0f 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/di -4081]]}
   [0x0f 0x00 0x86 0x00 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bp]]}
   [0x0f 0x00 0x86 0xf0 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bp 4080]]}
   [0x0f 0x00 0x86 0x0f 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bp -4081]]}
   [0x0f 0x00 0x87 0x00 0x00] {::i/tag ::i/sldt, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x87 0xf0 0x0f] {::i/tag ::i/sldt, ::i/args [[::r/bx 4080]]}
   [0x0f 0x00 0x87 0x0f 0xf0] {::i/tag ::i/sldt, ::i/args [[::r/bx -4081]]}
   [0x0f 0x00 0x88 0x00 0x00] {::i/tag ::i/str, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x88 0xf0 0x0f] {::i/tag ::i/str, ::i/args [[::r/bx ::r/si 4080]]}
   [0x0f 0x00 0x88 0x0f 0xf0] {::i/tag ::i/str, ::i/args [[::r/bx ::r/si -4081]]}
   [0x0f 0x00 0x89 0x00 0x00] {::i/tag ::i/str, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x89 0xf0 0x0f] {::i/tag ::i/str, ::i/args [[::r/bx ::r/di 4080]]}
   [0x0f 0x00 0x89 0x0f 0xf0] {::i/tag ::i/str, ::i/args [[::r/bx ::r/di -4081]]}
   [0x0f 0x00 0x8a 0x00 0x00] {::i/tag ::i/str, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x8a 0xf0 0x0f] {::i/tag ::i/str, ::i/args [[::r/bp ::r/si 4080]]}
   [0x0f 0x00 0x8a 0x0f 0xf0] {::i/tag ::i/str, ::i/args [[::r/bp ::r/si -4081]]}
   [0x0f 0x00 0x8b 0x00 0x00] {::i/tag ::i/str, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x8b 0xf0 0x0f] {::i/tag ::i/str, ::i/args [[::r/bp ::r/di 4080]]}
   [0x0f 0x00 0x8b 0x0f 0xf0] {::i/tag ::i/str, ::i/args [[::r/bp ::r/di -4081]]}
   [0x0f 0x00 0x8c 0x00 0x00] {::i/tag ::i/str, ::i/args [[::r/si]]}
   [0x0f 0x00 0x8c 0xf0 0x0f] {::i/tag ::i/str, ::i/args [[::r/si 4080]]}
   [0x0f 0x00 0x8c 0x0f 0xf0] {::i/tag ::i/str, ::i/args [[::r/si -4081]]}
   [0x0f 0x00 0x8d 0x00 0x00] {::i/tag ::i/str, ::i/args [[::r/di]]}
   [0x0f 0x00 0x8d 0xf0 0x0f] {::i/tag ::i/str, ::i/args [[::r/di 4080]]}
   [0x0f 0x00 0x8d 0x0f 0xf0] {::i/tag ::i/str, ::i/args [[::r/di -4081]]}
   [0x0f 0x00 0x8e 0x00 0x00] {::i/tag ::i/str, ::i/args [[::r/bp]]}
   [0x0f 0x00 0x8e 0xf0 0x0f] {::i/tag ::i/str, ::i/args [[::r/bp 4080]]}
   [0x0f 0x00 0x8e 0x0f 0xf0] {::i/tag ::i/str, ::i/args [[::r/bp -4081]]}
   [0x0f 0x00 0x8f 0x00 0x00] {::i/tag ::i/str, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x8f 0xf0 0x0f] {::i/tag ::i/str, ::i/args [[::r/bx 4080]]}
   [0x0f 0x00 0x8f 0x0f 0xf0] {::i/tag ::i/str, ::i/args [[::r/bx -4081]]}
   [0x0f 0x00 0x90 0x00 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x90 0xf0 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/si 4080]]}
   [0x0f 0x00 0x90 0x0f 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/si -4081]]}
   [0x0f 0x00 0x91 0x00 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x91 0xf0 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/di 4080]]}
   [0x0f 0x00 0x91 0x0f 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bx ::r/di -4081]]}
   [0x0f 0x00 0x92 0x00 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x92 0xf0 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/si 4080]]}
   [0x0f 0x00 0x92 0x0f 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/si -4081]]}
   [0x0f 0x00 0x93 0x00 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x93 0xf0 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/di 4080]]}
   [0x0f 0x00 0x93 0x0f 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bp ::r/di -4081]]}
   [0x0f 0x00 0x94 0x00 0x00] {::i/tag ::i/lldt, ::i/args [[::r/si]]}
   [0x0f 0x00 0x94 0xf0 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/si 4080]]}
   [0x0f 0x00 0x94 0x0f 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/si -4081]]}
   [0x0f 0x00 0x95 0x00 0x00] {::i/tag ::i/lldt, ::i/args [[::r/di]]}
   [0x0f 0x00 0x95 0xf0 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/di 4080]]}
   [0x0f 0x00 0x95 0x0f 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/di -4081]]}
   [0x0f 0x00 0x96 0x00 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bp]]}
   [0x0f 0x00 0x96 0xf0 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bp 4080]]}
   [0x0f 0x00 0x96 0x0f 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bp -4081]]}
   [0x0f 0x00 0x97 0x00 0x00] {::i/tag ::i/lldt, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x97 0xf0 0x0f] {::i/tag ::i/lldt, ::i/args [[::r/bx 4080]]}
   [0x0f 0x00 0x97 0x0f 0xf0] {::i/tag ::i/lldt, ::i/args [[::r/bx -4081]]}
   [0x0f 0x00 0x98 0x00 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0x98 0xf0 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/si 4080]]}
   [0x0f 0x00 0x98 0x0f 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/si -4081]]}
   [0x0f 0x00 0x99 0x00 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0x99 0xf0 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/di 4080]]}
   [0x0f 0x00 0x99 0x0f 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bx ::r/di -4081]]}
   [0x0f 0x00 0x9a 0x00 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0x9a 0xf0 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/si 4080]]}
   [0x0f 0x00 0x9a 0x0f 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/si -4081]]}
   [0x0f 0x00 0x9b 0x00 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0x9b 0xf0 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/di 4080]]}
   [0x0f 0x00 0x9b 0x0f 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bp ::r/di -4081]]}
   [0x0f 0x00 0x9c 0x00 0x00] {::i/tag ::i/ltr, ::i/args [[::r/si]]}
   [0x0f 0x00 0x9c 0xf0 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/si 4080]]}
   [0x0f 0x00 0x9c 0x0f 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/si -4081]]}
   [0x0f 0x00 0x9d 0x00 0x00] {::i/tag ::i/ltr, ::i/args [[::r/di]]}
   [0x0f 0x00 0x9d 0xf0 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/di 4080]]}
   [0x0f 0x00 0x9d 0x0f 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/di -4081]]}
   [0x0f 0x00 0x9e 0x00 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bp]]}
   [0x0f 0x00 0x9e 0xf0 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bp 4080]]}
   [0x0f 0x00 0x9e 0x0f 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bp -4081]]}
   [0x0f 0x00 0x9f 0x00 0x00] {::i/tag ::i/ltr, ::i/args [[::r/bx]]}
   [0x0f 0x00 0x9f 0xf0 0x0f] {::i/tag ::i/ltr, ::i/args [[::r/bx 4080]]}
   [0x0f 0x00 0x9f 0x0f 0xf0] {::i/tag ::i/ltr, ::i/args [[::r/bx -4081]]}
   [0x0f 0x00 0xa0 0x00 0x00] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0xa0 0xf0 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/si 4080]]}
   [0x0f 0x00 0xa0 0x0f 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/si -4081]]}
   [0x0f 0x00 0xa1 0x00 0x00] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0xa1 0xf0 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/di 4080]]}
   [0x0f 0x00 0xa1 0x0f 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bx ::r/di -4081]]}
   [0x0f 0x00 0xa2 0x00 0x00] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0xa2 0xf0 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/si 4080]]}
   [0x0f 0x00 0xa2 0x0f 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/si -4081]]}
   [0x0f 0x00 0xa3 0x00 0x00] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0xa3 0xf0 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/di 4080]]}
   [0x0f 0x00 0xa3 0x0f 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bp ::r/di -4081]]}
   [0x0f 0x00 0xa4 0x00 0x00] {::i/tag ::i/verr, ::i/args [[::r/si]]}
   [0x0f 0x00 0xa4 0xf0 0x0f] {::i/tag ::i/verr, ::i/args [[::r/si 4080]]}
   [0x0f 0x00 0xa4 0x0f 0xf0] {::i/tag ::i/verr, ::i/args [[::r/si -4081]]}
   [0x0f 0x00 0xa5 0x00 0x00] {::i/tag ::i/verr, ::i/args [[::r/di]]}
   [0x0f 0x00 0xa5 0xf0 0x0f] {::i/tag ::i/verr, ::i/args [[::r/di 4080]]}
   [0x0f 0x00 0xa5 0x0f 0xf0] {::i/tag ::i/verr, ::i/args [[::r/di -4081]]}
   [0x0f 0x00 0xa6 0x00 0x00] {::i/tag ::i/verr, ::i/args [[::r/bp]]}
   [0x0f 0x00 0xa6 0xf0 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bp 4080]]}
   [0x0f 0x00 0xa6 0x0f 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bp -4081]]}
   [0x0f 0x00 0xa7 0x00 0x00] {::i/tag ::i/verr, ::i/args [[::r/bx]]}
   [0x0f 0x00 0xa7 0xf0 0x0f] {::i/tag ::i/verr, ::i/args [[::r/bx 4080]]}
   [0x0f 0x00 0xa7 0x0f 0xf0] {::i/tag ::i/verr, ::i/args [[::r/bx -4081]]}
   [0x0f 0x00 0xa8 0x00 0x00] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/si]]}
   [0x0f 0x00 0xa8 0xf0 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/si 4080]]}
   [0x0f 0x00 0xa8 0x0f 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/si -4081]]}
   [0x0f 0x00 0xa9 0x00 0x00] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/di]]}
   [0x0f 0x00 0xa9 0xf0 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/di 4080]]}
   [0x0f 0x00 0xa9 0x0f 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bx ::r/di -4081]]}
   [0x0f 0x00 0xaa 0x00 0x00] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/si]]}
   [0x0f 0x00 0xaa 0xf0 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/si 4080]]}
   [0x0f 0x00 0xaa 0x0f 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/si -4081]]}
   [0x0f 0x00 0xab 0x00 0x00] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/di]]}
   [0x0f 0x00 0xab 0xf0 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/di 4080]]}
   [0x0f 0x00 0xab 0x0f 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bp ::r/di -4081]]}
   [0x0f 0x00 0xac 0x00 0x00] {::i/tag ::i/verw, ::i/args [[::r/si]]}
   [0x0f 0x00 0xac 0xf0 0x0f] {::i/tag ::i/verw, ::i/args [[::r/si 4080]]}
   [0x0f 0x00 0xac 0x0f 0xf0] {::i/tag ::i/verw, ::i/args [[::r/si -4081]]}
   [0x0f 0x00 0xad 0x00 0x00] {::i/tag ::i/verw, ::i/args [[::r/di]]}
   [0x0f 0x00 0xad 0xf0 0x0f] {::i/tag ::i/verw, ::i/args [[::r/di 4080]]}
   [0x0f 0x00 0xad 0x0f 0xf0] {::i/tag ::i/verw, ::i/args [[::r/di -4081]]}
   [0x0f 0x00 0xae 0x00 0x00] {::i/tag ::i/verw, ::i/args [[::r/bp]]}
   [0x0f 0x00 0xae 0xf0 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bp 4080]]}
   [0x0f 0x00 0xae 0x0f 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bp -4081]]}
   [0x0f 0x00 0xaf 0x00 0x00] {::i/tag ::i/verw, ::i/args [[::r/bx]]}
   [0x0f 0x00 0xaf 0xf0 0x0f] {::i/tag ::i/verw, ::i/args [[::r/bx 4080]]}
   [0x0f 0x00 0xaf 0x0f 0xf0] {::i/tag ::i/verw, ::i/args [[::r/bx -4081]]}
   [0x0f 0x00 0xb0] nil
   [0x0f 0x00 0xb1] nil
   [0x0f 0x00 0xb2] nil
   [0x0f 0x00 0xb3] nil
   [0x0f 0x00 0xb4] nil
   [0x0f 0x00 0xb5] nil
   [0x0f 0x00 0xb6] nil
   [0x0f 0x00 0xb7] nil
   [0x0f 0x00 0xb8] nil
   [0x0f 0x00 0xb9] nil
   [0x0f 0x00 0xba] nil
   [0x0f 0x00 0xbb] nil
   [0x0f 0x00 0xbc] nil
   [0x0f 0x00 0xbd] nil
   [0x0f 0x00 0xbe] nil
   [0x0f 0x00 0xbf] nil
   [0x0f 0x00 0xc0] {::i/tag ::i/sldt, ::i/args [::r/ax]}
   [0x0f 0x00 0xc1] {::i/tag ::i/sldt, ::i/args [::r/cx]}
   [0x0f 0x00 0xc2] {::i/tag ::i/sldt, ::i/args [::r/dx]}
   [0x0f 0x00 0xc3] {::i/tag ::i/sldt, ::i/args [::r/bx]}
   [0x0f 0x00 0xc4] {::i/tag ::i/sldt, ::i/args [::r/sp]}
   [0x0f 0x00 0xc5] {::i/tag ::i/sldt, ::i/args [::r/bp]}
   [0x0f 0x00 0xc6] {::i/tag ::i/sldt, ::i/args [::r/si]}
   [0x0f 0x00 0xc7] {::i/tag ::i/sldt, ::i/args [::r/di]}
   [0x0f 0x00 0xc8] {::i/tag ::i/str, ::i/args [::r/ax]}
   [0x0f 0x00 0xc9] {::i/tag ::i/str, ::i/args [::r/cx]}
   [0x0f 0x00 0xca] {::i/tag ::i/str, ::i/args [::r/dx]}
   [0x0f 0x00 0xcb] {::i/tag ::i/str, ::i/args [::r/bx]}
   [0x0f 0x00 0xcc] {::i/tag ::i/str, ::i/args [::r/sp]}
   [0x0f 0x00 0xcd] {::i/tag ::i/str, ::i/args [::r/bp]}
   [0x0f 0x00 0xce] {::i/tag ::i/str, ::i/args [::r/si]}
   [0x0f 0x00 0xcf] {::i/tag ::i/str, ::i/args [::r/di]}
   [0x0f 0x00 0xd0] {::i/tag ::i/lldt, ::i/args [::r/ax]}
   [0x0f 0x00 0xd1] {::i/tag ::i/lldt, ::i/args [::r/cx]}
   [0x0f 0x00 0xd2] {::i/tag ::i/lldt, ::i/args [::r/dx]}
   [0x0f 0x00 0xd3] {::i/tag ::i/lldt, ::i/args [::r/bx]}
   [0x0f 0x00 0xd4] {::i/tag ::i/lldt, ::i/args [::r/sp]}
   [0x0f 0x00 0xd5] {::i/tag ::i/lldt, ::i/args [::r/bp]}
   [0x0f 0x00 0xd6] {::i/tag ::i/lldt, ::i/args [::r/si]}
   [0x0f 0x00 0xd7] {::i/tag ::i/lldt, ::i/args [::r/di]}
   [0x0f 0x00 0xd8] {::i/tag ::i/ltr, ::i/args [::r/ax]}
   [0x0f 0x00 0xd9] {::i/tag ::i/ltr, ::i/args [::r/cx]}
   [0x0f 0x00 0xda] {::i/tag ::i/ltr, ::i/args [::r/dx]}
   [0x0f 0x00 0xdb] {::i/tag ::i/ltr, ::i/args [::r/bx]}
   [0x0f 0x00 0xdc] {::i/tag ::i/ltr, ::i/args [::r/sp]}
   [0x0f 0x00 0xdd] {::i/tag ::i/ltr, ::i/args [::r/bp]}
   [0x0f 0x00 0xde] {::i/tag ::i/ltr, ::i/args [::r/si]}
   [0x0f 0x00 0xdf] {::i/tag ::i/ltr, ::i/args [::r/di]}
   [0x0f 0x00 0xe0] {::i/tag ::i/verr, ::i/args [::r/ax]}
   [0x0f 0x00 0xe1] {::i/tag ::i/verr, ::i/args [::r/cx]}
   [0x0f 0x00 0xe2] {::i/tag ::i/verr, ::i/args [::r/dx]}
   [0x0f 0x00 0xe3] {::i/tag ::i/verr, ::i/args [::r/bx]}
   [0x0f 0x00 0xe4] {::i/tag ::i/verr, ::i/args [::r/sp]}
   [0x0f 0x00 0xe5] {::i/tag ::i/verr, ::i/args [::r/bp]}
   [0x0f 0x00 0xe6] {::i/tag ::i/verr, ::i/args [::r/si]}
   [0x0f 0x00 0xe7] {::i/tag ::i/verr, ::i/args [::r/di]}
   [0x0f 0x00 0xe8] {::i/tag ::i/verw, ::i/args [::r/ax]}
   [0x0f 0x00 0xe9] {::i/tag ::i/verw, ::i/args [::r/cx]}
   [0x0f 0x00 0xea] {::i/tag ::i/verw, ::i/args [::r/dx]}
   [0x0f 0x00 0xeb] {::i/tag ::i/verw, ::i/args [::r/bx]}
   [0x0f 0x00 0xec] {::i/tag ::i/verw, ::i/args [::r/sp]}
   [0x0f 0x00 0xed] {::i/tag ::i/verw, ::i/args [::r/bp]}
   [0x0f 0x00 0xee] {::i/tag ::i/verw, ::i/args [::r/si]}
   [0x0f 0x00 0xef] {::i/tag ::i/verw, ::i/args [::r/di]}
   [0x0f 0x00 0xf0] nil
   [0x0f 0x00 0xf1] nil
   [0x0f 0x00 0xf2] nil
   [0x0f 0x00 0xf3] nil
   [0x0f 0x00 0xf4] nil
   [0x0f 0x00 0xf5] nil
   [0x0f 0x00 0xf6] nil
   [0x0f 0x00 0xf7] nil
   [0x0f 0x00 0xf8] nil
   [0x0f 0x00 0xf9] nil
   [0x0f 0x00 0xfa] nil
   [0x0f 0x00 0xfb] nil
   [0x0f 0x00 0xfc] nil
   [0x0f 0x00 0xfd] nil
   [0x0f 0x00 0xfe] nil
   [0x0f 0x00 0xff] nil})
