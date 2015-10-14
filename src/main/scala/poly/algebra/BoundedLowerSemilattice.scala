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
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.0
 */
trait BoundedLowerSemilattice[@sp(Boolean) X] extends LowerSemilattice[X] with HasBottom[X]

object BoundedLowerSemilattice extends ImplicitGetter[BoundedLowerSemilattice]
