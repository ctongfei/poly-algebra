package poly.algebra

/**
 * Typeclass for additive groups.
 * Additives groups are groups with the operation `+`.
 * @tparam X Type
 */
trait AdditiveGroup[@specialized(Int, Double) X] extends AdditiveMonoid[X] { self =>
  /** Returns the negation (additive inverse) of an element. */
  def neg(x: X): X

  /** Returns the difference of two elements. */
  def sub(x: X, y: X): X = add(x, neg(y))

  /** Casts this object to a symbol-agnostic group with the group operation `+`. */
  def asGroupWithAdd: Group[X] = new Group[X] {
    def inv(x: X) = self.neg(x)
    def op(x: X, y: X) = self.add(x, y)
    def id = self.zero
  }
}

object AdditiveGroup {

  /** Retrieves the implicit additive group associated with the specific type. */
  def apply[@specialized(Int, Double) X](implicit G: AdditiveGroup[X]) = G

  /** Creates an additive monoid of the specific type using the provided `+`, `0` and `-` (unary negation). */
  def create[@specialized(Int, Double) X](f: (X, X) => X, zeroElem: X, fNeg: X => X) = new AdditiveGroup[X] {
    def neg(x: X): X = fNeg(x)
    def add(x: X, y: X): X = f(x, y)
    def zero: X = zeroElem
  }
}

