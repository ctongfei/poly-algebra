package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait NormedVectorSpace[@miniboxed V, @miniboxed F] extends VectorSpace[V, F] with MetricSpace[V, F] {
  def norm(x: V): F
  def dist(x: V, y: V): F = norm(sub(x, y))
  def normalize(x: V): V = scale(fieldOfScalar.inv(norm(x)), x)
}

object NormedVectorSpace {
  def apply[@miniboxed V, @miniboxed F](implicit S: NormedVectorSpace[V, F]) = S
  def create[@miniboxed V, @miniboxed F: Field](fNorm: V => F)(implicit S: VectorSpace[V, F]) = new NormedVectorSpace[V, F] {
    def fieldOfScalar = implicitly[Field[F]]
    def norm(x: V): F = fNorm(x)
    override def neg(x: V): V = S.neg(x)
    override def sub(x: V, y: V): V = S.sub(x, y)
    def add(x: V, y: V): V = S.add(x, y)
    def zero: V = S.zero
    def scale(k: F, x: V): V = S.scale(k, x)
  }
}
