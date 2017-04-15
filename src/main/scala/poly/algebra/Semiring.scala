package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a semiring. A semiring is a commutative additive monoid as well as a multiplicative monoid.
 *
 * An instance of this typeclass should satisfy the following axioms:
 * <ul>
 *  <li> $lawAdditiveAssociativity </li>
 *  <li> $lawAdditiveIdentity </li>
 *  <li> $lawAdditiveCommutativity </li>
 *  <li> $lawMultiplicativeAssociativity </li>
 *  <li> $lawMultiplicativeIdentity </li>
 *  <li> $lawDistributivityMA </li>
 *  <li> $lawAnnihilationM </li>
 * </ul>
 * @define lawDistributivityMA '''Distributivity of multiplication w.r.t. addition''': ∀''a'', ''b'', ''c''∈X, (''a'' + ''b'') * ''c'' == ''a'' * ''c'' + ''b'' * ''c'' and ''a'' * (''b'' + ''c'') == ''a'' * ''b'' + ''a'' * ''c''.
 * @define lawAnnihilationM '''Zero as an annihilator''': ∀''a''∈X, 0 * ''a'' == ''a'' * 0 == 0.
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait Semiring[@sp(fdi) X] extends AdditiveCMonoid[X] with MultiplicativeMonoid[X] {

  /** Returns the 2 element in this semiring. */
  def two = add(one, one)

  def product[Y](that: Semiring[Y]): Semiring[(X, Y)] = new SemiringT.Product(this, that)
}

object Semiring extends ImplicitGetter[Semiring] {

  /** Creates a semiring using the provided operations `add`, `mul`, `zero`, and `one`. */
  def create[@sp(di) X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X)
  : Semiring[X] = new Semiring[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = zeroElem
    def one: X = oneElem
  }

  def product[X, Y](implicit X: Semiring[X], Y: Semiring[Y]): Semiring[(X, Y)] = new SemiringT.Product(X, Y)
}

private[poly] object SemiringT {

  class Product[X, Y](X: Semiring[X], Y: Semiring[Y]) extends Semiring[(X, Y)] {
    def add(x: (X, Y), y: (X, Y)) = (X.add(x._1, y._1), Y.add(x._2, y._2))
    def one = (X.one, Y.one)
    def mul(x: (X, Y), y: (X, Y)) = (X.mul(x._1, y._1), Y.mul(x._2, y._2))
    def zero = (X.zero, Y.zero)
  }

}