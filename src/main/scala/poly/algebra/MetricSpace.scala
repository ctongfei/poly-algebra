package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen
 */
trait MetricSpace[@sp(fdi) -X, @sp(fdi) +S] {

  /** Returns the distance of two elements under this metric space. */
  def dist(x: X, y: X): S
}

object MetricSpace extends BinaryImplicitGetter[MetricSpace] {
  def create[@sp(fdi) X, @sp(fdi) S](fDist: (X, X) => S): MetricSpace[X, S] = new MetricSpace[X, S] {
    def dist(x: X, y: X): S = fDist(x, y)
  }
}
