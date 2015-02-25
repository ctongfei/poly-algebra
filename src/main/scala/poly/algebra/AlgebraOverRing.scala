package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait AlgebraOverRing[V, @specialized(Int, Double, Float) R] extends Module[V, R] with Ring[V]

object AlgebraOverRing {

  def apply[V, R](implicit A: AlgebraOverRing[V, R]) = A

  implicit def defaultOnRing[X](implicit R: Ring[X]): AlgebraOverRing[X, X] = new AlgebraOverRing[X, X] {
    def ringOfScalar = R
    def zero = R.zero
    def one = R.one
    def add(x: X, y: X) = R.add(x, y)
    override def sub(x: X, y: X) = R.sub(x, y)
    def neg(x: X) = R.neg(x)
    def mul(x: X, y: X) = R.mul(x, y)
    def scale(x: X, y: X) = R.mul(x, y)
  }

}
