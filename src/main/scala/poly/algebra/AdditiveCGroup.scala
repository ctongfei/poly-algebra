package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents an additive commutative group.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.6
 */
trait AdditiveCGroup[@sp(fdi) X] extends AdditiveGroup[X] with AdditiveCMonoid[X] { self =>
  override def asGroupWithAdd: CGroup[X] = new CGroup[X] {
    def inv(x: X) = self.neg(x)
    def id = self.zero
    def op(x: X, y: X) = self.add(x, y)
  }
}

object AdditiveCGroup extends ImplicitGetter[AdditiveCGroup] {
  def create[@sp(fdi) X](f: (X, X) => X, zeroElem: X, fNeg: X => X): AdditiveCGroup[X] = new AdditiveCGroup[X] {
    def neg(x: X): X = fNeg(x)
    def add(x: X, y: X): X = f(x, y)
    def zero: X = zeroElem
  }
}

