package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * Represents a similarity measure.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait SimilarityMeasure[-V, @sp(fdi) +F] {

  def sim(x: V, y: V): F
  
}

object SimilarityMeasure extends BinaryImplicitGetter[SimilarityMeasure] {
  def create[V, @sp(fdi) F](fSim: (V, V) => F): SimilarityMeasure[V, F] = new SimilarityMeasure[V, F] {
    def sim(x: V, y: V): F = fSim(x, y)
  }
}
