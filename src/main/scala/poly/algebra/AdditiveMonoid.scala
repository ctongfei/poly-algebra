package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents an additive monoid.
 * An additive monoid is an additive semigroup (with operation `+`) with an additive identity element `0`.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.1.0
 */
trait AdditiveMonoid[@sp(fdi) X] extends AdditiveSemigroup[X] with HasZero[X] { self =>

  override def sumN(x: X, n: Int): X = asMonoidWithAdd.combineN(x, n)

  /** Casts this object as a symbol-agnostic monoid with the operation `+`. */
  def asMonoidWithAdd: Monoid[X] = new Monoid[X] {
    def op(x: X, y: X) = self.add(x, y)
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




