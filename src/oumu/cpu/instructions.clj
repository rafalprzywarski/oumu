(ns oumu.cpu.instructions
  (:require [oumu.cpu.registers :as r]))

(def regs8 [::r/al ::r/cl ::r/dl ::r/bl ::r/ah ::r/ch ::r/dh ::r/bh])
(def memr [[::r/bx ::r/si]
           [::r/bx ::r/di]
           [::r/bp ::r/si]
           [::r/bp ::r/di]
           [::r/si]
           [::r/di]
           [::imm16]
           [::r/bx]])
(def memrd8 [[::r/bx ::r/si]
             [::r/bx ::r/di]
             [::r/bp ::r/si]
             [::r/bp ::r/di]
             [::r/si]
             [::r/di]
             [::r/bp]
             [::r/bx]])

(def one-byte {0x00 {::tag ::add, ::args [::r8-or-m8 ::r8]}
               0x04 {::tag ::add, ::args [::r/al ::imm8]}
               0x05 {::tag ::add, ::args [::r/ax ::imm16]}
               0x06 {::tag ::push, ::args [::r/es]}
               0x07 {::tag ::pop, ::args [::r/es]}
               0x0e {::tag ::push, ::args [::r/cs]}
               0x16 {::tag ::push, ::args [::r/ss]}
               0x17 {::tag ::pop, ::args [::r/ss]}
               0x1e {::tag ::push, ::args [::r/ds]}
               0x1f {::tag ::pop, ::args [::r/ds]}
               0x27 {::tag ::daa}
               0x2f {::tag ::das}
               0x37 {::tag ::aaa}
               0x3f {::tag ::aas}
               0x40 {::tag ::inc, ::args [::r/ax]}
               0x41 {::tag ::inc, ::args [::r/cx]}
               0x42 {::tag ::inc, ::args [::r/dx]}
               0x43 {::tag ::inc, ::args [::r/bx]}
               0x44 {::tag ::inc, ::args [::r/sp]}
               0x45 {::tag ::inc, ::args [::r/bp]}
               0x46 {::tag ::inc, ::args [::r/si]}
               0x47 {::tag ::inc, ::args [::r/di]}
               0x48 {::tag ::dec, ::args [::r/ax]}
               0x49 {::tag ::dec, ::args [::r/cx]}
               0x4a {::tag ::dec, ::args [::r/dx]}
               0x4b {::tag ::dec, ::args [::r/bx]}
               0x4c {::tag ::dec, ::args [::r/sp]}
               0x4d {::tag ::dec, ::args [::r/bp]}
               0x4e {::tag ::dec, ::args [::r/si]}
               0x4f {::tag ::dec, ::args [::r/di]}
               0x50 {::tag ::push, ::args [::r/ax]}
               0x51 {::tag ::push, ::args [::r/cx]}
               0x52 {::tag ::push, ::args [::r/dx]}
               0x53 {::tag ::push, ::args [::r/bx]}
               0x54 {::tag ::push, ::args [::r/sp]}
               0x55 {::tag ::push, ::args [::r/bp]}
               0x56 {::tag ::push, ::args [::r/si]}
               0x57 {::tag ::push, ::args [::r/di]}
               0x58 {::tag ::pop, ::args [::r/ax]}
               0x59 {::tag ::pop, ::args [::r/cx]}
               0x5a {::tag ::pop, ::args [::r/dx]}
               0x5b {::tag ::pop, ::args [::r/bx]}
               0x5c {::tag ::pop, ::args [::r/sp]}
               0x5d {::tag ::pop, ::args [::r/bp]}
               0x5e {::tag ::pop, ::args [::r/si]}
               0x5f {::tag ::pop, ::args [::r/di]}
               0x60 {::tag ::pusha }
               0x61 {::tag ::popa }
               0x6c {::tag ::insb}
               0x6d {::tag ::insw}
               0x6e {::tag ::outsb}
               0x6f {::tag ::outsw}
               0x90 {::tag ::nop}
               0x98 {::tag ::cbw}
               0x99 {::tag ::cwd}
               0x9b {::tag ::fwait}
               0x9c {::tag ::pushf}
               0x9d {::tag ::popf}
               0x9e {::tag ::sahf}
               0x9f {::tag ::lahf}
               0xa4 {::tag ::movsb}
               0xa5 {::tag ::movsw}
               0xa6 {::tag ::cmpsb}
               0xa7 {::tag ::cmpsw}
               0xaa {::tag ::stosb}
               0xab {::tag ::stosw}
               0xac {::tag ::lodsb}
               0xad {::tag ::lodsw}
               0xae {::tag ::scasb}
               0xaf {::tag ::scasw}
               0xc3 {::tag ::ret}
               0xc9 {::tag ::leave}
               0xcb {::tag ::retf}
               0xcc {::tag ::int, ::args [(byte 3)]}
               0xce {::tag ::into}
               0xcf {::tag ::iret}
               0xd6 {::tag ::setalc}
               0xd7 {::tag ::xlat}
               0xec {::tag ::in, ::args [::r/al ::r/dx]}
               0xed {::tag ::in, ::args [::r/ax ::r/dx]}
               0xee {::tag ::out, ::args [::r/dx ::r/al]}
               0xef {::tag ::out, ::args [::r/dx ::r/ax]}
               0xf1 {::tag ::icebp}
               0xf4 {::tag ::hlt}
               0xf5 {::tag ::cmc}
               0xf8 {::tag ::clc}
               0xf9 {::tag ::stc}
               0xfa {::tag ::cli}
               0xfb {::tag ::sti}
               0xfc {::tag ::cld}
               0xfd {::tag ::std}})

