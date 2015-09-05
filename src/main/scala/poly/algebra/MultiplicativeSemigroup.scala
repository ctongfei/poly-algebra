package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Typeclass for multiplicative semigroups.
 * An multiplicative semigroup is a semigroup with the binary operation `mul` (`*`).
 * @tparam X Type of element
 */
trait MultiplicativeSemigroup[@sp(fdi) X] {

  /** Returns the product of two elements. */
  def mul(x: X, y: X): X

  /** Computes the product ''x'' * ''x'' * ··· * ''x'' with ''x'' repeated for ''n'' times. */
  def ipow(x: X, n: Int): X = asSemigroupWithMul.combineN(x, n)

  /** Casts this structure as a symbol-agnostic semigroup. */
  def asSemigroupWithMul: Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X) = mul(x, y)
  }
}

object MultiplicativeSemigroup extends ImplicitGetter[MultiplicativeSemigroup] {

  /** Creates an multiplicative semigroup of the specific type using the `*` operation provided. */
  def create[@sp(fdi) X](f: (X, X) => X): MultiplicativeSemigroup[X] = new MultiplicativeSemigroup[X] {
    def mul(x: X, y: X): X = f(x, y)
  }
}




