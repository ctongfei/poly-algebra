package poly.algebra.hkt

import poly.algebra._
import poly.algebra.factory._
import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * Typeclass for monads.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.1
 */
trait Monad[M[_]] extends ApplicativeFunctor[M] { self =>

  def id[X](u: X): M[X]

  def flatMap[X, Y](mx: M[X])(f: X => M[Y]): M[Y]

  override def map[X, Y](mx: M[X])(f: X => Y): M[Y] = flatMap(mx)(u => id(f(u)))

  def flatten[Y](mmy: M[M[Y]]): M[Y] = flatMap(mmy)(u => u)

  def liftedMap[X, Y](mx: M[X])(mf: M[X => Y]): M[Y] = flatMap(mf)(f => map(mx)(f))

  override def product[X, Y](mx: M[X])(my: M[Y]): M[(X, Y)] = flatMap(mx)(x => map(my)(y => (x, y)))

  /**
   * Returns the Kleisli category naturally associated with this monad, i.e.
   * a category on the type `A => M[B]`.
   * @return The natural Kleisli category
   */
  def Kleisli: Arrow[({type λ[α, β] = α => M[β]})#λ] = new Arrow[({type λ[α, β] = α => M[β]})#λ] {
    def compose[X, Y, Z](g: Y => M[Z], f: X => M[Y]) = x => flatMap(f(x))(g)
    def id[X] = self.id[X]
    def lift[X, Y](f: X => Y) = x => id(f(x))
    def apply1[X, Y, Z](f: X => M[Y]) = xz => self.map(f(xz._1))(y => (y, xz._2))
    def apply2[X, Y, Z](f: X => M[Y]) = xz => self.map(f(xz._2))(y => (xz._1, y))
  }
}

object Monad extends ImplicitHktGetter[Monad] {

  /** The default monad on functions. */
  implicit def functionMonad[W]: Monad[({type λ[+α] = W => α})#λ] = new Monad[({type λ[+α] = W => α})#λ] {
    def flatMap[X, Y](mx: W => X)(f: X => W => Y): W => Y = w => f(mx(w))(w)
    def id[X](u: X): W => X = _ => u
    override def map[X, Y](mx: W => X)(f: X => Y): W => Y = f compose mx
  }


}
