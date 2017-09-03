(ns oumu.cpu.instructions
  (:require [oumu.cpu.registers :as r]))

(def regs8 [::r/al ::r/cl ::r/dl ::r/bl ::r/ah ::r/ch ::r/dh ::r/bh])

(def regs16 [::r/ax ::r/cx ::r/dx ::r/bx ::r/sp ::r/bp ::r/si ::r/di])

(def sregs [::r/es ::r/cs ::r/ss ::r/ds ::r/fs ::r/gs ::invalid ::invalid])

(def fregs [::r/st0 ::r/st1 ::r/st2 ::r/st3 ::r/st4 ::r/st5 ::r/st6 ::r/st7])

(def memr [[::r/bx ::r/si]
           [::r/bx ::r/di]
           [::r/bp ::r/si]
           [::r/bp ::r/di]
           [::r/si]
           [::r/di]
           ::ptr16
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
               0x62 {::tag ::bound, ::args [::r16 ::r-or-m16], ::length 2}
               0x63 {::tag ::arpl, ::args [::r-or-m16 ::r16], ::length 2}
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
               0x84 {::tag ::test, ::args [::r-or-m8 ::r8], ::length 2}
               0x85 {::tag ::test, ::args [::r-or-m16 ::r16], ::length 2}
               0x86 {::tag ::xchg, ::args [::r8 ::r-or-m8], ::length 2}
               0x87 {::tag ::xchg, ::args [::r16 ::r-or-m16], ::length 2}
               0x88 {::tag ::mov, ::args [::r-or-m8 ::r8], ::length 2}
               0x89 {::tag ::mov, ::args [::r-or-m16 ::r16], ::length 2}
               0x8a {::tag ::mov, ::args [::r8 ::r-or-m8], ::length 2}
               0x8b {::tag ::mov, ::args [::r16 ::r-or-m16], ::length 2}
               0x8c {::tag ::mov, ::args [::r-or-m16 ::sreg], ::length 2}
               0x8d {::tag ::lea, ::args [::r16 ::m], ::length 2}
               0x8e {::tag ::mov, ::args [::sreg ::r-or-m16], ::length 2}
               0x90 {::tag ::nop, ::length 1}
               0x91 {::tag ::xchg, ::args [::r/cx ::r/ax] ::length 1}
               0x92 {::tag ::xchg, ::args [::r/dx ::r/ax] ::length 1}
               0x93 {::tag ::xchg, ::args [::r/bx ::r/ax] ::length 1}
               0x94 {::tag ::xchg, ::args [::r/sp ::r/ax] ::length 1}
               0x95 {::tag ::xchg, ::args [::r/bp ::r/ax] ::length 1}
               0x96 {::tag ::xchg, ::args [::r/si ::r/ax] ::length 1}
               0x97 {::tag ::xchg, ::args [::r/di ::r/ax] ::length 1}
               0x98 {::tag ::cbw, ::length 1}
               0x99 {::tag ::cwd, ::length 1}
               0x9a {::tag ::call, ::args [::far-addr16], ::length 1}
               0x9b {::tag ::fwait, ::length 1}
               0x9c {::tag ::pushf, ::length 1}
               0x9d {::tag ::popf, ::length 1}
               0x9e {::tag ::sahf, ::length 1}
               0x9f {::tag ::lahf, ::length 1}
               0xa0 {::tag ::mov, ::args [::r/al ::ptr16], ::length 1}
               0xa1 {::tag ::mov, ::args [::r/ax ::ptr16], ::length 1}
               0xa2 {::tag ::mov, ::args [::ptr16 ::r/al], ::length 1}
               0xa3 {::tag ::mov, ::args [::ptr16 ::r/ax], ::length 1}
               0xa4 {::tag ::movsb, ::length 1}
               0xa5 {::tag ::movsw, ::length 1}
               0xa6 {::tag ::cmpsb, ::length 1}
               0xa7 {::tag ::cmpsw, ::length 1}
               0xa8 {::tag ::test, ::args [::r/al ::imm8], ::length 1}
               0xa9 {::tag ::test, ::args [::r/ax ::imm16], ::length 1}
               0xaa {::tag ::stosb, ::length 1}
               0xab {::tag ::stosw, ::length 1}
               0xac {::tag ::lodsb, ::length 1}
               0xad {::tag ::lodsw, ::length 1}
               0xae {::tag ::scasb, ::length 1}
               0xaf {::tag ::scasw, ::length 1}
               0xb0 {::tag ::mov, ::args [::r/al ::imm8], ::length 1}
               0xb1 {::tag ::mov, ::args [::r/cl ::imm8], ::length 1}
               0xb2 {::tag ::mov, ::args [::r/dl ::imm8], ::length 1}
               0xb3 {::tag ::mov, ::args [::r/bl ::imm8], ::length 1}
               0xb4 {::tag ::mov, ::args [::r/ah ::imm8], ::length 1}
               0xb5 {::tag ::mov, ::args [::r/ch ::imm8], ::length 1}
               0xb6 {::tag ::mov, ::args [::r/dh ::imm8], ::length 1}
               0xb7 {::tag ::mov, ::args [::r/bh ::imm8], ::length 1}
               0xb8 {::tag ::mov, ::args [::r/ax ::imm16], ::length 1}
               0xb9 {::tag ::mov, ::args [::r/cx ::imm16], ::length 1}
               0xba {::tag ::mov, ::args [::r/dx ::imm16], ::length 1}
               0xbb {::tag ::mov, ::args [::r/bx ::imm16], ::length 1}
               0xbc {::tag ::mov, ::args [::r/sp ::imm16], ::length 1}
               0xbd {::tag ::mov, ::args [::r/bp ::imm16], ::length 1}
               0xbe {::tag ::mov, ::args [::r/si ::imm16], ::length 1}
               0xbf {::tag ::mov, ::args [::r/di ::imm16], ::length 1}
               0xc2 {::tag ::ret, ::args [::imm16], ::length 1}
               0xc3 {::tag ::ret, ::length 1}
               0xc4 {::tag ::les, ::args [::r16 ::m], ::length 2}
               0xc5 {::tag ::lds, ::args [::r16 ::m], ::length 2}
               0xc8 {::tag ::enter, ::args [::imm16 ::imm8], ::length 1}
               0xc9 {::tag ::leave, ::length 1}
               0xca {::tag ::retf, ::args [::imm16], ::length 1}
               0xcb {::tag ::retf, ::length 1}
               0xcc {::tag ::int, ::args [(byte 3)], ::length 1}
               0xcd {::tag ::int, ::args [::imm8], ::length 1}
               0xce {::tag ::into, ::length 1}
               0xcf {::tag ::iret, ::length 1}
               0xd4 {::tag ::aam, ::args [::r/al ::r/ah ::imm8], ::length 1}
               0xd5 {::tag ::aad, ::args [::r/al ::r/ah ::imm8], ::length 1}
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
               0xea {::tag ::jmp, ::args [::far-addr16], ::length 1}
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
   0x0081 {::tag ::add, ::args [::r-or-m16 ::imm16], ::length 2}
   0x0181 {::tag ::or, ::args [::r-or-m16 ::imm16], ::length 2}
   0x0281 {::tag ::adc, ::args [::r-or-m16 ::imm16], ::length 2}
   0x0381 {::tag ::sbb, ::args [::r-or-m16 ::imm16], ::length 2}
   0x0481 {::tag ::and, ::args [::r-or-m16 ::imm16], ::length 2}
   0x0581 {::tag ::sub, ::args [::r-or-m16 ::imm16], ::length 2}
   0x0681 {::tag ::xor, ::args [::r-or-m16 ::imm16], ::length 2}
   0x0781 {::tag ::cmp, ::args [::r-or-m16 ::imm16], ::length 2}
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
   0x0783 {::tag ::cmp, ::args [::r-or-m16 ::imm8e], ::length 2}
   0x008f {::tag ::pop, ::args [::r-or-m16], ::length 2}
   0x00c0 {::tag ::rol, ::args [::r-or-m8 ::imm8], ::length 2}
   0x01c0 {::tag ::ror, ::args [::r-or-m8 ::imm8], ::length 2}
   0x02c0 {::tag ::rcl, ::args [::r-or-m8 ::imm8], ::length 2}
   0x03c0 {::tag ::rcr, ::args [::r-or-m8 ::imm8], ::length 2}
   0x04c0 {::tag ::shl, ::args [::r-or-m8 ::imm8], ::length 2}
   0x05c0 {::tag ::shr, ::args [::r-or-m8 ::imm8], ::length 2}
   0x06c0 {::tag ::shl, ::args [::r-or-m8 ::imm8], ::length 2}
   0x07c0 {::tag ::sar, ::args [::r-or-m8 ::imm8], ::length 2}
   0x00c1 {::tag ::rol, ::args [::r-or-m16 ::imm8], ::length 2}
   0x01c1 {::tag ::ror, ::args [::r-or-m16 ::imm8], ::length 2}
   0x02c1 {::tag ::rcl, ::args [::r-or-m16 ::imm8], ::length 2}
   0x03c1 {::tag ::rcr, ::args [::r-or-m16 ::imm8], ::length 2}
   0x04c1 {::tag ::shl, ::args [::r-or-m16 ::imm8], ::length 2}
   0x05c1 {::tag ::shr, ::args [::r-or-m16 ::imm8], ::length 2}
   0x06c1 {::tag ::shl, ::args [::r-or-m16 ::imm8], ::length 2}
   0x07c1 {::tag ::sar, ::args [::r-or-m16 ::imm8], ::length 2}
   0x00c6 {::tag ::mov, ::args [::r-or-m8 ::imm8], ::length 2}
   0x00c7 {::tag ::mov, ::args [::r-or-m16 ::imm16], ::length 2}
   0x00d0 {::tag ::rol, ::args [::r-or-m8 1], ::length 2}
   0x01d0 {::tag ::ror, ::args [::r-or-m8 1], ::length 2}
   0x02d0 {::tag ::rcl, ::args [::r-or-m8 1], ::length 2}
   0x03d0 {::tag ::rcr, ::args [::r-or-m8 1], ::length 2}
   0x04d0 {::tag ::shl, ::args [::r-or-m8 1], ::length 2}
   0x05d0 {::tag ::shr, ::args [::r-or-m8 1], ::length 2}
   0x06d0 {::tag ::shl, ::args [::r-or-m8 1], ::length 2}
   0x07d0 {::tag ::sar, ::args [::r-or-m8 1], ::length 2}
   0x00d1 {::tag ::rol, ::args [::r-or-m16 1], ::length 2}
   0x01d1 {::tag ::ror, ::args [::r-or-m16 1], ::length 2}
   0x02d1 {::tag ::rcl, ::args [::r-or-m16 1], ::length 2}
   0x03d1 {::tag ::rcr, ::args [::r-or-m16 1], ::length 2}
   0x04d1 {::tag ::shl, ::args [::r-or-m16 1], ::length 2}
   0x05d1 {::tag ::shr, ::args [::r-or-m16 1], ::length 2}
   0x06d1 {::tag ::shl, ::args [::r-or-m16 1], ::length 2}
   0x07d1 {::tag ::sar, ::args [::r-or-m16 1], ::length 2}
   0x00d2 {::tag ::rol, ::args [::r-or-m8 ::r/cl], ::length 2}
   0x01d2 {::tag ::ror, ::args [::r-or-m8 ::r/cl], ::length 2}
   0x02d2 {::tag ::rcl, ::args [::r-or-m8 ::r/cl], ::length 2}
   0x03d2 {::tag ::rcr, ::args [::r-or-m8 ::r/cl], ::length 2}
   0x04d2 {::tag ::shl, ::args [::r-or-m8 ::r/cl], ::length 2}
   0x05d2 {::tag ::shr, ::args [::r-or-m8 ::r/cl], ::length 2}
   0x06d2 {::tag ::shl, ::args [::r-or-m8 ::r/cl], ::length 2}
   0x07d2 {::tag ::sar, ::args [::r-or-m8 ::r/cl], ::length 2}
   0x00d3 {::tag ::rol, ::args [::r-or-m16 ::r/cl], ::length 2}
   0x01d3 {::tag ::ror, ::args [::r-or-m16 ::r/cl], ::length 2}
   0x02d3 {::tag ::rcl, ::args [::r-or-m16 ::r/cl], ::length 2}
   0x03d3 {::tag ::rcr, ::args [::r-or-m16 ::r/cl], ::length 2}
   0x04d3 {::tag ::shl, ::args [::r-or-m16 ::r/cl], ::length 2}
   0x05d3 {::tag ::shr, ::args [::r-or-m16 ::r/cl], ::length 2}
   0x06d3 {::tag ::shl, ::args [::r-or-m16 ::r/cl], ::length 2}
   0x07d3 {::tag ::sar, ::args [::r-or-m16 ::r/cl], ::length 2}
   0x00d8 {::tag ::fadd, ::args [::st-or-m32real], ::length 2}
   0x01d8 {::tag ::fmul, ::args [::st-or-m32real], ::length 2}
   0x02d8 {::tag ::fcom, ::args [::st-or-m32real], ::length 2}
   0x03d8 {::tag ::fcomp, ::args [::st-or-m32real], ::length 2}
   0x04d8 {::tag ::fsub, ::args [::st-or-m32real], ::length 2}
   0x05d8 {::tag ::fsubr, ::args [::st-or-m32real], ::length 2}
   0x06d8 {::tag ::fdiv, ::args [::st-or-m32real], ::length 2}
   0x07d8 {::tag ::fdivr, ::args [::st-or-m32real], ::length 2}
   0x00d9 {::tag ::fld, ::args [::st-or-m32real], ::length 2}
   0x01d9 {::tag ::fxch, ::args [::st-only], ::length 2}
   0x02d9 {::tag ::fst, ::args [::m], ::length 2}
   0x03d9 {::tag ::fstp, ::args [::m], ::length 2}
   0x04d9 {::tag ::fldenvw, ::args [::m], ::length 2}
   0x05d9 {::tag ::fldcw, ::args [::m], ::length 2}
   0x06d9 {::tag ::fnstenvw, ::args [::m], ::length 2}
   0x07d9 {::tag ::fnstcw, ::args [::m], ::length 2}
   0x00da {::tag ::fiadd, ::args [::m], ::length 2}
   0x01da {::tag ::fimul, ::args [::m], ::length 2}
   0x02da {::tag ::ficom, ::args [::m], ::length 2}
   0x03da {::tag ::ficomp, ::args [::m], ::length 2}
   0x04da {::tag ::fisub, ::args [::m], ::length 2}
   0x05da {::tag ::fisubr, ::args [::m], ::length 2}
   0x06da {::tag ::fidiv, ::args [::m], ::length 2}
   0x07da {::tag ::fidivr, ::args [::m], ::length 2}
   0x00db {::tag ::fild, ::args [::m], ::length 2}
   0x02db {::tag ::fist, ::args [::m], ::length 2}
   0x03db {::tag ::fistp, ::args [::m], ::length 2}
   0x05db {::tag ::fld, ::args [::m], ::length 2}
   0x07db {::tag ::fstp, ::args [::m], ::length 2}
   0x00dc {::tag ::fadd, ::args [::st-or-m64real], ::length 2, ::type ::qword}
   0x01dc {::tag ::fmul, ::args [::st-or-m64real], ::length 2, ::type ::qword}
   0x02dc {::tag ::fcom, ::args [::m], ::length 2, ::type ::qword}
   0x03dc {::tag ::fcomp, ::args [::m], ::length 2, ::type ::qword}
   0x04dc {::tag ::fsub, ::args [::st-or-m64real], ::length 2, ::type ::qword}
   0x05dc {::tag ::fsubr, ::args [::st-or-m64real], ::length 2, ::type ::qword}
   0x06dc {::tag ::fdiv, ::args [::st-or-m64real], ::length 2, ::type ::qword}
   0x07dc {::tag ::fdivr, ::args [::st-or-m64real], ::length 2, ::type ::qword}
   0x00dd {::tag ::fld, ::args [::st-or-m64real], ::length 2, ::type ::qword}
   0x02dd {::tag ::fst, ::args [::st-or-m64real], ::length 2, ::type ::qword}
   0x03dd {::tag ::fstp, ::args [::st-or-m64real], ::length 2, ::type ::qword}
   0x04dd {::tag ::frstorw, ::args [::m], ::length 2}
   0x05dd {::tag ::fucomp, ::args [::st-only], ::length 2}
   0x06dd {::tag ::fnsavew, ::args [::m], ::length 2}
   0x07dd {::tag ::fnstsw, ::args [::m], ::length 2}
   0x00de {::tag ::fiadd, ::args [::m], ::length 2, ::type ::word}
   0x01de {::tag ::fimul, ::args [::m], ::length 2, ::type ::word}
   0x02de {::tag ::ficom, ::args [::m], ::length 2, ::type ::word}
   0x03de {::tag ::ficomp, ::args [::m], ::length 2, ::type ::word}
   0x04de {::tag ::fisub, ::args [::m], ::length 2, ::type ::word}
   0x05de {::tag ::fisubr, ::args [::m], ::length 2, ::type ::word}
   0x06de {::tag ::fidiv, ::args [::m], ::length 2, ::type ::word}
   0x07de {::tag ::fidivr, ::args [::m], ::length 2, ::type ::word}
   0x00df {::tag ::fild, ::args [::m], ::length 2, ::type ::word}
   0x02df {::tag ::fist, ::args [::m], ::length 2, ::type ::word}
   0x03df {::tag ::fistp, ::args [::m], ::length 2, ::type ::word}
   0x04df {::tag ::fbld, ::args [::m], ::length 2}
   0x05df {::tag ::fild, ::args [::m], ::length 2, ::type ::qword}
   0x06df {::tag ::fbstp, ::args [::m], ::length 2}
   0x07df {::tag ::fistp, ::args [::m], ::length 2, ::type ::qword}
   0x00f6 {::tag ::test, ::args [::r-or-m8 ::imm8], ::length 2}
   0x01f6 {::tag ::test, ::args [::r-or-m8 ::imm8], ::length 2}
   0x02f6 {::tag ::not, ::args [::r-or-m8], ::length 2}
   0x03f6 {::tag ::neg, ::args [::r-or-m8], ::length 2}
   0x04f6 {::tag ::mul, ::args [::r/ax ::r/al ::r-or-m8], ::length 2}
   0x05f6 {::tag ::imul, ::args [::r/ax ::r/al ::r-or-m8], ::length 2}
   0x06f6 {::tag ::div, ::args [::r/al ::r/ah ::r/ax ::r-or-m8], ::length 2}
   0x07f6 {::tag ::idiv, ::args [::r/al ::r/ah ::r/ax ::r-or-m8], ::length 2}
   0x00f7 {::tag ::test, ::args [::r-or-m16 ::imm16], ::length 2}
   0x01f7 {::tag ::test, ::args [::r-or-m16 ::imm16], ::length 2}
   0x02f7 {::tag ::not, ::args [::r-or-m16], ::length 2}
   0x03f7 {::tag ::neg, ::args [::r-or-m16], ::length 2}
   0x04f7 {::tag ::mul, ::args [::r/dx ::r/ax ::r-or-m16], ::length 2}
   0x05f7 {::tag ::imul, ::args [::r/dx ::r/ax ::r-or-m16], ::length 2}
   0x06f7 {::tag ::div, ::args [::r/dx ::r/ax ::r-or-m16], ::length 2}
   0x07f7 {::tag ::idiv, ::args [::r/dx ::r/ax ::r-or-m16], ::length 2}
   0x00fe {::tag ::inc, ::args [::r-or-m8], ::length 2, ::type ::byte}
   0x01fe {::tag ::dec, ::args [::r-or-m8], ::length 2, ::type ::byte}
   0x00ff {::tag ::inc, ::args [::r-or-m16], ::length 2, ::type ::word}
   0x01ff {::tag ::dec, ::args [::r-or-m16], ::length 2, ::type ::word}
   0x02ff {::tag ::call, ::args [::r-or-m16], ::length 2}
   0x03ff {::tag ::callf, ::args [::m], ::length 2}
   0x04ff {::tag ::jmp, ::args [::r-or-m16], ::length 2}
   0x05ff {::tag ::jmpf, ::args [::m], ::length 2}
   0x06ff {::tag ::push, ::args [::r-or-m16], ::length 2}})


