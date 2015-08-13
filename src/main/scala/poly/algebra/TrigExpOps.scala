package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait TrigExpOps[@sp(fd) X] {
  def e: X
  def pi: X
  def exp(a: X): X
  def expm1(a: X): X
  def log(a: X): X
  def log1p(a: X): X
  def sin(a: X): X
  def cos(a: X): X
  def tan(a: X): X
  def arcsin(a: X): X
  def arccos(a: X): X
  def arctan(a: X): X
  def atan2(y: X, x: X): X
  def sinh(x: X): X
  def cosh(x: X): X
  def tanh(x: X): X
}

object TrigExpOps extends ImplicitGetter[TrigExpOps]
