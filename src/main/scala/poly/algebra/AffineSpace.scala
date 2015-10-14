package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
trait AffineSpace[P, V, @sp(fd) F] extends VectorSpace[V, F] with AdditiveAction[P, V] {

  implicit def fieldOfScalar = ???

  def translate(x: P, k: V) = ???

  /** Scales a vector by a scalar. */
  def scale(x: V, k: F) = ???

  /** The `0` element (additive identity) of this type. */
  def zero = ???

  /** The `+` operation of this semigroup. */
  def add(x: V, y: V) = ???
}

object AffineSpace extends TernaryImplicitGetter[AffineSpace]