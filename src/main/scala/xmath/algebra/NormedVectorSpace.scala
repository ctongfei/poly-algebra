package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait NormedVectorSpace[V, @specialized(Double, Float) F] extends VectorSpace[V, F] with MetricSpace[V, F] {
  def norm(x: V): F
  def dist(x: V, y: V): F = norm(sub(x, y))
  def normalize(x: V): V = scale(scalar.inv(norm(x)), x)
}

object NormedVectorSpace {
  def apply[V, F](implicit S: NormedVectorSpace[V, F]) = S
  def create[V, F: Field](fNorm: V => F)(implicit S: VectorSpace[V, F]) = new NormedVectorSpace[V, F] {
    def scalar = implicitly[Field[F]]
    def norm(x: V): F = fNorm(x)
    def neg(x: V): V = S.neg(x)
    def add(x: V, y: V): V = S.add(x, y)
    def zero: V = S.zero
    def scale(k: F, x: V): V = S.scale(k, x)
  }
}