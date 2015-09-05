package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents an additive commutative monoid.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.6
 */
trait AdditiveCMonoid[@sp(fdi) X] extends AdditiveMonoid[X] with AdditiveCSemigroup[X] { self =>
  override def asMonoidWithAdd: CMonoid[X] = new CMonoid[X] {
    def id = self.zero
    def op(x: X, y: X) = self.add(x, y)
  }
}

object AdditiveCMonoid extends ImplicitGetter[AdditiveCMonoid] {
  def create[@sp(fdi) X](f: (X, X) => X, zeroElem: X): AdditiveMonoid[X] = new AdditiveCMonoid[X] {
    def add(x: X, y: X): X = f(x, y)
    def zero: X = zeroElem
  }
}

