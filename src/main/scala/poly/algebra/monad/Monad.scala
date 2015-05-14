package poly.algebra.monad

import poly.algebra._
import scala.language.higherKinds

/**
 * Typeclass for monads.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Monad[M[_]] extends Functor[M] with Applicative[M] { self =>

  def flatMap[X, Y](mx: M[X], f: X => M[Y]): M[Y]

  def map[X, Y](mx: M[X], f: X => Y): M[Y] = flatMap(mx, u => id(f(u)))

  def flatten[Y](my: M[M[Y]]): M[Y] = flatMap(my, id)

  def asMonoid[X]: Monoid[X => M[X]] = new Monoid[X => M[X]] {

    def id = self.id[X]

    def op(f: X => M[X], g: X => M[X]) = u => flatMap(flatMap(self.id(u), f), g)

  }
}

object Monad {

  def apply[M[_]](implicit M: Monad[M]): Monad[M] = M

  implicit def seqMonad: Monad[Seq] = new Monad[Seq] {
    def flatMap[X, Y](mx: Seq[X], f: X => Seq[Y]) = mx.flatMap(f)
    def id[X](u: => X) = Seq(u)
    override def map[X, Y](mx: Seq[X], f: X => Y) = mx.map(f)
  }

  implicit def optionMonad: Monad[Option] = new Monad[Option] {
    def flatMap[X, Y](mx: Option[X], f: X => Option[Y]) = mx.flatMap(f)
    def id[X](u: => X) = Some(u)
    override def map[X, Y](mx: Seq[X], f: X => Y) = mx.map(f)
  }

}
