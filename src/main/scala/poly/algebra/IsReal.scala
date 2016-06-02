package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Witnesses that a type represents the set of real numbers.
 * @author Tongfei Chen
 * @since 0.2.10
 */
trait IsReal[@sp(fd) X] extends OrderedField[X] with PowerOps[X] with TrigExpOps[X] with MetricSpace[X, X] {

  def fromDouble(x: Double): X
  def fromFloat(x: Float): X = fromDouble(x.toDouble)

}

object IsReal extends ImplicitGetter[IsReal]
