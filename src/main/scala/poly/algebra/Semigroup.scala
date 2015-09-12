package poly.algebra

import poly.util.typeclass._

/**
 *
 * Typeclass for semigroups.
 * A semigroup is a set equipped with an associative binary operation.
 *
 * An instance of this typeclass should satisfy the following axiom:
 *  - $lawAssociativity
 * @define lawAssociativity '''Associativity''': ∀''a'', ''b'', ''c''∈X, (''a'' op ''b'') op ''c'' == ''a'' op (''b'' op ''c'').
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Semigroup[X] {

  /** The associative binary operation of this semigroup. */
  def op(x: X, y: X): X

  /** Combines an element with itself for ''n'' times using the binary exponentiation algorithm. */
  def combineN(x: X, n: Int): X = {
    require(n > 0)
    var y = x
    var m = n
    while (m % 2 == 0) {
      m >>= 1
      y = op(y, y)
    }
    var r = y
    while (m > 1) {
      m >>= 1
      y = op(y, y)
      if (m % 2 == 1)
        r = op(r, y)
    }
    r
  }


}

object Semigroup extends ImplicitGetter[Semigroup] {

  /** Creates a semigroup of the specific type using the binary operation provided. */
  def create[X](f: (X, X) => X): Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X): X = f(x, y)
  }
}




