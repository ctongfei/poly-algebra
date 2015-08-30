package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * Represents an algebra over a field, i.e., a vector space equipped with a
 * bilinear product over the type of vectors.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.1.0
 */
trait AlgebraOverField[V, @sp(fd) F] extends VectorSpace[V, F] with AlgebraOverRing[V, F]

object AlgebraOverField extends BinaryImplicitGetter[AlgebraOverField] {

  implicit def trivial[@sp(fd) X](implicit F: Field[X]): AlgebraOverField[X, X] = new AlgebraOverField[X, X] {
    def fieldOfScalar = F
    def zero = F.zero
    def one = F.one
    def add(x: X, y: X) = F.add(x, y)
    override def sub(x: X, y: X) = F.sub(x, y)
    override def neg(x: X) = F.neg(x)
    def inv(x: X) = F.inv(x)
    def mul(x: X, y: X) = F.mul(x, y)
    def scale(y: X, x: X) = F.mul(x, y)
  }

}