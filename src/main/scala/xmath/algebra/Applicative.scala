package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Applicative[M[_]] extends Functor[M] {

  def apply[A](x: A): M[A]

}
