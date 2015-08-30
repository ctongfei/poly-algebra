package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * A commutative ring (i.e. the multiplication operation is commutative).
 * @since 0.2.6
 */
trait CRing[@sp(fdi) X] extends Ring[X] with CSemiring[X]
object CRing extends ImplicitGetter[CRing]
