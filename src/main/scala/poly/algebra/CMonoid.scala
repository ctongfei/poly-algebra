package poly.algebra

import poly.util.typeclass._

/**
 * Represents a commutative monoid.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.6
 */
trait CMonoid[X] extends Monoid[X] with CSemigroup[X]
object CMonoid extends ImplicitGetter[CMonoid]
