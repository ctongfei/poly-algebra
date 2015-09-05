package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents an algebra over a ring, i.e., a module equipped with a bilinear product.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.1.0
 */
trait AlgebraOverRing[V, @sp(fdi) R] extends Module[V, R] with CRing[V]

object AlgebraOverRing extends BinaryImplicitGetter[AlgebraOverRing] {

  implicit def trivial[@sp(fdi) X](implicit R: Ring[X]): AlgebraOverRing[X, X] = new AlgebraOverRing[X, X] {
    def ringOfScalar = R
    def zero = R.zero
    def one = R.one
    def add(x: X, y: X) = R.add(x, y)
    override def sub(x: X, y: X) = R.sub(x, y)
    override def neg(x: X) = R.neg(x)
    def mul(x: X, y: X) = R.mul(x, y)
    def scale(y: X, x: X) = R.mul(x, y)
  }

}
