package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait NormedVectorSpace[V, @sp(fd) F] extends VectorSpace[V, F] with MetricSpace[V, F] {
  def norm(x: V): F
  def dist(x: V, y: V): F = norm(sub(x, y))
  def normalize(x: V): V = scale(x, fieldOfScalar.inv(norm(x)))
}

object NormedVectorSpace extends BinaryImplicitGetter[NormedVectorSpace] {

  def create[V, @sp(fd) F: Field](fNorm: V => F)(implicit S: VectorSpace[V, F]): NormedVectorSpace[V, F]
  = new NormedVectorSpace[V, F] {
    def fieldOfScalar = implicitly[Field[F]]
    def norm(x: V): F = fNorm(x)
    override def neg(x: V): V = S.neg(x)
    override def sub(x: V, y: V): V = S.sub(x, y)
    def add(x: V, y: V): V = S.add(x, y)
    def zero: V = S.zero
    def scale(x: V, k: F): V = S.scale(x, k)
  }
}
