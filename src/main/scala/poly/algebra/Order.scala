package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.algebra.specgroup._
import scala.annotation.unchecked.{uncheckedVariance => uv}

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
 *
 * @define lawOrderTotality '''Totality''':  ∀''a'', ''b''∈X, ''a'' <= ''b'' or ''b'' <= ''a''.
 * @since 0.1.0
 * @author Tongfei Chen
 */
trait Order[@sp(fdib) -X] extends PartialOrder[X] with Lattice[X @uv] { self =>

  def cmp(x: X, y: X): Int

  def le(x: X, y: X): Boolean = cmp(x, y) <= 0
  override def ge(x: X, y: X): Boolean = cmp(x, y) >= 0
  override def lt(x: X, y: X): Boolean = cmp(x, y) < 0
  override def gt(x: X, y: X): Boolean = cmp(x, y) > 0
  override def eq(x: X, y: X): Boolean = cmp(x, y) == 0
  override def ne(x: X, y: X): Boolean = cmp(x, y) != 0

  def sup(x: X, y: X): X @uv = max(x, y)
  def inf(x: X, y: X): X @uv = min(x, y)

  def max[Y <: X](x: Y, y: Y): Y = if (cmp(x, y) >= 0) x else y
  def min[Y <: X](x: Y, y: Y): Y = if (cmp(x, y) <= 0) x else y

  /** Returns the equivalence relation (tied relation) induced by this weak order. */
  def asEquiv: Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = self.cmp(x, y) == 0
  }

  override def reverse: Order[X] = new Order[X] {
    override def reverse: Order[X] = self
    def cmp(x: X, y: X): Int = -self.cmp(x, y)
  }

  def thenOrderBy[Y <: X](that: Order[Y]): Order[Y] = new Order[Y] {
    def cmp(x: Y, y: Y) = {
      val r = self.cmp(x, y)
      if (r != 0) r else that.cmp(x, y)
    }
  }

  override def contramap[@sp(fdib) Y](f: Y => X): Order[Y] = new Order[Y] {
    def cmp(x: Y, y: Y) = self.cmp(f(x), f(y))
  }

}

object Order extends ImplicitGetter[Order] {

  // CONSTRUCTORS

  def create[@sp(fdib) X](fCmp: (X, X) => Int): Order[X] = new Order[X] {
    def cmp(x: X, y: X) = fCmp(x, y)
  }

  def by[@sp(fdib) X, Y](f: Y => X)(implicit X: Order[X]) = X contramap f

  implicit def fromJavaComparable[X <: java.lang.Comparable[X]]: Order[X] = new Order[X] {
    def cmp(x: X, y: X) = x compareTo y
  }

  // TYPECLASS INSTANCES

  implicit object ContravariantFunctor extends ContravariantFunctor[Order] {
    def contramap[X, Y](wox: Order[X])(f: Y => X) = wox contramap f
  }
}