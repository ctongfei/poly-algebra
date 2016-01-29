package poly.algebra.hkt

import poly.algebra._
import poly.algebra.factory._
import scala.language.reflectiveCalls
import scala.language.higherKinds

/**
 * Typeclass for applicative functors.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.20
 */
trait Idiom[A[_]] extends Functor[A] { self =>

  def id[X](u: X): A[X]

  def liftedMap[X, Y](mx: A[X])(mf: A[X => Y]): A[Y]

  def map[X, Y](mx: A[X])(f: X => Y): A[Y] = liftedMap(mx)(id(f))

  def mapPair[X, Y, Z](mx: A[X], my: A[Y])(f: (X, Y) => Z): A[Z] = liftedMap(my)(liftedMap(mx)(map(id(f))(_.curried)))

  def product[X, Y](mx: A[X])(my: A[Y]): A[(X, Y)] = liftedMap(mx)(liftedMap(my)(id(y => x => (x, y))))

  def asIdentity[X]: HasIdentity[X => A[X]] = new HasIdentity[X => A[X]] {
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
        A.mapPair(mf, mx)((x, y) => B.liftedMap(y)(x))
      def id[X](u: X) = ???
    }

}
