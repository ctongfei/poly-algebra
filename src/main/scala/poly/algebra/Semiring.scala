package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents a semiring. A semiring is a commutative additive monoid as well as a multiplicative monoid.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAdditiveAssociativity
 *  - $lawAdditiveIdentity
 *  - $lawAdditiveCommutativity
 *  - $lawMultiplicativeAssociativity
 *  - $lawMultiplicativeIdentity
 *  - $lawDistributivityMA
 *  - $lawAnnihilationM
 *
 * @define lawDistributivityMA '''Distributivity of multiplication w.r.t. addition''': ∀''a'', ''b'', ''c''∈X, (''a'' + ''b'') * ''c'' == ''a'' * ''c'' + ''b'' * ''c'' and ''a'' * (''b'' + ''c'') == ''a'' * ''b'' + ''a'' * ''c''.
 * @define lawAnnihilationM '''Zero as an annihilator''': ∀''a''∈X, 0 * ''a'' == ''a'' * 0 == 0.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Semiring[@sp(fdi) X] extends AdditiveCMonoid[X] with MultiplicativeMonoid[X]

object Semiring extends ImplicitGetter[Semiring] {

  /** Creates a semiring using the provided operations `add`, `mul`, `zero`, and `one`. */
  def create[@sp(di) X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X)
  : Semiring[X] = new Semiring[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = zeroElem
    def one: X = oneElem
  }

  def from[@sp(di) X](am: AdditiveCMonoid[X], mm: MultiplicativeMonoid[X]): Semiring[X] = new Semiring[X] {
    def one: X = mm.one
    def add(x: X, y: X): X = am.add(x, y)
    def mul(x: X, y: X): X = mm.mul(x, y)
    def zero: X = am.zero
  }
}




