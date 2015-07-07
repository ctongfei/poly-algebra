package poly.algebra

import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait SimilarityMeasure[V, @sp(fdi) F] {

  def sim(x: V, y: V): F
  
}

object SimilarityMeasure {
  def apply[V, @sp(fdi) F](implicit S: SimilarityMeasure[V, F]) = S
  def create[V, @sp(fdi) F](fSim: (V, V) => F): SimilarityMeasure[V, F] = new SimilarityMeasure[V, F] {
    def sim(x: V, y: V): F = fSim(x, y)
  }
}
