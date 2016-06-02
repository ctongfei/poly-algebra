package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents the existence of an `0` element.
 *
 * An instance of this typeclass should satisfy the following axiom:
 *  - $lawAdditiveIdentity
 * @define lawAdditiveIdentity '''Additive identity''': ∀''a''∈X, ''a'' + 0 == 0 + ''a'' == ''a''.
 * @author Tongfei Chen
 */
trait HasZero[@sp(fdil) +X] {

  /** The `0` element (additive identity) of this type. */
  def zero: X

  /** Casts this object to a `HasIdentity` with identity `0`. */
  def asIdentityWithZero: HasIdentity[X] = new HasIdentity[X] {
    def id = zero
  }
}

object HasZero extends ImplicitGetter[HasZero]