package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MultiplicativeGroup[@specialized(Double) X] extends MultiplicativeMonoid[X] { self =>

  def inv(x: X): X
  def div(x: X, y: X): X = mul(x, inv(y))
  def asGroupWithMul: Group[X] = new Group[X] {
    def inv(x: X) = self.inv(x)
    def op(x: X, y: X) = self.mul(x, y)
    def id = self.one
  }
}

object MultiplicativeGroup {
  def apply[@specialized(Double) X](implicit G: MultiplicativeGroup[X]) = G
  def create[@specialized(Double) X](f: (X, X) => X, oneElem: X, fInv: X => X) = new MultiplicativeGroup[X] {
    def inv(x: X): X = fInv(x)
    def mul(x: X, y: X): X = f(x, y)
    def one: X = oneElem
  }
}
