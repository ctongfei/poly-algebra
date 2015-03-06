package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Applicative[M[_]] extends Functor[M] {

  def apply[A](x: A): M[A]
  def zip[A, B](a: M[A], b: M[B]): M[(A, B)]

}
