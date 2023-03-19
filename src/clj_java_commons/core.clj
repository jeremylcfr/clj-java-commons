(ns clj-java-commons.core
  (:require [clj-java-commons.coerce :refer [->clj ->java ->string]])
  (:import [java.util Arrays])
  (:refer-clojure :exclude [/ pos? neg?]))

;;////////////////////////////////////////////////////////////////////////////
;;============================================================================
;;                                  CLASSES
;;============================================================================
;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

;;==================================================
;;=======================BYTE=======================
;;==================================================

(def byte-array-class
  "Byte array class pointer,
   i.e. byte[]"
  (Class/forName "[B"))

(def bbyte-array-class
  "2D byte array class pointer,
   i.e. byte[][]"
  (Class/forName "[[B"))

;;=====================================================
;;=======================INTEGER=======================
;;=====================================================

(def int-array-class
  "Int array class pointer,
   i.e. int[]"
  (Class/forName "[I"))

(def iint-array-class
  "2D int array pointer,
   i.e. int[][]"
  (Class/forName "[[I"))

;;==================================================
;;=======================LONG=======================
;;==================================================

(def long-array-class
  "Long array class pointer,
   i.e. long[]"
  (Class/forName "[J"))

(def llong-array-class
  "2D long array class pointer,
   i.e. long[][]"
  (Class/forName "[[J"))

;;===================================================
;;=======================FLOAT=======================
;;===================================================

(def float-array-class
  "Float array class pointer,
   i.e. float[]"
  (Class/forName "[F"))

(def ffloat-array-class
  "2D float array class pointer,
   i.e. float[][]"
  (Class/forName "[[F"))

;;====================================================
;;=======================DOUBLE=======================
;;====================================================

(def double-array-class
  "Double array class pointer,
   i.e. double[]"
  (Class/forName "[D"))

(def ddouble-array-class
  "2D double array pointer,
   i.e. double[][]"
  (Class/forName "[[D"))

;;==================================================
;;=======================CHAR=======================
;;================================================== 

(def char-array-class
  "Char array class pointer,
   i.e. char[]"
   (Class/forName "[C"))

(def cchar-array-class
  "2D char array class pointer,
   i.e. char[][]"
   (Class/forName "[[C"))

;;////////////////////////////////////////////////////////////////////////////
;;============================================================================
;;                                  OPERATORS
;;============================================================================
;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

