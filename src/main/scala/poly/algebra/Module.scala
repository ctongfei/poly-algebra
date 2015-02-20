package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Module[V, @specialized(Int, Float, Double) R] extends AdditiveGroup[V] {
  def ringOfScalar: Ring[R]
  def scale(k: R, x: V): V
}

object Module {
  def apply[V, R](implicit M: Module[V, R]) = M

  implicit def default[V](implicit R: Ring[V]): Module[V, V] = new Module[V, V] {
    def ringOfScalar = R
    def add(x: V, y: V) = R.add(x, y)
    def mul(x: V, y: V) = R.mul(x, y)
    def neg(x: V) = R.neg(x)
    def zero = R.zero
    def scale(x: V, y: V) = R.mul(x, y)
  }
}
