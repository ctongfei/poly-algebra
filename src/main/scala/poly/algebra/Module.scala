package poly.algebra

import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Module[V, @sp(fdi) R] extends AdditiveGroup[V] { self =>
  def ringOfScalar: Ring[R]
  def scale(x: V, k: R): V
  def neg(x: V): V = scale(x, ringOfScalar.negOne)
}

object Module {

  /** Retrieves the implicit module associated with the specific type. */
  def apply[V, @sp(fdi) R](implicit M: Module[V, R]) = M

  def create[V, @sp(fdi) R](fAdd: (V, V) => V, fScale: (R, V) => V, fZero: V)(implicit R: Ring[R]): Module[V, R] = new Module[V, R] {
    def ringOfScalar = R
    def zero = fZero
    def add(x: V, y: V) = fAdd(x, y)
    def scale(x: V, k: R) = fScale(k, x)
  }

  /**
   * Constructs the trivial module of any ring over itself.
   * @param R The ring
   * @tparam V Type of values of the ring
   * @return The trivial module of a ring over itself.
   */
  implicit def trivial[V](implicit R: Ring[V]): Module[V, V] = new Module[V, V] {
    def ringOfScalar = R
    def add(x: V, y: V) = R.add(x, y)
    override def neg(x: V) = R.neg(x)
    override def sub(x: V, y: V) = R.sub(x, y)
    def zero = R.zero
    def scale(y: V, x: V) = R.mul(x, y)
  }
}
