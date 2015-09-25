package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.4
 */
trait OrderedField[@sp(fd) X] extends Field[X] with TotalOrder[X] with OrderedRing[X]

object OrderedField extends ImplicitGetter[OrderedField]
