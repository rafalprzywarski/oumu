(ns oumu.cpu.instructions_test
  (:require [clojure.test :refer :all]
            [oumu.cpu.instructions :refer :all :as i]
            [oumu.cpu.registers :as r]))


(def decode-examples {[0x00 0xc0] {::i/tag ::i/add, ::i/args [::r/al ::r/al]}
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
