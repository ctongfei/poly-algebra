package poly.algebra

import poly.algebra.factory._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object CGroup extends ImplicitGetter[CGroup]

trait CGroup[X] extends CMonoid[X] with Group[X]
