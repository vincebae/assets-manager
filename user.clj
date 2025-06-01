(ns user
  "Scratch pad for dev")

(require '[clj-commons.pretty.repl :as p]
         '[clojure.core.async :as a]
         '[clojure.java.io :as io]
         '[clojure.pprint :refer [pprint]]
         '[clojure.string :as s]
         '[clojure.test :as test]
         '[dev :refer [system stop! restart!]])

@system
(restart!)
(stop!)
