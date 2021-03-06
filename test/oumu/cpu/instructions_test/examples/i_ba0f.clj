(ns oumu.cpu.instructions-test.examples.i-ba0f
    (:require [oumu.cpu.instructions :as i]
              [oumu.cpu.registers :as r]))


(def decode-examples-0xba0f
  {[0x0f 0xba 0x00 0xf0] nil
   [0x0f 0xba 0x00 0x0f] nil
   [0x0f 0xba 0x01 0xf0] nil
   [0x0f 0xba 0x01 0x0f] nil
   [0x0f 0xba 0x02 0xf0] nil
   [0x0f 0xba 0x02 0x0f] nil
   [0x0f 0xba 0x03 0xf0] nil
   [0x0f 0xba 0x03 0x0f] nil
   [0x0f 0xba 0x04 0xf0] nil
   [0x0f 0xba 0x04 0x0f] nil
   [0x0f 0xba 0x05 0xf0] nil
   [0x0f 0xba 0x05 0x0f] nil
   [0x0f 0xba 0x06 0x12 0xf4 0xf0] nil
   [0x0f 0xba 0x06 0xf4 0x12 0x0f] nil
   [0x0f 0xba 0x07 0xf0] nil
   [0x0f 0xba 0x07 0x0f] nil
   [0x0f 0xba 0x08 0xf0] nil
   [0x0f 0xba 0x08 0x0f] nil
   [0x0f 0xba 0x09 0xf0] nil
   [0x0f 0xba 0x09 0x0f] nil
   [0x0f 0xba 0x0a 0xf0] nil
   [0x0f 0xba 0x0a 0x0f] nil
   [0x0f 0xba 0x0b 0xf0] nil
   [0x0f 0xba 0x0b 0x0f] nil
   [0x0f 0xba 0x0c 0xf0] nil
   [0x0f 0xba 0x0c 0x0f] nil
   [0x0f 0xba 0x0d 0xf0] nil
   [0x0f 0xba 0x0d 0x0f] nil
   [0x0f 0xba 0x0e 0x12 0xf4 0xf0] nil
   [0x0f 0xba 0x0e 0xf4 0x12 0x0f] nil
   [0x0f 0xba 0x0f 0xf0] nil
   [0x0f 0xba 0x0f 0x0f] nil
   [0x0f 0xba 0x10 0xf0] nil
   [0x0f 0xba 0x10 0x0f] nil
   [0x0f 0xba 0x11 0xf0] nil
   [0x0f 0xba 0x11 0x0f] nil
   [0x0f 0xba 0x12 0xf0] nil
   [0x0f 0xba 0x12 0x0f] nil
   [0x0f 0xba 0x13 0xf0] nil
   [0x0f 0xba 0x13 0x0f] nil
   [0x0f 0xba 0x14 0xf0] nil
   [0x0f 0xba 0x14 0x0f] nil
   [0x0f 0xba 0x15 0xf0] nil
   [0x0f 0xba 0x15 0x0f] nil
   [0x0f 0xba 0x16 0x12 0xf4 0xf0] nil
   [0x0f 0xba 0x16 0xf4 0x12 0x0f] nil
   [0x0f 0xba 0x17 0xf0] nil
   [0x0f 0xba 0x17 0x0f] nil
   [0x0f 0xba 0x18 0xf0] nil
   [0x0f 0xba 0x18 0x0f] nil
   [0x0f 0xba 0x19 0xf0] nil
   [0x0f 0xba 0x19 0x0f] nil
   [0x0f 0xba 0x1a 0xf0] nil
   [0x0f 0xba 0x1a 0x0f] nil
   [0x0f 0xba 0x1b 0xf0] nil
   [0x0f 0xba 0x1b 0x0f] nil
   [0x0f 0xba 0x1c 0xf0] nil
   [0x0f 0xba 0x1c 0x0f] nil
   [0x0f 0xba 0x1d 0xf0] nil
   [0x0f 0xba 0x1d 0x0f] nil
   [0x0f 0xba 0x1e 0x12 0xf4 0xf0] nil
   [0x0f 0xba 0x1e 0xf4 0x12 0x0f] nil
   [0x0f 0xba 0x1f 0xf0] nil
   [0x0f 0xba 0x1f 0x0f] nil
   [0x0f 0xba 0x20 0xf0] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/si] 0xf0]}
   [0x0f 0xba 0x20 0x0f] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/si] 0x0f]}
   [0x0f 0xba 0x21 0xf0] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/di] 0xf0]}
   [0x0f 0xba 0x21 0x0f] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/di] 0x0f]}
   [0x0f 0xba 0x22 0xf0] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/si] 0xf0]}
   [0x0f 0xba 0x22 0x0f] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/si] 0x0f]}
   [0x0f 0xba 0x23 0xf0] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/di] 0xf0]}
   [0x0f 0xba 0x23 0x0f] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/di] 0x0f]}
   [0x0f 0xba 0x24 0xf0] {::i/tag ::i/bt, ::i/args [[::r/si] 0xf0]}
   [0x0f 0xba 0x24 0x0f] {::i/tag ::i/bt, ::i/args [[::r/si] 0x0f]}
   [0x0f 0xba 0x25 0xf0] {::i/tag ::i/bt, ::i/args [[::r/di] 0xf0]}
   [0x0f 0xba 0x25 0x0f] {::i/tag ::i/bt, ::i/args [[::r/di] 0x0f]}
   [0x0f 0xba 0x26 0x12 0xf4 0xf0] {::i/tag ::i/bt, ::i/args [[0xf412] 0xf0]}
   [0x0f 0xba 0x26 0xf4 0x12 0x0f] {::i/tag ::i/bt, ::i/args [[0x12f4] 0x0f]}
   [0x0f 0xba 0x27 0xf0] {::i/tag ::i/bt, ::i/args [[::r/bx] 0xf0]}
   [0x0f 0xba 0x27 0x0f] {::i/tag ::i/bt, ::i/args [[::r/bx] 0x0f]}
   [0x0f 0xba 0x28 0xf0] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/si] 0xf0]}
   [0x0f 0xba 0x28 0x0f] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/si] 0x0f]}
   [0x0f 0xba 0x29 0xf0] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/di] 0xf0]}
   [0x0f 0xba 0x29 0x0f] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/di] 0x0f]}
   [0x0f 0xba 0x2a 0xf0] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/si] 0xf0]}
   [0x0f 0xba 0x2a 0x0f] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/si] 0x0f]}
   [0x0f 0xba 0x2b 0xf0] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/di] 0xf0]}
   [0x0f 0xba 0x2b 0x0f] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/di] 0x0f]}
   [0x0f 0xba 0x2c 0xf0] {::i/tag ::i/bts, ::i/args [[::r/si] 0xf0]}
   [0x0f 0xba 0x2c 0x0f] {::i/tag ::i/bts, ::i/args [[::r/si] 0x0f]}
   [0x0f 0xba 0x2d 0xf0] {::i/tag ::i/bts, ::i/args [[::r/di] 0xf0]}
   [0x0f 0xba 0x2d 0x0f] {::i/tag ::i/bts, ::i/args [[::r/di] 0x0f]}
   [0x0f 0xba 0x2e 0x12 0xf4 0xf0] {::i/tag ::i/bts, ::i/args [[0xf412] 0xf0]}
   [0x0f 0xba 0x2e 0xf4 0x12 0x0f] {::i/tag ::i/bts, ::i/args [[0x12f4] 0x0f]}
   [0x0f 0xba 0x2f 0xf0] {::i/tag ::i/bts, ::i/args [[::r/bx] 0xf0]}
   [0x0f 0xba 0x2f 0x0f] {::i/tag ::i/bts, ::i/args [[::r/bx] 0x0f]}
   [0x0f 0xba 0x30 0xf0] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/si] 0xf0]}
   [0x0f 0xba 0x30 0x0f] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/si] 0x0f]}
   [0x0f 0xba 0x31 0xf0] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/di] 0xf0]}
   [0x0f 0xba 0x31 0x0f] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/di] 0x0f]}
   [0x0f 0xba 0x32 0xf0] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/si] 0xf0]}
   [0x0f 0xba 0x32 0x0f] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/si] 0x0f]}
   [0x0f 0xba 0x33 0xf0] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/di] 0xf0]}
   [0x0f 0xba 0x33 0x0f] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/di] 0x0f]}
   [0x0f 0xba 0x34 0xf0] {::i/tag ::i/btr, ::i/args [[::r/si] 0xf0]}
   [0x0f 0xba 0x34 0x0f] {::i/tag ::i/btr, ::i/args [[::r/si] 0x0f]}
   [0x0f 0xba 0x35 0xf0] {::i/tag ::i/btr, ::i/args [[::r/di] 0xf0]}
   [0x0f 0xba 0x35 0x0f] {::i/tag ::i/btr, ::i/args [[::r/di] 0x0f]}
   [0x0f 0xba 0x36 0x12 0xf4 0xf0] {::i/tag ::i/btr, ::i/args [[0xf412] 0xf0]}
   [0x0f 0xba 0x36 0xf4 0x12 0x0f] {::i/tag ::i/btr, ::i/args [[0x12f4] 0x0f]}
   [0x0f 0xba 0x37 0xf0] {::i/tag ::i/btr, ::i/args [[::r/bx] 0xf0]}
   [0x0f 0xba 0x37 0x0f] {::i/tag ::i/btr, ::i/args [[::r/bx] 0x0f]}
   [0x0f 0xba 0x38 0xf0] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/si] 0xf0]}
   [0x0f 0xba 0x38 0x0f] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/si] 0x0f]}
   [0x0f 0xba 0x39 0xf0] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/di] 0xf0]}
   [0x0f 0xba 0x39 0x0f] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/di] 0x0f]}
   [0x0f 0xba 0x3a 0xf0] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/si] 0xf0]}
   [0x0f 0xba 0x3a 0x0f] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/si] 0x0f]}
   [0x0f 0xba 0x3b 0xf0] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/di] 0xf0]}
   [0x0f 0xba 0x3b 0x0f] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/di] 0x0f]}
   [0x0f 0xba 0x3c 0xf0] {::i/tag ::i/btc, ::i/args [[::r/si] 0xf0]}
   [0x0f 0xba 0x3c 0x0f] {::i/tag ::i/btc, ::i/args [[::r/si] 0x0f]}
   [0x0f 0xba 0x3d 0xf0] {::i/tag ::i/btc, ::i/args [[::r/di] 0xf0]}
   [0x0f 0xba 0x3d 0x0f] {::i/tag ::i/btc, ::i/args [[::r/di] 0x0f]}
   [0x0f 0xba 0x3e 0x12 0xf4 0xf0] {::i/tag ::i/btc, ::i/args [[0xf412] 0xf0]}
   [0x0f 0xba 0x3e 0xf4 0x12 0x0f] {::i/tag ::i/btc, ::i/args [[0x12f4] 0x0f]}
   [0x0f 0xba 0x3f 0xf0] {::i/tag ::i/btc, ::i/args [[::r/bx] 0xf0]}
   [0x0f 0xba 0x3f 0x0f] {::i/tag ::i/btc, ::i/args [[::r/bx] 0x0f]}
   [0x0f 0xba 0x40 0x00 0xf4] nil
   [0x0f 0xba 0x40 0xf0 0xf4] nil
   [0x0f 0xba 0x40 0x0f 0x12] nil
   [0x0f 0xba 0x41 0x00 0xf4] nil
   [0x0f 0xba 0x41 0xf0 0xf4] nil
   [0x0f 0xba 0x41 0x0f 0x12] nil
   [0x0f 0xba 0x42 0x00 0xf4] nil
   [0x0f 0xba 0x42 0xf0 0xf4] nil
   [0x0f 0xba 0x42 0x0f 0x12] nil
   [0x0f 0xba 0x43 0x00 0xf4] nil
   [0x0f 0xba 0x43 0xf0 0xf4] nil
   [0x0f 0xba 0x43 0x0f 0x12] nil
   [0x0f 0xba 0x44 0x00 0xf4] nil
   [0x0f 0xba 0x44 0xf0 0xf4] nil
   [0x0f 0xba 0x44 0x0f 0x12] nil
   [0x0f 0xba 0x45 0x00 0xf4] nil
   [0x0f 0xba 0x45 0xf0 0xf4] nil
   [0x0f 0xba 0x45 0x0f 0x12] nil
   [0x0f 0xba 0x46 0x00 0xf4] nil
   [0x0f 0xba 0x46 0xf0 0xf4] nil
   [0x0f 0xba 0x46 0x0f 0x12] nil
   [0x0f 0xba 0x47 0x00 0xf4] nil
   [0x0f 0xba 0x47 0xf0 0xf4] nil
   [0x0f 0xba 0x47 0x0f 0x12] nil
   [0x0f 0xba 0x48 0x00 0xf4] nil
   [0x0f 0xba 0x48 0xf0 0xf4] nil
   [0x0f 0xba 0x48 0x0f 0x12] nil
   [0x0f 0xba 0x49 0x00 0xf4] nil
   [0x0f 0xba 0x49 0xf0 0xf4] nil
   [0x0f 0xba 0x49 0x0f 0x12] nil
   [0x0f 0xba 0x4a 0x00 0xf4] nil
   [0x0f 0xba 0x4a 0xf0 0xf4] nil
   [0x0f 0xba 0x4a 0x0f 0x12] nil
   [0x0f 0xba 0x4b 0x00 0xf4] nil
   [0x0f 0xba 0x4b 0xf0 0xf4] nil
   [0x0f 0xba 0x4b 0x0f 0x12] nil
   [0x0f 0xba 0x4c 0x00 0xf4] nil
   [0x0f 0xba 0x4c 0xf0 0xf4] nil
   [0x0f 0xba 0x4c 0x0f 0x12] nil
   [0x0f 0xba 0x4d 0x00 0xf4] nil
   [0x0f 0xba 0x4d 0xf0 0xf4] nil
   [0x0f 0xba 0x4d 0x0f 0x12] nil
   [0x0f 0xba 0x4e 0x00 0xf4] nil
   [0x0f 0xba 0x4e 0xf0 0xf4] nil
   [0x0f 0xba 0x4e 0x0f 0x12] nil
   [0x0f 0xba 0x4f 0x00 0xf4] nil
   [0x0f 0xba 0x4f 0xf0 0xf4] nil
   [0x0f 0xba 0x4f 0x0f 0x12] nil
   [0x0f 0xba 0x50 0x00 0xf4] nil
   [0x0f 0xba 0x50 0xf0 0xf4] nil
   [0x0f 0xba 0x50 0x0f 0x12] nil
   [0x0f 0xba 0x51 0x00 0xf4] nil
   [0x0f 0xba 0x51 0xf0 0xf4] nil
   [0x0f 0xba 0x51 0x0f 0x12] nil
   [0x0f 0xba 0x52 0x00 0xf4] nil
   [0x0f 0xba 0x52 0xf0 0xf4] nil
   [0x0f 0xba 0x52 0x0f 0x12] nil
   [0x0f 0xba 0x53 0x00 0xf4] nil
   [0x0f 0xba 0x53 0xf0 0xf4] nil
   [0x0f 0xba 0x53 0x0f 0x12] nil
   [0x0f 0xba 0x54 0x00 0xf4] nil
   [0x0f 0xba 0x54 0xf0 0xf4] nil
   [0x0f 0xba 0x54 0x0f 0x12] nil
   [0x0f 0xba 0x55 0x00 0xf4] nil
   [0x0f 0xba 0x55 0xf0 0xf4] nil
   [0x0f 0xba 0x55 0x0f 0x12] nil
   [0x0f 0xba 0x56 0x00 0xf4] nil
   [0x0f 0xba 0x56 0xf0 0xf4] nil
   [0x0f 0xba 0x56 0x0f 0x12] nil
   [0x0f 0xba 0x57 0x00 0xf4] nil
   [0x0f 0xba 0x57 0xf0 0xf4] nil
   [0x0f 0xba 0x57 0x0f 0x12] nil
   [0x0f 0xba 0x58 0x00 0xf4] nil
   [0x0f 0xba 0x58 0xf0 0xf4] nil
   [0x0f 0xba 0x58 0x0f 0x12] nil
   [0x0f 0xba 0x59 0x00 0xf4] nil
   [0x0f 0xba 0x59 0xf0 0xf4] nil
   [0x0f 0xba 0x59 0x0f 0x12] nil
   [0x0f 0xba 0x5a 0x00 0xf4] nil
   [0x0f 0xba 0x5a 0xf0 0xf4] nil
   [0x0f 0xba 0x5a 0x0f 0x12] nil
   [0x0f 0xba 0x5b 0x00 0xf4] nil
   [0x0f 0xba 0x5b 0xf0 0xf4] nil
   [0x0f 0xba 0x5b 0x0f 0x12] nil
   [0x0f 0xba 0x5c 0x00 0xf4] nil
   [0x0f 0xba 0x5c 0xf0 0xf4] nil
   [0x0f 0xba 0x5c 0x0f 0x12] nil
   [0x0f 0xba 0x5d 0x00 0xf4] nil
   [0x0f 0xba 0x5d 0xf0 0xf4] nil
   [0x0f 0xba 0x5d 0x0f 0x12] nil
   [0x0f 0xba 0x5e 0x00 0xf4] nil
   [0x0f 0xba 0x5e 0xf0 0xf4] nil
   [0x0f 0xba 0x5e 0x0f 0x12] nil
   [0x0f 0xba 0x5f 0x00 0xf4] nil
   [0x0f 0xba 0x5f 0xf0 0xf4] nil
   [0x0f 0xba 0x5f 0x0f 0x12] nil
   [0x0f 0xba 0x60 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/si] 0xf4]}
   [0x0f 0xba 0x60 0xf0 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/si -16] 0xf4]}
   [0x0f 0xba 0x60 0x0f 0x12] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/si 15] 0x12]}
   [0x0f 0xba 0x61 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/di] 0xf4]}
   [0x0f 0xba 0x61 0xf0 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/di -16] 0xf4]}
   [0x0f 0xba 0x61 0x0f 0x12] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/di 15] 0x12]}
   [0x0f 0xba 0x62 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/si] 0xf4]}
   [0x0f 0xba 0x62 0xf0 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/si -16] 0xf4]}
   [0x0f 0xba 0x62 0x0f 0x12] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/si 15] 0x12]}
   [0x0f 0xba 0x63 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/di] 0xf4]}
   [0x0f 0xba 0x63 0xf0 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/di -16] 0xf4]}
   [0x0f 0xba 0x63 0x0f 0x12] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/di 15] 0x12]}
   [0x0f 0xba 0x64 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/si] 0xf4]}
   [0x0f 0xba 0x64 0xf0 0xf4] {::i/tag ::i/bt, ::i/args [[::r/si -16] 0xf4]}
   [0x0f 0xba 0x64 0x0f 0x12] {::i/tag ::i/bt, ::i/args [[::r/si 15] 0x12]}
   [0x0f 0xba 0x65 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/di] 0xf4]}
   [0x0f 0xba 0x65 0xf0 0xf4] {::i/tag ::i/bt, ::i/args [[::r/di -16] 0xf4]}
   [0x0f 0xba 0x65 0x0f 0x12] {::i/tag ::i/bt, ::i/args [[::r/di 15] 0x12]}
   [0x0f 0xba 0x66 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp] 0xf4]}
   [0x0f 0xba 0x66 0xf0 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp -16] 0xf4]}
   [0x0f 0xba 0x66 0x0f 0x12] {::i/tag ::i/bt, ::i/args [[::r/bp 15] 0x12]}
   [0x0f 0xba 0x67 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx] 0xf4]}
   [0x0f 0xba 0x67 0xf0 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx -16] 0xf4]}
   [0x0f 0xba 0x67 0x0f 0x12] {::i/tag ::i/bt, ::i/args [[::r/bx 15] 0x12]}
   [0x0f 0xba 0x68 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/si] 0xf4]}
   [0x0f 0xba 0x68 0xf0 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/si -16] 0xf4]}
   [0x0f 0xba 0x68 0x0f 0x12] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/si 15] 0x12]}
   [0x0f 0xba 0x69 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/di] 0xf4]}
   [0x0f 0xba 0x69 0xf0 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/di -16] 0xf4]}
   [0x0f 0xba 0x69 0x0f 0x12] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/di 15] 0x12]}
   [0x0f 0xba 0x6a 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/si] 0xf4]}
   [0x0f 0xba 0x6a 0xf0 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/si -16] 0xf4]}
   [0x0f 0xba 0x6a 0x0f 0x12] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/si 15] 0x12]}
   [0x0f 0xba 0x6b 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/di] 0xf4]}
   [0x0f 0xba 0x6b 0xf0 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/di -16] 0xf4]}
   [0x0f 0xba 0x6b 0x0f 0x12] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/di 15] 0x12]}
   [0x0f 0xba 0x6c 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/si] 0xf4]}
   [0x0f 0xba 0x6c 0xf0 0xf4] {::i/tag ::i/bts, ::i/args [[::r/si -16] 0xf4]}
   [0x0f 0xba 0x6c 0x0f 0x12] {::i/tag ::i/bts, ::i/args [[::r/si 15] 0x12]}
   [0x0f 0xba 0x6d 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/di] 0xf4]}
   [0x0f 0xba 0x6d 0xf0 0xf4] {::i/tag ::i/bts, ::i/args [[::r/di -16] 0xf4]}
   [0x0f 0xba 0x6d 0x0f 0x12] {::i/tag ::i/bts, ::i/args [[::r/di 15] 0x12]}
   [0x0f 0xba 0x6e 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp] 0xf4]}
   [0x0f 0xba 0x6e 0xf0 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp -16] 0xf4]}
   [0x0f 0xba 0x6e 0x0f 0x12] {::i/tag ::i/bts, ::i/args [[::r/bp 15] 0x12]}
   [0x0f 0xba 0x6f 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx] 0xf4]}
   [0x0f 0xba 0x6f 0xf0 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx -16] 0xf4]}
   [0x0f 0xba 0x6f 0x0f 0x12] {::i/tag ::i/bts, ::i/args [[::r/bx 15] 0x12]}
   [0x0f 0xba 0x70 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/si] 0xf4]}
   [0x0f 0xba 0x70 0xf0 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/si -16] 0xf4]}
   [0x0f 0xba 0x70 0x0f 0x12] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/si 15] 0x12]}
   [0x0f 0xba 0x71 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/di] 0xf4]}
   [0x0f 0xba 0x71 0xf0 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/di -16] 0xf4]}
   [0x0f 0xba 0x71 0x0f 0x12] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/di 15] 0x12]}
   [0x0f 0xba 0x72 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/si] 0xf4]}
   [0x0f 0xba 0x72 0xf0 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/si -16] 0xf4]}
   [0x0f 0xba 0x72 0x0f 0x12] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/si 15] 0x12]}
   [0x0f 0xba 0x73 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/di] 0xf4]}
   [0x0f 0xba 0x73 0xf0 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/di -16] 0xf4]}
   [0x0f 0xba 0x73 0x0f 0x12] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/di 15] 0x12]}
   [0x0f 0xba 0x74 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/si] 0xf4]}
   [0x0f 0xba 0x74 0xf0 0xf4] {::i/tag ::i/btr, ::i/args [[::r/si -16] 0xf4]}
   [0x0f 0xba 0x74 0x0f 0x12] {::i/tag ::i/btr, ::i/args [[::r/si 15] 0x12]}
   [0x0f 0xba 0x75 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/di] 0xf4]}
   [0x0f 0xba 0x75 0xf0 0xf4] {::i/tag ::i/btr, ::i/args [[::r/di -16] 0xf4]}
   [0x0f 0xba 0x75 0x0f 0x12] {::i/tag ::i/btr, ::i/args [[::r/di 15] 0x12]}
   [0x0f 0xba 0x76 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp] 0xf4]}
   [0x0f 0xba 0x76 0xf0 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp -16] 0xf4]}
   [0x0f 0xba 0x76 0x0f 0x12] {::i/tag ::i/btr, ::i/args [[::r/bp 15] 0x12]}
   [0x0f 0xba 0x77 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx] 0xf4]}
   [0x0f 0xba 0x77 0xf0 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx -16] 0xf4]}
   [0x0f 0xba 0x77 0x0f 0x12] {::i/tag ::i/btr, ::i/args [[::r/bx 15] 0x12]}
   [0x0f 0xba 0x78 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/si] 0xf4]}
   [0x0f 0xba 0x78 0xf0 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/si -16] 0xf4]}
   [0x0f 0xba 0x78 0x0f 0x12] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/si 15] 0x12]}
   [0x0f 0xba 0x79 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/di] 0xf4]}
   [0x0f 0xba 0x79 0xf0 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/di -16] 0xf4]}
   [0x0f 0xba 0x79 0x0f 0x12] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/di 15] 0x12]}
   [0x0f 0xba 0x7a 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/si] 0xf4]}
   [0x0f 0xba 0x7a 0xf0 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/si -16] 0xf4]}
   [0x0f 0xba 0x7a 0x0f 0x12] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/si 15] 0x12]}
   [0x0f 0xba 0x7b 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/di] 0xf4]}
   [0x0f 0xba 0x7b 0xf0 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/di -16] 0xf4]}
   [0x0f 0xba 0x7b 0x0f 0x12] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/di 15] 0x12]}
   [0x0f 0xba 0x7c 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/si] 0xf4]}
   [0x0f 0xba 0x7c 0xf0 0xf4] {::i/tag ::i/btc, ::i/args [[::r/si -16] 0xf4]}
   [0x0f 0xba 0x7c 0x0f 0x12] {::i/tag ::i/btc, ::i/args [[::r/si 15] 0x12]}
   [0x0f 0xba 0x7d 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/di] 0xf4]}
   [0x0f 0xba 0x7d 0xf0 0xf4] {::i/tag ::i/btc, ::i/args [[::r/di -16] 0xf4]}
   [0x0f 0xba 0x7d 0x0f 0x12] {::i/tag ::i/btc, ::i/args [[::r/di 15] 0x12]}
   [0x0f 0xba 0x7e 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp] 0xf4]}
   [0x0f 0xba 0x7e 0xf0 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp -16] 0xf4]}
   [0x0f 0xba 0x7e 0x0f 0x12] {::i/tag ::i/btc, ::i/args [[::r/bp 15] 0x12]}
   [0x0f 0xba 0x7f 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx] 0xf4]}
   [0x0f 0xba 0x7f 0xf0 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx -16] 0xf4]}
   [0x0f 0xba 0x7f 0x0f 0x12] {::i/tag ::i/btc, ::i/args [[::r/bx 15] 0x12]}
   [0x0f 0xba 0x80 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x80 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x80 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x81 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x81 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x81 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x82 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x82 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x82 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x83 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x83 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x83 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x84 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x84 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x84 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x85 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x85 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x85 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x86 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x86 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x86 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x87 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x87 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x87 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x88 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x88 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x88 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x89 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x89 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x89 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x8a 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x8a 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x8a 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x8b 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x8b 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x8b 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x8c 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x8c 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x8c 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x8d 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x8d 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x8d 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x8e 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x8e 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x8e 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x8f 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x8f 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x8f 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x90 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x90 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x90 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x91 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x91 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x91 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x92 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x92 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x92 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x93 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x93 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x93 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x94 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x94 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x94 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x95 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x95 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x95 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x96 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x96 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x96 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x97 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x97 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x97 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x98 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x98 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x98 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x99 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x99 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x99 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x9a 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x9a 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x9a 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x9b 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x9b 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x9b 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x9c 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x9c 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x9c 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x9d 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x9d 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x9d 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x9e 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x9e 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x9e 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0x9f 0x00 0x00 0xf4] nil
   [0x0f 0xba 0x9f 0xf0 0x0f 0xf4] nil
   [0x0f 0xba 0x9f 0x0f 0xf0 0x12] nil
   [0x0f 0xba 0xa0 0x00 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/si] 0xf4]}
   [0x0f 0xba 0xa0 0xf0 0x0f 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/si 4080] 0xf4]}
   [0x0f 0xba 0xa0 0x0f 0xf0 0x12] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/si -4081] 0x12]}
   [0x0f 0xba 0xa1 0x00 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/di] 0xf4]}
   [0x0f 0xba 0xa1 0xf0 0x0f 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/di 4080] 0xf4]}
   [0x0f 0xba 0xa1 0x0f 0xf0 0x12] {::i/tag ::i/bt, ::i/args [[::r/bx ::r/di -4081] 0x12]}
   [0x0f 0xba 0xa2 0x00 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/si] 0xf4]}
   [0x0f 0xba 0xa2 0xf0 0x0f 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/si 4080] 0xf4]}
   [0x0f 0xba 0xa2 0x0f 0xf0 0x12] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/si -4081] 0x12]}
   [0x0f 0xba 0xa3 0x00 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/di] 0xf4]}
   [0x0f 0xba 0xa3 0xf0 0x0f 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/di 4080] 0xf4]}
   [0x0f 0xba 0xa3 0x0f 0xf0 0x12] {::i/tag ::i/bt, ::i/args [[::r/bp ::r/di -4081] 0x12]}
   [0x0f 0xba 0xa4 0x00 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/si] 0xf4]}
   [0x0f 0xba 0xa4 0xf0 0x0f 0xf4] {::i/tag ::i/bt, ::i/args [[::r/si 4080] 0xf4]}
   [0x0f 0xba 0xa4 0x0f 0xf0 0x12] {::i/tag ::i/bt, ::i/args [[::r/si -4081] 0x12]}
   [0x0f 0xba 0xa5 0x00 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/di] 0xf4]}
   [0x0f 0xba 0xa5 0xf0 0x0f 0xf4] {::i/tag ::i/bt, ::i/args [[::r/di 4080] 0xf4]}
   [0x0f 0xba 0xa5 0x0f 0xf0 0x12] {::i/tag ::i/bt, ::i/args [[::r/di -4081] 0x12]}
   [0x0f 0xba 0xa6 0x00 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp] 0xf4]}
   [0x0f 0xba 0xa6 0xf0 0x0f 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bp 4080] 0xf4]}
   [0x0f 0xba 0xa6 0x0f 0xf0 0x12] {::i/tag ::i/bt, ::i/args [[::r/bp -4081] 0x12]}
   [0x0f 0xba 0xa7 0x00 0x00 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx] 0xf4]}
   [0x0f 0xba 0xa7 0xf0 0x0f 0xf4] {::i/tag ::i/bt, ::i/args [[::r/bx 4080] 0xf4]}
   [0x0f 0xba 0xa7 0x0f 0xf0 0x12] {::i/tag ::i/bt, ::i/args [[::r/bx -4081] 0x12]}
   [0x0f 0xba 0xa8 0x00 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/si] 0xf4]}
   [0x0f 0xba 0xa8 0xf0 0x0f 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/si 4080] 0xf4]}
   [0x0f 0xba 0xa8 0x0f 0xf0 0x12] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/si -4081] 0x12]}
   [0x0f 0xba 0xa9 0x00 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/di] 0xf4]}
   [0x0f 0xba 0xa9 0xf0 0x0f 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/di 4080] 0xf4]}
   [0x0f 0xba 0xa9 0x0f 0xf0 0x12] {::i/tag ::i/bts, ::i/args [[::r/bx ::r/di -4081] 0x12]}
   [0x0f 0xba 0xaa 0x00 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/si] 0xf4]}
   [0x0f 0xba 0xaa 0xf0 0x0f 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/si 4080] 0xf4]}
   [0x0f 0xba 0xaa 0x0f 0xf0 0x12] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/si -4081] 0x12]}
   [0x0f 0xba 0xab 0x00 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/di] 0xf4]}
   [0x0f 0xba 0xab 0xf0 0x0f 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/di 4080] 0xf4]}
   [0x0f 0xba 0xab 0x0f 0xf0 0x12] {::i/tag ::i/bts, ::i/args [[::r/bp ::r/di -4081] 0x12]}
   [0x0f 0xba 0xac 0x00 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/si] 0xf4]}
   [0x0f 0xba 0xac 0xf0 0x0f 0xf4] {::i/tag ::i/bts, ::i/args [[::r/si 4080] 0xf4]}
   [0x0f 0xba 0xac 0x0f 0xf0 0x12] {::i/tag ::i/bts, ::i/args [[::r/si -4081] 0x12]}
   [0x0f 0xba 0xad 0x00 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/di] 0xf4]}
   [0x0f 0xba 0xad 0xf0 0x0f 0xf4] {::i/tag ::i/bts, ::i/args [[::r/di 4080] 0xf4]}
   [0x0f 0xba 0xad 0x0f 0xf0 0x12] {::i/tag ::i/bts, ::i/args [[::r/di -4081] 0x12]}
   [0x0f 0xba 0xae 0x00 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp] 0xf4]}
   [0x0f 0xba 0xae 0xf0 0x0f 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bp 4080] 0xf4]}
   [0x0f 0xba 0xae 0x0f 0xf0 0x12] {::i/tag ::i/bts, ::i/args [[::r/bp -4081] 0x12]}
   [0x0f 0xba 0xaf 0x00 0x00 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx] 0xf4]}
   [0x0f 0xba 0xaf 0xf0 0x0f 0xf4] {::i/tag ::i/bts, ::i/args [[::r/bx 4080] 0xf4]}
   [0x0f 0xba 0xaf 0x0f 0xf0 0x12] {::i/tag ::i/bts, ::i/args [[::r/bx -4081] 0x12]}
   [0x0f 0xba 0xb0 0x00 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/si] 0xf4]}
   [0x0f 0xba 0xb0 0xf0 0x0f 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/si 4080] 0xf4]}
   [0x0f 0xba 0xb0 0x0f 0xf0 0x12] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/si -4081] 0x12]}
   [0x0f 0xba 0xb1 0x00 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/di] 0xf4]}
   [0x0f 0xba 0xb1 0xf0 0x0f 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/di 4080] 0xf4]}
   [0x0f 0xba 0xb1 0x0f 0xf0 0x12] {::i/tag ::i/btr, ::i/args [[::r/bx ::r/di -4081] 0x12]}
   [0x0f 0xba 0xb2 0x00 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/si] 0xf4]}
   [0x0f 0xba 0xb2 0xf0 0x0f 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/si 4080] 0xf4]}
   [0x0f 0xba 0xb2 0x0f 0xf0 0x12] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/si -4081] 0x12]}
   [0x0f 0xba 0xb3 0x00 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/di] 0xf4]}
   [0x0f 0xba 0xb3 0xf0 0x0f 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/di 4080] 0xf4]}
   [0x0f 0xba 0xb3 0x0f 0xf0 0x12] {::i/tag ::i/btr, ::i/args [[::r/bp ::r/di -4081] 0x12]}
   [0x0f 0xba 0xb4 0x00 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/si] 0xf4]}
   [0x0f 0xba 0xb4 0xf0 0x0f 0xf4] {::i/tag ::i/btr, ::i/args [[::r/si 4080] 0xf4]}
   [0x0f 0xba 0xb4 0x0f 0xf0 0x12] {::i/tag ::i/btr, ::i/args [[::r/si -4081] 0x12]}
   [0x0f 0xba 0xb5 0x00 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/di] 0xf4]}
   [0x0f 0xba 0xb5 0xf0 0x0f 0xf4] {::i/tag ::i/btr, ::i/args [[::r/di 4080] 0xf4]}
   [0x0f 0xba 0xb5 0x0f 0xf0 0x12] {::i/tag ::i/btr, ::i/args [[::r/di -4081] 0x12]}
   [0x0f 0xba 0xb6 0x00 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp] 0xf4]}
   [0x0f 0xba 0xb6 0xf0 0x0f 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bp 4080] 0xf4]}
   [0x0f 0xba 0xb6 0x0f 0xf0 0x12] {::i/tag ::i/btr, ::i/args [[::r/bp -4081] 0x12]}
   [0x0f 0xba 0xb7 0x00 0x00 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx] 0xf4]}
   [0x0f 0xba 0xb7 0xf0 0x0f 0xf4] {::i/tag ::i/btr, ::i/args [[::r/bx 4080] 0xf4]}
   [0x0f 0xba 0xb7 0x0f 0xf0 0x12] {::i/tag ::i/btr, ::i/args [[::r/bx -4081] 0x12]}
   [0x0f 0xba 0xb8 0x00 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/si] 0xf4]}
   [0x0f 0xba 0xb8 0xf0 0x0f 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/si 4080] 0xf4]}
   [0x0f 0xba 0xb8 0x0f 0xf0 0x12] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/si -4081] 0x12]}
   [0x0f 0xba 0xb9 0x00 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/di] 0xf4]}
   [0x0f 0xba 0xb9 0xf0 0x0f 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/di 4080] 0xf4]}
   [0x0f 0xba 0xb9 0x0f 0xf0 0x12] {::i/tag ::i/btc, ::i/args [[::r/bx ::r/di -4081] 0x12]}
   [0x0f 0xba 0xba 0x00 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/si] 0xf4]}
   [0x0f 0xba 0xba 0xf0 0x0f 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/si 4080] 0xf4]}
   [0x0f 0xba 0xba 0x0f 0xf0 0x12] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/si -4081] 0x12]}
   [0x0f 0xba 0xbb 0x00 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/di] 0xf4]}
   [0x0f 0xba 0xbb 0xf0 0x0f 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/di 4080] 0xf4]}
   [0x0f 0xba 0xbb 0x0f 0xf0 0x12] {::i/tag ::i/btc, ::i/args [[::r/bp ::r/di -4081] 0x12]}
   [0x0f 0xba 0xbc 0x00 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/si] 0xf4]}
   [0x0f 0xba 0xbc 0xf0 0x0f 0xf4] {::i/tag ::i/btc, ::i/args [[::r/si 4080] 0xf4]}
   [0x0f 0xba 0xbc 0x0f 0xf0 0x12] {::i/tag ::i/btc, ::i/args [[::r/si -4081] 0x12]}
   [0x0f 0xba 0xbd 0x00 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/di] 0xf4]}
   [0x0f 0xba 0xbd 0xf0 0x0f 0xf4] {::i/tag ::i/btc, ::i/args [[::r/di 4080] 0xf4]}
   [0x0f 0xba 0xbd 0x0f 0xf0 0x12] {::i/tag ::i/btc, ::i/args [[::r/di -4081] 0x12]}
   [0x0f 0xba 0xbe 0x00 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp] 0xf4]}
   [0x0f 0xba 0xbe 0xf0 0x0f 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bp 4080] 0xf4]}
   [0x0f 0xba 0xbe 0x0f 0xf0 0x12] {::i/tag ::i/btc, ::i/args [[::r/bp -4081] 0x12]}
   [0x0f 0xba 0xbf 0x00 0x00 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx] 0xf4]}
   [0x0f 0xba 0xbf 0xf0 0x0f 0xf4] {::i/tag ::i/btc, ::i/args [[::r/bx 4080] 0xf4]}
   [0x0f 0xba 0xbf 0x0f 0xf0 0x12] {::i/tag ::i/btc, ::i/args [[::r/bx -4081] 0x12]}
   [0x0f 0xba 0xc0 0xf0] nil
   [0x0f 0xba 0xc0 0x0f] nil
   [0x0f 0xba 0xc1 0xf0] nil
   [0x0f 0xba 0xc1 0x0f] nil
   [0x0f 0xba 0xc2 0xf0] nil
   [0x0f 0xba 0xc2 0x0f] nil
   [0x0f 0xba 0xc3 0xf0] nil
   [0x0f 0xba 0xc3 0x0f] nil
   [0x0f 0xba 0xc4 0xf0] nil
   [0x0f 0xba 0xc4 0x0f] nil
   [0x0f 0xba 0xc5 0xf0] nil
   [0x0f 0xba 0xc5 0x0f] nil
   [0x0f 0xba 0xc6 0xf0] nil
   [0x0f 0xba 0xc6 0x0f] nil
   [0x0f 0xba 0xc7 0xf0] nil
   [0x0f 0xba 0xc7 0x0f] nil
   [0x0f 0xba 0xc8 0xf0] nil
   [0x0f 0xba 0xc8 0x0f] nil
   [0x0f 0xba 0xc9 0xf0] nil
   [0x0f 0xba 0xc9 0x0f] nil
   [0x0f 0xba 0xca 0xf0] nil
   [0x0f 0xba 0xca 0x0f] nil
   [0x0f 0xba 0xcb 0xf0] nil
   [0x0f 0xba 0xcb 0x0f] nil
   [0x0f 0xba 0xcc 0xf0] nil
   [0x0f 0xba 0xcc 0x0f] nil
   [0x0f 0xba 0xcd 0xf0] nil
   [0x0f 0xba 0xcd 0x0f] nil
   [0x0f 0xba 0xce 0xf0] nil
   [0x0f 0xba 0xce 0x0f] nil
   [0x0f 0xba 0xcf 0xf0] nil
   [0x0f 0xba 0xcf 0x0f] nil
   [0x0f 0xba 0xd0 0xf0] nil
   [0x0f 0xba 0xd0 0x0f] nil
   [0x0f 0xba 0xd1 0xf0] nil
   [0x0f 0xba 0xd1 0x0f] nil
   [0x0f 0xba 0xd2 0xf0] nil
   [0x0f 0xba 0xd2 0x0f] nil
   [0x0f 0xba 0xd3 0xf0] nil
   [0x0f 0xba 0xd3 0x0f] nil
   [0x0f 0xba 0xd4 0xf0] nil
   [0x0f 0xba 0xd4 0x0f] nil
   [0x0f 0xba 0xd5 0xf0] nil
   [0x0f 0xba 0xd5 0x0f] nil
   [0x0f 0xba 0xd6 0xf0] nil
   [0x0f 0xba 0xd6 0x0f] nil
   [0x0f 0xba 0xd7 0xf0] nil
   [0x0f 0xba 0xd7 0x0f] nil
   [0x0f 0xba 0xd8 0xf0] nil
   [0x0f 0xba 0xd8 0x0f] nil
   [0x0f 0xba 0xd9 0xf0] nil
   [0x0f 0xba 0xd9 0x0f] nil
   [0x0f 0xba 0xda 0xf0] nil
   [0x0f 0xba 0xda 0x0f] nil
   [0x0f 0xba 0xdb 0xf0] nil
   [0x0f 0xba 0xdb 0x0f] nil
   [0x0f 0xba 0xdc 0xf0] nil
   [0x0f 0xba 0xdc 0x0f] nil
   [0x0f 0xba 0xdd 0xf0] nil
   [0x0f 0xba 0xdd 0x0f] nil
   [0x0f 0xba 0xde 0xf0] nil
   [0x0f 0xba 0xde 0x0f] nil
   [0x0f 0xba 0xdf 0xf0] nil
   [0x0f 0xba 0xdf 0x0f] nil
   [0x0f 0xba 0xe0 0xf0] {::i/tag ::i/bt, ::i/args [::r/ax 0xf0]}
   [0x0f 0xba 0xe0 0x0f] {::i/tag ::i/bt, ::i/args [::r/ax 0x0f]}
   [0x0f 0xba 0xe1 0xf0] {::i/tag ::i/bt, ::i/args [::r/cx 0xf0]}
   [0x0f 0xba 0xe1 0x0f] {::i/tag ::i/bt, ::i/args [::r/cx 0x0f]}
   [0x0f 0xba 0xe2 0xf0] {::i/tag ::i/bt, ::i/args [::r/dx 0xf0]}
   [0x0f 0xba 0xe2 0x0f] {::i/tag ::i/bt, ::i/args [::r/dx 0x0f]}
   [0x0f 0xba 0xe3 0xf0] {::i/tag ::i/bt, ::i/args [::r/bx 0xf0]}
   [0x0f 0xba 0xe3 0x0f] {::i/tag ::i/bt, ::i/args [::r/bx 0x0f]}
   [0x0f 0xba 0xe4 0xf0] {::i/tag ::i/bt, ::i/args [::r/sp 0xf0]}
   [0x0f 0xba 0xe4 0x0f] {::i/tag ::i/bt, ::i/args [::r/sp 0x0f]}
   [0x0f 0xba 0xe5 0xf0] {::i/tag ::i/bt, ::i/args [::r/bp 0xf0]}
   [0x0f 0xba 0xe5 0x0f] {::i/tag ::i/bt, ::i/args [::r/bp 0x0f]}
   [0x0f 0xba 0xe6 0xf0] {::i/tag ::i/bt, ::i/args [::r/si 0xf0]}
   [0x0f 0xba 0xe6 0x0f] {::i/tag ::i/bt, ::i/args [::r/si 0x0f]}
   [0x0f 0xba 0xe7 0xf0] {::i/tag ::i/bt, ::i/args [::r/di 0xf0]}
   [0x0f 0xba 0xe7 0x0f] {::i/tag ::i/bt, ::i/args [::r/di 0x0f]}
   [0x0f 0xba 0xe8 0xf0] {::i/tag ::i/bts, ::i/args [::r/ax 0xf0]}
   [0x0f 0xba 0xe8 0x0f] {::i/tag ::i/bts, ::i/args [::r/ax 0x0f]}
   [0x0f 0xba 0xe9 0xf0] {::i/tag ::i/bts, ::i/args [::r/cx 0xf0]}
   [0x0f 0xba 0xe9 0x0f] {::i/tag ::i/bts, ::i/args [::r/cx 0x0f]}
   [0x0f 0xba 0xea 0xf0] {::i/tag ::i/bts, ::i/args [::r/dx 0xf0]}
   [0x0f 0xba 0xea 0x0f] {::i/tag ::i/bts, ::i/args [::r/dx 0x0f]}
   [0x0f 0xba 0xeb 0xf0] {::i/tag ::i/bts, ::i/args [::r/bx 0xf0]}
   [0x0f 0xba 0xeb 0x0f] {::i/tag ::i/bts, ::i/args [::r/bx 0x0f]}
   [0x0f 0xba 0xec 0xf0] {::i/tag ::i/bts, ::i/args [::r/sp 0xf0]}
   [0x0f 0xba 0xec 0x0f] {::i/tag ::i/bts, ::i/args [::r/sp 0x0f]}
   [0x0f 0xba 0xed 0xf0] {::i/tag ::i/bts, ::i/args [::r/bp 0xf0]}
   [0x0f 0xba 0xed 0x0f] {::i/tag ::i/bts, ::i/args [::r/bp 0x0f]}
   [0x0f 0xba 0xee 0xf0] {::i/tag ::i/bts, ::i/args [::r/si 0xf0]}
   [0x0f 0xba 0xee 0x0f] {::i/tag ::i/bts, ::i/args [::r/si 0x0f]}
   [0x0f 0xba 0xef 0xf0] {::i/tag ::i/bts, ::i/args [::r/di 0xf0]}
   [0x0f 0xba 0xef 0x0f] {::i/tag ::i/bts, ::i/args [::r/di 0x0f]}
   [0x0f 0xba 0xf0 0xf0] {::i/tag ::i/btr, ::i/args [::r/ax 0xf0]}
   [0x0f 0xba 0xf0 0x0f] {::i/tag ::i/btr, ::i/args [::r/ax 0x0f]}
   [0x0f 0xba 0xf1 0xf0] {::i/tag ::i/btr, ::i/args [::r/cx 0xf0]}
   [0x0f 0xba 0xf1 0x0f] {::i/tag ::i/btr, ::i/args [::r/cx 0x0f]}
   [0x0f 0xba 0xf2 0xf0] {::i/tag ::i/btr, ::i/args [::r/dx 0xf0]}
   [0x0f 0xba 0xf2 0x0f] {::i/tag ::i/btr, ::i/args [::r/dx 0x0f]}
   [0x0f 0xba 0xf3 0xf0] {::i/tag ::i/btr, ::i/args [::r/bx 0xf0]}
   [0x0f 0xba 0xf3 0x0f] {::i/tag ::i/btr, ::i/args [::r/bx 0x0f]}
   [0x0f 0xba 0xf4 0xf0] {::i/tag ::i/btr, ::i/args [::r/sp 0xf0]}
   [0x0f 0xba 0xf4 0x0f] {::i/tag ::i/btr, ::i/args [::r/sp 0x0f]}
   [0x0f 0xba 0xf5 0xf0] {::i/tag ::i/btr, ::i/args [::r/bp 0xf0]}
   [0x0f 0xba 0xf5 0x0f] {::i/tag ::i/btr, ::i/args [::r/bp 0x0f]}
   [0x0f 0xba 0xf6 0xf0] {::i/tag ::i/btr, ::i/args [::r/si 0xf0]}
   [0x0f 0xba 0xf6 0x0f] {::i/tag ::i/btr, ::i/args [::r/si 0x0f]}
   [0x0f 0xba 0xf7 0xf0] {::i/tag ::i/btr, ::i/args [::r/di 0xf0]}
   [0x0f 0xba 0xf7 0x0f] {::i/tag ::i/btr, ::i/args [::r/di 0x0f]}
   [0x0f 0xba 0xf8 0xf0] {::i/tag ::i/btc, ::i/args [::r/ax 0xf0]}
   [0x0f 0xba 0xf8 0x0f] {::i/tag ::i/btc, ::i/args [::r/ax 0x0f]}
   [0x0f 0xba 0xf9 0xf0] {::i/tag ::i/btc, ::i/args [::r/cx 0xf0]}
   [0x0f 0xba 0xf9 0x0f] {::i/tag ::i/btc, ::i/args [::r/cx 0x0f]}
   [0x0f 0xba 0xfa 0xf0] {::i/tag ::i/btc, ::i/args [::r/dx 0xf0]}
   [0x0f 0xba 0xfa 0x0f] {::i/tag ::i/btc, ::i/args [::r/dx 0x0f]}
   [0x0f 0xba 0xfb 0xf0] {::i/tag ::i/btc, ::i/args [::r/bx 0xf0]}
   [0x0f 0xba 0xfb 0x0f] {::i/tag ::i/btc, ::i/args [::r/bx 0x0f]}
   [0x0f 0xba 0xfc 0xf0] {::i/tag ::i/btc, ::i/args [::r/sp 0xf0]}
   [0x0f 0xba 0xfc 0x0f] {::i/tag ::i/btc, ::i/args [::r/sp 0x0f]}
   [0x0f 0xba 0xfd 0xf0] {::i/tag ::i/btc, ::i/args [::r/bp 0xf0]}
   [0x0f 0xba 0xfd 0x0f] {::i/tag ::i/btc, ::i/args [::r/bp 0x0f]}
   [0x0f 0xba 0xfe 0xf0] {::i/tag ::i/btc, ::i/args [::r/si 0xf0]}
   [0x0f 0xba 0xfe 0x0f] {::i/tag ::i/btc, ::i/args [::r/si 0x0f]}
   [0x0f 0xba 0xff 0xf0] {::i/tag ::i/btc, ::i/args [::r/di 0xf0]}
   [0x0f 0xba 0xff 0x0f] {::i/tag ::i/btc, ::i/args [::r/di 0x0f]}})