(defn /
  "Overrides clojure.core to never return
   a clojure.lang Ratio without thinking about
   it.
   Allows a maximum of two arguments"
  (^double [^double a]
   (clojure.core// 1.0 a))
  (^double [^double a ^double b]
   (clojure.core// a b)))

(intern *ns* '++ @#'clojure.core/inc)
(intern *ns* '-- @#'clojure.core/dec)

;;////////////////////////////////////////////////////////////////////////////
;;============================================================================
;;                                PREDICATES
;;============================================================================
;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

;;===================================================
;;=======================TYPES=======================
;;===================================================

;;=================Byte===============

(defn byte-array?
  "Returns true when input is of type byte[]"
  [obj]
  (instance? byte-array-class obj))

(defn bbyte-array?
  "Returns true when input is of type byte[][]"
  [obj]
  (instance? bbyte-array-class obj))

;;=================Integer===============

(defn int-array?
  "Returns true when input is of type int[]"
  [obj]
  (instance? int-array-class obj))

(defn iint-array?
  "Returns true when input is of type int[][]"
  [obj]
  (instance? iint-array-class obj))

;;=================Long===============

(defn long-array?
  "Returns true when input is of type long[]"
  [obj]
  (instance? long-array-class obj))

(defn llong-array?
  "Returns true when input is of type long[][]"
  [obj]
  (instance? llong-array-class obj))

;;=================Float===============

(defn float-array?
  "Returns true when input is of type float[]"
  [obj]
  (instance? float-array-class obj))

(defn ffloat-array?
  "Returns true when input is of type float[][]"
  [obj]
  (instance? ffloat-array-class obj))

;;=================Double===============

(defn double-array?
  "Returns true when input is a double[]"
  [obj]
  (instance? double-array-class obj))

(defn ddouble-array?
  "Returns true when input is of type double[][]"
  [obj]
  (instance? ddouble-array-class obj))

;;=================Char===============

(defn char-array?
  "Returns true when input is a char[]"
  [obj]
  (instance? char-array-class obj))

(defn cchar-array?
  "Returns true when input is of type char[][]"
  [obj]
  (instance? cchar-array-class obj))

;;============================================================
;;=======================SPECIAL VALUES=======================
;;============================================================

;; Legacy interop
(def nan? NaN?)

;;======================================================
;;=======================EQUALITY=======================
;;======================================================

;;=================0D===============

(def not== (complement ==))

;;=================1D===============

(defn ba=
  "Byte array equality of content"
  [^bytes a1 ^bytes a2]
  (Arrays/equals a1 a2))

(defn ia=
  "Int arrays equality of content"
  [^ints a1 ^ints a2]
  (Arrays/equals a1 a2))

(defn la=
  "Long arrays equality of content"
  [^longs a1 ^longs a2]
  (Arrays/equals a1 a2))

(defn fa=
  "Float arrays equality of content"
  [^floats a1 ^floats a2]
  (Arrays/equals a1 a2))

(defn da=
  "Double arrays equality of content"
  [^doubles a1 ^doubles a2]
  (Arrays/equals a1 a2))

(defn ca=
  "Char arrays equality of content"
  [^chars a1 ^chars a2]
  (Arrays/equals a1 a2))

;;==================================================
;;=======================SIGN=======================
;;==================================================

;;=================Unstrict===============

(defn pos?
  "Returns true when the input is
   positive (in R+)"
  [x]
  (<= 0 x))

(defn neg?
  "Returns true when the input is
   negative (in R-)"
  [x]
  (>= 0 x))

;;=================Strict===============

(def strictly-pos?
  "Returns true when the input is
   strictly positive (in R+*)"
  clojure.core/pos?)

(def strictly-neg?
  "Returns true when the input is
   strictly negative (in R-*)"
  clojure.core/neg?)

;;////////////////////////////////////////////////////////////////////////////
;;============================================================================
;;                                  COERCION
;;============================================================================
;;\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

;;===========================================================
;;=======================ONE DIMENSION=======================
;;===========================================================

;;=================Byte===============

(defn ->byte-array
  "Coerces to byte[] but does not
   build a new instance when input is already
   as such"
  ^bytes
  [coll]
  (if (byte-array? coll)
    coll
    (byte-array coll)))

;;=================Integer===============

(defn ->int-array
  "Coerces to int[] but does not
   build a new instance when input is already
   as such"
  ^ints
  [coll]
  (if (int-array? coll)
    coll
    (int-array coll)))

;;=================Long===============

(defn ->long-array
  "Coerces to long[] but does not
   build a new instance when input is already
   as such"
  ^longs
  [coll]
  (if (long-array? coll)
    coll
    (long-array coll)))

;;=================Float===============

(defn ->float-array
  "Coerces to float[] but does not
   build a new instance when input is already
   as such"
  ^floats
  [coll]
  (if (float-array? coll)
    coll
    (float-array coll)))

;;=================Double===============

(defn ->double-array
  "Coerces to double[] but does not
   build a new instance when input is already
   as such"
  ^doubles
  [coll]
  (if (double-array? coll)
    coll
    (double-array coll)))

;;=================Char===============

(defn ->char-array
  "Coerces to char[] but does not
   build a new instance when input is already
   as such"
  ^chars
  [coll]
  (if (char-array? coll)
    coll
    (char-array coll)))

;;============================================================
;;=======================TWO DIMENSIONS=======================
;;============================================================

;;=================Byte===============

(defn bbyte-array
  "Coerces to byte[][]. Builds a new instance
   whatever type input is (especially byte[][]).
   Use ->bbyte-array if you wan to favor byte[][]"
  #^"[[B"
  [data]
  (->> (map byte-array data)
       (into-array byte-array-class)))

(defn ->bbyte-array
  "Coerces to byte[][]. Builds a new instance
   unless input is already of this type"
  #^"[[B"
  [data]
  (if (bbyte-array? data)
    data
    (->> (map ->byte-array data)
         (into-array byte-array-class))))

;;=================Integer===============

(defn iint-array
  "Coerces to int[][]. Builds a new instance
   whatever type input is (especially int[][]).
   Use ->iint-array if you wan to favor int[][]"
  #^"[[I"
  [data]
  (->> (map int-array data)
       (into-array int-array-class)))

(defn ->iint-array
  "Coerces to int[][]. Builds a new instance
   unless input is already of this type"
  #^"[[I"
  [data]
  (if (iint-array? data)
    data
    (->> (map ->int-array data)
         (into-array int-array-class))))

;;=================Long===============

(defn llong-array
  "Coerces to long[][]. Builds a new instance
   whatever type input is (especially long[][]).
   Use ->llong-array if you wan to favor long[][]"
  #^"[[J"
  [data]
  (->> (map long-array data)
       (into-array long-array-class)))

(defn ->llong-array
  "Coerces to long[][]. Builds a new instance
   unless input is already of this type"
  #^"[[J"
  [data]
  (if (llong-array? data)
    data
    (->> (map ->long-array data)
         (into-array long-array-class))))

;;=================Float===============

(defn ffloat-array
  "Coerces to float[][]. Builds a new instance
   whatever type input is (especially float[][]).
   Use ->ffloat-array if you wan to favor float[][]"
  #^"[[F"
  [data]
  (->> (map float-array data)
       (into-array float-array-class)))

(defn ->ffloat-array
  "Coerces to float[][]. Builds a new instance
   unless input is already of this type"
  #^"[[F"
  [data]
  (if (ffloat-array? data)
    data
    (->> (map ->float-array data)
         (into-array float-array-class))))

;;=================Double===============

(defn ddouble-array
  "Coerces to double[][]. Builds a new instance
   whatever type input is (especially double[][]).
   Use ->ddouble-array if you wan to favor double[][]"
  #^"[[D"
  [data]
  (->> (map double-array data)
       (into-array double-array-class)))

(defn ->ddouble-array
  "Coerces to double[][]. Builds a new instance
   unless input is already of this type"
  #^"[[D"
  [data]
  (if (ddouble-array? data)
    data
    (->> (map ->double-array data)
         (into-array double-array-class))))

;;=================Char===============

(defn cchar-array
  "Coerces to char[][]. Builds a new instance
   whatever type input is (especially char[][]).
   Use ->cchar-array if you wan to favor char[][]"
  #^"[[C"
  [data]
  (->> (map char-array data)
       (into-array char-array-class)))

(defn ->cchar-array
  "Coerces to char[][]. Builds a new instance
   unless input is already of this type"
  #^"[[C"
  [data]
  (if (cchar-array? data)
    data
    (->> (map ->char-array data)
         (into-array char-array-class))))

;;=====================================================
;;=======================CLOJURE=======================
;;=====================================================

;;=================1D array===============

(defmethod ->clj byte-array-class
  [^bytes obj]
  (vec obj))

(defmethod ->clj int-array-class
  [^ints obj]
  (vec obj))

(defmethod ->clj float-array-class
  [^floats obj]
  (vec obj))

(defmethod ->clj long-array-class
  [^longs obj]
  (vec obj))

(defmethod ->clj double-array-class
  [^doubles obj]
  (vec obj))

(defmethod ->clj char-array-class
  [^chars obj]
  (vec obj))

;;=================2D array===============

(defmethod ->clj bbyte-array-class
  [#^"[[B" obj]
  (mapv vec obj))

(defmethod ->clj iint-array-class
  [#^"[[I" obj]
  (mapv vec obj))

(defmethod ->clj llong-array-class
  [#^"[[J" obj]
  (mapv vec obj))

(defmethod ->clj ffloat-array-class
  [#^"[[F" obj]
  (mapv vec obj))

(defmethod ->clj ddouble-array-class
  [#^"[[D" obj]
  (mapv vec obj))

(defmethod ->clj cchar-array-class
  [#^"[[C" obj]
  (mapv vec obj))

;;==================================================
;;=======================JAVA=======================
;;==================================================

;;=================1D array===============

(defmethod ->java byte-array-class
  ^bytes
  [^bytes obj]
  obj)

(defmethod ->java int-array-class
  ^ints
  [^ints obj]
  obj)

(defmethod ->java float-array-class
  ^floats
  [^floats obj]
  obj)

(defmethod ->java long-array-class
  ^longs
  [^longs obj]
  obj)

(defmethod ->java double-array-class
  ^doubles
  [^doubles obj]
  obj)

(defmethod ->java char-array-class
  ^chars
  [^chars obj]
  obj)

;;=================2D array===============

(defmethod ->java bbyte-array-class
  #^"[[B"
  [#^"[[B" obj]
  obj)

(defmethod ->java iint-array-class
  #^"[[I"
  [#^"[[I" obj]
  obj)

(defmethod ->java llong-array-class
  #^"[[J"
  [#^"[[J" obj]
  obj)

(defmethod ->java ffloat-array-class
  #^"[[F"
  [#^"[[F" obj]
  obj)

(defmethod ->java ddouble-array-class
  #^"[[D"
  [#^"[[D" obj]
  obj)

(defmethod ->java cchar-array-class
  #^"[[C"
  [#^"[[C" obj]
  obj)

;;====================================================
;;=======================STRING=======================
;;====================================================

;;=================1D array===============

(defmethod ->string byte-array-class
  [^bytes obj]
  (Arrays/toString obj))

(defmethod ->string int-array-class
  [^ints obj]
  (Arrays/toString obj))

(defmethod ->string long-array-class
  [^longs obj]
  (Arrays/toString obj))

(defmethod ->string float-array-class
  [^floats obj]
  (Arrays/toString obj))

(defmethod ->string double-array-class
  [^doubles obj]
  (Arrays/toString obj))

(defmethod ->string char-array-class
  [^chars obj]
  (Arrays/toString obj))

;;=================2D array===============

;; Hinting is useless since the Java function itself
;; is typed with Object[]

(defmethod ->string bbyte-array-class
  [obj]
  (Arrays/deepToString obj))

(defmethod ->string iint-array-class
  [obj]
  (Arrays/deepToString obj))

(defmethod ->string llong-array-class
  [obj]
  (Arrays/deepToString obj))

(defmethod ->string ffloat-array-class
  [obj]
  (Arrays/deepToString obj))

(defmethod ->string ddouble-array-class
  [obj]
  (Arrays/deepToString obj))

(defmethod ->string cchar-array-class
  [obj]
  (Arrays/deepToString obj))

