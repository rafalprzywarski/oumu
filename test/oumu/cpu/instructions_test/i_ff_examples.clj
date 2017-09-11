(ns oumu.cpu.instructions-test.i-ff-examples
    (:require [oumu.cpu.instructions :as i]
              [oumu.cpu.registers :as r]))


(def decode-examples-0xff
  {[0xff 0x00] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xff 0x01] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xff 0x02] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xff 0x03] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xff 0x04] {::i/tag ::i/inc, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xff 0x05] {::i/tag ::i/inc, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xff 0x06 0x12 0x34] {::i/tag ::i/inc, ::i/args [[0x3412]], ::i/type ::i/word}
   [0xff 0x06 0x34 0x12] {::i/tag ::i/inc, ::i/args [[0x1234]], ::i/type ::i/word}
   [0xff 0x07] {::i/tag ::i/inc, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xff 0x08] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xff 0x09] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xff 0x0a] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xff 0x0b] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xff 0x0c] {::i/tag ::i/dec, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xff 0x0d] {::i/tag ::i/dec, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xff 0x0e 0x12 0x34] {::i/tag ::i/dec, ::i/args [[0x3412]], ::i/type ::i/word}
   [0xff 0x0e 0x34 0x12] {::i/tag ::i/dec, ::i/args [[0x1234]], ::i/type ::i/word}
   [0xff 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xff 0x10] {::i/tag ::i/call, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x11] {::i/tag ::i/call, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x12] {::i/tag ::i/call, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x13] {::i/tag ::i/call, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x14] {::i/tag ::i/call, ::i/args [[::r/si]]}
   [0xff 0x15] {::i/tag ::i/call, ::i/args [[::r/di]]}
   [0xff 0x16 0x12 0x34] {::i/tag ::i/call, ::i/args [[0x3412]]}
   [0xff 0x16 0x34 0x12] {::i/tag ::i/call, ::i/args [[0x1234]]}
   [0xff 0x17] {::i/tag ::i/call, ::i/args [[::r/bx]]}
   [0xff 0x18] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x19] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x1a] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x1b] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x1c] {::i/tag ::i/callf, ::i/args [[::r/si]]}
   [0xff 0x1d] {::i/tag ::i/callf, ::i/args [[::r/di]]}
   [0xff 0x1e 0x12 0x34] {::i/tag ::i/callf, ::i/args [[0x3412]]}
   [0xff 0x1e 0x34 0x12] {::i/tag ::i/callf, ::i/args [[0x1234]]}
   [0xff 0x1f] {::i/tag ::i/callf, ::i/args [[::r/bx]]}
   [0xff 0x20] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x21] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x22] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x23] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x24] {::i/tag ::i/jmp, ::i/args [[::r/si]]}
   [0xff 0x25] {::i/tag ::i/jmp, ::i/args [[::r/di]]}
   [0xff 0x26 0x12 0x34] {::i/tag ::i/jmp, ::i/args [[0x3412]]}
   [0xff 0x26 0x34 0x12] {::i/tag ::i/jmp, ::i/args [[0x1234]]}
   [0xff 0x27] {::i/tag ::i/jmp, ::i/args [[::r/bx]]}
   [0xff 0x28] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x29] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x2a] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x2b] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x2c] {::i/tag ::i/jmpf, ::i/args [[::r/si]]}
   [0xff 0x2d] {::i/tag ::i/jmpf, ::i/args [[::r/di]]}
   [0xff 0x2e 0x12 0x34] {::i/tag ::i/jmpf, ::i/args [[0x3412]]}
   [0xff 0x2e 0x34 0x12] {::i/tag ::i/jmpf, ::i/args [[0x1234]]}
   [0xff 0x2f] {::i/tag ::i/jmpf, ::i/args [[::r/bx]]}
   [0xff 0x30] {::i/tag ::i/push, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x31] {::i/tag ::i/push, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x32] {::i/tag ::i/push, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x33] {::i/tag ::i/push, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x34] {::i/tag ::i/push, ::i/args [[::r/si]]}
   [0xff 0x35] {::i/tag ::i/push, ::i/args [[::r/di]]}
   [0xff 0x36 0x12 0x34] {::i/tag ::i/push, ::i/args [[0x3412]]}
   [0xff 0x36 0x34 0x12] {::i/tag ::i/push, ::i/args [[0x1234]]}
   [0xff 0x37] {::i/tag ::i/push, ::i/args [[::r/bx]]}
   [0xff 0x38] nil
   [0xff 0x39] nil
   [0xff 0x3a] nil
   [0xff 0x3b] nil
   [0xff 0x3c] nil
   [0xff 0x3d] nil
   [0xff 0x3e 0x12 0x34] nil
   [0xff 0x3e 0x34 0x12] nil
   [0xff 0x3f] nil
   [0xff 0x40 0x00] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xff 0x40 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xff 0x40 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xff 0x41 0x00] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xff 0x41 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xff 0x41 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xff 0x42 0x00] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xff 0x42 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xff 0x42 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xff 0x43 0x00] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xff 0x43 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xff 0x43 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xff 0x44 0x00] {::i/tag ::i/inc, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xff 0x44 0xf0] {::i/tag ::i/inc, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xff 0x44 0x0f] {::i/tag ::i/inc, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xff 0x45 0x00] {::i/tag ::i/inc, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xff 0x45 0xf0] {::i/tag ::i/inc, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xff 0x45 0x0f] {::i/tag ::i/inc, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xff 0x46 0x00] {::i/tag ::i/inc, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xff 0x46 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xff 0x46 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xff 0x47 0x00] {::i/tag ::i/inc, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xff 0x47 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xff 0x47 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xff 0x48 0x00] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xff 0x48 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/si -16]], ::i/type ::i/word}
   [0xff 0x48 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/si 15]], ::i/type ::i/word}
   [0xff 0x49 0x00] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xff 0x49 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/di -16]], ::i/type ::i/word}
   [0xff 0x49 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/di 15]], ::i/type ::i/word}
   [0xff 0x4a 0x00] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xff 0x4a 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/si -16]], ::i/type ::i/word}
   [0xff 0x4a 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/si 15]], ::i/type ::i/word}
   [0xff 0x4b 0x00] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xff 0x4b 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/di -16]], ::i/type ::i/word}
   [0xff 0x4b 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/di 15]], ::i/type ::i/word}
   [0xff 0x4c 0x00] {::i/tag ::i/dec, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xff 0x4c 0xf0] {::i/tag ::i/dec, ::i/args [[::r/si -16]], ::i/type ::i/word}
   [0xff 0x4c 0x0f] {::i/tag ::i/dec, ::i/args [[::r/si 15]], ::i/type ::i/word}
   [0xff 0x4d 0x00] {::i/tag ::i/dec, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xff 0x4d 0xf0] {::i/tag ::i/dec, ::i/args [[::r/di -16]], ::i/type ::i/word}
   [0xff 0x4d 0x0f] {::i/tag ::i/dec, ::i/args [[::r/di 15]], ::i/type ::i/word}
   [0xff 0x4e 0x00] {::i/tag ::i/dec, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xff 0x4e 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bp -16]], ::i/type ::i/word}
   [0xff 0x4e 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bp 15]], ::i/type ::i/word}
   [0xff 0x4f 0x00] {::i/tag ::i/dec, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xff 0x4f 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bx -16]], ::i/type ::i/word}
   [0xff 0x4f 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bx 15]], ::i/type ::i/word}
   [0xff 0x50 0x00] {::i/tag ::i/call, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x50 0xf0] {::i/tag ::i/call, ::i/args [[::r/bx ::r/si -16]]}
   [0xff 0x50 0x0f] {::i/tag ::i/call, ::i/args [[::r/bx ::r/si 15]]}
   [0xff 0x51 0x00] {::i/tag ::i/call, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x51 0xf0] {::i/tag ::i/call, ::i/args [[::r/bx ::r/di -16]]}
   [0xff 0x51 0x0f] {::i/tag ::i/call, ::i/args [[::r/bx ::r/di 15]]}
   [0xff 0x52 0x00] {::i/tag ::i/call, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x52 0xf0] {::i/tag ::i/call, ::i/args [[::r/bp ::r/si -16]]}
   [0xff 0x52 0x0f] {::i/tag ::i/call, ::i/args [[::r/bp ::r/si 15]]}
   [0xff 0x53 0x00] {::i/tag ::i/call, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x53 0xf0] {::i/tag ::i/call, ::i/args [[::r/bp ::r/di -16]]}
   [0xff 0x53 0x0f] {::i/tag ::i/call, ::i/args [[::r/bp ::r/di 15]]}
   [0xff 0x54 0x00] {::i/tag ::i/call, ::i/args [[::r/si]]}
   [0xff 0x54 0xf0] {::i/tag ::i/call, ::i/args [[::r/si -16]]}
   [0xff 0x54 0x0f] {::i/tag ::i/call, ::i/args [[::r/si 15]]}
   [0xff 0x55 0x00] {::i/tag ::i/call, ::i/args [[::r/di]]}
   [0xff 0x55 0xf0] {::i/tag ::i/call, ::i/args [[::r/di -16]]}
   [0xff 0x55 0x0f] {::i/tag ::i/call, ::i/args [[::r/di 15]]}
   [0xff 0x56 0x00] {::i/tag ::i/call, ::i/args [[::r/bp]]}
   [0xff 0x56 0xf0] {::i/tag ::i/call, ::i/args [[::r/bp -16]]}
   [0xff 0x56 0x0f] {::i/tag ::i/call, ::i/args [[::r/bp 15]]}
   [0xff 0x57 0x00] {::i/tag ::i/call, ::i/args [[::r/bx]]}
   [0xff 0x57 0xf0] {::i/tag ::i/call, ::i/args [[::r/bx -16]]}
   [0xff 0x57 0x0f] {::i/tag ::i/call, ::i/args [[::r/bx 15]]}
   [0xff 0x58 0x00] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x58 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/si -16]]}
   [0xff 0x58 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/si 15]]}
   [0xff 0x59 0x00] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x59 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/di -16]]}
   [0xff 0x59 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/di 15]]}
   [0xff 0x5a 0x00] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x5a 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/si -16]]}
   [0xff 0x5a 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/si 15]]}
   [0xff 0x5b 0x00] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x5b 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/di -16]]}
   [0xff 0x5b 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/di 15]]}
   [0xff 0x5c 0x00] {::i/tag ::i/callf, ::i/args [[::r/si]]}
   [0xff 0x5c 0xf0] {::i/tag ::i/callf, ::i/args [[::r/si -16]]}
   [0xff 0x5c 0x0f] {::i/tag ::i/callf, ::i/args [[::r/si 15]]}
   [0xff 0x5d 0x00] {::i/tag ::i/callf, ::i/args [[::r/di]]}
   [0xff 0x5d 0xf0] {::i/tag ::i/callf, ::i/args [[::r/di -16]]}
   [0xff 0x5d 0x0f] {::i/tag ::i/callf, ::i/args [[::r/di 15]]}
   [0xff 0x5e 0x00] {::i/tag ::i/callf, ::i/args [[::r/bp]]}
   [0xff 0x5e 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bp -16]]}
   [0xff 0x5e 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bp 15]]}
   [0xff 0x5f 0x00] {::i/tag ::i/callf, ::i/args [[::r/bx]]}
   [0xff 0x5f 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bx -16]]}
   [0xff 0x5f 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bx 15]]}
   [0xff 0x60 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x60 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/si -16]]}
   [0xff 0x60 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/si 15]]}
   [0xff 0x61 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x61 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/di -16]]}
   [0xff 0x61 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/di 15]]}
   [0xff 0x62 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x62 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/si -16]]}
   [0xff 0x62 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/si 15]]}
   [0xff 0x63 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x63 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/di -16]]}
   [0xff 0x63 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/di 15]]}
   [0xff 0x64 0x00] {::i/tag ::i/jmp, ::i/args [[::r/si]]}
   [0xff 0x64 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/si -16]]}
   [0xff 0x64 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/si 15]]}
   [0xff 0x65 0x00] {::i/tag ::i/jmp, ::i/args [[::r/di]]}
   [0xff 0x65 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/di -16]]}
   [0xff 0x65 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/di 15]]}
   [0xff 0x66 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bp]]}
   [0xff 0x66 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bp -16]]}
   [0xff 0x66 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bp 15]]}
   [0xff 0x67 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bx]]}
   [0xff 0x67 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bx -16]]}
   [0xff 0x67 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bx 15]]}
   [0xff 0x68 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x68 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/si -16]]}
   [0xff 0x68 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/si 15]]}
   [0xff 0x69 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x69 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/di -16]]}
   [0xff 0x69 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/di 15]]}
   [0xff 0x6a 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x6a 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/si -16]]}
   [0xff 0x6a 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/si 15]]}
   [0xff 0x6b 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x6b 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/di -16]]}
   [0xff 0x6b 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/di 15]]}
   [0xff 0x6c 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/si]]}
   [0xff 0x6c 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/si -16]]}
   [0xff 0x6c 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/si 15]]}
   [0xff 0x6d 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/di]]}
   [0xff 0x6d 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/di -16]]}
   [0xff 0x6d 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/di 15]]}
   [0xff 0x6e 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bp]]}
   [0xff 0x6e 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bp -16]]}
   [0xff 0x6e 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bp 15]]}
   [0xff 0x6f 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bx]]}
   [0xff 0x6f 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bx -16]]}
   [0xff 0x6f 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bx 15]]}
   [0xff 0x70 0x00] {::i/tag ::i/push, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x70 0xf0] {::i/tag ::i/push, ::i/args [[::r/bx ::r/si -16]]}
   [0xff 0x70 0x0f] {::i/tag ::i/push, ::i/args [[::r/bx ::r/si 15]]}
   [0xff 0x71 0x00] {::i/tag ::i/push, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x71 0xf0] {::i/tag ::i/push, ::i/args [[::r/bx ::r/di -16]]}
   [0xff 0x71 0x0f] {::i/tag ::i/push, ::i/args [[::r/bx ::r/di 15]]}
   [0xff 0x72 0x00] {::i/tag ::i/push, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x72 0xf0] {::i/tag ::i/push, ::i/args [[::r/bp ::r/si -16]]}
   [0xff 0x72 0x0f] {::i/tag ::i/push, ::i/args [[::r/bp ::r/si 15]]}
   [0xff 0x73 0x00] {::i/tag ::i/push, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x73 0xf0] {::i/tag ::i/push, ::i/args [[::r/bp ::r/di -16]]}
   [0xff 0x73 0x0f] {::i/tag ::i/push, ::i/args [[::r/bp ::r/di 15]]}
   [0xff 0x74 0x00] {::i/tag ::i/push, ::i/args [[::r/si]]}
   [0xff 0x74 0xf0] {::i/tag ::i/push, ::i/args [[::r/si -16]]}
   [0xff 0x74 0x0f] {::i/tag ::i/push, ::i/args [[::r/si 15]]}
   [0xff 0x75 0x00] {::i/tag ::i/push, ::i/args [[::r/di]]}
   [0xff 0x75 0xf0] {::i/tag ::i/push, ::i/args [[::r/di -16]]}
   [0xff 0x75 0x0f] {::i/tag ::i/push, ::i/args [[::r/di 15]]}
   [0xff 0x76 0x00] {::i/tag ::i/push, ::i/args [[::r/bp]]}
   [0xff 0x76 0xf0] {::i/tag ::i/push, ::i/args [[::r/bp -16]]}
   [0xff 0x76 0x0f] {::i/tag ::i/push, ::i/args [[::r/bp 15]]}
   [0xff 0x77 0x00] {::i/tag ::i/push, ::i/args [[::r/bx]]}
   [0xff 0x77 0xf0] {::i/tag ::i/push, ::i/args [[::r/bx -16]]}
   [0xff 0x77 0x0f] {::i/tag ::i/push, ::i/args [[::r/bx 15]]}
   [0xff 0x78 0x00] nil
   [0xff 0x78 0xf0] nil
   [0xff 0x78 0x0f] nil
   [0xff 0x79 0x00] nil
   [0xff 0x79 0xf0] nil
   [0xff 0x79 0x0f] nil
   [0xff 0x7a 0x00] nil
   [0xff 0x7a 0xf0] nil
   [0xff 0x7a 0x0f] nil
   [0xff 0x7b 0x00] nil
   [0xff 0x7b 0xf0] nil
   [0xff 0x7b 0x0f] nil
   [0xff 0x7c 0x00] nil
   [0xff 0x7c 0xf0] nil
   [0xff 0x7c 0x0f] nil
   [0xff 0x7d 0x00] nil
   [0xff 0x7d 0xf0] nil
   [0xff 0x7d 0x0f] nil
   [0xff 0x7e 0x00] nil
   [0xff 0x7e 0xf0] nil
   [0xff 0x7e 0x0f] nil
   [0xff 0x7f 0x00] nil
   [0xff 0x7f 0xf0] nil
   [0xff 0x7f 0x0f] nil
   [0xff 0x80 0x00 0x00] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xff 0x80 0xf0 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xff 0x80 0x0f 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xff 0x81 0x00 0x00] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xff 0x81 0xf0 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xff 0x81 0x0f 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xff 0x82 0x00 0x00] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xff 0x82 0xf0 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xff 0x82 0x0f 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xff 0x83 0x00 0x00] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xff 0x83 0xf0 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xff 0x83 0x0f 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xff 0x84 0x00 0x00] {::i/tag ::i/inc, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xff 0x84 0xf0 0x0f] {::i/tag ::i/inc, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xff 0x84 0x0f 0xf0] {::i/tag ::i/inc, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xff 0x85 0x00 0x00] {::i/tag ::i/inc, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xff 0x85 0xf0 0x0f] {::i/tag ::i/inc, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xff 0x85 0x0f 0xf0] {::i/tag ::i/inc, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xff 0x86 0x00 0x00] {::i/tag ::i/inc, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xff 0x86 0xf0 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xff 0x86 0x0f 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xff 0x87 0x00 0x00] {::i/tag ::i/inc, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xff 0x87 0xf0 0x0f] {::i/tag ::i/inc, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xff 0x87 0x0f 0xf0] {::i/tag ::i/inc, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xff 0x88 0x00 0x00] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/si]], ::i/type ::i/word}
   [0xff 0x88 0xf0 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/si 4080]], ::i/type ::i/word}
   [0xff 0x88 0x0f 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/si -4081]], ::i/type ::i/word}
   [0xff 0x89 0x00 0x00] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/di]], ::i/type ::i/word}
   [0xff 0x89 0xf0 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/di 4080]], ::i/type ::i/word}
   [0xff 0x89 0x0f 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bx ::r/di -4081]], ::i/type ::i/word}
   [0xff 0x8a 0x00 0x00] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/si]], ::i/type ::i/word}
   [0xff 0x8a 0xf0 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/si 4080]], ::i/type ::i/word}
   [0xff 0x8a 0x0f 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/si -4081]], ::i/type ::i/word}
   [0xff 0x8b 0x00 0x00] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/di]], ::i/type ::i/word}
   [0xff 0x8b 0xf0 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/di 4080]], ::i/type ::i/word}
   [0xff 0x8b 0x0f 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bp ::r/di -4081]], ::i/type ::i/word}
   [0xff 0x8c 0x00 0x00] {::i/tag ::i/dec, ::i/args [[::r/si]], ::i/type ::i/word}
   [0xff 0x8c 0xf0 0x0f] {::i/tag ::i/dec, ::i/args [[::r/si 4080]], ::i/type ::i/word}
   [0xff 0x8c 0x0f 0xf0] {::i/tag ::i/dec, ::i/args [[::r/si -4081]], ::i/type ::i/word}
   [0xff 0x8d 0x00 0x00] {::i/tag ::i/dec, ::i/args [[::r/di]], ::i/type ::i/word}
   [0xff 0x8d 0xf0 0x0f] {::i/tag ::i/dec, ::i/args [[::r/di 4080]], ::i/type ::i/word}
   [0xff 0x8d 0x0f 0xf0] {::i/tag ::i/dec, ::i/args [[::r/di -4081]], ::i/type ::i/word}
   [0xff 0x8e 0x00 0x00] {::i/tag ::i/dec, ::i/args [[::r/bp]], ::i/type ::i/word}
   [0xff 0x8e 0xf0 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bp 4080]], ::i/type ::i/word}
   [0xff 0x8e 0x0f 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bp -4081]], ::i/type ::i/word}
   [0xff 0x8f 0x00 0x00] {::i/tag ::i/dec, ::i/args [[::r/bx]], ::i/type ::i/word}
   [0xff 0x8f 0xf0 0x0f] {::i/tag ::i/dec, ::i/args [[::r/bx 4080]], ::i/type ::i/word}
   [0xff 0x8f 0x0f 0xf0] {::i/tag ::i/dec, ::i/args [[::r/bx -4081]], ::i/type ::i/word}
   [0xff 0x90 0x00 0x00] {::i/tag ::i/call, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x90 0xf0 0x0f] {::i/tag ::i/call, ::i/args [[::r/bx ::r/si 4080]]}
   [0xff 0x90 0x0f 0xf0] {::i/tag ::i/call, ::i/args [[::r/bx ::r/si -4081]]}
   [0xff 0x91 0x00 0x00] {::i/tag ::i/call, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x91 0xf0 0x0f] {::i/tag ::i/call, ::i/args [[::r/bx ::r/di 4080]]}
   [0xff 0x91 0x0f 0xf0] {::i/tag ::i/call, ::i/args [[::r/bx ::r/di -4081]]}
   [0xff 0x92 0x00 0x00] {::i/tag ::i/call, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x92 0xf0 0x0f] {::i/tag ::i/call, ::i/args [[::r/bp ::r/si 4080]]}
   [0xff 0x92 0x0f 0xf0] {::i/tag ::i/call, ::i/args [[::r/bp ::r/si -4081]]}
   [0xff 0x93 0x00 0x00] {::i/tag ::i/call, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x93 0xf0 0x0f] {::i/tag ::i/call, ::i/args [[::r/bp ::r/di 4080]]}
   [0xff 0x93 0x0f 0xf0] {::i/tag ::i/call, ::i/args [[::r/bp ::r/di -4081]]}
   [0xff 0x94 0x00 0x00] {::i/tag ::i/call, ::i/args [[::r/si]]}
   [0xff 0x94 0xf0 0x0f] {::i/tag ::i/call, ::i/args [[::r/si 4080]]}
   [0xff 0x94 0x0f 0xf0] {::i/tag ::i/call, ::i/args [[::r/si -4081]]}
   [0xff 0x95 0x00 0x00] {::i/tag ::i/call, ::i/args [[::r/di]]}
   [0xff 0x95 0xf0 0x0f] {::i/tag ::i/call, ::i/args [[::r/di 4080]]}
   [0xff 0x95 0x0f 0xf0] {::i/tag ::i/call, ::i/args [[::r/di -4081]]}
   [0xff 0x96 0x00 0x00] {::i/tag ::i/call, ::i/args [[::r/bp]]}
   [0xff 0x96 0xf0 0x0f] {::i/tag ::i/call, ::i/args [[::r/bp 4080]]}
   [0xff 0x96 0x0f 0xf0] {::i/tag ::i/call, ::i/args [[::r/bp -4081]]}
   [0xff 0x97 0x00 0x00] {::i/tag ::i/call, ::i/args [[::r/bx]]}
   [0xff 0x97 0xf0 0x0f] {::i/tag ::i/call, ::i/args [[::r/bx 4080]]}
   [0xff 0x97 0x0f 0xf0] {::i/tag ::i/call, ::i/args [[::r/bx -4081]]}
   [0xff 0x98 0x00 0x00] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/si]]}
   [0xff 0x98 0xf0 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/si 4080]]}
   [0xff 0x98 0x0f 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/si -4081]]}
   [0xff 0x99 0x00 0x00] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/di]]}
   [0xff 0x99 0xf0 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/di 4080]]}
   [0xff 0x99 0x0f 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bx ::r/di -4081]]}
   [0xff 0x9a 0x00 0x00] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/si]]}
   [0xff 0x9a 0xf0 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/si 4080]]}
   [0xff 0x9a 0x0f 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/si -4081]]}
   [0xff 0x9b 0x00 0x00] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/di]]}
   [0xff 0x9b 0xf0 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/di 4080]]}
   [0xff 0x9b 0x0f 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bp ::r/di -4081]]}
   [0xff 0x9c 0x00 0x00] {::i/tag ::i/callf, ::i/args [[::r/si]]}
   [0xff 0x9c 0xf0 0x0f] {::i/tag ::i/callf, ::i/args [[::r/si 4080]]}
   [0xff 0x9c 0x0f 0xf0] {::i/tag ::i/callf, ::i/args [[::r/si -4081]]}
   [0xff 0x9d 0x00 0x00] {::i/tag ::i/callf, ::i/args [[::r/di]]}
   [0xff 0x9d 0xf0 0x0f] {::i/tag ::i/callf, ::i/args [[::r/di 4080]]}
   [0xff 0x9d 0x0f 0xf0] {::i/tag ::i/callf, ::i/args [[::r/di -4081]]}
   [0xff 0x9e 0x00 0x00] {::i/tag ::i/callf, ::i/args [[::r/bp]]}
   [0xff 0x9e 0xf0 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bp 4080]]}
   [0xff 0x9e 0x0f 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bp -4081]]}
   [0xff 0x9f 0x00 0x00] {::i/tag ::i/callf, ::i/args [[::r/bx]]}
   [0xff 0x9f 0xf0 0x0f] {::i/tag ::i/callf, ::i/args [[::r/bx 4080]]}
   [0xff 0x9f 0x0f 0xf0] {::i/tag ::i/callf, ::i/args [[::r/bx -4081]]}
   [0xff 0xa0 0x00 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/si]]}
   [0xff 0xa0 0xf0 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/si 4080]]}
   [0xff 0xa0 0x0f 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/si -4081]]}
   [0xff 0xa1 0x00 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/di]]}
   [0xff 0xa1 0xf0 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/di 4080]]}
   [0xff 0xa1 0x0f 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bx ::r/di -4081]]}
   [0xff 0xa2 0x00 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/si]]}
   [0xff 0xa2 0xf0 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/si 4080]]}
   [0xff 0xa2 0x0f 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/si -4081]]}
   [0xff 0xa3 0x00 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/di]]}
   [0xff 0xa3 0xf0 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/di 4080]]}
   [0xff 0xa3 0x0f 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bp ::r/di -4081]]}
   [0xff 0xa4 0x00 0x00] {::i/tag ::i/jmp, ::i/args [[::r/si]]}
   [0xff 0xa4 0xf0 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/si 4080]]}
   [0xff 0xa4 0x0f 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/si -4081]]}
   [0xff 0xa5 0x00 0x00] {::i/tag ::i/jmp, ::i/args [[::r/di]]}
   [0xff 0xa5 0xf0 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/di 4080]]}
   [0xff 0xa5 0x0f 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/di -4081]]}
   [0xff 0xa6 0x00 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bp]]}
   [0xff 0xa6 0xf0 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bp 4080]]}
   [0xff 0xa6 0x0f 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bp -4081]]}
   [0xff 0xa7 0x00 0x00] {::i/tag ::i/jmp, ::i/args [[::r/bx]]}
   [0xff 0xa7 0xf0 0x0f] {::i/tag ::i/jmp, ::i/args [[::r/bx 4080]]}
   [0xff 0xa7 0x0f 0xf0] {::i/tag ::i/jmp, ::i/args [[::r/bx -4081]]}
   [0xff 0xa8 0x00 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/si]]}
   [0xff 0xa8 0xf0 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/si 4080]]}
   [0xff 0xa8 0x0f 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/si -4081]]}
   [0xff 0xa9 0x00 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/di]]}
   [0xff 0xa9 0xf0 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/di 4080]]}
   [0xff 0xa9 0x0f 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bx ::r/di -4081]]}
   [0xff 0xaa 0x00 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/si]]}
   [0xff 0xaa 0xf0 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/si 4080]]}
   [0xff 0xaa 0x0f 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/si -4081]]}
   [0xff 0xab 0x00 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/di]]}
   [0xff 0xab 0xf0 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/di 4080]]}
   [0xff 0xab 0x0f 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bp ::r/di -4081]]}
   [0xff 0xac 0x00 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/si]]}
   [0xff 0xac 0xf0 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/si 4080]]}
   [0xff 0xac 0x0f 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/si -4081]]}
   [0xff 0xad 0x00 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/di]]}
   [0xff 0xad 0xf0 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/di 4080]]}
   [0xff 0xad 0x0f 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/di -4081]]}
   [0xff 0xae 0x00 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bp]]}
   [0xff 0xae 0xf0 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bp 4080]]}
   [0xff 0xae 0x0f 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bp -4081]]}
   [0xff 0xaf 0x00 0x00] {::i/tag ::i/jmpf, ::i/args [[::r/bx]]}
   [0xff 0xaf 0xf0 0x0f] {::i/tag ::i/jmpf, ::i/args [[::r/bx 4080]]}
   [0xff 0xaf 0x0f 0xf0] {::i/tag ::i/jmpf, ::i/args [[::r/bx -4081]]}
   [0xff 0xb0 0x00 0x00] {::i/tag ::i/push, ::i/args [[::r/bx ::r/si]]}
   [0xff 0xb0 0xf0 0x0f] {::i/tag ::i/push, ::i/args [[::r/bx ::r/si 4080]]}
   [0xff 0xb0 0x0f 0xf0] {::i/tag ::i/push, ::i/args [[::r/bx ::r/si -4081]]}
   [0xff 0xb1 0x00 0x00] {::i/tag ::i/push, ::i/args [[::r/bx ::r/di]]}
   [0xff 0xb1 0xf0 0x0f] {::i/tag ::i/push, ::i/args [[::r/bx ::r/di 4080]]}
   [0xff 0xb1 0x0f 0xf0] {::i/tag ::i/push, ::i/args [[::r/bx ::r/di -4081]]}
   [0xff 0xb2 0x00 0x00] {::i/tag ::i/push, ::i/args [[::r/bp ::r/si]]}
   [0xff 0xb2 0xf0 0x0f] {::i/tag ::i/push, ::i/args [[::r/bp ::r/si 4080]]}
   [0xff 0xb2 0x0f 0xf0] {::i/tag ::i/push, ::i/args [[::r/bp ::r/si -4081]]}
   [0xff 0xb3 0x00 0x00] {::i/tag ::i/push, ::i/args [[::r/bp ::r/di]]}
   [0xff 0xb3 0xf0 0x0f] {::i/tag ::i/push, ::i/args [[::r/bp ::r/di 4080]]}
   [0xff 0xb3 0x0f 0xf0] {::i/tag ::i/push, ::i/args [[::r/bp ::r/di -4081]]}
   [0xff 0xb4 0x00 0x00] {::i/tag ::i/push, ::i/args [[::r/si]]}
   [0xff 0xb4 0xf0 0x0f] {::i/tag ::i/push, ::i/args [[::r/si 4080]]}
   [0xff 0xb4 0x0f 0xf0] {::i/tag ::i/push, ::i/args [[::r/si -4081]]}
   [0xff 0xb5 0x00 0x00] {::i/tag ::i/push, ::i/args [[::r/di]]}
   [0xff 0xb5 0xf0 0x0f] {::i/tag ::i/push, ::i/args [[::r/di 4080]]}
   [0xff 0xb5 0x0f 0xf0] {::i/tag ::i/push, ::i/args [[::r/di -4081]]}
   [0xff 0xb6 0x00 0x00] {::i/tag ::i/push, ::i/args [[::r/bp]]}
   [0xff 0xb6 0xf0 0x0f] {::i/tag ::i/push, ::i/args [[::r/bp 4080]]}
   [0xff 0xb6 0x0f 0xf0] {::i/tag ::i/push, ::i/args [[::r/bp -4081]]}
   [0xff 0xb7 0x00 0x00] {::i/tag ::i/push, ::i/args [[::r/bx]]}
   [0xff 0xb7 0xf0 0x0f] {::i/tag ::i/push, ::i/args [[::r/bx 4080]]}
   [0xff 0xb7 0x0f 0xf0] {::i/tag ::i/push, ::i/args [[::r/bx -4081]]}
   [0xff 0xb8 0x00 0x00] nil
   [0xff 0xb8 0xf0 0x0f] nil
   [0xff 0xb8 0x0f 0xf0] nil
   [0xff 0xb9 0x00 0x00] nil
   [0xff 0xb9 0xf0 0x0f] nil
   [0xff 0xb9 0x0f 0xf0] nil
   [0xff 0xba 0x00 0x00] nil
   [0xff 0xba 0xf0 0x0f] nil
   [0xff 0xba 0x0f 0xf0] nil
   [0xff 0xbb 0x00 0x00] nil
   [0xff 0xbb 0xf0 0x0f] nil
   [0xff 0xbb 0x0f 0xf0] nil
   [0xff 0xbc 0x00 0x00] nil
   [0xff 0xbc 0xf0 0x0f] nil
   [0xff 0xbc 0x0f 0xf0] nil
   [0xff 0xbd 0x00 0x00] nil
   [0xff 0xbd 0xf0 0x0f] nil
   [0xff 0xbd 0x0f 0xf0] nil
   [0xff 0xbe 0x00 0x00] nil
   [0xff 0xbe 0xf0 0x0f] nil
   [0xff 0xbe 0x0f 0xf0] nil
   [0xff 0xbf 0x00 0x00] nil
   [0xff 0xbf 0xf0 0x0f] nil
   [0xff 0xbf 0x0f 0xf0] nil
   [0xff 0xc0] {::i/tag ::i/inc, ::i/args [::r/ax]}
   [0xff 0xc1] {::i/tag ::i/inc, ::i/args [::r/cx]}
   [0xff 0xc2] {::i/tag ::i/inc, ::i/args [::r/dx]}
   [0xff 0xc3] {::i/tag ::i/inc, ::i/args [::r/bx]}
   [0xff 0xc4] {::i/tag ::i/inc, ::i/args [::r/sp]}
   [0xff 0xc5] {::i/tag ::i/inc, ::i/args [::r/bp]}
   [0xff 0xc6] {::i/tag ::i/inc, ::i/args [::r/si]}
   [0xff 0xc7] {::i/tag ::i/inc, ::i/args [::r/di]}
   [0xff 0xc8] {::i/tag ::i/dec, ::i/args [::r/ax]}
   [0xff 0xc9] {::i/tag ::i/dec, ::i/args [::r/cx]}
   [0xff 0xca] {::i/tag ::i/dec, ::i/args [::r/dx]}
   [0xff 0xcb] {::i/tag ::i/dec, ::i/args [::r/bx]}
   [0xff 0xcc] {::i/tag ::i/dec, ::i/args [::r/sp]}
   [0xff 0xcd] {::i/tag ::i/dec, ::i/args [::r/bp]}
   [0xff 0xce] {::i/tag ::i/dec, ::i/args [::r/si]}
   [0xff 0xcf] {::i/tag ::i/dec, ::i/args [::r/di]}
   [0xff 0xd0] {::i/tag ::i/call, ::i/args [::r/ax]}
   [0xff 0xd1] {::i/tag ::i/call, ::i/args [::r/cx]}
   [0xff 0xd2] {::i/tag ::i/call, ::i/args [::r/dx]}
   [0xff 0xd3] {::i/tag ::i/call, ::i/args [::r/bx]}
   [0xff 0xd4] {::i/tag ::i/call, ::i/args [::r/sp]}
   [0xff 0xd5] {::i/tag ::i/call, ::i/args [::r/bp]}
   [0xff 0xd6] {::i/tag ::i/call, ::i/args [::r/si]}
   [0xff 0xd7] {::i/tag ::i/call, ::i/args [::r/di]}
   [0xff 0xd8] nil
   [0xff 0xd9] nil
   [0xff 0xda] nil
   [0xff 0xdb] nil
   [0xff 0xdc] nil
   [0xff 0xdd] nil
   [0xff 0xde] nil
   [0xff 0xdf] nil
   [0xff 0xe0] {::i/tag ::i/jmp, ::i/args [::r/ax]}
   [0xff 0xe1] {::i/tag ::i/jmp, ::i/args [::r/cx]}
   [0xff 0xe2] {::i/tag ::i/jmp, ::i/args [::r/dx]}
   [0xff 0xe3] {::i/tag ::i/jmp, ::i/args [::r/bx]}
   [0xff 0xe4] {::i/tag ::i/jmp, ::i/args [::r/sp]}
   [0xff 0xe5] {::i/tag ::i/jmp, ::i/args [::r/bp]}
   [0xff 0xe6] {::i/tag ::i/jmp, ::i/args [::r/si]}
   [0xff 0xe7] {::i/tag ::i/jmp, ::i/args [::r/di]}
   [0xff 0xe8] nil
   [0xff 0xe9] nil
   [0xff 0xea] nil
   [0xff 0xeb] nil
   [0xff 0xec] nil
   [0xff 0xed] nil
   [0xff 0xee] nil
   [0xff 0xef] nil
   [0xff 0xf0] {::i/tag ::i/push, ::i/args [::r/ax]}
   [0xff 0xf1] {::i/tag ::i/push, ::i/args [::r/cx]}
   [0xff 0xf2] {::i/tag ::i/push, ::i/args [::r/dx]}
   [0xff 0xf3] {::i/tag ::i/push, ::i/args [::r/bx]}
   [0xff 0xf4] {::i/tag ::i/push, ::i/args [::r/sp]}
   [0xff 0xf5] {::i/tag ::i/push, ::i/args [::r/bp]}
   [0xff 0xf6] {::i/tag ::i/push, ::i/args [::r/si]}
   [0xff 0xf7] {::i/tag ::i/push, ::i/args [::r/di]}
   [0xff 0xf8] nil
   [0xff 0xf9] nil
   [0xff 0xfa] nil
   [0xff 0xfb] nil
   [0xff 0xfc] nil
   [0xff 0xfd] nil
   [0xff 0xfe] nil
   [0xff 0xff] nil})
