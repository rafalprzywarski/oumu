(ns oumu.cpu.instructions-test.arpl-examples
    (:require [oumu.cpu.instructions :as i]
              [oumu.cpu.registers :as r]))


(def decode-arpl-examples
  {[0x63 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/ax]}
   [0x63 0x01] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/ax]}
   [0x63 0x02] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/ax]}
   [0x63 0x03] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/ax]}
   [0x63 0x04] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/ax]}
   [0x63 0x05] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/ax]}
   [0x63 0x06 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[0x0ff0] ::r/ax]}
   [0x63 0x06 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[0xf00f] ::r/ax]}
   [0x63 0x07] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/ax]}
   [0x63 0x08] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/cx]}
   [0x63 0x09] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/cx]}
   [0x63 0x0a] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/cx]}
   [0x63 0x0b] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/cx]}
   [0x63 0x0c] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/cx]}
   [0x63 0x0d] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/cx]}
   [0x63 0x0e 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[0x0ff0] ::r/cx]}
   [0x63 0x0e 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[0xf00f] ::r/cx]}
   [0x63 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/cx]}
   [0x63 0x10] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/dx]}
   [0x63 0x11] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/dx]}
   [0x63 0x12] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/dx]}
   [0x63 0x13] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/dx]}
   [0x63 0x14] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/dx]}
   [0x63 0x15] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/dx]}
   [0x63 0x16 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[0x0ff0] ::r/dx]}
   [0x63 0x16 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[0xf00f] ::r/dx]}
   [0x63 0x17] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/dx]}
   [0x63 0x18] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/bx]}
   [0x63 0x19] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/bx]}
   [0x63 0x1a] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/bx]}
   [0x63 0x1b] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/bx]}
   [0x63 0x1c] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/bx]}
   [0x63 0x1d] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/bx]}
   [0x63 0x1e 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[0x0ff0] ::r/bx]}
   [0x63 0x1e 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[0xf00f] ::r/bx]}
   [0x63 0x1f] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/bx]}
   [0x63 0x20] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/sp]}
   [0x63 0x21] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/sp]}
   [0x63 0x22] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/sp]}
   [0x63 0x23] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/sp]}
   [0x63 0x24] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/sp]}
   [0x63 0x25] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/sp]}
   [0x63 0x26 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[0x0ff0] ::r/sp]}
   [0x63 0x26 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[0xf00f] ::r/sp]}
   [0x63 0x27] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/sp]}
   [0x63 0x28] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/bp]}
   [0x63 0x29] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/bp]}
   [0x63 0x2a] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/bp]}
   [0x63 0x2b] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/bp]}
   [0x63 0x2c] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/bp]}
   [0x63 0x2d] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/bp]}
   [0x63 0x2e 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[0x0ff0] ::r/bp]}
   [0x63 0x2e 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[0xf00f] ::r/bp]}
   [0x63 0x2f] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/bp]}
   [0x63 0x30] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/si]}
   [0x63 0x31] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/si]}
   [0x63 0x32] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/si]}
   [0x63 0x33] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/si]}
   [0x63 0x34] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/si]}
   [0x63 0x35] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/si]}
   [0x63 0x36 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[0x0ff0] ::r/si]}
   [0x63 0x36 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[0xf00f] ::r/si]}
   [0x63 0x37] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/si]}
   [0x63 0x38] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/di]}
   [0x63 0x39] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/di]}
   [0x63 0x3a] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/di]}
   [0x63 0x3b] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/di]}
   [0x63 0x3c] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/di]}
   [0x63 0x3d] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/di]}
   [0x63 0x3e 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[0x0ff0] ::r/di]}
   [0x63 0x3e 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[0xf00f] ::r/di]}
   [0x63 0x3f] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/di]}
   [0x63 0x40 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/ax]}
   [0x63 0x40 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -16] ::r/ax]}
   [0x63 0x40 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 15] ::r/ax]}
   [0x63 0x41 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/ax]}
   [0x63 0x41 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -16] ::r/ax]}
   [0x63 0x41 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 15] ::r/ax]}
   [0x63 0x42 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/ax]}
   [0x63 0x42 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -16] ::r/ax]}
   [0x63 0x42 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 15] ::r/ax]}
   [0x63 0x43 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/ax]}
   [0x63 0x43 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -16] ::r/ax]}
   [0x63 0x43 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 15] ::r/ax]}
   [0x63 0x44 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/ax]}
   [0x63 0x44 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -16] ::r/ax]}
   [0x63 0x44 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 15] ::r/ax]}
   [0x63 0x45 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/ax]}
   [0x63 0x45 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -16] ::r/ax]}
   [0x63 0x45 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 15] ::r/ax]}
   [0x63 0x46 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/ax]}
   [0x63 0x46 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -16] ::r/ax]}
   [0x63 0x46 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 15] ::r/ax]}
   [0x63 0x47 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/ax]}
   [0x63 0x47 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -16] ::r/ax]}
   [0x63 0x47 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 15] ::r/ax]}
   [0x63 0x48 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/cx]}
   [0x63 0x48 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -16] ::r/cx]}
   [0x63 0x48 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 15] ::r/cx]}
   [0x63 0x49 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/cx]}
   [0x63 0x49 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -16] ::r/cx]}
   [0x63 0x49 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 15] ::r/cx]}
   [0x63 0x4a 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/cx]}
   [0x63 0x4a 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -16] ::r/cx]}
   [0x63 0x4a 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 15] ::r/cx]}
   [0x63 0x4b 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/cx]}
   [0x63 0x4b 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -16] ::r/cx]}
   [0x63 0x4b 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 15] ::r/cx]}
   [0x63 0x4c 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/cx]}
   [0x63 0x4c 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -16] ::r/cx]}
   [0x63 0x4c 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 15] ::r/cx]}
   [0x63 0x4d 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/cx]}
   [0x63 0x4d 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -16] ::r/cx]}
   [0x63 0x4d 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 15] ::r/cx]}
   [0x63 0x4e 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/cx]}
   [0x63 0x4e 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -16] ::r/cx]}
   [0x63 0x4e 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 15] ::r/cx]}
   [0x63 0x4f 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/cx]}
   [0x63 0x4f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -16] ::r/cx]}
   [0x63 0x4f 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 15] ::r/cx]}
   [0x63 0x50 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/dx]}
   [0x63 0x50 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -16] ::r/dx]}
   [0x63 0x50 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 15] ::r/dx]}
   [0x63 0x51 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/dx]}
   [0x63 0x51 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -16] ::r/dx]}
   [0x63 0x51 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 15] ::r/dx]}
   [0x63 0x52 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/dx]}
   [0x63 0x52 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -16] ::r/dx]}
   [0x63 0x52 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 15] ::r/dx]}
   [0x63 0x53 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/dx]}
   [0x63 0x53 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -16] ::r/dx]}
   [0x63 0x53 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 15] ::r/dx]}
   [0x63 0x54 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/dx]}
   [0x63 0x54 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -16] ::r/dx]}
   [0x63 0x54 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 15] ::r/dx]}
   [0x63 0x55 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/dx]}
   [0x63 0x55 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -16] ::r/dx]}
   [0x63 0x55 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 15] ::r/dx]}
   [0x63 0x56 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/dx]}
   [0x63 0x56 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -16] ::r/dx]}
   [0x63 0x56 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 15] ::r/dx]}
   [0x63 0x57 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/dx]}
   [0x63 0x57 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -16] ::r/dx]}
   [0x63 0x57 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 15] ::r/dx]}
   [0x63 0x58 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/bx]}
   [0x63 0x58 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -16] ::r/bx]}
   [0x63 0x58 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 15] ::r/bx]}
   [0x63 0x59 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/bx]}
   [0x63 0x59 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -16] ::r/bx]}
   [0x63 0x59 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 15] ::r/bx]}
   [0x63 0x5a 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/bx]}
   [0x63 0x5a 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -16] ::r/bx]}
   [0x63 0x5a 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 15] ::r/bx]}
   [0x63 0x5b 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/bx]}
   [0x63 0x5b 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -16] ::r/bx]}
   [0x63 0x5b 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 15] ::r/bx]}
   [0x63 0x5c 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/bx]}
   [0x63 0x5c 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -16] ::r/bx]}
   [0x63 0x5c 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 15] ::r/bx]}
   [0x63 0x5d 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/bx]}
   [0x63 0x5d 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -16] ::r/bx]}
   [0x63 0x5d 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 15] ::r/bx]}
   [0x63 0x5e 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/bx]}
   [0x63 0x5e 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -16] ::r/bx]}
   [0x63 0x5e 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 15] ::r/bx]}
   [0x63 0x5f 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/bx]}
   [0x63 0x5f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -16] ::r/bx]}
   [0x63 0x5f 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 15] ::r/bx]}
   [0x63 0x60 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/sp]}
   [0x63 0x60 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -16] ::r/sp]}
   [0x63 0x60 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 15] ::r/sp]}
   [0x63 0x61 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/sp]}
   [0x63 0x61 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -16] ::r/sp]}
   [0x63 0x61 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 15] ::r/sp]}
   [0x63 0x62 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/sp]}
   [0x63 0x62 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -16] ::r/sp]}
   [0x63 0x62 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 15] ::r/sp]}
   [0x63 0x63 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/sp]}
   [0x63 0x63 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -16] ::r/sp]}
   [0x63 0x63 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 15] ::r/sp]}
   [0x63 0x64 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/sp]}
   [0x63 0x64 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -16] ::r/sp]}
   [0x63 0x64 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 15] ::r/sp]}
   [0x63 0x65 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/sp]}
   [0x63 0x65 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -16] ::r/sp]}
   [0x63 0x65 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 15] ::r/sp]}
   [0x63 0x66 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/sp]}
   [0x63 0x66 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -16] ::r/sp]}
   [0x63 0x66 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 15] ::r/sp]}
   [0x63 0x67 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/sp]}
   [0x63 0x67 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -16] ::r/sp]}
   [0x63 0x67 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 15] ::r/sp]}
   [0x63 0x68 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/bp]}
   [0x63 0x68 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -16] ::r/bp]}
   [0x63 0x68 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 15] ::r/bp]}
   [0x63 0x69 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/bp]}
   [0x63 0x69 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -16] ::r/bp]}
   [0x63 0x69 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 15] ::r/bp]}
   [0x63 0x6a 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/bp]}
   [0x63 0x6a 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -16] ::r/bp]}
   [0x63 0x6a 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 15] ::r/bp]}
   [0x63 0x6b 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/bp]}
   [0x63 0x6b 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -16] ::r/bp]}
   [0x63 0x6b 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 15] ::r/bp]}
   [0x63 0x6c 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/bp]}
   [0x63 0x6c 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -16] ::r/bp]}
   [0x63 0x6c 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 15] ::r/bp]}
   [0x63 0x6d 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/bp]}
   [0x63 0x6d 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -16] ::r/bp]}
   [0x63 0x6d 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 15] ::r/bp]}
   [0x63 0x6e 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/bp]}
   [0x63 0x6e 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -16] ::r/bp]}
   [0x63 0x6e 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 15] ::r/bp]}
   [0x63 0x6f 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/bp]}
   [0x63 0x6f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -16] ::r/bp]}
   [0x63 0x6f 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 15] ::r/bp]}
   [0x63 0x70 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/si]}
   [0x63 0x70 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -16] ::r/si]}
   [0x63 0x70 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 15] ::r/si]}
   [0x63 0x71 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/si]}
   [0x63 0x71 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -16] ::r/si]}
   [0x63 0x71 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 15] ::r/si]}
   [0x63 0x72 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/si]}
   [0x63 0x72 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -16] ::r/si]}
   [0x63 0x72 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 15] ::r/si]}
   [0x63 0x73 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/si]}
   [0x63 0x73 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -16] ::r/si]}
   [0x63 0x73 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 15] ::r/si]}
   [0x63 0x74 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/si]}
   [0x63 0x74 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -16] ::r/si]}
   [0x63 0x74 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 15] ::r/si]}
   [0x63 0x75 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/si]}
   [0x63 0x75 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -16] ::r/si]}
   [0x63 0x75 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 15] ::r/si]}
   [0x63 0x76 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/si]}
   [0x63 0x76 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -16] ::r/si]}
   [0x63 0x76 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 15] ::r/si]}
   [0x63 0x77 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/si]}
   [0x63 0x77 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -16] ::r/si]}
   [0x63 0x77 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 15] ::r/si]}
   [0x63 0x78 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/di]}
   [0x63 0x78 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -16] ::r/di]}
   [0x63 0x78 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 15] ::r/di]}
   [0x63 0x79 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/di]}
   [0x63 0x79 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -16] ::r/di]}
   [0x63 0x79 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 15] ::r/di]}
   [0x63 0x7a 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/di]}
   [0x63 0x7a 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -16] ::r/di]}
   [0x63 0x7a 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 15] ::r/di]}
   [0x63 0x7b 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/di]}
   [0x63 0x7b 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -16] ::r/di]}
   [0x63 0x7b 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 15] ::r/di]}
   [0x63 0x7c 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/di]}
   [0x63 0x7c 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -16] ::r/di]}
   [0x63 0x7c 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 15] ::r/di]}
   [0x63 0x7d 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/di]}
   [0x63 0x7d 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -16] ::r/di]}
   [0x63 0x7d 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 15] ::r/di]}
   [0x63 0x7e 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/di]}
   [0x63 0x7e 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -16] ::r/di]}
   [0x63 0x7e 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 15] ::r/di]}
   [0x63 0x7f 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/di]}
   [0x63 0x7f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -16] ::r/di]}
   [0x63 0x7f 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 15] ::r/di]}
   [0x63 0x80 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/ax]}
   [0x63 0x80 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 4080] ::r/ax]}
   [0x63 0x80 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -4081] ::r/ax]}
   [0x63 0x81 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/ax]}
   [0x63 0x81 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 4080] ::r/ax]}
   [0x63 0x81 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -4081] ::r/ax]}
   [0x63 0x82 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/ax]}
   [0x63 0x82 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 4080] ::r/ax]}
   [0x63 0x82 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -4081] ::r/ax]}
   [0x63 0x83 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/ax]}
   [0x63 0x83 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 4080] ::r/ax]}
   [0x63 0x83 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -4081] ::r/ax]}
   [0x63 0x84 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/ax]}
   [0x63 0x84 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 4080] ::r/ax]}
   [0x63 0x84 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -4081] ::r/ax]}
   [0x63 0x85 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/ax]}
   [0x63 0x85 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 4080] ::r/ax]}
   [0x63 0x85 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -4081] ::r/ax]}
   [0x63 0x86 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/ax]}
   [0x63 0x86 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 4080] ::r/ax]}
   [0x63 0x86 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -4081] ::r/ax]}
   [0x63 0x87 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/ax]}
   [0x63 0x87 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 4080] ::r/ax]}
   [0x63 0x87 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -4081] ::r/ax]}
   [0x63 0x88 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/cx]}
   [0x63 0x88 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 4080] ::r/cx]}
   [0x63 0x88 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -4081] ::r/cx]}
   [0x63 0x89 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/cx]}
   [0x63 0x89 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 4080] ::r/cx]}
   [0x63 0x89 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -4081] ::r/cx]}
   [0x63 0x8a 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/cx]}
   [0x63 0x8a 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 4080] ::r/cx]}
   [0x63 0x8a 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -4081] ::r/cx]}
   [0x63 0x8b 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/cx]}
   [0x63 0x8b 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 4080] ::r/cx]}
   [0x63 0x8b 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -4081] ::r/cx]}
   [0x63 0x8c 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/cx]}
   [0x63 0x8c 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 4080] ::r/cx]}
   [0x63 0x8c 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -4081] ::r/cx]}
   [0x63 0x8d 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/cx]}
   [0x63 0x8d 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 4080] ::r/cx]}
   [0x63 0x8d 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -4081] ::r/cx]}
   [0x63 0x8e 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/cx]}
   [0x63 0x8e 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 4080] ::r/cx]}
   [0x63 0x8e 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -4081] ::r/cx]}
   [0x63 0x8f 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/cx]}
   [0x63 0x8f 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 4080] ::r/cx]}
   [0x63 0x8f 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -4081] ::r/cx]}
   [0x63 0x90 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/dx]}
   [0x63 0x90 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 4080] ::r/dx]}
   [0x63 0x90 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -4081] ::r/dx]}
   [0x63 0x91 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/dx]}
   [0x63 0x91 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 4080] ::r/dx]}
   [0x63 0x91 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -4081] ::r/dx]}
   [0x63 0x92 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/dx]}
   [0x63 0x92 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 4080] ::r/dx]}
   [0x63 0x92 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -4081] ::r/dx]}
   [0x63 0x93 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/dx]}
   [0x63 0x93 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 4080] ::r/dx]}
   [0x63 0x93 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -4081] ::r/dx]}
   [0x63 0x94 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/dx]}
   [0x63 0x94 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 4080] ::r/dx]}
   [0x63 0x94 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -4081] ::r/dx]}
   [0x63 0x95 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/dx]}
   [0x63 0x95 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 4080] ::r/dx]}
   [0x63 0x95 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -4081] ::r/dx]}
   [0x63 0x96 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/dx]}
   [0x63 0x96 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 4080] ::r/dx]}
   [0x63 0x96 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -4081] ::r/dx]}
   [0x63 0x97 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/dx]}
   [0x63 0x97 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 4080] ::r/dx]}
   [0x63 0x97 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -4081] ::r/dx]}
   [0x63 0x98 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/bx]}
   [0x63 0x98 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 4080] ::r/bx]}
   [0x63 0x98 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -4081] ::r/bx]}
   [0x63 0x99 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/bx]}
   [0x63 0x99 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 4080] ::r/bx]}
   [0x63 0x99 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -4081] ::r/bx]}
   [0x63 0x9a 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/bx]}
   [0x63 0x9a 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 4080] ::r/bx]}
   [0x63 0x9a 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -4081] ::r/bx]}
   [0x63 0x9b 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/bx]}
   [0x63 0x9b 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 4080] ::r/bx]}
   [0x63 0x9b 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -4081] ::r/bx]}
   [0x63 0x9c 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/bx]}
   [0x63 0x9c 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 4080] ::r/bx]}
   [0x63 0x9c 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -4081] ::r/bx]}
   [0x63 0x9d 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/bx]}
   [0x63 0x9d 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 4080] ::r/bx]}
   [0x63 0x9d 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -4081] ::r/bx]}
   [0x63 0x9e 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/bx]}
   [0x63 0x9e 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 4080] ::r/bx]}
   [0x63 0x9e 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -4081] ::r/bx]}
   [0x63 0x9f 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/bx]}
   [0x63 0x9f 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 4080] ::r/bx]}
   [0x63 0x9f 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -4081] ::r/bx]}
   [0x63 0xa0 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/sp]}
   [0x63 0xa0 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 4080] ::r/sp]}
   [0x63 0xa0 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -4081] ::r/sp]}
   [0x63 0xa1 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/sp]}
   [0x63 0xa1 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 4080] ::r/sp]}
   [0x63 0xa1 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -4081] ::r/sp]}
   [0x63 0xa2 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/sp]}
   [0x63 0xa2 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 4080] ::r/sp]}
   [0x63 0xa2 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -4081] ::r/sp]}
   [0x63 0xa3 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/sp]}
   [0x63 0xa3 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 4080] ::r/sp]}
   [0x63 0xa3 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -4081] ::r/sp]}
   [0x63 0xa4 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/sp]}
   [0x63 0xa4 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 4080] ::r/sp]}
   [0x63 0xa4 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -4081] ::r/sp]}
   [0x63 0xa5 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/sp]}
   [0x63 0xa5 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 4080] ::r/sp]}
   [0x63 0xa5 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -4081] ::r/sp]}
   [0x63 0xa6 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/sp]}
   [0x63 0xa6 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 4080] ::r/sp]}
   [0x63 0xa6 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -4081] ::r/sp]}
   [0x63 0xa7 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/sp]}
   [0x63 0xa7 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 4080] ::r/sp]}
   [0x63 0xa7 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -4081] ::r/sp]}
   [0x63 0xa8 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/bp]}
   [0x63 0xa8 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 4080] ::r/bp]}
   [0x63 0xa8 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -4081] ::r/bp]}
   [0x63 0xa9 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/bp]}
   [0x63 0xa9 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 4080] ::r/bp]}
   [0x63 0xa9 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -4081] ::r/bp]}
   [0x63 0xaa 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/bp]}
   [0x63 0xaa 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 4080] ::r/bp]}
   [0x63 0xaa 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -4081] ::r/bp]}
   [0x63 0xab 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/bp]}
   [0x63 0xab 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 4080] ::r/bp]}
   [0x63 0xab 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -4081] ::r/bp]}
   [0x63 0xac 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/bp]}
   [0x63 0xac 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 4080] ::r/bp]}
   [0x63 0xac 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -4081] ::r/bp]}
   [0x63 0xad 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/bp]}
   [0x63 0xad 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 4080] ::r/bp]}
   [0x63 0xad 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -4081] ::r/bp]}
   [0x63 0xae 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/bp]}
   [0x63 0xae 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 4080] ::r/bp]}
   [0x63 0xae 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -4081] ::r/bp]}
   [0x63 0xaf 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/bp]}
   [0x63 0xaf 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 4080] ::r/bp]}
   [0x63 0xaf 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -4081] ::r/bp]}
   [0x63 0xb0 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/si]}
   [0x63 0xb0 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 4080] ::r/si]}
   [0x63 0xb0 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -4081] ::r/si]}
   [0x63 0xb1 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/si]}
   [0x63 0xb1 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 4080] ::r/si]}
   [0x63 0xb1 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -4081] ::r/si]}
   [0x63 0xb2 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/si]}
   [0x63 0xb2 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 4080] ::r/si]}
   [0x63 0xb2 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -4081] ::r/si]}
   [0x63 0xb3 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/si]}
   [0x63 0xb3 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 4080] ::r/si]}
   [0x63 0xb3 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -4081] ::r/si]}
   [0x63 0xb4 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/si]}
   [0x63 0xb4 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 4080] ::r/si]}
   [0x63 0xb4 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -4081] ::r/si]}
   [0x63 0xb5 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/si]}
   [0x63 0xb5 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 4080] ::r/si]}
   [0x63 0xb5 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -4081] ::r/si]}
   [0x63 0xb6 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/si]}
   [0x63 0xb6 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 4080] ::r/si]}
   [0x63 0xb6 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -4081] ::r/si]}
   [0x63 0xb7 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/si]}
   [0x63 0xb7 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 4080] ::r/si]}
   [0x63 0xb7 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -4081] ::r/si]}
   [0x63 0xb8 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si] ::r/di]}
   [0x63 0xb8 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si 4080] ::r/di]}
   [0x63 0xb8 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/si -4081] ::r/di]}
   [0x63 0xb9 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di] ::r/di]}
   [0x63 0xb9 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di 4080] ::r/di]}
   [0x63 0xb9 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx ::r/di -4081] ::r/di]}
   [0x63 0xba 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si] ::r/di]}
   [0x63 0xba 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si 4080] ::r/di]}
   [0x63 0xba 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/si -4081] ::r/di]}
   [0x63 0xbb 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di] ::r/di]}
   [0x63 0xbb 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di 4080] ::r/di]}
   [0x63 0xbb 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp ::r/di -4081] ::r/di]}
   [0x63 0xbc 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/si] ::r/di]}
   [0x63 0xbc 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/si 4080] ::r/di]}
   [0x63 0xbc 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/si -4081] ::r/di]}
   [0x63 0xbd 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/di] ::r/di]}
   [0x63 0xbd 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/di 4080] ::r/di]}
   [0x63 0xbd 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/di -4081] ::r/di]}
   [0x63 0xbe 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bp] ::r/di]}
   [0x63 0xbe 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bp 4080] ::r/di]}
   [0x63 0xbe 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bp -4081] ::r/di]}
   [0x63 0xbf 0x00 0x00] {::i/tag ::i/arpl, ::i/args [[::r/bx] ::r/di]}
   [0x63 0xbf 0xf0 0x0f] {::i/tag ::i/arpl, ::i/args [[::r/bx 4080] ::r/di]}
   [0x63 0xbf 0x0f 0xf0] {::i/tag ::i/arpl, ::i/args [[::r/bx -4081] ::r/di]}
   [0x63 0xc0] {::i/tag ::i/arpl, ::i/args [::r/ax ::r/ax]}
   [0x63 0xc1] {::i/tag ::i/arpl, ::i/args [::r/cx ::r/ax]}
   [0x63 0xc2] {::i/tag ::i/arpl, ::i/args [::r/dx ::r/ax]}
   [0x63 0xc3] {::i/tag ::i/arpl, ::i/args [::r/bx ::r/ax]}
   [0x63 0xc4] {::i/tag ::i/arpl, ::i/args [::r/sp ::r/ax]}
   [0x63 0xc5] {::i/tag ::i/arpl, ::i/args [::r/bp ::r/ax]}
   [0x63 0xc6] {::i/tag ::i/arpl, ::i/args [::r/si ::r/ax]}
   [0x63 0xc7] {::i/tag ::i/arpl, ::i/args [::r/di ::r/ax]}
   [0x63 0xc8] {::i/tag ::i/arpl, ::i/args [::r/ax ::r/cx]}
   [0x63 0xc9] {::i/tag ::i/arpl, ::i/args [::r/cx ::r/cx]}
   [0x63 0xca] {::i/tag ::i/arpl, ::i/args [::r/dx ::r/cx]}
   [0x63 0xcb] {::i/tag ::i/arpl, ::i/args [::r/bx ::r/cx]}
   [0x63 0xcc] {::i/tag ::i/arpl, ::i/args [::r/sp ::r/cx]}
   [0x63 0xcd] {::i/tag ::i/arpl, ::i/args [::r/bp ::r/cx]}
   [0x63 0xce] {::i/tag ::i/arpl, ::i/args [::r/si ::r/cx]}
   [0x63 0xcf] {::i/tag ::i/arpl, ::i/args [::r/di ::r/cx]}
   [0x63 0xd0] {::i/tag ::i/arpl, ::i/args [::r/ax ::r/dx]}
   [0x63 0xd1] {::i/tag ::i/arpl, ::i/args [::r/cx ::r/dx]}
   [0x63 0xd2] {::i/tag ::i/arpl, ::i/args [::r/dx ::r/dx]}
   [0x63 0xd3] {::i/tag ::i/arpl, ::i/args [::r/bx ::r/dx]}
   [0x63 0xd4] {::i/tag ::i/arpl, ::i/args [::r/sp ::r/dx]}
   [0x63 0xd5] {::i/tag ::i/arpl, ::i/args [::r/bp ::r/dx]}
   [0x63 0xd6] {::i/tag ::i/arpl, ::i/args [::r/si ::r/dx]}
   [0x63 0xd7] {::i/tag ::i/arpl, ::i/args [::r/di ::r/dx]}
   [0x63 0xd8] {::i/tag ::i/arpl, ::i/args [::r/ax ::r/bx]}
   [0x63 0xd9] {::i/tag ::i/arpl, ::i/args [::r/cx ::r/bx]}
   [0x63 0xda] {::i/tag ::i/arpl, ::i/args [::r/dx ::r/bx]}
   [0x63 0xdb] {::i/tag ::i/arpl, ::i/args [::r/bx ::r/bx]}
   [0x63 0xdc] {::i/tag ::i/arpl, ::i/args [::r/sp ::r/bx]}
   [0x63 0xdd] {::i/tag ::i/arpl, ::i/args [::r/bp ::r/bx]}
   [0x63 0xde] {::i/tag ::i/arpl, ::i/args [::r/si ::r/bx]}
   [0x63 0xdf] {::i/tag ::i/arpl, ::i/args [::r/di ::r/bx]}
   [0x63 0xe0] {::i/tag ::i/arpl, ::i/args [::r/ax ::r/sp]}
   [0x63 0xe1] {::i/tag ::i/arpl, ::i/args [::r/cx ::r/sp]}
   [0x63 0xe2] {::i/tag ::i/arpl, ::i/args [::r/dx ::r/sp]}
   [0x63 0xe3] {::i/tag ::i/arpl, ::i/args [::r/bx ::r/sp]}
   [0x63 0xe4] {::i/tag ::i/arpl, ::i/args [::r/sp ::r/sp]}
   [0x63 0xe5] {::i/tag ::i/arpl, ::i/args [::r/bp ::r/sp]}
   [0x63 0xe6] {::i/tag ::i/arpl, ::i/args [::r/si ::r/sp]}
   [0x63 0xe7] {::i/tag ::i/arpl, ::i/args [::r/di ::r/sp]}
   [0x63 0xe8] {::i/tag ::i/arpl, ::i/args [::r/ax ::r/bp]}
   [0x63 0xe9] {::i/tag ::i/arpl, ::i/args [::r/cx ::r/bp]}
   [0x63 0xea] {::i/tag ::i/arpl, ::i/args [::r/dx ::r/bp]}
   [0x63 0xeb] {::i/tag ::i/arpl, ::i/args [::r/bx ::r/bp]}
   [0x63 0xec] {::i/tag ::i/arpl, ::i/args [::r/sp ::r/bp]}
   [0x63 0xed] {::i/tag ::i/arpl, ::i/args [::r/bp ::r/bp]}
   [0x63 0xee] {::i/tag ::i/arpl, ::i/args [::r/si ::r/bp]}
   [0x63 0xef] {::i/tag ::i/arpl, ::i/args [::r/di ::r/bp]}
   [0x63 0xf0] {::i/tag ::i/arpl, ::i/args [::r/ax ::r/si]}
   [0x63 0xf1] {::i/tag ::i/arpl, ::i/args [::r/cx ::r/si]}
   [0x63 0xf2] {::i/tag ::i/arpl, ::i/args [::r/dx ::r/si]}
   [0x63 0xf3] {::i/tag ::i/arpl, ::i/args [::r/bx ::r/si]}
   [0x63 0xf4] {::i/tag ::i/arpl, ::i/args [::r/sp ::r/si]}
   [0x63 0xf5] {::i/tag ::i/arpl, ::i/args [::r/bp ::r/si]}
   [0x63 0xf6] {::i/tag ::i/arpl, ::i/args [::r/si ::r/si]}
   [0x63 0xf7] {::i/tag ::i/arpl, ::i/args [::r/di ::r/si]}
   [0x63 0xf8] {::i/tag ::i/arpl, ::i/args [::r/ax ::r/di]}
   [0x63 0xf9] {::i/tag ::i/arpl, ::i/args [::r/cx ::r/di]}
   [0x63 0xfa] {::i/tag ::i/arpl, ::i/args [::r/dx ::r/di]}
   [0x63 0xfb] {::i/tag ::i/arpl, ::i/args [::r/bx ::r/di]}
   [0x63 0xfc] {::i/tag ::i/arpl, ::i/args [::r/sp ::r/di]}
   [0x63 0xfd] {::i/tag ::i/arpl, ::i/args [::r/bp ::r/di]}
   [0x63 0xfe] {::i/tag ::i/arpl, ::i/args [::r/si ::r/di]}
   [0x63 0xff] {::i/tag ::i/arpl, ::i/args [::r/di ::r/di]}})
