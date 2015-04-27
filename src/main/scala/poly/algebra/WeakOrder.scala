package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait WeakOrder[@specialized(Int, Double) X] extends PartialOrder[X] {

  def cmp(x: X, y: X): Int

  def le(x: X, y: X): Boolean = cmp(x, y) <= 0
  override def ge(x: X, y: X): Boolean = cmp(x, y) >= 0
  override def lt(x: X, y: X): Boolean = cmp(x, y) < 0
  override def gt(x: X, y: X): Boolean = cmp(x, y) > 0
  override def eq(x: X, y: X): Boolean = cmp(x, y) == 0

}

object WeakOrder {
  def apply[@specialized(Int, Double) X](implicit O: WeakOrder[X]) = O

  def create[@specialized(Int, Double) X](fCmp: (X, X) => Int): WeakOrder[X] = new WeakOrder[X] {
    def cmp(x: X, y: X) = fCmp(x, y)
  }

  def on[@specialized(Int, Double) X, Y](f: Y => X)(implicit O: WeakOrder[X]) = new WeakOrder[Y] {
    def cmp(x: Y, y: Y) = O.cmp(f(x), f(y))
  }
}
