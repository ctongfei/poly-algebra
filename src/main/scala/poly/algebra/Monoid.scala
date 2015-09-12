package poly.algebra

import poly.util.typeclass._

/**
 * Typeclass for monoids.
 * A monoid is a semigroup with an identity element.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAssociativity
 *  - $lawIdentity
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Monoid[X] extends Semigroup[X] with HasIdentity[X] {

  override def combineN(x: X, n: Int): X = {
    if (n == 0) return id
    var y = x
    var m = n
    while (m % 2 == 0) {
      m >>= 1
      y = op(y, y)
    }
    var r = y
    while (m > 1) {
      m >>= 1
      y = op(y, y)
      if (m % 2 == 1)
        r = op(r, y)
    }
    r
  }

}

object Monoid extends ImplicitGetter[Monoid] {

  /** Creates an monoid of the specific type using the binary operation and the identity element provided. */
  def create[X](f: (X, X) => X, idElem: X): Monoid[X] = new Monoid[X] {
    def op(x: X, y: X): X = f(x, y)
    val id: X = idElem
  }

  /** Returns the product monoid of two monoids. */
  def product[X, Y](implicit Mx: Monoid[X], My: Monoid[Y]): Monoid[(X, Y)] = new Monoid[(X, Y)] {
    def op(x: (X, Y), y: (X, Y)) = (Mx.op(x._1, y._1), My.op(x._2, y._2))
    val id: (X, Y) = (Mx.id, My.id)
  }

}



