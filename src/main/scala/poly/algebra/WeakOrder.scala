package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait WeakOrder[@miniboxed X] extends PartialOrder[X] {
  def lt(x: X, y: X): Boolean
  def gt(x: X, y: X): Boolean = lt(y, x)
  def le(x: X, y: X): Boolean = lt(x, y) | eq(x, y)
  def tied(x: X, y: X): Boolean = (!lt(x, y)) & (!lt(y, x))
}

object WeakOrder {
  def apply[@miniboxed X](implicit O: WeakOrder[X]) = O

}