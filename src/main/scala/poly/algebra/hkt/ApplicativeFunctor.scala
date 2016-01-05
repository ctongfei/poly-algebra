package poly.algebra.hkt

import poly.algebra._
import poly.algebra.factory._

import scala.language.higherKinds

/**
 * Typeclass for applicative functors.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait ApplicativeFunctor[A[_]] extends Functor[A] { self =>

  def id[X](u: X): A[X]

  def liftedMap[X, Y](mx: A[X])(mf: A[X => Y]): A[Y]

  def map[X, Y](mx: A[X])(f: X => Y): A[Y] = liftedMap(mx)(id(f))

  def mapPair[X, Y, Z](mx: A[X], my: A[Y])(f: (X, Y) => Z): A[Z] = liftedMap(my)(liftedMap(mx)(map(id(f))(_.curried)))

  def product[X, Y](mx: A[X])(my: A[Y]): A[(X, Y)] = liftedMap(mx)(liftedMap(my)(id(y => x => (x, y))))

  def asIdentity[X]: HasIdentity[X => A[X]] = new HasIdentity[X => A[X]] {
    def id = self.id[X]
  }

}

object ApplicativeFunctor extends ImplicitHktGetter[ApplicativeFunctor]
