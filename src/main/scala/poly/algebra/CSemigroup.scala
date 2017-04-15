package poly.algebra

import poly.algebra.factory._

/**
 *
 * Represents a commutative semigroup.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAssociativity
 *  - $lawCommutativity
 * @define lawCommutativity '''Commutativity''': \(\forall a, b \in X, ab = ba\).
 * @author Tongfei Chen
 * @since 0.2.6
 */
trait CSemigroup[X] extends Semigroup[X]

object CSemigroup extends ImplicitGetter[CSemigroup]

