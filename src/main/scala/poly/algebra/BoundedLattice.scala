package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a bounded lattice, i.e., a lattice containing both the top and the bottom elements.
 *
 * An instance of this typeclass should satisfy the following axioms:
 * <ul>
 *  <li> $lawSupremumAssociativity </li>
 *  <li> $lawInfimumAssociativity </li>
 *  <li> $lawSupremumCommutativity </li>
 *  <li> $lawInfimumCommutativity </li>
 *  <li> $lawAbsorptionSI </li>
 *  <li> $lawAbsorptionIS </li>
 *  <li> $lawTop </li>
 *  <li> $lawBottom </li>
 * </ul>
 * @author Tongfei Chen
 * @since 0.2.0
 */
trait BoundedLattice[@sp(Boolean) X] extends
  Lattice[X] with Bounded[X] with BoundedLowerSemilattice[X] with BoundedUpperSemilattice[X] { self =>

  def asMonoidWithInf: CMonoid[X] = new CMonoid[X] {
    def id: X = self.top
    def op(x: X, y: X): X = self.inf(x, y)
  }

  def asMonoidWithSup: CMonoid[X] = new CMonoid[X] {
    def id: X = self.bot
    def op(x: X, y: X): X = self.sup(x, y)
  }

}

object BoundedLattice extends ImplicitGetter[BoundedLattice]

trait EqBoundedLattice[@sp(Boolean) X] extends EqBoundedLowerSemilattice[X] with BoundedUpperSemilatticeWithEq[X] with BoundedLattice[X] { self =>
  override def reverse: EqBoundedLattice[X] = new EqBoundedLattice[X] {
    def sup(x: X, y: X) = self.inf(x, y)
    def le(x: X, y: X) = self.le(y, x)
    def top = self.bot
    def bot = self.top
    def inf(x: X, y: X) = self.sup(x, y)
    override def reverse = self
  }
}

object EqBoundedLattice extends ImplicitGetter[EqBoundedLattice]
