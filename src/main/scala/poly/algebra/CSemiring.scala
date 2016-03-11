package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a commutative semiring, i.e., a semiring where the multiplication
 * operation is also commutative.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAdditiveAssociativity
 *  - $lawAdditiveIdentity
 *  - $lawAdditiveCommutativity
 *  - $lawMultiplicativeAssociativity
 *  - $lawMultiplicativeIdentity
 *  - $lawMultiplicativeCommutativity
 *  - $lawDistributivityMA
 *  - $lawAnnihilationM
 *
 * @author Tongfei Chen
 * @since 0.2.6
 */
trait CSemiring[@sp(fdi) X] extends Semiring[X] with MultiplicativeCMonoid[X]

object CSemiring extends ImplicitGetter[CSemiring]
