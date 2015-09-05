package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object MultiplicativeCMonoid extends ImplicitGetter[MultiplicativeCMonoid] {
  def create[@sp(fdi) X](f: (X, X) => X, oneElem: X): MultiplicativeCMonoid[X] = new MultiplicativeCMonoid[X] {
    def mul(x: X, y: X): X = f(x, y)
    def one: X = oneElem
  }
}

trait MultiplicativeCMonoid[@sp(fdi) X] extends MultiplicativeMonoid[X] with MultiplicativeCSemigroup[X] { self =>
  override def asMonoidWithMul: CMonoid[X] = new CMonoid[X] {
    def id = self.one
    def op(x: X, y: X) = self.mul(x, y)
  }
}