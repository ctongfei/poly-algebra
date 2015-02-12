package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MetricSpace[V, F] {
  def dist(x: V, y: V): F
}
