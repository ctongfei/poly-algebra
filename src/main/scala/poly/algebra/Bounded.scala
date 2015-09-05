package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Witnesses the existence of both a maximum element and a minimum element of a specific type.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.7
 */
trait Bounded[@sp(fdib) +X] extends HasTop[X] with HasBottom[X]
object Bounded extends ImplicitGetter[Bounded]

