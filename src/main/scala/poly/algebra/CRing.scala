package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a commutative ring (i.e. the multiplication operation is also commutative).
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAdditiveAssociativity
 *  - $lawAdditiveIdentity
 *  - $lawAdditiveInvertibility
 *  - $lawAdditiveCommutativity
 *  - $lawMultiplicativeAssociativity
 *  - $lawMultiplicativeIdentity
 *  - $lawMultiplicativeCommutativity
 *  - $lawDistributivityMA
 * @since 0.2.6
 */
trait CRing[@sp(fdi) X] extends Ring[X] with CSemiring[X]

object CRing extends ImplicitGetter[CRing]
