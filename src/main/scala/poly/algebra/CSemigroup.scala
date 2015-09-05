package poly.algebra

import poly.util.typeclass._

/**
 * Represents a commutative semigroup.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.6
 */
trait CSemigroup[X] extends Semigroup[X]

object CSemigroup extends ImplicitGetter[CSemigroup]
