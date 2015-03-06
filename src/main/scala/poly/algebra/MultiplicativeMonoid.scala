package poly.algebra

trait MultiplicativeMonoid[@specialized(Int, Double) X] extends MultiplicativeSemigroup[X] with HasOne[X] { self =>

  override def ipow(x: X, n: Int): X = asMonoidWithMul.combineN(x, n)

  def asMonoidWithMul: Monoid[X] = new Monoid[X] {
    def op(x: X, y: X) = self.mul(x, y)
    def id = self.one
  }
}


object MultiplicativeMonoid {
  def apply[@specialized(Int, Double) X](implicit M: MultiplicativeMonoid[X]) = M
  def create[@specialized(Int, Double) X](f: (X, X) => X, oneElem: X) = new MultiplicativeMonoid[X] {
    def mul(x: X, y: X): X = f(x, y)
    def one: X = oneElem
  }
}

