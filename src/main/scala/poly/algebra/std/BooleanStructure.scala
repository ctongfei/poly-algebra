package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object BooleanStructure extends BooleanAlgebra[Boolean] with TotalOrder[Boolean] with Hash[Boolean] {
  def eq(x: Boolean, y: Boolean) = x == y
  override def ne(x: Boolean, y: Boolean) = x != y
  def hash(x: Boolean) = x.hashCode()

  def lt(x: Boolean, y: Boolean) = (!x) & y

  def one = true
  def zero = false
  def not(x: Boolean) = !x
  def and(x: Boolean, y: Boolean) = x & y
  def or(x: Boolean, y: Boolean) = x | y
}
