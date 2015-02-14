package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Ring[@specialized(Double, Float, Int) X] extends AdditiveGroup[X] with MultiplicativeMonoid[X]

object Ring {
  def apply[X](implicit R: Ring[X]) = R
  def create[X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X, fNeg: X => X) = new Ring[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = zeroElem
    def one: X = oneElem
    def neg(x: X): X = fNeg(x)
  }
}
