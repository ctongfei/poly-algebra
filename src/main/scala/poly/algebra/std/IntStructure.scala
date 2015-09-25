package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object IntStructure extends EuclideanDomain[Int] with BoundedLattice[Int] with MetricSpace[Int, Int] {

  final val bot: Int = Int.MinValue
  final val top: Int = Int.MaxValue
  
  def cmp(x: Int, y: Int) = x - y
  override def le(x: Int, y: Int) = x <= y
  override def lt(x: Int, y: Int) = x < y
  override def ge(x: Int, y: Int) = x >= y
  override def gt(x: Int, y: Int) = x > y
  override def max[Y <: Int](x: Y, y: Y) = if (x > y) x else y
  override def min[Y <: Int](x: Y, y: Y) = if (x < y) x else y

  final val zero = 0
  final val one = 1
  override final val negOne = -1
  def add(x: Int, y: Int) = x + y
  def neg(x: Int) = -x
  override def sub(x: Int, y: Int) = x - y
  def mul(x: Int, y: Int) = x * y
  def div(x: Int, y: Int) = x / y
  def mod(x: Int, y: Int) = x % y

  override def abs(x: Int) = Math.abs(x)
  override def sgn(x: Int) = if (x == 0) 0 else if (x > 0) 1 else -1
  override def dist(x: Int, y: Int) = Math.abs(x - y)

}
