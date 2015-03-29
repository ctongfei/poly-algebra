package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object DoubleStructure extends Field[RR] with TotalOrder[RR] with Hash[RR] with PowerOps[RR] with TrigExpOps[RR] with SignOps[RR] {
  def eq(x: RR, y: RR) = x == y
  override def ne(x: RR, y: RR) = x != y
  def hash(x: RR) = x.hashCode()

  def cmp(x: RR, y: RR) = (x - y).toInt
  override def le(x: RR, y: RR) = x <= y
  override def lt(x: RR, y: RR) = x < y
  override def ge(x: RR, y: RR) = x >= y
  override def gt(x: RR, y: RR) = x > y
  override def max(x: RR, y: RR) = if (x > y) x else y
  override def min(x: RR, y: RR) = if (x < y) x else y
  def zero = 0.0
  def one = 1.0
  def add(x: RR, y: RR) = x + y
  def neg(x: RR) = -x
  override def sub(x: RR, y: RR) = x - y
  def mul(x: RR, y: RR) = x * y
  def inv(x: RR) = 1.0 / x
  override def div(x: RR, y: RR) = x / y
  override def quot(x: RR, y: RR) = x / y
  override def mod(x: RR, y: RR) = 0.0

  def root(x: RR, n: Int): RR = Math.pow(x, 1.0 / n)
  override def sqrt(x: RR): RR = Math.sqrt(x)
  override def cbrt(x: RR): RR = Math.cbrt(x)
  def pow(x: RR, y: RR): RR = Math.pow(x, y)

  def e: RR = Math.E
  def pi: RR = Math.PI
  def exp(a: RR): RR = Math.exp(a)
  def expm1(a: RR): RR = Math.expm1(a)
  def log(a: RR): RR = Math.log(a)
  def log1p(a: RR): RR = Math.log1p(a)
  def sin(a: RR): RR = Math.sin(a)
  def cos(a: RR): RR = Math.cos(a)
  def tan(a: RR): RR = Math.tan(a)
  def arcsin(a: RR): RR = Math.asin(a)
  def arccos(a: RR): RR = Math.acos(a)
  def arctan(a: RR): RR = Math.atan(a)
  def atan2(y: RR, x: RR): RR = Math.atan2(y, x)
  def sinh(x: RR): RR = Math.sinh(x)
  def cosh(x: RR): RR = Math.cosh(x)
  def tanh(x: RR): RR = Math.tanh(x)

  def abs(x: RR) = Math.abs(x)
  def sgn(x: RR) = Math.signum(x)
}
