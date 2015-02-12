package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MultiplicativeGroup[@specialized(Int, Float, Double) X] extends MultiplicativeMonoid[X] { self =>

  def inv(x: X): X
  def div(x: X, y: X): X = mul(x, inv(y))
  def groupWithMul: Group[X] = new Group[X] {
    def inv(x: X) = self.inv(x)
    def op(x: X, y: X) = self.mul(x, y)
    def id = self.one
  }
}

object MultiplicativeGroup {
  def apply[X](implicit G: MultiplicativeGroup[X]) = G
  def create[X](f: (X, X) => X, oneElem: X, fInv: X => X) = new MultiplicativeGroup[X] {
    def inv(x: X): X = fInv(x)
    def mul(x: X, y: X): X = f(x, y)
    def one: X = oneElem
  }
}
