package poly.algebra

import poly.util.typeclass._

/**
 * Represents the existence of an identity element.
 *
 * An instance of this typeclass should satisfy the following axiom:
 *  - $lawIdentity
 * @define lawIdentity '''Identity''': ∀''a''∈X, ''a'' op id == id op ''a'' == ''a''.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait HasIdentity[+X] {
  /** The identity element of this type. */
  def id: X
}

object HasIdentity extends ImplicitGetter[HasIdentity] {

  /** Creates a `HasIdentity` object given an identity element of a type. */
  def create[X](idElem: X) = new HasIdentity[X] {
    def id = idElem
  }
}

