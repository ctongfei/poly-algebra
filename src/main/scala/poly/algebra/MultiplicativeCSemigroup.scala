package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents a multiplicative commutative semigroup.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawMultiplicativeAssociativity
 *  - $lawMultiplicativeCommutativity
 *
 * @define lawMultiplicativeCommutativity '''Multiplicative commutativity''':  ∀''a'', ''b''∈X, ''a'' * ''b'' == ''b'' * ''a''.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MultiplicativeCSemigroup[@sp(fdi) X] extends MultiplicativeSemigroup[X] {
  override def asSemigroupWithMul: CSemigroup[X] = new CSemigroup[X] {
    def op(x: X, y: X) = mul(x, y)
  }
}

object MultiplicativeCSemigroup extends ImplicitGetter[MultiplicativeCSemigroup] {
  def create[@sp(fdi) X](f: (X, X) => X): MultiplicativeCSemigroup[X] = new MultiplicativeCSemigroup[X] {
    def mul(x: X, y: X): X = f(x, y)
  }
}
