package poly.algebra.hkt

import poly.algebra._

import scala.language.higherKinds

/**
 * Typeclass for applicatives.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Applicative[M[_]] extends Functor[M] { self =>

  def id[x](u: x): M[x]

  def ap[x, y](mx: M[x])(f: M[x => y]): M[y]

  def asHasIdentity[x]: HasIdentity[x => M[x]] = new HasIdentity[x => M[x]] {
    def id = self.id[x]
  }

}

object Applicative {

  def apply[M[_]](implicit M: Applicative[M]): Applicative[M] = M

}