package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

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
trait AdditiveSemigroup[@sp(fdil) S] { self =>

  /** The `+` operation of this semigroup. */
  def add(x: S, y: S): S

  /** Computes the sum ''x'' + ''x'' + ··· + ''x'' with ''x'' repeated for ''n'' times. */
  def sumN(x: S, n: Int): S = asSemigroupWithAdd.combineN(x, n)

  /** Casts this structure as a symbol-agnostic semigroup. */
  def asSemigroupWithAdd: Semigroup[S] = new Semigroup[S] {
    def op(x: S, y: S) = self.add(x, y)
  }
}

object AdditiveSemigroup extends ImplicitGetter[AdditiveSemigroup] {
  /** Creates an additive semigroup of the specific type using the `+` operation provided. */
  def create[@sp(fdi) X](f: (X, X) => X): AdditiveSemigroup[X] = new AdditiveSemigroup[X] {
    def add(x: X, y: X): X = f(x, y)
  }
}




