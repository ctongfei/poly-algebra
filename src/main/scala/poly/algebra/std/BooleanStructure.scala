package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object BooleanStructure extends BooleanAlgebra[Boolean] with TotalOrder[Boolean] {

  override final def cmp(x: Boolean, y: Boolean) = {
    if (x == y) 0
    else if (x) 1
    else -1
  }

  final val top = true
  final val bot = false
  final def not(x: Boolean) = !x
  final def and(x: Boolean, y: Boolean) = x & y
  final def or(x: Boolean, y: Boolean) = x | y
  override def xor(x: Boolean, y: Boolean) = x ^ y

  override final def sup(x: Boolean, y: Boolean) = x | y
  override final def inf(x: Boolean, y: Boolean) = x & y

}
