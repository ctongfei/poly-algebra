package poly.algebra

import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait OrderedAdditiveCGroup[@sp(fdi) X] extends OrderedAdditiveGroup[X] with AdditiveCGroup[X] with MetricSpace[X, X] {

  def abs(x: X) = if (lt(x, zero)) neg(x) else x

  def dist(x: X, y: X) = abs(sub(x, y))

}
