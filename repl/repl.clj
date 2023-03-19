(ns repl
  (:require [clojure.tools.namespace.repl :refer [refresh]] 
            [clj-java-commons.core :refer :all]
            [clj-java-commons.coerce :refer :all])
  (:refer-clojure :exclude [/ neg? pos?]))