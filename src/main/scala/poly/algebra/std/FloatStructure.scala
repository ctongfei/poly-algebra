package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object FloatStructure extends Field[RF] with TotalOrder[RF] with Hashing[RF, Int] with PowerOps[RF] with TrigExpOps[RF] with SignOps[RF] {
  def hash(x: RF) = x.hashCode()

  def cmp(x: RF, y: RF) = (x - y).toInt
  override def le(x: RF, y: RF) = x <= y
  override def lt(x: RF, y: RF) = x < y
  override def ge(x: RF, y: RF) = x >= y
  override def gt(x: RF, y: RF) = x > y
  override def max(x: RF, y: RF) = if (x > y) x else y
  override def min(x: RF, y: RF) = if (x < y) x else y
  final val zero = 0.0f
  final val one = 1.0f
  def add(x: RF, y: RF) = x + y
  def neg(x: RF) = -x
  override def sub(x: RF, y: RF) = x - y
  def mul(x: RF, y: RF) = x * y
  def inv(x: RF) = 1.0f / x
  override def div(x: RF, y: RF) = x / y
  override def mod(x: RF, y: RF) = 0.0f

  def root(x: RF, n: Int): RF = Math.pow(x, 1.0f / n).toFloat
  override def sqrt(x: RF): RF = Math.sqrt(x).toFloat
  override def cbrt(x: RF): RF = Math.cbrt(x).toFloat
  def pow(x: RF, y: RF): RF = Math.pow(x, y).toFloat

  final val e: RF = Math.E.toFloat
  final val pi: RF = Math.PI.toFloat
  def exp(a: RF): RF = Math.exp(a).toFloat
  def expm1(a: RF): RF = Math.expm1(a).toFloat
  def log(a: RF): RF = Math.log(a).toFloat
  def log1p(a: RF): RF = Math.log1p(a).toFloat
  def sin(a: RF): RF = Math.sin(a).toFloat
  def cos(a: RF): RF = Math.cos(a).toFloat
  def tan(a: RF): RF = Math.tan(a).toFloat
  def arcsin(a: RF): RF = Math.asin(a).toFloat
  def arccos(a: RF): RF = Math.acos(a).toFloat
  def arctan(a: RF): RF = Math.atan(a).toFloat
  def atan2(y: RF, x: RF): RF = Math.atan2(y, x).toFloat
  def sinh(x: RF): RF = Math.sinh(x).toFloat
  def cosh(x: RF): RF = Math.cosh(x).toFloat
  def tanh(x: RF): RF = Math.tanh(x).toFloat

  def abs(x: RF) = Math.abs(x)
  def sgn(x: RF) = Math.signum(x)
}
