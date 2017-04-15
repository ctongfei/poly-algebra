package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an additive commutative semigroup.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAdditiveAssociativity
 *  - $lawAdditiveCommutativity
 *
 * @define lawAdditiveCommutativity '''Additive commutativity''':  ∀''a'', ''b''∈X, ''a'' + ''b'' == ''b'' + ''a''.
 * @author Tongfei Chen
 * @since 0.2.6
 */
trait AdditiveCSemigroup[@sp(fdil) X] extends AdditiveSemigroup[X] { self =>
  override def asSemigroupWithAdd: CSemigroup[X] = new CSemigroup[X] {
    def op(x: X, y: X) = self.add(x, y)
  }
}

object AdditiveCSemigroup extends ImplicitGetter[AdditiveCSemigroup] {

  def create[@sp(fdi) X](f: (X, X) => X): AdditiveCSemigroup[X] = new AdditiveCSemigroup[X] {
    def add(x: X, y: X): X = f(x, y)
  }

}

