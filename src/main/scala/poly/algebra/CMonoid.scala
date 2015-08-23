package poly.algebra

import poly.algebra.factory._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object CMonoid extends ImplicitGetter[CMonoid]

trait CMonoid[X] extends Monoid[X] with CSemigroup[X]