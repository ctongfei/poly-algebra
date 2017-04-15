package poly.algebra

import poly.algebra.factory._

/**
 *
 * Represents a monoid, i.e., a semigroup with an identity element.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAssociativity
 *  - $lawIdentity
 * @author Tongfei Chen
 */
trait Monoid[M] extends Semigroup[M] with HasIdentity[M] { self =>

  /**
   * Computes x <> x <> ... <> x (''n'' times) using the binary exponentiation algorithm.
   */
  override def combineN(x: M, n: Int): M = {
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

  /** Returns the product monoid of two monoids. */
  def product[N](that: Monoid[N]): Monoid[(M, N)] = new MonoidT.Product(self, that)

}

object Monoid extends ImplicitGetter[Monoid] {

  /** Creates an monoid of the specific type using the binary operation and the identity element provided. */
  def create[X](f: (X, X) => X, idElem: X): Monoid[X] = new Monoid[X] {
    def op(x: X, y: X): X = f(x, y)
    val id: X = idElem
  }

  def product[X, Y](implicit mx: Monoid[X], my: Monoid[Y]): Monoid[(X, Y)] = new MonoidT.Product(mx, my)

}

private[poly] object MonoidT {

  class Product[X, Y](mx: Monoid[X], my: Monoid[Y]) extends SemigroupT.Product(mx, my) with Monoid[(X, Y)] {
    def id = (mx.id, my.id)
  }

}