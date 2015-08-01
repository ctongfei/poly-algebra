package poly.algebra.hkt

import poly.algebra._
import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * Typeclass for monads.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Monad[M[+_]] extends Functor[M] with Applicative[M] { self =>

  def id[X](u: X): M[X]

  def flatMap[X, Y](mx: M[X])(f: X => M[Y]): M[Y]

  override def map[X, Y](mx: M[X])(f: X => Y): M[Y] = flatMap(mx)(u => id(f(u)))

  def flatten[Y](mmy: M[M[Y]]): M[Y] = flatMap(mmy)(u => u)

  def liftedApply[X, Y](mx: M[X])(f: M[X => Y]): M[Y] = flatMap(f)(map(mx = mx))

  def product[X, Y](mx: M[X], my: M[Y]): M[(X, Y)] = flatMap(mx)(x => map(my)(y => (x, y)))

  /** Casts this monad as a monoid. */
  def asMonoid[X]: Monoid[X => M[X]] = new Monoid[X => M[X]] {
    def id = self.id[X]
    def op(f: X => M[X], g: X => M[X]) = u => flatMap(flatMap(self.id(u))(f))(g)
  }

}

object Monad {

  def apply[M[+_]](implicit M: Monad[M]): Monad[M] = M

  /** The default monad on sequences. */
  implicit def seqMonad: Monad[Seq] = new Monad[Seq] {
    def flatMap[X, Y](mx: Seq[X])(f: X => Seq[Y]) = mx.flatMap(f)
    def id[X](u: X) = Seq(u)
    override def map[X, Y](mx: Seq[X])(f: X => Y): Seq[Y] = mx.map(f)
  }

 /** The default monad on `Option`s. */
  implicit def optionMonad: Monad[Option] = new Monad[Option] {
    def flatMap[X, Y](mx: Option[X])(f: X => Option[Y]) = mx.flatMap(f)
    def id[X](u: X) = Some(u)
    override def map[X, Y](mx: Option[X])(f: X => Y): Option[Y] = mx.map(f)
  }

  /** The default monad on functions. */
  implicit def functionMonad[W]: Monad[({type λ[+α] = W => α})#λ] = new Monad[({type λ[+α] = W => α})#λ] {
    def flatMap[X, Y](mx: W => X)(f: X => W => Y): W => Y = w => f(mx(w))(w)
    def id[X](u: X): W => X = _ => u
    override def map[X, Y](mx: W => X)(f: X => Y): W => Y = f compose mx
  }

}
