package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait InnerProductSpace[V, F] extends NormedVectorSpace[V, F] {
  def power: Power[F]
  def dot(x: V, y: V): F
  def norm(x: V) = power.sqrt(dot(x, x))
}
