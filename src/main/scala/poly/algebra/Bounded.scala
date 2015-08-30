package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.7
 */
trait Bounded[@sp(fdib) +X] extends HasTop[X] with HasBottom[X]
object Bounded extends ImplicitGetter[Bounded]

