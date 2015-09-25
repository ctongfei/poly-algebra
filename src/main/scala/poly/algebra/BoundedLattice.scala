package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a bounded lattice, i.e., a lattice containing both the top and the bottom elements.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawSupremumAssociativity
 *  - $lawInfimumAssociativity
 *  - $lawSupremumCommutativity
 *  - $lawInfimumCommutativity
 *  - $lawAbsorptionSI
 *  - $lawAbsorptionIS
 *  - $lawTop
 *  - $lawBottom
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.0
 */
trait BoundedLattice[@sp(Boolean) X] extends
  Lattice[X] with Bounded[X] with BoundedLowerSemilattice[X] with BoundedUpperSemilattice[X]

object BoundedLattice extends ImplicitGetter[BoundedLattice]
