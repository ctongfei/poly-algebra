package poly.algebra

import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MetricSpace[V, @sp(fdi) F] {
  def dist(x: V, y: V): F
}

object MetricSpace {
  def apply[V, @sp(fdi) F](implicit S: MetricSpace[V, F]) = S
  def create[V, @sp(fdi) F](fDist: (V, V) => F): MetricSpace[V, F] = new MetricSpace[V, F] {
    def dist(x: V, y: V): F = fDist(x, y)
  }
}
