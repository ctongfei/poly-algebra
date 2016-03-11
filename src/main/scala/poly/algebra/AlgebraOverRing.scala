package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an algebra over a ring, i.e., a module equipped with a bilinear product.
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait AlgebraOverRing[X, @sp(fdi) S] extends Module[X, S] with CRing[X]

object AlgebraOverRing extends BinaryImplicitGetter[AlgebraOverRing] {

  implicit def trivial[@sp(fdi) X](implicit R: Ring[X]): AlgebraOverRing[X, X] = new AlgebraOverRing[X, X] {
    def ringOnScalar = R
    def zero = R.zero
    def one = R.one
    def add(x: X, y: X) = R.add(x, y)
    override def sub(x: X, y: X) = R.sub(x, y)
    override def neg(x: X) = R.neg(x)
    def mul(x: X, y: X) = R.mul(x, y)
    def scale(x: X, y: X) = R.mul(x, y)
  }

}
