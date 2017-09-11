(ns oumu.cpu.instructions-test.examples.i-dx
    (:require [oumu.cpu.instructions :as i]
              [oumu.cpu.registers :as r]))


(def decode-examples-0xd0
  {[0xd0 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x01] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x02] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x03] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x04] {::i/tag ::i/rol, ::i/args [[::r/si] 1]}
   [0xd0 0x05] {::i/tag ::i/rol, ::i/args [[::r/di] 1]}
   [0xd0 0x06 0x12 0x34] {::i/tag ::i/rol, ::i/args [[0x3412] 1]}
   [0xd0 0x06 0x34 0x12] {::i/tag ::i/rol, ::i/args [[0x1234] 1]}
   [0xd0 0x07] {::i/tag ::i/rol, ::i/args [[::r/bx] 1]}
   [0xd0 0x08] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x09] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x0a] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x0b] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x0c] {::i/tag ::i/ror, ::i/args [[::r/si] 1]}
   [0xd0 0x0d] {::i/tag ::i/ror, ::i/args [[::r/di] 1]}
   [0xd0 0x0e 0x12 0x34] {::i/tag ::i/ror, ::i/args [[0x3412] 1]}
   [0xd0 0x0e 0x34 0x12] {::i/tag ::i/ror, ::i/args [[0x1234] 1]}
   [0xd0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx] 1]}
   [0xd0 0x10] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x11] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x12] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x13] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x14] {::i/tag ::i/rcl, ::i/args [[::r/si] 1]}
   [0xd0 0x15] {::i/tag ::i/rcl, ::i/args [[::r/di] 1]}
   [0xd0 0x16 0x12 0x34] {::i/tag ::i/rcl, ::i/args [[0x3412] 1]}
   [0xd0 0x16 0x34 0x12] {::i/tag ::i/rcl, ::i/args [[0x1234] 1]}
   [0xd0 0x17] {::i/tag ::i/rcl, ::i/args [[::r/bx] 1]}
   [0xd0 0x18] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x19] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x1a] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x1b] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x1c] {::i/tag ::i/rcr, ::i/args [[::r/si] 1]}
   [0xd0 0x1d] {::i/tag ::i/rcr, ::i/args [[::r/di] 1]}
   [0xd0 0x1e 0x12 0x34] {::i/tag ::i/rcr, ::i/args [[0x3412] 1]}
   [0xd0 0x1e 0x34 0x12] {::i/tag ::i/rcr, ::i/args [[0x1234] 1]}
   [0xd0 0x1f] {::i/tag ::i/rcr, ::i/args [[::r/bx] 1]}
   [0xd0 0x20] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x21] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x22] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x23] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x24] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd0 0x25] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd0 0x26 0x12 0x34] {::i/tag ::i/shl, ::i/args [[0x3412] 1]}
   [0xd0 0x26 0x34 0x12] {::i/tag ::i/shl, ::i/args [[0x1234] 1]}
   [0xd0 0x27] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd0 0x28] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x29] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x2a] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x2b] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x2c] {::i/tag ::i/shr, ::i/args [[::r/si] 1]}
   [0xd0 0x2d] {::i/tag ::i/shr, ::i/args [[::r/di] 1]}
   [0xd0 0x2e 0x12 0x34] {::i/tag ::i/shr, ::i/args [[0x3412] 1]}
   [0xd0 0x2e 0x34 0x12] {::i/tag ::i/shr, ::i/args [[0x1234] 1]}
   [0xd0 0x2f] {::i/tag ::i/shr, ::i/args [[::r/bx] 1]}
   [0xd0 0x30] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x31] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x32] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x33] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x34] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd0 0x35] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd0 0x36 0x12 0x34] {::i/tag ::i/shl, ::i/args [[0x3412] 1]}
   [0xd0 0x36 0x34 0x12] {::i/tag ::i/shl, ::i/args [[0x1234] 1]}
   [0xd0 0x37] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd0 0x38] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x39] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x3a] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x3b] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x3c] {::i/tag ::i/sar, ::i/args [[::r/si] 1]}
   [0xd0 0x3d] {::i/tag ::i/sar, ::i/args [[::r/di] 1]}
   [0xd0 0x3e 0x12 0x34] {::i/tag ::i/sar, ::i/args [[0x3412] 1]}
   [0xd0 0x3e 0x34 0x12] {::i/tag ::i/sar, ::i/args [[0x1234] 1]}
   [0xd0 0x3f] {::i/tag ::i/sar, ::i/args [[::r/bx] 1]}
   [0xd0 0x40 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x40 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd0 0x40 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd0 0x41 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x41 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd0 0x41 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd0 0x42 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x42 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd0 0x42 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd0 0x43 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x43 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd0 0x43 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd0 0x44 0x00] {::i/tag ::i/rol, ::i/args [[::r/si] 1]}
   [0xd0 0x44 0xf0] {::i/tag ::i/rol, ::i/args [[::r/si -16] 1]}
   [0xd0 0x44 0x0f] {::i/tag ::i/rol, ::i/args [[::r/si 15] 1]}
   [0xd0 0x45 0x00] {::i/tag ::i/rol, ::i/args [[::r/di] 1]}
   [0xd0 0x45 0xf0] {::i/tag ::i/rol, ::i/args [[::r/di -16] 1]}
   [0xd0 0x45 0x0f] {::i/tag ::i/rol, ::i/args [[::r/di 15] 1]}
   [0xd0 0x46 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp] 1]}
   [0xd0 0x46 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp -16] 1]}
   [0xd0 0x46 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp 15] 1]}
   [0xd0 0x47 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx] 1]}
   [0xd0 0x47 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx -16] 1]}
   [0xd0 0x47 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx 15] 1]}
   [0xd0 0x48 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x48 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd0 0x48 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd0 0x49 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x49 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd0 0x49 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd0 0x4a 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x4a 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd0 0x4a 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd0 0x4b 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x4b 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd0 0x4b 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd0 0x4c 0x00] {::i/tag ::i/ror, ::i/args [[::r/si] 1]}
   [0xd0 0x4c 0xf0] {::i/tag ::i/ror, ::i/args [[::r/si -16] 1]}
   [0xd0 0x4c 0x0f] {::i/tag ::i/ror, ::i/args [[::r/si 15] 1]}
   [0xd0 0x4d 0x00] {::i/tag ::i/ror, ::i/args [[::r/di] 1]}
   [0xd0 0x4d 0xf0] {::i/tag ::i/ror, ::i/args [[::r/di -16] 1]}
   [0xd0 0x4d 0x0f] {::i/tag ::i/ror, ::i/args [[::r/di 15] 1]}
   [0xd0 0x4e 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp] 1]}
   [0xd0 0x4e 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp -16] 1]}
   [0xd0 0x4e 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp 15] 1]}
   [0xd0 0x4f 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx] 1]}
   [0xd0 0x4f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx -16] 1]}
   [0xd0 0x4f 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx 15] 1]}
   [0xd0 0x50 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x50 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd0 0x50 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd0 0x51 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x51 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd0 0x51 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd0 0x52 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x52 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd0 0x52 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd0 0x53 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x53 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd0 0x53 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd0 0x54 0x00] {::i/tag ::i/rcl, ::i/args [[::r/si] 1]}
   [0xd0 0x54 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/si -16] 1]}
   [0xd0 0x54 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/si 15] 1]}
   [0xd0 0x55 0x00] {::i/tag ::i/rcl, ::i/args [[::r/di] 1]}
   [0xd0 0x55 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/di -16] 1]}
   [0xd0 0x55 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/di 15] 1]}
   [0xd0 0x56 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp] 1]}
   [0xd0 0x56 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp -16] 1]}
   [0xd0 0x56 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp 15] 1]}
   [0xd0 0x57 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx] 1]}
   [0xd0 0x57 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx -16] 1]}
   [0xd0 0x57 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx 15] 1]}
   [0xd0 0x58 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x58 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd0 0x58 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd0 0x59 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x59 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd0 0x59 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd0 0x5a 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x5a 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd0 0x5a 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd0 0x5b 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x5b 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd0 0x5b 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd0 0x5c 0x00] {::i/tag ::i/rcr, ::i/args [[::r/si] 1]}
   [0xd0 0x5c 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/si -16] 1]}
   [0xd0 0x5c 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/si 15] 1]}
   [0xd0 0x5d 0x00] {::i/tag ::i/rcr, ::i/args [[::r/di] 1]}
   [0xd0 0x5d 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/di -16] 1]}
   [0xd0 0x5d 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/di 15] 1]}
   [0xd0 0x5e 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp] 1]}
   [0xd0 0x5e 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp -16] 1]}
   [0xd0 0x5e 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp 15] 1]}
   [0xd0 0x5f 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx] 1]}
   [0xd0 0x5f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx -16] 1]}
   [0xd0 0x5f 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx 15] 1]}
   [0xd0 0x60 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x60 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd0 0x60 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd0 0x61 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x61 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd0 0x61 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd0 0x62 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x62 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd0 0x62 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd0 0x63 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x63 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd0 0x63 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd0 0x64 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd0 0x64 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -16] 1]}
   [0xd0 0x64 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 15] 1]}
   [0xd0 0x65 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd0 0x65 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -16] 1]}
   [0xd0 0x65 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 15] 1]}
   [0xd0 0x66 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] 1]}
   [0xd0 0x66 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -16] 1]}
   [0xd0 0x66 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 15] 1]}
   [0xd0 0x67 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd0 0x67 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -16] 1]}
   [0xd0 0x67 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 15] 1]}
   [0xd0 0x68 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x68 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd0 0x68 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd0 0x69 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x69 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd0 0x69 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd0 0x6a 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x6a 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd0 0x6a 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd0 0x6b 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x6b 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd0 0x6b 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd0 0x6c 0x00] {::i/tag ::i/shr, ::i/args [[::r/si] 1]}
   [0xd0 0x6c 0xf0] {::i/tag ::i/shr, ::i/args [[::r/si -16] 1]}
   [0xd0 0x6c 0x0f] {::i/tag ::i/shr, ::i/args [[::r/si 15] 1]}
   [0xd0 0x6d 0x00] {::i/tag ::i/shr, ::i/args [[::r/di] 1]}
   [0xd0 0x6d 0xf0] {::i/tag ::i/shr, ::i/args [[::r/di -16] 1]}
   [0xd0 0x6d 0x0f] {::i/tag ::i/shr, ::i/args [[::r/di 15] 1]}
   [0xd0 0x6e 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp] 1]}
   [0xd0 0x6e 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp -16] 1]}
   [0xd0 0x6e 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp 15] 1]}
   [0xd0 0x6f 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx] 1]}
   [0xd0 0x6f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx -16] 1]}
   [0xd0 0x6f 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx 15] 1]}
   [0xd0 0x70 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x70 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd0 0x70 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd0 0x71 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x71 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd0 0x71 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd0 0x72 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x72 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd0 0x72 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd0 0x73 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x73 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd0 0x73 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd0 0x74 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd0 0x74 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -16] 1]}
   [0xd0 0x74 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 15] 1]}
   [0xd0 0x75 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd0 0x75 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -16] 1]}
   [0xd0 0x75 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 15] 1]}
   [0xd0 0x76 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] 1]}
   [0xd0 0x76 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -16] 1]}
   [0xd0 0x76 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 15] 1]}
   [0xd0 0x77 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd0 0x77 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -16] 1]}
   [0xd0 0x77 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 15] 1]}
   [0xd0 0x78 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x78 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd0 0x78 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd0 0x79 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x79 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd0 0x79 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd0 0x7a 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x7a 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd0 0x7a 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd0 0x7b 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x7b 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd0 0x7b 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd0 0x7c 0x00] {::i/tag ::i/sar, ::i/args [[::r/si] 1]}
   [0xd0 0x7c 0xf0] {::i/tag ::i/sar, ::i/args [[::r/si -16] 1]}
   [0xd0 0x7c 0x0f] {::i/tag ::i/sar, ::i/args [[::r/si 15] 1]}
   [0xd0 0x7d 0x00] {::i/tag ::i/sar, ::i/args [[::r/di] 1]}
   [0xd0 0x7d 0xf0] {::i/tag ::i/sar, ::i/args [[::r/di -16] 1]}
   [0xd0 0x7d 0x0f] {::i/tag ::i/sar, ::i/args [[::r/di 15] 1]}
   [0xd0 0x7e 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp] 1]}
   [0xd0 0x7e 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp -16] 1]}
   [0xd0 0x7e 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp 15] 1]}
   [0xd0 0x7f 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx] 1]}
   [0xd0 0x7f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx -16] 1]}
   [0xd0 0x7f 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx 15] 1]}
   [0xd0 0x80 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x80 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd0 0x80 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd0 0x81 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x81 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd0 0x81 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd0 0x82 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x82 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd0 0x82 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd0 0x83 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x83 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd0 0x83 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd0 0x84 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/si] 1]}
   [0xd0 0x84 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/si 4080] 1]}
   [0xd0 0x84 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/si -4081] 1]}
   [0xd0 0x85 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/di] 1]}
   [0xd0 0x85 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/di 4080] 1]}
   [0xd0 0x85 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/di -4081] 1]}
   [0xd0 0x86 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp] 1]}
   [0xd0 0x86 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp 4080] 1]}
   [0xd0 0x86 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp -4081] 1]}
   [0xd0 0x87 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx] 1]}
   [0xd0 0x87 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx 4080] 1]}
   [0xd0 0x87 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx -4081] 1]}
   [0xd0 0x88 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x88 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd0 0x88 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd0 0x89 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x89 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd0 0x89 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd0 0x8a 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x8a 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd0 0x8a 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd0 0x8b 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x8b 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd0 0x8b 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd0 0x8c 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/si] 1]}
   [0xd0 0x8c 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/si 4080] 1]}
   [0xd0 0x8c 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/si -4081] 1]}
   [0xd0 0x8d 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/di] 1]}
   [0xd0 0x8d 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/di 4080] 1]}
   [0xd0 0x8d 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/di -4081] 1]}
   [0xd0 0x8e 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp] 1]}
   [0xd0 0x8e 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp 4080] 1]}
   [0xd0 0x8e 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp -4081] 1]}
   [0xd0 0x8f 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx] 1]}
   [0xd0 0x8f 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx 4080] 1]}
   [0xd0 0x8f 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx -4081] 1]}
   [0xd0 0x90 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x90 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd0 0x90 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd0 0x91 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x91 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd0 0x91 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd0 0x92 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x92 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd0 0x92 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd0 0x93 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x93 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd0 0x93 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd0 0x94 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/si] 1]}
   [0xd0 0x94 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/si 4080] 1]}
   [0xd0 0x94 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/si -4081] 1]}
   [0xd0 0x95 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/di] 1]}
   [0xd0 0x95 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/di 4080] 1]}
   [0xd0 0x95 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/di -4081] 1]}
   [0xd0 0x96 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp] 1]}
   [0xd0 0x96 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp 4080] 1]}
   [0xd0 0x96 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp -4081] 1]}
   [0xd0 0x97 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx] 1]}
   [0xd0 0x97 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx 4080] 1]}
   [0xd0 0x97 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx -4081] 1]}
   [0xd0 0x98 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0x98 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd0 0x98 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd0 0x99 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0x99 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd0 0x99 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd0 0x9a 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0x9a 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd0 0x9a 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd0 0x9b 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0x9b 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd0 0x9b 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd0 0x9c 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/si] 1]}
   [0xd0 0x9c 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/si 4080] 1]}
   [0xd0 0x9c 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/si -4081] 1]}
   [0xd0 0x9d 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/di] 1]}
   [0xd0 0x9d 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/di 4080] 1]}
   [0xd0 0x9d 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/di -4081] 1]}
   [0xd0 0x9e 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp] 1]}
   [0xd0 0x9e 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp 4080] 1]}
   [0xd0 0x9e 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp -4081] 1]}
   [0xd0 0x9f 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx] 1]}
   [0xd0 0x9f 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx 4080] 1]}
   [0xd0 0x9f 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx -4081] 1]}
   [0xd0 0xa0 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0xa0 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd0 0xa0 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd0 0xa1 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0xa1 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd0 0xa1 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd0 0xa2 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0xa2 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd0 0xa2 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd0 0xa3 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0xa3 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd0 0xa3 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd0 0xa4 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd0 0xa4 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 4080] 1]}
   [0xd0 0xa4 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -4081] 1]}
   [0xd0 0xa5 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd0 0xa5 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 4080] 1]}
   [0xd0 0xa5 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -4081] 1]}
   [0xd0 0xa6 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] 1]}
   [0xd0 0xa6 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 4080] 1]}
   [0xd0 0xa6 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -4081] 1]}
   [0xd0 0xa7 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd0 0xa7 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 4080] 1]}
   [0xd0 0xa7 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -4081] 1]}
   [0xd0 0xa8 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0xa8 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd0 0xa8 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd0 0xa9 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0xa9 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd0 0xa9 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd0 0xaa 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0xaa 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd0 0xaa 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd0 0xab 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0xab 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd0 0xab 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd0 0xac 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/si] 1]}
   [0xd0 0xac 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/si 4080] 1]}
   [0xd0 0xac 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/si -4081] 1]}
   [0xd0 0xad 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/di] 1]}
   [0xd0 0xad 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/di 4080] 1]}
   [0xd0 0xad 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/di -4081] 1]}
   [0xd0 0xae 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp] 1]}
   [0xd0 0xae 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp 4080] 1]}
   [0xd0 0xae 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp -4081] 1]}
   [0xd0 0xaf 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx] 1]}
   [0xd0 0xaf 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx 4080] 1]}
   [0xd0 0xaf 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx -4081] 1]}
   [0xd0 0xb0 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0xb0 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd0 0xb0 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd0 0xb1 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0xb1 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd0 0xb1 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd0 0xb2 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0xb2 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd0 0xb2 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd0 0xb3 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0xb3 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd0 0xb3 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd0 0xb4 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd0 0xb4 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 4080] 1]}
   [0xd0 0xb4 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -4081] 1]}
   [0xd0 0xb5 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd0 0xb5 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 4080] 1]}
   [0xd0 0xb5 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -4081] 1]}
   [0xd0 0xb6 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] 1]}
   [0xd0 0xb6 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 4080] 1]}
   [0xd0 0xb6 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -4081] 1]}
   [0xd0 0xb7 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd0 0xb7 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 4080] 1]}
   [0xd0 0xb7 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -4081] 1]}
   [0xd0 0xb8 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] 1]}
   [0xd0 0xb8 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd0 0xb8 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd0 0xb9 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] 1]}
   [0xd0 0xb9 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd0 0xb9 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd0 0xba 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] 1]}
   [0xd0 0xba 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd0 0xba 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd0 0xbb 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] 1]}
   [0xd0 0xbb 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd0 0xbb 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd0 0xbc 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/si] 1]}
   [0xd0 0xbc 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/si 4080] 1]}
   [0xd0 0xbc 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/si -4081] 1]}
   [0xd0 0xbd 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/di] 1]}
   [0xd0 0xbd 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/di 4080] 1]}
   [0xd0 0xbd 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/di -4081] 1]}
   [0xd0 0xbe 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp] 1]}
   [0xd0 0xbe 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp 4080] 1]}
   [0xd0 0xbe 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp -4081] 1]}
   [0xd0 0xbf 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx] 1]}
   [0xd0 0xbf 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx 4080] 1]}
   [0xd0 0xbf 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx -4081] 1]}
   [0xd0 0xc0] {::i/tag ::i/rol, ::i/args [::r/al 1]}
   [0xd0 0xc1] {::i/tag ::i/rol, ::i/args [::r/cl 1]}
   [0xd0 0xc2] {::i/tag ::i/rol, ::i/args [::r/dl 1]}
   [0xd0 0xc3] {::i/tag ::i/rol, ::i/args [::r/bl 1]}
   [0xd0 0xc4] {::i/tag ::i/rol, ::i/args [::r/ah 1]}
   [0xd0 0xc5] {::i/tag ::i/rol, ::i/args [::r/ch 1]}
   [0xd0 0xc6] {::i/tag ::i/rol, ::i/args [::r/dh 1]}
   [0xd0 0xc7] {::i/tag ::i/rol, ::i/args [::r/bh 1]}
   [0xd0 0xc8] {::i/tag ::i/ror, ::i/args [::r/al 1]}
   [0xd0 0xc9] {::i/tag ::i/ror, ::i/args [::r/cl 1]}
   [0xd0 0xca] {::i/tag ::i/ror, ::i/args [::r/dl 1]}
   [0xd0 0xcb] {::i/tag ::i/ror, ::i/args [::r/bl 1]}
   [0xd0 0xcc] {::i/tag ::i/ror, ::i/args [::r/ah 1]}
   [0xd0 0xcd] {::i/tag ::i/ror, ::i/args [::r/ch 1]}
   [0xd0 0xce] {::i/tag ::i/ror, ::i/args [::r/dh 1]}
   [0xd0 0xcf] {::i/tag ::i/ror, ::i/args [::r/bh 1]}
   [0xd0 0xd0] {::i/tag ::i/rcl, ::i/args [::r/al 1]}
   [0xd0 0xd1] {::i/tag ::i/rcl, ::i/args [::r/cl 1]}
   [0xd0 0xd2] {::i/tag ::i/rcl, ::i/args [::r/dl 1]}
   [0xd0 0xd3] {::i/tag ::i/rcl, ::i/args [::r/bl 1]}
   [0xd0 0xd4] {::i/tag ::i/rcl, ::i/args [::r/ah 1]}
   [0xd0 0xd5] {::i/tag ::i/rcl, ::i/args [::r/ch 1]}
   [0xd0 0xd6] {::i/tag ::i/rcl, ::i/args [::r/dh 1]}
   [0xd0 0xd7] {::i/tag ::i/rcl, ::i/args [::r/bh 1]}
   [0xd0 0xd8] {::i/tag ::i/rcr, ::i/args [::r/al 1]}
   [0xd0 0xd9] {::i/tag ::i/rcr, ::i/args [::r/cl 1]}
   [0xd0 0xda] {::i/tag ::i/rcr, ::i/args [::r/dl 1]}
   [0xd0 0xdb] {::i/tag ::i/rcr, ::i/args [::r/bl 1]}
   [0xd0 0xdc] {::i/tag ::i/rcr, ::i/args [::r/ah 1]}
   [0xd0 0xdd] {::i/tag ::i/rcr, ::i/args [::r/ch 1]}
   [0xd0 0xde] {::i/tag ::i/rcr, ::i/args [::r/dh 1]}
   [0xd0 0xdf] {::i/tag ::i/rcr, ::i/args [::r/bh 1]}
   [0xd0 0xe0] {::i/tag ::i/shl, ::i/args [::r/al 1]}
   [0xd0 0xe1] {::i/tag ::i/shl, ::i/args [::r/cl 1]}
   [0xd0 0xe2] {::i/tag ::i/shl, ::i/args [::r/dl 1]}
   [0xd0 0xe3] {::i/tag ::i/shl, ::i/args [::r/bl 1]}
   [0xd0 0xe4] {::i/tag ::i/shl, ::i/args [::r/ah 1]}
   [0xd0 0xe5] {::i/tag ::i/shl, ::i/args [::r/ch 1]}
   [0xd0 0xe6] {::i/tag ::i/shl, ::i/args [::r/dh 1]}
   [0xd0 0xe7] {::i/tag ::i/shl, ::i/args [::r/bh 1]}
   [0xd0 0xe8] {::i/tag ::i/shr, ::i/args [::r/al 1]}
   [0xd0 0xe9] {::i/tag ::i/shr, ::i/args [::r/cl 1]}
   [0xd0 0xea] {::i/tag ::i/shr, ::i/args [::r/dl 1]}
   [0xd0 0xeb] {::i/tag ::i/shr, ::i/args [::r/bl 1]}
   [0xd0 0xec] {::i/tag ::i/shr, ::i/args [::r/ah 1]}
   [0xd0 0xed] {::i/tag ::i/shr, ::i/args [::r/ch 1]}
   [0xd0 0xee] {::i/tag ::i/shr, ::i/args [::r/dh 1]}
   [0xd0 0xef] {::i/tag ::i/shr, ::i/args [::r/bh 1]}
   [0xd0 0xf0] {::i/tag ::i/shl, ::i/args [::r/al 1]}
   [0xd0 0xf1] {::i/tag ::i/shl, ::i/args [::r/cl 1]}
   [0xd0 0xf2] {::i/tag ::i/shl, ::i/args [::r/dl 1]}
   [0xd0 0xf3] {::i/tag ::i/shl, ::i/args [::r/bl 1]}
   [0xd0 0xf4] {::i/tag ::i/shl, ::i/args [::r/ah 1]}
   [0xd0 0xf5] {::i/tag ::i/shl, ::i/args [::r/ch 1]}
   [0xd0 0xf6] {::i/tag ::i/shl, ::i/args [::r/dh 1]}
   [0xd0 0xf7] {::i/tag ::i/shl, ::i/args [::r/bh 1]}
   [0xd0 0xf8] {::i/tag ::i/sar, ::i/args [::r/al 1]}
   [0xd0 0xf9] {::i/tag ::i/sar, ::i/args [::r/cl 1]}
   [0xd0 0xfa] {::i/tag ::i/sar, ::i/args [::r/dl 1]}
   [0xd0 0xfb] {::i/tag ::i/sar, ::i/args [::r/bl 1]}
   [0xd0 0xfc] {::i/tag ::i/sar, ::i/args [::r/ah 1]}
   [0xd0 0xfd] {::i/tag ::i/sar, ::i/args [::r/ch 1]}
   [0xd0 0xfe] {::i/tag ::i/sar, ::i/args [::r/dh 1]}
   [0xd0 0xff] {::i/tag ::i/sar, ::i/args [::r/bh 1]}})


