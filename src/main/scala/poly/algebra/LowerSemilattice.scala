package poly.algebra

import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait LowerSemilattice[@sp(dib) X] extends PartialOrder[X] { self =>


  def inf(x: X, y: X): X

  /** @inheritdoc */
  def le(x: X, y: X) = eq(x, inf(x, y))
  override def ge(x: X, y: X) = eq(y, inf(x, y))

  override def reverse: UpperSemilattice[X] = new UpperSemilattice[X] {
    override def reverse = self
    def sup(x: X, y: X) = inf(x, y)
  }

  def asSemigroupWithInf: Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X) = inf(x, y)
  }
}

object LowerSemilattice {
  def apply[@sp(dib) X](implicit L: LowerSemilattice[X]) = L
  def create[@sp(dib) X](fInf: (X, X) => X) = new LowerSemilattice[X] {
    def inf(x: X, y: X): X = fInf(x, y)
  }
}


trait BoundedLowerSemilattice[@sp(dib) X] extends LowerSemilattice[X] with HasZero[X] { self =>
  def asMonoidWithInf: Monoid[X] = new Monoid[X] {
  def id: X = self.zero
    def op(x: X, y: X): X = self.inf(x, y)
  }
}
