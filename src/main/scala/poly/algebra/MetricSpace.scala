package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MetricSpace[-V, @sp(fdi) +F] {
  def dist(x: V, y: V): F
}

object MetricSpace extends BinaryImplicitGetter[MetricSpace] {
  def create[V, @sp(fdi) F](fDist: (V, V) => F): MetricSpace[V, F] = new MetricSpace[V, F] {
    def dist(x: V, y: V): F = fDist(x, y)
  }
}
