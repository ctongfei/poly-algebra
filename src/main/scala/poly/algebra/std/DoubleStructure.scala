package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object DoubleStructure extends IsReal[Double] with BoundedLattice[Double] {

  final def fromDouble(x: Double) = x

  final val bot: Double = Double.NegativeInfinity
  final val top: Double = Double.PositiveInfinity

  final def cmp(x: Double, y: Double) = (x - y).toInt
  final override def le(x: Double, y: Double) = x <= y
  final override def lt(x: Double, y: Double) = x < y
  final override def ge(x: Double, y: Double) = x >= y
  final override def gt(x: Double, y: Double) = x > y
  final override def max[Y <: Double](x: Y, y: Y) = if (x > y) x else y
  final override def min[Y <: Double](x: Y, y: Y) = if (x < y) x else y
  final val zero = 0.0
  final val one = 1.0
  final def add(x: Double, y: Double) = x + y
  final def neg(x: Double) = -x
  final override def sub(x: Double, y: Double) = x - y
  final def mul(x: Double, y: Double) = x * y
  final def inv(x: Double) = 1.0 / x
  final override def div(x: Double, y: Double) = x / y

  final def root(x: Double, n: Int): Double = Math.pow(x, 1.0 / n)
  final override def sqrt(x: Double): Double = Math.sqrt(x)
  final override def cbrt(x: Double): Double = Math.cbrt(x)
  final def pow(x: Double, y: Double): Double = Math.pow(x, y)

  final val e: Double = Math.E
  final val pi: Double = Math.PI
  final def exp(a: Double): Double = Math.exp(a)
  final def expm1(a: Double): Double = Math.expm1(a)
  final def log(a: Double): Double = Math.log(a)
  final def log1p(a: Double): Double = Math.log1p(a)
  final def sin(a: Double): Double = Math.sin(a)
  final def cos(a: Double): Double = Math.cos(a)
  final def tan(a: Double): Double = Math.tan(a)
  final def arcsin(a: Double): Double = Math.asin(a)
  final def arccos(a: Double): Double = Math.acos(a)
  final def arctan(a: Double): Double = Math.atan(a)
  final def atan2(y: Double, x: Double): Double = Math.atan2(y, x)
  final def sinh(x: Double): Double = Math.sinh(x)
  final def cosh(x: Double): Double = Math.cosh(x)
  final def tanh(x: Double): Double = Math.tanh(x)

  override final def abs(x: Double) = Math.abs(x)
  override final def sgn(x: Double) = Math.signum(x)
  override final def dist(x: Double, y: Double) = Math.abs(x - y)


}
