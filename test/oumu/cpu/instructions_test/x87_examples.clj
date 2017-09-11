(ns oumu.cpu.instructions-test.x87-examples
    (:require [oumu.cpu.instructions :as i]
              [oumu.cpu.registers :as r]))


(def decode-examples-0xd8
  {[0xd8 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x01] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x02] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x03] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x04] {::i/tag ::i/fadd, ::i/args [[::r/si]]}
   [0xd8 0x05] {::i/tag ::i/fadd, ::i/args [[::r/di]]}
   [0xd8 0x06 0x12 0x34] {::i/tag ::i/fadd, ::i/args [[0x3412]]}
   [0xd8 0x06 0x34 0x12] {::i/tag ::i/fadd, ::i/args [[0x1234]]}
   [0xd8 0x07] {::i/tag ::i/fadd, ::i/args [[::r/bx]]}
   [0xd8 0x08] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x09] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x0a] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x0b] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x0c] {::i/tag ::i/fmul, ::i/args [[::r/si]]}
   [0xd8 0x0d] {::i/tag ::i/fmul, ::i/args [[::r/di]]}
   [0xd8 0x0e 0x12 0x34] {::i/tag ::i/fmul, ::i/args [[0x3412]]}
   [0xd8 0x0e 0x34 0x12] {::i/tag ::i/fmul, ::i/args [[0x1234]]}
   [0xd8 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx]]}
   [0xd8 0x10] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x11] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x12] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x13] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x14] {::i/tag ::i/fcom, ::i/args [[::r/si]]}
   [0xd8 0x15] {::i/tag ::i/fcom, ::i/args [[::r/di]]}
   [0xd8 0x16 0x12 0x34] {::i/tag ::i/fcom, ::i/args [[0x3412]]}
   [0xd8 0x16 0x34 0x12] {::i/tag ::i/fcom, ::i/args [[0x1234]]}
   [0xd8 0x17] {::i/tag ::i/fcom, ::i/args [[::r/bx]]}
   [0xd8 0x18] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x19] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x1a] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x1b] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x1c] {::i/tag ::i/fcomp, ::i/args [[::r/si]]}
   [0xd8 0x1d] {::i/tag ::i/fcomp, ::i/args [[::r/di]]}
   [0xd8 0x1e 0x12 0x34] {::i/tag ::i/fcomp, ::i/args [[0x3412]]}
   [0xd8 0x1e 0x34 0x12] {::i/tag ::i/fcomp, ::i/args [[0x1234]]}
   [0xd8 0x1f] {::i/tag ::i/fcomp, ::i/args [[::r/bx]]}
   [0xd8 0x20] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x21] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x22] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x23] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x24] {::i/tag ::i/fsub, ::i/args [[::r/si]]}
   [0xd8 0x25] {::i/tag ::i/fsub, ::i/args [[::r/di]]}
   [0xd8 0x26 0x12 0x34] {::i/tag ::i/fsub, ::i/args [[0x3412]]}
   [0xd8 0x26 0x34 0x12] {::i/tag ::i/fsub, ::i/args [[0x1234]]}
   [0xd8 0x27] {::i/tag ::i/fsub, ::i/args [[::r/bx]]}
   [0xd8 0x28] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x29] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x2a] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x2b] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x2c] {::i/tag ::i/fsubr, ::i/args [[::r/si]]}
   [0xd8 0x2d] {::i/tag ::i/fsubr, ::i/args [[::r/di]]}
   [0xd8 0x2e 0x12 0x34] {::i/tag ::i/fsubr, ::i/args [[0x3412]]}
   [0xd8 0x2e 0x34 0x12] {::i/tag ::i/fsubr, ::i/args [[0x1234]]}
   [0xd8 0x2f] {::i/tag ::i/fsubr, ::i/args [[::r/bx]]}
   [0xd8 0x30] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x31] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x32] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x33] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x34] {::i/tag ::i/fdiv, ::i/args [[::r/si]]}
   [0xd8 0x35] {::i/tag ::i/fdiv, ::i/args [[::r/di]]}
   [0xd8 0x36 0x12 0x34] {::i/tag ::i/fdiv, ::i/args [[0x3412]]}
   [0xd8 0x36 0x34 0x12] {::i/tag ::i/fdiv, ::i/args [[0x1234]]}
   [0xd8 0x37] {::i/tag ::i/fdiv, ::i/args [[::r/bx]]}
   [0xd8 0x38] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x39] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x3a] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x3b] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x3c] {::i/tag ::i/fdivr, ::i/args [[::r/si]]}
   [0xd8 0x3d] {::i/tag ::i/fdivr, ::i/args [[::r/di]]}
   [0xd8 0x3e 0x12 0x34] {::i/tag ::i/fdivr, ::i/args [[0x3412]]}
   [0xd8 0x3e 0x34 0x12] {::i/tag ::i/fdivr, ::i/args [[0x1234]]}
   [0xd8 0x3f] {::i/tag ::i/fdivr, ::i/args [[::r/bx]]}
   [0xd8 0x40 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x40 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si -16]]}
   [0xd8 0x40 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si 15]]}
   [0xd8 0x41 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x41 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di -16]]}
   [0xd8 0x41 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di 15]]}
   [0xd8 0x42 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x42 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si -16]]}
   [0xd8 0x42 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si 15]]}
   [0xd8 0x43 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x43 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di -16]]}
   [0xd8 0x43 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di 15]]}
   [0xd8 0x44 0x00] {::i/tag ::i/fadd, ::i/args [[::r/si]]}
   [0xd8 0x44 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/si -16]]}
   [0xd8 0x44 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/si 15]]}
   [0xd8 0x45 0x00] {::i/tag ::i/fadd, ::i/args [[::r/di]]}
   [0xd8 0x45 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/di -16]]}
   [0xd8 0x45 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/di 15]]}
   [0xd8 0x46 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp]]}
   [0xd8 0x46 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp -16]]}
   [0xd8 0x46 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp 15]]}
   [0xd8 0x47 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx]]}
   [0xd8 0x47 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx -16]]}
   [0xd8 0x47 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx 15]]}
   [0xd8 0x48 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x48 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si -16]]}
   [0xd8 0x48 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si 15]]}
   [0xd8 0x49 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x49 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di -16]]}
   [0xd8 0x49 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di 15]]}
   [0xd8 0x4a 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x4a 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si -16]]}
   [0xd8 0x4a 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si 15]]}
   [0xd8 0x4b 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x4b 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di -16]]}
   [0xd8 0x4b 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di 15]]}
   [0xd8 0x4c 0x00] {::i/tag ::i/fmul, ::i/args [[::r/si]]}
   [0xd8 0x4c 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/si -16]]}
   [0xd8 0x4c 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/si 15]]}
   [0xd8 0x4d 0x00] {::i/tag ::i/fmul, ::i/args [[::r/di]]}
   [0xd8 0x4d 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/di -16]]}
   [0xd8 0x4d 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/di 15]]}
   [0xd8 0x4e 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp]]}
   [0xd8 0x4e 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp -16]]}
   [0xd8 0x4e 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp 15]]}
   [0xd8 0x4f 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx]]}
   [0xd8 0x4f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx -16]]}
   [0xd8 0x4f 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx 15]]}
   [0xd8 0x50 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x50 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si -16]]}
   [0xd8 0x50 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si 15]]}
   [0xd8 0x51 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x51 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di -16]]}
   [0xd8 0x51 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di 15]]}
   [0xd8 0x52 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x52 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si -16]]}
   [0xd8 0x52 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si 15]]}
   [0xd8 0x53 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x53 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di -16]]}
   [0xd8 0x53 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di 15]]}
   [0xd8 0x54 0x00] {::i/tag ::i/fcom, ::i/args [[::r/si]]}
   [0xd8 0x54 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/si -16]]}
   [0xd8 0x54 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/si 15]]}
   [0xd8 0x55 0x00] {::i/tag ::i/fcom, ::i/args [[::r/di]]}
   [0xd8 0x55 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/di -16]]}
   [0xd8 0x55 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/di 15]]}
   [0xd8 0x56 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp]]}
   [0xd8 0x56 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp -16]]}
   [0xd8 0x56 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp 15]]}
   [0xd8 0x57 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx]]}
   [0xd8 0x57 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx -16]]}
   [0xd8 0x57 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx 15]]}
   [0xd8 0x58 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x58 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si -16]]}
   [0xd8 0x58 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si 15]]}
   [0xd8 0x59 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x59 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di -16]]}
   [0xd8 0x59 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di 15]]}
   [0xd8 0x5a 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x5a 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si -16]]}
   [0xd8 0x5a 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si 15]]}
   [0xd8 0x5b 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x5b 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di -16]]}
   [0xd8 0x5b 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di 15]]}
   [0xd8 0x5c 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/si]]}
   [0xd8 0x5c 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/si -16]]}
   [0xd8 0x5c 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/si 15]]}
   [0xd8 0x5d 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/di]]}
   [0xd8 0x5d 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/di -16]]}
   [0xd8 0x5d 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/di 15]]}
   [0xd8 0x5e 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp]]}
   [0xd8 0x5e 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp -16]]}
   [0xd8 0x5e 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp 15]]}
   [0xd8 0x5f 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx]]}
   [0xd8 0x5f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx -16]]}
   [0xd8 0x5f 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx 15]]}
   [0xd8 0x60 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x60 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si -16]]}
   [0xd8 0x60 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si 15]]}
   [0xd8 0x61 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x61 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di -16]]}
   [0xd8 0x61 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di 15]]}
   [0xd8 0x62 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x62 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si -16]]}
   [0xd8 0x62 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si 15]]}
   [0xd8 0x63 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x63 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di -16]]}
   [0xd8 0x63 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di 15]]}
   [0xd8 0x64 0x00] {::i/tag ::i/fsub, ::i/args [[::r/si]]}
   [0xd8 0x64 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/si -16]]}
   [0xd8 0x64 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/si 15]]}
   [0xd8 0x65 0x00] {::i/tag ::i/fsub, ::i/args [[::r/di]]}
   [0xd8 0x65 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/di -16]]}
   [0xd8 0x65 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/di 15]]}
   [0xd8 0x66 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp]]}
   [0xd8 0x66 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp -16]]}
   [0xd8 0x66 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp 15]]}
   [0xd8 0x67 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx]]}
   [0xd8 0x67 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx -16]]}
   [0xd8 0x67 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx 15]]}
   [0xd8 0x68 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x68 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si -16]]}
   [0xd8 0x68 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si 15]]}
   [0xd8 0x69 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x69 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di -16]]}
   [0xd8 0x69 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di 15]]}
   [0xd8 0x6a 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x6a 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si -16]]}
   [0xd8 0x6a 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si 15]]}
   [0xd8 0x6b 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x6b 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di -16]]}
   [0xd8 0x6b 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di 15]]}
   [0xd8 0x6c 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/si]]}
   [0xd8 0x6c 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/si -16]]}
   [0xd8 0x6c 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/si 15]]}
   [0xd8 0x6d 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/di]]}
   [0xd8 0x6d 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/di -16]]}
   [0xd8 0x6d 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/di 15]]}
   [0xd8 0x6e 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp]]}
   [0xd8 0x6e 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp -16]]}
   [0xd8 0x6e 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp 15]]}
   [0xd8 0x6f 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx]]}
   [0xd8 0x6f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx -16]]}
   [0xd8 0x6f 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx 15]]}
   [0xd8 0x70 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x70 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si -16]]}
   [0xd8 0x70 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si 15]]}
   [0xd8 0x71 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x71 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di -16]]}
   [0xd8 0x71 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di 15]]}
   [0xd8 0x72 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x72 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si -16]]}
   [0xd8 0x72 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si 15]]}
   [0xd8 0x73 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x73 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di -16]]}
   [0xd8 0x73 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di 15]]}
   [0xd8 0x74 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/si]]}
   [0xd8 0x74 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/si -16]]}
   [0xd8 0x74 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/si 15]]}
   [0xd8 0x75 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/di]]}
   [0xd8 0x75 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/di -16]]}
   [0xd8 0x75 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/di 15]]}
   [0xd8 0x76 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp]]}
   [0xd8 0x76 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp -16]]}
   [0xd8 0x76 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp 15]]}
   [0xd8 0x77 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx]]}
   [0xd8 0x77 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx -16]]}
   [0xd8 0x77 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx 15]]}
   [0xd8 0x78 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x78 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si -16]]}
   [0xd8 0x78 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si 15]]}
   [0xd8 0x79 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x79 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di -16]]}
   [0xd8 0x79 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di 15]]}
   [0xd8 0x7a 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x7a 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si -16]]}
   [0xd8 0x7a 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si 15]]}
   [0xd8 0x7b 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x7b 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di -16]]}
   [0xd8 0x7b 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di 15]]}
   [0xd8 0x7c 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/si]]}
   [0xd8 0x7c 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/si -16]]}
   [0xd8 0x7c 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/si 15]]}
   [0xd8 0x7d 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/di]]}
   [0xd8 0x7d 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/di -16]]}
   [0xd8 0x7d 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/di 15]]}
   [0xd8 0x7e 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp]]}
   [0xd8 0x7e 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp -16]]}
   [0xd8 0x7e 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp 15]]}
   [0xd8 0x7f 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx]]}
   [0xd8 0x7f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx -16]]}
   [0xd8 0x7f 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx 15]]}
   [0xd8 0x80 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x80 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd8 0x80 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd8 0x81 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x81 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd8 0x81 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd8 0x82 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x82 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd8 0x82 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd8 0x83 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x83 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd8 0x83 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd8 0x84 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/si]]}
   [0xd8 0x84 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/si 4080]]}
   [0xd8 0x84 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/si -4081]]}
   [0xd8 0x85 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/di]]}
   [0xd8 0x85 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/di 4080]]}
   [0xd8 0x85 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/di -4081]]}
   [0xd8 0x86 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp]]}
   [0xd8 0x86 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp 4080]]}
   [0xd8 0x86 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp -4081]]}
   [0xd8 0x87 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx]]}
   [0xd8 0x87 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx 4080]]}
   [0xd8 0x87 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx -4081]]}
   [0xd8 0x88 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x88 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd8 0x88 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd8 0x89 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x89 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd8 0x89 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd8 0x8a 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x8a 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd8 0x8a 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd8 0x8b 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x8b 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd8 0x8b 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd8 0x8c 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/si]]}
   [0xd8 0x8c 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/si 4080]]}
   [0xd8 0x8c 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/si -4081]]}
   [0xd8 0x8d 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/di]]}
   [0xd8 0x8d 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/di 4080]]}
   [0xd8 0x8d 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/di -4081]]}
   [0xd8 0x8e 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp]]}
   [0xd8 0x8e 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp 4080]]}
   [0xd8 0x8e 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp -4081]]}
   [0xd8 0x8f 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx]]}
   [0xd8 0x8f 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx 4080]]}
   [0xd8 0x8f 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx -4081]]}
   [0xd8 0x90 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x90 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd8 0x90 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd8 0x91 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x91 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd8 0x91 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd8 0x92 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x92 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd8 0x92 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd8 0x93 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x93 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd8 0x93 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd8 0x94 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/si]]}
   [0xd8 0x94 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/si 4080]]}
   [0xd8 0x94 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/si -4081]]}
   [0xd8 0x95 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/di]]}
   [0xd8 0x95 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/di 4080]]}
   [0xd8 0x95 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/di -4081]]}
   [0xd8 0x96 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp]]}
   [0xd8 0x96 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp 4080]]}
   [0xd8 0x96 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp -4081]]}
   [0xd8 0x97 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx]]}
   [0xd8 0x97 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx 4080]]}
   [0xd8 0x97 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx -4081]]}
   [0xd8 0x98 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0x98 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd8 0x98 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd8 0x99 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0x99 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd8 0x99 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd8 0x9a 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0x9a 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd8 0x9a 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd8 0x9b 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0x9b 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd8 0x9b 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd8 0x9c 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/si]]}
   [0xd8 0x9c 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/si 4080]]}
   [0xd8 0x9c 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/si -4081]]}
   [0xd8 0x9d 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/di]]}
   [0xd8 0x9d 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/di 4080]]}
   [0xd8 0x9d 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/di -4081]]}
   [0xd8 0x9e 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp]]}
   [0xd8 0x9e 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp 4080]]}
   [0xd8 0x9e 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp -4081]]}
   [0xd8 0x9f 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx]]}
   [0xd8 0x9f 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx 4080]]}
   [0xd8 0x9f 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx -4081]]}
   [0xd8 0xa0 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0xa0 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd8 0xa0 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd8 0xa1 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0xa1 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd8 0xa1 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd8 0xa2 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0xa2 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd8 0xa2 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd8 0xa3 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0xa3 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd8 0xa3 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd8 0xa4 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/si]]}
   [0xd8 0xa4 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/si 4080]]}
   [0xd8 0xa4 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/si -4081]]}
   [0xd8 0xa5 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/di]]}
   [0xd8 0xa5 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/di 4080]]}
   [0xd8 0xa5 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/di -4081]]}
   [0xd8 0xa6 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp]]}
   [0xd8 0xa6 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp 4080]]}
   [0xd8 0xa6 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp -4081]]}
   [0xd8 0xa7 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx]]}
   [0xd8 0xa7 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx 4080]]}
   [0xd8 0xa7 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx -4081]]}
   [0xd8 0xa8 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0xa8 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd8 0xa8 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd8 0xa9 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0xa9 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd8 0xa9 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd8 0xaa 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0xaa 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd8 0xaa 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd8 0xab 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0xab 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd8 0xab 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd8 0xac 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/si]]}
   [0xd8 0xac 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/si 4080]]}
   [0xd8 0xac 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/si -4081]]}
   [0xd8 0xad 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/di]]}
   [0xd8 0xad 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/di 4080]]}
   [0xd8 0xad 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/di -4081]]}
   [0xd8 0xae 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp]]}
   [0xd8 0xae 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp 4080]]}
   [0xd8 0xae 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp -4081]]}
   [0xd8 0xaf 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx]]}
   [0xd8 0xaf 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx 4080]]}
   [0xd8 0xaf 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx -4081]]}
   [0xd8 0xb0 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0xb0 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd8 0xb0 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd8 0xb1 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0xb1 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd8 0xb1 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd8 0xb2 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0xb2 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd8 0xb2 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd8 0xb3 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0xb3 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd8 0xb3 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd8 0xb4 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/si]]}
   [0xd8 0xb4 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/si 4080]]}
   [0xd8 0xb4 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/si -4081]]}
   [0xd8 0xb5 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/di]]}
   [0xd8 0xb5 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/di 4080]]}
   [0xd8 0xb5 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/di -4081]]}
   [0xd8 0xb6 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp]]}
   [0xd8 0xb6 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp 4080]]}
   [0xd8 0xb6 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp -4081]]}
   [0xd8 0xb7 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx]]}
   [0xd8 0xb7 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx 4080]]}
   [0xd8 0xb7 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx -4081]]}
   [0xd8 0xb8 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si]]}
   [0xd8 0xb8 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd8 0xb8 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd8 0xb9 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di]]}
   [0xd8 0xb9 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd8 0xb9 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd8 0xba 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si]]}
   [0xd8 0xba 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd8 0xba 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd8 0xbb 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di]]}
   [0xd8 0xbb 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd8 0xbb 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd8 0xbc 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/si]]}
   [0xd8 0xbc 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/si 4080]]}
   [0xd8 0xbc 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/si -4081]]}
   [0xd8 0xbd 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/di]]}
   [0xd8 0xbd 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/di 4080]]}
   [0xd8 0xbd 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/di -4081]]}
   [0xd8 0xbe 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp]]}
   [0xd8 0xbe 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp 4080]]}
   [0xd8 0xbe 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp -4081]]}
   [0xd8 0xbf 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx]]}
   [0xd8 0xbf 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx 4080]]}
   [0xd8 0xbf 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx -4081]]}
   [0xd8 0xc0] {::i/tag ::i/fadd, ::i/args [::r/st0]}
   [0xd8 0xc1] {::i/tag ::i/fadd, ::i/args [::r/st1]}
   [0xd8 0xc2] {::i/tag ::i/fadd, ::i/args [::r/st2]}
   [0xd8 0xc3] {::i/tag ::i/fadd, ::i/args [::r/st3]}
   [0xd8 0xc4] {::i/tag ::i/fadd, ::i/args [::r/st4]}
   [0xd8 0xc5] {::i/tag ::i/fadd, ::i/args [::r/st5]}
   [0xd8 0xc6] {::i/tag ::i/fadd, ::i/args [::r/st6]}
   [0xd8 0xc7] {::i/tag ::i/fadd, ::i/args [::r/st7]}
   [0xd8 0xc8] {::i/tag ::i/fmul, ::i/args [::r/st0]}
   [0xd8 0xc9] {::i/tag ::i/fmul, ::i/args [::r/st1]}
   [0xd8 0xca] {::i/tag ::i/fmul, ::i/args [::r/st2]}
   [0xd8 0xcb] {::i/tag ::i/fmul, ::i/args [::r/st3]}
   [0xd8 0xcc] {::i/tag ::i/fmul, ::i/args [::r/st4]}
   [0xd8 0xcd] {::i/tag ::i/fmul, ::i/args [::r/st5]}
   [0xd8 0xce] {::i/tag ::i/fmul, ::i/args [::r/st6]}
   [0xd8 0xcf] {::i/tag ::i/fmul, ::i/args [::r/st7]}
   [0xd8 0xd0] {::i/tag ::i/fcom, ::i/args [::r/st0]}
   [0xd8 0xd1] {::i/tag ::i/fcom, ::i/args [::r/st1]}
   [0xd8 0xd2] {::i/tag ::i/fcom, ::i/args [::r/st2]}
   [0xd8 0xd3] {::i/tag ::i/fcom, ::i/args [::r/st3]}
   [0xd8 0xd4] {::i/tag ::i/fcom, ::i/args [::r/st4]}
   [0xd8 0xd5] {::i/tag ::i/fcom, ::i/args [::r/st5]}
   [0xd8 0xd6] {::i/tag ::i/fcom, ::i/args [::r/st6]}
   [0xd8 0xd7] {::i/tag ::i/fcom, ::i/args [::r/st7]}
   [0xd8 0xd8] {::i/tag ::i/fcomp, ::i/args [::r/st0]}
   [0xd8 0xd9] {::i/tag ::i/fcomp, ::i/args [::r/st1]}
   [0xd8 0xda] {::i/tag ::i/fcomp, ::i/args [::r/st2]}
   [0xd8 0xdb] {::i/tag ::i/fcomp, ::i/args [::r/st3]}
   [0xd8 0xdc] {::i/tag ::i/fcomp, ::i/args [::r/st4]}
   [0xd8 0xdd] {::i/tag ::i/fcomp, ::i/args [::r/st5]}
   [0xd8 0xde] {::i/tag ::i/fcomp, ::i/args [::r/st6]}
   [0xd8 0xdf] {::i/tag ::i/fcomp, ::i/args [::r/st7]}
   [0xd8 0xe0] {::i/tag ::i/fsub, ::i/args [::r/st0]}
   [0xd8 0xe1] {::i/tag ::i/fsub, ::i/args [::r/st1]}
   [0xd8 0xe2] {::i/tag ::i/fsub, ::i/args [::r/st2]}
   [0xd8 0xe3] {::i/tag ::i/fsub, ::i/args [::r/st3]}
   [0xd8 0xe4] {::i/tag ::i/fsub, ::i/args [::r/st4]}
   [0xd8 0xe5] {::i/tag ::i/fsub, ::i/args [::r/st5]}
   [0xd8 0xe6] {::i/tag ::i/fsub, ::i/args [::r/st6]}
   [0xd8 0xe7] {::i/tag ::i/fsub, ::i/args [::r/st7]}
   [0xd8 0xe8] {::i/tag ::i/fsubr, ::i/args [::r/st0]}
   [0xd8 0xe9] {::i/tag ::i/fsubr, ::i/args [::r/st1]}
   [0xd8 0xea] {::i/tag ::i/fsubr, ::i/args [::r/st2]}
   [0xd8 0xeb] {::i/tag ::i/fsubr, ::i/args [::r/st3]}
   [0xd8 0xec] {::i/tag ::i/fsubr, ::i/args [::r/st4]}
   [0xd8 0xed] {::i/tag ::i/fsubr, ::i/args [::r/st5]}
   [0xd8 0xee] {::i/tag ::i/fsubr, ::i/args [::r/st6]}
   [0xd8 0xef] {::i/tag ::i/fsubr, ::i/args [::r/st7]}
   [0xd8 0xf0] {::i/tag ::i/fdiv, ::i/args [::r/st0]}
   [0xd8 0xf1] {::i/tag ::i/fdiv, ::i/args [::r/st1]}
   [0xd8 0xf2] {::i/tag ::i/fdiv, ::i/args [::r/st2]}
   [0xd8 0xf3] {::i/tag ::i/fdiv, ::i/args [::r/st3]}
   [0xd8 0xf4] {::i/tag ::i/fdiv, ::i/args [::r/st4]}
   [0xd8 0xf5] {::i/tag ::i/fdiv, ::i/args [::r/st5]}
   [0xd8 0xf6] {::i/tag ::i/fdiv, ::i/args [::r/st6]}
   [0xd8 0xf7] {::i/tag ::i/fdiv, ::i/args [::r/st7]}
   [0xd8 0xf8] {::i/tag ::i/fdivr, ::i/args [::r/st0]}
   [0xd8 0xf9] {::i/tag ::i/fdivr, ::i/args [::r/st1]}
   [0xd8 0xfa] {::i/tag ::i/fdivr, ::i/args [::r/st2]}
   [0xd8 0xfb] {::i/tag ::i/fdivr, ::i/args [::r/st3]}
   [0xd8 0xfc] {::i/tag ::i/fdivr, ::i/args [::r/st4]}
   [0xd8 0xfd] {::i/tag ::i/fdivr, ::i/args [::r/st5]}
   [0xd8 0xfe] {::i/tag ::i/fdivr, ::i/args [::r/st6]}
   [0xd8 0xff] {::i/tag ::i/fdivr, ::i/args [::r/st7]}})


