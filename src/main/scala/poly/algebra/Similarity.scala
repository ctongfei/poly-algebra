package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a similarity measure.
 * @author Tongfei Chen
 */
trait Similarity[-X, @sp(fdi) S] {

  /** Returns the order on similarity values. */
  implicit def orderOnSimilarity: Order[S]

  /** Returns the similarity measure between two objects. */
  def sim(x: X, y: X): S
  
}

object Similarity extends BinaryImplicitGetter[Similarity] {
  def create[V, @sp(fdi) F](fSim: (V, V) => F)(implicit F: Order[F]): Similarity[V, F] = new Similarity[V, F] {
    def sim(x: V, y: V): F = fSim(x, y)
    def orderOnSimilarity = F
  }
}
