package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen
 */
trait OrderedAdditiveGroup[@sp(fdi) X] extends AdditiveGroup[X] with TotalOrder[X] {

  def isPositive(x: X) = gt(x, zero)

  def isNegative(x: X) = lt(x, zero)

}

object OrderedAdditiveGroup extends ImplicitGetter[OrderedAdditiveGroup]
