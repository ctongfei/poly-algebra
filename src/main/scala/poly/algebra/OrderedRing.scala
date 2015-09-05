package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.4
 */
trait OrderedRing[@sp(fdi) X] extends Ring[X] with TotalOrder[X] with SignOps[X] with MetricSpace[X, X] {

  def sgn(x: X): X = if (gt(x, zero)) one else if (lt(x, zero)) negOne else zero

  def abs(x: X): X = if (ge(x, zero)) x else neg(x)

  def isPositive(x: X): Boolean = gt(x, zero)

  def isNegative(x: X): Boolean = lt(x, zero)

  def dist(x: X, y: X): X = abs(sub(x, y))

}

object OrderedRing extends ImplicitGetter[OrderedRing]
