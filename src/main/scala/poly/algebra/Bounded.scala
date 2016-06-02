package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Witnesses the existence of both a maximum element and a minimum element of a specific type.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawTop
 *  - $lawBottom
 * @author Tongfei Chen
 * @since 0.2.7
 */
trait Bounded[@sp(fdib) +X] extends HasTop[X] with HasBottom[X]

object Bounded extends ImplicitGetter[Bounded]