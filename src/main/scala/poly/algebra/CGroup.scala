package poly.algebra

import poly.algebra.factory._

/**
 * Represents an Abelian (commutative) group.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawAssociativity
 *  - $lawIdentity
 *  - $lawInvertibility
 *  - $lawCommutativity
 * @author Tongfei Chen
 * @since 0.2.6
 */
trait CGroup[X] extends CMonoid[X] with Group[X]
object CGroup extends ImplicitGetter[CGroup]
