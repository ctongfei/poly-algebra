package poly.algebra.hkt

import poly.algebra._
import poly.algebra.factory._
import scala.language.reflectiveCalls
import scala.language.higherKinds

/**
 * Typeclass for applicative functors.
 * @author Tongfei Chen
 * @since 0.2.20
 */
trait Idiom[I[_]] extends Functor[I] { self =>

  def id[X](u: X): I[X]

  def liftedMap[X, Y](mx: I[X])(mf: I[X => Y]): I[Y]

  def map[X, Y](mx: I[X])(f: X => Y): I[Y] = liftedMap(mx)(id(f))

  def productMap[X, Y, Z](mx: I[X], my: I[Y])(f: (X, Y) => Z): I[Z] = liftedMap(my)(liftedMap(mx)(map(id(f))(_.curried)))

  def product[X, Y](mx: I[X])(my: I[Y]): I[(X, Y)] = liftedMap(mx)(liftedMap(my)(id(y => x => (x, y))))

  def asIdentity[X]: HasIdentity[X => I[X]] = new HasIdentity[X => I[X]] {
    def id = self.id[X]
  }

}

object Idiom extends ImplicitHktGetter[Idiom] {

  def product[A[_], B[_]](implicit A: Idiom[A], B: Idiom[B]): Idiom[({type λ[α] = (A[α], B[α])})#λ] =
    new Idiom[({type λ[α] = (A[α], B[α])})#λ] {
      def liftedMap[X, Y](mx: (A[X], B[X]))(mf: (A[X => Y], B[X => Y])) =
        (A.liftedMap(mx._1)(mf._1), B.liftedMap(mx._2)(mf._2))
      def id[X](u: X) = (A.id(u), B.id(u))
    }

  def compose[A[_], B[_]](implicit A: Idiom[A], B: Idiom[B]): Idiom[(({type λ[α] = A[B[α]]})#λ)] =
    new Idiom[({type λ[α] = A[B[α]]})#λ] {
      def liftedMap[X, Y](mx: A[B[X]])(mf: A[B[X => Y]]) =
        A.productMap(mf, mx)((x, y) => B.liftedMap(y)(x))
      def id[X](u: X) = ???
    }

}
