package poly.algebra

import poly.algebra.specgroup._

/**
 * Represents the existence of an `1` element.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait HasOne[@sp(di) X] {
  /** The `1` element (multiplicative identity) of this type. */
  def one: X

  /** Casts this object to a `HasIdentity` with identity `1`. */
  def asIdentityWithOne: HasIdentity[X] = new HasIdentity[X] {
    def id = one
  }
}

object HasOne {
  /** Retrieves the implicit `HasOne` object of this type. */
  def apply[@sp(di) X](implicit O: HasOne[X]) = O

  /** Creates a `HasOne` object given an identity element `1` of a type. */
  def create[@sp(di) X](oneElem: X) = new HasOne[X] {
    def one = oneElem
  }
}
