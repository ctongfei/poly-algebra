package poly.algebra

import poly.util.typeclass._

/**
 * Represents a commutative semigroup.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAssociativity
 *  - $lawCommutativity
 * @define lawCommutativity '''Commutativity''': ∀''a'', ''b''∈X, ''a'' op ''b'' == ''b'' op ''a''.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.6
 */
trait CSemigroup[X] extends Semigroup[X]

object CSemigroup extends ImplicitGetter[CSemigroup]