(def decode-examples-0xd1
  {[0xd1 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x01] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x02] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x03] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x04] {::i/tag ::i/rol, ::i/args [[::r/si] 1]}
   [0xd1 0x05] {::i/tag ::i/rol, ::i/args [[::r/di] 1]}
   [0xd1 0x06 0x12 0xf4] {::i/tag ::i/rol, ::i/args [[0xf412] 1]}
   [0xd1 0x06 0xf4 0x12] {::i/tag ::i/rol, ::i/args [[0x12f4] 1]}
   [0xd1 0x07] {::i/tag ::i/rol, ::i/args [[::r/bx] 1]}
   [0xd1 0x08] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x09] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x0a] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x0b] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x0c] {::i/tag ::i/ror, ::i/args [[::r/si] 1]}
   [0xd1 0x0d] {::i/tag ::i/ror, ::i/args [[::r/di] 1]}
   [0xd1 0x0e 0x12 0xf4] {::i/tag ::i/ror, ::i/args [[0xf412] 1]}
   [0xd1 0x0e 0xf4 0x12] {::i/tag ::i/ror, ::i/args [[0x12f4] 1]}
   [0xd1 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx] 1]}
   [0xd1 0x10] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x11] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x12] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x13] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x14] {::i/tag ::i/rcl, ::i/args [[::r/si] 1]}
   [0xd1 0x15] {::i/tag ::i/rcl, ::i/args [[::r/di] 1]}
   [0xd1 0x16 0x12 0xf4] {::i/tag ::i/rcl, ::i/args [[0xf412] 1]}
   [0xd1 0x16 0xf4 0x12] {::i/tag ::i/rcl, ::i/args [[0x12f4] 1]}
   [0xd1 0x17] {::i/tag ::i/rcl, ::i/args [[::r/bx] 1]}
   [0xd1 0x18] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x19] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x1a] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x1b] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x1c] {::i/tag ::i/rcr, ::i/args [[::r/si] 1]}
   [0xd1 0x1d] {::i/tag ::i/rcr, ::i/args [[::r/di] 1]}
   [0xd1 0x1e 0x12 0xf4] {::i/tag ::i/rcr, ::i/args [[0xf412] 1]}
   [0xd1 0x1e 0xf4 0x12] {::i/tag ::i/rcr, ::i/args [[0x12f4] 1]}
   [0xd1 0x1f] {::i/tag ::i/rcr, ::i/args [[::r/bx] 1]}
   [0xd1 0x20] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x21] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x22] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x23] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x24] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd1 0x25] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd1 0x26 0x12 0xf4] {::i/tag ::i/shl, ::i/args [[0xf412] 1]}
   [0xd1 0x26 0xf4 0x12] {::i/tag ::i/shl, ::i/args [[0x12f4] 1]}
   [0xd1 0x27] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd1 0x28] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x29] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x2a] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x2b] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x2c] {::i/tag ::i/shr, ::i/args [[::r/si] 1]}
   [0xd1 0x2d] {::i/tag ::i/shr, ::i/args [[::r/di] 1]}
   [0xd1 0x2e 0x12 0xf4] {::i/tag ::i/shr, ::i/args [[0xf412] 1]}
   [0xd1 0x2e 0xf4 0x12] {::i/tag ::i/shr, ::i/args [[0x12f4] 1]}
   [0xd1 0x2f] {::i/tag ::i/shr, ::i/args [[::r/bx] 1]}
   [0xd1 0x30] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x31] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x32] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x33] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x34] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd1 0x35] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd1 0x36 0x12 0xf4] {::i/tag ::i/shl, ::i/args [[0xf412] 1]}
   [0xd1 0x36 0xf4 0x12] {::i/tag ::i/shl, ::i/args [[0x12f4] 1]}
   [0xd1 0x37] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd1 0x38] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x39] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x3a] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x3b] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x3c] {::i/tag ::i/sar, ::i/args [[::r/si] 1]}
   [0xd1 0x3d] {::i/tag ::i/sar, ::i/args [[::r/di] 1]}
   [0xd1 0x3e 0x12 0xf4] {::i/tag ::i/sar, ::i/args [[0xf412] 1]}
   [0xd1 0x3e 0xf4 0x12] {::i/tag ::i/sar, ::i/args [[0x12f4] 1]}
   [0xd1 0x3f] {::i/tag ::i/sar, ::i/args [[::r/bx] 1]}
   [0xd1 0x40 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x40 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd1 0x40 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd1 0x41 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x41 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd1 0x41 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd1 0x42 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x42 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd1 0x42 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd1 0x43 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x43 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd1 0x43 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd1 0x44 0x00] {::i/tag ::i/rol, ::i/args [[::r/si] 1]}
   [0xd1 0x44 0xf0] {::i/tag ::i/rol, ::i/args [[::r/si -16] 1]}
   [0xd1 0x44 0x0f] {::i/tag ::i/rol, ::i/args [[::r/si 15] 1]}
   [0xd1 0x45 0x00] {::i/tag ::i/rol, ::i/args [[::r/di] 1]}
   [0xd1 0x45 0xf0] {::i/tag ::i/rol, ::i/args [[::r/di -16] 1]}
   [0xd1 0x45 0x0f] {::i/tag ::i/rol, ::i/args [[::r/di 15] 1]}
   [0xd1 0x46 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp] 1]}
   [0xd1 0x46 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp -16] 1]}
   [0xd1 0x46 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp 15] 1]}
   [0xd1 0x47 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx] 1]}
   [0xd1 0x47 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx -16] 1]}
   [0xd1 0x47 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx 15] 1]}
   [0xd1 0x48 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x48 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd1 0x48 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd1 0x49 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x49 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd1 0x49 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd1 0x4a 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x4a 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd1 0x4a 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd1 0x4b 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x4b 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd1 0x4b 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd1 0x4c 0x00] {::i/tag ::i/ror, ::i/args [[::r/si] 1]}
   [0xd1 0x4c 0xf0] {::i/tag ::i/ror, ::i/args [[::r/si -16] 1]}
   [0xd1 0x4c 0x0f] {::i/tag ::i/ror, ::i/args [[::r/si 15] 1]}
   [0xd1 0x4d 0x00] {::i/tag ::i/ror, ::i/args [[::r/di] 1]}
   [0xd1 0x4d 0xf0] {::i/tag ::i/ror, ::i/args [[::r/di -16] 1]}
   [0xd1 0x4d 0x0f] {::i/tag ::i/ror, ::i/args [[::r/di 15] 1]}
   [0xd1 0x4e 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp] 1]}
   [0xd1 0x4e 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp -16] 1]}
   [0xd1 0x4e 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp 15] 1]}
   [0xd1 0x4f 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx] 1]}
   [0xd1 0x4f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx -16] 1]}
   [0xd1 0x4f 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx 15] 1]}
   [0xd1 0x50 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x50 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd1 0x50 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd1 0x51 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x51 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd1 0x51 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd1 0x52 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x52 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd1 0x52 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd1 0x53 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x53 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd1 0x53 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd1 0x54 0x00] {::i/tag ::i/rcl, ::i/args [[::r/si] 1]}
   [0xd1 0x54 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/si -16] 1]}
   [0xd1 0x54 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/si 15] 1]}
   [0xd1 0x55 0x00] {::i/tag ::i/rcl, ::i/args [[::r/di] 1]}
   [0xd1 0x55 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/di -16] 1]}
   [0xd1 0x55 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/di 15] 1]}
   [0xd1 0x56 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp] 1]}
   [0xd1 0x56 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp -16] 1]}
   [0xd1 0x56 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp 15] 1]}
   [0xd1 0x57 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx] 1]}
   [0xd1 0x57 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx -16] 1]}
   [0xd1 0x57 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx 15] 1]}
   [0xd1 0x58 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x58 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd1 0x58 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd1 0x59 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x59 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd1 0x59 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd1 0x5a 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x5a 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd1 0x5a 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd1 0x5b 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x5b 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd1 0x5b 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd1 0x5c 0x00] {::i/tag ::i/rcr, ::i/args [[::r/si] 1]}
   [0xd1 0x5c 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/si -16] 1]}
   [0xd1 0x5c 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/si 15] 1]}
   [0xd1 0x5d 0x00] {::i/tag ::i/rcr, ::i/args [[::r/di] 1]}
   [0xd1 0x5d 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/di -16] 1]}
   [0xd1 0x5d 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/di 15] 1]}
   [0xd1 0x5e 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp] 1]}
   [0xd1 0x5e 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp -16] 1]}
   [0xd1 0x5e 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp 15] 1]}
   [0xd1 0x5f 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx] 1]}
   [0xd1 0x5f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx -16] 1]}
   [0xd1 0x5f 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx 15] 1]}
   [0xd1 0x60 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x60 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd1 0x60 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd1 0x61 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x61 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd1 0x61 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd1 0x62 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x62 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd1 0x62 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd1 0x63 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x63 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd1 0x63 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd1 0x64 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd1 0x64 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -16] 1]}
   [0xd1 0x64 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 15] 1]}
   [0xd1 0x65 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd1 0x65 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -16] 1]}
   [0xd1 0x65 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 15] 1]}
   [0xd1 0x66 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] 1]}
   [0xd1 0x66 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -16] 1]}
   [0xd1 0x66 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 15] 1]}
   [0xd1 0x67 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd1 0x67 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -16] 1]}
   [0xd1 0x67 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 15] 1]}
   [0xd1 0x68 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x68 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd1 0x68 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd1 0x69 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x69 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd1 0x69 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd1 0x6a 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x6a 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd1 0x6a 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd1 0x6b 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x6b 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd1 0x6b 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd1 0x6c 0x00] {::i/tag ::i/shr, ::i/args [[::r/si] 1]}
   [0xd1 0x6c 0xf0] {::i/tag ::i/shr, ::i/args [[::r/si -16] 1]}
   [0xd1 0x6c 0x0f] {::i/tag ::i/shr, ::i/args [[::r/si 15] 1]}
   [0xd1 0x6d 0x00] {::i/tag ::i/shr, ::i/args [[::r/di] 1]}
   [0xd1 0x6d 0xf0] {::i/tag ::i/shr, ::i/args [[::r/di -16] 1]}
   [0xd1 0x6d 0x0f] {::i/tag ::i/shr, ::i/args [[::r/di 15] 1]}
   [0xd1 0x6e 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp] 1]}
   [0xd1 0x6e 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp -16] 1]}
   [0xd1 0x6e 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp 15] 1]}
   [0xd1 0x6f 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx] 1]}
   [0xd1 0x6f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx -16] 1]}
   [0xd1 0x6f 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx 15] 1]}
   [0xd1 0x70 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x70 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd1 0x70 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd1 0x71 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x71 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd1 0x71 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd1 0x72 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x72 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd1 0x72 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd1 0x73 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x73 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd1 0x73 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd1 0x74 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd1 0x74 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -16] 1]}
   [0xd1 0x74 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 15] 1]}
   [0xd1 0x75 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd1 0x75 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -16] 1]}
   [0xd1 0x75 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 15] 1]}
   [0xd1 0x76 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] 1]}
   [0xd1 0x76 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -16] 1]}
   [0xd1 0x76 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 15] 1]}
   [0xd1 0x77 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd1 0x77 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -16] 1]}
   [0xd1 0x77 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 15] 1]}
   [0xd1 0x78 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x78 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si -16] 1]}
   [0xd1 0x78 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si 15] 1]}
   [0xd1 0x79 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x79 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di -16] 1]}
   [0xd1 0x79 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di 15] 1]}
   [0xd1 0x7a 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x7a 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si -16] 1]}
   [0xd1 0x7a 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si 15] 1]}
   [0xd1 0x7b 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x7b 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di -16] 1]}
   [0xd1 0x7b 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di 15] 1]}
   [0xd1 0x7c 0x00] {::i/tag ::i/sar, ::i/args [[::r/si] 1]}
   [0xd1 0x7c 0xf0] {::i/tag ::i/sar, ::i/args [[::r/si -16] 1]}
   [0xd1 0x7c 0x0f] {::i/tag ::i/sar, ::i/args [[::r/si 15] 1]}
   [0xd1 0x7d 0x00] {::i/tag ::i/sar, ::i/args [[::r/di] 1]}
   [0xd1 0x7d 0xf0] {::i/tag ::i/sar, ::i/args [[::r/di -16] 1]}
   [0xd1 0x7d 0x0f] {::i/tag ::i/sar, ::i/args [[::r/di 15] 1]}
   [0xd1 0x7e 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp] 1]}
   [0xd1 0x7e 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp -16] 1]}
   [0xd1 0x7e 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp 15] 1]}
   [0xd1 0x7f 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx] 1]}
   [0xd1 0x7f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx -16] 1]}
   [0xd1 0x7f 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx 15] 1]}
   [0xd1 0x80 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x80 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd1 0x80 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd1 0x81 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x81 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd1 0x81 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd1 0x82 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x82 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd1 0x82 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd1 0x83 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x83 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd1 0x83 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd1 0x84 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/si] 1]}
   [0xd1 0x84 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/si 4080] 1]}
   [0xd1 0x84 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/si -4081] 1]}
   [0xd1 0x85 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/di] 1]}
   [0xd1 0x85 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/di 4080] 1]}
   [0xd1 0x85 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/di -4081] 1]}
   [0xd1 0x86 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp] 1]}
   [0xd1 0x86 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp 4080] 1]}
   [0xd1 0x86 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp -4081] 1]}
   [0xd1 0x87 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx] 1]}
   [0xd1 0x87 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx 4080] 1]}
   [0xd1 0x87 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx -4081] 1]}
   [0xd1 0x88 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x88 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd1 0x88 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd1 0x89 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x89 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd1 0x89 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd1 0x8a 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x8a 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd1 0x8a 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd1 0x8b 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x8b 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd1 0x8b 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd1 0x8c 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/si] 1]}
   [0xd1 0x8c 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/si 4080] 1]}
   [0xd1 0x8c 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/si -4081] 1]}
   [0xd1 0x8d 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/di] 1]}
   [0xd1 0x8d 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/di 4080] 1]}
   [0xd1 0x8d 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/di -4081] 1]}
   [0xd1 0x8e 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp] 1]}
   [0xd1 0x8e 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp 4080] 1]}
   [0xd1 0x8e 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp -4081] 1]}
   [0xd1 0x8f 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx] 1]}
   [0xd1 0x8f 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx 4080] 1]}
   [0xd1 0x8f 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx -4081] 1]}
   [0xd1 0x90 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x90 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd1 0x90 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd1 0x91 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x91 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd1 0x91 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd1 0x92 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x92 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd1 0x92 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd1 0x93 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x93 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd1 0x93 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd1 0x94 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/si] 1]}
   [0xd1 0x94 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/si 4080] 1]}
   [0xd1 0x94 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/si -4081] 1]}
   [0xd1 0x95 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/di] 1]}
   [0xd1 0x95 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/di 4080] 1]}
   [0xd1 0x95 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/di -4081] 1]}
   [0xd1 0x96 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp] 1]}
   [0xd1 0x96 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp 4080] 1]}
   [0xd1 0x96 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp -4081] 1]}
   [0xd1 0x97 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx] 1]}
   [0xd1 0x97 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx 4080] 1]}
   [0xd1 0x97 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx -4081] 1]}
   [0xd1 0x98 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0x98 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd1 0x98 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd1 0x99 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0x99 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd1 0x99 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd1 0x9a 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0x9a 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd1 0x9a 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd1 0x9b 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0x9b 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd1 0x9b 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd1 0x9c 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/si] 1]}
   [0xd1 0x9c 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/si 4080] 1]}
   [0xd1 0x9c 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/si -4081] 1]}
   [0xd1 0x9d 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/di] 1]}
   [0xd1 0x9d 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/di 4080] 1]}
   [0xd1 0x9d 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/di -4081] 1]}
   [0xd1 0x9e 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp] 1]}
   [0xd1 0x9e 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp 4080] 1]}
   [0xd1 0x9e 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp -4081] 1]}
   [0xd1 0x9f 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx] 1]}
   [0xd1 0x9f 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx 4080] 1]}
   [0xd1 0x9f 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx -4081] 1]}
   [0xd1 0xa0 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0xa0 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd1 0xa0 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd1 0xa1 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0xa1 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd1 0xa1 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd1 0xa2 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0xa2 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd1 0xa2 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd1 0xa3 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0xa3 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd1 0xa3 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd1 0xa4 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd1 0xa4 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 4080] 1]}
   [0xd1 0xa4 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -4081] 1]}
   [0xd1 0xa5 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd1 0xa5 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 4080] 1]}
   [0xd1 0xa5 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -4081] 1]}
   [0xd1 0xa6 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] 1]}
   [0xd1 0xa6 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 4080] 1]}
   [0xd1 0xa6 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -4081] 1]}
   [0xd1 0xa7 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd1 0xa7 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 4080] 1]}
   [0xd1 0xa7 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -4081] 1]}
   [0xd1 0xa8 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0xa8 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd1 0xa8 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd1 0xa9 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0xa9 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd1 0xa9 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd1 0xaa 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0xaa 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd1 0xaa 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd1 0xab 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0xab 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd1 0xab 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd1 0xac 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/si] 1]}
   [0xd1 0xac 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/si 4080] 1]}
   [0xd1 0xac 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/si -4081] 1]}
   [0xd1 0xad 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/di] 1]}
   [0xd1 0xad 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/di 4080] 1]}
   [0xd1 0xad 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/di -4081] 1]}
   [0xd1 0xae 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp] 1]}
   [0xd1 0xae 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp 4080] 1]}
   [0xd1 0xae 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp -4081] 1]}
   [0xd1 0xaf 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx] 1]}
   [0xd1 0xaf 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx 4080] 1]}
   [0xd1 0xaf 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx -4081] 1]}
   [0xd1 0xb0 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0xb0 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd1 0xb0 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd1 0xb1 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0xb1 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd1 0xb1 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd1 0xb2 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0xb2 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd1 0xb2 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd1 0xb3 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0xb3 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd1 0xb3 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd1 0xb4 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] 1]}
   [0xd1 0xb4 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 4080] 1]}
   [0xd1 0xb4 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -4081] 1]}
   [0xd1 0xb5 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] 1]}
   [0xd1 0xb5 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 4080] 1]}
   [0xd1 0xb5 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -4081] 1]}
   [0xd1 0xb6 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] 1]}
   [0xd1 0xb6 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 4080] 1]}
   [0xd1 0xb6 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -4081] 1]}
   [0xd1 0xb7 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] 1]}
   [0xd1 0xb7 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 4080] 1]}
   [0xd1 0xb7 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -4081] 1]}
   [0xd1 0xb8 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] 1]}
   [0xd1 0xb8 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si 4080] 1]}
   [0xd1 0xb8 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si -4081] 1]}
   [0xd1 0xb9 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] 1]}
   [0xd1 0xb9 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di 4080] 1]}
   [0xd1 0xb9 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di -4081] 1]}
   [0xd1 0xba 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] 1]}
   [0xd1 0xba 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si 4080] 1]}
   [0xd1 0xba 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si -4081] 1]}
   [0xd1 0xbb 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] 1]}
   [0xd1 0xbb 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di 4080] 1]}
   [0xd1 0xbb 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di -4081] 1]}
   [0xd1 0xbc 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/si] 1]}
   [0xd1 0xbc 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/si 4080] 1]}
   [0xd1 0xbc 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/si -4081] 1]}
   [0xd1 0xbd 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/di] 1]}
   [0xd1 0xbd 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/di 4080] 1]}
   [0xd1 0xbd 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/di -4081] 1]}
   [0xd1 0xbe 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp] 1]}
   [0xd1 0xbe 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp 4080] 1]}
   [0xd1 0xbe 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp -4081] 1]}
   [0xd1 0xbf 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx] 1]}
   [0xd1 0xbf 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx 4080] 1]}
   [0xd1 0xbf 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx -4081] 1]}
   [0xd1 0xc0] {::i/tag ::i/rol, ::i/args [::r/ax 1]}
   [0xd1 0xc1] {::i/tag ::i/rol, ::i/args [::r/cx 1]}
   [0xd1 0xc2] {::i/tag ::i/rol, ::i/args [::r/dx 1]}
   [0xd1 0xc3] {::i/tag ::i/rol, ::i/args [::r/bx 1]}
   [0xd1 0xc4] {::i/tag ::i/rol, ::i/args [::r/sp 1]}
   [0xd1 0xc5] {::i/tag ::i/rol, ::i/args [::r/bp 1]}
   [0xd1 0xc6] {::i/tag ::i/rol, ::i/args [::r/si 1]}
   [0xd1 0xc7] {::i/tag ::i/rol, ::i/args [::r/di 1]}
   [0xd1 0xc8] {::i/tag ::i/ror, ::i/args [::r/ax 1]}
   [0xd1 0xc9] {::i/tag ::i/ror, ::i/args [::r/cx 1]}
   [0xd1 0xca] {::i/tag ::i/ror, ::i/args [::r/dx 1]}
   [0xd1 0xcb] {::i/tag ::i/ror, ::i/args [::r/bx 1]}
   [0xd1 0xcc] {::i/tag ::i/ror, ::i/args [::r/sp 1]}
   [0xd1 0xcd] {::i/tag ::i/ror, ::i/args [::r/bp 1]}
   [0xd1 0xce] {::i/tag ::i/ror, ::i/args [::r/si 1]}
   [0xd1 0xcf] {::i/tag ::i/ror, ::i/args [::r/di 1]}
   [0xd1 0xd0] {::i/tag ::i/rcl, ::i/args [::r/ax 1]}
   [0xd1 0xd1] {::i/tag ::i/rcl, ::i/args [::r/cx 1]}
   [0xd1 0xd2] {::i/tag ::i/rcl, ::i/args [::r/dx 1]}
   [0xd1 0xd3] {::i/tag ::i/rcl, ::i/args [::r/bx 1]}
   [0xd1 0xd4] {::i/tag ::i/rcl, ::i/args [::r/sp 1]}
   [0xd1 0xd5] {::i/tag ::i/rcl, ::i/args [::r/bp 1]}
   [0xd1 0xd6] {::i/tag ::i/rcl, ::i/args [::r/si 1]}
   [0xd1 0xd7] {::i/tag ::i/rcl, ::i/args [::r/di 1]}
   [0xd1 0xd8] {::i/tag ::i/rcr, ::i/args [::r/ax 1]}
   [0xd1 0xd9] {::i/tag ::i/rcr, ::i/args [::r/cx 1]}
   [0xd1 0xda] {::i/tag ::i/rcr, ::i/args [::r/dx 1]}
   [0xd1 0xdb] {::i/tag ::i/rcr, ::i/args [::r/bx 1]}
   [0xd1 0xdc] {::i/tag ::i/rcr, ::i/args [::r/sp 1]}
   [0xd1 0xdd] {::i/tag ::i/rcr, ::i/args [::r/bp 1]}
   [0xd1 0xde] {::i/tag ::i/rcr, ::i/args [::r/si 1]}
   [0xd1 0xdf] {::i/tag ::i/rcr, ::i/args [::r/di 1]}
   [0xd1 0xe0] {::i/tag ::i/shl, ::i/args [::r/ax 1]}
   [0xd1 0xe1] {::i/tag ::i/shl, ::i/args [::r/cx 1]}
   [0xd1 0xe2] {::i/tag ::i/shl, ::i/args [::r/dx 1]}
   [0xd1 0xe3] {::i/tag ::i/shl, ::i/args [::r/bx 1]}
   [0xd1 0xe4] {::i/tag ::i/shl, ::i/args [::r/sp 1]}
   [0xd1 0xe5] {::i/tag ::i/shl, ::i/args [::r/bp 1]}
   [0xd1 0xe6] {::i/tag ::i/shl, ::i/args [::r/si 1]}
   [0xd1 0xe7] {::i/tag ::i/shl, ::i/args [::r/di 1]}
   [0xd1 0xe8] {::i/tag ::i/shr, ::i/args [::r/ax 1]}
   [0xd1 0xe9] {::i/tag ::i/shr, ::i/args [::r/cx 1]}
   [0xd1 0xea] {::i/tag ::i/shr, ::i/args [::r/dx 1]}
   [0xd1 0xeb] {::i/tag ::i/shr, ::i/args [::r/bx 1]}
   [0xd1 0xec] {::i/tag ::i/shr, ::i/args [::r/sp 1]}
   [0xd1 0xed] {::i/tag ::i/shr, ::i/args [::r/bp 1]}
   [0xd1 0xee] {::i/tag ::i/shr, ::i/args [::r/si 1]}
   [0xd1 0xef] {::i/tag ::i/shr, ::i/args [::r/di 1]}
   [0xd1 0xf0] {::i/tag ::i/shl, ::i/args [::r/ax 1]}
   [0xd1 0xf1] {::i/tag ::i/shl, ::i/args [::r/cx 1]}
   [0xd1 0xf2] {::i/tag ::i/shl, ::i/args [::r/dx 1]}
   [0xd1 0xf3] {::i/tag ::i/shl, ::i/args [::r/bx 1]}
   [0xd1 0xf4] {::i/tag ::i/shl, ::i/args [::r/sp 1]}
   [0xd1 0xf5] {::i/tag ::i/shl, ::i/args [::r/bp 1]}
   [0xd1 0xf6] {::i/tag ::i/shl, ::i/args [::r/si 1]}
   [0xd1 0xf7] {::i/tag ::i/shl, ::i/args [::r/di 1]}
   [0xd1 0xf8] {::i/tag ::i/sar, ::i/args [::r/ax 1]}
   [0xd1 0xf9] {::i/tag ::i/sar, ::i/args [::r/cx 1]}
   [0xd1 0xfa] {::i/tag ::i/sar, ::i/args [::r/dx 1]}
   [0xd1 0xfb] {::i/tag ::i/sar, ::i/args [::r/bx 1]}
   [0xd1 0xfc] {::i/tag ::i/sar, ::i/args [::r/sp 1]}
   [0xd1 0xfd] {::i/tag ::i/sar, ::i/args [::r/bp 1]}
   [0xd1 0xfe] {::i/tag ::i/sar, ::i/args [::r/si 1]}
   [0xd1 0xff] {::i/tag ::i/sar, ::i/args [::r/di 1]}})


