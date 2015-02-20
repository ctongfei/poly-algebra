package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait InnerProductSpace[V, @specialized(Float, Double) F] extends NormedVectorSpace[V, F] {
  def power: Power[F]
  def dot(x: V, y: V): F
  def norm(x: V) = power.sqrt(dot(x, x))
}

object InnerProductSpace {
  def apply[V, F](implicit S: InnerProductSpace[V, F]) = S
  def create[V, F: Power : Field](fDot: (V, V) => F)(implicit S: VectorSpace[V, F]) = new InnerProductSpace[V, F] {
    def power = implicitly[Power[F]]
    def fieldOfScalar = implicitly[Field[F]]
    def dot(x: V, y: V): F = fDot(x, y)
    def neg(x: V): V = S.neg(x)
    def add(x: V, y: V): V = S.add(x, y)
    def zero: V = S.zero
    def scale(k: F, x: V): V = S.scale(k, x)
  }
}