package poly.algebra

import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MetricSpace[V, @sp(di) F] {
  def dist(x: V, y: V): F
}

object MetricSpace {
  def apply[V, @sp(di) F](implicit S: MetricSpace[V, F]) = S
  def create[V, @sp(di) F](fDist: (V, V) => F): MetricSpace[V, F] = new MetricSpace[V, F] {
    def dist(x: V, y: V): F = fDist(x, y)
  }
}
