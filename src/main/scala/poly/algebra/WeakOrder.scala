package poly.algebra

import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait WeakOrder[@sp(di) X] extends PartialOrder[X] { self =>

  def cmp(x: X, y: X): Int

  def le(x: X, y: X): Boolean = cmp(x, y) <= 0
  override def ge(x: X, y: X): Boolean = cmp(x, y) >= 0
  override def lt(x: X, y: X): Boolean = cmp(x, y) < 0
  override def gt(x: X, y: X): Boolean = cmp(x, y) > 0
  override def eq(x: X, y: X): Boolean = cmp(x, y) == 0
  override def ne(x: X, y: X): Boolean = cmp(x, y) != 0

  def max(x: X, y: X): X = if (cmp(x, y) >= 0) x else y
  def min(x: X, y: X): X = if (cmp(x, y) <= 0) x else y

  /** Returns the equivalence relation (tied relation) induced by this weak order. */
  def asEq: Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = self.cmp(x, y) == 0
  }

  override def reverse: WeakOrder[X] = new WeakOrder[X] {
    override def reverse: WeakOrder[X] = self
    def cmp(x: X, y: X): Int = -self.cmp(x, y)
  }

}

object WeakOrder {
  def apply[@sp(di) X](implicit O: WeakOrder[X]) = O

  def create[@sp(di) X](fCmp: (X, X) => Int): WeakOrder[X] = new WeakOrder[X] {
    def cmp(x: X, y: X) = fCmp(x, y)
  }

  def by[@sp(di) X, Y](f: Y => X)(implicit O: WeakOrder[X]) = new WeakOrder[Y] {
    def cmp(x: Y, y: Y) = O.cmp(f(x), f(y))
  }
}