(def one-byte-ext-st
  {0xc0dd {::tag ::ffree, ::args [::st-only], ::length 2}
   0xc4dd {::tag ::fucom, ::args [::st-only], ::length 2}
   0xc0de {::tag ::faddp, ::args [::st-only], ::length 2}
   0xc1de {::tag ::fmulp, ::args [::st-only], ::length 2}
   0xc4de {::tag ::fsubrp, ::args [::st-only], ::length 2}
   0xc5de {::tag ::fsubp, ::args [::st-only], ::length 2}
   0xc6de {::tag ::fdivrp, ::args [::st-only], ::length 2}
   0xc7de {::tag ::fdivp, ::args [::st-only], ::length 2}
   0xc0df {::tag ::ffreep, ::args [::st-only], ::length 2}})


(def two-byte
  {0x020f {::tag ::lar, ::args [::r16 ::r-or-m16], ::length 3}
   0x030f {::tag ::lsl, ::args [::r16 ::r-or-m16], ::length 3}
   0x060f {::tag ::clts, ::length 2}
   0xd0d9 {::tag ::fnop, ::length 2}
   0xd8d9 {::tag ::fnop, ::length 2}
   0xe0d9 {::tag ::fchs, ::length 2}
   0xe1d9 {::tag ::fabs, ::length 2}
   0xe4d9 {::tag ::ftst, ::length 2}
   0xe5d9 {::tag ::fxam, ::length 2}
   0xe8d9 {::tag ::fld1, ::length 2}
   0xe9d9 {::tag ::fldl2t, ::length 2}
   0xead9 {::tag ::fldl2e, ::length 2}
   0xebd9 {::tag ::fldpi, ::length 2}
   0xecd9 {::tag ::fldlg2, ::length 2}
   0xedd9 {::tag ::fldln2, ::length 2}
   0xeed9 {::tag ::fldz, ::length 2}
   0xf0d9 {::tag ::f2xm1, ::length 2}
   0xf1d9 {::tag ::fyl2x, ::length 2}
   0xf2d9 {::tag ::fptan, ::length 2}
   0xf3d9 {::tag ::fpatan, ::length 2}
   0xf4d9 {::tag ::fxtract, ::length 2}
   0xf5d9 {::tag ::fprem1, ::length 2}
   0xf6d9 {::tag ::fdecstp, ::length 2}
   0xf7d9 {::tag ::fincstp, ::length 2}
   0xf8d9 {::tag ::fprem, ::length 2}
   0xf9d9 {::tag ::fyl2xp1, ::length 2}
   0xfad9 {::tag ::fsqrt, ::length 2}
   0xfbd9 {::tag ::fsincos, ::length 2}
   0xfcd9 {::tag ::frndint, ::length 2}
   0xfdd9 {::tag ::fscale, ::length 2}
   0xfed9 {::tag ::fsin, ::length 2}
   0xffd9 {::tag ::fcos, ::length 2}
   0xe9da {::tag ::fucompp, ::length 2}
   0xe0db {::tag ::fneni, ::length 2}
   0xe1db {::tag ::fndisi, ::length 2}
   0xe2db {::tag ::fnclex, ::length 2}
   0xe3db {::tag ::fninit, ::length 2}
   0xe4db {::tag ::fnsetpm, ::length 2}
   0xe5db {::tag ::frstpm, ::length 2}
   0xf8db {::tag ::fnop, ::length 2}
   0xd0dc {::tag ::fnop, ::length 2}
   0xd8dc {::tag ::fnop, ::length 2}
   0xc8dd {::tag ::fnop, ::length 2}
   0xf0dd {::tag ::fnop, ::length 2}
   0xf8dd {::tag ::fnop, ::length 2}
   0xd0de {::tag ::fnop, ::length 2}
   0xd9de {::tag ::fcompp, ::length 2}
   0xc8df {::tag ::fnop, ::length 2}
   0xd0df {::tag ::fnop, ::length 2}
   0xd8df {::tag ::fnop, ::length 2}
   0xe0df {::tag ::fnstsw, ::args [::r/ax], ::length 2}
   0xf8df {::tag ::fnop, ::length 2}})


