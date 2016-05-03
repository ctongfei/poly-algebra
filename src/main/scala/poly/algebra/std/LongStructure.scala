package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen
 */
object LongStructure extends EuclideanDomain[Long] with BoundedLattice[Long] with SequentialOrder[Long] with OrderedHashing[Long] {

  final def hash(x: Long) = x.##

  final val bot: Long = Long.MinValue
  final val top: Long = Long.MaxValue

  def pred(x: Long) = x - 1l
  def succ(x: Long) = x + 1l
  override def predN(x: Long, n: Int) = x - n
  override def succN(x: Long, n: Int) = x + n

  def cmp(x: Long, y: Long) = if (x == y) 0 else if (x < y) -1 else 1
  override def le(x: Long, y: Long) = x <= y
  override def lt(x: Long, y: Long) = x < y
  override def ge(x: Long, y: Long) = x >= y
  override def gt(x: Long, y: Long) = x > y
  override def max[Y <: Long](x: Y, y: Y) = if (x > y) x else y
  override def min[Y <: Long](x: Y, y: Y) = if (x < y) x else y

  final val zero = 0l
  final val one = 1l
  override val negOne = -1l // ! `final` causes compiler generating a class that when loaded, throws ClassFormatException
  def add(x: Long, y: Long) = x + y
  def neg(x: Long) = -x
  override def sub(x: Long, y: Long) = x - y
  def mul(x: Long, y: Long) = x * y
  def div(x: Long, y: Long) = x / y
  def mod(x: Long, y: Long) = x % y

  override def abs(x: Long) = Math.abs(x)
  override def sgn(x: Long) = if (x == 0l) 0l else if (x > 0l) 1l else -1l
  override def dist(x: Long, y: Long) = Math.abs(x - y)

}
