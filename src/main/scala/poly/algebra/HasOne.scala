package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents the existence of an `1` element.

 * An instance of this typeclass should satisfy the following axiom:
 *  - $lawMultiplicativeIdentity
 * @define lawMultiplicativeIdentity '''Multiplicative identity''': ∀''a''∈X, ''a'' * 1 == 1 * ''a'' == ''a''.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait HasOne[@sp(fdi) +X] {
  /** The `1` element (multiplicative identity) of this type. */
  def one: X

  /** Casts this object to a `HasIdentity` with identity `1`. */
  def asIdentityWithOne: HasIdentity[X] = new HasIdentity[X] {
    def id = one
  }
}

object HasOne extends ImplicitGetter[HasOne] {

  /** Creates a `HasOne` object given an identity element `1` of a type. */
  def create[@sp(fdi) X](oneElem: X) = new HasOne[X] {
    def one = oneElem
  }
}
