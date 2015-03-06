package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Module[@specialized(Int, Double) V, @specialized(Int, Double) R] extends AdditiveGroup[V] {
  def ringOfScalar: Ring[R]
  def scale(k: R, x: V): V
  def neg(x: V): V = scale(ringOfScalar.negOne, x)
}

object Module {
  def apply[@specialized(Int, Double) V, @specialized(Int, Double) R](implicit M: Module[V, R]) = M

  implicit def default[@specialized(Int, Double) V](implicit R: Ring[V]): Module[V, V] = new Module[V, V] {
    def ringOfScalar = R
    def add(x: V, y: V) = R.add(x, y)
    override def neg(x: V) = R.neg(x)
    override def sub(x: V, y: V) = R.sub(x, y)
    def zero = R.zero
    def scale(x: V, y: V) = R.mul(x, y)
  }
}
