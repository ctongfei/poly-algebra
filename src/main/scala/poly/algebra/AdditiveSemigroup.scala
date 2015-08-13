package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * Typeclass for additive semigroups.
 * An additive semigroup is a semigroup with the binary operation `add` (`+`).
 * @tparam X Type of element
 */
trait AdditiveSemigroup[@sp(fdi) X] { self =>

  /** The `+` operation of this semigroup. */
  def add(x: X, y: X): X

  /** Computes the sum ''x'' + ''x'' + ··· + ''x'' with ''x'' repeated for ''n'' times. */
  def sumN(x: X, n: Int): X = asSemigroupWithAdd.combineN(x, n)

  /** Casts this structure as a symbol-agnostic semigroup. */
  def asSemigroupWithAdd: Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X) = self.add(x, y)
  }
}

object AdditiveSemigroup extends ImplicitGetter[AdditiveSemigroup] {

  /** Creates an additive semigroup of the specific type using the `+` operation provided. */
  def create[@sp(fdi) X](f: (X, X) => X) = new AdditiveSemigroup[X] {
    def add(x: X, y: X): X = f(x, y)
  }
}