(def decode-examples-0xd9
  {[0xd9 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x01] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x02] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x03] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x04] {::i/tag ::i/fld, ::i/args [[::r/si]]}
   [0xd9 0x05] {::i/tag ::i/fld, ::i/args [[::r/di]]}
   [0xd9 0x06 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[0x0ff0]]}
   [0xd9 0x06 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[0xf00f]]}
   [0xd9 0x07] {::i/tag ::i/fld, ::i/args [[::r/bx]]}
   [0xd9 0x08] nil
   [0xd9 0x09] nil
   [0xd9 0x0a] nil
   [0xd9 0x0b] nil
   [0xd9 0x0c] nil
   [0xd9 0x0d] nil
   [0xd9 0x0e 0x90 0x90] nil
   [0xd9 0x0f] nil
   [0xd9 0x10] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x11] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x12] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x13] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x14] {::i/tag ::i/fst, ::i/args [[::r/si]]}
   [0xd9 0x15] {::i/tag ::i/fst, ::i/args [[::r/di]]}
   [0xd9 0x16 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[0x0ff0]]}
   [0xd9 0x16 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[0xf00f]]}
   [0xd9 0x17] {::i/tag ::i/fst, ::i/args [[::r/bx]]}
   [0xd9 0x18] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x19] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x1a] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x1b] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x1c] {::i/tag ::i/fstp, ::i/args [[::r/si]]}
   [0xd9 0x1d] {::i/tag ::i/fstp, ::i/args [[::r/di]]}
   [0xd9 0x1e 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[0x0ff0]]}
   [0xd9 0x1e 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[0xf00f]]}
   [0xd9 0x1f] {::i/tag ::i/fstp, ::i/args [[::r/bx]]}
   [0xd9 0x20] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x21] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x22] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x23] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x24] {::i/tag ::i/fldenvw, ::i/args [[::r/si]]}
   [0xd9 0x25] {::i/tag ::i/fldenvw, ::i/args [[::r/di]]}
   [0xd9 0x26 0xf0 0x0f] {::i/tag ::i/fldenvw, ::i/args [[0x0ff0]]}
   [0xd9 0x26 0x0f 0xf0] {::i/tag ::i/fldenvw, ::i/args [[0xf00f]]}
   [0xd9 0x27] {::i/tag ::i/fldenvw, ::i/args [[::r/bx]]}
   [0xd9 0x28] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x29] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x2a] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x2b] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x2c] {::i/tag ::i/fldcw, ::i/args [[::r/si]]}
   [0xd9 0x2d] {::i/tag ::i/fldcw, ::i/args [[::r/di]]}
   [0xd9 0x2e 0xf0 0x0f] {::i/tag ::i/fldcw, ::i/args [[0x0ff0]]}
   [0xd9 0x2e 0x0f 0xf0] {::i/tag ::i/fldcw, ::i/args [[0xf00f]]}
   [0xd9 0x2f] {::i/tag ::i/fldcw, ::i/args [[::r/bx]]}
   [0xd9 0x30] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x31] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x32] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x33] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x34] {::i/tag ::i/fnstenvw, ::i/args [[::r/si]]}
   [0xd9 0x35] {::i/tag ::i/fnstenvw, ::i/args [[::r/di]]}
   [0xd9 0x36 0xf0 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[0x0ff0]]}
   [0xd9 0x36 0x0f 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[0xf00f]]}
   [0xd9 0x37] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx]]}
   [0xd9 0x38] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x39] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x3a] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x3b] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x3c] {::i/tag ::i/fnstcw, ::i/args [[::r/si]]}
   [0xd9 0x3d] {::i/tag ::i/fnstcw, ::i/args [[::r/di]]}
   [0xd9 0x3e 0xf0 0x0f] {::i/tag ::i/fnstcw, ::i/args [[0x0ff0]]}
   [0xd9 0x3e 0x0f 0xf0] {::i/tag ::i/fnstcw, ::i/args [[0xf00f]]}
   [0xd9 0x3f] {::i/tag ::i/fnstcw, ::i/args [[::r/bx]]}
   [0xd9 0x40 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x40 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si 15]]}
   [0xd9 0x40 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si -16]]}
   [0xd9 0x41 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x41 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di 15]]}
   [0xd9 0x41 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di -16]]}
   [0xd9 0x42 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x42 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si 15]]}
   [0xd9 0x42 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si -16]]}
   [0xd9 0x43 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x43 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di 15]]}
   [0xd9 0x43 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di -16]]}
   [0xd9 0x44 0x00] {::i/tag ::i/fld, ::i/args [[::r/si]]}
   [0xd9 0x44 0x0f] {::i/tag ::i/fld, ::i/args [[::r/si 15]]}
   [0xd9 0x44 0xf0] {::i/tag ::i/fld, ::i/args [[::r/si -16]]}
   [0xd9 0x45 0x00] {::i/tag ::i/fld, ::i/args [[::r/di]]}
   [0xd9 0x45 0x0f] {::i/tag ::i/fld, ::i/args [[::r/di 15]]}
   [0xd9 0x45 0xf0] {::i/tag ::i/fld, ::i/args [[::r/di -16]]}
   [0xd9 0x46 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp]]}
   [0xd9 0x46 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp 15]]}
   [0xd9 0x46 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp -16]]}
   [0xd9 0x47 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx]]}
   [0xd9 0x47 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx 15]]}
   [0xd9 0x47 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx -16]]}
   [0xd9 0x48 0x90] nil
   [0xd9 0x49 0x90] nil
   [0xd9 0x4a 0x90] nil
   [0xd9 0x4b 0x90] nil
   [0xd9 0x4c 0x90] nil
   [0xd9 0x4d 0x90] nil
   [0xd9 0x4e 0x90] nil
   [0xd9 0x4f 0x90] nil
   [0xd9 0x50 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x50 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si 15]]}
   [0xd9 0x50 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si -16]]}
   [0xd9 0x51 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x51 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di 15]]}
   [0xd9 0x51 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di -16]]}
   [0xd9 0x52 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x52 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si 15]]}
   [0xd9 0x52 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si -16]]}
   [0xd9 0x53 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x53 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di 15]]}
   [0xd9 0x53 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di -16]]}
   [0xd9 0x54 0x00] {::i/tag ::i/fst, ::i/args [[::r/si]]}
   [0xd9 0x54 0x0f] {::i/tag ::i/fst, ::i/args [[::r/si 15]]}
   [0xd9 0x54 0xf0] {::i/tag ::i/fst, ::i/args [[::r/si -16]]}
   [0xd9 0x55 0x00] {::i/tag ::i/fst, ::i/args [[::r/di]]}
   [0xd9 0x55 0x0f] {::i/tag ::i/fst, ::i/args [[::r/di 15]]}
   [0xd9 0x55 0xf0] {::i/tag ::i/fst, ::i/args [[::r/di -16]]}
   [0xd9 0x56 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp]]}
   [0xd9 0x56 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp 15]]}
   [0xd9 0x56 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp -16]]}
   [0xd9 0x57 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx]]}
   [0xd9 0x57 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx 15]]}
   [0xd9 0x57 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx -16]]}
   [0xd9 0x58 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x58 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si 15]]}
   [0xd9 0x58 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si -16]]}
   [0xd9 0x59 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x59 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di 15]]}
   [0xd9 0x59 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di -16]]}
   [0xd9 0x5a 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x5a 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si 15]]}
   [0xd9 0x5a 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si -16]]}
   [0xd9 0x5b 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x5b 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di 15]]}
   [0xd9 0x5b 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di -16]]}
   [0xd9 0x5c 0x00] {::i/tag ::i/fstp, ::i/args [[::r/si]]}
   [0xd9 0x5c 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/si 15]]}
   [0xd9 0x5c 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/si -16]]}
   [0xd9 0x5d 0x00] {::i/tag ::i/fstp, ::i/args [[::r/di]]}
   [0xd9 0x5d 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/di 15]]}
   [0xd9 0x5d 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/di -16]]}
   [0xd9 0x5e 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp]]}
   [0xd9 0x5e 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp 15]]}
   [0xd9 0x5e 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp -16]]}
   [0xd9 0x5f 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx]]}
   [0xd9 0x5f 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx 15]]}
   [0xd9 0x5f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx -16]]}
   [0xd9 0x60 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x60 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/si 15]]}
   [0xd9 0x60 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/si -16]]}
   [0xd9 0x61 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x61 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/di 15]]}
   [0xd9 0x61 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/di -16]]}
   [0xd9 0x62 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x62 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/si 15]]}
   [0xd9 0x62 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/si -16]]}
   [0xd9 0x63 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x63 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/di 15]]}
   [0xd9 0x63 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/di -16]]}
   [0xd9 0x64 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/si]]}
   [0xd9 0x64 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/si 15]]}
   [0xd9 0x64 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/si -16]]}
   [0xd9 0x65 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/di]]}
   [0xd9 0x65 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/di 15]]}
   [0xd9 0x65 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/di -16]]}
   [0xd9 0x66 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bp]]}
   [0xd9 0x66 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bp 15]]}
   [0xd9 0x66 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bp -16]]}
   [0xd9 0x67 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bx]]}
   [0xd9 0x67 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bx 15]]}
   [0xd9 0x67 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bx -16]]}
   [0xd9 0x68 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x68 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/si 15]]}
   [0xd9 0x68 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/si -16]]}
   [0xd9 0x69 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x69 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/di 15]]}
   [0xd9 0x69 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/di -16]]}
   [0xd9 0x6a 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x6a 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/si 15]]}
   [0xd9 0x6a 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/si -16]]}
   [0xd9 0x6b 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x6b 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/di 15]]}
   [0xd9 0x6b 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/di -16]]}
   [0xd9 0x6c 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/si]]}
   [0xd9 0x6c 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/si 15]]}
   [0xd9 0x6c 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/si -16]]}
   [0xd9 0x6d 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/di]]}
   [0xd9 0x6d 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/di 15]]}
   [0xd9 0x6d 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/di -16]]}
   [0xd9 0x6e 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bp]]}
   [0xd9 0x6e 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bp 15]]}
   [0xd9 0x6e 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bp -16]]}
   [0xd9 0x6f 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bx]]}
   [0xd9 0x6f 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bx 15]]}
   [0xd9 0x6f 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bx -16]]}
   [0xd9 0x70 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x70 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/si 15]]}
   [0xd9 0x70 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/si -16]]}
   [0xd9 0x71 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x71 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/di 15]]}
   [0xd9 0x71 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/di -16]]}
   [0xd9 0x72 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x72 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/si 15]]}
   [0xd9 0x72 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/si -16]]}
   [0xd9 0x73 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x73 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/di 15]]}
   [0xd9 0x73 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/di -16]]}
   [0xd9 0x74 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/si]]}
   [0xd9 0x74 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/si 15]]}
   [0xd9 0x74 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/si -16]]}
   [0xd9 0x75 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/di]]}
   [0xd9 0x75 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/di 15]]}
   [0xd9 0x75 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/di -16]]}
   [0xd9 0x76 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp]]}
   [0xd9 0x76 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp 15]]}
   [0xd9 0x76 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp -16]]}
   [0xd9 0x77 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx]]}
   [0xd9 0x77 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx 15]]}
   [0xd9 0x77 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx -16]]}
   [0xd9 0x78 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x78 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/si 15]]}
   [0xd9 0x78 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/si -16]]}
   [0xd9 0x79 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x79 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/di 15]]}
   [0xd9 0x79 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/di -16]]}
   [0xd9 0x7a 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x7a 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/si 15]]}
   [0xd9 0x7a 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/si -16]]}
   [0xd9 0x7b 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x7b 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/di 15]]}
   [0xd9 0x7b 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/di -16]]}
   [0xd9 0x7c 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/si]]}
   [0xd9 0x7c 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/si 15]]}
   [0xd9 0x7c 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/si -16]]}
   [0xd9 0x7d 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/di]]}
   [0xd9 0x7d 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/di 15]]}
   [0xd9 0x7d 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/di -16]]}
   [0xd9 0x7e 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bp]]}
   [0xd9 0x7e 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bp 15]]}
   [0xd9 0x7e 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bp -16]]}
   [0xd9 0x7f 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bx]]}
   [0xd9 0x7f 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bx 15]]}
   [0xd9 0x7f 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bx -16]]}
   [0xd9 0x80 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x80 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd9 0x80 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd9 0x81 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x81 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd9 0x81 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd9 0x82 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x82 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd9 0x82 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd9 0x83 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x83 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd9 0x83 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd9 0x84 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/si]]}
   [0xd9 0x84 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/si 4080]]}
   [0xd9 0x84 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/si -4081]]}
   [0xd9 0x85 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/di]]}
   [0xd9 0x85 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/di 4080]]}
   [0xd9 0x85 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/di -4081]]}
   [0xd9 0x86 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp]]}
   [0xd9 0x86 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp 4080]]}
   [0xd9 0x86 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp -4081]]}
   [0xd9 0x87 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx]]}
   [0xd9 0x87 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx 4080]]}
   [0xd9 0x87 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx -4081]]}
   [0xd9 0x88 0x90 0x90] nil
   [0xd9 0x89 0x90 0x90] nil
   [0xd9 0x8a 0x90 0x90] nil
   [0xd9 0x8b 0x90 0x90] nil
   [0xd9 0x8c 0x90 0x90] nil
   [0xd9 0x8d 0x90 0x90] nil
   [0xd9 0x8e 0x90 0x90] nil
   [0xd9 0x8f 0x90 0x90] nil
   [0xd9 0x90 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x90 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd9 0x90 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd9 0x91 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x91 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd9 0x91 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd9 0x92 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x92 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd9 0x92 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd9 0x93 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x93 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd9 0x93 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd9 0x94 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/si]]}
   [0xd9 0x94 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/si 4080]]}
   [0xd9 0x94 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/si -4081]]}
   [0xd9 0x95 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/di]]}
   [0xd9 0x95 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/di 4080]]}
   [0xd9 0x95 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/di -4081]]}
   [0xd9 0x96 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp]]}
   [0xd9 0x96 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp 4080]]}
   [0xd9 0x96 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp -4081]]}
   [0xd9 0x97 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx]]}
   [0xd9 0x97 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx 4080]]}
   [0xd9 0x97 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx -4081]]}
   [0xd9 0x98 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0x98 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd9 0x98 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd9 0x99 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0x99 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd9 0x99 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd9 0x9a 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0x9a 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd9 0x9a 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd9 0x9b 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0x9b 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd9 0x9b 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd9 0x9c 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/si]]}
   [0xd9 0x9c 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/si 4080]]}
   [0xd9 0x9c 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/si -4081]]}
   [0xd9 0x9d 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/di]]}
   [0xd9 0x9d 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/di 4080]]}
   [0xd9 0x9d 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/di -4081]]}
   [0xd9 0x9e 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp]]}
   [0xd9 0x9e 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp 4080]]}
   [0xd9 0x9e 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp -4081]]}
   [0xd9 0x9f 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx]]}
   [0xd9 0x9f 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx 4080]]}
   [0xd9 0x9f 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx -4081]]}
   [0xd9 0xa0 0x00 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0xa0 0xf0 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd9 0xa0 0x0f 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd9 0xa1 0x00 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0xa1 0xf0 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd9 0xa1 0x0f 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd9 0xa2 0x00 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0xa2 0xf0 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd9 0xa2 0x0f 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd9 0xa3 0x00 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0xa3 0xf0 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd9 0xa3 0x0f 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd9 0xa4 0x00 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/si]]}
   [0xd9 0xa4 0xf0 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/si 4080]]}
   [0xd9 0xa4 0x0f 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/si -4081]]}
   [0xd9 0xa5 0x00 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/di]]}
   [0xd9 0xa5 0xf0 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/di 4080]]}
   [0xd9 0xa5 0x0f 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/di -4081]]}
   [0xd9 0xa6 0x00 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bp]]}
   [0xd9 0xa6 0xf0 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bp 4080]]}
   [0xd9 0xa6 0x0f 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bp -4081]]}
   [0xd9 0xa7 0x00 0x00] {::i/tag ::i/fldenvw, ::i/args [[::r/bx]]}
   [0xd9 0xa7 0xf0 0x0f] {::i/tag ::i/fldenvw, ::i/args [[::r/bx 4080]]}
   [0xd9 0xa7 0x0f 0xf0] {::i/tag ::i/fldenvw, ::i/args [[::r/bx -4081]]}
   [0xd9 0xa8 0x00 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0xa8 0xf0 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd9 0xa8 0x0f 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd9 0xa9 0x00 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0xa9 0xf0 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd9 0xa9 0x0f 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd9 0xaa 0x00 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0xaa 0xf0 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd9 0xaa 0x0f 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd9 0xab 0x00 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0xab 0xf0 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd9 0xab 0x0f 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd9 0xac 0x00 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/si]]}
   [0xd9 0xac 0xf0 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/si 4080]]}
   [0xd9 0xac 0x0f 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/si -4081]]}
   [0xd9 0xad 0x00 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/di]]}
   [0xd9 0xad 0xf0 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/di 4080]]}
   [0xd9 0xad 0x0f 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/di -4081]]}
   [0xd9 0xae 0x00 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bp]]}
   [0xd9 0xae 0xf0 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bp 4080]]}
   [0xd9 0xae 0x0f 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bp -4081]]}
   [0xd9 0xaf 0x00 0x00] {::i/tag ::i/fldcw, ::i/args [[::r/bx]]}
   [0xd9 0xaf 0xf0 0x0f] {::i/tag ::i/fldcw, ::i/args [[::r/bx 4080]]}
   [0xd9 0xaf 0x0f 0xf0] {::i/tag ::i/fldcw, ::i/args [[::r/bx -4081]]}
   [0xd9 0xb0 0x00 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0xb0 0xf0 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd9 0xb0 0x0f 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd9 0xb1 0x00 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0xb1 0xf0 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd9 0xb1 0x0f 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd9 0xb2 0x00 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0xb2 0xf0 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd9 0xb2 0x0f 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd9 0xb3 0x00 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0xb3 0xf0 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd9 0xb3 0x0f 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd9 0xb4 0x00 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/si]]}
   [0xd9 0xb4 0xf0 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/si 4080]]}
   [0xd9 0xb4 0x0f 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/si -4081]]}
   [0xd9 0xb5 0x00 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/di]]}
   [0xd9 0xb5 0xf0 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/di 4080]]}
   [0xd9 0xb5 0x0f 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/di -4081]]}
   [0xd9 0xb6 0x00 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp]]}
   [0xd9 0xb6 0xf0 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp 4080]]}
   [0xd9 0xb6 0x0f 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bp -4081]]}
   [0xd9 0xb7 0x00 0x00] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx]]}
   [0xd9 0xb7 0xf0 0x0f] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx 4080]]}
   [0xd9 0xb7 0x0f 0xf0] {::i/tag ::i/fnstenvw, ::i/args [[::r/bx -4081]]}
   [0xd9 0xb8 0x00 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/si]]}
   [0xd9 0xb8 0xf0 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/si 4080]]}
   [0xd9 0xb8 0x0f 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/si -4081]]}
   [0xd9 0xb9 0x00 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/di]]}
   [0xd9 0xb9 0xf0 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/di 4080]]}
   [0xd9 0xb9 0x0f 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bx ::r/di -4081]]}
   [0xd9 0xba 0x00 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/si]]}
   [0xd9 0xba 0xf0 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/si 4080]]}
   [0xd9 0xba 0x0f 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/si -4081]]}
   [0xd9 0xbb 0x00 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/di]]}
   [0xd9 0xbb 0xf0 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/di 4080]]}
   [0xd9 0xbb 0x0f 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bp ::r/di -4081]]}
   [0xd9 0xbc 0x00 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/si]]}
   [0xd9 0xbc 0xf0 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/si 4080]]}
   [0xd9 0xbc 0x0f 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/si -4081]]}
   [0xd9 0xbd 0x00 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/di]]}
   [0xd9 0xbd 0xf0 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/di 4080]]}
   [0xd9 0xbd 0x0f 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/di -4081]]}
   [0xd9 0xbe 0x00 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bp]]}
   [0xd9 0xbe 0xf0 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bp 4080]]}
   [0xd9 0xbe 0x0f 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bp -4081]]}
   [0xd9 0xbf 0x00 0x00] {::i/tag ::i/fnstcw, ::i/args [[::r/bx]]}
   [0xd9 0xbf 0xf0 0x0f] {::i/tag ::i/fnstcw, ::i/args [[::r/bx 4080]]}
   [0xd9 0xbf 0x0f 0xf0] {::i/tag ::i/fnstcw, ::i/args [[::r/bx -4081]]}
   [0xd9 0xc0] {::i/tag ::i/fld, ::i/args [::r/st0]}
   [0xd9 0xc1] {::i/tag ::i/fld, ::i/args [::r/st1]}
   [0xd9 0xc2] {::i/tag ::i/fld, ::i/args [::r/st2]}
   [0xd9 0xc3] {::i/tag ::i/fld, ::i/args [::r/st3]}
   [0xd9 0xc4] {::i/tag ::i/fld, ::i/args [::r/st4]}
   [0xd9 0xc5] {::i/tag ::i/fld, ::i/args [::r/st5]}
   [0xd9 0xc6] {::i/tag ::i/fld, ::i/args [::r/st6]}
   [0xd9 0xc7] {::i/tag ::i/fld, ::i/args [::r/st7]}
   [0xd9 0xc8] {::i/tag ::i/fxch, ::i/args [::r/st0]}
   [0xd9 0xc9] {::i/tag ::i/fxch, ::i/args [::r/st1]}
   [0xd9 0xca] {::i/tag ::i/fxch, ::i/args [::r/st2]}
   [0xd9 0xcb] {::i/tag ::i/fxch, ::i/args [::r/st3]}
   [0xd9 0xcc] {::i/tag ::i/fxch, ::i/args [::r/st4]}
   [0xd9 0xcd] {::i/tag ::i/fxch, ::i/args [::r/st5]}
   [0xd9 0xce] {::i/tag ::i/fxch, ::i/args [::r/st6]}
   [0xd9 0xcf] {::i/tag ::i/fxch, ::i/args [::r/st7]}
   [0xd9 0xd0] {::i/tag ::i/fnop}
   [0xd9 0xd1] nil
   [0xd9 0xd2] nil
   [0xd9 0xd3] nil
   [0xd9 0xd4] nil
   [0xd9 0xd5] nil
   [0xd9 0xd6] nil
   [0xd9 0xd7] nil
   [0xd9 0xd8] {::i/tag ::i/fnop}
   [0xd9 0xd9] nil
   [0xd9 0xda] nil
   [0xd9 0xdb] nil
   [0xd9 0xdc] nil
   [0xd9 0xdd] nil
   [0xd9 0xde] nil
   [0xd9 0xdf] nil
   [0xd9 0xe0] {::i/tag ::i/fchs}
   [0xd9 0xe1] {::i/tag ::i/fabs}
   [0xd9 0xe2] nil
   [0xd9 0xe3] nil
   [0xd9 0xe4] {::i/tag ::i/ftst}
   [0xd9 0xe5] {::i/tag ::i/fxam}
   [0xd9 0xe6] nil
   [0xd9 0xe7] nil
   [0xd9 0xe8] {::i/tag ::i/fld1}
   [0xd9 0xe9] {::i/tag ::i/fldl2t}
   [0xd9 0xea] {::i/tag ::i/fldl2e}
   [0xd9 0xeb] {::i/tag ::i/fldpi}
   [0xd9 0xec] {::i/tag ::i/fldlg2}
   [0xd9 0xed] {::i/tag ::i/fldln2}
   [0xd9 0xee] {::i/tag ::i/fldz}
   [0xd9 0xef] nil
   [0xd9 0xf0] {::i/tag ::i/f2xm1}
   [0xd9 0xf1] {::i/tag ::i/fyl2x}
   [0xd9 0xf2] {::i/tag ::i/fptan}
   [0xd9 0xf3] {::i/tag ::i/fpatan}
   [0xd9 0xf4] {::i/tag ::i/fxtract}
   [0xd9 0xf5] {::i/tag ::i/fprem1}
   [0xd9 0xf6] {::i/tag ::i/fdecstp}
   [0xd9 0xf7] {::i/tag ::i/fincstp}
   [0xd9 0xf8] {::i/tag ::i/fprem}
   [0xd9 0xf9] {::i/tag ::i/fyl2xp1}
   [0xd9 0xfa] {::i/tag ::i/fsqrt}
   [0xd9 0xfb] {::i/tag ::i/fsincos}
   [0xd9 0xfc] {::i/tag ::i/frndint}
   [0xd9 0xfd] {::i/tag ::i/fscale}
   [0xd9 0xfe] {::i/tag ::i/fsin}
   [0xd9 0xff] {::i/tag ::i/fcos}})


