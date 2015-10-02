package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
trait IsReal[X] extends OrderedField[X] with PowerOps[X] with TrigExpOps[X] with CompleteMetricSpace[X, X] {

  def fromDouble(x: Double): X

}
