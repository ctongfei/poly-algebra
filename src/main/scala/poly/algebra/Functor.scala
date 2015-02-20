package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Functor[M[_]] {

  def map[A, B](m: M[A])(f: A => B): M[B]

}

object Functor {
  def apply[M[_]](implicit F: Functor[M]) = F
}
