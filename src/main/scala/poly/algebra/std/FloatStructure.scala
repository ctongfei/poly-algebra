package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen
 */
object FloatStructure extends IsReal[Float] with BoundedLattice[Float] {

  final def fromDouble(x: Double) = x.toFloat

  final val bot: Float = Float.NegativeInfinity
  final val top: Float = Float.PositiveInfinity

  def cmp(x: Float, y: Float) = (x - y).toInt
  override def le(x: Float, y: Float) = x <= y
  override def lt(x: Float, y: Float) = x < y
  override def ge(x: Float, y: Float) = x >= y
  override def gt(x: Float, y: Float) = x > y
  override def max[Y <: Float](x: Y, y: Y) = if (x > y) x else y
  override def min[Y <: Float](x: Y, y: Y) = if (x < y) x else y
  final val zero = 0.0f
  final val one = 1.0f
  def add(x: Float, y: Float) = x + y
  def neg(x: Float) = -x
  override def sub(x: Float, y: Float) = x - y
  def mul(x: Float, y: Float) = x * y
  def inv(x: Float) = 1.0f / x
  override def div(x: Float, y: Float) = x / y

  def root(x: Float, n: Int): Float = Math.pow(x, 1.0f / n).toFloat
  override def sqrt(x: Float): Float = Math.sqrt(x).toFloat
  override def cbrt(x: Float): Float = Math.cbrt(x).toFloat
  def pow(x: Float, y: Float): Float = Math.pow(x, y).toFloat

  final val e: Float = Math.E.toFloat
  final val pi: Float = Math.PI.toFloat
  def exp(a: Float): Float = Math.exp(a).toFloat
  def expm1(a: Float): Float = Math.expm1(a).toFloat
  def log(a: Float): Float = Math.log(a).toFloat
  def log1p(a: Float): Float = Math.log1p(a).toFloat
  def sin(a: Float): Float = Math.sin(a).toFloat
  def cos(a: Float): Float = Math.cos(a).toFloat
  def tan(a: Float): Float = Math.tan(a).toFloat
  def arcsin(a: Float): Float = Math.asin(a).toFloat
  def arccos(a: Float): Float = Math.acos(a).toFloat
  def arctan(a: Float): Float = Math.atan(a).toFloat
  def atan2(y: Float, x: Float): Float = Math.atan2(y, x).toFloat
  def sinh(x: Float): Float = Math.sinh(x).toFloat
  def cosh(x: Float): Float = Math.cosh(x).toFloat
  def tanh(x: Float): Float = Math.tanh(x).toFloat

  override def abs(x: Float) = Math.abs(x)
  override def sgn(x: Float) = Math.signum(x)
  override def dist(x: Float, y: Float) = Math.abs(x - y)

}
