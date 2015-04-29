package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object BooleanStructure extends BooleanAlgebra[BB] with TotalOrder[BB] with Hash[BB] {
  def hash(x: BB) = x.hashCode()

  override def cmp(x: BB, y: BB) = {
    if (x == y) 0
    else if (x) 1
    else -1
  }

  def one = true
  def zero = false
  def not(x: BB) = !x
  def and(x: BB, y: BB) = x & y
  def or(x: BB, y: BB) = x | y

  override def sup(x: BB, y: BB) = x | y
  override def inf(x: BB, y: BB) = x & y

}
