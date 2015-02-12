package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait TotalOrder[X] extends Lattice[X] {
  def lt(x: X, y: X): Boolean = le(x, y) & x != y
  def gt(x: X, y: X): Boolean = le(y, x) & x != y
}

object TotalOrder {
  def apply[X](implicit O: TotalOrder[X]) = O
  def create[X](fLt: (X, X) => Boolean) = new TotalOrder[X] {
    override def lt(x: X, y: X) = fLt(x, y)
    override def gt(x: X, y: X) = fLt(y, x)
    def sup(x: X, y: X) = if (fLt(x, y)) y else x
    def inf(x: X, y: X) = if (fLt(x, y)) x else y
  }
}
