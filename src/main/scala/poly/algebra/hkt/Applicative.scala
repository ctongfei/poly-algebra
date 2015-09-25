package poly.algebra.hkt

import poly.algebra._

import scala.language.higherKinds

/**
 * Typeclass for applicatives.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Applicative[M[+_]] extends Functor[M] { self =>

  def id[x](u: x): M[x]

  def liftedApply[x, y](mx: M[x])(mf: M[x => y]): M[y]

  def map[x, y](mx: M[x])(f: x => y): M[y] = liftedApply(mx)(id(f))

  def asHasIdentity[x]: HasIdentity[x => M[x]] = new HasIdentity[x => M[x]] {
    def id = self.id[x]
  }

}

object Applicative {

  def apply[M[+_]](implicit M: Applicative[M]): Applicative[M] = M

}
