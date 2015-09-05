package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents a bounded lattice, i.e., a lattice containing both the top and the bottom elements.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.0
 */
trait BoundedLattice[@sp(Boolean) X] extends
  Lattice[X] with Bounded[X] with BoundedLowerSemilattice[X] with BoundedUpperSemilattice[X]

object BoundedLattice extends ImplicitGetter[BoundedLattice]
