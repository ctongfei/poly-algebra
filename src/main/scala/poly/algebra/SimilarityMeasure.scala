package poly.algebra

import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait SimilarityMeasure[V, @sp(di) F] {

  def sim(x: V, y: V): F
  
}

object SimilarityMeasure {
  def apply[V, @sp(di) F](implicit S: SimilarityMeasure[V, F]) = S
  def create[V, @sp(di) F](fSim: (V, V) => F): SimilarityMeasure[V, F] = new SimilarityMeasure[V, F] {
    def sim(x: V, y: V): F = fSim(x, y)
  }
}
