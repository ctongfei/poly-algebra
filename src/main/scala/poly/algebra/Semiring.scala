package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Semiring[@specialized(Int, Double) X] extends AdditiveMonoid[X] with MultiplicativeMonoid[X]

object Semiring {
  def apply[@specialized(Int, Double) X](implicit S: Semiring[X]) = S
  def create[@specialized(Int, Double) X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X) = new Semiring[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = zeroElem
    def one: X = oneElem
  }
}
