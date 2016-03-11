package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen
 * @since 0.2.4
 */
trait OrderedRing[@sp(fdi) X] extends Ring[X] with OrderedAdditiveCGroup[X] with SignOps[X] {

  def sgn(x: X) = if (gt(x, zero)) one else if (lt(x, zero)) negOne else zero

}

object OrderedRing extends ImplicitGetter[OrderedRing]
