package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait AdditiveGroup[@specialized(Int, Float, Double) X] extends AdditiveMonoid[X] { self =>
  def neg(x: X): X
  def sub(x: X, y: X): X = add(x, neg(y))
  def groupWithAdd: Group[X] = new Group[X] {
    def inv(x: X) = self.neg(x)
    def op(x: X, y: X) = self.add(x, y)
    def id = self.zero
  }
}

object AdditiveGroup {
  def apply[X](implicit G: AdditiveGroup[X]) = G
  def create[X](f: (X, X) => X, zeroElem: X, fNeg: X => X) = new AdditiveGroup[X] {
    def neg(x: X): X = fNeg(x)
    def add(x: X, y: X): X = f(x, y)
    def zero: X = zeroElem
  }
}
