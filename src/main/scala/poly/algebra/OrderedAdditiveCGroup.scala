package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen
 */
trait OrderedAdditiveCGroup[@sp(fdi) X] extends OrderedAdditiveGroup[X] with AdditiveCGroup[X] with MetricSpace[X, X] {

  def abs(x: X) = if (lt(x, zero)) neg(x) else x

  def dist(x: X, y: X) = abs(sub(x, y))

}

object OrderedAdditiveCGroup extends ImplicitGetter[OrderedAdditiveCGroup]
