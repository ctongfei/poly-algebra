package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object IntStructure extends EuclideanDomain[ZZ] with TotalOrder[ZZ] with Hash[ZZ] with SignOps[ZZ] {
  def eq(x: ZZ, y: ZZ) = x == y
  override def ne(x: ZZ, y: ZZ) = x != y
  def hash(x: ZZ) = x.hashCode()

  def cmp(x: ZZ, y: ZZ) = x - y
  override def le(x: ZZ, y: ZZ) = x <= y
  override def lt(x: ZZ, y: ZZ) = x < y
  override def ge(x: ZZ, y: ZZ) = x >= y
  override def gt(x: ZZ, y: ZZ) = x > y
  override def max(x: ZZ, y: ZZ) = if (x > y) x else y
  override def min(x: ZZ, y: ZZ) = if (x < y) x else y
  def zero = 0
  def one = 1
  def add(x: ZZ, y: ZZ) = x + y
  def neg(x: ZZ) = -x
  override def sub(x: ZZ, y: ZZ) = x - y
  def mul(x: ZZ, y: ZZ) = x * y
  def quot(x: ZZ, y: ZZ) = x / y
  def mod(x: ZZ, y: ZZ) = x % y

  def abs(x: ZZ) = Math.abs(x)
  def sgn(x: ZZ) = if (x == 0) 0 else if (x > 0) 1 else -1
}
