package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Module[V, @sp(fdi) R] extends AdditiveGroup[V] { self =>
  def ringOfScalar: Ring[R]
  def scale(x: V, k: R): V
  def neg(x: V): V = scale(x, ringOfScalar.negOne)
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
