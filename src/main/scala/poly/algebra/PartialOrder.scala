package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.util.specgroup._

/**
 * Typeclass for partial orders.
 *
 * A partial order is a binary relation `≤` (precedence) on a type `X` which is reflexive, antisymmetric
 * and transitive.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait PartialOrder[@sp(fdib) -X] extends Eq[X] { self =>

  /** Returns whether ''x'' precedes ''y'' under this order. */
  def le(x: X, y: X): Boolean

  /** Returns whether ''x'' succeeds ''y'' under this order. */
  def ge(x: X, y: X): Boolean = le(y, x)

  /** Returns whether ''x'' is equivalent to ''y'' under this order. */
  def eq(x: X, y: X) = le(x, y) && le(y, x)

  /** Returns whether ''x'' strictly precedes ''y'' under this order. */
  def lt(x: X, y: X): Boolean = le(x, y) & !le(y, x)

  /** Returns whether ''x'' strictly succeeds ''y'' under this order. */
  def gt(x: X, y: X): Boolean = le(y, x) & !le(x, y)

  /** Returns the reverse order of this partial order. */
  def reverse: PartialOrder[X] = new PartialOrder[X] {
    override def reverse = self
    def le(x: X, y: X) = self.le(y, x)
  }

}

object PartialOrder extends ImplicitGetter[PartialOrder] {

  def create[@sp(fdib) X](fLe: (X, X) => Boolean): PartialOrder[X] = new PartialOrder[X] {
    def le(x: X, y: X): Boolean = fLe(x, y)
  }

  def by[Y, @sp(fdib) X](f: Y => X)(implicit ev: PartialOrder[X]): PartialOrder[Y] = new PartialOrder[Y] {
    def le(x: Y, y: Y) = ev.le(f(x), f(y))
  }

  implicit object ContravariantFunctor extends ContravariantFunctor[PartialOrder] {
    def contramap[X, Y](pox: PartialOrder[X])(f: Y => X) = PartialOrder.by(f)(pox)
  }
}
