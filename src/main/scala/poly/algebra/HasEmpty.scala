package poly.algebra

import poly.algebra.factory._

/**
 * Represents the existence of an empty element.
 * @author Tongfei Chen
 */
trait HasEmpty[+X] {

  /** The empty element (concatenative identity) of this type. */
  def empty: X

  /** Casts this object to a `HasIdentity` with identity `0`. */
  def asIdentityWithEmpty: HasIdentity[X] = new HasIdentity[X] {
    def id = empty
  }
}

object HasEmpty extends ImplicitGetter[HasEmpty]
