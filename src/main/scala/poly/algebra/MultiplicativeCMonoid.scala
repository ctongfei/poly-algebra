package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a multiplicative commutative monoid.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawMultiplicativeAssociativity
 *  - $lawMultiplicativeIdentity
 *  - $lawMultiplicativeCommutativity
 * @author Tongfei Chen
 */
trait MultiplicativeCMonoid[@sp(fdi) X] extends MultiplicativeMonoid[X] with MultiplicativeCSemigroup[X] { self =>
  override def asMonoidWithMul: CMonoid[X] = new CMonoid[X] {
    def id = self.one
    def op(x: X, y: X) = self.mul(x, y)
  }
}

object MultiplicativeCMonoid extends ImplicitGetter[MultiplicativeCMonoid] {
  def create[@sp(fdi) X](f: (X, X) => X, oneElem: X): MultiplicativeCMonoid[X] = new MultiplicativeCMonoid[X] {
    def mul(x: X, y: X): X = f(x, y)
    def one: X = oneElem
  }
}