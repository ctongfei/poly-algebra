package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Monad[M[_]] extends Applicative[M] {

  def flatMap[A, B](m: M[A])(f: A => M[B]): M[B]
  override def map[A, B](m: M[A])(f: A => B): M[B] = flatMap(m)(t => apply(f(t)))

}
