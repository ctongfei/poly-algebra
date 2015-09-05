package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait LowerSemilattice[@sp(Boolean) X] extends PartialOrder[X] { self =>
  /** Returns the infimum (meet, a.k.a. greatest lower bound) of the two arguments. */
  def inf(x: X, y: X): X

  def le(x: X, y: X) = eq(x, inf(x, y))
  override def ge(x: X, y: X) = eq(y, inf(x, y))

  override def reverse: UpperSemilattice[X] = new UpperSemilattice[X] {
    override def reverse = self
    def sup(x: X, y: X) = inf(x, y)
  }

  def asSemigroupWithInf: CSemigroup[X] = new CSemigroup[X] {
    def op(x: X, y: X) = inf(x, y)
  }
}

object LowerSemilattice extends ImplicitGetter[LowerSemilattice] {

  def create[@sp(Boolean) X](fInf: (X, X) => X): LowerSemilattice[X] = new LowerSemilattice[X] {
    def inf(x: X, y: X): X = fInf(x, y)
  }
}


