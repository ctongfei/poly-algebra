package poly.algebra

import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait UpperSemilattice[@sp(dib) X] extends PartialOrder[X] { self =>
  def sup(x: X, y: X): X
  def le(x: X, y: X) = eq(y, sup(x, y))
  override def ge(x: X, y: X) = eq(x, sup(x, y))

  override def reverse: LowerSemilattice[X] = new LowerSemilattice[X] {
    override def reverse = self
    def inf(x: X, y: X) = sup(x, y)
  }

  def asSemigroupWithSup: Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X) = sup(x, y)
  }
}

object UpperSemilattice {
  def apply[@specialized(dib) X](implicit L: UpperSemilattice[X]) = L
  def create[@sp(dib) X](fSup: (X, X) => X) = new UpperSemilattice[X] {
    def sup(x: X, y: X): X = fSup(x, y)
  }
}


trait BoundedUpperSemilattice[@sp(dib) X] extends UpperSemilattice[X] with HasOne[X] { self =>
  def asMonoidWithSup: Monoid[X] = new Monoid[X] {
    def id: X = self.one
    def op(x: X, y: X): X = self.sup(x, y)
  }
}
