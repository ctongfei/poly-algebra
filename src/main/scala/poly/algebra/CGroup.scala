package poly.algebra

import poly.util.typeclass._

/**
 * Represents an Abelian (commutative) group.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.6
 */
trait CGroup[X] extends CMonoid[X] with Group[X]
object CGroup extends ImplicitGetter[CGroup]
