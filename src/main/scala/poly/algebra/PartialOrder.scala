package poly.algebra

import poly.util.specgroup._
import poly.util.typeclass._

/**
 * Typeclass for partial orders.
 *
 * A partial order is a binary relation `≤` (precedence) on a type `X` which is reflexive, antisymmetric
 * and transitive.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait PartialOrder[@sp(fdib) -X] extends Eq[X] { self =>

  /** Returns whether ''x'' precedes ''y'' under this partial order. */
  def le(x: X, y: X): Boolean

  /** Returns whether ''x'' succeeds ''y'' under this partial order. */
  def ge(x: X, y: X): Boolean = le(y, x)

  /** Returns whether ''x'' is equivalent to ''y'' under this partial order. */
  def eq(x: X, y: X) = le(x, y) && le(y, x)

  /** Returns whether ''x'' strictly precedes ''y'' under this partial order. */
  def lt(x: X, y: X): Boolean = le(x, y) & !le(y, x)

  /** Returns whether ''x'' strictly succeeds ''y'' under this partial order. */
  def gt(x: X, y: X): Boolean = le(y, x) & !le(x, y)

  /** Returns the reverse order of this partial order. */
  def reverse: PartialOrder[X] = new PartialOrder[X] {
    override def reverse = self
    def le(x: X, y: X) = self.le(y, x)
  }

}

object PartialOrder {
  def apply[@sp(fdib) X](implicit O: PartialOrder[X]) = O

  def create[@sp(fdib) X](fLe: (X, X) => Boolean) = new PartialOrder[X] {
    def le(x: X, y: X): Boolean = fLe(x, y)
  }

  def by[Y, @sp(fdib) X](f: Y => X)(implicit ev: PartialOrder[X]) = new PartialOrder[Y] {
    def le(x: Y, y: Y) = ev.le(f(x), f(y))
  }
}
