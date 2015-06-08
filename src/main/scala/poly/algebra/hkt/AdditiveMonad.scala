package poly.algebra.hkt

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait AdditiveMonad[M[_]] extends Monad[M] { self =>

  def zero[X]: M[X]
  def add[X](a: M[X], b: M[X]): M[X]

  implicit def monoid[X]: Monoid[M[X]] = new Monoid[M[X]] {
    def id = self.zero[X]
    def op(a: M[X], b: M[X]) = self.add(a, b)
  }

}
