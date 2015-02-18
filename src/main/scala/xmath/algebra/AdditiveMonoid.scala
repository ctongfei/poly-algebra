package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait AdditiveMonoid[@specialized(Int, Float, Double) X] extends AdditiveSemigroup[X] with HasZero[X] { self =>

  override def sumN(x: X, n: Int): X = asMonoidWithAdd.combineN(x, n)

  def asMonoidWithAdd: Monoid[X] = new Monoid[X] {
    def op(x: X, y: X) = self.add(x, y)
    def id = self.zero
  }
}

object AdditiveMonoid {
  def apply[X](implicit M: AdditiveMonoid[X]) = M
  def create[X](f: (X, X) => X, zeroElem: X) = new AdditiveMonoid[X] {
    def add(x: X, y: X): X = f(x, y)
    def zero: X = zeroElem
  }
}