(def decode-examples-0xda
  {[0xda 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x01] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x02] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x03] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x04] {::i/tag ::i/fiadd, ::i/args [[::r/si]]}
   [0xda 0x05] {::i/tag ::i/fiadd, ::i/args [[::r/di]]}
   [0xda 0x06 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[0x0ff0]]}
   [0xda 0x06 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[0xf00f]]}
   [0xda 0x07] {::i/tag ::i/fiadd, ::i/args [[::r/bx]]}
   [0xda 0x08] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x09] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x0a] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x0b] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x0c] {::i/tag ::i/fimul, ::i/args [[::r/si]]}
   [0xda 0x0d] {::i/tag ::i/fimul, ::i/args [[::r/di]]}
   [0xda 0x0e 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[0x0ff0]]}
   [0xda 0x0e 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[0xf00f]]}
   [0xda 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx]]}
   [0xda 0x10] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x11] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x12] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x13] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x14] {::i/tag ::i/ficom, ::i/args [[::r/si]]}
   [0xda 0x15] {::i/tag ::i/ficom, ::i/args [[::r/di]]}
   [0xda 0x16 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[0x0ff0]]}
   [0xda 0x16 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[0xf00f]]}
   [0xda 0x17] {::i/tag ::i/ficom, ::i/args [[::r/bx]]}
   [0xda 0x18] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x19] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x1a] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x1b] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x1c] {::i/tag ::i/ficomp, ::i/args [[::r/si]]}
   [0xda 0x1d] {::i/tag ::i/ficomp, ::i/args [[::r/di]]}
   [0xda 0x1e 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[0x0ff0]]}
   [0xda 0x1e 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[0xf00f]]}
   [0xda 0x1f] {::i/tag ::i/ficomp, ::i/args [[::r/bx]]}
   [0xda 0x20] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x21] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x22] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x23] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x24] {::i/tag ::i/fisub, ::i/args [[::r/si]]}
   [0xda 0x25] {::i/tag ::i/fisub, ::i/args [[::r/di]]}
   [0xda 0x26 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[0x0ff0]]}
   [0xda 0x26 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[0xf00f]]}
   [0xda 0x27] {::i/tag ::i/fisub, ::i/args [[::r/bx]]}
   [0xda 0x28] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x29] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x2a] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x2b] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x2c] {::i/tag ::i/fisubr, ::i/args [[::r/si]]}
   [0xda 0x2d] {::i/tag ::i/fisubr, ::i/args [[::r/di]]}
   [0xda 0x2e 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[0x0ff0]]}
   [0xda 0x2e 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[0xf00f]]}
   [0xda 0x2f] {::i/tag ::i/fisubr, ::i/args [[::r/bx]]}
   [0xda 0x30] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x31] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x32] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x33] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x34] {::i/tag ::i/fidiv, ::i/args [[::r/si]]}
   [0xda 0x35] {::i/tag ::i/fidiv, ::i/args [[::r/di]]}
   [0xda 0x36 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[0x0ff0]]}
   [0xda 0x36 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[0xf00f]]}
   [0xda 0x37] {::i/tag ::i/fidiv, ::i/args [[::r/bx]]}
   [0xda 0x38] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x39] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x3a] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x3b] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x3c] {::i/tag ::i/fidivr, ::i/args [[::r/si]]}
   [0xda 0x3d] {::i/tag ::i/fidivr, ::i/args [[::r/di]]}
   [0xda 0x3e 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[0x0ff0]]}
   [0xda 0x3e 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[0xf00f]]}
   [0xda 0x3f] {::i/tag ::i/fidivr, ::i/args [[::r/bx]]}
   [0xda 0x40 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x40 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si 15]]}
   [0xda 0x40 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si -16]]}
   [0xda 0x41 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x41 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di 15]]}
   [0xda 0x41 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di -16]]}
   [0xda 0x42 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x42 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si 15]]}
   [0xda 0x42 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si -16]]}
   [0xda 0x43 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x43 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di 15]]}
   [0xda 0x43 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di -16]]}
   [0xda 0x44 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/si]]}
   [0xda 0x44 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/si 15]]}
   [0xda 0x44 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/si -16]]}
   [0xda 0x45 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/di]]}
   [0xda 0x45 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/di 15]]}
   [0xda 0x45 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/di -16]]}
   [0xda 0x46 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp]]}
   [0xda 0x46 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp 15]]}
   [0xda 0x46 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp -16]]}
   [0xda 0x47 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx]]}
   [0xda 0x47 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx 15]]}
   [0xda 0x47 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx -16]]}
   [0xda 0x48 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x48 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si 15]]}
   [0xda 0x48 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si -16]]}
   [0xda 0x49 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x49 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di 15]]}
   [0xda 0x49 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di -16]]}
   [0xda 0x4a 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x4a 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si 15]]}
   [0xda 0x4a 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si -16]]}
   [0xda 0x4b 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x4b 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di 15]]}
   [0xda 0x4b 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di -16]]}
   [0xda 0x4c 0x00] {::i/tag ::i/fimul, ::i/args [[::r/si]]}
   [0xda 0x4c 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/si 15]]}
   [0xda 0x4c 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/si -16]]}
   [0xda 0x4d 0x00] {::i/tag ::i/fimul, ::i/args [[::r/di]]}
   [0xda 0x4d 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/di 15]]}
   [0xda 0x4d 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/di -16]]}
   [0xda 0x4e 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp]]}
   [0xda 0x4e 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp 15]]}
   [0xda 0x4e 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp -16]]}
   [0xda 0x4f 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx]]}
   [0xda 0x4f 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx 15]]}
   [0xda 0x4f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx -16]]}
   [0xda 0x50 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x50 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si 15]]}
   [0xda 0x50 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si -16]]}
   [0xda 0x51 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x51 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di 15]]}
   [0xda 0x51 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di -16]]}
   [0xda 0x52 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x52 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si 15]]}
   [0xda 0x52 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si -16]]}
   [0xda 0x53 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x53 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di 15]]}
   [0xda 0x53 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di -16]]}
   [0xda 0x54 0x00] {::i/tag ::i/ficom, ::i/args [[::r/si]]}
   [0xda 0x54 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/si 15]]}
   [0xda 0x54 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/si -16]]}
   [0xda 0x55 0x00] {::i/tag ::i/ficom, ::i/args [[::r/di]]}
   [0xda 0x55 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/di 15]]}
   [0xda 0x55 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/di -16]]}
   [0xda 0x56 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp]]}
   [0xda 0x56 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp 15]]}
   [0xda 0x56 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp -16]]}
   [0xda 0x57 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx]]}
   [0xda 0x57 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx 15]]}
   [0xda 0x57 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx -16]]}
   [0xda 0x58 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x58 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si 15]]}
   [0xda 0x58 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si -16]]}
   [0xda 0x59 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x59 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di 15]]}
   [0xda 0x59 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di -16]]}
   [0xda 0x5a 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x5a 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si 15]]}
   [0xda 0x5a 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si -16]]}
   [0xda 0x5b 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x5b 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di 15]]}
   [0xda 0x5b 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di -16]]}
   [0xda 0x5c 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/si]]}
   [0xda 0x5c 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/si 15]]}
   [0xda 0x5c 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/si -16]]}
   [0xda 0x5d 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/di]]}
   [0xda 0x5d 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/di 15]]}
   [0xda 0x5d 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/di -16]]}
   [0xda 0x5e 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp]]}
   [0xda 0x5e 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp 15]]}
   [0xda 0x5e 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp -16]]}
   [0xda 0x5f 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx]]}
   [0xda 0x5f 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx 15]]}
   [0xda 0x5f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx -16]]}
   [0xda 0x60 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x60 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si 15]]}
   [0xda 0x60 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si -16]]}
   [0xda 0x61 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x61 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di 15]]}
   [0xda 0x61 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di -16]]}
   [0xda 0x62 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x62 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si 15]]}
   [0xda 0x62 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si -16]]}
   [0xda 0x63 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x63 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di 15]]}
   [0xda 0x63 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di -16]]}
   [0xda 0x64 0x00] {::i/tag ::i/fisub, ::i/args [[::r/si]]}
   [0xda 0x64 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/si 15]]}
   [0xda 0x64 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/si -16]]}
   [0xda 0x65 0x00] {::i/tag ::i/fisub, ::i/args [[::r/di]]}
   [0xda 0x65 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/di 15]]}
   [0xda 0x65 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/di -16]]}
   [0xda 0x66 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp]]}
   [0xda 0x66 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp 15]]}
   [0xda 0x66 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp -16]]}
   [0xda 0x67 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx]]}
   [0xda 0x67 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx 15]]}
   [0xda 0x67 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx -16]]}
   [0xda 0x68 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x68 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si 15]]}
   [0xda 0x68 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si -16]]}
   [0xda 0x69 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x69 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di 15]]}
   [0xda 0x69 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di -16]]}
   [0xda 0x6a 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x6a 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si 15]]}
   [0xda 0x6a 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si -16]]}
   [0xda 0x6b 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x6b 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di 15]]}
   [0xda 0x6b 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di -16]]}
   [0xda 0x6c 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/si]]}
   [0xda 0x6c 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/si 15]]}
   [0xda 0x6c 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/si -16]]}
   [0xda 0x6d 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/di]]}
   [0xda 0x6d 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/di 15]]}
   [0xda 0x6d 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/di -16]]}
   [0xda 0x6e 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp]]}
   [0xda 0x6e 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp 15]]}
   [0xda 0x6e 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp -16]]}
   [0xda 0x6f 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx]]}
   [0xda 0x6f 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx 15]]}
   [0xda 0x6f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx -16]]}
   [0xda 0x70 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x70 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si 15]]}
   [0xda 0x70 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si -16]]}
   [0xda 0x71 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x71 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di 15]]}
   [0xda 0x71 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di -16]]}
   [0xda 0x72 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x72 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si 15]]}
   [0xda 0x72 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si -16]]}
   [0xda 0x73 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x73 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di 15]]}
   [0xda 0x73 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di -16]]}
   [0xda 0x74 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/si]]}
   [0xda 0x74 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/si 15]]}
   [0xda 0x74 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/si -16]]}
   [0xda 0x75 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/di]]}
   [0xda 0x75 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/di 15]]}
   [0xda 0x75 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/di -16]]}
   [0xda 0x76 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp]]}
   [0xda 0x76 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp 15]]}
   [0xda 0x76 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp -16]]}
   [0xda 0x77 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx]]}
   [0xda 0x77 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx 15]]}
   [0xda 0x77 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx -16]]}
   [0xda 0x78 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x78 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si 15]]}
   [0xda 0x78 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si -16]]}
   [0xda 0x79 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x79 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di 15]]}
   [0xda 0x79 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di -16]]}
   [0xda 0x7a 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x7a 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si 15]]}
   [0xda 0x7a 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si -16]]}
   [0xda 0x7b 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x7b 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di 15]]}
   [0xda 0x7b 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di -16]]}
   [0xda 0x7c 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/si]]}
   [0xda 0x7c 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/si 15]]}
   [0xda 0x7c 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/si -16]]}
   [0xda 0x7d 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/di]]}
   [0xda 0x7d 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/di 15]]}
   [0xda 0x7d 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/di -16]]}
   [0xda 0x7e 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp]]}
   [0xda 0x7e 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp 15]]}
   [0xda 0x7e 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp -16]]}
   [0xda 0x7f 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx]]}
   [0xda 0x7f 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx 15]]}
   [0xda 0x7f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx -16]]}
   [0xda 0x80 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x80 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si 4080]]}
   [0xda 0x80 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si -4081]]}
   [0xda 0x81 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x81 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di 4080]]}
   [0xda 0x81 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di -4081]]}
   [0xda 0x82 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x82 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si 4080]]}
   [0xda 0x82 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si -4081]]}
   [0xda 0x83 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x83 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di 4080]]}
   [0xda 0x83 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di -4081]]}
   [0xda 0x84 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/si]]}
   [0xda 0x84 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/si 4080]]}
   [0xda 0x84 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/si -4081]]}
   [0xda 0x85 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/di]]}
   [0xda 0x85 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/di 4080]]}
   [0xda 0x85 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/di -4081]]}
   [0xda 0x86 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp]]}
   [0xda 0x86 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp 4080]]}
   [0xda 0x86 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp -4081]]}
   [0xda 0x87 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx]]}
   [0xda 0x87 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx 4080]]}
   [0xda 0x87 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx -4081]]}
   [0xda 0x88 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x88 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si 4080]]}
   [0xda 0x88 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si -4081]]}
   [0xda 0x89 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x89 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di 4080]]}
   [0xda 0x89 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di -4081]]}
   [0xda 0x8a 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x8a 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si 4080]]}
   [0xda 0x8a 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si -4081]]}
   [0xda 0x8b 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x8b 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di 4080]]}
   [0xda 0x8b 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di -4081]]}
   [0xda 0x8c 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/si]]}
   [0xda 0x8c 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/si 4080]]}
   [0xda 0x8c 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/si -4081]]}
   [0xda 0x8d 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/di]]}
   [0xda 0x8d 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/di 4080]]}
   [0xda 0x8d 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/di -4081]]}
   [0xda 0x8e 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp]]}
   [0xda 0x8e 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp 4080]]}
   [0xda 0x8e 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp -4081]]}
   [0xda 0x8f 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx]]}
   [0xda 0x8f 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx 4080]]}
   [0xda 0x8f 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx -4081]]}
   [0xda 0x90 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x90 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si 4080]]}
   [0xda 0x90 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si -4081]]}
   [0xda 0x91 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x91 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di 4080]]}
   [0xda 0x91 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di -4081]]}
   [0xda 0x92 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x92 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si 4080]]}
   [0xda 0x92 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si -4081]]}
   [0xda 0x93 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x93 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di 4080]]}
   [0xda 0x93 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di -4081]]}
   [0xda 0x94 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/si]]}
   [0xda 0x94 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/si 4080]]}
   [0xda 0x94 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/si -4081]]}
   [0xda 0x95 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/di]]}
   [0xda 0x95 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/di 4080]]}
   [0xda 0x95 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/di -4081]]}
   [0xda 0x96 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp]]}
   [0xda 0x96 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp 4080]]}
   [0xda 0x96 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp -4081]]}
   [0xda 0x97 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx]]}
   [0xda 0x97 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx 4080]]}
   [0xda 0x97 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx -4081]]}
   [0xda 0x98 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si]]}
   [0xda 0x98 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si 4080]]}
   [0xda 0x98 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si -4081]]}
   [0xda 0x99 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di]]}
   [0xda 0x99 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di 4080]]}
   [0xda 0x99 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di -4081]]}
   [0xda 0x9a 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si]]}
   [0xda 0x9a 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si 4080]]}
   [0xda 0x9a 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si -4081]]}
   [0xda 0x9b 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di]]}
   [0xda 0x9b 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di 4080]]}
   [0xda 0x9b 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di -4081]]}
   [0xda 0x9c 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/si]]}
   [0xda 0x9c 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/si 4080]]}
   [0xda 0x9c 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/si -4081]]}
   [0xda 0x9d 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/di]]}
   [0xda 0x9d 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/di 4080]]}
   [0xda 0x9d 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/di -4081]]}
   [0xda 0x9e 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp]]}
   [0xda 0x9e 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp 4080]]}
   [0xda 0x9e 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp -4081]]}
   [0xda 0x9f 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx]]}
   [0xda 0x9f 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx 4080]]}
   [0xda 0x9f 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx -4081]]}
   [0xda 0xa0 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si]]}
   [0xda 0xa0 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si 4080]]}
   [0xda 0xa0 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si -4081]]}
   [0xda 0xa1 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di]]}
   [0xda 0xa1 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di 4080]]}
   [0xda 0xa1 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di -4081]]}
   [0xda 0xa2 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si]]}
   [0xda 0xa2 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si 4080]]}
   [0xda 0xa2 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si -4081]]}
   [0xda 0xa3 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di]]}
   [0xda 0xa3 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di 4080]]}
   [0xda 0xa3 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di -4081]]}
   [0xda 0xa4 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/si]]}
   [0xda 0xa4 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/si 4080]]}
   [0xda 0xa4 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/si -4081]]}
   [0xda 0xa5 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/di]]}
   [0xda 0xa5 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/di 4080]]}
   [0xda 0xa5 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/di -4081]]}
   [0xda 0xa6 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp]]}
   [0xda 0xa6 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp 4080]]}
   [0xda 0xa6 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp -4081]]}
   [0xda 0xa7 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx]]}
   [0xda 0xa7 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx 4080]]}
   [0xda 0xa7 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx -4081]]}
   [0xda 0xa8 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si]]}
   [0xda 0xa8 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si 4080]]}
   [0xda 0xa8 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si -4081]]}
   [0xda 0xa9 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di]]}
   [0xda 0xa9 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di 4080]]}
   [0xda 0xa9 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di -4081]]}
   [0xda 0xaa 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si]]}
   [0xda 0xaa 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si 4080]]}
   [0xda 0xaa 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si -4081]]}
   [0xda 0xab 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di]]}
   [0xda 0xab 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di 4080]]}
   [0xda 0xab 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di -4081]]}
   [0xda 0xac 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/si]]}
   [0xda 0xac 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/si 4080]]}
   [0xda 0xac 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/si -4081]]}
   [0xda 0xad 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/di]]}
   [0xda 0xad 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/di 4080]]}
   [0xda 0xad 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/di -4081]]}
   [0xda 0xae 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp]]}
   [0xda 0xae 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp 4080]]}
   [0xda 0xae 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp -4081]]}
   [0xda 0xaf 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx]]}
   [0xda 0xaf 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx 4080]]}
   [0xda 0xaf 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx -4081]]}
   [0xda 0xb0 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si]]}
   [0xda 0xb0 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si 4080]]}
   [0xda 0xb0 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si -4081]]}
   [0xda 0xb1 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di]]}
   [0xda 0xb1 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di 4080]]}
   [0xda 0xb1 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di -4081]]}
   [0xda 0xb2 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si]]}
   [0xda 0xb2 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si 4080]]}
   [0xda 0xb2 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si -4081]]}
   [0xda 0xb3 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di]]}
   [0xda 0xb3 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di 4080]]}
   [0xda 0xb3 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di -4081]]}
   [0xda 0xb4 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/si]]}
   [0xda 0xb4 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/si 4080]]}
   [0xda 0xb4 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/si -4081]]}
   [0xda 0xb5 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/di]]}
   [0xda 0xb5 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/di 4080]]}
   [0xda 0xb5 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/di -4081]]}
   [0xda 0xb6 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp]]}
   [0xda 0xb6 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp 4080]]}
   [0xda 0xb6 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp -4081]]}
   [0xda 0xb7 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx]]}
   [0xda 0xb7 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx 4080]]}
   [0xda 0xb7 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx -4081]]}
   [0xda 0xb8 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si]]}
   [0xda 0xb8 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si 4080]]}
   [0xda 0xb8 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si -4081]]}
   [0xda 0xb9 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di]]}
   [0xda 0xb9 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di 4080]]}
   [0xda 0xb9 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di -4081]]}
   [0xda 0xba 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si]]}
   [0xda 0xba 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si 4080]]}
   [0xda 0xba 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si -4081]]}
   [0xda 0xbb 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di]]}
   [0xda 0xbb 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di 4080]]}
   [0xda 0xbb 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di -4081]]}
   [0xda 0xbc 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/si]]}
   [0xda 0xbc 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/si 4080]]}
   [0xda 0xbc 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/si -4081]]}
   [0xda 0xbd 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/di]]}
   [0xda 0xbd 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/di 4080]]}
   [0xda 0xbd 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/di -4081]]}
   [0xda 0xbe 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp]]}
   [0xda 0xbe 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp 4080]]}
   [0xda 0xbe 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp -4081]]}
   [0xda 0xbf 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx]]}
   [0xda 0xbf 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx 4080]]}
   [0xda 0xbf 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx -4081]]}
   [0xda 0xc0] nil
   [0xda 0xc1] nil
   [0xda 0xc2] nil
   [0xda 0xc3] nil
   [0xda 0xc4] nil
   [0xda 0xc5] nil
   [0xda 0xc6] nil
   [0xda 0xc7] nil
   [0xda 0xc8] nil
   [0xda 0xc9] nil
   [0xda 0xca] nil
   [0xda 0xcb] nil
   [0xda 0xcc] nil
   [0xda 0xcd] nil
   [0xda 0xce] nil
   [0xda 0xcf] nil
   [0xda 0xd0] nil
   [0xda 0xd1] nil
   [0xda 0xd2] nil
   [0xda 0xd3] nil
   [0xda 0xd4] nil
   [0xda 0xd5] nil
   [0xda 0xd6] nil
   [0xda 0xd7] nil
   [0xda 0xd8] nil
   [0xda 0xd9] nil
   [0xda 0xda] nil
   [0xda 0xdb] nil
   [0xda 0xdc] nil
   [0xda 0xdd] nil
   [0xda 0xde] nil
   [0xda 0xdf] nil
   [0xda 0xe1] nil
   [0xda 0xe2] nil
   [0xda 0xe3] nil
   [0xda 0xe4] nil
   [0xda 0xe5] nil
   [0xda 0xe6] nil
   [0xda 0xe7] nil
   [0xda 0xe8] nil
   [0xda 0xe9] {::i/tag ::i/fucompp}
   [0xda 0xea] nil
   [0xda 0xeb] nil
   [0xda 0xec] nil
   [0xda 0xed] nil
   [0xda 0xee] nil
   [0xda 0xef] nil
   [0xda 0xf1] nil
   [0xda 0xf2] nil
   [0xda 0xf3] nil
   [0xda 0xf4] nil
   [0xda 0xf5] nil
   [0xda 0xf6] nil
   [0xda 0xf7] nil
   [0xda 0xf9] nil
   [0xda 0xfa] nil
   [0xda 0xfb] nil
   [0xda 0xfc] nil
   [0xda 0xfd] nil
   [0xda 0xfe] nil
   [0xda 0xff] nil})


