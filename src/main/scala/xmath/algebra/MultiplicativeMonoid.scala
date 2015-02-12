package xmath.algebra

trait MultiplicativeMonoid[@specialized X] extends MultiplicativeSemigroup[X] with HasOne[X] { self =>
  def monoidWithMul: Monoid[X] = new Monoid[X] {
    def op(x: X, y: X) = self.mul(x, y)
    def id = self.one
  }
}


object MultiplicativeMonoid {
  def apply[X](implicit M: MultiplicativeMonoid[X]) = M
  def create[X](f: (X, X) => X, oneElem: X) = new MultiplicativeMonoid[X] {
    def mul(x: X, y: X): X = f(x, y)
    def one: X = oneElem
  }
}

