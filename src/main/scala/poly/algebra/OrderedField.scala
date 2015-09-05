package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.4
 */
trait OrderedField[@sp(fd) X] extends Field[X] with TotalOrder[X] with OrderedRing[X]

object OrderedField extends ImplicitGetter[OrderedField]
