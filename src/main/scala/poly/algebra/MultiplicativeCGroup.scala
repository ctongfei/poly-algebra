package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object MultiplicativeCGroup extends ImplicitGetter[MultiplicativeCGroup] {
  def create[@sp(fd) X](f: (X, X) => X, oneElem: X, fInv: X => X): MultiplicativeCGroup[X] = new MultiplicativeCGroup[X] {
    def inv(x: X): X = fInv(x)
    def mul(x: X, y: X): X = f(x, y)
    def one: X = oneElem
  }
}

trait MultiplicativeCGroup[@sp(fd) X] extends MultiplicativeGroup[X] with MultiplicativeCMonoid[X] { self =>
  override def asGroupWithMul: CGroup[X] = new CGroup[X] {
    def inv(x: X) = self.inv(x)
    def id = self.one
    def op(x: X, y: X) = self.mul(x, y)
  }
}