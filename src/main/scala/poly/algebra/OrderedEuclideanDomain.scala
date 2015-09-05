package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.5
 */
trait OrderedEuclideanDomain[@sp(fdi) X] extends EuclideanDomain[X] with OrderedRing[X] with TotalOrder[X]

object OrderedEuclideanDomain extends ImplicitGetter[OrderedEuclideanDomain]