(def decode-examples-0xdb
  {[0xdb 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x01] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x02] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x03] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x04] {::i/tag ::i/fild, ::i/args [[::r/si]]}
   [0xdb 0x05] {::i/tag ::i/fild, ::i/args [[::r/di]]}
   [0xdb 0x06 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[0x0ff0]]}
   [0xdb 0x06 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[0xf00f]]}
   [0xdb 0x07] {::i/tag ::i/fild, ::i/args [[::r/bx]]}
   [0xdb 0x08] nil
   [0xdb 0x09] nil
   [0xdb 0x0a] nil
   [0xdb 0x0b] nil
   [0xdb 0x0c] nil
   [0xdb 0x0d] nil
   [0xdb 0x0e 0x90 0x90] nil
   [0xdb 0x0f] nil
   [0xdb 0x10] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x11] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x12] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x13] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x14] {::i/tag ::i/fist, ::i/args [[::r/si]]}
   [0xdb 0x15] {::i/tag ::i/fist, ::i/args [[::r/di]]}
   [0xdb 0x16 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[0x0ff0]]}
   [0xdb 0x16 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[0xf00f]]}
   [0xdb 0x17] {::i/tag ::i/fist, ::i/args [[::r/bx]]}
   [0xdb 0x18] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x19] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x1a] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x1b] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x1c] {::i/tag ::i/fistp, ::i/args [[::r/si]]}
   [0xdb 0x1d] {::i/tag ::i/fistp, ::i/args [[::r/di]]}
   [0xdb 0x1e 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[0x0ff0]]}
   [0xdb 0x1e 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[0xf00f]]}
   [0xdb 0x1f] {::i/tag ::i/fistp, ::i/args [[::r/bx]]}
   [0xdb 0x20] nil
   [0xdb 0x21] nil
   [0xdb 0x22] nil
   [0xdb 0x23] nil
   [0xdb 0x24] nil
   [0xdb 0x25] nil
   [0xdb 0x26 0x90 0x90] nil
   [0xdb 0x27] nil
   [0xdb 0x28] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x29] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x2a] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x2b] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x2c] {::i/tag ::i/fld, ::i/args [[::r/si]]}
   [0xdb 0x2d] {::i/tag ::i/fld, ::i/args [[::r/di]]}
   [0xdb 0x2e 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[0x0ff0]]}
   [0xdb 0x2e 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[0xf00f]]}
   [0xdb 0x2f] {::i/tag ::i/fld, ::i/args [[::r/bx]]}
   [0xdb 0x30] nil
   [0xdb 0x31] nil
   [0xdb 0x32] nil
   [0xdb 0x33] nil
   [0xdb 0x34] nil
   [0xdb 0x35] nil
   [0xdb 0x36 0x90 0x90] nil
   [0xdb 0x37] nil
   [0xdb 0x38] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x39] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x3a] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x3b] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x3c] {::i/tag ::i/fstp, ::i/args [[::r/si]]}
   [0xdb 0x3d] {::i/tag ::i/fstp, ::i/args [[::r/di]]}
   [0xdb 0x3e 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[0x0ff0]]}
   [0xdb 0x3e 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[0xf00f]]}
   [0xdb 0x3f] {::i/tag ::i/fstp, ::i/args [[::r/bx]]}
   [0xdb 0x40 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x40 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si 15]]}
   [0xdb 0x40 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si -16]]}
   [0xdb 0x41 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x41 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di 15]]}
   [0xdb 0x41 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di -16]]}
   [0xdb 0x42 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x42 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si 15]]}
   [0xdb 0x42 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si -16]]}
   [0xdb 0x43 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x43 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di 15]]}
   [0xdb 0x43 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di -16]]}
   [0xdb 0x44 0x00] {::i/tag ::i/fild, ::i/args [[::r/si]]}
   [0xdb 0x44 0x0f] {::i/tag ::i/fild, ::i/args [[::r/si 15]]}
   [0xdb 0x44 0xf0] {::i/tag ::i/fild, ::i/args [[::r/si -16]]}
   [0xdb 0x45 0x00] {::i/tag ::i/fild, ::i/args [[::r/di]]}
   [0xdb 0x45 0x0f] {::i/tag ::i/fild, ::i/args [[::r/di 15]]}
   [0xdb 0x45 0xf0] {::i/tag ::i/fild, ::i/args [[::r/di -16]]}
   [0xdb 0x46 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp]]}
   [0xdb 0x46 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp 15]]}
   [0xdb 0x46 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp -16]]}
   [0xdb 0x47 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx]]}
   [0xdb 0x47 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx 15]]}
   [0xdb 0x47 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx -16]]}
   [0xdb 0x48 0x90] nil
   [0xdb 0x49 0x90] nil
   [0xdb 0x4a 0x90] nil
   [0xdb 0x4b 0x90] nil
   [0xdb 0x4c 0x90] nil
   [0xdb 0x4d 0x90] nil
   [0xdb 0x4e 0x90] nil
   [0xdb 0x4f 0x90] nil
   [0xdb 0x50 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x50 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si 15]]}
   [0xdb 0x50 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si -16]]}
   [0xdb 0x51 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x51 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di 15]]}
   [0xdb 0x51 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di -16]]}
   [0xdb 0x52 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x52 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si 15]]}
   [0xdb 0x52 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si -16]]}
   [0xdb 0x53 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x53 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di 15]]}
   [0xdb 0x53 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di -16]]}
   [0xdb 0x54 0x00] {::i/tag ::i/fist, ::i/args [[::r/si]]}
   [0xdb 0x54 0x0f] {::i/tag ::i/fist, ::i/args [[::r/si 15]]}
   [0xdb 0x54 0xf0] {::i/tag ::i/fist, ::i/args [[::r/si -16]]}
   [0xdb 0x55 0x00] {::i/tag ::i/fist, ::i/args [[::r/di]]}
   [0xdb 0x55 0x0f] {::i/tag ::i/fist, ::i/args [[::r/di 15]]}
   [0xdb 0x55 0xf0] {::i/tag ::i/fist, ::i/args [[::r/di -16]]}
   [0xdb 0x56 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp]]}
   [0xdb 0x56 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp 15]]}
   [0xdb 0x56 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp -16]]}
   [0xdb 0x57 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx]]}
   [0xdb 0x57 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx 15]]}
   [0xdb 0x57 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx -16]]}
   [0xdb 0x58 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x58 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si 15]]}
   [0xdb 0x58 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si -16]]}
   [0xdb 0x59 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x59 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di 15]]}
   [0xdb 0x59 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di -16]]}
   [0xdb 0x5a 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x5a 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si 15]]}
   [0xdb 0x5a 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si -16]]}
   [0xdb 0x5b 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x5b 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di 15]]}
   [0xdb 0x5b 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di -16]]}
   [0xdb 0x5c 0x00] {::i/tag ::i/fistp, ::i/args [[::r/si]]}
   [0xdb 0x5c 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/si 15]]}
   [0xdb 0x5c 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/si -16]]}
   [0xdb 0x5d 0x00] {::i/tag ::i/fistp, ::i/args [[::r/di]]}
   [0xdb 0x5d 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/di 15]]}
   [0xdb 0x5d 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/di -16]]}
   [0xdb 0x5e 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp]]}
   [0xdb 0x5e 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp 15]]}
   [0xdb 0x5e 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp -16]]}
   [0xdb 0x5f 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx]]}
   [0xdb 0x5f 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx 15]]}
   [0xdb 0x5f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx -16]]}
   [0xdb 0x60 0x90] nil
   [0xdb 0x61 0x90] nil
   [0xdb 0x62 0x90] nil
   [0xdb 0x63 0x90] nil
   [0xdb 0x64 0x90] nil
   [0xdb 0x65 0x90] nil
   [0xdb 0x66 0x90] nil
   [0xdb 0x67 0x90] nil
   [0xdb 0x68 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x68 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si 15]]}
   [0xdb 0x68 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si -16]]}
   [0xdb 0x69 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x69 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di 15]]}
   [0xdb 0x69 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di -16]]}
   [0xdb 0x6a 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x6a 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si 15]]}
   [0xdb 0x6a 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si -16]]}
   [0xdb 0x6b 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x6b 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di 15]]}
   [0xdb 0x6b 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di -16]]}
   [0xdb 0x6c 0x00] {::i/tag ::i/fld, ::i/args [[::r/si]]}
   [0xdb 0x6c 0x0f] {::i/tag ::i/fld, ::i/args [[::r/si 15]]}
   [0xdb 0x6c 0xf0] {::i/tag ::i/fld, ::i/args [[::r/si -16]]}
   [0xdb 0x6d 0x00] {::i/tag ::i/fld, ::i/args [[::r/di]]}
   [0xdb 0x6d 0x0f] {::i/tag ::i/fld, ::i/args [[::r/di 15]]}
   [0xdb 0x6d 0xf0] {::i/tag ::i/fld, ::i/args [[::r/di -16]]}
   [0xdb 0x6e 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp]]}
   [0xdb 0x6e 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp 15]]}
   [0xdb 0x6e 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp -16]]}
   [0xdb 0x6f 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx]]}
   [0xdb 0x6f 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx 15]]}
   [0xdb 0x6f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx -16]]}
   [0xdb 0x70 0x90] nil
   [0xdb 0x71 0x90] nil
   [0xdb 0x72 0x90] nil
   [0xdb 0x73 0x90] nil
   [0xdb 0x74 0x90] nil
   [0xdb 0x75 0x90] nil
   [0xdb 0x76 0x90] nil
   [0xdb 0x77 0x90] nil
   [0xdb 0x78 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x78 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si 15]]}
   [0xdb 0x78 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si -16]]}
   [0xdb 0x79 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x79 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di 15]]}
   [0xdb 0x79 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di -16]]}
   [0xdb 0x7a 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x7a 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si 15]]}
   [0xdb 0x7a 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si -16]]}
   [0xdb 0x7b 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x7b 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di 15]]}
   [0xdb 0x7b 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di -16]]}
   [0xdb 0x7c 0x00] {::i/tag ::i/fstp, ::i/args [[::r/si]]}
   [0xdb 0x7c 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/si 15]]}
   [0xdb 0x7c 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/si -16]]}
   [0xdb 0x7d 0x00] {::i/tag ::i/fstp, ::i/args [[::r/di]]}
   [0xdb 0x7d 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/di 15]]}
   [0xdb 0x7d 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/di -16]]}
   [0xdb 0x7e 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp]]}
   [0xdb 0x7e 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp 15]]}
   [0xdb 0x7e 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp -16]]}
   [0xdb 0x7f 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx]]}
   [0xdb 0x7f 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx 15]]}
   [0xdb 0x7f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx -16]]}
   [0xdb 0x80 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x80 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si 4080]]}
   [0xdb 0x80 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si -4081]]}
   [0xdb 0x81 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x81 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di 4080]]}
   [0xdb 0x81 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di -4081]]}
   [0xdb 0x82 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x82 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si 4080]]}
   [0xdb 0x82 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si -4081]]}
   [0xdb 0x83 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x83 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di 4080]]}
   [0xdb 0x83 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di -4081]]}
   [0xdb 0x84 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/si]]}
   [0xdb 0x84 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/si 4080]]}
   [0xdb 0x84 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/si -4081]]}
   [0xdb 0x85 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/di]]}
   [0xdb 0x85 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/di 4080]]}
   [0xdb 0x85 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/di -4081]]}
   [0xdb 0x86 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp]]}
   [0xdb 0x86 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp 4080]]}
   [0xdb 0x86 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp -4081]]}
   [0xdb 0x87 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx]]}
   [0xdb 0x87 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx 4080]]}
   [0xdb 0x87 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx -4081]]}
   [0xdb 0x88 0x90 0x90] nil
   [0xdb 0x89 0x90 0x90] nil
   [0xdb 0x8a 0x90 0x90] nil
   [0xdb 0x8b 0x90 0x90] nil
   [0xdb 0x8c 0x90 0x90] nil
   [0xdb 0x8d 0x90 0x90] nil
   [0xdb 0x8e 0x90 0x90] nil
   [0xdb 0x8f 0x90 0x90] nil
   [0xdb 0x90 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x90 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si 4080]]}
   [0xdb 0x90 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si -4081]]}
   [0xdb 0x91 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x91 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di 4080]]}
   [0xdb 0x91 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di -4081]]}
   [0xdb 0x92 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x92 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si 4080]]}
   [0xdb 0x92 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si -4081]]}
   [0xdb 0x93 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x93 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di 4080]]}
   [0xdb 0x93 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di -4081]]}
   [0xdb 0x94 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/si]]}
   [0xdb 0x94 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/si 4080]]}
   [0xdb 0x94 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/si -4081]]}
   [0xdb 0x95 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/di]]}
   [0xdb 0x95 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/di 4080]]}
   [0xdb 0x95 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/di -4081]]}
   [0xdb 0x96 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp]]}
   [0xdb 0x96 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp 4080]]}
   [0xdb 0x96 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp -4081]]}
   [0xdb 0x97 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx]]}
   [0xdb 0x97 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx 4080]]}
   [0xdb 0x97 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx -4081]]}
   [0xdb 0x98 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0x98 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si 4080]]}
   [0xdb 0x98 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si -4081]]}
   [0xdb 0x99 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0x99 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di 4080]]}
   [0xdb 0x99 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di -4081]]}
   [0xdb 0x9a 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0x9a 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si 4080]]}
   [0xdb 0x9a 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si -4081]]}
   [0xdb 0x9b 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0x9b 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di 4080]]}
   [0xdb 0x9b 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di -4081]]}
   [0xdb 0x9c 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/si]]}
   [0xdb 0x9c 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/si 4080]]}
   [0xdb 0x9c 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/si -4081]]}
   [0xdb 0x9d 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/di]]}
   [0xdb 0x9d 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/di 4080]]}
   [0xdb 0x9d 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/di -4081]]}
   [0xdb 0x9e 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp]]}
   [0xdb 0x9e 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp 4080]]}
   [0xdb 0x9e 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp -4081]]}
   [0xdb 0x9f 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx]]}
   [0xdb 0x9f 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx 4080]]}
   [0xdb 0x9f 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx -4081]]}
   [0xdb 0xa0 0x90 0x90] nil
   [0xdb 0xa1 0x90 0x90] nil
   [0xdb 0xa2 0x90 0x90] nil
   [0xdb 0xa3 0x90 0x90] nil
   [0xdb 0xa4 0x90 0x90] nil
   [0xdb 0xa5 0x90 0x90] nil
   [0xdb 0xa6 0x90 0x90] nil
   [0xdb 0xa7 0x90 0x90] nil
   [0xdb 0xa8 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0xa8 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si 4080]]}
   [0xdb 0xa8 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si -4081]]}
   [0xdb 0xa9 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0xa9 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di 4080]]}
   [0xdb 0xa9 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di -4081]]}
   [0xdb 0xaa 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0xaa 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si 4080]]}
   [0xdb 0xaa 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si -4081]]}
   [0xdb 0xab 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0xab 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di 4080]]}
   [0xdb 0xab 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di -4081]]}
   [0xdb 0xac 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/si]]}
   [0xdb 0xac 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/si 4080]]}
   [0xdb 0xac 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/si -4081]]}
   [0xdb 0xad 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/di]]}
   [0xdb 0xad 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/di 4080]]}
   [0xdb 0xad 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/di -4081]]}
   [0xdb 0xae 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp]]}
   [0xdb 0xae 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp 4080]]}
   [0xdb 0xae 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp -4081]]}
   [0xdb 0xaf 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx]]}
   [0xdb 0xaf 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx 4080]]}
   [0xdb 0xaf 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx -4081]]}
   [0xdb 0xb0 0x90 0x90] nil
   [0xdb 0xb1 0x90 0x90] nil
   [0xdb 0xb2 0x90 0x90] nil
   [0xdb 0xb3 0x90 0x90] nil
   [0xdb 0xb4 0x90 0x90] nil
   [0xdb 0xb5 0x90 0x90] nil
   [0xdb 0xb6 0x90 0x90] nil
   [0xdb 0xb7 0x90 0x90] nil
   [0xdb 0xb8 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si]]}
   [0xdb 0xb8 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si 4080]]}
   [0xdb 0xb8 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si -4081]]}
   [0xdb 0xb9 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di]]}
   [0xdb 0xb9 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di 4080]]}
   [0xdb 0xb9 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di -4081]]}
   [0xdb 0xba 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si]]}
   [0xdb 0xba 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si 4080]]}
   [0xdb 0xba 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si -4081]]}
   [0xdb 0xbb 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di]]}
   [0xdb 0xbb 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di 4080]]}
   [0xdb 0xbb 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di -4081]]}
   [0xdb 0xbc 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/si]]}
   [0xdb 0xbc 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/si 4080]]}
   [0xdb 0xbc 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/si -4081]]}
   [0xdb 0xbd 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/di]]}
   [0xdb 0xbd 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/di 4080]]}
   [0xdb 0xbd 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/di -4081]]}
   [0xdb 0xbe 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp]]}
   [0xdb 0xbe 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp 4080]]}
   [0xdb 0xbe 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp -4081]]}
   [0xdb 0xbf 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx]]}
   [0xdb 0xbf 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx 4080]]}
   [0xdb 0xbf 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx -4081]]}
   [0xdb 0xc0] nil
   [0xdb 0xc1] nil
   [0xdb 0xc2] nil
   [0xdb 0xc3] nil
   [0xdb 0xc4] nil
   [0xdb 0xc5] nil
   [0xdb 0xc6] nil
   [0xdb 0xc7] nil
   [0xdb 0xc8] nil
   [0xdb 0xc9] nil
   [0xdb 0xca] nil
   [0xdb 0xcb] nil
   [0xdb 0xcc] nil
   [0xdb 0xcd] nil
   [0xdb 0xce] nil
   [0xdb 0xcf] nil
   [0xdb 0xd0] nil
   [0xdb 0xd1] nil
   [0xdb 0xd2] nil
   [0xdb 0xd3] nil
   [0xdb 0xd4] nil
   [0xdb 0xd5] nil
   [0xdb 0xd6] nil
   [0xdb 0xd7] nil
   [0xdb 0xd8] nil
   [0xdb 0xd9] nil
   [0xdb 0xda] nil
   [0xdb 0xdb] nil
   [0xdb 0xdc] nil
   [0xdb 0xdd] nil
   [0xdb 0xde] nil
   [0xdb 0xdf] nil
   [0xdb 0xe0] {::i/tag ::i/fneni}
   [0xdb 0xe1] {::i/tag ::i/fndisi}
   [0xdb 0xe2] {::i/tag ::i/fnclex}
   [0xdb 0xe3] {::i/tag ::i/fninit}
   [0xdb 0xe4] {::i/tag ::i/fnsetpm}
   [0xdb 0xe5] {::i/tag ::i/frstpm}
   [0xdb 0xe6] nil
   [0xdb 0xe7] nil
   [0xdb 0xe8] nil
   [0xdb 0xe9] nil
   [0xdb 0xea] nil
   [0xdb 0xeb] nil
   [0xdb 0xec] nil
   [0xdb 0xed] nil
   [0xdb 0xee] nil
   [0xdb 0xef] nil
   [0xdb 0xf0] nil
   [0xdb 0xf1] nil
   [0xdb 0xf2] nil
   [0xdb 0xf3] nil
   [0xdb 0xf4] nil
   [0xdb 0xf5] nil
   [0xdb 0xf6] nil
   [0xdb 0xf7] nil
   [0xdb 0xf8] {::i/tag ::i/fnop}
   [0xdb 0xf9] nil
   [0xdb 0xfa] nil
   [0xdb 0xfb] nil
   [0xdb 0xfc] nil
   [0xdb 0xfd] nil
   [0xdb 0xfe] nil
   [0xdb 0xff] nil})


