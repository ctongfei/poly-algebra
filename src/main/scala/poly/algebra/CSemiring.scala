package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object CSemiring extends ImplicitGetter[CSemiring]

trait CSemiring[@sp(fdi) X] extends Semiring[X] with MultiplicativeCMonoid[X]
