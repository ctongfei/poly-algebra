package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait WeakOrder[@specialized(Int, Double) X] extends PartialOrder[X] {

  def cmp(x: X, y: X): Int

  def lt(x: X, y: X): Boolean = cmp(x, y) < 0
  def gt(x: X, y: X): Boolean = cmp(x, y) > 0
  def le(x: X, y: X): Boolean = cmp(x, y) <= 0
  override def ge(x: X, y: X): Boolean = cmp(x, y) >= 0
  def tied(x: X, y: X): Boolean = cmp(x, y) == 0

}

object WeakOrder {
  def apply[@specialized(Int, Double) X](implicit O: WeakOrder[X]) = O

}
