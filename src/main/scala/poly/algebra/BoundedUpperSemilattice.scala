package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an upper semilattice that has a specific top element.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawSupremumAssociativity
 *  - $lawSupremumCommutativity
 *  - $lawSupremumIdempotency
 *  - $lawTop
 * @author Tongfei Chen
 */
trait BoundedUpperSemilattice[@sp(Boolean) X] extends UpperSemilattice[X] with HasTop[X]

object BoundedUpperSemilattice extends ImplicitGetter[BoundedUpperSemilattice]

trait BoundedUpperSemilatticeWithEq[@sp(Boolean) X] extends BoundedUpperSemilattice[X] with EqUpperSemilattice[X] { self =>
  override def reverse: EqBoundedLowerSemilattice[X] = new EqBoundedLowerSemilattice[X] {
    def le(x: X, y: X) = self.le(y, x)
    def inf(x: X, y: X) = self.sup(x, y)
    def bot = self.top
    override def reverse = self
  }
}

object BoundedUpperSemilatticeWithEq extends ImplicitGetter[BoundedUpperSemilatticeWithEq]
