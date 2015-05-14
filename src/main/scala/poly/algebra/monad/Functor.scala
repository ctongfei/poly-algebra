package poly.algebra.monad

import scala.language.higherKinds

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Functor[M[X]] {

  def map[X, Y](mx: M[X], f: X => Y): M[Y]

}

object Functor {

  def apply[M[_]](implicit M: Functor[M]): Functor[M] = M

}