(def two-byte-ext
  {0x00000f {::tag ::sldt, ::args [::r-or-m16], ::length 3}
   0x01000f {::tag ::str, ::args [::r-or-m16], ::length 3}
   0x02000f {::tag ::lldt, ::args [::r-or-m16], ::length 3}
   0x03000f {::tag ::ltr, ::args [::r-or-m16], ::length 3}
   0x04000f {::tag ::verr, ::args [::r-or-m16], ::length 3}
   0x05000f {::tag ::verw, ::args [::r-or-m16], ::length 3}
   0x00010f {::tag ::sgdtw, ::args [::m], ::length 3}
   0x01010f {::tag ::sidtw, ::args [::m], ::length 3}
   0x02010f {::tag ::lgdtw, ::args [::m], ::length 3}
   0x03010f {::tag ::lidtw, ::args [::m], ::length 3}
   0x04010f {::tag ::smsw, ::args [::r-or-m16], ::length 3}
   0x06010f {::tag ::lmsw, ::args [::r-or-m16], ::length 3}})


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

(defn- decode-m [modrm bytes]
  (let [mod (bit-and 0xc0 modrm)]
    (case mod
      0x00 (let [mr (decode-memr modrm)]
             (if (= mr ::ptr16) [[(word bytes)] 2] [mr 0]))
      0x40 (let [d (signed-byte (first bytes))
                 ptr (decode-memrd modrm)]
             [(conj-not-zero ptr d) 1])
      0x80 (let [d (signed-word (word bytes))
                 ptr (decode-memrd modrm)]
             [(conj-not-zero ptr d) 2])
      [::invalid 0])))


