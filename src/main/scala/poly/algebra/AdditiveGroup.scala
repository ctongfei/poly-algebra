package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an additive group.
 * An additive group is an additive monoid (with operation `+` and identity element `0`)
 * that is also invertible.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAdditiveAssociativity
 *  - $lawAdditiveIdentity
 *  - $lawAdditiveInvertibility
 * @define lawAdditiveInvertibility '''Additive invertibility''': ∀''a''∈X, ∃(-''a'')∈X, ''a'' + (-''a'') == (-''a'') + ''a'' == 0.
 * @since 0.1.0
 */
trait AdditiveGroup[@sp(fdil) G] extends AdditiveMonoid[G] { self =>
  /** Returns the negation (additive inverse) of an element. */
  def neg(x: G): G

  /** Returns the difference of two elements. */
  def sub(x: G, y: G): G = add(x, neg(y))

  /** Casts this object to a symbol-agnostic group with the group operation `+`. */
  def asGroupWithAdd: Group[G] = new Group[G] {
    def inv(x: G) = self.neg(x)
    def op(x: G, y: G) = self.add(x, y)
    def id = self.zero
  }
}

object AdditiveGroup extends ImplicitGetter[AdditiveGroup] {
  /** Creates an additive monoid of the specific type using the provided `+`, `0` and `-` (unary negation). */
  def create[@sp(fdi) X](f: (X, X) => X, zeroElem: X, fNeg: X => X): AdditiveGroup[X] = new AdditiveGroup[X] {
    def neg(x: X): X = fNeg(x)
    def add(x: X, y: X): X = f(x, y)
    def zero: X = zeroElem
  }
}
