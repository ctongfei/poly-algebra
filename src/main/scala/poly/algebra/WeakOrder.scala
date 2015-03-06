package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait WeakOrder[@specialized(Int, Double) X] extends PartialOrder[X] {
  //TODO: add a cmp method that returns an int
  def lt(x: X, y: X): Boolean
  def gt(x: X, y: X): Boolean = lt(y, x)
  def le(x: X, y: X): Boolean = lt(x, y) | eq(x, y)
  def tied(x: X, y: X): Boolean = (!lt(x, y)) & (!lt(y, x))
}

object WeakOrder {
  def apply[@specialized(Int, Double) X](implicit O: WeakOrder[X]) = O

}