(def decode-examples-0xdc-0xdd
  {[0xdc 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x01] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x02] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x03] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x04] {::i/tag ::i/fadd, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x05] {::i/tag ::i/fadd, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x06 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdc 0x06 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdc 0x07] {::i/tag ::i/fadd, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x08] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x09] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x0a] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x0b] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x0c] {::i/tag ::i/fmul, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x0d] {::i/tag ::i/fmul, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x0e 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdc 0x0e 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdc 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x10] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x11] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x12] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x13] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x14] {::i/tag ::i/fcom, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x15] {::i/tag ::i/fcom, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x16 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdc 0x16 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdc 0x17] {::i/tag ::i/fcom, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x18] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x19] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x1a] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x1b] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x1c] {::i/tag ::i/fcomp, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x1d] {::i/tag ::i/fcomp, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x1e 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdc 0x1e 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdc 0x1f] {::i/tag ::i/fcomp, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x20] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x21] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x22] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x23] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x24] {::i/tag ::i/fsub, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x25] {::i/tag ::i/fsub, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x26 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdc 0x26 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdc 0x27] {::i/tag ::i/fsub, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x28] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x29] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x2a] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x2b] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x2c] {::i/tag ::i/fsubr, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x2d] {::i/tag ::i/fsubr, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x2e 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdc 0x2e 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdc 0x2f] {::i/tag ::i/fsubr, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x30] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x31] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x32] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x33] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x34] {::i/tag ::i/fdiv, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x35] {::i/tag ::i/fdiv, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x36 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdc 0x36 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdc 0x37] {::i/tag ::i/fdiv, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x38] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x39] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x3a] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x3b] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x3c] {::i/tag ::i/fdivr, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x3d] {::i/tag ::i/fdivr, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x3e 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdc 0x3e 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdc 0x3f] {::i/tag ::i/fdivr, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x40 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x40 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x40 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x41 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x41 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x41 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x42 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x42 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x42 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x43 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x43 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x43 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x44 0x00] {::i/tag ::i/fadd, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x44 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x44 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x45 0x00] {::i/tag ::i/fadd, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x45 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x45 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x46 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x46 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdc 0x46 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdc 0x47 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x47 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdc 0x47 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdc 0x48 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x48 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x48 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x49 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x49 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x49 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x4a 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x4a 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x4a 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x4b 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x4b 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x4b 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x4c 0x00] {::i/tag ::i/fmul, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x4c 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x4c 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x4d 0x00] {::i/tag ::i/fmul, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x4d 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x4d 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x4e 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x4e 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdc 0x4e 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdc 0x4f 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x4f 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdc 0x4f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdc 0x50 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x50 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x50 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x51 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x51 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x51 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x52 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x52 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x52 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x53 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x53 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x53 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x54 0x00] {::i/tag ::i/fcom, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x54 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x54 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x55 0x00] {::i/tag ::i/fcom, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x55 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x55 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x56 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x56 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdc 0x56 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdc 0x57 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x57 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdc 0x57 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdc 0x58 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x58 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x58 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x59 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x59 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x59 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x5a 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x5a 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x5a 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x5b 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x5b 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x5b 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x5c 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x5c 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x5c 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x5d 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x5d 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x5d 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x5e 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x5e 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdc 0x5e 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdc 0x5f 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x5f 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdc 0x5f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdc 0x60 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x60 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x60 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x61 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x61 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x61 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x62 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x62 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x62 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x63 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x63 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x63 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x64 0x00] {::i/tag ::i/fsub, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x64 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x64 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x65 0x00] {::i/tag ::i/fsub, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x65 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x65 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x66 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x66 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdc 0x66 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdc 0x67 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x67 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdc 0x67 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdc 0x68 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x68 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x68 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x69 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x69 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x69 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x6a 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x6a 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x6a 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x6b 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x6b 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x6b 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x6c 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x6c 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x6c 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x6d 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x6d 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x6d 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x6e 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x6e 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdc 0x6e 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdc 0x6f 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x6f 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdc 0x6f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdc 0x70 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x70 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x70 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x71 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x71 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x71 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x72 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x72 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x72 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x73 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x73 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x73 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x74 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x74 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x74 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x75 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x75 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x75 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x76 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x76 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdc 0x76 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdc 0x77 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x77 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdc 0x77 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdc 0x78 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x78 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x78 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x79 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x79 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x79 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x7a 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x7a 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x7a 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x7b 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x7b 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x7b 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x7c 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x7c 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdc 0x7c 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdc 0x7d 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x7d 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdc 0x7d 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdc 0x7e 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x7e 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdc 0x7e 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdc 0x7f 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x7f 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdc 0x7f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdc 0x80 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x80 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x80 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x81 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x81 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x81 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x82 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x82 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x82 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x83 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x83 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x83 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x84 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x84 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x84 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x85 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x85 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x85 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x86 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x86 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdc 0x86 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdc 0x87 0x00 0x00] {::i/tag ::i/fadd, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x87 0xf0 0x0f] {::i/tag ::i/fadd, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdc 0x87 0x0f 0xf0] {::i/tag ::i/fadd, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdc 0x88 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x88 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x88 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x89 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x89 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x89 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x8a 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x8a 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x8a 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x8b 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x8b 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x8b 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x8c 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x8c 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x8c 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x8d 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x8d 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x8d 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x8e 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x8e 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdc 0x8e 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdc 0x8f 0x00 0x00] {::i/tag ::i/fmul, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x8f 0xf0 0x0f] {::i/tag ::i/fmul, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdc 0x8f 0x0f 0xf0] {::i/tag ::i/fmul, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdc 0x90 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x90 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x90 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x91 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x91 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x91 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x92 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x92 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x92 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x93 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x93 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x93 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x94 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x94 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x94 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x95 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x95 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x95 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x96 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x96 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdc 0x96 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdc 0x97 0x00 0x00] {::i/tag ::i/fcom, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x97 0xf0 0x0f] {::i/tag ::i/fcom, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdc 0x97 0x0f 0xf0] {::i/tag ::i/fcom, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdc 0x98 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0x98 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x98 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x99 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0x99 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x99 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x9a 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0x9a 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x9a 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x9b 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0x9b 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x9b 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x9c 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0x9c 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0x9c 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0x9d 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0x9d 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0x9d 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0x9e 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0x9e 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdc 0x9e 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdc 0x9f 0x00 0x00] {::i/tag ::i/fcomp, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0x9f 0xf0 0x0f] {::i/tag ::i/fcomp, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdc 0x9f 0x0f 0xf0] {::i/tag ::i/fcomp, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdc 0xa0 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0xa0 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xa0 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xa1 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0xa1 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xa1 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xa2 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0xa2 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xa2 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xa3 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0xa3 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xa3 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xa4 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0xa4 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xa4 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xa5 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0xa5 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xa5 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xa6 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0xa6 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdc 0xa6 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdc 0xa7 0x00 0x00] {::i/tag ::i/fsub, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0xa7 0xf0 0x0f] {::i/tag ::i/fsub, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdc 0xa7 0x0f 0xf0] {::i/tag ::i/fsub, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdc 0xa8 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0xa8 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xa8 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xa9 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0xa9 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xa9 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xaa 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0xaa 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xaa 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xab 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0xab 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xab 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xac 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0xac 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xac 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xad 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0xad 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xad 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xae 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0xae 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdc 0xae 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdc 0xaf 0x00 0x00] {::i/tag ::i/fsubr, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0xaf 0xf0 0x0f] {::i/tag ::i/fsubr, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdc 0xaf 0x0f 0xf0] {::i/tag ::i/fsubr, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdc 0xb0 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0xb0 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xb0 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xb1 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0xb1 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xb1 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xb2 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0xb2 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xb2 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xb3 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0xb3 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xb3 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xb4 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0xb4 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xb4 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xb5 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0xb5 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xb5 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xb6 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0xb6 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdc 0xb6 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdc 0xb7 0x00 0x00] {::i/tag ::i/fdiv, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0xb7 0xf0 0x0f] {::i/tag ::i/fdiv, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdc 0xb7 0x0f 0xf0] {::i/tag ::i/fdiv, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdc 0xb8 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdc 0xb8 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xb8 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xb9 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdc 0xb9 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xb9 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xba 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdc 0xba 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xba 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xbb 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdc 0xbb 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xbb 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xbc 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdc 0xbc 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdc 0xbc 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdc 0xbd 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdc 0xbd 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdc 0xbd 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdc 0xbe 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdc 0xbe 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdc 0xbe 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdc 0xbf 0x00 0x00] {::i/tag ::i/fdivr, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdc 0xbf 0xf0 0x0f] {::i/tag ::i/fdivr, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdc 0xbf 0x0f 0xf0] {::i/tag ::i/fdivr, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdc 0xc0] {::i/tag ::i/fadd, ::i/args [::r/st0]}
   [0xdc 0xc1] {::i/tag ::i/fadd, ::i/args [::r/st1]}
   [0xdc 0xc2] {::i/tag ::i/fadd, ::i/args [::r/st2]}
   [0xdc 0xc3] {::i/tag ::i/fadd, ::i/args [::r/st3]}
   [0xdc 0xc4] {::i/tag ::i/fadd, ::i/args [::r/st4]}
   [0xdc 0xc5] {::i/tag ::i/fadd, ::i/args [::r/st5]}
   [0xdc 0xc6] {::i/tag ::i/fadd, ::i/args [::r/st6]}
   [0xdc 0xc7] {::i/tag ::i/fadd, ::i/args [::r/st7]}
   [0xdc 0xc8] {::i/tag ::i/fmul, ::i/args [::r/st0]}
   [0xdc 0xc9] {::i/tag ::i/fmul, ::i/args [::r/st1]}
   [0xdc 0xca] {::i/tag ::i/fmul, ::i/args [::r/st2]}
   [0xdc 0xcb] {::i/tag ::i/fmul, ::i/args [::r/st3]}
   [0xdc 0xcc] {::i/tag ::i/fmul, ::i/args [::r/st4]}
   [0xdc 0xcd] {::i/tag ::i/fmul, ::i/args [::r/st5]}
   [0xdc 0xce] {::i/tag ::i/fmul, ::i/args [::r/st6]}
   [0xdc 0xcf] {::i/tag ::i/fmul, ::i/args [::r/st7]}
   [0xdc 0xd0] {::i/tag ::i/fnop}
   [0xdc 0xd1] nil
   [0xdc 0xd2] nil
   [0xdc 0xd3] nil
   [0xdc 0xd4] nil
   [0xdc 0xd5] nil
   [0xdc 0xd6] nil
   [0xdc 0xd7] nil
   [0xdc 0xd8] {::i/tag ::i/fnop}
   [0xdc 0xd9] nil
   [0xdc 0xda] nil
   [0xdc 0xdb] nil
   [0xdc 0xdc] nil
   [0xdc 0xdd] nil
   [0xdc 0xde] nil
   [0xdc 0xdf] nil
   [0xdc 0xe0] {::i/tag ::i/fsub, ::i/args [::r/st0]}
   [0xdc 0xe1] {::i/tag ::i/fsub, ::i/args [::r/st1]}
   [0xdc 0xe2] {::i/tag ::i/fsub, ::i/args [::r/st2]}
   [0xdc 0xe3] {::i/tag ::i/fsub, ::i/args [::r/st3]}
   [0xdc 0xe4] {::i/tag ::i/fsub, ::i/args [::r/st4]}
   [0xdc 0xe5] {::i/tag ::i/fsub, ::i/args [::r/st5]}
   [0xdc 0xe6] {::i/tag ::i/fsub, ::i/args [::r/st6]}
   [0xdc 0xe7] {::i/tag ::i/fsub, ::i/args [::r/st7]}
   [0xdc 0xe8] {::i/tag ::i/fsubr, ::i/args [::r/st0]}
   [0xdc 0xe9] {::i/tag ::i/fsubr, ::i/args [::r/st1]}
   [0xdc 0xea] {::i/tag ::i/fsubr, ::i/args [::r/st2]}
   [0xdc 0xeb] {::i/tag ::i/fsubr, ::i/args [::r/st3]}
   [0xdc 0xec] {::i/tag ::i/fsubr, ::i/args [::r/st4]}
   [0xdc 0xed] {::i/tag ::i/fsubr, ::i/args [::r/st5]}
   [0xdc 0xee] {::i/tag ::i/fsubr, ::i/args [::r/st6]}
   [0xdc 0xef] {::i/tag ::i/fsubr, ::i/args [::r/st7]}
   [0xdc 0xf0] {::i/tag ::i/fdiv, ::i/args [::r/st0]}
   [0xdc 0xf1] {::i/tag ::i/fdiv, ::i/args [::r/st1]}
   [0xdc 0xf2] {::i/tag ::i/fdiv, ::i/args [::r/st2]}
   [0xdc 0xf3] {::i/tag ::i/fdiv, ::i/args [::r/st3]}
   [0xdc 0xf4] {::i/tag ::i/fdiv, ::i/args [::r/st4]}
   [0xdc 0xf5] {::i/tag ::i/fdiv, ::i/args [::r/st5]}
   [0xdc 0xf6] {::i/tag ::i/fdiv, ::i/args [::r/st6]}
   [0xdc 0xf7] {::i/tag ::i/fdiv, ::i/args [::r/st7]}
   [0xdc 0xf8] {::i/tag ::i/fdivr, ::i/args [::r/st0]}
   [0xdc 0xf9] {::i/tag ::i/fdivr, ::i/args [::r/st1]}
   [0xdc 0xfa] {::i/tag ::i/fdivr, ::i/args [::r/st2]}
   [0xdc 0xfb] {::i/tag ::i/fdivr, ::i/args [::r/st3]}
   [0xdc 0xfc] {::i/tag ::i/fdivr, ::i/args [::r/st4]}
   [0xdc 0xfd] {::i/tag ::i/fdivr, ::i/args [::r/st5]}
   [0xdc 0xfe] {::i/tag ::i/fdivr, ::i/args [::r/st6]}
   [0xdc 0xff] {::i/tag ::i/fdivr, ::i/args [::r/st7]}
   [0xdd 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdd 0x01] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdd 0x02] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdd 0x03] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdd 0x04] {::i/tag ::i/fld, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdd 0x05] {::i/tag ::i/fld, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdd 0x06 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdd 0x06 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdd 0x07] {::i/tag ::i/fld, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdd 0x08] nil
   [0xdd 0x09] nil
   [0xdd 0x0a] nil
   [0xdd 0x0b] nil
   [0xdd 0x0c] nil
   [0xdd 0x0d] nil
   [0xdd 0x0e 0x90 0x90] nil
   [0xdd 0x0f] nil
   [0xdd 0x10] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdd 0x11] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdd 0x12] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdd 0x13] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdd 0x14] {::i/tag ::i/fst, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdd 0x15] {::i/tag ::i/fst, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdd 0x16 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdd 0x16 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdd 0x17] {::i/tag ::i/fst, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdd 0x18] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdd 0x19] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdd 0x1a] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdd 0x1b] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdd 0x1c] {::i/tag ::i/fstp, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdd 0x1d] {::i/tag ::i/fstp, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdd 0x1e 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdd 0x1e 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdd 0x1f] {::i/tag ::i/fstp, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdd 0x20] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/si]]}
   [0xdd 0x21] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/di]]}
   [0xdd 0x22] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/si]]}
   [0xdd 0x23] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/di]]}
   [0xdd 0x24] {::i/tag ::i/frstorw, ::i/args [[::r/si]]}
   [0xdd 0x25] {::i/tag ::i/frstorw, ::i/args [[::r/di]]}
   [0xdd 0x26 0xf0 0x0f] {::i/tag ::i/frstorw, ::i/args [[0x0ff0]]}
   [0xdd 0x26 0x0f 0xf0] {::i/tag ::i/frstorw, ::i/args [[0xf00f]]}
   [0xdd 0x27] {::i/tag ::i/frstorw, ::i/args [[::r/bx]]}
   [0xdd 0x28] nil
   [0xdd 0x29] nil
   [0xdd 0x2a] nil
   [0xdd 0x2b] nil
   [0xdd 0x2c] nil
   [0xdd 0x2d] nil
   [0xdd 0x2e 0x90 0x90] nil
   [0xdd 0x2f] nil
   [0xdd 0x30] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/si]]}
   [0xdd 0x31] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/di]]}
   [0xdd 0x32] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/si]]}
   [0xdd 0x33] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/di]]}
   [0xdd 0x34] {::i/tag ::i/fnsavew, ::i/args [[::r/si]]}
   [0xdd 0x35] {::i/tag ::i/fnsavew, ::i/args [[::r/di]]}
   [0xdd 0x36 0xf0 0x0f] {::i/tag ::i/fnsavew, ::i/args [[0x0ff0]]}
   [0xdd 0x36 0x0f 0xf0] {::i/tag ::i/fnsavew, ::i/args [[0xf00f]]}
   [0xdd 0x37] {::i/tag ::i/fnsavew, ::i/args [[::r/bx]]}
   [0xdd 0x38] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/si]]}
   [0xdd 0x39] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/di]]}
   [0xdd 0x3a] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/si]]}
   [0xdd 0x3b] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/di]]}
   [0xdd 0x3c] {::i/tag ::i/fnstsw, ::i/args [[::r/si]]}
   [0xdd 0x3d] {::i/tag ::i/fnstsw, ::i/args [[::r/di]]}
   [0xdd 0x3e 0xf0 0x0f] {::i/tag ::i/fnstsw, ::i/args [[0x0ff0]]}
   [0xdd 0x3e 0x0f 0xf0] {::i/tag ::i/fnstsw, ::i/args [[0xf00f]]}
   [0xdd 0x3f] {::i/tag ::i/fnstsw, ::i/args [[::r/bx]]}
   [0xdd 0x40 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdd 0x40 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdd 0x40 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdd 0x41 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdd 0x41 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdd 0x41 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdd 0x42 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdd 0x42 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdd 0x42 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdd 0x43 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdd 0x43 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdd 0x43 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdd 0x44 0x00] {::i/tag ::i/fld, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdd 0x44 0x0f] {::i/tag ::i/fld, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdd 0x44 0xf0] {::i/tag ::i/fld, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdd 0x45 0x00] {::i/tag ::i/fld, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdd 0x45 0x0f] {::i/tag ::i/fld, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdd 0x45 0xf0] {::i/tag ::i/fld, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdd 0x46 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdd 0x46 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdd 0x46 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdd 0x47 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdd 0x47 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdd 0x47 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdd 0x48 0x90] nil
   [0xdd 0x49 0x90] nil
   [0xdd 0x4a 0x90] nil
   [0xdd 0x4b 0x90] nil
   [0xdd 0x4c 0x90] nil
   [0xdd 0x4d 0x90] nil
   [0xdd 0x4e 0x90] nil
   [0xdd 0x4f 0x90] nil
   [0xdd 0x50 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdd 0x50 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdd 0x50 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdd 0x51 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdd 0x51 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdd 0x51 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdd 0x52 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdd 0x52 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdd 0x52 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdd 0x53 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdd 0x53 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdd 0x53 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdd 0x54 0x00] {::i/tag ::i/fst, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdd 0x54 0x0f] {::i/tag ::i/fst, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdd 0x54 0xf0] {::i/tag ::i/fst, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdd 0x55 0x00] {::i/tag ::i/fst, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdd 0x55 0x0f] {::i/tag ::i/fst, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdd 0x55 0xf0] {::i/tag ::i/fst, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdd 0x56 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdd 0x56 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdd 0x56 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdd 0x57 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdd 0x57 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdd 0x57 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdd 0x58 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdd 0x58 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdd 0x58 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdd 0x59 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdd 0x59 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdd 0x59 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdd 0x5a 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdd 0x5a 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdd 0x5a 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdd 0x5b 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdd 0x5b 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdd 0x5b 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdd 0x5c 0x00] {::i/tag ::i/fstp, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdd 0x5c 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdd 0x5c 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdd 0x5d 0x00] {::i/tag ::i/fstp, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdd 0x5d 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdd 0x5d 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdd 0x5e 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdd 0x5e 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdd 0x5e 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdd 0x5f 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdd 0x5f 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdd 0x5f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdd 0x60 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/si]]}
   [0xdd 0x60 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/si 15]]}
   [0xdd 0x60 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/si -16]]}
   [0xdd 0x61 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/di]]}
   [0xdd 0x61 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/di 15]]}
   [0xdd 0x61 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/di -16]]}
   [0xdd 0x62 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/si]]}
   [0xdd 0x62 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/si 15]]}
   [0xdd 0x62 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/si -16]]}
   [0xdd 0x63 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/di]]}
   [0xdd 0x63 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/di 15]]}
   [0xdd 0x63 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/di -16]]}
   [0xdd 0x64 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/si]]}
   [0xdd 0x64 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/si 15]]}
   [0xdd 0x64 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/si -16]]}
   [0xdd 0x65 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/di]]}
   [0xdd 0x65 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/di 15]]}
   [0xdd 0x65 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/di -16]]}
   [0xdd 0x66 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bp]]}
   [0xdd 0x66 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bp 15]]}
   [0xdd 0x66 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bp -16]]}
   [0xdd 0x67 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bx]]}
   [0xdd 0x67 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bx 15]]}
   [0xdd 0x67 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bx -16]]}
   [0xdd 0x68 0x90] nil
   [0xdd 0x69 0x90] nil
   [0xdd 0x6a 0x90] nil
   [0xdd 0x6b 0x90] nil
   [0xdd 0x6c 0x90] nil
   [0xdd 0x6d 0x90] nil
   [0xdd 0x6e 0x90] nil
   [0xdd 0x6f 0x90] nil
   [0xdd 0x70 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/si]]}
   [0xdd 0x70 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/si 15]]}
   [0xdd 0x70 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/si -16]]}
   [0xdd 0x71 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/di]]}
   [0xdd 0x71 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/di 15]]}
   [0xdd 0x71 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/di -16]]}
   [0xdd 0x72 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/si]]}
   [0xdd 0x72 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/si 15]]}
   [0xdd 0x72 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/si -16]]}
   [0xdd 0x73 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/di]]}
   [0xdd 0x73 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/di 15]]}
   [0xdd 0x73 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/di -16]]}
   [0xdd 0x74 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/si]]}
   [0xdd 0x74 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/si 15]]}
   [0xdd 0x74 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/si -16]]}
   [0xdd 0x75 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/di]]}
   [0xdd 0x75 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/di 15]]}
   [0xdd 0x75 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/di -16]]}
   [0xdd 0x76 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bp]]}
   [0xdd 0x76 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bp 15]]}
   [0xdd 0x76 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bp -16]]}
   [0xdd 0x77 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bx]]}
   [0xdd 0x77 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bx 15]]}
   [0xdd 0x77 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bx -16]]}
   [0xdd 0x78 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/si]]}
   [0xdd 0x78 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/si 15]]}
   [0xdd 0x78 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/si -16]]}
   [0xdd 0x79 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/di]]}
   [0xdd 0x79 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/di 15]]}
   [0xdd 0x79 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/di -16]]}
   [0xdd 0x7a 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/si]]}
   [0xdd 0x7a 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/si 15]]}
   [0xdd 0x7a 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/si -16]]}
   [0xdd 0x7b 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/di]]}
   [0xdd 0x7b 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/di 15]]}
   [0xdd 0x7b 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/di -16]]}
   [0xdd 0x7c 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/si]]}
   [0xdd 0x7c 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/si 15]]}
   [0xdd 0x7c 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/si -16]]}
   [0xdd 0x7d 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/di]]}
   [0xdd 0x7d 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/di 15]]}
   [0xdd 0x7d 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/di -16]]}
   [0xdd 0x7e 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bp]]}
   [0xdd 0x7e 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bp 15]]}
   [0xdd 0x7e 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bp -16]]}
   [0xdd 0x7f 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bx]]}
   [0xdd 0x7f 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bx 15]]}
   [0xdd 0x7f 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bx -16]]}
   [0xdd 0x80 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdd 0x80 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdd 0x80 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdd 0x81 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdd 0x81 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdd 0x81 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdd 0x82 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdd 0x82 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdd 0x82 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdd 0x83 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdd 0x83 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdd 0x83 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdd 0x84 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdd 0x84 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdd 0x84 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdd 0x85 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdd 0x85 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdd 0x85 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdd 0x86 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdd 0x86 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdd 0x86 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdd 0x87 0x00 0x00] {::i/tag ::i/fld, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdd 0x87 0xf0 0x0f] {::i/tag ::i/fld, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdd 0x87 0x0f 0xf0] {::i/tag ::i/fld, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdd 0x88 0x90 0x90] nil
   [0xdd 0x89 0x90 0x90] nil
   [0xdd 0x8a 0x90 0x90] nil
   [0xdd 0x8b 0x90 0x90] nil
   [0xdd 0x8c 0x90 0x90] nil
   [0xdd 0x8d 0x90 0x90] nil
   [0xdd 0x8e 0x90 0x90] nil
   [0xdd 0x8f 0x90 0x90] nil
   [0xdd 0x90 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdd 0x90 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdd 0x90 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdd 0x91 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdd 0x91 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdd 0x91 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdd 0x92 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdd 0x92 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdd 0x92 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdd 0x93 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdd 0x93 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdd 0x93 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdd 0x94 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdd 0x94 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdd 0x94 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdd 0x95 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdd 0x95 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdd 0x95 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdd 0x96 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdd 0x96 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdd 0x96 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdd 0x97 0x00 0x00] {::i/tag ::i/fst, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdd 0x97 0xf0 0x0f] {::i/tag ::i/fst, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdd 0x97 0x0f 0xf0] {::i/tag ::i/fst, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdd 0x98 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdd 0x98 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdd 0x98 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdd 0x99 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdd 0x99 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdd 0x99 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdd 0x9a 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdd 0x9a 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdd 0x9a 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdd 0x9b 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdd 0x9b 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdd 0x9b 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdd 0x9c 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdd 0x9c 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdd 0x9c 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdd 0x9d 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdd 0x9d 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdd 0x9d 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdd 0x9e 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdd 0x9e 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdd 0x9e 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdd 0x9f 0x00 0x00] {::i/tag ::i/fstp, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdd 0x9f 0xf0 0x0f] {::i/tag ::i/fstp, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdd 0x9f 0x0f 0xf0] {::i/tag ::i/fstp, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdd 0xa0 0x00 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/si]]}
   [0xdd 0xa0 0xf0 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/si 4080]]}
   [0xdd 0xa0 0x0f 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/si -4081]]}
   [0xdd 0xa1 0x00 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/di]]}
   [0xdd 0xa1 0xf0 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/di 4080]]}
   [0xdd 0xa1 0x0f 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bx ::r/di -4081]]}
   [0xdd 0xa2 0x00 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/si]]}
   [0xdd 0xa2 0xf0 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/si 4080]]}
   [0xdd 0xa2 0x0f 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/si -4081]]}
   [0xdd 0xa3 0x00 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/di]]}
   [0xdd 0xa3 0xf0 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/di 4080]]}
   [0xdd 0xa3 0x0f 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bp ::r/di -4081]]}
   [0xdd 0xa4 0x00 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/si]]}
   [0xdd 0xa4 0xf0 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/si 4080]]}
   [0xdd 0xa4 0x0f 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/si -4081]]}
   [0xdd 0xa5 0x00 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/di]]}
   [0xdd 0xa5 0xf0 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/di 4080]]}
   [0xdd 0xa5 0x0f 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/di -4081]]}
   [0xdd 0xa6 0x00 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bp]]}
   [0xdd 0xa6 0xf0 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bp 4080]]}
   [0xdd 0xa6 0x0f 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bp -4081]]}
   [0xdd 0xa7 0x00 0x00] {::i/tag ::i/frstorw, ::i/args [[::r/bx]]}
   [0xdd 0xa7 0xf0 0x0f] {::i/tag ::i/frstorw, ::i/args [[::r/bx 4080]]}
   [0xdd 0xa7 0x0f 0xf0] {::i/tag ::i/frstorw, ::i/args [[::r/bx -4081]]}
   [0xdd 0xa8 0x90 0x90] nil
   [0xdd 0xa9 0x90 0x90] nil
   [0xdd 0xaa 0x90 0x90] nil
   [0xdd 0xab 0x90 0x90] nil
   [0xdd 0xac 0x90 0x90] nil
   [0xdd 0xad 0x90 0x90] nil
   [0xdd 0xae 0x90 0x90] nil
   [0xdd 0xaf 0x90 0x90] nil
   [0xdd 0xb0 0x00 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/si]]}
   [0xdd 0xb0 0xf0 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/si 4080]]}
   [0xdd 0xb0 0x0f 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/si -4081]]}
   [0xdd 0xb1 0x00 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/di]]}
   [0xdd 0xb1 0xf0 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/di 4080]]}
   [0xdd 0xb1 0x0f 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bx ::r/di -4081]]}
   [0xdd 0xb2 0x00 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/si]]}
   [0xdd 0xb2 0xf0 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/si 4080]]}
   [0xdd 0xb2 0x0f 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/si -4081]]}
   [0xdd 0xb3 0x00 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/di]]}
   [0xdd 0xb3 0xf0 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/di 4080]]}
   [0xdd 0xb3 0x0f 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bp ::r/di -4081]]}
   [0xdd 0xb4 0x00 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/si]]}
   [0xdd 0xb4 0xf0 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/si 4080]]}
   [0xdd 0xb4 0x0f 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/si -4081]]}
   [0xdd 0xb5 0x00 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/di]]}
   [0xdd 0xb5 0xf0 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/di 4080]]}
   [0xdd 0xb5 0x0f 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/di -4081]]}
   [0xdd 0xb6 0x00 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bp]]}
   [0xdd 0xb6 0xf0 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bp 4080]]}
   [0xdd 0xb6 0x0f 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bp -4081]]}
   [0xdd 0xb7 0x00 0x00] {::i/tag ::i/fnsavew, ::i/args [[::r/bx]]}
   [0xdd 0xb7 0xf0 0x0f] {::i/tag ::i/fnsavew, ::i/args [[::r/bx 4080]]}
   [0xdd 0xb7 0x0f 0xf0] {::i/tag ::i/fnsavew, ::i/args [[::r/bx -4081]]}
   [0xdd 0xb8 0x00 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/si]]}
   [0xdd 0xb8 0xf0 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/si 4080]]}
   [0xdd 0xb8 0x0f 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/si -4081]]}
   [0xdd 0xb9 0x00 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/di]]}
   [0xdd 0xb9 0xf0 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/di 4080]]}
   [0xdd 0xb9 0x0f 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bx ::r/di -4081]]}
   [0xdd 0xba 0x00 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/si]]}
   [0xdd 0xba 0xf0 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/si 4080]]}
   [0xdd 0xba 0x0f 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/si -4081]]}
   [0xdd 0xbb 0x00 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/di]]}
   [0xdd 0xbb 0xf0 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/di 4080]]}
   [0xdd 0xbb 0x0f 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bp ::r/di -4081]]}
   [0xdd 0xbc 0x00 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/si]]}
   [0xdd 0xbc 0xf0 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/si 4080]]}
   [0xdd 0xbc 0x0f 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/si -4081]]}
   [0xdd 0xbd 0x00 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/di]]}
   [0xdd 0xbd 0xf0 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/di 4080]]}
   [0xdd 0xbd 0x0f 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/di -4081]]}
   [0xdd 0xbe 0x00 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bp]]}
   [0xdd 0xbe 0xf0 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bp 4080]]}
   [0xdd 0xbe 0x0f 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bp -4081]]}
   [0xdd 0xbf 0x00 0x00] {::i/tag ::i/fnstsw, ::i/args [[::r/bx]]}
   [0xdd 0xbf 0xf0 0x0f] {::i/tag ::i/fnstsw, ::i/args [[::r/bx 4080]]}
   [0xdd 0xbf 0x0f 0xf0] {::i/tag ::i/fnstsw, ::i/args [[::r/bx -4081]]}
   [0xdd 0xc0] {::i/tag ::i/ffree, ::i/args [::r/st0]}
   [0xdd 0xc1] {::i/tag ::i/ffree, ::i/args [::r/st1]}
   [0xdd 0xc2] {::i/tag ::i/ffree, ::i/args [::r/st2]}
   [0xdd 0xc3] {::i/tag ::i/ffree, ::i/args [::r/st3]}
   [0xdd 0xc4] {::i/tag ::i/ffree, ::i/args [::r/st4]}
   [0xdd 0xc5] {::i/tag ::i/ffree, ::i/args [::r/st5]}
   [0xdd 0xc6] {::i/tag ::i/ffree, ::i/args [::r/st6]}
   [0xdd 0xc7] {::i/tag ::i/ffree, ::i/args [::r/st7]}
   [0xdd 0xc8] {::i/tag ::i/fnop}
   [0xdd 0xc9] nil
   [0xdd 0xca] nil
   [0xdd 0xcb] nil
   [0xdd 0xcc] nil
   [0xdd 0xcd] nil
   [0xdd 0xce] nil
   [0xdd 0xcf] nil
   [0xdd 0xd0] {::i/tag ::i/fst, ::i/args [::r/st0]}
   [0xdd 0xd1] {::i/tag ::i/fst, ::i/args [::r/st1]}
   [0xdd 0xd2] {::i/tag ::i/fst, ::i/args [::r/st2]}
   [0xdd 0xd3] {::i/tag ::i/fst, ::i/args [::r/st3]}
   [0xdd 0xd4] {::i/tag ::i/fst, ::i/args [::r/st4]}
   [0xdd 0xd5] {::i/tag ::i/fst, ::i/args [::r/st5]}
   [0xdd 0xd6] {::i/tag ::i/fst, ::i/args [::r/st6]}
   [0xdd 0xd7] {::i/tag ::i/fst, ::i/args [::r/st7]}
   [0xdd 0xd8] {::i/tag ::i/fstp, ::i/args [::r/st0]}
   [0xdd 0xd9] {::i/tag ::i/fstp, ::i/args [::r/st1]}
   [0xdd 0xda] {::i/tag ::i/fstp, ::i/args [::r/st2]}
   [0xdd 0xdb] {::i/tag ::i/fstp, ::i/args [::r/st3]}
   [0xdd 0xdc] {::i/tag ::i/fstp, ::i/args [::r/st4]}
   [0xdd 0xdd] {::i/tag ::i/fstp, ::i/args [::r/st5]}
   [0xdd 0xde] {::i/tag ::i/fstp, ::i/args [::r/st6]}
   [0xdd 0xdf] {::i/tag ::i/fstp, ::i/args [::r/st7]}
   [0xdd 0xe0] {::i/tag ::i/fucom, ::i/args [::r/st0]}
   [0xdd 0xe1] {::i/tag ::i/fucom, ::i/args [::r/st1]}
   [0xdd 0xe2] {::i/tag ::i/fucom, ::i/args [::r/st2]}
   [0xdd 0xe3] {::i/tag ::i/fucom, ::i/args [::r/st3]}
   [0xdd 0xe4] {::i/tag ::i/fucom, ::i/args [::r/st4]}
   [0xdd 0xe5] {::i/tag ::i/fucom, ::i/args [::r/st5]}
   [0xdd 0xe6] {::i/tag ::i/fucom, ::i/args [::r/st6]}
   [0xdd 0xe7] {::i/tag ::i/fucom, ::i/args [::r/st7]}
   [0xdd 0xe8] {::i/tag ::i/fucomp, ::i/args [::r/st0]}
   [0xdd 0xe9] {::i/tag ::i/fucomp, ::i/args [::r/st1]}
   [0xdd 0xea] {::i/tag ::i/fucomp, ::i/args [::r/st2]}
   [0xdd 0xeb] {::i/tag ::i/fucomp, ::i/args [::r/st3]}
   [0xdd 0xec] {::i/tag ::i/fucomp, ::i/args [::r/st4]}
   [0xdd 0xed] {::i/tag ::i/fucomp, ::i/args [::r/st5]}
   [0xdd 0xee] {::i/tag ::i/fucomp, ::i/args [::r/st6]}
   [0xdd 0xef] {::i/tag ::i/fucomp, ::i/args [::r/st7]}
   [0xdd 0xf0] {::i/tag ::i/fnop}
   [0xdd 0xf1] nil
   [0xdd 0xf2] nil
   [0xdd 0xf3] nil
   [0xdd 0xf4] nil
   [0xdd 0xf5] nil
   [0xdd 0xf6] nil
   [0xdd 0xf7] nil
   [0xdd 0xf8] {::i/tag ::i/fnop}
   [0xdd 0xf9] nil
   [0xdd 0xfa] nil
   [0xdd 0xfb] nil
   [0xdd 0xfc] nil
   [0xdd 0xfd] nil
   [0xdd 0xfe] nil
   [0xdd 0xff] nil})