(defn- decode-r-or-m [regs modrm bytes]
  (if (= 0xc0 (bit-and 0xc0 modrm))
    [(decode-reg regs 0 modrm) 0]
    (decode-m modrm bytes)))


(defn- decode-st-only [modrm bytes]
  [(if (= 0xc0 (bit-and 0xc0 modrm))
     (decode-reg fregs 0 modrm)
     ::invalid)
   0])


(defn- decode-arg [arg modrm bytes]
  (case arg
    ::r8 [(decode-reg regs8 3 modrm) 0]
    ::r-or-m8 (decode-r-or-m regs8 modrm bytes)
    ::r16 [(decode-reg regs16 3 modrm) 0]
    ::r-or-m16 (decode-r-or-m regs16 modrm bytes)
    ::m (decode-m modrm bytes)
    ::imm8 [(first bytes) 1]
    ::rel8 [(signed-byte (first bytes)) 1]
    ::imm16 [(word bytes) 2]
    ::imm8e [(byte-to-word (first bytes)) 1]
    ::rel16 [(signed-word (word bytes)) 2]
    ::sreg [(decode-reg sregs 3 modrm) 0]
    ::ptr16 [[(word bytes)] 2]
    ::far-addr16 [{::seg (word (drop 2 bytes)) ::off (word bytes)} 4]
    ::st-or-m32real (decode-r-or-m fregs modrm bytes)
    ::st-or-m64real (decode-r-or-m fregs modrm bytes)
    ::st-only (decode-st-only modrm bytes)
    nil))


