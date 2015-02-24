package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait TotalOrder[X] extends Lattice[X] {
  def lt(x: X, y: X): Boolean = le(x, y) & ne(x, y)
  def gt(x: X, y: X): Boolean = le(y, x) & ne(x, y)
  def min(x: X, y: X): X
  def max(x: X, y: X): X
  override def sup(x: X, y: X) = max(x, y)
  override def inf(x: X, y: X) = min(x, y)
}

object TotalOrder {
  def apply[X](implicit O: TotalOrder[X]) = O
  def create[X](fLt: (X, X) => Boolean) = new TotalOrder[X] {
    def eq(x: X, y: X) = x == y
    override def lt(x: X, y: X) = fLt(x, y)
    override def gt(x: X, y: X) = fLt(y, x)
    def max(x: X, y: X) = if (fLt(x, y)) y else x
    def min(x: X, y: X) = if (fLt(x, y)) x else y
  }
}
