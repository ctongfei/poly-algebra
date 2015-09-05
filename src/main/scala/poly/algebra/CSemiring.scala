package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents a commutative semiring.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.6
 */
trait CSemiring[@sp(fdi) X] extends Semiring[X] with MultiplicativeCMonoid[X]
object CSemiring extends ImplicitGetter[CSemiring]
