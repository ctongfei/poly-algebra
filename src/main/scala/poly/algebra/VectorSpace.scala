package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents a vector space over a field.
 *
 * A vector space over a field ([[poly.algebra.Field]]) is a set of vectors
 * together with a linear distributive scaling function.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - S is a field.
 *  - $lawAdditiveAssociativity
 *  - $lawAdditiveIdentity
 *  - $lawAdditiveInvertibility
 *  - $lawAdditiveCommutativity
 *  - $lawCompatibility
 *  - $lawScalingIdentity
 *  - $lawDistributivitySV
 *  - $lawDistributivitySS
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait VectorSpace[X, @sp(fd) S] extends Module[X, S] { self =>

  type LinearForm <: X => S
  type BilinearForm <: (X, X) => S

  implicit def fieldOfScalar: Field[S]
  def ringOfScalar: Ring[S] = fieldOfScalar

  /**
   * Returns the dual space of this vector space.
   * @since 0.2.2
   */
  override def dualSpace: VectorSpace[X => S, S] = new FunctionSpaceOverField[X, S, S] {
    def fieldOfScalar: Field[S] = self.fieldOfScalar
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
