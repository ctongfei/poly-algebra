package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an additive monoid.
 * An additive monoid is an additive semigroup (with operation `+`) with an additive identity element `0`.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAdditiveAssociativity
 *  - $lawAdditiveIdentity
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait AdditiveMonoid[@sp(fdil) M] extends AdditiveSemigroup[M] with HasZero[M] { self =>

  override def sumN(x: M, n: Int): M = asMonoidWithAdd.combineN(x, n)

  /** Casts this object as a symbol-agnostic monoid with the operation `+`. */
  def asMonoidWithAdd: Monoid[M] = new Monoid[M] {
    def op(x: M, y: M) = self.add(x, y)
    def id = self.zero
  }
}

object AdditiveMonoid extends ImplicitGetter[AdditiveMonoid] {
  /** Creates an additive monoid of the specific type using the provided `+` and `0`. */
  def create[@sp(fdi) X](f: (X, X) => X, zeroElem: X): AdditiveMonoid[X] = new AdditiveMonoid[X] {
    def add(x: X, y: X): X = f(x, y)
    def zero: X = zeroElem
  }

}