(def decode-examples-0xde-0xdf
  {[0xde 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x01] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x02] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x03] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x04] {::i/tag ::i/fiadd, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x05] {::i/tag ::i/fiadd, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x06 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xde 0x06 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xde 0x07] {::i/tag ::i/fiadd, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x08] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x09] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x0a] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x0b] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x0c] {::i/tag ::i/fimul, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x0d] {::i/tag ::i/fimul, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x0e 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xde 0x0e 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xde 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x10] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x11] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x12] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x13] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x14] {::i/tag ::i/ficom, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x15] {::i/tag ::i/ficom, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x16 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xde 0x16 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xde 0x17] {::i/tag ::i/ficom, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x18] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x19] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x1a] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x1b] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x1c] {::i/tag ::i/ficomp, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x1d] {::i/tag ::i/ficomp, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x1e 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xde 0x1e 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xde 0x1f] {::i/tag ::i/ficomp, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x20] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x21] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x22] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x23] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x24] {::i/tag ::i/fisub, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x25] {::i/tag ::i/fisub, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x26 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xde 0x26 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xde 0x27] {::i/tag ::i/fisub, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x28] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x29] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x2a] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x2b] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x2c] {::i/tag ::i/fisubr, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x2d] {::i/tag ::i/fisubr, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x2e 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xde 0x2e 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xde 0x2f] {::i/tag ::i/fisubr, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x30] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x31] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x32] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x33] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x34] {::i/tag ::i/fidiv, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x35] {::i/tag ::i/fidiv, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x36 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xde 0x36 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xde 0x37] {::i/tag ::i/fidiv, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x38] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x39] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x3a] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x3b] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x3c] {::i/tag ::i/fidivr, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x3d] {::i/tag ::i/fidivr, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x3e 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xde 0x3e 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xde 0x3f] {::i/tag ::i/fidivr, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x40 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x40 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xde 0x40 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xde 0x41 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x41 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xde 0x41 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xde 0x42 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x42 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xde 0x42 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xde 0x43 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x43 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xde 0x43 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xde 0x44 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x44 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xde 0x44 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xde 0x45 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x45 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xde 0x45 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xde 0x46 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x46 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xde 0x46 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xde 0x47 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x47 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xde 0x47 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xde 0x48 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x48 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xde 0x48 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xde 0x49 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x49 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xde 0x49 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xde 0x4a 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x4a 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xde 0x4a 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xde 0x4b 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x4b 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xde 0x4b 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xde 0x4c 0x00] {::i/tag ::i/fimul, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x4c 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xde 0x4c 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xde 0x4d 0x00] {::i/tag ::i/fimul, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x4d 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xde 0x4d 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xde 0x4e 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x4e 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xde 0x4e 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xde 0x4f 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x4f 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xde 0x4f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xde 0x50 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x50 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xde 0x50 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xde 0x51 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x51 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xde 0x51 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xde 0x52 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x52 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xde 0x52 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xde 0x53 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x53 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xde 0x53 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xde 0x54 0x00] {::i/tag ::i/ficom, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x54 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xde 0x54 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xde 0x55 0x00] {::i/tag ::i/ficom, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x55 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xde 0x55 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xde 0x56 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x56 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xde 0x56 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xde 0x57 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x57 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xde 0x57 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xde 0x58 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x58 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xde 0x58 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xde 0x59 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x59 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xde 0x59 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xde 0x5a 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x5a 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xde 0x5a 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xde 0x5b 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x5b 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xde 0x5b 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xde 0x5c 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x5c 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xde 0x5c 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xde 0x5d 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x5d 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xde 0x5d 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xde 0x5e 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x5e 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xde 0x5e 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xde 0x5f 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x5f 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xde 0x5f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xde 0x60 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x60 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xde 0x60 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xde 0x61 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x61 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xde 0x61 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xde 0x62 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x62 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xde 0x62 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xde 0x63 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x63 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xde 0x63 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xde 0x64 0x00] {::i/tag ::i/fisub, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x64 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xde 0x64 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xde 0x65 0x00] {::i/tag ::i/fisub, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x65 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xde 0x65 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xde 0x66 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x66 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xde 0x66 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xde 0x67 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x67 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xde 0x67 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xde 0x68 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x68 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xde 0x68 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xde 0x69 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x69 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xde 0x69 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xde 0x6a 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x6a 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xde 0x6a 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xde 0x6b 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x6b 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xde 0x6b 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xde 0x6c 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x6c 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xde 0x6c 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xde 0x6d 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x6d 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xde 0x6d 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xde 0x6e 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x6e 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xde 0x6e 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xde 0x6f 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x6f 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xde 0x6f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xde 0x70 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x70 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xde 0x70 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xde 0x71 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x71 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xde 0x71 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xde 0x72 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x72 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xde 0x72 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xde 0x73 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x73 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xde 0x73 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xde 0x74 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x74 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xde 0x74 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xde 0x75 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x75 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xde 0x75 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xde 0x76 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x76 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xde 0x76 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xde 0x77 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x77 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xde 0x77 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xde 0x78 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x78 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xde 0x78 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xde 0x79 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x79 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xde 0x79 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xde 0x7a 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x7a 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xde 0x7a 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xde 0x7b 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x7b 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xde 0x7b 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xde 0x7c 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x7c 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xde 0x7c 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xde 0x7d 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x7d 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xde 0x7d 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xde 0x7e 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x7e 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xde 0x7e 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xde 0x7f 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x7f 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xde 0x7f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xde 0x80 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x80 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xde 0x80 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xde 0x81 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x81 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xde 0x81 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xde 0x82 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x82 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xde 0x82 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xde 0x83 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x83 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xde 0x83 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xde 0x84 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x84 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xde 0x84 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xde 0x85 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x85 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xde 0x85 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xde 0x86 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x86 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xde 0x86 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xde 0x87 0x00 0x00] {::i/tag ::i/fiadd, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x87 0xf0 0x0f] {::i/tag ::i/fiadd, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xde 0x87 0x0f 0xf0] {::i/tag ::i/fiadd, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xde 0x88 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x88 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xde 0x88 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xde 0x89 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x89 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xde 0x89 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xde 0x8a 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x8a 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xde 0x8a 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xde 0x8b 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x8b 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xde 0x8b 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xde 0x8c 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x8c 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xde 0x8c 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xde 0x8d 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x8d 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xde 0x8d 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xde 0x8e 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x8e 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xde 0x8e 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xde 0x8f 0x00 0x00] {::i/tag ::i/fimul, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x8f 0xf0 0x0f] {::i/tag ::i/fimul, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xde 0x8f 0x0f 0xf0] {::i/tag ::i/fimul, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xde 0x90 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x90 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xde 0x90 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xde 0x91 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x91 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xde 0x91 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xde 0x92 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x92 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xde 0x92 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xde 0x93 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x93 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xde 0x93 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xde 0x94 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x94 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xde 0x94 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xde 0x95 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x95 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xde 0x95 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xde 0x96 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x96 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xde 0x96 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xde 0x97 0x00 0x00] {::i/tag ::i/ficom, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x97 0xf0 0x0f] {::i/tag ::i/ficom, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xde 0x97 0x0f 0xf0] {::i/tag ::i/ficom, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xde 0x98 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0x98 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xde 0x98 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xde 0x99 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0x99 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xde 0x99 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xde 0x9a 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0x9a 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xde 0x9a 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xde 0x9b 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0x9b 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xde 0x9b 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xde 0x9c 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0x9c 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xde 0x9c 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xde 0x9d 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0x9d 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xde 0x9d 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xde 0x9e 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0x9e 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xde 0x9e 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xde 0x9f 0x00 0x00] {::i/tag ::i/ficomp, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0x9f 0xf0 0x0f] {::i/tag ::i/ficomp, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xde 0x9f 0x0f 0xf0] {::i/tag ::i/ficomp, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xde 0xa0 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0xa0 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xde 0xa0 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xde 0xa1 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0xa1 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xde 0xa1 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xde 0xa2 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0xa2 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xde 0xa2 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xde 0xa3 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0xa3 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xde 0xa3 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xde 0xa4 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0xa4 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xde 0xa4 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xde 0xa5 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0xa5 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xde 0xa5 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xde 0xa6 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0xa6 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xde 0xa6 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xde 0xa7 0x00 0x00] {::i/tag ::i/fisub, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0xa7 0xf0 0x0f] {::i/tag ::i/fisub, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xde 0xa7 0x0f 0xf0] {::i/tag ::i/fisub, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xde 0xa8 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0xa8 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xde 0xa8 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xde 0xa9 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0xa9 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xde 0xa9 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xde 0xaa 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0xaa 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xde 0xaa 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xde 0xab 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0xab 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xde 0xab 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xde 0xac 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0xac 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xde 0xac 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xde 0xad 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0xad 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xde 0xad 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xde 0xae 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0xae 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xde 0xae 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xde 0xaf 0x00 0x00] {::i/tag ::i/fisubr, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0xaf 0xf0 0x0f] {::i/tag ::i/fisubr, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xde 0xaf 0x0f 0xf0] {::i/tag ::i/fisubr, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xde 0xb0 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0xb0 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xde 0xb0 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xde 0xb1 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0xb1 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xde 0xb1 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xde 0xb2 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0xb2 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xde 0xb2 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xde 0xb3 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0xb3 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xde 0xb3 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xde 0xb4 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0xb4 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xde 0xb4 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xde 0xb5 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0xb5 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xde 0xb5 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xde 0xb6 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0xb6 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xde 0xb6 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xde 0xb7 0x00 0x00] {::i/tag ::i/fidiv, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0xb7 0xf0 0x0f] {::i/tag ::i/fidiv, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xde 0xb7 0x0f 0xf0] {::i/tag ::i/fidiv, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xde 0xb8 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xde 0xb8 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xde 0xb8 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xde 0xb9 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xde 0xb9 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xde 0xb9 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xde 0xba 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xde 0xba 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xde 0xba 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xde 0xbb 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xde 0xbb 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xde 0xbb 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xde 0xbc 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xde 0xbc 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xde 0xbc 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xde 0xbd 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xde 0xbd 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xde 0xbd 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xde 0xbe 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xde 0xbe 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xde 0xbe 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xde 0xbf 0x00 0x00] {::i/tag ::i/fidivr, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xde 0xbf 0xf0 0x0f] {::i/tag ::i/fidivr, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xde 0xbf 0x0f 0xf0] {::i/tag ::i/fidivr, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xde 0xc0] {::i/tag ::i/faddp, ::i/args [::r/st0]}
   [0xde 0xc1] {::i/tag ::i/faddp, ::i/args [::r/st1]}
   [0xde 0xc2] {::i/tag ::i/faddp, ::i/args [::r/st2]}
   [0xde 0xc3] {::i/tag ::i/faddp, ::i/args [::r/st3]}
   [0xde 0xc4] {::i/tag ::i/faddp, ::i/args [::r/st4]}
   [0xde 0xc5] {::i/tag ::i/faddp, ::i/args [::r/st5]}
   [0xde 0xc6] {::i/tag ::i/faddp, ::i/args [::r/st6]}
   [0xde 0xc7] {::i/tag ::i/faddp, ::i/args [::r/st7]}
   [0xde 0xc8] {::i/tag ::i/fmulp, ::i/args [::r/st0]}
   [0xde 0xc9] {::i/tag ::i/fmulp, ::i/args [::r/st1]}
   [0xde 0xca] {::i/tag ::i/fmulp, ::i/args [::r/st2]}
   [0xde 0xcb] {::i/tag ::i/fmulp, ::i/args [::r/st3]}
   [0xde 0xcc] {::i/tag ::i/fmulp, ::i/args [::r/st4]}
   [0xde 0xcd] {::i/tag ::i/fmulp, ::i/args [::r/st5]}
   [0xde 0xce] {::i/tag ::i/fmulp, ::i/args [::r/st6]}
   [0xde 0xcf] {::i/tag ::i/fmulp, ::i/args [::r/st7]}
   [0xde 0xd0] {::i/tag ::i/fnop}
   [0xde 0xd1] nil
   [0xde 0xd2] nil
   [0xde 0xd3] nil
   [0xde 0xd4] nil
   [0xde 0xd5] nil
   [0xde 0xd6] nil
   [0xde 0xd7] nil
   [0xde 0xd8] nil
   [0xde 0xd9] {::i/tag ::i/fcompp}
   [0xde 0xda] nil
   [0xde 0xdb] nil
   [0xde 0xdc] nil
   [0xde 0xdd] nil
   [0xde 0xde] nil
   [0xde 0xdf] nil
   [0xde 0xe0] {::i/tag ::i/fsubrp, ::i/args [::r/st0]}
   [0xde 0xe1] {::i/tag ::i/fsubrp, ::i/args [::r/st1]}
   [0xde 0xe2] {::i/tag ::i/fsubrp, ::i/args [::r/st2]}
   [0xde 0xe3] {::i/tag ::i/fsubrp, ::i/args [::r/st3]}
   [0xde 0xe4] {::i/tag ::i/fsubrp, ::i/args [::r/st4]}
   [0xde 0xe5] {::i/tag ::i/fsubrp, ::i/args [::r/st5]}
   [0xde 0xe6] {::i/tag ::i/fsubrp, ::i/args [::r/st6]}
   [0xde 0xe7] {::i/tag ::i/fsubrp, ::i/args [::r/st7]}
   [0xde 0xe8] {::i/tag ::i/fsubp, ::i/args [::r/st0]}
   [0xde 0xe9] {::i/tag ::i/fsubp, ::i/args [::r/st1]}
   [0xde 0xea] {::i/tag ::i/fsubp, ::i/args [::r/st2]}
   [0xde 0xeb] {::i/tag ::i/fsubp, ::i/args [::r/st3]}
   [0xde 0xec] {::i/tag ::i/fsubp, ::i/args [::r/st4]}
   [0xde 0xed] {::i/tag ::i/fsubp, ::i/args [::r/st5]}
   [0xde 0xee] {::i/tag ::i/fsubp, ::i/args [::r/st6]}
   [0xde 0xef] {::i/tag ::i/fsubp, ::i/args [::r/st7]}
   [0xde 0xf0] {::i/tag ::i/fdivrp, ::i/args [::r/st0]}
   [0xde 0xf1] {::i/tag ::i/fdivrp, ::i/args [::r/st1]}
   [0xde 0xf2] {::i/tag ::i/fdivrp, ::i/args [::r/st2]}
   [0xde 0xf3] {::i/tag ::i/fdivrp, ::i/args [::r/st3]}
   [0xde 0xf4] {::i/tag ::i/fdivrp, ::i/args [::r/st4]}
   [0xde 0xf5] {::i/tag ::i/fdivrp, ::i/args [::r/st5]}
   [0xde 0xf6] {::i/tag ::i/fdivrp, ::i/args [::r/st6]}
   [0xde 0xf7] {::i/tag ::i/fdivrp, ::i/args [::r/st7]}
   [0xde 0xf8] {::i/tag ::i/fdivp, ::i/args [::r/st0]}
   [0xde 0xf9] {::i/tag ::i/fdivp, ::i/args [::r/st1]}
   [0xde 0xfa] {::i/tag ::i/fdivp, ::i/args [::r/st2]}
   [0xde 0xfb] {::i/tag ::i/fdivp, ::i/args [::r/st3]}
   [0xde 0xfc] {::i/tag ::i/fdivp, ::i/args [::r/st4]}
   [0xde 0xfd] {::i/tag ::i/fdivp, ::i/args [::r/st5]}
   [0xde 0xfe] {::i/tag ::i/fdivp, ::i/args [::r/st6]}
   [0xde 0xff] {::i/tag ::i/fdivp, ::i/args [::r/st7]}
   [0xdf 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xdf 0x01] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xdf 0x02] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xdf 0x03] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xdf 0x04] {::i/tag ::i/fild, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xdf 0x05] {::i/tag ::i/fild, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xdf 0x06 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xdf 0x06 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xdf 0x07] {::i/tag ::i/fild, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xdf 0x08] nil
   [0xdf 0x09] nil
   [0xdf 0x0a] nil
   [0xdf 0x0b] nil
   [0xdf 0x0c] nil
   [0xdf 0x0d] nil
   [0xdf 0x0e 0x90 0x90] nil
   [0xdf 0x0f] nil
   [0xdf 0x10] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xdf 0x11] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xdf 0x12] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xdf 0x13] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xdf 0x14] {::i/tag ::i/fist, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xdf 0x15] {::i/tag ::i/fist, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xdf 0x16 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xdf 0x16 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xdf 0x17] {::i/tag ::i/fist, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xdf 0x18] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xdf 0x19] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xdf 0x1a] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xdf 0x1b] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xdf 0x1c] {::i/tag ::i/fistp, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xdf 0x1d] {::i/tag ::i/fistp, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xdf 0x1e 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[0x0ff0]], ::i/type ::i/word}
   [0xdf 0x1e 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[0xf00f]], ::i/type ::i/word}
   [0xdf 0x1f] {::i/tag ::i/fistp, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xdf 0x20] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/si]]}
   [0xdf 0x21] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/di]]}
   [0xdf 0x22] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/si]]}
   [0xdf 0x23] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/di]]}
   [0xdf 0x24] {::i/tag ::i/fbld, ::i/args [[::r/si]]}
   [0xdf 0x25] {::i/tag ::i/fbld, ::i/args [[::r/di]]}
   [0xdf 0x26 0xf0 0x0f] {::i/tag ::i/fbld, ::i/args [[0x0ff0]]}
   [0xdf 0x26 0x0f 0xf0] {::i/tag ::i/fbld, ::i/args [[0xf00f]]}
   [0xdf 0x27] {::i/tag ::i/fbld, ::i/args [[::r/bx]]}
   [0xdf 0x28] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdf 0x29] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdf 0x2a] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdf 0x2b] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdf 0x2c] {::i/tag ::i/fild, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdf 0x2d] {::i/tag ::i/fild, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdf 0x2e 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdf 0x2e 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdf 0x2f] {::i/tag ::i/fild, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdf 0x30] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/si]]}
   [0xdf 0x31] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/di]]}
   [0xdf 0x32] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/si]]}
   [0xdf 0x33] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/di]]}
   [0xdf 0x34] {::i/tag ::i/fbstp, ::i/args [[::r/si]]}
   [0xdf 0x35] {::i/tag ::i/fbstp, ::i/args [[::r/di]]}
   [0xdf 0x36 0xf0 0x0f] {::i/tag ::i/fbstp, ::i/args [[0x0ff0]]}
   [0xdf 0x36 0x0f 0xf0] {::i/tag ::i/fbstp, ::i/args [[0xf00f]]}
   [0xdf 0x37] {::i/tag ::i/fbstp, ::i/args [[::r/bx]]}
   [0xdf 0x38] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdf 0x39] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdf 0x3a] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdf 0x3b] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdf 0x3c] {::i/tag ::i/fistp, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdf 0x3d] {::i/tag ::i/fistp, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdf 0x3e 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[0x0ff0]], ::i/type ::i/qword}
   [0xdf 0x3e 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[0xf00f]], ::i/type ::i/qword}
   [0xdf 0x3f] {::i/tag ::i/fistp, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdf 0x40 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xdf 0x40 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xdf 0x40 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xdf 0x41 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xdf 0x41 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xdf 0x41 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xdf 0x42 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xdf 0x42 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xdf 0x42 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xdf 0x43 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xdf 0x43 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xdf 0x43 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xdf 0x44 0x00] {::i/tag ::i/fild, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xdf 0x44 0x0f] {::i/tag ::i/fild, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xdf 0x44 0xf0] {::i/tag ::i/fild, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xdf 0x45 0x00] {::i/tag ::i/fild, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xdf 0x45 0x0f] {::i/tag ::i/fild, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xdf 0x45 0xf0] {::i/tag ::i/fild, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xdf 0x46 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xdf 0x46 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xdf 0x46 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xdf 0x47 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xdf 0x47 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xdf 0x47 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xdf 0x48 0x90] nil
   [0xdf 0x49 0x90] nil
   [0xdf 0x4a 0x90] nil
   [0xdf 0x4b 0x90] nil
   [0xdf 0x4c 0x90] nil
   [0xdf 0x4d 0x90] nil
   [0xdf 0x4e 0x90] nil
   [0xdf 0x4f 0x90] nil
   [0xdf 0x50 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xdf 0x50 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xdf 0x50 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xdf 0x51 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xdf 0x51 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xdf 0x51 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xdf 0x52 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xdf 0x52 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xdf 0x52 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xdf 0x53 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xdf 0x53 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xdf 0x53 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xdf 0x54 0x00] {::i/tag ::i/fist, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xdf 0x54 0x0f] {::i/tag ::i/fist, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xdf 0x54 0xf0] {::i/tag ::i/fist, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xdf 0x55 0x00] {::i/tag ::i/fist, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xdf 0x55 0x0f] {::i/tag ::i/fist, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xdf 0x55 0xf0] {::i/tag ::i/fist, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xdf 0x56 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xdf 0x56 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xdf 0x56 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xdf 0x57 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xdf 0x57 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xdf 0x57 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xdf 0x58 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xdf 0x58 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xdf 0x58 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xdf 0x59 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xdf 0x59 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xdf 0x59 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xdf 0x5a 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xdf 0x5a 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xdf 0x5a 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xdf 0x5b 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xdf 0x5b 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xdf 0x5b 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xdf 0x5c 0x00] {::i/tag ::i/fistp, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xdf 0x5c 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xdf 0x5c 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xdf 0x5d 0x00] {::i/tag ::i/fistp, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xdf 0x5d 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xdf 0x5d 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xdf 0x5e 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xdf 0x5e 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xdf 0x5e 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xdf 0x5f 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xdf 0x5f 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xdf 0x5f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xdf 0x60 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/si]]}
   [0xdf 0x60 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/si 15]]}
   [0xdf 0x60 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/si -16]]}
   [0xdf 0x61 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/di]]}
   [0xdf 0x61 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/di 15]]}
   [0xdf 0x61 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/di -16]]}
   [0xdf 0x62 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/si]]}
   [0xdf 0x62 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/si 15]]}
   [0xdf 0x62 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/si -16]]}
   [0xdf 0x63 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/di]]}
   [0xdf 0x63 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/di 15]]}
   [0xdf 0x63 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/di -16]]}
   [0xdf 0x64 0x00] {::i/tag ::i/fbld, ::i/args [[::r/si]]}
   [0xdf 0x64 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/si 15]]}
   [0xdf 0x64 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/si -16]]}
   [0xdf 0x65 0x00] {::i/tag ::i/fbld, ::i/args [[::r/di]]}
   [0xdf 0x65 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/di 15]]}
   [0xdf 0x65 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/di -16]]}
   [0xdf 0x66 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bp]]}
   [0xdf 0x66 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bp 15]]}
   [0xdf 0x66 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bp -16]]}
   [0xdf 0x67 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bx]]}
   [0xdf 0x67 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bx 15]]}
   [0xdf 0x67 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bx -16]]}
   [0xdf 0x68 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdf 0x68 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdf 0x68 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdf 0x69 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdf 0x69 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdf 0x69 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdf 0x6a 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdf 0x6a 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdf 0x6a 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdf 0x6b 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdf 0x6b 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdf 0x6b 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdf 0x6c 0x00] {::i/tag ::i/fild, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdf 0x6c 0x0f] {::i/tag ::i/fild, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdf 0x6c 0xf0] {::i/tag ::i/fild, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdf 0x6d 0x00] {::i/tag ::i/fild, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdf 0x6d 0x0f] {::i/tag ::i/fild, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdf 0x6d 0xf0] {::i/tag ::i/fild, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdf 0x6e 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdf 0x6e 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdf 0x6e 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdf 0x6f 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdf 0x6f 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdf 0x6f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdf 0x70 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/si]]}
   [0xdf 0x70 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/si 15]]}
   [0xdf 0x70 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/si -16]]}
   [0xdf 0x71 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/di]]}
   [0xdf 0x71 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/di 15]]}
   [0xdf 0x71 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/di -16]]}
   [0xdf 0x72 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/si]]}
   [0xdf 0x72 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/si 15]]}
   [0xdf 0x72 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/si -16]]}
   [0xdf 0x73 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/di]]}
   [0xdf 0x73 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/di 15]]}
   [0xdf 0x73 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/di -16]]}
   [0xdf 0x74 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/si]]}
   [0xdf 0x74 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/si 15]]}
   [0xdf 0x74 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/si -16]]}
   [0xdf 0x75 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/di]]}
   [0xdf 0x75 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/di 15]]}
   [0xdf 0x75 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/di -16]]}
   [0xdf 0x76 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bp]]}
   [0xdf 0x76 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bp 15]]}
   [0xdf 0x76 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bp -16]]}
   [0xdf 0x77 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bx]]}
   [0xdf 0x77 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bx 15]]}
   [0xdf 0x77 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bx -16]]}
   [0xdf 0x78 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdf 0x78 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/qword}
   [0xdf 0x78 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/qword}
   [0xdf 0x79 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdf 0x79 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/qword}
   [0xdf 0x79 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/qword}
   [0xdf 0x7a 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdf 0x7a 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/qword}
   [0xdf 0x7a 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/qword}
   [0xdf 0x7b 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdf 0x7b 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/qword}
   [0xdf 0x7b 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/qword}
   [0xdf 0x7c 0x00] {::i/tag ::i/fistp, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdf 0x7c 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/si 15]], ::i/type ::i/qword}
   [0xdf 0x7c 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/si -16]], ::i/type ::i/qword}
   [0xdf 0x7d 0x00] {::i/tag ::i/fistp, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdf 0x7d 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/di 15]], ::i/type ::i/qword}
   [0xdf 0x7d 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/di -16]], ::i/type ::i/qword}
   [0xdf 0x7e 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdf 0x7e 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp 15]], ::i/type ::i/qword}
   [0xdf 0x7e 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp -16]], ::i/type ::i/qword}
   [0xdf 0x7f 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdf 0x7f 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx 15]], ::i/type ::i/qword}
   [0xdf 0x7f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx -16]], ::i/type ::i/qword}
   [0xdf 0x80 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xdf 0x80 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xdf 0x80 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xdf 0x81 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xdf 0x81 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xdf 0x81 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xdf 0x82 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xdf 0x82 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xdf 0x82 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xdf 0x83 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xdf 0x83 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xdf 0x83 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xdf 0x84 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xdf 0x84 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xdf 0x84 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xdf 0x85 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xdf 0x85 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xdf 0x85 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xdf 0x86 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xdf 0x86 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xdf 0x86 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xdf 0x87 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xdf 0x87 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xdf 0x87 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xdf 0x88 0x90 0x90] nil
   [0xdf 0x89 0x90 0x90] nil
   [0xdf 0x8a 0x90 0x90] nil
   [0xdf 0x8b 0x90 0x90] nil
   [0xdf 0x8c 0x90 0x90] nil
   [0xdf 0x8d 0x90 0x90] nil
   [0xdf 0x8e 0x90 0x90] nil
   [0xdf 0x8f 0x90 0x90] nil
   [0xdf 0x90 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xdf 0x90 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xdf 0x90 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xdf 0x91 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xdf 0x91 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xdf 0x91 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xdf 0x92 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xdf 0x92 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xdf 0x92 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xdf 0x93 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xdf 0x93 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xdf 0x93 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xdf 0x94 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xdf 0x94 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xdf 0x94 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xdf 0x95 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xdf 0x95 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xdf 0x95 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xdf 0x96 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xdf 0x96 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xdf 0x96 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xdf 0x97 0x00 0x00] {::i/tag ::i/fist, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xdf 0x97 0xf0 0x0f] {::i/tag ::i/fist, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xdf 0x97 0x0f 0xf0] {::i/tag ::i/fist, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xdf 0x98 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xdf 0x98 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xdf 0x98 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xdf 0x99 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xdf 0x99 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xdf 0x99 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xdf 0x9a 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xdf 0x9a 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xdf 0x9a 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xdf 0x9b 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xdf 0x9b 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xdf 0x9b 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xdf 0x9c 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xdf 0x9c 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xdf 0x9c 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xdf 0x9d 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xdf 0x9d 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xdf 0x9d 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xdf 0x9e 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xdf 0x9e 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xdf 0x9e 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xdf 0x9f 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xdf 0x9f 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xdf 0x9f 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xdf 0xa0 0x00 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/si]]}
   [0xdf 0xa0 0xf0 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/si 4080]]}
   [0xdf 0xa0 0x0f 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/si -4081]]}
   [0xdf 0xa1 0x00 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/di]]}
   [0xdf 0xa1 0xf0 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/di 4080]]}
   [0xdf 0xa1 0x0f 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bx ::r/di -4081]]}
   [0xdf 0xa2 0x00 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/si]]}
   [0xdf 0xa2 0xf0 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/si 4080]]}
   [0xdf 0xa2 0x0f 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/si -4081]]}
   [0xdf 0xa3 0x00 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/di]]}
   [0xdf 0xa3 0xf0 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/di 4080]]}
   [0xdf 0xa3 0x0f 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bp ::r/di -4081]]}
   [0xdf 0xa4 0x00 0x00] {::i/tag ::i/fbld, ::i/args [[::r/si]]}
   [0xdf 0xa4 0xf0 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/si 4080]]}
   [0xdf 0xa4 0x0f 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/si -4081]]}
   [0xdf 0xa5 0x00 0x00] {::i/tag ::i/fbld, ::i/args [[::r/di]]}
   [0xdf 0xa5 0xf0 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/di 4080]]}
   [0xdf 0xa5 0x0f 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/di -4081]]}
   [0xdf 0xa6 0x00 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bp]]}
   [0xdf 0xa6 0xf0 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bp 4080]]}
   [0xdf 0xa6 0x0f 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bp -4081]]}
   [0xdf 0xa7 0x00 0x00] {::i/tag ::i/fbld, ::i/args [[::r/bx]]}
   [0xdf 0xa7 0xf0 0x0f] {::i/tag ::i/fbld, ::i/args [[::r/bx 4080]]}
   [0xdf 0xa7 0x0f 0xf0] {::i/tag ::i/fbld, ::i/args [[::r/bx -4081]]}
   [0xdf 0xa8 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdf 0xa8 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdf 0xa8 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdf 0xa9 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdf 0xa9 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdf 0xa9 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdf 0xaa 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdf 0xaa 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdf 0xaa 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdf 0xab 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdf 0xab 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdf 0xab 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdf 0xac 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdf 0xac 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdf 0xac 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdf 0xad 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdf 0xad 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdf 0xad 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdf 0xae 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdf 0xae 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdf 0xae 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdf 0xaf 0x00 0x00] {::i/tag ::i/fild, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdf 0xaf 0xf0 0x0f] {::i/tag ::i/fild, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdf 0xaf 0x0f 0xf0] {::i/tag ::i/fild, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdf 0xb0 0x00 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/si]]}
   [0xdf 0xb0 0xf0 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/si 4080]]}
   [0xdf 0xb0 0x0f 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/si -4081]]}
   [0xdf 0xb1 0x00 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/di]]}
   [0xdf 0xb1 0xf0 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/di 4080]]}
   [0xdf 0xb1 0x0f 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bx ::r/di -4081]]}
   [0xdf 0xb2 0x00 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/si]]}
   [0xdf 0xb2 0xf0 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/si 4080]]}
   [0xdf 0xb2 0x0f 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/si -4081]]}
   [0xdf 0xb3 0x00 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/di]]}
   [0xdf 0xb3 0xf0 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/di 4080]]}
   [0xdf 0xb3 0x0f 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bp ::r/di -4081]]}
   [0xdf 0xb4 0x00 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/si]]}
   [0xdf 0xb4 0xf0 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/si 4080]]}
   [0xdf 0xb4 0x0f 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/si -4081]]}
   [0xdf 0xb5 0x00 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/di]]}
   [0xdf 0xb5 0xf0 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/di 4080]]}
   [0xdf 0xb5 0x0f 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/di -4081]]}
   [0xdf 0xb6 0x00 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bp]]}
   [0xdf 0xb6 0xf0 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bp 4080]]}
   [0xdf 0xb6 0x0f 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bp -4081]]}
   [0xdf 0xb7 0x00 0x00] {::i/tag ::i/fbstp, ::i/args [[::r/bx]]}
   [0xdf 0xb7 0xf0 0x0f] {::i/tag ::i/fbstp, ::i/args [[::r/bx 4080]]}
   [0xdf 0xb7 0x0f 0xf0] {::i/tag ::i/fbstp, ::i/args [[::r/bx -4081]]}
   [0xdf 0xb8 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si]], ::i/type ::i/qword}
   [0xdf 0xb8 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/qword}
   [0xdf 0xb8 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/qword}
   [0xdf 0xb9 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di]], ::i/type ::i/qword}
   [0xdf 0xb9 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/qword}
   [0xdf 0xb9 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/qword}
   [0xdf 0xba 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si]], ::i/type ::i/qword}
   [0xdf 0xba 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/qword}
   [0xdf 0xba 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/qword}
   [0xdf 0xbb 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di]], ::i/type ::i/qword}
   [0xdf 0xbb 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/qword}
   [0xdf 0xbb 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/qword}
   [0xdf 0xbc 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/si]], ::i/type ::i/qword}
   [0xdf 0xbc 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/si 4080]], ::i/type ::i/qword}
   [0xdf 0xbc 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/si -4081]], ::i/type ::i/qword}
   [0xdf 0xbd 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/di]], ::i/type ::i/qword}
   [0xdf 0xbd 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/di 4080]], ::i/type ::i/qword}
   [0xdf 0xbd 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/di -4081]], ::i/type ::i/qword}
   [0xdf 0xbe 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bp]], ::i/type ::i/qword}
   [0xdf 0xbe 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bp 4080]], ::i/type ::i/qword}
   [0xdf 0xbe 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bp -4081]], ::i/type ::i/qword}
   [0xdf 0xbf 0x00 0x00] {::i/tag ::i/fistp, ::i/args [[::r/bx]], ::i/type ::i/qword}
   [0xdf 0xbf 0xf0 0x0f] {::i/tag ::i/fistp, ::i/args [[::r/bx 4080]], ::i/type ::i/qword}
   [0xdf 0xbf 0x0f 0xf0] {::i/tag ::i/fistp, ::i/args [[::r/bx -4081]], ::i/type ::i/qword}
   [0xdf 0xc0] {::i/tag ::i/ffreep, ::i/args [::r/st0]}
   [0xdf 0xc1] {::i/tag ::i/ffreep, ::i/args [::r/st1]}
   [0xdf 0xc2] {::i/tag ::i/ffreep, ::i/args [::r/st2]}
   [0xdf 0xc3] {::i/tag ::i/ffreep, ::i/args [::r/st3]}
   [0xdf 0xc4] {::i/tag ::i/ffreep, ::i/args [::r/st4]}
   [0xdf 0xc5] {::i/tag ::i/ffreep, ::i/args [::r/st5]}
   [0xdf 0xc6] {::i/tag ::i/ffreep, ::i/args [::r/st6]}
   [0xdf 0xc7] {::i/tag ::i/ffreep, ::i/args [::r/st7]}
   [0xdf 0xc8] {::i/tag ::i/fnop}
   [0xdf 0xc9] nil
   [0xdf 0xca] nil
   [0xdf 0xcb] nil
   [0xdf 0xcc] nil
   [0xdf 0xcd] nil
   [0xdf 0xce] nil
   [0xdf 0xcf] nil
   [0xdf 0xd0] {::i/tag ::i/fnop}
   [0xdf 0xd1] nil
   [0xdf 0xd2] nil
   [0xdf 0xd3] nil
   [0xdf 0xd4] nil
   [0xdf 0xd5] nil
   [0xdf 0xd6] nil
   [0xdf 0xd7] nil
   [0xdf 0xd8] {::i/tag ::i/fnop}
   [0xdf 0xd9] nil
   [0xdf 0xda] nil
   [0xdf 0xdb] nil
   [0xdf 0xdc] nil
   [0xdf 0xdd] nil
   [0xdf 0xde] nil
   [0xdf 0xdf] nil
   [0xdf 0xe0] {::i/tag ::i/fnstsw, ::i/args [::r/ax]}
   [0xdf 0xe1] nil
   [0xdf 0xe2] nil
   [0xdf 0xe3] nil
   [0xdf 0xe4] nil
   [0xdf 0xe5] nil
   [0xdf 0xe6] nil
   [0xdf 0xe7] nil
   [0xdf 0xe8] nil
   [0xdf 0xe9] nil
   [0xdf 0xea] nil
   [0xdf 0xeb] nil
   [0xdf 0xec] nil
   [0xdf 0xed] nil
   [0xdf 0xee] nil
   [0xdf 0xef] nil
   [0xdf 0xf0] nil
   [0xdf 0xf1] nil
   [0xdf 0xf2] nil
   [0xdf 0xf3] nil
   [0xdf 0xf4] nil
   [0xdf 0xf5] nil
   [0xdf 0xf6] nil
   [0xdf 0xf7] nil
   [0xdf 0xf8] {::i/tag ::i/fnop}
   [0xdf 0xf9] nil
   [0xdf 0xfa] nil
   [0xdf 0xfb] nil
   [0xdf 0xfc] nil
   [0xdf 0xfd] nil
   [0xdf 0xfe] nil
   [0xdf 0xff] nil})
