package poly.algebra.hkt

import poly.algebra._
import poly.algebra.factory._
import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * Typeclass for monads.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Monad[M[_]] extends Functor[M] with Applicative[M] { self =>

  def id[X](u: X): M[X]

  def flatMap[X, Y](mx: M[X])(f: X => M[Y]): M[Y]

  override def map[X, Y](mx: M[X])(f: X => Y): M[Y] = flatMap(mx)(u => id(f(u)))

  def flatten[Y](mmy: M[M[Y]]): M[Y] = flatMap(mmy)(u => u)

  def liftedApply[X, Y](mx: M[X])(mf: M[X => Y]): M[Y] = flatMap(mf)(f => map(mx)(f))

  def product[X, Y](mx: M[X], my: M[Y]): M[(X, Y)] = flatMap(mx)(x => map(my)(y => (x, y)))

  /** Casts this monad as a monoid on Kleisli functions. */
  def asMonoid[X]: Monoid[X => M[X]] = new Monoid[X => M[X]] {
    def id = self.id[X]
    def op(f: X => M[X], g: X => M[X]) = u => flatMap(flatMap(self.id(u))(f))(g)
  }

}

object Monad extends ImplicitHktGetter[Monad]{

  /** The default monad on functions. */
  implicit def functionMonad[W]: Monad[({type λ[+α] = W => α})#λ] = new Monad[({type λ[+α] = W => α})#λ] {
    def flatMap[X, Y](mx: W => X)(f: X => W => Y): W => Y = w => f(mx(w))(w)
    def id[X](u: X): W => X = _ => u
    override def map[X, Y](mx: W => X)(f: X => Y): W => Y = f compose mx
  }

}
