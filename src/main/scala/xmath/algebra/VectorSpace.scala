package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait VectorSpace[V, F] extends Module[V, F] {
  def scalar: Field[F]
}

