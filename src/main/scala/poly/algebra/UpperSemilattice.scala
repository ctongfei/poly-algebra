package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait UpperSemilattice[@sp(fdib) X] extends PartialOrder[X] { self =>
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

object UpperSemilattice extends ImplicitGetter[UpperSemilattice] {

  def create[@sp(Int, Boolean) X](fSup: (X, X) => X): UpperSemilattice[X] = new UpperSemilattice[X] {
    def sup(x: X, y: X): X = fSup(x, y)
  }
}


