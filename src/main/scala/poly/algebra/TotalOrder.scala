package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait TotalOrder[@miniboxed X] extends Lattice[X] with WeakOrder[X] {
  def min(x: X, y: X): X = if (lt(x, y)) x else y
  def max(x: X, y: X): X = if (lt(x, y)) y else x
  override def le(x: X, y: X): Boolean = lt(x, y) | eq(x, y)
  override def sup(x: X, y: X) = max(x, y)
  override def inf(x: X, y: X) = min(x, y)
  override def tied(x: X, y: X) = eq(x, y)
}

object TotalOrder {
  def apply[@miniboxed X](implicit O: TotalOrder[X]) = O
  def create[@miniboxed X](fLt: (X, X) => Boolean) = new TotalOrder[X] {
    def eq(x: X, y: X) = x == y
    override def lt(x: X, y: X) = fLt(x, y)
    override def gt(x: X, y: X) = fLt(y, x)
  }
}
