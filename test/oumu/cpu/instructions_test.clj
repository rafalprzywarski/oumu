(ns oumu.cpu.instructions_test
  (:require [clojure.test :refer :all]
            [oumu.cpu.instructions :refer :all :as i]
            [oumu.cpu.registers :as r]))


(def decode-examples {[0x00 0x00] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/al]}
                      [0x00 0x01] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/al]}
                      [0x00 0x02] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/al]}
                      [0x00 0x03] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/al]}
                      [0x00 0x04] {::i/tag ::i/add, ::i/args [[::r/si] ::r/al]}
                      [0x00 0x05] {::i/tag ::i/add, ::i/args [[::r/di] ::r/al]}
                      [0x00 0x06 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/al]}
                      [0x00 0x06 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/al]}
                      [0x00 0x07] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/al]}
                      [0x00 0x08] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/cl]}
                      [0x00 0x09] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/cl]}
                      [0x00 0x0a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/cl]}
                      [0x00 0x0b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/cl]}
                      [0x00 0x0c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/cl]}
                      [0x00 0x0d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/cl]}
                      [0x00 0x0e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/cl]}
                      [0x00 0x0e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/cl]}
                      [0x00 0x0f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/cl]}
                      [0x00 0x10] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dl]}
                      [0x00 0x11] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dl]}
                      [0x00 0x12] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dl]}
                      [0x00 0x13] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dl]}
                      [0x00 0x14] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dl]}
                      [0x00 0x15] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dl]}
                      [0x00 0x16 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/dl]}
                      [0x00 0x16 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/dl]}
                      [0x00 0x17] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dl]}
                      [0x00 0x18] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bl]}
                      [0x00 0x19] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bl]}
                      [0x00 0x1a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bl]}
                      [0x00 0x1b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bl]}
                      [0x00 0x1c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bl]}
                      [0x00 0x1d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bl]}
                      [0x00 0x1e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/bl]}
                      [0x00 0x1e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/bl]}
                      [0x00 0x1f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bl]}
                      [0x00 0x20] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ah]}
                      [0x00 0x21] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ah]}
                      [0x00 0x22] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ah]}
                      [0x00 0x23] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ah]}
                      [0x00 0x24] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ah]}
                      [0x00 0x25] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ah]}
                      [0x00 0x26 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/ah]}
                      [0x00 0x26 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/ah]}
                      [0x00 0x27] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ah]}
                      [0x00 0x28] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/ch]}
                      [0x00 0x29] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/ch]}
                      [0x00 0x2a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/ch]}
                      [0x00 0x2b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/ch]}
                      [0x00 0x2c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/ch]}
                      [0x00 0x2d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/ch]}
                      [0x00 0x2e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/ch]}
                      [0x00 0x2e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/ch]}
                      [0x00 0x2f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/ch]}
                      [0x00 0x30] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/dh]}
                      [0x00 0x31] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/dh]}
                      [0x00 0x32] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/dh]}
                      [0x00 0x33] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/dh]}
                      [0x00 0x34] {::i/tag ::i/add, ::i/args [[::r/si] ::r/dh]}
                      [0x00 0x35] {::i/tag ::i/add, ::i/args [[::r/di] ::r/dh]}
                      [0x00 0x36 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/dh]}
                      [0x00 0x36 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/dh]}
                      [0x00 0x37] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/dh]}
                      [0x00 0x38] {::i/tag ::i/add, ::i/args [[::r/bx ::r/si] ::r/bh]}
                      [0x00 0x39] {::i/tag ::i/add, ::i/args [[::r/bx ::r/di] ::r/bh]}
                      [0x00 0x3a] {::i/tag ::i/add, ::i/args [[::r/bp ::r/si] ::r/bh]}
                      [0x00 0x3b] {::i/tag ::i/add, ::i/args [[::r/bp ::r/di] ::r/bh]}
                      [0x00 0x3c] {::i/tag ::i/add, ::i/args [[::r/si] ::r/bh]}
                      [0x00 0x3d] {::i/tag ::i/add, ::i/args [[::r/di] ::r/bh]}
                      [0x00 0x3e 0xf0 0x0f] {::i/tag ::i/add, ::i/args [[0x0ff0] ::r/bh]}
                      [0x00 0x3e 0x0f 0xf0] {::i/tag ::i/add, ::i/args [[0xf00f] ::r/bh]}
                      [0x00 0x3f] {::i/tag ::i/add, ::i/args [[::r/bx] ::r/bh]}

                      [0x00 0xc0] {::i/tag ::i/add, ::i/args [::r/al ::r/al]}
                      [0x00 0xc1] {::i/tag ::i/add, ::i/args [::r/cl ::r/al]}
                      [0x00 0xc2] {::i/tag ::i/add, ::i/args [::r/dl ::r/al]}
                      [0x00 0xc3] {::i/tag ::i/add, ::i/args [::r/bl ::r/al]}
                      [0x00 0xc4] {::i/tag ::i/add, ::i/args [::r/ah ::r/al]}
                      [0x00 0xc5] {::i/tag ::i/add, ::i/args [::r/ch ::r/al]}
                      [0x00 0xc6] {::i/tag ::i/add, ::i/args [::r/dh ::r/al]}
                      [0x00 0xc7] {::i/tag ::i/add, ::i/args [::r/bh ::r/al]}
                      [0x00 0xc8] {::i/tag ::i/add, ::i/args [::r/al ::r/cl]}
                      [0x00 0xc9] {::i/tag ::i/add, ::i/args [::r/cl ::r/cl]}
                      [0x00 0xca] {::i/tag ::i/add, ::i/args [::r/dl ::r/cl]}
                      [0x00 0xcb] {::i/tag ::i/add, ::i/args [::r/bl ::r/cl]}
                      [0x00 0xcc] {::i/tag ::i/add, ::i/args [::r/ah ::r/cl]}
                      [0x00 0xcd] {::i/tag ::i/add, ::i/args [::r/ch ::r/cl]}
                      [0x00 0xce] {::i/tag ::i/add, ::i/args [::r/dh ::r/cl]}
                      [0x00 0xcf] {::i/tag ::i/add, ::i/args [::r/bh ::r/cl]}
                      [0x00 0xd0] {::i/tag ::i/add, ::i/args [::r/al ::r/dl]}
                      [0x00 0xd1] {::i/tag ::i/add, ::i/args [::r/cl ::r/dl]}
                      [0x00 0xd2] {::i/tag ::i/add, ::i/args [::r/dl ::r/dl]}
                      [0x00 0xd3] {::i/tag ::i/add, ::i/args [::r/bl ::r/dl]}
                      [0x00 0xd4] {::i/tag ::i/add, ::i/args [::r/ah ::r/dl]}
                      [0x00 0xd5] {::i/tag ::i/add, ::i/args [::r/ch ::r/dl]}
                      [0x00 0xd6] {::i/tag ::i/add, ::i/args [::r/dh ::r/dl]}
                      [0x00 0xd7] {::i/tag ::i/add, ::i/args [::r/bh ::r/dl]}
                      [0x00 0xd8] {::i/tag ::i/add, ::i/args [::r/al ::r/bl]}
                      [0x00 0xd9] {::i/tag ::i/add, ::i/args [::r/cl ::r/bl]}
                      [0x00 0xda] {::i/tag ::i/add, ::i/args [::r/dl ::r/bl]}
                      [0x00 0xdb] {::i/tag ::i/add, ::i/args [::r/bl ::r/bl]}
                      [0x00 0xdc] {::i/tag ::i/add, ::i/args [::r/ah ::r/bl]}
                      [0x00 0xdd] {::i/tag ::i/add, ::i/args [::r/ch ::r/bl]}
                      [0x00 0xde] {::i/tag ::i/add, ::i/args [::r/dh ::r/bl]}
                      [0x00 0xdf] {::i/tag ::i/add, ::i/args [::r/bh ::r/bl]}
                      [0x00 0xe0] {::i/tag ::i/add, ::i/args [::r/al ::r/ah]}
                      [0x00 0xe1] {::i/tag ::i/add, ::i/args [::r/cl ::r/ah]}
                      [0x00 0xe2] {::i/tag ::i/add, ::i/args [::r/dl ::r/ah]}
                      [0x00 0xe3] {::i/tag ::i/add, ::i/args [::r/bl ::r/ah]}
                      [0x00 0xe4] {::i/tag ::i/add, ::i/args [::r/ah ::r/ah]}
                      [0x00 0xe5] {::i/tag ::i/add, ::i/args [::r/ch ::r/ah]}
                      [0x00 0xe6] {::i/tag ::i/add, ::i/args [::r/dh ::r/ah]}
                      [0x00 0xe7] {::i/tag ::i/add, ::i/args [::r/bh ::r/ah]}
                      [0x00 0xe8] {::i/tag ::i/add, ::i/args [::r/al ::r/ch]}
                      [0x00 0xe9] {::i/tag ::i/add, ::i/args [::r/cl ::r/ch]}
                      [0x00 0xea] {::i/tag ::i/add, ::i/args [::r/dl ::r/ch]}
                      [0x00 0xeb] {::i/tag ::i/add, ::i/args [::r/bl ::r/ch]}
                      [0x00 0xec] {::i/tag ::i/add, ::i/args [::r/ah ::r/ch]}
                      [0x00 0xed] {::i/tag ::i/add, ::i/args [::r/ch ::r/ch]}
                      [0x00 0xee] {::i/tag ::i/add, ::i/args [::r/dh ::r/ch]}
                      [0x00 0xef] {::i/tag ::i/add, ::i/args [::r/bh ::r/ch]}
                      [0x00 0xf0] {::i/tag ::i/add, ::i/args [::r/al ::r/dh]}
                      [0x00 0xf1] {::i/tag ::i/add, ::i/args [::r/cl ::r/dh]}
                      [0x00 0xf2] {::i/tag ::i/add, ::i/args [::r/dl ::r/dh]}
                      [0x00 0xf3] {::i/tag ::i/add, ::i/args [::r/bl ::r/dh]}
                      [0x00 0xf4] {::i/tag ::i/add, ::i/args [::r/ah ::r/dh]}
                      [0x00 0xf5] {::i/tag ::i/add, ::i/args [::r/ch ::r/dh]}
                      [0x00 0xf6] {::i/tag ::i/add, ::i/args [::r/dh ::r/dh]}
                      [0x00 0xf7] {::i/tag ::i/add, ::i/args [::r/bh ::r/dh]}
                      [0x00 0xf8] {::i/tag ::i/add, ::i/args [::r/al ::r/bh]}
                      [0x00 0xf9] {::i/tag ::i/add, ::i/args [::r/cl ::r/bh]}
                      [0x00 0xfa] {::i/tag ::i/add, ::i/args [::r/dl ::r/bh]}
                      [0x00 0xfb] {::i/tag ::i/add, ::i/args [::r/bl ::r/bh]}
                      [0x00 0xfc] {::i/tag ::i/add, ::i/args [::r/ah ::r/bh]}
                      [0x00 0xfd] {::i/tag ::i/add, ::i/args [::r/ch ::r/bh]}
                      [0x00 0xfe] {::i/tag ::i/add, ::i/args [::r/dh ::r/bh]}
                      [0x00 0xff] {::i/tag ::i/add, ::i/args [::r/bh ::r/bh]}

                      [0x04 0xf0] {::i/tag ::i/add, ::i/args [::r/al 0xf0]}
                      [0x04 0x0f] {::i/tag ::i/add, ::i/args [::r/al 0x0f]}
                      [0x05 0xf0 0x0f] {::i/tag ::i/add ::i/args [::r/ax 0x0ff0]}
                      [0x05 0x0f 0xf0] {::i/tag ::i/add ::i/args [::r/ax 0xf00f]}
                      [0x06] {::i/tag ::i/push, ::i/args [::r/es]}
                      [0x07] {::i/tag ::i/pop, ::i/args [::r/es]}
                      [0x0e] {::i/tag ::i/push, ::i/args [::r/cs]}
                      [0x16] {::i/tag ::i/push, ::i/args [::r/ss]}
                      [0x17] {::i/tag ::i/pop, ::i/args [::r/ss]}
                      [0x1e] {::i/tag ::i/push, ::i/args [::r/ds]}
                      [0x1f] {::i/tag ::i/pop, ::i/args [::r/ds]}
                      [0x37] {::i/tag ::i/aaa}
                      [0x27] {::i/tag ::i/daa}
                      [0x2f] {::i/tag ::i/das}
                      [0x3f] {::i/tag ::i/aas}
                      [0x40] {::i/tag ::i/inc, ::i/args [::r/ax]}
                      [0x41] {::i/tag ::i/inc, ::i/args [::r/cx]}
                      [0x42] {::i/tag ::i/inc, ::i/args [::r/dx]}
                      [0x43] {::i/tag ::i/inc, ::i/args [::r/bx]}
                      [0x44] {::i/tag ::i/inc, ::i/args [::r/sp]}
                      [0x45] {::i/tag ::i/inc, ::i/args [::r/bp]}
                      [0x46] {::i/tag ::i/inc, ::i/args [::r/si]}
                      [0x47] {::i/tag ::i/inc, ::i/args [::r/di]}
                      [0x48] {::i/tag ::i/dec, ::i/args [::r/ax]}
                      [0x49] {::i/tag ::i/dec, ::i/args [::r/cx]}
                      [0x4a] {::i/tag ::i/dec, ::i/args [::r/dx]}
                      [0x4b] {::i/tag ::i/dec, ::i/args [::r/bx]}
                      [0x4c] {::i/tag ::i/dec, ::i/args [::r/sp]}
                      [0x4d] {::i/tag ::i/dec, ::i/args [::r/bp]}
                      [0x4e] {::i/tag ::i/dec, ::i/args [::r/si]}
                      [0x4f] {::i/tag ::i/dec, ::i/args [::r/di]}
                      [0x50] {::i/tag ::i/push, ::i/args [::r/ax]}
                      [0x51] {::i/tag ::i/push, ::i/args [::r/cx]}
                      [0x52] {::i/tag ::i/push, ::i/args [::r/dx]}
                      [0x53] {::i/tag ::i/push, ::i/args [::r/bx]}
                      [0x54] {::i/tag ::i/push, ::i/args [::r/sp]}
                      [0x55] {::i/tag ::i/push, ::i/args [::r/bp]}
                      [0x56] {::i/tag ::i/push, ::i/args [::r/si]}
                      [0x57] {::i/tag ::i/push, ::i/args [::r/di]}
                      [0x58] {::i/tag ::i/pop, ::i/args [::r/ax]}
                      [0x59] {::i/tag ::i/pop, ::i/args [::r/cx]}
                      [0x5a] {::i/tag ::i/pop, ::i/args [::r/dx]}
                      [0x5b] {::i/tag ::i/pop, ::i/args [::r/bx]}
                      [0x5c] {::i/tag ::i/pop, ::i/args [::r/sp]}
                      [0x5d] {::i/tag ::i/pop, ::i/args [::r/bp]}
                      [0x5e] {::i/tag ::i/pop, ::i/args [::r/si]}
                      [0x5f] {::i/tag ::i/pop, ::i/args [::r/di]}
                      [0x60] {::i/tag ::i/pusha}
                      [0x61] {::i/tag ::i/popa}
                      [0x6c] {::i/tag ::i/insb}
                      [0x6d] {::i/tag ::i/insw}
                      [0x6e] {::i/tag ::i/outsb}
                      [0x6f] {::i/tag ::i/outsw}
                      [0x90] {::i/tag ::i/nop}
                      [0x98] {::i/tag ::i/cbw}
                      [0x99] {::i/tag ::i/cwd}
                      [0x9b] {::i/tag ::i/fwait}
                      [0x9c] {::i/tag ::i/pushf}
                      [0x9d] {::i/tag ::i/popf}
                      [0x9e] {::i/tag ::i/sahf}
                      [0x9f] {::i/tag ::i/lahf}
                      [0xa4] {::i/tag ::i/movsb}
                      [0xa5] {::i/tag ::i/movsw}
                      [0xa6] {::i/tag ::i/cmpsb}
                      [0xa7] {::i/tag ::i/cmpsw}
                      [0xaa] {::i/tag ::i/stosb}
                      [0xab] {::i/tag ::i/stosw}
                      [0xac] {::i/tag ::i/lodsb}
                      [0xad] {::i/tag ::i/lodsw}
                      [0xae] {::i/tag ::i/scasb}
                      [0xaf] {::i/tag ::i/scasw}
                      [0xc3] {::i/tag ::i/ret}
                      [0xc9] {::i/tag ::i/leave}
                      [0xcb] {::i/tag ::i/retf}
                      [0xcc] {::i/tag ::i/int, ::i/args [3]}
                      [0xce] {::i/tag ::i/into}
                      [0xcf] {::i/tag ::i/iret}
                      [0xd6] {::i/tag ::i/setalc}
                      [0xd7] {::i/tag ::i/xlat}
                      [0xec] {::i/tag ::i/in, ::i/args [::r/al ::r/dx]}
                      [0xed] {::i/tag ::i/in, ::i/args [::r/ax ::r/dx]}
                      [0xee] {::i/tag ::i/out, ::i/args [::r/dx ::r/al]}
                      [0xef] {::i/tag ::i/out, ::i/args [::r/dx ::r/ax]}
                      [0xf1] {::i/tag ::i/icebp}
                      [0xf4] {::i/tag ::i/hlt}
                      [0xf5] {::i/tag ::i/cmc}
                      [0xf8] {::i/tag ::i/clc}
                      [0xf9] {::i/tag ::i/stc}
                      [0xfa] {::i/tag ::i/cli}
                      [0xfb] {::i/tag ::i/sti}
                      [0xfc] {::i/tag ::i/cld}
                      [0xfd] {::i/tag ::i/std}})


(deftest decode-test
  (doseq [br (seq decode-examples)]
    (is (= (br 1)
           (decode (br 0)))
        (mapv #(format "%02x" %) (br 0)))))
