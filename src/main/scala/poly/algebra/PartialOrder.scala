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
 * @since 0.1.0
 */
trait PartialOrder[@sp(fdib) -X] extends Eq[X] { self =>

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
  def reverse: PartialOrder[X] = new PartialOrderT.Reverse(self)

  override def contramap[@sp(Int) Y](f: Y => X): PartialOrder[Y] = new PartialOrderT.Contramapped(self, f)

  /** Returns the product order of two partial orders. */
  def product[Y](that: PartialOrder[Y]): PartialOrder[(X, Y)] = new PartialOrderT.Product(self, that)

}

object PartialOrder extends ImplicitGetter[PartialOrder] {

  // CONSTRUCTORS

  implicit def fromScalaPartiallyOrdered[X <: scala.math.PartiallyOrdered[X]]: PartialOrder[X] = new PartialOrderT.FromScalaPartiallyOrdered[X]
  
  def create[@sp(fdib) X](fLe: (X, X) => Boolean): PartialOrder[X] = new PartialOrder[X] {
    def le(x: X, y: X): Boolean = fLe(x, y)
  }

  def by[Y, @sp(fdib) X](f: Y => X)(implicit X: PartialOrder[X]) = X contramap f

  implicit object ContravariantFunctor extends ContravariantFunctor[PartialOrder] {
    def contramap[X, Y](pox: PartialOrder[X])(f: Y => X) = pox contramap f
  }
}

abstract class AbstractPartialOrder[@sp(fdib) -X] extends PartialOrder[X]

private[poly] object PartialOrderT {

  class Reverse[X](self: PartialOrder[X]) extends AbstractPartialOrder[X] {
    override def reverse = self
    def le(x: X, y: X) = self.le(y, x)
  }

  class FromScalaPartiallyOrdered[X <: scala.math.PartiallyOrdered[X]] extends AbstractPartialOrder[X] {
    def le(x: X, y: X) = x tryCompareTo y match {
      case Some(r) => r <= 0
      case None => false
    }
  }

  class Contramapped[@sp(fdib) X, @sp(Int) Y](self: PartialOrder[X], f: Y ⇒ X) extends PartialOrder[Y] {
    def le(x: Y, y: Y) = self.le(f(x), f(y))
  }

  class Product[X, Y](self: PartialOrder[X], that: PartialOrder[Y]) extends AbstractPartialOrder[(X, Y)] {
    def le(x: (X, Y), y: (X, Y)) = self.le(x._1, y._1) && that.le(x._2, y._2)
  }

}
