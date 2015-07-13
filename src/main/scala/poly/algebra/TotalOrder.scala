package poly.algebra

import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.1.0
 */
//TODO: should be covariant. But it seems impossible under Scala's type system
trait TotalOrder[@sp(fdib) X] extends Lattice[X] with WeakOrder[X] { self =>
  override def min[Y <: X](x: Y, y: Y): Y = if (lt(x, y)) x else y
  override def max[Y <: X](x: Y, y: Y): Y = if (lt(x, y)) y else x

  def sup(x: X, y: X): X = max(x, y)
  def inf(x: X, y: X): X = min(x, y)

  override def eq(x: X, y: X) = x == y
  override def ne(x: X, y: X) = x != y
  override def le(x: X, y: X) = cmp(x, y) <= 0
  override def ge(x: X, y: X) = cmp(x, y) >= 0

  override def reverse: TotalOrder[X] = new TotalOrder[X] {
    override def reverse: TotalOrder[X] = self
    def cmp(x: X, y: X): Int = -self.cmp(x, y)
  }
}

object TotalOrder {

  def apply[@sp(fdib) X](implicit O: TotalOrder[X]) = O

  def create[@sp(fdib) X](fLt: (X, X) => Boolean): TotalOrder[X] = new TotalOrder[X] {
    def cmp(x: X, y: X) = if (fLt(x, y)) -1 else if (x == y) 0 else 1
    override def lt(x: X, y: X) = fLt(x, y)
    override def gt(x: X, y: X) = fLt(y, x)
  }

  def by[Y, @sp(fdib) X](f: Y => X)(implicit O: TotalOrder[X]): TotalOrder[Y] = new TotalOrder[Y] {
    def cmp(x: Y, y: Y) = O.cmp(f(x), f(y))
  }

}
