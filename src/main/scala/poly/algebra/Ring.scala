package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a ring. A ring is an Abelian additive group together with a multiplicative monoid.
 *
 * An instance of this typeclass should satisfy the following axioms:
 * <ul>
 *  <li> $lawAdditiveAssociativity </li>
 *  <li> $lawAdditiveIdentity </li>
 *  <li> $lawAdditiveInvertibility </li>
 *  <li> $lawAdditiveCommutativity </li>
 *  <li> $lawMultiplicativeAssociativity </li>
 *  <li> $lawMultiplicativeIdentity </li>
 *  <li> $lawDistributivityMA </li>
 * </ul>
 * @author Tongfei Chen
 * @since 0.1.0
 */
@scala.annotation.implicitNotFound("Cannot find a ring on type ${X}.")
trait Ring[@sp(fdi) X] extends Semiring[X] with AdditiveCGroup[X] {

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
  def create[@sp(di) X](fMul: (X, X) => X, oneElem: X)(implicit X: AdditiveGroup[X]): Ring[X] = new Ring[X] {
    def add(x: X, y: X): X = X.add(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = X.zero
    def one: X = oneElem
    def neg(x: X): X = X.neg(x)
    override def sub(x: X, y: X): X = X.sub(x, y)
  }
}