(def decode-examples-0xd2
  {[0xd2 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x01] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x02] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x03] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x04] {::i/tag ::i/rol, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x05] {::i/tag ::i/rol, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x06 0x12 0x34] {::i/tag ::i/rol, ::i/args [[0x3412] ::r/cl]}
   [0xd2 0x06 0x34 0x12] {::i/tag ::i/rol, ::i/args [[0x1234] ::r/cl]}
   [0xd2 0x07] {::i/tag ::i/rol, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x08] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x09] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x0a] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x0b] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x0c] {::i/tag ::i/ror, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x0d] {::i/tag ::i/ror, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x0e 0x12 0x34] {::i/tag ::i/ror, ::i/args [[0x3412] ::r/cl]}
   [0xd2 0x0e 0x34 0x12] {::i/tag ::i/ror, ::i/args [[0x1234] ::r/cl]}
   [0xd2 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x10] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x11] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x12] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x13] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x14] {::i/tag ::i/rcl, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x15] {::i/tag ::i/rcl, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x16 0x12 0x34] {::i/tag ::i/rcl, ::i/args [[0x3412] ::r/cl]}
   [0xd2 0x16 0x34 0x12] {::i/tag ::i/rcl, ::i/args [[0x1234] ::r/cl]}
   [0xd2 0x17] {::i/tag ::i/rcl, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x18] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x19] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x1a] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x1b] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x1c] {::i/tag ::i/rcr, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x1d] {::i/tag ::i/rcr, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x1e 0x12 0x34] {::i/tag ::i/rcr, ::i/args [[0x3412] ::r/cl]}
   [0xd2 0x1e 0x34 0x12] {::i/tag ::i/rcr, ::i/args [[0x1234] ::r/cl]}
   [0xd2 0x1f] {::i/tag ::i/rcr, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x20] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x21] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x22] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x23] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x24] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x25] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x26 0x12 0x34] {::i/tag ::i/shl, ::i/args [[0x3412] ::r/cl]}
   [0xd2 0x26 0x34 0x12] {::i/tag ::i/shl, ::i/args [[0x1234] ::r/cl]}
   [0xd2 0x27] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x28] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x29] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x2a] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x2b] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x2c] {::i/tag ::i/shr, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x2d] {::i/tag ::i/shr, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x2e 0x12 0x34] {::i/tag ::i/shr, ::i/args [[0x3412] ::r/cl]}
   [0xd2 0x2e 0x34 0x12] {::i/tag ::i/shr, ::i/args [[0x1234] ::r/cl]}
   [0xd2 0x2f] {::i/tag ::i/shr, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x30] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x31] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x32] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x33] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x34] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x35] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x36 0x12 0x34] {::i/tag ::i/shl, ::i/args [[0x3412] ::r/cl]}
   [0xd2 0x36 0x34 0x12] {::i/tag ::i/shl, ::i/args [[0x1234] ::r/cl]}
   [0xd2 0x37] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x38] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x39] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x3a] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x3b] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x3c] {::i/tag ::i/sar, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x3d] {::i/tag ::i/sar, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x3e 0x12 0x34] {::i/tag ::i/sar, ::i/args [[0x3412] ::r/cl]}
   [0xd2 0x3e 0x34 0x12] {::i/tag ::i/sar, ::i/args [[0x1234] ::r/cl]}
   [0xd2 0x3f] {::i/tag ::i/sar, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x40 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x40 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd2 0x40 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd2 0x41 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x41 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd2 0x41 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd2 0x42 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x42 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd2 0x42 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd2 0x43 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x43 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd2 0x43 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd2 0x44 0x00] {::i/tag ::i/rol, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x44 0xf0] {::i/tag ::i/rol, ::i/args [[::r/si -16] ::r/cl]}
   [0xd2 0x44 0x0f] {::i/tag ::i/rol, ::i/args [[::r/si 15] ::r/cl]}
   [0xd2 0x45 0x00] {::i/tag ::i/rol, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x45 0xf0] {::i/tag ::i/rol, ::i/args [[::r/di -16] ::r/cl]}
   [0xd2 0x45 0x0f] {::i/tag ::i/rol, ::i/args [[::r/di 15] ::r/cl]}
   [0xd2 0x46 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x46 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd2 0x46 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd2 0x47 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x47 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd2 0x47 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd2 0x48 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x48 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd2 0x48 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd2 0x49 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x49 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd2 0x49 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd2 0x4a 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x4a 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd2 0x4a 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd2 0x4b 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x4b 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd2 0x4b 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd2 0x4c 0x00] {::i/tag ::i/ror, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x4c 0xf0] {::i/tag ::i/ror, ::i/args [[::r/si -16] ::r/cl]}
   [0xd2 0x4c 0x0f] {::i/tag ::i/ror, ::i/args [[::r/si 15] ::r/cl]}
   [0xd2 0x4d 0x00] {::i/tag ::i/ror, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x4d 0xf0] {::i/tag ::i/ror, ::i/args [[::r/di -16] ::r/cl]}
   [0xd2 0x4d 0x0f] {::i/tag ::i/ror, ::i/args [[::r/di 15] ::r/cl]}
   [0xd2 0x4e 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x4e 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd2 0x4e 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd2 0x4f 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x4f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd2 0x4f 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd2 0x50 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x50 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd2 0x50 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd2 0x51 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x51 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd2 0x51 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd2 0x52 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x52 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd2 0x52 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd2 0x53 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x53 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd2 0x53 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd2 0x54 0x00] {::i/tag ::i/rcl, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x54 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/si -16] ::r/cl]}
   [0xd2 0x54 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/si 15] ::r/cl]}
   [0xd2 0x55 0x00] {::i/tag ::i/rcl, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x55 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/di -16] ::r/cl]}
   [0xd2 0x55 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/di 15] ::r/cl]}
   [0xd2 0x56 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x56 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd2 0x56 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd2 0x57 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x57 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd2 0x57 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd2 0x58 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x58 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd2 0x58 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd2 0x59 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x59 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd2 0x59 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd2 0x5a 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x5a 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd2 0x5a 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd2 0x5b 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x5b 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd2 0x5b 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd2 0x5c 0x00] {::i/tag ::i/rcr, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x5c 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/si -16] ::r/cl]}
   [0xd2 0x5c 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/si 15] ::r/cl]}
   [0xd2 0x5d 0x00] {::i/tag ::i/rcr, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x5d 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/di -16] ::r/cl]}
   [0xd2 0x5d 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/di 15] ::r/cl]}
   [0xd2 0x5e 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x5e 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd2 0x5e 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd2 0x5f 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x5f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd2 0x5f 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd2 0x60 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x60 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd2 0x60 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd2 0x61 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x61 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd2 0x61 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd2 0x62 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x62 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd2 0x62 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd2 0x63 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x63 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd2 0x63 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd2 0x64 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x64 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -16] ::r/cl]}
   [0xd2 0x64 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 15] ::r/cl]}
   [0xd2 0x65 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x65 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -16] ::r/cl]}
   [0xd2 0x65 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 15] ::r/cl]}
   [0xd2 0x66 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x66 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd2 0x66 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd2 0x67 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x67 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd2 0x67 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd2 0x68 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x68 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd2 0x68 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd2 0x69 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x69 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd2 0x69 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd2 0x6a 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x6a 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd2 0x6a 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd2 0x6b 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x6b 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd2 0x6b 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd2 0x6c 0x00] {::i/tag ::i/shr, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x6c 0xf0] {::i/tag ::i/shr, ::i/args [[::r/si -16] ::r/cl]}
   [0xd2 0x6c 0x0f] {::i/tag ::i/shr, ::i/args [[::r/si 15] ::r/cl]}
   [0xd2 0x6d 0x00] {::i/tag ::i/shr, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x6d 0xf0] {::i/tag ::i/shr, ::i/args [[::r/di -16] ::r/cl]}
   [0xd2 0x6d 0x0f] {::i/tag ::i/shr, ::i/args [[::r/di 15] ::r/cl]}
   [0xd2 0x6e 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x6e 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd2 0x6e 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd2 0x6f 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x6f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd2 0x6f 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd2 0x70 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x70 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd2 0x70 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd2 0x71 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x71 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd2 0x71 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd2 0x72 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x72 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd2 0x72 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd2 0x73 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x73 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd2 0x73 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd2 0x74 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x74 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -16] ::r/cl]}
   [0xd2 0x74 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 15] ::r/cl]}
   [0xd2 0x75 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x75 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -16] ::r/cl]}
   [0xd2 0x75 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 15] ::r/cl]}
   [0xd2 0x76 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x76 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd2 0x76 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd2 0x77 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x77 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd2 0x77 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd2 0x78 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x78 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd2 0x78 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd2 0x79 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x79 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd2 0x79 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd2 0x7a 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x7a 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd2 0x7a 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd2 0x7b 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x7b 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd2 0x7b 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd2 0x7c 0x00] {::i/tag ::i/sar, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x7c 0xf0] {::i/tag ::i/sar, ::i/args [[::r/si -16] ::r/cl]}
   [0xd2 0x7c 0x0f] {::i/tag ::i/sar, ::i/args [[::r/si 15] ::r/cl]}
   [0xd2 0x7d 0x00] {::i/tag ::i/sar, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x7d 0xf0] {::i/tag ::i/sar, ::i/args [[::r/di -16] ::r/cl]}
   [0xd2 0x7d 0x0f] {::i/tag ::i/sar, ::i/args [[::r/di 15] ::r/cl]}
   [0xd2 0x7e 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x7e 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd2 0x7e 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd2 0x7f 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x7f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd2 0x7f 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd2 0x80 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x80 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd2 0x80 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd2 0x81 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x81 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd2 0x81 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd2 0x82 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x82 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd2 0x82 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd2 0x83 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x83 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd2 0x83 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd2 0x84 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x84 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd2 0x84 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd2 0x85 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x85 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd2 0x85 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd2 0x86 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x86 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd2 0x86 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd2 0x87 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x87 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd2 0x87 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd2 0x88 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x88 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd2 0x88 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd2 0x89 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x89 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd2 0x89 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd2 0x8a 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x8a 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd2 0x8a 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd2 0x8b 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x8b 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd2 0x8b 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd2 0x8c 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x8c 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd2 0x8c 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd2 0x8d 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x8d 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd2 0x8d 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd2 0x8e 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x8e 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd2 0x8e 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd2 0x8f 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x8f 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd2 0x8f 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd2 0x90 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x90 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd2 0x90 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd2 0x91 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x91 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd2 0x91 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd2 0x92 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x92 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd2 0x92 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd2 0x93 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x93 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd2 0x93 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd2 0x94 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x94 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd2 0x94 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd2 0x95 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x95 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd2 0x95 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd2 0x96 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x96 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd2 0x96 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd2 0x97 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x97 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd2 0x97 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd2 0x98 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0x98 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd2 0x98 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd2 0x99 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0x99 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd2 0x99 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd2 0x9a 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0x9a 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd2 0x9a 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd2 0x9b 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0x9b 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd2 0x9b 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd2 0x9c 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0x9c 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd2 0x9c 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd2 0x9d 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0x9d 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd2 0x9d 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd2 0x9e 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0x9e 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd2 0x9e 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd2 0x9f 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0x9f 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd2 0x9f 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd2 0xa0 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0xa0 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd2 0xa0 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd2 0xa1 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0xa1 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd2 0xa1 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd2 0xa2 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0xa2 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd2 0xa2 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd2 0xa3 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0xa3 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd2 0xa3 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd2 0xa4 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0xa4 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd2 0xa4 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd2 0xa5 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0xa5 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd2 0xa5 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd2 0xa6 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0xa6 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd2 0xa6 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd2 0xa7 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0xa7 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd2 0xa7 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd2 0xa8 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0xa8 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd2 0xa8 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd2 0xa9 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0xa9 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd2 0xa9 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd2 0xaa 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0xaa 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd2 0xaa 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd2 0xab 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0xab 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd2 0xab 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd2 0xac 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0xac 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd2 0xac 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd2 0xad 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0xad 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd2 0xad 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd2 0xae 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0xae 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd2 0xae 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd2 0xaf 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0xaf 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd2 0xaf 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd2 0xb0 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0xb0 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd2 0xb0 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd2 0xb1 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0xb1 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd2 0xb1 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd2 0xb2 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0xb2 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd2 0xb2 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd2 0xb3 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0xb3 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd2 0xb3 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd2 0xb4 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0xb4 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd2 0xb4 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd2 0xb5 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0xb5 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd2 0xb5 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd2 0xb6 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0xb6 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd2 0xb6 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd2 0xb7 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0xb7 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd2 0xb7 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd2 0xb8 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd2 0xb8 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd2 0xb8 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd2 0xb9 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd2 0xb9 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd2 0xb9 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd2 0xba 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd2 0xba 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd2 0xba 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd2 0xbb 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd2 0xbb 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd2 0xbb 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd2 0xbc 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/si] ::r/cl]}
   [0xd2 0xbc 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd2 0xbc 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd2 0xbd 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/di] ::r/cl]}
   [0xd2 0xbd 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd2 0xbd 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd2 0xbe 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp] ::r/cl]}
   [0xd2 0xbe 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd2 0xbe 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd2 0xbf 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx] ::r/cl]}
   [0xd2 0xbf 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd2 0xbf 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd2 0xc0] {::i/tag ::i/rol, ::i/args [::r/al ::r/cl]}
   [0xd2 0xc1] {::i/tag ::i/rol, ::i/args [::r/cl ::r/cl]}
   [0xd2 0xc2] {::i/tag ::i/rol, ::i/args [::r/dl ::r/cl]}
   [0xd2 0xc3] {::i/tag ::i/rol, ::i/args [::r/bl ::r/cl]}
   [0xd2 0xc4] {::i/tag ::i/rol, ::i/args [::r/ah ::r/cl]}
   [0xd2 0xc5] {::i/tag ::i/rol, ::i/args [::r/ch ::r/cl]}
   [0xd2 0xc6] {::i/tag ::i/rol, ::i/args [::r/dh ::r/cl]}
   [0xd2 0xc7] {::i/tag ::i/rol, ::i/args [::r/bh ::r/cl]}
   [0xd2 0xc8] {::i/tag ::i/ror, ::i/args [::r/al ::r/cl]}
   [0xd2 0xc9] {::i/tag ::i/ror, ::i/args [::r/cl ::r/cl]}
   [0xd2 0xca] {::i/tag ::i/ror, ::i/args [::r/dl ::r/cl]}
   [0xd2 0xcb] {::i/tag ::i/ror, ::i/args [::r/bl ::r/cl]}
   [0xd2 0xcc] {::i/tag ::i/ror, ::i/args [::r/ah ::r/cl]}
   [0xd2 0xcd] {::i/tag ::i/ror, ::i/args [::r/ch ::r/cl]}
   [0xd2 0xce] {::i/tag ::i/ror, ::i/args [::r/dh ::r/cl]}
   [0xd2 0xcf] {::i/tag ::i/ror, ::i/args [::r/bh ::r/cl]}
   [0xd2 0xd0] {::i/tag ::i/rcl, ::i/args [::r/al ::r/cl]}
   [0xd2 0xd1] {::i/tag ::i/rcl, ::i/args [::r/cl ::r/cl]}
   [0xd2 0xd2] {::i/tag ::i/rcl, ::i/args [::r/dl ::r/cl]}
   [0xd2 0xd3] {::i/tag ::i/rcl, ::i/args [::r/bl ::r/cl]}
   [0xd2 0xd4] {::i/tag ::i/rcl, ::i/args [::r/ah ::r/cl]}
   [0xd2 0xd5] {::i/tag ::i/rcl, ::i/args [::r/ch ::r/cl]}
   [0xd2 0xd6] {::i/tag ::i/rcl, ::i/args [::r/dh ::r/cl]}
   [0xd2 0xd7] {::i/tag ::i/rcl, ::i/args [::r/bh ::r/cl]}
   [0xd2 0xd8] {::i/tag ::i/rcr, ::i/args [::r/al ::r/cl]}
   [0xd2 0xd9] {::i/tag ::i/rcr, ::i/args [::r/cl ::r/cl]}
   [0xd2 0xda] {::i/tag ::i/rcr, ::i/args [::r/dl ::r/cl]}
   [0xd2 0xdb] {::i/tag ::i/rcr, ::i/args [::r/bl ::r/cl]}
   [0xd2 0xdc] {::i/tag ::i/rcr, ::i/args [::r/ah ::r/cl]}
   [0xd2 0xdd] {::i/tag ::i/rcr, ::i/args [::r/ch ::r/cl]}
   [0xd2 0xde] {::i/tag ::i/rcr, ::i/args [::r/dh ::r/cl]}
   [0xd2 0xdf] {::i/tag ::i/rcr, ::i/args [::r/bh ::r/cl]}
   [0xd2 0xe0] {::i/tag ::i/shl, ::i/args [::r/al ::r/cl]}
   [0xd2 0xe1] {::i/tag ::i/shl, ::i/args [::r/cl ::r/cl]}
   [0xd2 0xe2] {::i/tag ::i/shl, ::i/args [::r/dl ::r/cl]}
   [0xd2 0xe3] {::i/tag ::i/shl, ::i/args [::r/bl ::r/cl]}
   [0xd2 0xe4] {::i/tag ::i/shl, ::i/args [::r/ah ::r/cl]}
   [0xd2 0xe5] {::i/tag ::i/shl, ::i/args [::r/ch ::r/cl]}
   [0xd2 0xe6] {::i/tag ::i/shl, ::i/args [::r/dh ::r/cl]}
   [0xd2 0xe7] {::i/tag ::i/shl, ::i/args [::r/bh ::r/cl]}
   [0xd2 0xe8] {::i/tag ::i/shr, ::i/args [::r/al ::r/cl]}
   [0xd2 0xe9] {::i/tag ::i/shr, ::i/args [::r/cl ::r/cl]}
   [0xd2 0xea] {::i/tag ::i/shr, ::i/args [::r/dl ::r/cl]}
   [0xd2 0xeb] {::i/tag ::i/shr, ::i/args [::r/bl ::r/cl]}
   [0xd2 0xec] {::i/tag ::i/shr, ::i/args [::r/ah ::r/cl]}
   [0xd2 0xed] {::i/tag ::i/shr, ::i/args [::r/ch ::r/cl]}
   [0xd2 0xee] {::i/tag ::i/shr, ::i/args [::r/dh ::r/cl]}
   [0xd2 0xef] {::i/tag ::i/shr, ::i/args [::r/bh ::r/cl]}
   [0xd2 0xf0] {::i/tag ::i/shl, ::i/args [::r/al ::r/cl]}
   [0xd2 0xf1] {::i/tag ::i/shl, ::i/args [::r/cl ::r/cl]}
   [0xd2 0xf2] {::i/tag ::i/shl, ::i/args [::r/dl ::r/cl]}
   [0xd2 0xf3] {::i/tag ::i/shl, ::i/args [::r/bl ::r/cl]}
   [0xd2 0xf4] {::i/tag ::i/shl, ::i/args [::r/ah ::r/cl]}
   [0xd2 0xf5] {::i/tag ::i/shl, ::i/args [::r/ch ::r/cl]}
   [0xd2 0xf6] {::i/tag ::i/shl, ::i/args [::r/dh ::r/cl]}
   [0xd2 0xf7] {::i/tag ::i/shl, ::i/args [::r/bh ::r/cl]}
   [0xd2 0xf8] {::i/tag ::i/sar, ::i/args [::r/al ::r/cl]}
   [0xd2 0xf9] {::i/tag ::i/sar, ::i/args [::r/cl ::r/cl]}
   [0xd2 0xfa] {::i/tag ::i/sar, ::i/args [::r/dl ::r/cl]}
   [0xd2 0xfb] {::i/tag ::i/sar, ::i/args [::r/bl ::r/cl]}
   [0xd2 0xfc] {::i/tag ::i/sar, ::i/args [::r/ah ::r/cl]}
   [0xd2 0xfd] {::i/tag ::i/sar, ::i/args [::r/ch ::r/cl]}
   [0xd2 0xfe] {::i/tag ::i/sar, ::i/args [::r/dh ::r/cl]}
   [0xd2 0xff] {::i/tag ::i/sar, ::i/args [::r/bh ::r/cl]}})


