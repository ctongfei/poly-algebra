package poly.algebra

import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Semiring[@sp(di) X] extends AdditiveMonoid[X] with MultiplicativeMonoid[X]

object Semiring {
  def apply[@sp(di) X](implicit S: Semiring[X]) = S

  /** Creates a semiring using the provided operations `add`, `mul`, `zero`, and `one`. */
  def create[@sp(di) X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X)
  : Semiring[X] = new Semiring[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = zeroElem
    def one: X = oneElem
  }
}
