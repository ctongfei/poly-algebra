package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MetricSpace[V, @specialized(Float, Double) F] {
  def dist(x: V, y: V): F
}

object MetricSpace {
  def apply[V, F](implicit S: MetricSpace[V, F]) = S
  def create[V, F](fDist: (V, V) => F) = new MetricSpace[V, F] {
    def dist(x: V, y: V): F = fDist(x, y)
  }
}