(def decode-examples-0xd3
  {[0xd3 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x01] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x02] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x03] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x04] {::i/tag ::i/rol, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x05] {::i/tag ::i/rol, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x06 0x12 0xf4] {::i/tag ::i/rol, ::i/args [[0xf412] ::r/cl]}
   [0xd3 0x06 0xf4 0x12] {::i/tag ::i/rol, ::i/args [[0x12f4] ::r/cl]}
   [0xd3 0x07] {::i/tag ::i/rol, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x08] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x09] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x0a] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x0b] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x0c] {::i/tag ::i/ror, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x0d] {::i/tag ::i/ror, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x0e 0x12 0xf4] {::i/tag ::i/ror, ::i/args [[0xf412] ::r/cl]}
   [0xd3 0x0e 0xf4 0x12] {::i/tag ::i/ror, ::i/args [[0x12f4] ::r/cl]}
   [0xd3 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x10] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x11] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x12] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x13] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x14] {::i/tag ::i/rcl, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x15] {::i/tag ::i/rcl, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x16 0x12 0xf4] {::i/tag ::i/rcl, ::i/args [[0xf412] ::r/cl]}
   [0xd3 0x16 0xf4 0x12] {::i/tag ::i/rcl, ::i/args [[0x12f4] ::r/cl]}
   [0xd3 0x17] {::i/tag ::i/rcl, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x18] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x19] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x1a] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x1b] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x1c] {::i/tag ::i/rcr, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x1d] {::i/tag ::i/rcr, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x1e 0x12 0xf4] {::i/tag ::i/rcr, ::i/args [[0xf412] ::r/cl]}
   [0xd3 0x1e 0xf4 0x12] {::i/tag ::i/rcr, ::i/args [[0x12f4] ::r/cl]}
   [0xd3 0x1f] {::i/tag ::i/rcr, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x20] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x21] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x22] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x23] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x24] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x25] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x26 0x12 0xf4] {::i/tag ::i/shl, ::i/args [[0xf412] ::r/cl]}
   [0xd3 0x26 0xf4 0x12] {::i/tag ::i/shl, ::i/args [[0x12f4] ::r/cl]}
   [0xd3 0x27] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x28] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x29] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x2a] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x2b] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x2c] {::i/tag ::i/shr, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x2d] {::i/tag ::i/shr, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x2e 0x12 0xf4] {::i/tag ::i/shr, ::i/args [[0xf412] ::r/cl]}
   [0xd3 0x2e 0xf4 0x12] {::i/tag ::i/shr, ::i/args [[0x12f4] ::r/cl]}
   [0xd3 0x2f] {::i/tag ::i/shr, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x30] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x31] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x32] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x33] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x34] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x35] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x36 0x12 0xf4] {::i/tag ::i/shl, ::i/args [[0xf412] ::r/cl]}
   [0xd3 0x36 0xf4 0x12] {::i/tag ::i/shl, ::i/args [[0x12f4] ::r/cl]}
   [0xd3 0x37] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x38] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x39] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x3a] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x3b] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x3c] {::i/tag ::i/sar, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x3d] {::i/tag ::i/sar, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x3e 0x12 0xf4] {::i/tag ::i/sar, ::i/args [[0xf412] ::r/cl]}
   [0xd3 0x3e 0xf4 0x12] {::i/tag ::i/sar, ::i/args [[0x12f4] ::r/cl]}
   [0xd3 0x3f] {::i/tag ::i/sar, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x40 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x40 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd3 0x40 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd3 0x41 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x41 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd3 0x41 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd3 0x42 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x42 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd3 0x42 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd3 0x43 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x43 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd3 0x43 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd3 0x44 0x00] {::i/tag ::i/rol, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x44 0xf0] {::i/tag ::i/rol, ::i/args [[::r/si -16] ::r/cl]}
   [0xd3 0x44 0x0f] {::i/tag ::i/rol, ::i/args [[::r/si 15] ::r/cl]}
   [0xd3 0x45 0x00] {::i/tag ::i/rol, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x45 0xf0] {::i/tag ::i/rol, ::i/args [[::r/di -16] ::r/cl]}
   [0xd3 0x45 0x0f] {::i/tag ::i/rol, ::i/args [[::r/di 15] ::r/cl]}
   [0xd3 0x46 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x46 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd3 0x46 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd3 0x47 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x47 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd3 0x47 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd3 0x48 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x48 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd3 0x48 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd3 0x49 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x49 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd3 0x49 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd3 0x4a 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x4a 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd3 0x4a 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd3 0x4b 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x4b 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd3 0x4b 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd3 0x4c 0x00] {::i/tag ::i/ror, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x4c 0xf0] {::i/tag ::i/ror, ::i/args [[::r/si -16] ::r/cl]}
   [0xd3 0x4c 0x0f] {::i/tag ::i/ror, ::i/args [[::r/si 15] ::r/cl]}
   [0xd3 0x4d 0x00] {::i/tag ::i/ror, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x4d 0xf0] {::i/tag ::i/ror, ::i/args [[::r/di -16] ::r/cl]}
   [0xd3 0x4d 0x0f] {::i/tag ::i/ror, ::i/args [[::r/di 15] ::r/cl]}
   [0xd3 0x4e 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x4e 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd3 0x4e 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd3 0x4f 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x4f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd3 0x4f 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd3 0x50 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x50 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd3 0x50 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd3 0x51 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x51 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd3 0x51 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd3 0x52 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x52 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd3 0x52 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd3 0x53 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x53 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd3 0x53 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd3 0x54 0x00] {::i/tag ::i/rcl, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x54 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/si -16] ::r/cl]}
   [0xd3 0x54 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/si 15] ::r/cl]}
   [0xd3 0x55 0x00] {::i/tag ::i/rcl, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x55 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/di -16] ::r/cl]}
   [0xd3 0x55 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/di 15] ::r/cl]}
   [0xd3 0x56 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x56 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd3 0x56 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd3 0x57 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x57 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd3 0x57 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd3 0x58 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x58 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd3 0x58 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd3 0x59 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x59 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd3 0x59 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd3 0x5a 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x5a 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd3 0x5a 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd3 0x5b 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x5b 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd3 0x5b 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd3 0x5c 0x00] {::i/tag ::i/rcr, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x5c 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/si -16] ::r/cl]}
   [0xd3 0x5c 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/si 15] ::r/cl]}
   [0xd3 0x5d 0x00] {::i/tag ::i/rcr, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x5d 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/di -16] ::r/cl]}
   [0xd3 0x5d 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/di 15] ::r/cl]}
   [0xd3 0x5e 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x5e 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd3 0x5e 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd3 0x5f 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x5f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd3 0x5f 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd3 0x60 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x60 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd3 0x60 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd3 0x61 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x61 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd3 0x61 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd3 0x62 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x62 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd3 0x62 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd3 0x63 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x63 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd3 0x63 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd3 0x64 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x64 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -16] ::r/cl]}
   [0xd3 0x64 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 15] ::r/cl]}
   [0xd3 0x65 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x65 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -16] ::r/cl]}
   [0xd3 0x65 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 15] ::r/cl]}
   [0xd3 0x66 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x66 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd3 0x66 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd3 0x67 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x67 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd3 0x67 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd3 0x68 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x68 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd3 0x68 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd3 0x69 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x69 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd3 0x69 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd3 0x6a 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x6a 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd3 0x6a 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd3 0x6b 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x6b 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd3 0x6b 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd3 0x6c 0x00] {::i/tag ::i/shr, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x6c 0xf0] {::i/tag ::i/shr, ::i/args [[::r/si -16] ::r/cl]}
   [0xd3 0x6c 0x0f] {::i/tag ::i/shr, ::i/args [[::r/si 15] ::r/cl]}
   [0xd3 0x6d 0x00] {::i/tag ::i/shr, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x6d 0xf0] {::i/tag ::i/shr, ::i/args [[::r/di -16] ::r/cl]}
   [0xd3 0x6d 0x0f] {::i/tag ::i/shr, ::i/args [[::r/di 15] ::r/cl]}
   [0xd3 0x6e 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x6e 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd3 0x6e 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd3 0x6f 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x6f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd3 0x6f 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd3 0x70 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x70 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd3 0x70 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd3 0x71 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x71 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd3 0x71 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd3 0x72 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x72 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd3 0x72 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd3 0x73 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x73 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd3 0x73 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd3 0x74 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x74 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -16] ::r/cl]}
   [0xd3 0x74 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 15] ::r/cl]}
   [0xd3 0x75 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x75 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -16] ::r/cl]}
   [0xd3 0x75 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 15] ::r/cl]}
   [0xd3 0x76 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x76 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd3 0x76 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd3 0x77 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x77 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd3 0x77 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd3 0x78 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x78 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si -16] ::r/cl]}
   [0xd3 0x78 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si 15] ::r/cl]}
   [0xd3 0x79 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x79 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di -16] ::r/cl]}
   [0xd3 0x79 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di 15] ::r/cl]}
   [0xd3 0x7a 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x7a 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si -16] ::r/cl]}
   [0xd3 0x7a 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si 15] ::r/cl]}
   [0xd3 0x7b 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x7b 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di -16] ::r/cl]}
   [0xd3 0x7b 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di 15] ::r/cl]}
   [0xd3 0x7c 0x00] {::i/tag ::i/sar, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x7c 0xf0] {::i/tag ::i/sar, ::i/args [[::r/si -16] ::r/cl]}
   [0xd3 0x7c 0x0f] {::i/tag ::i/sar, ::i/args [[::r/si 15] ::r/cl]}
   [0xd3 0x7d 0x00] {::i/tag ::i/sar, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x7d 0xf0] {::i/tag ::i/sar, ::i/args [[::r/di -16] ::r/cl]}
   [0xd3 0x7d 0x0f] {::i/tag ::i/sar, ::i/args [[::r/di 15] ::r/cl]}
   [0xd3 0x7e 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x7e 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp -16] ::r/cl]}
   [0xd3 0x7e 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp 15] ::r/cl]}
   [0xd3 0x7f 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x7f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx -16] ::r/cl]}
   [0xd3 0x7f 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx 15] ::r/cl]}
   [0xd3 0x80 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x80 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd3 0x80 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd3 0x81 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x81 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd3 0x81 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd3 0x82 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x82 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd3 0x82 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd3 0x83 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x83 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd3 0x83 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd3 0x84 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x84 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd3 0x84 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd3 0x85 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x85 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd3 0x85 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd3 0x86 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x86 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd3 0x86 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd3 0x87 0x00 0x00] {::i/tag ::i/rol, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x87 0xf0 0x0f] {::i/tag ::i/rol, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd3 0x87 0x0f 0xf0] {::i/tag ::i/rol, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd3 0x88 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x88 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd3 0x88 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd3 0x89 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x89 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd3 0x89 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd3 0x8a 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x8a 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd3 0x8a 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd3 0x8b 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x8b 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd3 0x8b 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd3 0x8c 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x8c 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd3 0x8c 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd3 0x8d 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x8d 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd3 0x8d 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd3 0x8e 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x8e 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd3 0x8e 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd3 0x8f 0x00 0x00] {::i/tag ::i/ror, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x8f 0xf0 0x0f] {::i/tag ::i/ror, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd3 0x8f 0x0f 0xf0] {::i/tag ::i/ror, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd3 0x90 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x90 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd3 0x90 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd3 0x91 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x91 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd3 0x91 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd3 0x92 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x92 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd3 0x92 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd3 0x93 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x93 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd3 0x93 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd3 0x94 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x94 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd3 0x94 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd3 0x95 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x95 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd3 0x95 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd3 0x96 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x96 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd3 0x96 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd3 0x97 0x00 0x00] {::i/tag ::i/rcl, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x97 0xf0 0x0f] {::i/tag ::i/rcl, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd3 0x97 0x0f 0xf0] {::i/tag ::i/rcl, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd3 0x98 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0x98 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd3 0x98 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd3 0x99 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0x99 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd3 0x99 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd3 0x9a 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0x9a 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd3 0x9a 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd3 0x9b 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0x9b 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd3 0x9b 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd3 0x9c 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0x9c 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd3 0x9c 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd3 0x9d 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0x9d 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd3 0x9d 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd3 0x9e 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0x9e 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd3 0x9e 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd3 0x9f 0x00 0x00] {::i/tag ::i/rcr, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0x9f 0xf0 0x0f] {::i/tag ::i/rcr, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd3 0x9f 0x0f 0xf0] {::i/tag ::i/rcr, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd3 0xa0 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0xa0 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd3 0xa0 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd3 0xa1 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0xa1 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd3 0xa1 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd3 0xa2 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0xa2 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd3 0xa2 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd3 0xa3 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0xa3 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd3 0xa3 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd3 0xa4 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0xa4 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd3 0xa4 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd3 0xa5 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0xa5 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd3 0xa5 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd3 0xa6 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0xa6 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd3 0xa6 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd3 0xa7 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0xa7 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd3 0xa7 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd3 0xa8 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0xa8 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd3 0xa8 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd3 0xa9 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0xa9 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd3 0xa9 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd3 0xaa 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0xaa 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd3 0xaa 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd3 0xab 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0xab 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd3 0xab 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd3 0xac 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0xac 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd3 0xac 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd3 0xad 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0xad 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd3 0xad 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd3 0xae 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0xae 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd3 0xae 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd3 0xaf 0x00 0x00] {::i/tag ::i/shr, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0xaf 0xf0 0x0f] {::i/tag ::i/shr, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd3 0xaf 0x0f 0xf0] {::i/tag ::i/shr, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd3 0xb0 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0xb0 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd3 0xb0 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd3 0xb1 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0xb1 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd3 0xb1 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd3 0xb2 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0xb2 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd3 0xb2 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd3 0xb3 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0xb3 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd3 0xb3 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd3 0xb4 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0xb4 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd3 0xb4 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd3 0xb5 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0xb5 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd3 0xb5 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd3 0xb6 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0xb6 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd3 0xb6 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd3 0xb7 0x00 0x00] {::i/tag ::i/shl, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0xb7 0xf0 0x0f] {::i/tag ::i/shl, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd3 0xb7 0x0f 0xf0] {::i/tag ::i/shl, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd3 0xb8 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si] ::r/cl]}
   [0xd3 0xb8 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si 4080] ::r/cl]}
   [0xd3 0xb8 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/si -4081] ::r/cl]}
   [0xd3 0xb9 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di] ::r/cl]}
   [0xd3 0xb9 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di 4080] ::r/cl]}
   [0xd3 0xb9 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx ::r/di -4081] ::r/cl]}
   [0xd3 0xba 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si] ::r/cl]}
   [0xd3 0xba 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si 4080] ::r/cl]}
   [0xd3 0xba 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/si -4081] ::r/cl]}
   [0xd3 0xbb 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di] ::r/cl]}
   [0xd3 0xbb 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di 4080] ::r/cl]}
   [0xd3 0xbb 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp ::r/di -4081] ::r/cl]}
   [0xd3 0xbc 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/si] ::r/cl]}
   [0xd3 0xbc 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/si 4080] ::r/cl]}
   [0xd3 0xbc 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/si -4081] ::r/cl]}
   [0xd3 0xbd 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/di] ::r/cl]}
   [0xd3 0xbd 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/di 4080] ::r/cl]}
   [0xd3 0xbd 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/di -4081] ::r/cl]}
   [0xd3 0xbe 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bp] ::r/cl]}
   [0xd3 0xbe 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bp 4080] ::r/cl]}
   [0xd3 0xbe 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bp -4081] ::r/cl]}
   [0xd3 0xbf 0x00 0x00] {::i/tag ::i/sar, ::i/args [[::r/bx] ::r/cl]}
   [0xd3 0xbf 0xf0 0x0f] {::i/tag ::i/sar, ::i/args [[::r/bx 4080] ::r/cl]}
   [0xd3 0xbf 0x0f 0xf0] {::i/tag ::i/sar, ::i/args [[::r/bx -4081] ::r/cl]}
   [0xd3 0xc0] {::i/tag ::i/rol, ::i/args [::r/ax ::r/cl]}
   [0xd3 0xc1] {::i/tag ::i/rol, ::i/args [::r/cx ::r/cl]}
   [0xd3 0xc2] {::i/tag ::i/rol, ::i/args [::r/dx ::r/cl]}
   [0xd3 0xc3] {::i/tag ::i/rol, ::i/args [::r/bx ::r/cl]}
   [0xd3 0xc4] {::i/tag ::i/rol, ::i/args [::r/sp ::r/cl]}
   [0xd3 0xc5] {::i/tag ::i/rol, ::i/args [::r/bp ::r/cl]}
   [0xd3 0xc6] {::i/tag ::i/rol, ::i/args [::r/si ::r/cl]}
   [0xd3 0xc7] {::i/tag ::i/rol, ::i/args [::r/di ::r/cl]}
   [0xd3 0xc8] {::i/tag ::i/ror, ::i/args [::r/ax ::r/cl]}
   [0xd3 0xc9] {::i/tag ::i/ror, ::i/args [::r/cx ::r/cl]}
   [0xd3 0xca] {::i/tag ::i/ror, ::i/args [::r/dx ::r/cl]}
   [0xd3 0xcb] {::i/tag ::i/ror, ::i/args [::r/bx ::r/cl]}
   [0xd3 0xcc] {::i/tag ::i/ror, ::i/args [::r/sp ::r/cl]}
   [0xd3 0xcd] {::i/tag ::i/ror, ::i/args [::r/bp ::r/cl]}
   [0xd3 0xce] {::i/tag ::i/ror, ::i/args [::r/si ::r/cl]}
   [0xd3 0xcf] {::i/tag ::i/ror, ::i/args [::r/di ::r/cl]}
   [0xd3 0xd0] {::i/tag ::i/rcl, ::i/args [::r/ax ::r/cl]}
   [0xd3 0xd1] {::i/tag ::i/rcl, ::i/args [::r/cx ::r/cl]}
   [0xd3 0xd2] {::i/tag ::i/rcl, ::i/args [::r/dx ::r/cl]}
   [0xd3 0xd3] {::i/tag ::i/rcl, ::i/args [::r/bx ::r/cl]}
   [0xd3 0xd4] {::i/tag ::i/rcl, ::i/args [::r/sp ::r/cl]}
   [0xd3 0xd5] {::i/tag ::i/rcl, ::i/args [::r/bp ::r/cl]}
   [0xd3 0xd6] {::i/tag ::i/rcl, ::i/args [::r/si ::r/cl]}
   [0xd3 0xd7] {::i/tag ::i/rcl, ::i/args [::r/di ::r/cl]}
   [0xd3 0xd8] {::i/tag ::i/rcr, ::i/args [::r/ax ::r/cl]}
   [0xd3 0xd9] {::i/tag ::i/rcr, ::i/args [::r/cx ::r/cl]}
   [0xd3 0xda] {::i/tag ::i/rcr, ::i/args [::r/dx ::r/cl]}
   [0xd3 0xdb] {::i/tag ::i/rcr, ::i/args [::r/bx ::r/cl]}
   [0xd3 0xdc] {::i/tag ::i/rcr, ::i/args [::r/sp ::r/cl]}
   [0xd3 0xdd] {::i/tag ::i/rcr, ::i/args [::r/bp ::r/cl]}
   [0xd3 0xde] {::i/tag ::i/rcr, ::i/args [::r/si ::r/cl]}
   [0xd3 0xdf] {::i/tag ::i/rcr, ::i/args [::r/di ::r/cl]}
   [0xd3 0xe0] {::i/tag ::i/shl, ::i/args [::r/ax ::r/cl]}
   [0xd3 0xe1] {::i/tag ::i/shl, ::i/args [::r/cx ::r/cl]}
   [0xd3 0xe2] {::i/tag ::i/shl, ::i/args [::r/dx ::r/cl]}
   [0xd3 0xe3] {::i/tag ::i/shl, ::i/args [::r/bx ::r/cl]}
   [0xd3 0xe4] {::i/tag ::i/shl, ::i/args [::r/sp ::r/cl]}
   [0xd3 0xe5] {::i/tag ::i/shl, ::i/args [::r/bp ::r/cl]}
   [0xd3 0xe6] {::i/tag ::i/shl, ::i/args [::r/si ::r/cl]}
   [0xd3 0xe7] {::i/tag ::i/shl, ::i/args [::r/di ::r/cl]}
   [0xd3 0xe8] {::i/tag ::i/shr, ::i/args [::r/ax ::r/cl]}
   [0xd3 0xe9] {::i/tag ::i/shr, ::i/args [::r/cx ::r/cl]}
   [0xd3 0xea] {::i/tag ::i/shr, ::i/args [::r/dx ::r/cl]}
   [0xd3 0xeb] {::i/tag ::i/shr, ::i/args [::r/bx ::r/cl]}
   [0xd3 0xec] {::i/tag ::i/shr, ::i/args [::r/sp ::r/cl]}
   [0xd3 0xed] {::i/tag ::i/shr, ::i/args [::r/bp ::r/cl]}
   [0xd3 0xee] {::i/tag ::i/shr, ::i/args [::r/si ::r/cl]}
   [0xd3 0xef] {::i/tag ::i/shr, ::i/args [::r/di ::r/cl]}
   [0xd3 0xf0] {::i/tag ::i/shl, ::i/args [::r/ax ::r/cl]}
   [0xd3 0xf1] {::i/tag ::i/shl, ::i/args [::r/cx ::r/cl]}
   [0xd3 0xf2] {::i/tag ::i/shl, ::i/args [::r/dx ::r/cl]}
   [0xd3 0xf3] {::i/tag ::i/shl, ::i/args [::r/bx ::r/cl]}
   [0xd3 0xf4] {::i/tag ::i/shl, ::i/args [::r/sp ::r/cl]}
   [0xd3 0xf5] {::i/tag ::i/shl, ::i/args [::r/bp ::r/cl]}
   [0xd3 0xf6] {::i/tag ::i/shl, ::i/args [::r/si ::r/cl]}
   [0xd3 0xf7] {::i/tag ::i/shl, ::i/args [::r/di ::r/cl]}
   [0xd3 0xf8] {::i/tag ::i/sar, ::i/args [::r/ax ::r/cl]}
   [0xd3 0xf9] {::i/tag ::i/sar, ::i/args [::r/cx ::r/cl]}
   [0xd3 0xfa] {::i/tag ::i/sar, ::i/args [::r/dx ::r/cl]}
   [0xd3 0xfb] {::i/tag ::i/sar, ::i/args [::r/bx ::r/cl]}
   [0xd3 0xfc] {::i/tag ::i/sar, ::i/args [::r/sp ::r/cl]}
   [0xd3 0xfd] {::i/tag ::i/sar, ::i/args [::r/bp ::r/cl]}
   [0xd3 0xfe] {::i/tag ::i/sar, ::i/args [::r/si ::r/cl]}
   [0xd3 0xff] {::i/tag ::i/sar, ::i/args [::r/di ::r/cl]}})
