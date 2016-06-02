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
 * <ul>
 *  <li> S is a field. </li>
 *  <li> $lawAdditiveAssociativity </li>
 *  <li> $lawAdditiveIdentity </li>
 *  <li> $lawAdditiveInvertibility </li>
 *  <li> $lawAdditiveCommutativity </li>
 *  <li> $lawCompatibility </li>
 *  <li> $lawScalingIdentity </li>
 *  <li> $lawDistributivitySV </li>
 *  <li> $lawDistributivitySS </li>
 * </ul>
 * @since 0.1.0
 * @author Tongfei Chen
 */
trait VectorSpace[X, @sp(fd) S] extends Module[X, S] { self =>

  /** Returns the field structure endowed on the set of scalars. */
  def fieldOnScalar: Field[S]

  def ringOnScalar = fieldOnScalar

  /**
   * Returns the dual space of this vector space.
   * @since 0.2.2
   */
  override def dual: VectorSpace[X => S, S] = new VectorSpace[X => S, S] {
    def fieldOnScalar = self.fieldOnScalar
    def scale(f: X => S, k: S) = x => fieldOnScalar.mul(f(x), k)
    def zero = x => fieldOnScalar.zero
    def add(f: X => S, g: X => S) = x => fieldOnScalar.add(f(x), g(x))
    override def neg(f: X => S) = x => fieldOnScalar.neg(f(x))
    override def sub(f: X => S, g: X => S) = x => fieldOnScalar.sub(f(x), g(x))
  }

}

object VectorSpace extends BinaryImplicitGetter[VectorSpace] {

  /**
   * Constructs the trivial vector space of any field over itself.
   * @tparam F Type of values of the field
   * @return The trivial vector space of a field over itself.
   */
  implicit def trivial[@sp(fd) F](implicit F: Field[F]): VectorSpace[F, F] = new VectorSpace[F, F] {
    def fieldOnScalar = F
    def add(x: F, y: F) = F.add(x, y)
    override def neg(x: F) = F.neg(x)
    override def sub(x: F, y: F) = F.sub(x, y)
    def zero = F.zero
    def scale(x: F, y: F) = F.mul(x, y)
  }

  /** Constructs the natural function space of type `X => Y` if there exists a vector space over `Y`. */
  implicit def lift[X, @sp(fd) Y, @sp(fd) F](implicit Y: VectorSpace[Y, F]): VectorSpace[X => Y, F] = new VectorSpace[X => Y, F] {
    implicit def fieldOnScalar = Y.ringOnScalar
    def scale(f: X => Y, k: F) = x => Y.scale(f(x), k)
    def zero = x => Y.zero
    def add(f: X => Y, g: X => Y) = x => Y.add(f(x), g(x))
    override def sub(f: X => Y, g: X => Y) = x => Y.sub(f(x), g(x))
    override def neg(f: X => Y) = x => Y.neg(f(x))
  }

}
