package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object DoubleStructure extends Field[Double] with TotalOrder[Double] with Hash[Double] with PowerOps[Double] with TrigExpOps[Double] with Signed[Double] {
  def eq(x: Double, y: Double) = x == y
  override def ne(x: Double, y: Double) = x != y
  def hash(x: Double) = x.hashCode()

  def cmp(x: Double, y: Double) = (x - y).toInt
  override def le(x: Double, y: Double) = x <= y
  override def lt(x: Double, y: Double) = x < y
  override def ge(x: Double, y: Double) = x >= y
  override def gt(x: Double, y: Double) = x > y
  override def max(x: Double, y: Double) = if (x > y) x else y
  override def min(x: Double, y: Double) = if (x < y) x else y
  def zero = 0.0
  def one = 1.0
  def add(x: Double, y: Double) = x + y
  def neg(x: Double) = -x
  override def sub(x: Double, y: Double) = x - y
  def mul(x: Double, y: Double) = x * y
  def inv(x: Double) = 1.0 / x
  override def div(x: Double, y: Double) = x / y
  override def quot(x: Double, y: Double) = x / y
  override def mod(x: Double, y: Double) = 0.0

  def root(x: Double, n: Int): Double = Math.pow(x, 1.0 / n)
  override def sqrt(x: Double): Double = Math.sqrt(x)
  override def cbrt(x: Double): Double = Math.cbrt(x)
  def pow(x: Double, y: Double): Double = Math.pow(x, y)

  def e: Double = Math.E
  def pi: Double = Math.PI
  def exp(a: Double): Double = Math.exp(a)
  def expm1(a: Double): Double = Math.expm1(a)
  def log(a: Double): Double = Math.log(a)
  def log1p(a: Double): Double = Math.log1p(a)
  def sin(a: Double): Double = Math.sin(a)
  def cos(a: Double): Double = Math.cos(a)
  def tan(a: Double): Double = Math.tan(a)
  def arcsin(a: Double): Double = Math.asin(a)
  def arccos(a: Double): Double = Math.acos(a)
  def arctan(a: Double): Double = Math.atan(a)
  def atan2(y: Double, x: Double): Double = Math.atan2(y, x)
  def sinh(x: Double): Double = Math.sinh(x)
  def cosh(x: Double): Double = Math.cosh(x)
  def tanh(x: Double): Double = Math.tanh(x)

  def abs(x: Double) = Math.abs(x)
  def sgn(x: Double) = Math.signum(x)
}
