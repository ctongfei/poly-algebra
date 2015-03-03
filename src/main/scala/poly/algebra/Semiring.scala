package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Semiring[@miniboxed X] extends AdditiveMonoid[X] with MultiplicativeMonoid[X]

object Semiring {
  def apply[@miniboxed X](implicit S: Semiring[X]) = S
  def create[@miniboxed X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X) = new Semiring[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = zeroElem
    def one: X = oneElem
  }
}
