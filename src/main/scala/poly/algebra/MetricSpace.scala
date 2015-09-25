package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait MetricSpace[-X, @sp(fdi) +S] {
  def dist(x: X, y: X): S
}

object MetricSpace extends BinaryImplicitGetter[MetricSpace] {
  def create[X, @sp(fdi) S](fDist: (X, X) => S): MetricSpace[X, S] = new MetricSpace[X, S] {
    def dist(x: X, y: X): S = fDist(x, y)
  }
}
