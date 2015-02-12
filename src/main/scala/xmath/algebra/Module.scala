package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Module[V, R] extends AdditiveGroup[V] {

  def scalar: Ring[R]
  def scale(k: R, x: V): V

}


object Module {
  def apply[V, R](implicit M: Module[V, R]) = M
  //TODO: create
}
