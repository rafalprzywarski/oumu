(ns oumu.cpu.instructions
  (:require [oumu.cpu.registers :as r]))

(def regs8 [::r/al ::r/cl ::r/dl ::r/bl ::r/ah ::r/ch ::r/dh ::r/bh])

(def regs16 [::r/ax ::r/cx ::r/dx ::r/bx ::r/sp ::r/bp ::r/si ::r/di])

(def memr [[::r/bx ::r/si]
           [::r/bx ::r/di]
           [::r/bp ::r/si]
           [::r/bp ::r/di]
           [::r/si]
           [::r/di]
           [::imm16]
           [::r/bx]])

(def memrd [[::r/bx ::r/si]
            [::r/bx ::r/di]
            [::r/bp ::r/si]
            [::r/bp ::r/di]
            [::r/si]
            [::r/di]
            [::r/bp]
            [::r/bx]])

(def one-byte {0x00 {::tag ::add, ::args [::r-or-m8 ::r8], ::length 2}
               0x01 {::tag ::add, ::args [::r-or-m16 ::r16], ::length 2}
               0x02 {::tag ::add, ::args [::r8 ::r-or-m8], ::length 2}
               0x03 {::tag ::add, ::args [::r16 ::r-or-m16], ::length 2}
               0x04 {::tag ::add, ::args [::r/al ::imm8], ::length 1}
               0x05 {::tag ::add, ::args [::r/ax ::imm16], ::length 1}
               0x06 {::tag ::push, ::args [::r/es], ::length 1}
               0x07 {::tag ::pop, ::args [::r/es], ::length 1}
               0x08 {::tag ::or, ::args [::r-or-m8 ::r8], ::length 2}
               0x09 {::tag ::or, ::args [::r-or-m16 ::r16], ::length 2}
               0x0a {::tag ::or, ::args [::r8 ::r-or-m8], ::length 2}
               0x0b {::tag ::or, ::args [::r16 ::r-or-m16], ::length 2}
               0x0c {::tag ::or, ::args [::r/al ::imm8], ::length 1}
               0x0d {::tag ::or, ::args [::r/ax ::imm16], ::length 1}
               0x0e {::tag ::push, ::args [::r/cs], ::length 1}
               0x10 {::tag ::adc, ::args [::r-or-m8 ::r8], ::length 2}
               0x11 {::tag ::adc, ::args [::r-or-m16 ::r16], ::length 2}
               0x12 {::tag ::adc, ::args [::r8 ::r-or-m8], ::length 2}
               0x13 {::tag ::adc, ::args [::r16 ::r-or-m16], ::length 2}
               0x14 {::tag ::adc, ::args [::r/al ::imm8], ::length 1}
               0x15 {::tag ::adc, ::args [::r/ax ::imm16], ::length 1}
               0x16 {::tag ::push, ::args [::r/ss], ::length 1}
               0x17 {::tag ::pop, ::args [::r/ss], ::length 1}
               0x18 {::tag ::sbb, ::args [::r-or-m8 ::r8], ::length 2}
               0x19 {::tag ::sbb, ::args [::r-or-m16 ::r16], ::length 2}
               0x1a {::tag ::sbb, ::args [::r8 ::r-or-m8], ::length 2}
               0x1b {::tag ::sbb, ::args [::r16 ::r-or-m16], ::length 2}
               0x1c {::tag ::sbb, ::args [::r/al ::imm8], ::length 1}
               0x1d {::tag ::sbb, ::args [::r/ax ::imm16], ::length 1}
               0x1e {::tag ::push, ::args [::r/ds], ::length 1}
               0x1f {::tag ::pop, ::args [::r/ds], ::length 1}
               0x20 {::tag ::and, ::args [::r-or-m8 ::r8], ::length 2}
               0x21 {::tag ::and, ::args [::r-or-m16 ::r16], ::length 2}
               0x22 {::tag ::and, ::args [::r8 ::r-or-m8], ::length 2}
               0x23 {::tag ::and, ::args [::r16 ::r-or-m16], ::length 2}
               0x24 {::tag ::and, ::args [::r/al ::imm8], ::length 1}
               0x25 {::tag ::and, ::args [::r/ax ::imm16], ::length 1}
               0x27 {::tag ::daa, ::length 1}
               0x28 {::tag ::sub, ::args [::r-or-m8 ::r8], ::length 2}
               0x29 {::tag ::sub, ::args [::r-or-m16 ::r16], ::length 2}
               0x2a {::tag ::sub, ::args [::r8 ::r-or-m8], ::length 2}
               0x2b {::tag ::sub, ::args [::r16 ::r-or-m16], ::length 2}
               0x2c {::tag ::sub, ::args [::r/al ::imm8], ::length 1}
               0x2d {::tag ::sub, ::args [::r/ax ::imm16], ::length 1}
               0x2f {::tag ::das, ::length 1}
               0x30 {::tag ::xor, ::args [::r-or-m8 ::r8], ::length 2}
               0x31 {::tag ::xor, ::args [::r-or-m16 ::r16], ::length 2}
               0x32 {::tag ::xor, ::args [::r8 ::r-or-m8], ::length 2}
               0x33 {::tag ::xor, ::args [::r16 ::r-or-m16], ::length 2}
               0x34 {::tag ::xor, ::args [::r/al ::imm8], ::length 1}
               0x35 {::tag ::xor, ::args [::r/ax ::imm16], ::length 1}
               0x37 {::tag ::aaa, ::length 1}
               0x38 {::tag ::cmp, ::args [::r-or-m8 ::r8], ::length 2}
               0x39 {::tag ::cmp, ::args [::r-or-m16 ::r16], ::length 2}
               0x3a {::tag ::cmp, ::args [::r8 ::r-or-m8], ::length 2}
               0x3b {::tag ::cmp, ::args [::r16 ::r-or-m16], ::length 2}
               0x3c {::tag ::cmp, ::args [::r/al ::imm8], ::length 1}
               0x3d {::tag ::cmp, ::args [::r/ax ::imm16], ::length 1}
               0x3f {::tag ::aas, ::length 1}
               0x40 {::tag ::inc, ::args [::r/ax], ::length 1}
               0x41 {::tag ::inc, ::args [::r/cx], ::length 1}
               0x42 {::tag ::inc, ::args [::r/dx], ::length 1}
               0x43 {::tag ::inc, ::args [::r/bx], ::length 1}
               0x44 {::tag ::inc, ::args [::r/sp], ::length 1}
               0x45 {::tag ::inc, ::args [::r/bp], ::length 1}
               0x46 {::tag ::inc, ::args [::r/si], ::length 1}
               0x47 {::tag ::inc, ::args [::r/di], ::length 1}
               0x48 {::tag ::dec, ::args [::r/ax], ::length 1}
               0x49 {::tag ::dec, ::args [::r/cx], ::length 1}
               0x4a {::tag ::dec, ::args [::r/dx], ::length 1}
               0x4b {::tag ::dec, ::args [::r/bx], ::length 1}
               0x4c {::tag ::dec, ::args [::r/sp], ::length 1}
               0x4d {::tag ::dec, ::args [::r/bp], ::length 1}
               0x4e {::tag ::dec, ::args [::r/si], ::length 1}
               0x4f {::tag ::dec, ::args [::r/di], ::length 1}
               0x50 {::tag ::push, ::args [::r/ax], ::length 1}
               0x51 {::tag ::push, ::args [::r/cx], ::length 1}
               0x52 {::tag ::push, ::args [::r/dx], ::length 1}
               0x53 {::tag ::push, ::args [::r/bx], ::length 1}
               0x54 {::tag ::push, ::args [::r/sp], ::length 1}
               0x55 {::tag ::push, ::args [::r/bp], ::length 1}
               0x56 {::tag ::push, ::args [::r/si], ::length 1}
               0x57 {::tag ::push, ::args [::r/di], ::length 1}
               0x58 {::tag ::pop, ::args [::r/ax], ::length 1}
               0x59 {::tag ::pop, ::args [::r/cx], ::length 1}
               0x5a {::tag ::pop, ::args [::r/dx], ::length 1}
               0x5b {::tag ::pop, ::args [::r/bx], ::length 1}
               0x5c {::tag ::pop, ::args [::r/sp], ::length 1}
               0x5d {::tag ::pop, ::args [::r/bp], ::length 1}
               0x5e {::tag ::pop, ::args [::r/si], ::length 1}
               0x5f {::tag ::pop, ::args [::r/di], ::length 1}
               0x60 {::tag ::pusha, ::length 1}
               0x61 {::tag ::popa, ::length 1}
               0x68 {::tag ::push, ::args [::imm16], ::length 1}
               0x69 {::tag ::imul, ::args [::r16 ::r-or-m16 ::imm16], ::length 2}
               0x6a {::tag ::push, ::args [::imm8], ::length 1}
               0x6b {::tag ::imul, ::args [::r16 ::r-or-m16 ::imm8e], ::length 2}
               0x6c {::tag ::insb, ::length 1}
               0x6d {::tag ::insw, ::length 1}
               0x6e {::tag ::outsb, ::length 1}
               0x6f {::tag ::outsw, ::length 1}
               0x70 {::tag ::jo, ::args [::rel8], ::length 1}
               0x71 {::tag ::jno, ::args [::rel8], ::length 1}
               0x72 {::tag ::jb, ::args [::rel8], ::length 1}
               0x73 {::tag ::jnb, ::args [::rel8], ::length 1}
               0x74 {::tag ::je, ::args [::rel8], ::length 1}
               0x75 {::tag ::jne, ::args [::rel8], ::length 1}
               0x76 {::tag ::jbe, ::args [::rel8], ::length 1}
               0x77 {::tag ::jnbe, ::args [::rel8], ::length 1}
               0x78 {::tag ::js, ::args [::rel8], ::length 1}
               0x79 {::tag ::jns, ::args [::rel8], ::length 1}
               0x7a {::tag ::jp, ::args [::rel8], ::length 1}
               0x7b {::tag ::jnp, ::args [::rel8], ::length 1}
               0x7c {::tag ::jl, ::args [::rel8], ::length 1}
               0x7d {::tag ::jnl, ::args [::rel8], ::length 1}
               0x7e {::tag ::jle, ::args [::rel8], ::length 1}
               0x7f {::tag ::jnle, ::args [::rel8], ::length 1}
               0x90 {::tag ::nop, ::length 1}
               0x98 {::tag ::cbw, ::length 1}
               0x99 {::tag ::cwd, ::length 1}
               0x9b {::tag ::fwait, ::length 1}
               0x9c {::tag ::pushf, ::length 1}
               0x9d {::tag ::popf, ::length 1}
               0x9e {::tag ::sahf, ::length 1}
               0x9f {::tag ::lahf, ::length 1}
               0xa4 {::tag ::movsb, ::length 1}
               0xa5 {::tag ::movsw, ::length 1}
               0xa6 {::tag ::cmpsb, ::length 1}
               0xa7 {::tag ::cmpsw, ::length 1}
               0xaa {::tag ::stosb, ::length 1}
               0xab {::tag ::stosw, ::length 1}
               0xac {::tag ::lodsb, ::length 1}
               0xad {::tag ::lodsw, ::length 1}
               0xae {::tag ::scasb, ::length 1}
               0xaf {::tag ::scasw, ::length 1}
               0xc3 {::tag ::ret, ::length 1}
               0xc9 {::tag ::leave, ::length 1}
               0xcb {::tag ::retf, ::length 1}
               0xcc {::tag ::int, ::args [(byte 3)], ::length 1}
               0xce {::tag ::into, ::length 1}
               0xcf {::tag ::iret, ::length 1}
               0xd6 {::tag ::setalc, ::length 1}
               0xd7 {::tag ::xlat, ::length 1}
               0xe0 {::tag ::loopne, ::args [::rel8], ::length 1}
               0xe1 {::tag ::loope, ::args [::rel8], ::length 1}
               0xe2 {::tag ::loop, ::args [::rel8], ::length 1}
               0xe3 {::tag ::jcxz, ::args [::rel8], ::length 1}
               0xe4 {::tag ::in, ::args [::r/al ::imm8], ::length 1}
               0xe5 {::tag ::in, ::args [::r/ax ::imm8], ::length 1}
               0xe6 {::tag ::out, ::args [::imm8 ::r/al], ::length 1}
               0xe7 {::tag ::out, ::args [::imm8 ::r/ax], ::length 1}
               0xe8 {::tag ::call, ::args [::rel16], ::length 1}
               0xe9 {::tag ::jmp, ::args [::rel16], ::length 1}
               0xeb {::tag ::jmp, ::args [::rel8], ::length 1}
               0xec {::tag ::in, ::args [::r/al ::r/dx], ::length 1}
               0xed {::tag ::in, ::args [::r/ax ::r/dx], ::length 1}
               0xee {::tag ::out, ::args [::r/dx ::r/al], ::length 1}
               0xef {::tag ::out, ::args [::r/dx ::r/ax], ::length 1}
               0xf1 {::tag ::icebp, ::length 1}
               0xf4 {::tag ::hlt, ::length 1}
               0xf5 {::tag ::cmc, ::length 1}
               0xf8 {::tag ::clc, ::length 1}
               0xf9 {::tag ::stc, ::length 1}
               0xfa {::tag ::cli, ::length 1}
               0xfb {::tag ::sti, ::length 1}
               0xfc {::tag ::cld, ::length 1}
               0xfd {::tag ::std, ::length 1}})


