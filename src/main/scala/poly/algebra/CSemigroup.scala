package poly.algebra

import poly.algebra.factory._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object CSemigroup extends ImplicitGetter[CSemigroup]

trait CSemigroup[X] extends Semigroup[X]