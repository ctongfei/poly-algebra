package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MetricSpace[V, @miniboxed  F] {
  def dist(x: V, y: V): F
}

object MetricSpace {
  def apply[V, @miniboxed F](implicit S: MetricSpace[V, F]) = S
  def create[V, @miniboxed F](fDist: (V, V) => F) = new MetricSpace[V, F] {
    def dist(x: V, y: V): F = fDist(x, y)
  }
}
