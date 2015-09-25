package poly.algebra

import poly.algebra.factory._


/**
 * Represents a group. A group is a monoid where for each element there exists an inverse element.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAssociativity
 *  - $lawIdentity
 *  - $lawInvertibility
 * @define lawInvertibility '''Invertibility''': ∀''a''∈X, ∃''b''∈X, ''a'' op ''b'' == ''b'' op ''a'' == id.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Group[X] extends Monoid[X] {
  def inv(x: X): X
}

object Group extends ImplicitGetter[Group] {
  def create[X](f: (X, X) => X, idElem: X, fInv: X => X) = new Group[X] {
    def inv(x: X): X = fInv(x)
    def op(x: X, y: X): X = f(x, y)
    def id: X = idElem
  }
}



