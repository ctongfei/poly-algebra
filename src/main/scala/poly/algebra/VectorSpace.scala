package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait VectorSpace[V, @specialized(Int, Double) F] extends Module[V, F] {
  def fieldOfScalar: Field[F]
  def ringOfScalar: Ring[F] = fieldOfScalar
}

object VectorSpace {

  def apply[V, @specialized(Int, Double) F](implicit V: VectorSpace[V, F]) = V

  /**
   * Constructs the trivial vector space of any field over itself.
   * @param F The field
   * @tparam V Type of values of the field
   * @return The trivial vector space of a field over itself.
   */
  implicit def trivial[V](implicit F: Field[V]): VectorSpace[V, V] = new VectorSpace[V, V] {
    def fieldOfScalar = F
    def add(x: V, y: V) = F.add(x, y)
    override def neg(x: V) = F.neg(x)
    override def sub(x: V, y: V) = F.sub(x, y)
    def zero = F.zero
    def scale(x: V, y: V) = F.mul(x, y)
  }

  def create[V, @specialized(Int, Double) F](fAdd: (V, V) => V, fScale: (F, V) => V, fZero: V)(implicit F: Field[F]): VectorSpace[V, F] = new VectorSpace[V, F] {
    def fieldOfScalar = F
    def add(x: V, y: V) = fAdd(x, y)
    def scale(k: F, x: V) = fScale(k, x)
    def zero = fZero
  }

}
