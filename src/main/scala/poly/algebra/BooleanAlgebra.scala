package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an abstract Boolean algebra, which is a complemented distributive lattice.
 *
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait BooleanAlgebra[@sp(Boolean) X] extends BoundedLattice[X] { self =>

  /** Returns the conjunction of the two arguments. */
  def and(x: X, y: X): X
  /** Returns the disjunction of the two arguments. */
  def or(x: X, y: X): X
  /** Returns the complement of the argument. */
  def not(x: X): X

  /** Returns the exclusive disjunction of the two arguments. */
  def xor(x: X, y: X): X = or(and(x, not(y)), and(not(x), y))
  /** Returns the complement of the conjunction of the two elements. */
  def nand(x: X, y: X): X = not(and(x, y))
  /** Returns the complement of the disjunction of the two elements. */
  def nor(x: X, y: X): X = not(or(x, y))

  def sup(x: X, y: X): X = or(x, y)
  def inf(x: X, y: X): X = and(x, y)

  /** Casts this Boolean algebra as a Boolean ring with "`xor`" as "`+`" and "`and`" as "`*`". */
  def asBooleanRing = new BooleanAlgebra.BooleanRing[X](self)

}

object BooleanAlgebra extends ImplicitGetter[BooleanAlgebra] {

  def create[@sp(Boolean) X](fAnd: (X, X) => X, fOr: (X, X) => X, fNot: X => X, fZero: X, fOne: X) = new BooleanAlgebra[X] {
    def and(x: X, y: X) = fAnd(x, y)
    def or(x: X, y: X) = fOr(x, y)
    def not(x: X) = fNot(x)
    val bot = fZero
    val top = fOne
  }

  class BooleanRing[X](booleanAlgebra: BooleanAlgebra[X]) extends Ring[X] {
    def mul(x: X, y: X): X = booleanAlgebra.and(x, y)
    def add(x: X, y: X): X = booleanAlgebra.xor(x, y)
    def neg(x: X): X = x
    def one: X = booleanAlgebra.top
    def zero: X = booleanAlgebra.bot
  }

  implicit val onBoolean = std.BooleanStructure
}
