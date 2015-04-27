package poly.algebra

/**
 * Typeclass for partial orders.
 *
 * A partial order is a binary relation `≤` (precedence) on a type `X` which is reflexive, antisymmetric
 * and transitive.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait PartialOrder[@specialized(Int, Double) X] extends Eq[X] { self =>

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

  def pmax(x: X, y: X): Option[X] = {
    if (le(x, y)) Some(y)
    else if (le(y, x)) Some(x)
    else None
  }

  def pmin(x: X, y: X): Option[X] = {
    if (le(x, y)) Some(x)
    else if (le(y, x)) Some(y)
    else None
  }

  /** Returns the reverse order of this partial order. */
  def reverse: PartialOrder[X] = new PartialOrder[X] {
    override def reverse = self
    def le(x: X, y: X) = self.le(y, x)
  }

}

object PartialOrder {
  def apply[@specialized(Int, Double) X](implicit O: PartialOrder[X]) = O

  def create[@specialized(Int, Double) X](fLe: (X, X) => Boolean) = new PartialOrder[X] {
    def le(x: X, y: X): Boolean = fLe(x, y)
  }

  def on[@specialized(Int, Double) X, Y](f: Y => X)(implicit ev: PartialOrder[X]) = new PartialOrder[Y] {
    def le(x: Y, y: Y) = ev.le(f(x), f(y))
  }
}
