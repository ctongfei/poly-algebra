package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object DoubleStructure extends Field[RR] with TotalOrder[RR] with PowerOps[RR] with TrigExpOps[RR] with SignOps[RR] {

  final def cmp(x: RR, y: RR) = (x - y).toInt
  final override def le(x: RR, y: RR) = x <= y
  final override def lt(x: RR, y: RR) = x < y
  final override def ge(x: RR, y: RR) = x >= y
  final override def gt(x: RR, y: RR) = x > y
  final override def max[Y <: RR](x: Y, y: Y) = if (x > y) x else y
  final override def min[Y <: RR](x: Y, y: Y) = if (x < y) x else y
  final val zero = 0.0
  final val one = 1.0
  final def add(x: RR, y: RR) = x + y
  final def neg(x: RR) = -x
  final override def sub(x: RR, y: RR) = x - y
  final def mul(x: RR, y: RR) = x * y
  final def inv(x: RR) = 1.0 / x
  final override def div(x: RR, y: RR) = x / y
  final override def mod(x: RR, y: RR) = 0.0

  final def root(x: RR, n: Int): RR = Math.pow(x, 1.0 / n)
  final override def sqrt(x: RR): RR = Math.sqrt(x)
  final override def cbrt(x: RR): RR = Math.cbrt(x)
  final def pow(x: RR, y: RR): RR = Math.pow(x, y)

  final val e: RR = Math.E
  final val pi: RR = Math.PI
  final def exp(a: RR): RR = Math.exp(a)
  final def expm1(a: RR): RR = Math.expm1(a)
  final def log(a: RR): RR = Math.log(a)
  final def log1p(a: RR): RR = Math.log1p(a)
  final def sin(a: RR): RR = Math.sin(a)
  final def cos(a: RR): RR = Math.cos(a)
  final def tan(a: RR): RR = Math.tan(a)
  final def arcsin(a: RR): RR = Math.asin(a)
  final def arccos(a: RR): RR = Math.acos(a)
  final def arctan(a: RR): RR = Math.atan(a)
  final def atan2(y: RR, x: RR): RR = Math.atan2(y, x)
  final def sinh(x: RR): RR = Math.sinh(x)
  final def cosh(x: RR): RR = Math.cosh(x)
  final def tanh(x: RR): RR = Math.tanh(x)

  final def abs(x: RR) = Math.abs(x)
  final def sgn(x: RR) = Math.signum(x)
}