(defn- word [bytes]
  (+ (first bytes) (bit-shift-left (second bytes) 8)))

(defn signed-byte [v]
  (if (< 0x7f v) (- v 0x100) v))

(defn signed-word [v]
  (if (< 0x7fff v) (- v 0x10000) v))

(defn- decode-reg8 [offset modrm]
  (regs8 (bit-and 0x07 (bit-shift-right modrm offset))))

(defn- decode-memrd8 [modrm]
  (memrd8 (bit-and 0x07 modrm)))

(defn- decode-memr [modrm]
  (memr (bit-and 0x07 modrm)))

(defn- decode-arg1 [instr bytes]
  (let [arg1 (second (::args instr))]
    (cond
      (= ::r8 arg1) (assoc-in instr [::args 1] (decode-reg8 3 (first bytes)))
      (= ::imm8 arg1) (assoc-in instr [::args 1] (first bytes))
      (= ::imm16 arg1) (assoc-in instr [::args 1] (word bytes))
      :else instr)))

(defn- conj-not-zero [coll val]
  (if (zero? val)
    coll
    (conj coll val)))

(defn- decode-arg0 [instr bytes]
  (let [arg0 (first (::args instr))]
    (if (= ::r8-or-m8 arg0)
      (let [modrm (first bytes)
            mod (bit-and 0xc0 modrm)
            arg0 (cond
                   (= 0xc0 mod) (decode-reg8 0 modrm)
                   (= 0x40 mod) (let [d (signed-byte (second bytes))
                                      ptr (decode-memrd8 modrm)]
                                  (conj-not-zero ptr d))
                   (= 0x80 mod) (let [d (signed-word (word (next bytes)))
                                      ptr (decode-memrd8 modrm)]
                                  (conj-not-zero ptr d))
                   :else (let [m (decode-memr modrm)
                               m (if (= m [::imm16]) [(word (next bytes))] m)]
                           m))]
       (assoc-in instr [::args 0] arg0))
      instr)))

(defn decode [bytes]
  (let [instr (one-byte (first bytes))
        instr (decode-arg0 instr (next bytes))
        instr (decode-arg1 instr (next bytes))]
    instr))
