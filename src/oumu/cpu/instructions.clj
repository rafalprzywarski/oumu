(ns oumu.cpu.instructions
  (:require [oumu.cpu.registers :as r]))


(def one-byte {0x06 {:tag :push, :args [::r/es]}
               0x07 {:tag :pop, :args [::r/es]}
               0x0e {:tag :push, :args [::r/cs]}
               0x16 {:tag :push, :args [::r/ss]}
               0x17 {:tag :pop, :args [::r/ss]}
               0x1e {:tag :push, :args [::r/ds]}
               0x1f {:tag :pop, :args [::r/ds]}
               0x27 {:tag :daa}
               0x2f {:tag :das}
               0x37 {:tag :aaa}
               0x3f {:tag :aas}
               0x40 {:tag :inc, :args [::r/ax]}
               0x41 {:tag :inc, :args [::r/cx]}
               0x42 {:tag :inc, :args [::r/dx]}
               0x43 {:tag :inc, :args [::r/bx]}
               0x44 {:tag :inc, :args [::r/sp]}
               0x45 {:tag :inc, :args [::r/bp]}
               0x46 {:tag :inc, :args [::r/si]}
               0x47 {:tag :inc, :args [::r/di]}
               0x48 {:tag :dec, :args [::r/ax]}
               0x49 {:tag :dec, :args [::r/cx]}
               0x4a {:tag :dec, :args [::r/dx]}
               0x4b {:tag :dec, :args [::r/bx]}
               0x4c {:tag :dec, :args [::r/sp]}
               0x4d {:tag :dec, :args [::r/bp]}
               0x4e {:tag :dec, :args [::r/si]}
               0x4f {:tag :dec, :args [::r/di]}
               0x50 {:tag :push, :args [::r/ax]}
               0x51 {:tag :push, :args [::r/cx]}
               0x52 {:tag :push, :args [::r/dx]}
               0x53 {:tag :push, :args [::r/bx]}
               0x54 {:tag :push, :args [::r/sp]}
               0x55 {:tag :push, :args [::r/bp]}
               0x56 {:tag :push, :args [::r/si]}
               0x57 {:tag :push, :args [::r/di]}
               0x58 {:tag :pop, :args [::r/ax]}
               0x59 {:tag :pop, :args [::r/cx]}
               0x5a {:tag :pop, :args [::r/dx]}
               0x5b {:tag :pop, :args [::r/bx]}
               0x5c {:tag :pop, :args [::r/sp]}
               0x5d {:tag :pop, :args [::r/bp]}
               0x5e {:tag :pop, :args [::r/si]}
               0x5f {:tag :pop, :args [::r/di]}})

(defn decode [bytes]
  (one-byte (first bytes)))
