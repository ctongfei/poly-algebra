package poly.algebra

import poly.algebra.factory._

/**
 *
 * Represents the existence of an identity element.
 * An instance of this typeclass should satisfy the following axiom:
 *  - $lawIdentity
 * @define lawIdentity '''Identity''': \( \forall a \in X, ia = ai = a\), where \(i\) is the identity element.
 * @author Tongfei Chen
 */
trait HasIdentity[+X] {
  /** The identity element of this type. */
  def id: X
}

object HasIdentity extends ImplicitGetter[HasIdentity]
