package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait LowerSemilattice[@specialized(Int, Boolean, Double) X] extends PartialOrder[X] { self =>
  def inf(x: X, y: X): X
  def le(x: X, y: X) = eq(x, inf(x, y))
  override def ge(x: X, y: X) = eq(y, inf(x, y))

  override def reverse: UpperSemilattice[X] = new UpperSemilattice[X] {
    override def reverse = self
    def sup(x: X, y: X) = inf(x, y)
  }
}

object LowerSemilattice {
  def apply[@specialized(Int, Boolean, Double) X](implicit L: LowerSemilattice[X]) = L
  def create[@specialized(Int, Double) X](fInf: (X, X) => X) = new LowerSemilattice[X] {
    def inf(x: X, y: X): X = fInf(x, y)
  }
}
