package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object AdditiveCMonoid extends ImplicitGetter[AdditiveCMonoid] {
  def create[@sp(fdi) X](f: (X, X) => X, zeroElem: X): AdditiveMonoid[X] = new AdditiveCMonoid[X] {
    def add(x: X, y: X): X = f(x, y)
    def zero: X = zeroElem
  }
}

trait AdditiveCMonoid[@sp(fdi) X] extends AdditiveMonoid[X] with AdditiveCSemigroup[X] { self =>
  override def asMonoidWithAdd: CMonoid[X] = new CMonoid[X] {
    def id = self.zero
    def op(x: X, y: X) = self.add(x, y)
  }
}