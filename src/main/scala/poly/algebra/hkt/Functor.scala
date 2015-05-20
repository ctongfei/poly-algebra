package poly.algebra.hkt

import scala.language.higherKinds

/**
 * Typeclass for functors.
 * A functor is any type which has the `map` operation with type signature `(X => Y) => M[X] => M[Y]`.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Functor[M[_]] {

  def map[x, y](mx: M[x])(f: x => y): M[y]

}

object Functor {

  def apply[M[_]](implicit M: Functor[M]): Functor[M] = M


}
