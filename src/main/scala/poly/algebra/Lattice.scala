package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */

trait Lattice[@specialized(Int, Double) X] extends UpperSemilattice[X] with LowerSemilattice[X] { self =>
  override def le(x: X, y: X) = eq(x, inf(x, y))
  override def ge(x: X, y: X) = eq(x, sup(x, y))

  override def reverse: Lattice[X] = new Lattice[X] {
    override def reverse = self
    def sup(x: X, y: X) = self.inf(x, y)
    def inf(x: X, y: X) = self.sup(x, y)
  }
}

object Lattice {
  def apply[@specialized(Int, Double) X](implicit L: Lattice[X]) = L
  def create[@specialized(Int, Double) X](fSup: (X, X) => X, fInf: (X, X) => X) = new Lattice[X] {
    def sup(x: X, y: X): X = fSup(x, y)
    def inf(x: X, y: X): X = fInf(x, y)
  }
}
