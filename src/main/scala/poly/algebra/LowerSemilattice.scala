package poly.algebra

import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait LowerSemilattice[@sp(Int, Boolean) X] extends PartialOrder[X] { self =>

  def inf(x: X, y: X): X

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
  def apply[@sp(Int, Boolean) X](implicit L: LowerSemilattice[X]) = L

  def create[@sp(Int, Boolean) X](fInf: (X, X) => X): LowerSemilattice[X] = new LowerSemilattice[X] {
    def inf(x: X, y: X): X = fInf(x, y)
  }
}

trait BoundedLowerSemilattice[@sp(Int, Boolean) X] extends LowerSemilattice[X] with HasZero[X] { self =>
  def asMonoidWithInf: Monoid[X] = new Monoid[X] {
    def id: X = self.zero
    def op(x: X, y: X): X = self.inf(x, y)
  }
}
