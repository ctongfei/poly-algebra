package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a lower semilattice that has a specific bottom element.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawInfimumAssociativity
 *  - $lawInfimumCommutativity
 *  - $lawInfimumIdempotency
 *  - $lawBottom
 * @author Tongfei Chen
 * @since 0.2.0
 */
trait BoundedLowerSemilattice[@sp(Boolean) X] extends LowerSemilattice[X] with HasBottom[X]

object BoundedLowerSemilattice extends ImplicitGetter[BoundedLowerSemilattice]

trait EqBoundedLowerSemilattice[@sp(Boolean) X] extends BoundedLowerSemilattice[X] with EqLowerSemilattice[X] { self =>
  override def reverse: BoundedUpperSemilatticeWithEq[X] = new BoundedUpperSemilatticeWithEq[X] {
    def top = self.bot
    def le(x: X, y: X) = self.le(y, x)
    def sup(x: X, y: X) = self.inf(x, y)
    override def reverse = self
  }
}

object EqBoundedLowerSemilattice extends ImplicitGetter[EqBoundedLowerSemilattice]
