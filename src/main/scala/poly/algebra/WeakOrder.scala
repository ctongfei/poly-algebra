package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.algebra.specgroup._

import scala.annotation.unchecked._

/**
 * Represents a weak order.
 *
 * A weak order is a generalization of a total order in which some elements may be tied.
 * Weak orders appear ubiquitously in ordered data structures and sorting algorithms.
 *
 * An instance of this typeclass should satisfy the following axioms:
 * <ul>
 *  <li> $lawOrderTransitivity </li>
 *  <li> $lawOrderTotality </li>
 * </ul>
 * @define lawOrderTotality '''Totality''':  ∀''a'', ''b''∈X, ''a'' <= ''b'' or ''b'' <= ''a''.
 * @since 0.1.0
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait WeakOrder[@sp(fdib) -X] extends PartialOrder[X] { self =>

  def cmp(x: X, y: X): Int

  def le(x: X, y: X): Boolean = cmp(x, y) <= 0
  override def ge(x: X, y: X): Boolean = cmp(x, y) >= 0
  override def lt(x: X, y: X): Boolean = cmp(x, y) < 0
  override def gt(x: X, y: X): Boolean = cmp(x, y) > 0
  override def eq(x: X, y: X): Boolean = cmp(x, y) == 0
  override def ne(x: X, y: X): Boolean = cmp(x, y) != 0

  def max[Y <: X](x: Y, y: Y): Y = if (cmp(x, y) >= 0) x else y
  def min[Y <: X](x: Y, y: Y): Y = if (cmp(x, y) <= 0) x else y

  /** Returns the equivalence relation (tied relation) induced by this weak order. */
  def asEquiv: Equiv[X] = new Equiv[X] {
    def eq(x: X, y: X) = self.cmp(x, y) == 0
  }

  override def reverse: WeakOrder[X] = new WeakOrder[X] {
    override def reverse: WeakOrder[X] = self
    def cmp(x: X, y: X): Int = -self.cmp(x, y)
  }

  /** If tied after first order, use second order. Experimental.
   * @since 0.2.3
   */
  def thenOrderBy[Y <: X](that: WeakOrder[Y]): WeakOrder[Y] = new WeakOrder[Y] {
    def cmp(x: Y, y: Y) = {
      val r = self.cmp(x, y)
      if (r != 0) r else that.cmp(x, y)
    }
  }

}

object WeakOrder extends ImplicitGetter[WeakOrder] {

  def create[@sp(fdib) X](fCmp: (X, X) => Int): WeakOrder[X] = new WeakOrder[X] {
    def cmp(x: X, y: X) = fCmp(x, y)
  }

  def by[@sp(fdib) X, Y](f: Y => X)(implicit O: WeakOrder[X]): WeakOrder[Y] = new WeakOrder[Y] {
    def cmp(x: Y, y: Y) = O.cmp(f(x), f(y))
  }

  implicit def fromJavaComparable[X <: java.lang.Comparable[X]]: WeakOrder[X] = new WeakOrder[X] {
    def cmp(x: X, y: X) = x compareTo y
  }

  implicit object ContravariantFunctor extends ContravariantFunctor[WeakOrder] {
    def contramap[X, Y](wox: WeakOrder[X])(f: Y => X) = WeakOrder.by(f)(wox)
  }
}
