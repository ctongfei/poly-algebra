package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a similarity measure.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Similarity[-X, @sp(fdi) +S] {

  def sim(x: X, y: X): S
  
}

object Similarity extends BinaryImplicitGetter[Similarity] {
  def create[V, @sp(fdi) F](fSim: (V, V) => F): Similarity[V, F] = new Similarity[V, F] {
    def sim(x: V, y: V): F = fSim(x, y)
  }
}
