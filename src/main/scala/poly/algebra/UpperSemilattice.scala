package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */

trait UpperSemilattice[@specialized(Int, Boolean, Double) X] extends PartialOrder[X] {
  def sup(x: X, y: X): X
  def le(x: X, y: X) = eq(y, sup(x, y))
  override def ge(x: X, y: X) = eq(x, sup(x, y))
}

object UpperSemilattice {
  def apply[@specialized(Int, Boolean, Double) X](implicit L: UpperSemilattice[X]) = L
  def create[@specialized(Int, Double) X](fSup: (X, X) => X) = new UpperSemilattice[X] {
    def eq(x: X, y: X) = x == y
    def sup(x: X, y: X): X = fSup(x, y)
  }
}
