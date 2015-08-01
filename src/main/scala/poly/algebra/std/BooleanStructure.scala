package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object BooleanStructure extends BooleanAlgebra[BB] with TotalOrder[BB] {

  override final def cmp(x: BB, y: BB) = {
    if (x == y) 0
    else if (x) 1
    else -1
  }

  final val top = true
  final val bottom = false
  final def not(x: BB) = !x
  final def and(x: BB, y: BB) = x & y
  final def or(x: BB, y: BB) = x | y

  override final def sup(x: BB, y: BB) = x | y
  override final def inf(x: BB, y: BB) = x & y

}
