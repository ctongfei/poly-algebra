package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Semiring[@sp(fdi) X] extends AdditiveMonoid[X] with MultiplicativeMonoid[X]

object Semiring extends ImplicitGetter[Semiring] {

  /** Creates a semiring using the provided operations `add`, `mul`, `zero`, and `one`. */
  def create[@sp(di) X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X)
  : Semiring[X] = new Semiring[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = zeroElem
    def one: X = oneElem
  }

  def from[@sp(di) X](am: AdditiveMonoid[X], mm: MultiplicativeMonoid[X]): Semiring[X] = new Semiring[X] {
    def one: X = mm.one
    def add(x: X, y: X): X = am.add(x, y)
    def mul(x: X, y: X): X = mm.mul(x, y)
    def zero: X = am.zero
  }
}
