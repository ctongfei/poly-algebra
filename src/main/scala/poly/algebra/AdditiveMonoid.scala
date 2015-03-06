package poly.algebra

/**
 * Typeclass for additive monoids.
 * An additive monoid is an additive semigroup (with operation `+`) with an additive identity element `0`.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait AdditiveMonoid[@specialized(Int, Double) X] extends AdditiveSemigroup[X] with HasZero[X] { self =>

  override def sumN(x: X, n: Int): X = asMonoidWithAdd.combineN(x, n)

  /** Casts this additive monoid as a symbol-agnostic monoid. */
  def asMonoidWithAdd: Monoid[X] = new Monoid[X] {
    def op(x: X, y: X) = self.add(x, y)
    def id = self.zero
  }
}

object AdditiveMonoid {
  /** Retrieves the implicit additive monoid associated with the specific type. */
  def apply[@specialized(Int, Double) X](implicit M: AdditiveMonoid[X]) = M

  /** Creates an additive monoid of the specific type using the provided `+` and `0`. */
  def create[@specialized(Int, Double) X](f: (X, X) => X, zeroElem: X) = new AdditiveMonoid[X] {
    def add(x: X, y: X): X = f(x, y)
    def zero: X = zeroElem
  }
}

