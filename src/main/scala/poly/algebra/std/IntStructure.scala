package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen
 */
object IntStructure extends EuclideanDomain[Int] with BoundedLattice[Int] with SequentialOrder[Int] with OrderedHashing[Int] {

  final def hash(x: Int) = x.##

  final val bot: Int = Int.MinValue
  final val top: Int = Int.MaxValue

  def pred(x: Int) = x - 1
  def succ(x: Int) = x + 1
  override def predN(x: Int, n: Int) = x - n
  override def succN(x: Int, n: Int) = x + n

  def cmp(x: Int, y: Int) = x - y
  override def eq(x: Int, y: Int) = x == y
  override def le(x: Int, y: Int) = x <= y
  override def lt(x: Int, y: Int) = x < y
  override def ge(x: Int, y: Int) = x >= y
  override def gt(x: Int, y: Int) = x > y
  override def max[Y <: Int](x: Y, y: Y) = if (x > y) x else y
  override def min[Y <: Int](x: Y, y: Y) = if (x < y) x else y

  final val zero = 0
  final val one = 1
  //TODO: ! `final` causes compiler generating a class that when loaded, throws ClassFormatException (SI-9486)
  override val negOne = -1
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
