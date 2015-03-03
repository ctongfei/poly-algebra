package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object IntStructure extends EuclideanDomain[Int] with TotalOrder[Int] with Hash[Int] with Signed[Int] {
  def eq(x: Int, y: Int) = x == y
  override def ne(x: Int, y: Int) = x != y
  def hash(x: Int) = x.hashCode()

  override def le(x: Int, y: Int) = x <= y
  override def lt(x: Int, y: Int) = x < y
  override def ge(x: Int, y: Int) = x >= y
  override def gt(x: Int, y: Int) = x > y
  def max(x: Int, y: Int) = if (x > y) x else y
  def min(x: Int, y: Int) = if (x < y) x else y
  def zero = 0
  def one = 1
  def add(x: Int, y: Int) = x + y
  def neg(x: Int) = -x
  override def sub(x: Int, y: Int) = x - y
  def mul(x: Int, y: Int) = x * y
  def quot(x: Int, y: Int) = x / y
  def mod(x: Int, y: Int) = x % y

  def abs(x: Int) = Math.abs(x)
  def sgn(x: Int) = if (x == 0) 0 else if (x > 0) 1 else -1
}
