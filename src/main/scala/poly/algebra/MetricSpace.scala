package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MetricSpace[@miniboxed V, @miniboxed  F] {
  def dist(x: V, y: V): F
}

object MetricSpace {
  def apply[@miniboxed V, @miniboxed F](implicit S: MetricSpace[V, F]) = S
  def create[@miniboxed V, @miniboxed F](fDist: (V, V) => F) = new MetricSpace[V, F] {
    def dist(x: V, y: V): F = fDist(x, y)
  }
}