(def one-byte-ext
  {0x0080 {::tag ::add, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0180 {::tag ::or, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0280 {::tag ::adc, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0380 {::tag ::sbb, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0480 {::tag ::and, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0580 {::tag ::sub, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0680 {::tag ::xor, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0780 {::tag ::cmp, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0082 {::tag ::add, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0182 {::tag ::or, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0282 {::tag ::adc, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0382 {::tag ::sbb, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0482 {::tag ::and, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0582 {::tag ::sub, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0682 {::tag ::xor, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0782 {::tag ::cmp, ::args [::r-or-m8 ::imm8], ::length 2}
   0x0083 {::tag ::add, ::args [::r-or-m16 ::imm8e], ::length 2}
   0x0183 {::tag ::or, ::args [::r-or-m16 ::imm8e], ::length 2}
   0x0283 {::tag ::adc, ::args [::r-or-m16 ::imm8e], ::length 2}
   0x0383 {::tag ::sbb, ::args [::r-or-m16 ::imm8e], ::length 2}
   0x0483 {::tag ::and, ::args [::r-or-m16 ::imm8e], ::length 2}
   0x0583 {::tag ::sub, ::args [::r-or-m16 ::imm8e], ::length 2}
   0x0683 {::tag ::xor, ::args [::r-or-m16 ::imm8e], ::length 2}
   0x0783 {::tag ::cmp, ::args [::r-or-m16 ::imm8e], ::length 2}})

(defn- word [bytes]
  (+ (first bytes) (bit-shift-left (second bytes) 8)))

(defn signed-byte [v]
  (if (< 0x7f v) (- v 0x100) v))

(defn signed-word [v]
  (if (< 0x7fff v) (- v 0x10000) v))

(defn byte-to-word [v]
  (if (< v 0x80) v (bit-or 0xff00 v)))


(defn- decode-reg [regs offset modrm]
  (regs (bit-and 0x07 (bit-shift-right modrm offset))))

(defn- decode-memrd [modrm]
  (memrd (bit-and 0x07 modrm)))

(defn- decode-memr [modrm]
  (memr (bit-and 0x07 modrm)))

(defn- conj-not-zero [coll val]
  (if (zero? val)
    coll
    (conj coll val)))

(defn- decode-r-or-m [regs modrm bytes]
  (let [mod (bit-and 0xc0 modrm)]
    (cond
      (= 0xc0 mod) [(decode-reg regs 0 modrm) 0]
      (= 0x40 mod) (let [d (signed-byte (first bytes))
                         ptr (decode-memrd modrm)]
                     [(conj-not-zero ptr d) 1])
      (= 0x80 mod) (let [d (signed-word (word bytes))
                         ptr (decode-memrd modrm)]
                     [(conj-not-zero ptr d) 2])
      :else (let [mr (decode-memr modrm)]
              (if (= mr [::imm16]) [[(word bytes)] 2] [mr 0])))))

(defn- decode-arg [arg modrm bytes]
  (case arg
    ::r8 [(decode-reg regs8 3 modrm) 0]
    ::r-or-m8 (decode-r-or-m regs8 modrm bytes)
    ::r16 [(decode-reg regs16 3 modrm) 0]
    ::r-or-m16 (decode-r-or-m regs16 modrm bytes)
    ::imm8 [(first bytes) 1]
    ::rel8 [(signed-byte (first bytes)) 1]
    ::imm16 [(word bytes) 2]
    ::imm8e [(byte-to-word (first bytes)) 1]
    ::rel16 [(signed-word (word bytes)) 2]
    nil))


(defn- decode-instr-arg [instr n bytes]
  (if-let [arg (decode-arg (get (::args instr) n) (second bytes) (drop (::length instr) bytes))]
    (update (assoc-in instr [::args n] (arg 0)) ::length #(+ % (arg 1)))
    instr))


(defn- ext-opcode [bytes]
  (bit-or (first bytes)
          (bit-and 0x0700 (bit-shift-left (second bytes) 5))))


(defn decode [bytes]
  (when-let [instr (or (one-byte (first bytes))
                       (one-byte-ext (ext-opcode bytes)))]
    (let [instr (decode-instr-arg instr 0 bytes)
          instr (decode-instr-arg instr 1 bytes)
          instr (decode-instr-arg instr 2 bytes)]
      instr)))
