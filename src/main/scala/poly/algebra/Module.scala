package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Typeclass for modules.
 *
 * A module over a ring is an additive Abelian group ([[poly.algebra.AdditiveCGroup]])
 * on vectors that supports a linear distributive scaling function. It is a generalization
 * of a vector space because the scalars need only be a ring ([[poly.algebra.Ring]]).
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Module[V, @sp(fdi) R] extends AdditiveCGroup[V] { self =>

  type LinearForm <: V => R
  type BilinearForm <: (V, V) => R

  implicit def ringOfScalar: Ring[R]

  /** Scales a vector by a scalar. */
  def scale(x: V, k: R): V
  def neg(x: V): V = scale(x, ringOfScalar.negOne)

  /**
   * Returns the dual space of this module.
   * @since 0.2.7
   */
  def dualSpace: Module[V => R, R] = new FunctionSpaceOverRing[V, R, R] {
    def moduleOfCodomain = Module.trivial(self.ringOfScalar)
    implicit def ringOfScalar = self.ringOfScalar
  }
}

object Module extends BinaryImplicitGetter[Module] {

  def create[V, @sp(fdi) R](fAdd: (V, V) => V, fScale: (R, V) => V, fZero: V)(implicit R: Ring[R]): Module[V, R] = new Module[V, R] {
    def ringOfScalar = R
    def zero = fZero
    def add(x: V, y: V) = fAdd(x, y)
    def scale(x: V, k: R) = fScale(k, x)
  }

  /**
   * Constructs the trivial module of any ring over itself.
   * @param R The ring
   * @tparam R Type of values of the ring
   * @return The trivial module of a ring over itself.
   */
  implicit def trivial[R](implicit R: Ring[R]): Module[R, R] = new Module[R, R] {
    def ringOfScalar = R
    def add(x: R, y: R) = R.add(x, y)
    override def neg(x: R) = R.neg(x)
    override def sub(x: R, y: R) = R.sub(x, y)
    def zero = R.zero
    def scale(y: R, x: R) = R.mul(x, y)
  }
}
