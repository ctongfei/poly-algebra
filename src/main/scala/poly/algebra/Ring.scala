package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * Typeclass for rings.
 *
 * A ring is an algebraic structure that generalizes the arithmetic operations of addition
 * and multiplication.
 *
 * Formally, a ring is a set ''R'' equipped with `+` and `*` where (''R'', +) form an
 * Abelian additive group (associative, `0` as the additive identity, commutative, invertible),
 * (''R'', *) form a multiplicative monoid (associative, `1` as the multiplicative identity),
 * and `*` distributes over `+`.
 *
 * In order to create a ring, `add`, `mul`, `zero`, `one`, `neg` should be implemented. `sub`
 * should be overridden for performance.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Ring[@sp(fdi) X] extends Semiring[X] with AdditiveGroup[X] {

  /** Returns the -1 element in this ring. */
  def negOne = neg(one)

}

object Ring extends ImplicitGetter[Ring] {

  /** Creates a ring using the provided operations `add`, `mul`, `zero`, `one` and `neg`. */
  def create[@sp(di) X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X, fNeg: X => X)
  : Ring[X] = new Ring[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = zeroElem
    def one: X = oneElem
    def neg(x: X): X = fNeg(x)
  }

  /** Creates a ring using the provided operations `mul`, `one` based on an existing additive group. */
  def create[@sp(di) X](fMul: (X, X) => X, oneElem: X)(implicit G: AdditiveGroup[X]): Ring[X] = new Ring[X] {
    def add(x: X, y: X): X = G.add(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = G.zero
    def one: X = oneElem
    def neg(x: X): X = G.neg(x)
    override def sub(x: X, y: X): X = G.sub(x, y)
  }
}
