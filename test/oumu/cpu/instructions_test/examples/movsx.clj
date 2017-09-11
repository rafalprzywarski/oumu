(ns oumu.cpu.instructions-test.examples.movsx
    (:require [oumu.cpu.instructions :as i]
              [oumu.cpu.registers :as r]))


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
