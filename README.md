## poly-algebra [![Build Status](https://travis-ci.org/ctongfei/poly-algebra.svg?branch=master)](https://travis-ci.org/ctongfei/poly-algebra)

`Poly-algebra` is a library that contains a hierarchy of typeclass abstractions for common algebraic structures and 
higher-kinded functional constructs that enables generic math programming in Scala.
Implicits, specialization and macros are used extensively to create an elegant API with virtually no performance cost.

This project is in pre-alpha stage and should not be deployed in production environments.

Import once for good:
```scala
import poly.algebra._           // Imports definitions for common algebraic structures
import poly.algebra.syntax._    // Enables syntactic sugars, generic math functions and default typeclass instances
```
Or, in a more fine-grained manner:
```scala
import poly.algebra._           // Imports definitions for common algebraic structures
import poly.algebra.implicits._ // Brings default structures of system types into scope
import poly.algebra.function._  // Imports generic version of common math functions
import poly.algebra.ops._       // Enables generic infix operators with no overhead 
import poly.algebra.hkt._       // Imports definitions for higher-kinded typeclasses
import poly.algebra.hkt.ops._   // Enables operators on higher-kinded types
```

`Poly-algebra` is inspired by [Spire](https://github.com/non/spire) and [Scalaz](http://github.com/scalaz/scalaz).

#### Installation
```scala
libraryDependencies += "me.tongfei" %% "poly-algebra" % "0.3.5-SNAPSHOT"
```

