package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.4
 */
trait OrderedRing[@sp(fdi) X] extends Ring[X] with OrderedAdditiveCGroup[X] with SignOps[X] {

  def sgn(x: X) = if (isPositive(x)) one else if (isNegative(x)) negOne else zero

}

object OrderedRing extends ImplicitGetter[OrderedRing]
