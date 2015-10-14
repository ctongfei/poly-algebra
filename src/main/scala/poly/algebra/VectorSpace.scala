package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

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

  implicit def fieldOfScalar: Field[S]
  def ringOfScalar: Ring[S] = fieldOfScalar

  /**
   * Returns the dual space of this vector space.
   * @since 0.2.2
   */
  override def dualSpace: VectorSpace[X => S, S] = new VectorSpace[X => S, S] {
    def fieldOfScalar: Field[S] = self.fieldOfScalar

    def scale(f: X => S, k: S) = x => fieldOfScalar.mul(f(x), k)
    def zero = x => fieldOfScalar.zero
    def add(f: X => S, g: X => S) = x => fieldOfScalar.add(f(x), g(x))
    override def neg(f: X => S) = x => fieldOfScalar.neg(f(x))
    override def sub(f: X => S, g: X => S) = x => fieldOfScalar.sub(f(x), g(x))
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

  implicit def lift[X, Y, @sp(fd) F](implicit Y: VectorSpace[Y, F]): VectorSpace[X => Y, F] = new VectorSpace[X => Y, F] {
    implicit def fieldOfScalar = Y.fieldOfScalar
    def scale(f: X => Y, k: F) = x => Y.scale(f(x), k)
    def zero = x => Y.zero
    def add(f: X => Y, g: X => Y) = x => Y.add(f(x), g(x))
    override def sub(f: X => Y, g: X => Y) = x => Y.sub(f(x), g(x))
    override def neg(f: X => Y) = x => Y.neg(f(x))
  }

}