(defn- decode-instr-arg [instr modrm n bytes]
  (if-let [arg (decode-arg (get (::args instr) n) modrm (drop (::length instr) bytes))]
    (update (assoc-in instr [::args n] (arg 0)) ::length #(+ % (arg 1)))
    instr))


(defn- ext-opcode [bytes]
  (bit-or (first bytes)
          (bit-and 0x0700 (bit-shift-left (second bytes) 5))))


(defn- ext2-opcode [bytes]
  (bit-or (first bytes)
          (bit-shift-left (second bytes) 8)
          (bit-and 0x070000 (bit-shift-left (second (next bytes)) 13))))


(defn- ext-opcode-st [bytes]
  (bit-or (first bytes)
          (bit-and 0x0700 (bit-shift-left (second bytes) 5))
          (bit-and 0xc000 (bit-shift-left (second bytes) 8))))


(defn decode [bytes]
  (when-let [instr (or (one-byte (first bytes))
                       (two-byte (word bytes))
                       (two-byte-ext (ext2-opcode bytes))
                       (one-byte-ext-st (ext-opcode-st bytes))
                       (one-byte-ext (ext-opcode bytes)))]
    (let [modrm (nth bytes (dec (::length instr)))
          instr (decode-instr-arg instr modrm 0 bytes)
          instr (decode-instr-arg instr modrm 1 bytes)
          instr (decode-instr-arg instr modrm 2 bytes)
          instr (decode-instr-arg instr modrm 3 bytes)
          instr (if (not-any? vector? (::args instr)) (dissoc instr ::type) instr)]
      (if (not-any? #(= ::invalid %) (::args instr))
        instr))))
