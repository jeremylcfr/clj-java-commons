(ns clj-java-commons.coerce)

;;////////////////////////////////////////////////////////////////////////////
;;============================================================================
;;                                  GENERIC
;;============================================================================
;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

;;===================================================
;;=======================->CLJ=======================
;;===================================================

;;=================Meta===============

(defmulti ->clj
  (fn [obj] (type obj)))

;;=================Implementation===============

(defmethod ->clj :default
  [obj]
  obj)

(defmethod ->clj java.util.HashMap
  [obj]
  (->clj (into {} obj)))

(defmethod ->clj clojure.lang.IPersistentMap
  [obj]
  (persistent!
   (reduce-kv
    (fn [agg k v]
      (assoc! agg k (->clj v)))
    (transient {}) obj)))

;;====================================================
;;=======================->JAVA=======================
;;====================================================

;;=================Meta===============

(defmulti ->java
  (fn [obj] (type obj)))

;;=================Implementation===============

(defmethod ->java :default
  [obj]
  obj)

(defmethod ->java java.util.HashMap
  [obj]
  (->java (into {} obj)))

(defmethod ->java clojure.lang.IPersistentMap
  [obj]
  (persistent!
   (reduce-kv
    (fn [agg k v]
      (assoc! agg k (->java v)))
    (transient {}) obj)))

;;======================================================
;;=======================->STRING=======================
;;======================================================

;;=================Meta===============

(defmulti ->string
  (fn [obj] (type obj)))

;;=================Implementation===============

(defmethod ->string :default
  [obj]
  (str obj))

