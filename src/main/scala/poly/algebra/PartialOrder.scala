package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.algebra.specgroup._

/**
 * Represents a partial order.
 *
 * Partial orders are a generalization of total orders in which some element pairs
 * may not be compared.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawOrderReflexivity
 *  - $lawOrderAntisymmetry
 *  - $lawOrderTransitivity
 *
 * @define lawOrderReflexivity '''Reflexivity''':  ∀''a''∈X, ''a'' <= ''a''.
 * @define lawOrderAntisymmetry '''Antisymmetry''':  ∀''a'', ''b''∈X, ''a'' <= ''b'' and ''b'' <= ''a'' implies ''a'' == ''b''.
 * @define lawOrderTransitivity '''Transitivity''':  ∀''a'', ''b'', ''c''∈X, ''a'' <= ''b'' and ''b'' <= ''c'' implies ''a'' <= ''c''.
 * @author Tongfei Chen
 */
trait PartialOrder[@sp(fdib) -X] extends Equiv[X] { self =>

  /** Returns whether ''x'' precedes ''y'' under this order. */
  def le(x: X, y: X): Boolean

  /** Returns whether ''x'' succeeds ''y'' under this order. */
  def ge(x: X, y: X): Boolean = le(y, x)

  def eq(x: X, y: X): Boolean = le(x, y) && le(y, x)

  /** Returns whether ''x'' strictly precedes ''y'' under this order. */
  def lt(x: X, y: X): Boolean = le(x, y) & !le(y, x)

  /** Returns whether ''x'' strictly succeeds ''y'' under this order. */
  def gt(x: X, y: X): Boolean = le(y, x) & !le(x, y)

  /** Returns the reverse order of this ordering relation. */
  def reverse: PartialOrder[X] = new PartialOrder[X] {
    override def reverse = self
    def le(x: X, y: X) = self.le(y, x)
  }

  override def contramap[@sp(fdib) Y](f: Y => X): PartialOrder[Y] = new PartialOrder[Y] {
    def le(x: Y, y: Y) = self.le(f(x), f(y))
  }

  /** Returns the product order of two partial orders. */
  def product[Y](that: PartialOrder[Y]): PartialOrder[(X, Y)] = new PartialOrder[(X, Y)] {
    def le(x: (X, Y), y: (X, Y)) = self.le(x._1, y._1) && that.le(x._2, y._2)
  }

}

object PartialOrder extends ImplicitGetter[PartialOrder] {

  // CONSTRUCTORS

  implicit def fromScalaPartiallyOrdered[X <: scala.math.PartiallyOrdered[X]]: PartialOrder[X] = new PartialOrder[X] {
    def le(x: X, y: X) = x tryCompareTo y match {
      case Some(r) => r <= 0
      case None => false
    }
  }
  
  def create[@sp(fdib) X](fLe: (X, X) => Boolean): PartialOrder[X] = new PartialOrder[X] {
    def le(x: X, y: X): Boolean = fLe(x, y)
  }

  def by[Y, @sp(fdib) X](f: Y => X)(implicit X: PartialOrder[X]) = X contramap f

  implicit object ContravariantFunctor extends ContravariantFunctor[PartialOrder] {
    def contramap[X, Y](pox: PartialOrder[X])(f: Y => X) = pox contramap f
  }
}
