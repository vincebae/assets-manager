(ns my.app
  (:require [my.example :as ex])
  (:gen-class))

(defn init
  [& _]
  (ex/init))

(defn start
  [system]
  (ex/start system))

(defn stop
  [system]
  (ex/stop system))

(defn -main
  [& args]
  (start (apply init args)))
