package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an multiplicative group.
 * A multiplicative group is a multiplicative monoid (with operation `*` and identity `1`)
 * that is also invertible.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawMultiplicativeAssociativity
 *  - $lawMultiplicativeIdentity
 *  - $lawMultiplicativeInvertibility
 *
 * @define lawMultiplicativeInvertibility '''Multiplicative invertibility''': ∀''a''∈X, ∃''a''^-1^∈X, ''a'' * ''a''^-1^ == ''a''^-1^ * ''a'' == 1.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MultiplicativeGroup[@sp(fd) X] extends MultiplicativeMonoid[X] { self =>

  def inv(x: X): X
  def div(x: X, y: X): X = mul(x, inv(y))
  def asGroupWithMul: Group[X] = new Group[X] {
    def inv(x: X) = self.inv(x)
    def op(x: X, y: X) = self.mul(x, y)
    def id = self.one
  }
}

object MultiplicativeGroup extends ImplicitGetter[MultiplicativeGroup] {
  def create[@sp(fd) X](f: (X, X) => X, oneElem: X, fInv: X => X): MultiplicativeGroup[X] = new MultiplicativeGroup[X] {
    def inv(x: X): X = fInv(x)
    def mul(x: X, y: X): X = f(x, y)
    def one: X = oneElem
  }
}




