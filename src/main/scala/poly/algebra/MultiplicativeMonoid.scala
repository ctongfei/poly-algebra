package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

trait MultiplicativeMonoid[@sp(fdi) X] extends MultiplicativeSemigroup[X] with HasOne[X] { self =>

  override def ipow(x: X, n: Int): X = asMonoidWithMul.combineN(x, n)

  def asMonoidWithMul: Monoid[X] = new Monoid[X] {
    def op(x: X, y: X) = self.mul(x, y)
    def id = self.one
  }
}


object MultiplicativeMonoid extends ImplicitGetter[MultiplicativeMonoid] {
  def create[@sp(fdi) X](f: (X, X) => X, oneElem: X): MultiplicativeMonoid[X] = new MultiplicativeMonoid[X] {
    def mul(x: X, y: X): X = f(x, y)
    def one: X = oneElem
  }
}





