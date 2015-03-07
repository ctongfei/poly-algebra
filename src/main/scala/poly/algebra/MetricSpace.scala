package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MetricSpace[V, @specialized(Int, Double) F] {
  def dist(x: V, y: V): F
}

object MetricSpace {
  def apply[V, @specialized(Int, Double) F](implicit S: MetricSpace[V, F]) = S
  def create[V, @specialized(Int, Double) F](fDist: (V, V) => F) = new MetricSpace[V, F] {
    def dist(x: V, y: V): F = fDist(x, y)
  }
}
