package poly

/**
 * `Poly-algebra` contains a collection of typeclass abstractions over common algebraic structures,
 * and provides a framework for generic math programming in Scala.
 * @author Tongfei Chen
 */
package object algebra {

  type Id[X] = X

  private[algebra] type implicitNotFound = scala.annotation.implicitNotFound

  /** ASCII-compliant symbolic alias for bijections. */
  type <=>[X, Y] = Bijection[X, Y]

  /** Symbolic alias for bijections. */
  type ⇔[X, Y] = Bijection[X, Y]

}
