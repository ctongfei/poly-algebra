package poly.algebra

import poly.algebra.factory._

/**
 * Typeclass for semigroups.
 * A semigroup is a set equipped with an associative binary operation.
 *
 * An instance of this typeclass should satisfy the following axiom:
 * <ul>
 *   <li> $lawAssociativity </li>
 * </ul>
 * @define lawAssociativity '''Associativity''': \(\forall a, b, c \in S, (ab)c = a(bc)\).
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait Semigroup[S] extends Magma[S] { self =>

  /** The associative binary operation of this semigroup. */
  def op(x: S, y: S): S

  /** Combines an element with itself for ''n'' times using the binary exponentiation algorithm. */
  def combineN(x: S, n: Int): S = {
    require(n > 0)
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

  /** Returns the semigroup on tuples in which each component of the tuple also forms a semigroup. */
  def product[T](that: Semigroup[T]): Semigroup[(S, T)] = new SemigroupT.Product(self, that)

}

object Semigroup extends ImplicitGetter[Semigroup] {

  /** Creates a semigroup of the specific type using the binary operation provided. */
  def create[X](f: (X, X) => X): Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X): X = f(x, y)
  }

  def product[X, Y](implicit sx: Semigroup[X], sy: Semigroup[Y]): Semigroup[(X, Y)] = new SemigroupT.Product(sx, sy)
}

private[poly] object SemigroupT {

  class Product[X, Y](sx: Semigroup[X], sy: Semigroup[Y]) extends Semigroup[(X, Y)] {
    def op(x: (X, Y), y: (X, Y)) = (sx.op(x._1, y._1), sy.op(x._2, y._2))
  }

}