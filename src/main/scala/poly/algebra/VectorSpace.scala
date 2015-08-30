package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait VectorSpace[V, @sp(fd) F] extends Module[V, F] { self =>

  type LinearForm = V => F
  type BilinearForm = (V, V) => F

  implicit def fieldOfScalar: Field[F]
  def ringOfScalar: Ring[F] = fieldOfScalar

  /**
   * Returns the dual space of this vector space.
   * @since 0.2.2
   */
  def dualSpace: VectorSpace[V => F, F] = new FunctionSpaceOverField[V, F, F] {
    def fieldOfScalar: Field[F] = self.fieldOfScalar
    def vectorSpaceOfCodomain = VectorSpace.trivial(self.fieldOfScalar)
  }

}

object VectorSpace extends BinaryImplicitGetter[VectorSpace] {

  /**
   * Constructs the trivial vector space of any field over itself.
   * @param F The field
   * @tparam V Type of values of the field
   * @return The trivial vector space of a field over itself.
   */
  implicit def trivial[@sp(fd) V](implicit F: Field[V]): VectorSpace[V, V] = new VectorSpace[V, V] {
    def fieldOfScalar = F
    def add(x: V, y: V) = F.add(x, y)
    override def neg(x: V) = F.neg(x)
    override def sub(x: V, y: V) = F.sub(x, y)
    def zero = F.zero
    def scale(y: V, x: V) = F.mul(x, y)
  }

  def create[V, @sp(fd) F](fAdd: (V, V) => V, fScale: (F, V) => V, fZero: V)(implicit F: Field[F]): VectorSpace[V, F] = new VectorSpace[V, F] {
    def fieldOfScalar = F
    def add(x: V, y: V) = fAdd(x, y)
    def scale(x: V, k: F) = fScale(k, x)
    def zero = fZero
  }

}
