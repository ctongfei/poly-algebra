package poly.algebra.monad

import poly.algebra._

import scala.language.higherKinds

/**
 * Typeclass for applicatives.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Applicative[M[_]] { self =>

  def id[X](u: => X): M[X]

  def asHasIdentity[X]: HasIdentity[X => M[X]] = new HasIdentity[(X) => M[X]] {
    def id = self.id[X]
  }

}
