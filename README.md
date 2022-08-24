# clj-java-commons

Fundamental programmatic Java interop tools, mainly targetted at numerical libraries.
These are mostly used for higher level libraries.

## Usage

### Project inclusion

Include it in your project :

```clojure
[jeremylcfr/clj-java-commons "0.1.0-SNAPSHOT"]
```

This is not available for download for now, you must install it locally.

### Preliminary note about naming

This library uses some naming hints about what functions do.

First, you have to distinguish unconditionnal builders to conditionnal coercers.

Conditional coercers generally check if input is already of target type and then coerce it if it is not of the given type. This is especially true for primitive arrays since Clojure chooses to reconstruct an object anyway. 
For other objects, the conditional coercers are mainly functions which can adress a relatively large number of representations with opinionated choices (nil conversion, etc.). The notation for conditional coercer is always prefixed with an arrow "->".
See an example above:


```clojure
;; This is the clojure core function which will build a new double[] even
;; if input is already an instance of this class
,, This is what we call an unconditional builder
(double-array [1 2 3])

;; This is the conditionnal coercer version
;; It will be a lot faster for double[] but will have a (really) minor
;; loss for other types compared to double-array
(->double-array [1 2 3])
```

Generally, "->" functions are for internal usage and/or to provide flexibility for the user.

Secondly, 2D arrays are named with the first letter repeated :

```clojure
byte-array   ===> bbyte-array
int-array    ===> iint-arrray
long-array   ===> llong-array
float-array  ===> ffloat-array
double-array ===> ddouble-array
```

### Content

Basically, this library contains opinionated standard ways to deal with primitives-related Java numerics or objects.
This is not a math library, the scope is only programmatic and the library acts as a middleware
between some Java objects and Clojure.
It redefines also some clojure.core functions to be more precise or to do not break code.

```clojure
;; Clojure.core redef
(/ 2 4) ;; 0.5, i.e. never return ratios as clojure.core// does
(neg? 0) ;; true, neg? means (<= x 0)
(strictly-neg? 0) ;; false, this is clojure.core/neg?
(++ 1) ;; 2 i.e. ++ = inc
(-- 1) ;; 0 (guess)

;; Opinionated builders
(->double-array [1 2 3])

;; Missing predicates
(double-array? (double-array [1 2 3])) ;; true
(ddouble-array? (ddouble-array [[1 2 3] [4 5 6]]) ;; true

;; Shared ->clj interface (defmulti)
(defn my-ns
  (:require [clj-numerics.coerce :refer [->clj]]))

(->clj (double-array [1 2 3])) ;; [1.0 2.0 3.0]
(->clj {:name "koala" :measures (int-array [1 2 3])}) ;; {:name "koala" :measures [1 2 3]}

;; Shared ->java interface, nothing there
;; Like ->clj, does nothing if no method is defined

;; Same for ->string to support parts not covered by clojure.core
(->string (double-array [1 2 3])) ;; "[1.0, 2.0, 3.0]"
;; If no dispatch is found, uses str
```

## Integration in upper-level libraries

As it has already been said, this library is mainly used for low-level
internal usage to favor performance over "clojure-is-the-king" approach.
While some functions can be used as standalone (pos? etc.), the intent is
mostly limited to that purpose.
Additionaly, the ->clj multimethod intends to normalize representations and
eventually to share among different upcoming frameworks.

## Dependencies

This library have no dependencies apart from clojure base stuff.
No dependency should be added since it is a base library, even if
it requires reinventing the wheel/copy-pasting for small utilities.

## License

Copyright © 2022 Jérémy Le Corguillé

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
