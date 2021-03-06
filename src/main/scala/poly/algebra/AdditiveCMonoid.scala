package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an additive commutative monoid.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAdditiveAssociativity
 *  - $lawAdditiveIdentity
 *  - $lawAdditiveCommutativity
 * @author Tongfei Chen
 * @since 0.2.6
 */
trait AdditiveCMonoid[@sp(fdil) X] extends AdditiveMonoid[X] with AdditiveCSemigroup[X] { self =>
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

