package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait InnerProductSpace[@miniboxed V, @miniboxed F] extends NormedVectorSpace[V, F] {
  def powerOpsOfScalar: PowerOps[F]
  def dot(x: V, y: V): F
  def norm(x: V) = powerOpsOfScalar.sqrt(dot(x, x))
}

object InnerProductSpace {
  def apply[@miniboxed V, @miniboxed F](implicit S: InnerProductSpace[V, F]) = S
  def create[@miniboxed V, @miniboxed F: PowerOps : Field](fDot: (V, V) => F)(implicit S: VectorSpace[V, F]) = new InnerProductSpace[V, F] {
    def powerOpsOfScalar = implicitly[PowerOps[F]]
    def fieldOfScalar = implicitly[Field[F]]
    def dot(x: V, y: V): F = fDot(x, y)
    def add(x: V, y: V): V = S.add(x, y)
    override def neg(x: V): V = S.neg(x)
    override def sub(x: V, y: V): V = S.sub(x, y)
    def zero: V = S.zero
    def scale(k: F, x: V): V = S.scale(k, x)
  }
}