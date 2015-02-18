package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait VectorSpace[V, @specialized(Double, Float) F] extends Module[V, F] {
  def fieldOfScalar: Field[F]
  def ringOfScalar: Ring[F] = fieldOfScalar
}

