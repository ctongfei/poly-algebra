package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Field[@specialized(Double) X] extends EuclideanDomain[X] with MultiplicativeGroup[X] {
  def quot(x: X, y: X) = div(x, y)
  def mod(x: X, y: X) = zero
}

object Field {
  def apply[@specialized(Double) X](implicit F: Field[X]) = F
  def create[@specialized(Double) X](R: Ring[X], fInv: X => X) = new Field[X] {
    def one = R.one
    def neg(x: X) = R.neg(x)
    def add(x: X, y: X) = R.add(x, y)
    def zero = R.zero
    def inv(x: X) = fInv(x)
    def mul(x: X, y: X) = R.mul(x, y)
  }

  def create[@specialized(Double) X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X, fNeg: X => X, fInv: X => X) = new Field[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def neg(x: X): X = fNeg(x)
    def inv(x: X): X = fInv(x)
    def zero: X = zeroElem
    def one: X = oneElem
  }
}
