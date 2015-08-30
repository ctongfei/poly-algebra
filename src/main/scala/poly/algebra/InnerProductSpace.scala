package poly.algebra

import poly.algebra.ops._
import poly.algebra.factory._
import poly.util.specgroup._

/**
 * Typeclass for inner product spaces.
 * An inner product space is a vector space endowed with an inner product operation.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.1.0
 */
trait InnerProductSpace[V, @sp(fd) F] extends NormedVectorSpace[V, F] {
  implicit def powerOpsOfScalar: PowerOps[F]
  def dot(x: V, y: V): F
  def norm(x: V) = powerOpsOfScalar.sqrt(dot(x, x))

  /** Returns the angle between two vectors in this inner product space. */
  def angle(x: V, y: V)(implicit txo: TrigExpOps[F]): F = {
    val F = fieldOfScalar
    val cosine = F.div(dot(x, y), F.mul(norm(x), norm(y)))
    txo.arccos(cosine)
  }

  /** Returns the cosine similarity measure based on this inner product. */
  def cosineSimilarity: SimilarityMeasure[V, F] = new SimilarityMeasure[V, F] {
    def sim(x: V, y: V): F = {
      val F = fieldOfScalar
      F.div(dot(x, y), F.mul(norm(x), norm(y)))
    }
  }

}

object InnerProductSpace extends BinaryImplicitGetter[InnerProductSpace] {
  def create[V, @sp(fd) F: PowerOps : Field](fDot: (V, V) => F)(implicit S: VectorSpace[V, F]): InnerProductSpace[V, F] =
    new InnerProductSpace[V, F] {
      def powerOpsOfScalar = implicitly[PowerOps[F]]
      def fieldOfScalar = implicitly[Field[F]]
      def dot(x: V, y: V): F = fDot(x, y)
      def add(x: V, y: V): V = S.add(x, y)
      override def neg(x: V): V = S.neg(x)
      override def sub(x: V, y: V): V = S.sub(x, y)
      def zero: V = S.zero
      def scale(x: V, k: F): V = S.scale(x, k)
    }
}
