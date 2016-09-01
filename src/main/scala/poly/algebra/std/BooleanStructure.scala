package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen
 */
object BooleanStructure extends BooleanAlgebra[Boolean] with OrderedHashing[Boolean] with SequentialOrder[Boolean] with EqBoundedLattice[Boolean] {

  def elements = List(false, true)
  def pred(x: Boolean) = false
  def succ(x: Boolean) = true
  def hash(x: Boolean) = x.##

  override final def cmp(x: Boolean, y: Boolean) = {
    if (x == y) 0
    else if (x) 1
    else -1
  }

  final val top = true
  final val bot = false
  final def not(x: Boolean) = !x
  final def and(x: Boolean, y: Boolean) = x & y
  final def or(x: Boolean, y: Boolean) = x | y
  override def xor(x: Boolean, y: Boolean) = x ^ y

  override final def sup(x: Boolean, y: Boolean) = x | y
  override final def inf(x: Boolean, y: Boolean) = x & y

  override object reverse extends BooleanAlgebra[Boolean] with OrderedHashing[Boolean] with SequentialOrder[Boolean] with EqBoundedLattice[Boolean] {
    def and(x: Boolean, y: Boolean) = x || y
    def or(x: Boolean, y: Boolean) = x && y
    def not(x: Boolean) = !x
    override final def sup(x: Boolean, y: Boolean) = x & y
    override final def inf(x: Boolean, y: Boolean) = x | y
    def pred(x: Boolean) = true
    def succ(x: Boolean) = false
    def hash(x: Boolean) = x.##
    def top = false
    def cmp(x: Boolean, y: Boolean) = -BooleanStructure.cmp(x, y)
    def bot = true

    override def reverse = BooleanStructure
  }
}
