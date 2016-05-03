package poly.algebra

import poly.algebra.ops._
import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Typeclass for inner product spaces.
 * An inner product space is a vector space endowed with an inner product operation.
 *
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait InnerProductSpace[V, @sp(fd) F] extends NormedVectorSpace[V, F] {
  implicit def powerOpsOnScalar: PowerOps[F]
  def dot(x: V, y: V): F
  def norm(x: V) = powerOpsOnScalar.sqrt(dot(x, x))

  /** Returns the angle between two vectors in this inner product space. */
  def angle(x: V, y: V)(implicit txo: TrigExpOps[F]): F = {
    val F = ringOnScalar
    val cosine = F.div(dot(x, y), F.mul(norm(x), norm(y)))
    txo.arccos(cosine)
  }

  /** Returns the cosine similarity measure based on this inner product. */
  def cosineSimilarity(implicit F: Order[F]): Similarity[V, F] = new Similarity[V, F] {
    def orderOnSimilarity = F
    def sim(x: V, y: V): F = {
      val F = ringOnScalar
      F.div(dot(x, y), F.mul(norm(x), norm(y)))
    }
  }

}

object InnerProductSpace extends BinaryImplicitGetter[InnerProductSpace] {
  def create[V, @sp(fd) F: PowerOps : Field](fDot: (V, V) => F)(implicit V: VectorSpace[V, F]): InnerProductSpace[V, F] =
    new InnerProductSpace[V, F] {
      def powerOpsOnScalar = implicitly[PowerOps[F]]
      def fieldOnScalar = implicitly[Field[F]]
      def dot(x: V, y: V): F = fDot(x, y)
      def add(x: V, y: V): V = V.add(x, y)
      override def neg(x: V): V = V.neg(x)
      override def sub(x: V, y: V): V = V.sub(x, y)
      def zero: V = V.zero
      def scale(x: V, k: F): V = V.scale(x, k)
    }
}
