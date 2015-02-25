package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait AlgebraOverField[V, @specialized(Double, Float) F] extends VectorSpace[V, F] with AlgebraOverRing[V, F]

object AlgebraOverField {

  def apply[V, F](implicit A: AlgebraOverField[V, F]) = A

  implicit def defaultOnField[X](implicit F: Field[X]): AlgebraOverField[X, X] = new AlgebraOverField[X, X] {
    def fieldOfScalar = F
    def zero = F.zero
    def one = F.one
    def add(x: X, y: X) = F.add(x, y)
    override def sub(x: X, y: X) = F.sub(x, y)
    def neg(x: X) = F.neg(x)
    def inv(x: X) = F.inv(x)
    def mul(x: X, y: X) = F.mul(x, y)
    def scale(x: X, y: X) = F.mul(x, y)
  }

}