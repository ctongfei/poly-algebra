package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * Represents an additive commutative semigroup.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.6
 */
object AdditiveCSemigroup extends ImplicitGetter[AdditiveCSemigroup] {
  def create[@sp(fdi) X](f: (X, X) => X): AdditiveCSemigroup[X] = new AdditiveCSemigroup[X] {
    def add(x: X, y: X): X = f(x, y)
  }
}

trait AdditiveCSemigroup[@sp(fdi) X] extends AdditiveSemigroup[X] { self =>
  override def asSemigroupWithAdd: CSemigroup[X] = new CSemigroup[X] {
    def op(x: X, y: X) = self.add(x, y)
  }
}