package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Field[@sp(fd) X] extends CRing[X] with MultiplicativeCGroup[X]

object Field extends ImplicitGetter[Field] {
  def create[@sp(fd) X](R: Ring[X], fInv: X => X) = new Field[X] {
    def one = R.one
    def neg(x: X) = R.neg(x)
    def add(x: X, y: X) = R.add(x, y)
    def zero = R.zero
    def inv(x: X) = fInv(x)
    def mul(x: X, y: X) = R.mul(x, y)
  }

  def create[@sp(fd) X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X, fNeg: X => X, fInv: X => X) = new Field[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def neg(x: X): X = fNeg(x)
    def inv(x: X): X = fInv(x)
    def zero: X = zeroElem
    def one: X = oneElem
  }
}
