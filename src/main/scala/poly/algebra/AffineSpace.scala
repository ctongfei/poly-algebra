package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an affine space.
 * @tparam P Type of points
 * @tparam V Type of vectors
 * @tparam F Type of field on which the vector space is based
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
trait AffineSpace[P, V, @sp(fd) F] extends AdditiveGroupAction[P, V] {
  def vectorSpaceOnVector: VectorSpace[V, F]
  def groupOnActor = vectorSpaceOnVector

  /** Returns the unique difference of two points in this affine space. */
  def sub(x: P, y: P): V
}

object AffineSpace extends TernaryImplicitGetter[AffineSpace] {

  /**
   * Constructs the trivial affine space of any vector space over itself.
   * @param V Type of the vector space
   */
  implicit def trivial[V, F](implicit V: VectorSpace[V, F]): AffineSpace[V, V, F] = new AffineSpace[V, V, F] {
    def vectorSpaceOnVector: VectorSpace[V, F] = V
    def sub(x: V, y: V): V = V.sub(x, y)
    def translate(k: V, x: V): V = V.add(k, x)
  }
}
