package poly.algebra.hkt

import poly.algebra._
import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * Typeclass for monads.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Monad[M[_]] extends Functor[M] with Applicative[M] { self =>

  def id[x](u: x): M[x]

  def flatMap[x, y](mx: M[x])(f: x => M[y]): M[y]

  def map[x, y](mx: M[x])(f: x => y): M[y] = flatMap(mx)(u => id(f(u)))

  def flatten[y](mmy: M[M[y]]): M[y] = flatMap(mmy)(u => u)

  def ap[x, y](mx: M[x])(f: M[x => y]): M[y] = flatMap(f)(map(mx))

  def zip[x, y](mx: M[x], my: M[y]): M[(x, y)] = flatMap(mx)(x => map(my)(y => (x, y)))

  /** Casts this monad as a monoid. */
  def asMonoid[x]: Monoid[x => M[x]] = new Monoid[x => M[x]] {
    def id = self.id[x]
    def op(f: x => M[x], g: x => M[x]) = u => flatMap(flatMap(self.id(u))(f))(g)
  }

}

object Monad {

  def apply[M[_]](implicit M: Monad[M]): Monad[M] = M

  /** The default monad on sequences. */
  implicit def seqMonad: Monad[Seq] = new Monad[Seq] {
    def flatMap[X, Y](mx: Seq[X])(f: X => Seq[Y]) = mx.flatMap(f)
    def id[X](u: X) = Seq(u)
    override def map[X, Y](mx: Seq[X])(f: X => Y) = mx.map(f)
  }

 /** The default monad on `Option`s. */
  implicit def optionMonad: Monad[Option] = new Monad[Option] {
    def flatMap[X, Y](mx: Option[X])(f: X => Option[Y]) = mx.flatMap(f)
    def id[X](u: X) = Some(u)
    override def map[X, Y](mx: Option[X])(f: X => Y) = mx.map(f)
  }

  /** The default monad on functions. */
  implicit def functionMonad[z]: Monad[({type λ[α] = z => α})#λ] = new Monad[({type λ[α] = z => α})#λ] {
    def flatMap[x, y](mx: z => x)(f: x => z => y): z => y = w => f(mx(w))(w)
    def id[x](u: x): z => x = _ => u
    override def map[x, y](mx: z => x)(f: x => y) = f compose mx
  }

}
