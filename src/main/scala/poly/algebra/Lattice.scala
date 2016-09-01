package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a lattice, a partially-ordered set where each two elements have a
 * unique infimum and a unique supremum.
 *
 * An instance of this typeclass should satisfy the following axioms:
 * <ul>
 *  <li> $lawSupremumAssociativity </li>
 *  <li> $lawInfimumAssociativity </li>
 *  <li> $lawSupremumCommutativity </li>
 *  <li> $lawInfimumCommutativity </li>
 *  <li> $lawAbsorptionSI </li>
 *  <li> $lawAbsorptionIS </li>
 * </ul>
 * @define lawAbsorptionSI '''Absorption of supremum w.r.t. infimum''': ∀''a'', ''b''∈X, sup(''a'', inf(''a'', ''b'')) == ''a''.
 * @define lawAbsorptionIS '''Absorption of infimum w.r.t. supremum''': ∀''a'', ''b''∈X, inf(''a'', sup(''a'', ''b'')) == ''a''.
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait Lattice[@sp(fdib) X] extends UpperSemilattice[X] with LowerSemilattice[X] { self =>

  override def reverse: Lattice[X] = new Lattice[X] {
    override def reverse = self
    def sup(x: X, y: X) = self.inf(x, y)
    def inf(x: X, y: X) = self.sup(x, y)
  }

}

object Lattice extends ImplicitGetter[Lattice] {
  def create[@sp(fdib) X](fSup: (X, X) => X, fInf: (X, X) => X) = new Lattice[X] {
    def sup(x: X, y: X): X = fSup(x, y)
    def inf(x: X, y: X): X = fInf(x, y)
  }
}

trait EqLattice[@sp(Boolean) X] extends Lattice[X] with EqLowerSemilattice[X] with EqUpperSemilattice[X] { self =>

  override def reverse: EqLattice[X] = new EqLattice[X] {
    def le(x: X, y: X) = self.le(y, x)
    def sup(x: X, y: X) = self.inf(x, y)
    def inf(x: X, y: X) = self.sup(x, y)
    override def reverse = self
  }

}

object EqLattice extends ImplicitGetter[EqLattice]

