(ns oumu.cpu.instructions-test.examples.lea
    (:require [oumu.cpu.instructions :as i]
              [oumu.cpu.registers :as r]))


(def decode-lea-examples
  {[0x8d 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x8d 0x01] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x8d 0x02] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x8d 0x03] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x8d 0x04] {::i/tag ::i/lea, ::i/args [::r/ax [::r/si]]}
   [0x8d 0x05] {::i/tag ::i/lea, ::i/args [::r/ax [::r/di]]}
   [0x8d 0x06 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [0x0ff0]]}
   [0x8d 0x06 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [0xf00f]]}
   [0x8d 0x07] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx]]}
   [0x8d 0x08] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x8d 0x09] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x8d 0x0a] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x8d 0x0b] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x8d 0x0c] {::i/tag ::i/lea, ::i/args [::r/cx [::r/si]]}
   [0x8d 0x0d] {::i/tag ::i/lea, ::i/args [::r/cx [::r/di]]}
   [0x8d 0x0e 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [0x0ff0]]}
   [0x8d 0x0e 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [0xf00f]]}
   [0x8d 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx]]}
   [0x8d 0x10] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x8d 0x11] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x8d 0x12] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x8d 0x13] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x8d 0x14] {::i/tag ::i/lea, ::i/args [::r/dx [::r/si]]}
   [0x8d 0x15] {::i/tag ::i/lea, ::i/args [::r/dx [::r/di]]}
   [0x8d 0x16 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [0x0ff0]]}
   [0x8d 0x16 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [0xf00f]]}
   [0x8d 0x17] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx]]}
   [0x8d 0x18] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x8d 0x19] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x8d 0x1a] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x8d 0x1b] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x8d 0x1c] {::i/tag ::i/lea, ::i/args [::r/bx [::r/si]]}
   [0x8d 0x1d] {::i/tag ::i/lea, ::i/args [::r/bx [::r/di]]}
   [0x8d 0x1e 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [0x0ff0]]}
   [0x8d 0x1e 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [0xf00f]]}
   [0x8d 0x1f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx]]}
   [0x8d 0x20] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x8d 0x21] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x8d 0x22] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x8d 0x23] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x8d 0x24] {::i/tag ::i/lea, ::i/args [::r/sp [::r/si]]}
   [0x8d 0x25] {::i/tag ::i/lea, ::i/args [::r/sp [::r/di]]}
   [0x8d 0x26 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [0x0ff0]]}
   [0x8d 0x26 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [0xf00f]]}
   [0x8d 0x27] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx]]}
   [0x8d 0x28] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x8d 0x29] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x8d 0x2a] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x8d 0x2b] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x8d 0x2c] {::i/tag ::i/lea, ::i/args [::r/bp [::r/si]]}
   [0x8d 0x2d] {::i/tag ::i/lea, ::i/args [::r/bp [::r/di]]}
   [0x8d 0x2e 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [0x0ff0]]}
   [0x8d 0x2e 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [0xf00f]]}
   [0x8d 0x2f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx]]}
   [0x8d 0x30] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x8d 0x31] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x8d 0x32] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x8d 0x33] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x8d 0x34] {::i/tag ::i/lea, ::i/args [::r/si [::r/si]]}
   [0x8d 0x35] {::i/tag ::i/lea, ::i/args [::r/si [::r/di]]}
   [0x8d 0x36 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [0x0ff0]]}
   [0x8d 0x36 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [0xf00f]]}
   [0x8d 0x37] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx]]}
   [0x8d 0x38] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x8d 0x39] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x8d 0x3a] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x8d 0x3b] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x8d 0x3c] {::i/tag ::i/lea, ::i/args [::r/di [::r/si]]}
   [0x8d 0x3d] {::i/tag ::i/lea, ::i/args [::r/di [::r/di]]}
   [0x8d 0x3e 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [0x0ff0]]}
   [0x8d 0x3e 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [0xf00f]]}
   [0x8d 0x3f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx]]}
   [0x8d 0x40 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x8d 0x40 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/si -16]]}
   [0x8d 0x40 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/si 15]]}
   [0x8d 0x41 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x8d 0x41 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/di -16]]}
   [0x8d 0x41 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/di 15]]}
   [0x8d 0x42 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x8d 0x42 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/si -16]]}
   [0x8d 0x42 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/si 15]]}
   [0x8d 0x43 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x8d 0x43 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/di -16]]}
   [0x8d 0x43 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/di 15]]}
   [0x8d 0x44 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/si]]}
   [0x8d 0x44 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/si -16]]}
   [0x8d 0x44 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/si 15]]}
   [0x8d 0x45 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/di]]}
   [0x8d 0x45 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/di -16]]}
   [0x8d 0x45 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/di 15]]}
   [0x8d 0x46 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp]]}
   [0x8d 0x46 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp -16]]}
   [0x8d 0x46 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp 15]]}
   [0x8d 0x47 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx]]}
   [0x8d 0x47 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx -16]]}
   [0x8d 0x47 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx 15]]}
   [0x8d 0x48 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x8d 0x48 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/si -16]]}
   [0x8d 0x48 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/si 15]]}
   [0x8d 0x49 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x8d 0x49 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/di -16]]}
   [0x8d 0x49 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/di 15]]}
   [0x8d 0x4a 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x8d 0x4a 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/si -16]]}
   [0x8d 0x4a 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/si 15]]}
   [0x8d 0x4b 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x8d 0x4b 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/di -16]]}
   [0x8d 0x4b 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/di 15]]}
   [0x8d 0x4c 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/si]]}
   [0x8d 0x4c 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/si -16]]}
   [0x8d 0x4c 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/si 15]]}
   [0x8d 0x4d 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/di]]}
   [0x8d 0x4d 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/di -16]]}
   [0x8d 0x4d 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/di 15]]}
   [0x8d 0x4e 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp]]}
   [0x8d 0x4e 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp -16]]}
   [0x8d 0x4e 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp 15]]}
   [0x8d 0x4f 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx]]}
   [0x8d 0x4f 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx -16]]}
   [0x8d 0x4f 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx 15]]}
   [0x8d 0x50 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x8d 0x50 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/si -16]]}
   [0x8d 0x50 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/si 15]]}
   [0x8d 0x51 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x8d 0x51 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/di -16]]}
   [0x8d 0x51 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/di 15]]}
   [0x8d 0x52 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x8d 0x52 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/si -16]]}
   [0x8d 0x52 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/si 15]]}
   [0x8d 0x53 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x8d 0x53 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/di -16]]}
   [0x8d 0x53 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/di 15]]}
   [0x8d 0x54 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/si]]}
   [0x8d 0x54 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/si -16]]}
   [0x8d 0x54 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/si 15]]}
   [0x8d 0x55 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/di]]}
   [0x8d 0x55 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/di -16]]}
   [0x8d 0x55 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/di 15]]}
   [0x8d 0x56 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp]]}
   [0x8d 0x56 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp -16]]}
   [0x8d 0x56 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp 15]]}
   [0x8d 0x57 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx]]}
   [0x8d 0x57 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx -16]]}
   [0x8d 0x57 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx 15]]}
   [0x8d 0x58 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x8d 0x58 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/si -16]]}
   [0x8d 0x58 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/si 15]]}
   [0x8d 0x59 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x8d 0x59 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/di -16]]}
   [0x8d 0x59 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/di 15]]}
   [0x8d 0x5a 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x8d 0x5a 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/si -16]]}
   [0x8d 0x5a 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/si 15]]}
   [0x8d 0x5b 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x8d 0x5b 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/di -16]]}
   [0x8d 0x5b 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/di 15]]}
   [0x8d 0x5c 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/si]]}
   [0x8d 0x5c 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/si -16]]}
   [0x8d 0x5c 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/si 15]]}
   [0x8d 0x5d 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/di]]}
   [0x8d 0x5d 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/di -16]]}
   [0x8d 0x5d 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/di 15]]}
   [0x8d 0x5e 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp]]}
   [0x8d 0x5e 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp -16]]}
   [0x8d 0x5e 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp 15]]}
   [0x8d 0x5f 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx]]}
   [0x8d 0x5f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx -16]]}
   [0x8d 0x5f 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx 15]]}
   [0x8d 0x60 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x8d 0x60 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/si -16]]}
   [0x8d 0x60 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/si 15]]}
   [0x8d 0x61 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x8d 0x61 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/di -16]]}
   [0x8d 0x61 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/di 15]]}
   [0x8d 0x62 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x8d 0x62 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/si -16]]}
   [0x8d 0x62 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/si 15]]}
   [0x8d 0x63 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x8d 0x63 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/di -16]]}
   [0x8d 0x63 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/di 15]]}
   [0x8d 0x64 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/si]]}
   [0x8d 0x64 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/si -16]]}
   [0x8d 0x64 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/si 15]]}
   [0x8d 0x65 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/di]]}
   [0x8d 0x65 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/di -16]]}
   [0x8d 0x65 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/di 15]]}
   [0x8d 0x66 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp]]}
   [0x8d 0x66 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp -16]]}
   [0x8d 0x66 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp 15]]}
   [0x8d 0x67 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx]]}
   [0x8d 0x67 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx -16]]}
   [0x8d 0x67 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx 15]]}
   [0x8d 0x68 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x8d 0x68 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/si -16]]}
   [0x8d 0x68 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/si 15]]}
   [0x8d 0x69 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x8d 0x69 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/di -16]]}
   [0x8d 0x69 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/di 15]]}
   [0x8d 0x6a 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x8d 0x6a 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/si -16]]}
   [0x8d 0x6a 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/si 15]]}
   [0x8d 0x6b 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x8d 0x6b 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/di -16]]}
   [0x8d 0x6b 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/di 15]]}
   [0x8d 0x6c 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/si]]}
   [0x8d 0x6c 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/si -16]]}
   [0x8d 0x6c 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/si 15]]}
   [0x8d 0x6d 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/di]]}
   [0x8d 0x6d 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/di -16]]}
   [0x8d 0x6d 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/di 15]]}
   [0x8d 0x6e 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp]]}
   [0x8d 0x6e 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp -16]]}
   [0x8d 0x6e 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp 15]]}
   [0x8d 0x6f 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx]]}
   [0x8d 0x6f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx -16]]}
   [0x8d 0x6f 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx 15]]}
   [0x8d 0x70 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x8d 0x70 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/si -16]]}
   [0x8d 0x70 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/si 15]]}
   [0x8d 0x71 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x8d 0x71 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/di -16]]}
   [0x8d 0x71 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/di 15]]}
   [0x8d 0x72 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x8d 0x72 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/si -16]]}
   [0x8d 0x72 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/si 15]]}
   [0x8d 0x73 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x8d 0x73 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/di -16]]}
   [0x8d 0x73 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/di 15]]}
   [0x8d 0x74 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/si]]}
   [0x8d 0x74 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/si -16]]}
   [0x8d 0x74 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/si 15]]}
   [0x8d 0x75 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/di]]}
   [0x8d 0x75 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/di -16]]}
   [0x8d 0x75 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/di 15]]}
   [0x8d 0x76 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp]]}
   [0x8d 0x76 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp -16]]}
   [0x8d 0x76 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp 15]]}
   [0x8d 0x77 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx]]}
   [0x8d 0x77 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx -16]]}
   [0x8d 0x77 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx 15]]}
   [0x8d 0x78 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x8d 0x78 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/si -16]]}
   [0x8d 0x78 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/si 15]]}
   [0x8d 0x79 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x8d 0x79 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/di -16]]}
   [0x8d 0x79 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/di 15]]}
   [0x8d 0x7a 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x8d 0x7a 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/si -16]]}
   [0x8d 0x7a 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/si 15]]}
   [0x8d 0x7b 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x8d 0x7b 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/di -16]]}
   [0x8d 0x7b 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/di 15]]}
   [0x8d 0x7c 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/si]]}
   [0x8d 0x7c 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/si -16]]}
   [0x8d 0x7c 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/si 15]]}
   [0x8d 0x7d 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/di]]}
   [0x8d 0x7d 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/di -16]]}
   [0x8d 0x7d 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/di 15]]}
   [0x8d 0x7e 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp]]}
   [0x8d 0x7e 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp -16]]}
   [0x8d 0x7e 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp 15]]}
   [0x8d 0x7f 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx]]}
   [0x8d 0x7f 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx -16]]}
   [0x8d 0x7f 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx 15]]}
   [0x8d 0x80 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/si]]}
   [0x8d 0x80 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/si 4080]]}
   [0x8d 0x80 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/si -4081]]}
   [0x8d 0x81 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/di]]}
   [0x8d 0x81 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/di 4080]]}
   [0x8d 0x81 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx ::r/di -4081]]}
   [0x8d 0x82 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/si]]}
   [0x8d 0x82 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/si 4080]]}
   [0x8d 0x82 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/si -4081]]}
   [0x8d 0x83 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/di]]}
   [0x8d 0x83 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/di 4080]]}
   [0x8d 0x83 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp ::r/di -4081]]}
   [0x8d 0x84 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/si]]}
   [0x8d 0x84 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/si 4080]]}
   [0x8d 0x84 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/si -4081]]}
   [0x8d 0x85 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/di]]}
   [0x8d 0x85 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/di 4080]]}
   [0x8d 0x85 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/di -4081]]}
   [0x8d 0x86 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp]]}
   [0x8d 0x86 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp 4080]]}
   [0x8d 0x86 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bp -4081]]}
   [0x8d 0x87 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx]]}
   [0x8d 0x87 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx 4080]]}
   [0x8d 0x87 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/ax [::r/bx -4081]]}
   [0x8d 0x88 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/si]]}
   [0x8d 0x88 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/si 4080]]}
   [0x8d 0x88 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/si -4081]]}
   [0x8d 0x89 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/di]]}
   [0x8d 0x89 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/di 4080]]}
   [0x8d 0x89 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx ::r/di -4081]]}
   [0x8d 0x8a 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/si]]}
   [0x8d 0x8a 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/si 4080]]}
   [0x8d 0x8a 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/si -4081]]}
   [0x8d 0x8b 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/di]]}
   [0x8d 0x8b 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/di 4080]]}
   [0x8d 0x8b 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp ::r/di -4081]]}
   [0x8d 0x8c 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/si]]}
   [0x8d 0x8c 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/si 4080]]}
   [0x8d 0x8c 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/si -4081]]}
   [0x8d 0x8d 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/di]]}
   [0x8d 0x8d 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/di 4080]]}
   [0x8d 0x8d 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/di -4081]]}
   [0x8d 0x8e 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp]]}
   [0x8d 0x8e 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp 4080]]}
   [0x8d 0x8e 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bp -4081]]}
   [0x8d 0x8f 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx]]}
   [0x8d 0x8f 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx 4080]]}
   [0x8d 0x8f 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/cx [::r/bx -4081]]}
   [0x8d 0x90 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/si]]}
   [0x8d 0x90 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/si 4080]]}
   [0x8d 0x90 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/si -4081]]}
   [0x8d 0x91 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/di]]}
   [0x8d 0x91 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/di 4080]]}
   [0x8d 0x91 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx ::r/di -4081]]}
   [0x8d 0x92 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/si]]}
   [0x8d 0x92 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/si 4080]]}
   [0x8d 0x92 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/si -4081]]}
   [0x8d 0x93 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/di]]}
   [0x8d 0x93 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/di 4080]]}
   [0x8d 0x93 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp ::r/di -4081]]}
   [0x8d 0x94 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/si]]}
   [0x8d 0x94 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/si 4080]]}
   [0x8d 0x94 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/si -4081]]}
   [0x8d 0x95 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/di]]}
   [0x8d 0x95 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/di 4080]]}
   [0x8d 0x95 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/di -4081]]}
   [0x8d 0x96 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp]]}
   [0x8d 0x96 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp 4080]]}
   [0x8d 0x96 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bp -4081]]}
   [0x8d 0x97 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx]]}
   [0x8d 0x97 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx 4080]]}
   [0x8d 0x97 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/dx [::r/bx -4081]]}
   [0x8d 0x98 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/si]]}
   [0x8d 0x98 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/si 4080]]}
   [0x8d 0x98 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/si -4081]]}
   [0x8d 0x99 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/di]]}
   [0x8d 0x99 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/di 4080]]}
   [0x8d 0x99 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx ::r/di -4081]]}
   [0x8d 0x9a 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/si]]}
   [0x8d 0x9a 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/si 4080]]}
   [0x8d 0x9a 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/si -4081]]}
   [0x8d 0x9b 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/di]]}
   [0x8d 0x9b 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/di 4080]]}
   [0x8d 0x9b 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp ::r/di -4081]]}
   [0x8d 0x9c 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/si]]}
   [0x8d 0x9c 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/si 4080]]}
   [0x8d 0x9c 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/si -4081]]}
   [0x8d 0x9d 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/di]]}
   [0x8d 0x9d 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/di 4080]]}
   [0x8d 0x9d 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/di -4081]]}
   [0x8d 0x9e 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp]]}
   [0x8d 0x9e 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp 4080]]}
   [0x8d 0x9e 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bp -4081]]}
   [0x8d 0x9f 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx]]}
   [0x8d 0x9f 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx 4080]]}
   [0x8d 0x9f 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bx [::r/bx -4081]]}
   [0x8d 0xa0 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/si]]}
   [0x8d 0xa0 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/si 4080]]}
   [0x8d 0xa0 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/si -4081]]}
   [0x8d 0xa1 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/di]]}
   [0x8d 0xa1 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/di 4080]]}
   [0x8d 0xa1 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx ::r/di -4081]]}
   [0x8d 0xa2 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/si]]}
   [0x8d 0xa2 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/si 4080]]}
   [0x8d 0xa2 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/si -4081]]}
   [0x8d 0xa3 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/di]]}
   [0x8d 0xa3 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/di 4080]]}
   [0x8d 0xa3 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp ::r/di -4081]]}
   [0x8d 0xa4 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/si]]}
   [0x8d 0xa4 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/si 4080]]}
   [0x8d 0xa4 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/si -4081]]}
   [0x8d 0xa5 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/di]]}
   [0x8d 0xa5 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/di 4080]]}
   [0x8d 0xa5 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/di -4081]]}
   [0x8d 0xa6 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp]]}
   [0x8d 0xa6 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp 4080]]}
   [0x8d 0xa6 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bp -4081]]}
   [0x8d 0xa7 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx]]}
   [0x8d 0xa7 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx 4080]]}
   [0x8d 0xa7 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/sp [::r/bx -4081]]}
   [0x8d 0xa8 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/si]]}
   [0x8d 0xa8 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/si 4080]]}
   [0x8d 0xa8 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/si -4081]]}
   [0x8d 0xa9 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/di]]}
   [0x8d 0xa9 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/di 4080]]}
   [0x8d 0xa9 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx ::r/di -4081]]}
   [0x8d 0xaa 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/si]]}
   [0x8d 0xaa 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/si 4080]]}
   [0x8d 0xaa 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/si -4081]]}
   [0x8d 0xab 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/di]]}
   [0x8d 0xab 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/di 4080]]}
   [0x8d 0xab 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp ::r/di -4081]]}
   [0x8d 0xac 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/si]]}
   [0x8d 0xac 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/si 4080]]}
   [0x8d 0xac 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/si -4081]]}
   [0x8d 0xad 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/di]]}
   [0x8d 0xad 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/di 4080]]}
   [0x8d 0xad 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/di -4081]]}
   [0x8d 0xae 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp]]}
   [0x8d 0xae 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp 4080]]}
   [0x8d 0xae 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bp -4081]]}
   [0x8d 0xaf 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx]]}
   [0x8d 0xaf 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx 4080]]}
   [0x8d 0xaf 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/bp [::r/bx -4081]]}
   [0x8d 0xb0 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/si]]}
   [0x8d 0xb0 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/si 4080]]}
   [0x8d 0xb0 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/si -4081]]}
   [0x8d 0xb1 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/di]]}
   [0x8d 0xb1 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/di 4080]]}
   [0x8d 0xb1 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx ::r/di -4081]]}
   [0x8d 0xb2 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/si]]}
   [0x8d 0xb2 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/si 4080]]}
   [0x8d 0xb2 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/si -4081]]}
   [0x8d 0xb3 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/di]]}
   [0x8d 0xb3 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/di 4080]]}
   [0x8d 0xb3 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp ::r/di -4081]]}
   [0x8d 0xb4 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/si]]}
   [0x8d 0xb4 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/si 4080]]}
   [0x8d 0xb4 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/si -4081]]}
   [0x8d 0xb5 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/di]]}
   [0x8d 0xb5 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/di 4080]]}
   [0x8d 0xb5 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/di -4081]]}
   [0x8d 0xb6 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp]]}
   [0x8d 0xb6 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp 4080]]}
   [0x8d 0xb6 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bp -4081]]}
   [0x8d 0xb7 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx]]}
   [0x8d 0xb7 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx 4080]]}
   [0x8d 0xb7 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/si [::r/bx -4081]]}
   [0x8d 0xb8 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/si]]}
   [0x8d 0xb8 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/si 4080]]}
   [0x8d 0xb8 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/si -4081]]}
   [0x8d 0xb9 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/di]]}
   [0x8d 0xb9 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/di 4080]]}
   [0x8d 0xb9 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx ::r/di -4081]]}
   [0x8d 0xba 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/si]]}
   [0x8d 0xba 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/si 4080]]}
   [0x8d 0xba 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/si -4081]]}
   [0x8d 0xbb 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/di]]}
   [0x8d 0xbb 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/di 4080]]}
   [0x8d 0xbb 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp ::r/di -4081]]}
   [0x8d 0xbc 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/si]]}
   [0x8d 0xbc 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/si 4080]]}
   [0x8d 0xbc 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/si -4081]]}
   [0x8d 0xbd 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/di]]}
   [0x8d 0xbd 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/di 4080]]}
   [0x8d 0xbd 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/di -4081]]}
   [0x8d 0xbe 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp]]}
   [0x8d 0xbe 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp 4080]]}
   [0x8d 0xbe 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bp -4081]]}
   [0x8d 0xbf 0x00 0x00] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx]]}
   [0x8d 0xbf 0xf0 0x0f] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx 4080]]}
   [0x8d 0xbf 0x0f 0xf0] {::i/tag ::i/lea, ::i/args [::r/di [::r/bx -4081]]}
   [0x8d 0xc0] nil
   [0x8d 0xc1] nil
   [0x8d 0xc2] nil
   [0x8d 0xc3] nil
   [0x8d 0xc4] nil
   [0x8d 0xc5] nil
   [0x8d 0xc6] nil
   [0x8d 0xc7] nil
   [0x8d 0xc8] nil
   [0x8d 0xc9] nil
   [0x8d 0xca] nil
   [0x8d 0xcb] nil
   [0x8d 0xcc] nil
   [0x8d 0xcd] nil
   [0x8d 0xce] nil
   [0x8d 0xcf] nil
   [0x8d 0xd0] nil
   [0x8d 0xd1] nil
   [0x8d 0xd2] nil
   [0x8d 0xd3] nil
   [0x8d 0xd4] nil
   [0x8d 0xd5] nil
   [0x8d 0xd6] nil
   [0x8d 0xd7] nil
   [0x8d 0xd8] nil
   [0x8d 0xd9] nil
   [0x8d 0xda] nil
   [0x8d 0xdb] nil
   [0x8d 0xdc] nil
   [0x8d 0xdd] nil
   [0x8d 0xde] nil
   [0x8d 0xdf] nil
   [0x8d 0xe0] nil
   [0x8d 0xe1] nil
   [0x8d 0xe2] nil
   [0x8d 0xe3] nil
   [0x8d 0xe4] nil
   [0x8d 0xe5] nil
   [0x8d 0xe6] nil
   [0x8d 0xe7] nil
   [0x8d 0xe8] nil
   [0x8d 0xe9] nil
   [0x8d 0xea] nil
   [0x8d 0xeb] nil
   [0x8d 0xec] nil
   [0x8d 0xed] nil
   [0x8d 0xee] nil
   [0x8d 0xef] nil
   [0x8d 0xf0] nil
   [0x8d 0xf1] nil
   [0x8d 0xf2] nil
   [0x8d 0xf3] nil
   [0x8d 0xf4] nil
   [0x8d 0xf5] nil
   [0x8d 0xf6] nil
   [0x8d 0xf7] nil
   [0x8d 0xf8] nil
   [0x8d 0xf9] nil
   [0x8d 0xfa] nil
   [0x8d 0xfb] nil
   [0x8d 0xfc] nil
   [0x8d 0xfd] nil
   [0x8d 0xfe] nil
   [0x8d 0xff] nil})
