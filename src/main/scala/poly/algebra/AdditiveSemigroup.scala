package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents an additive semigroup.
 * An additive semigroup is a semigroup with the binary operation `add` (`+`).
 *
 * An instance of this typeclass should satisfy the following axiom:
 *  - $lawAdditiveAssociativity
 *
 * @define lawAdditiveAssociativity '''Additive associativity''': ∀''a'', ''b'', ''c''∈X, (''a'' + ''b'') + ''c'' == ''a'' + (''b'' + ''c'').
 * @since 0.1.0
 */
trait AdditiveSemigroup[@sp(fdi) X] { self =>

  /** The `+` operation of this semigroup. */
  def add(x: X, y: X): X

  /** Computes the sum ''x'' + ''x'' + ··· + ''x'' with ''x'' repeated for ''n'' times. */
  def sumN(x: X, n: Int): X = asSemigroupWithAdd.combineN(x, n)

  /** Casts this structure as a symbol-agnostic semigroup. */
  def asSemigroupWithAdd: Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X) = self.add(x, y)
  }
}

object AdditiveSemigroup extends ImplicitGetter[AdditiveSemigroup] {

  /** Creates an additive semigroup of the specific type using the `+` operation provided. */
  def create[@sp(fdi) X](f: (X, X) => X): AdditiveSemigroup[X] = new AdditiveSemigroup[X] {
    def add(x: X, y: X): X = f(x, y)
  }
}




