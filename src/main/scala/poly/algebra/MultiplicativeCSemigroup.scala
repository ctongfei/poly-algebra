package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object MultiplicativeCSemigroup extends ImplicitGetter[MultiplicativeCSemigroup] {
  def create[@sp(fdi) X](f: (X, X) => X): MultiplicativeCSemigroup[X] = new MultiplicativeCSemigroup[X] {
    def mul(x: X, y: X): X = f(x, y)
  }
}

trait MultiplicativeCSemigroup[@sp(fdi) X] extends MultiplicativeSemigroup[X] {
  override def asSemigroupWithMul: CSemigroup[X] = new CSemigroup[X] {
    def op(x: X, y: X) = mul(x, y)
  }
}