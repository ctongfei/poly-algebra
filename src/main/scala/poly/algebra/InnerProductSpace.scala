package poly.algebra

import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait InnerProductSpace[V, @sp(fd) F] extends NormedVectorSpace[V, F] {
  def powerOpsOfScalar: PowerOps[F]
  def dot(x: V, y: V): F
  def norm(x: V) = powerOpsOfScalar.sqrt(dot(x, x))
}

object InnerProductSpace {
  def apply[V, @sp(fd) F](implicit S: InnerProductSpace[V, F]) = S
  def create[V, @sp(fd) F: PowerOps : Field](fDot: (V, V) => F)(implicit S: VectorSpace[V, F]): InnerProductSpace[V, F] =
    new InnerProductSpace[V, F] {
      def powerOpsOfScalar = implicitly[PowerOps[F]]
      def fieldOfScalar = implicitly[Field[F]]
      def dot(x: V, y: V): F = fDot(x, y)
      def add(x: V, y: V): V = S.add(x, y)
      override def neg(x: V): V = S.neg(x)
      override def sub(x: V, y: V): V = S.sub(x, y)
      def zero: V = S.zero
      def scale(x: V, k: F): V = S.scale(x, k)
    }
}
