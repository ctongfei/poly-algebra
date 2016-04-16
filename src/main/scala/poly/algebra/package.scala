package poly

/**
 * `Poly-algebra` contains a collection of typeclass abstractions over common algebraic structures,
 * and provides a framework for generic math programming in Scala.
 * @author Tongfei Chen
 */
package object algebra {

  type Id[X] = X

  type implicitNotFound = scala.annotation.implicitNotFound

}
