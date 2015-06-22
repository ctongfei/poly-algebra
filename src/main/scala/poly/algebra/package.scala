package poly

/**
 * `Poly-algebra` contains typeclass abstractions over common algebraic structures.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object algebra {

  type Eq[X] = poly.util.typeclass.Eq[X]

  type Hashing[X, H] = poly.util.typeclass.Hashing[X, H]

}
