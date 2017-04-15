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
trait Group[G] extends Monoid[G] { self =>

  /** Returns the unique inverse element of the given element in this group. */
  def inv(x: G): G

  def invOp(x: G, y: G) = op(x, inv(y))

  /** Returns the direct product of two groups. */
  def product[H](that: Group[H]): Group[(G, H)] = new GroupT.Product(self, that)
}

object Group extends ImplicitGetter[Group] {
  def create[X](f: (X, X) => X, idElem: X, fInv: X => X): Group[X] = new Group[X] {
    def inv(x: X): X = fInv(x)
    def op(x: X, y: X): X = f(x, y)
    def id: X = idElem
  }

  /** Returns the direct product of two groups. */
  def product[X, Y](implicit X: Group[X], Y: Group[Y]): Group[(X, Y)] = new GroupT.Product(X, Y)
}

private[poly] object GroupT {

  class Product[X, Y](gx: Group[X], gy: Group[Y]) extends MonoidT.Product[X, Y](gx, gy) with Group[(X, Y)] {
    def inv(x: (X, Y)) = (gx.inv(x._1), gy.inv(x._2))
  }

}