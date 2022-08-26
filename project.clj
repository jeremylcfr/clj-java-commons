(defproject jeremylcfr/clj-java-commons "0.1.0-SNAPSHOT"
  :description "Various Java interop and coercion shared material"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]] 
  :profiles {:dev {:source-paths ["repl"]
                   :dependencies [[org.clojure/tools.namespace "1.3.0"]]}}
  :repl-options {:init-ns repl})
