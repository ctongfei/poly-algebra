package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * Represents the existence of an `0` element.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait HasZero[@sp(fdi) +X] {

  /** The `0` element (additive identity) of this type. */
  def zero: X

  /** Casts this object to a `HasIdentity` with identity `0`. */
  def asIdentityWithZero: HasIdentity[X] = new HasIdentity[X] {
    def id = zero
  }
}

object HasZero extends ImplicitGetter[HasZero] {

  /** Creates a `HasZero` object given an identity element `0` of a type. */
  def create[@sp(fdi) X](zeroElem: X) = new HasZero[X] {
    def zero = zeroElem
  }
}
