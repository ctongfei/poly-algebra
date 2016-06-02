package poly.algebra

import poly.algebra.factory._

/**
 * Represents a group. A group is a monoid where an inverse element exists for every element.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAssociativity
 *  - $lawIdentity
 *  - $lawInvertibility
 * @define lawInvertibility '''Invertibility''': ∀''a''∈X, ∃''b''∈X, ''a'' op ''b'' == ''b'' op ''a'' == id.
 * @since 0.1.0
 * @author Tongfei Chen
 */
trait Group[X] extends Monoid[X] {

  /** Returns the unique inverse element of the given element in this group. */
  def inv(x: X): X

  def invOp(x: X, y: X) = op(x, inv(y))
}

object Group extends ImplicitGetter[Group] {
  def create[X](f: (X, X) => X, idElem: X, fInv: X => X): Group[X] = new Group[X] {
    def inv(x: X): X = fInv(x)
    def op(x: X, y: X): X = f(x, y)
    def id: X = idElem
  }
}



