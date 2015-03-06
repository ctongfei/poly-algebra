package poly.algebra

import scala.collection._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Monad[M[_]] extends Applicative[M] {

  def flatMap[A, B](m: M[A])(f: A => M[B]): M[B]
  override def map[A, B](m: M[A])(f: A => B): M[B] = flatMap(m)(t => apply(f(t)))
  override def zip[A, B](a: M[A], b: M[B]): M[(A, B)] = flatMap(a)(t => map(b)(u => (t, u)))

}

object Monad {

  def apply[M[_]](implicit monad: Monad[M]): Monad[M] = monad

  implicit val option: Monad[Option] = new Monad[Option] {
    def apply[A](x: A) = Some(x)
    def flatMap[A, B](a: Option[A])(f: A => Option[B]) = a.flatMap(f)
  }

  implicit val seq: Monad[Seq] = new Monad[Seq] {
    def apply[A](x: A) = Seq(x)
    def flatMap[A, B](a: Seq[A])(f: A => Seq[B]) = a.flatMap(f)
  }

}
