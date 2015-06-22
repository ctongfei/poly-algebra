package poly.algebra.hkt

import scala.language.higherKinds

/**
 * Typeclass for functors.
 * A functor is any type which has the `map` operation with type signature `(X => Y) => M[X] => M[Y]`.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.0
 */
trait Functor[M[_]] {

  def map[X, Y](mx: M[X])(f: X => Y): M[Y]

  def lift[X, Y](f: X => Y): M[X] => M[Y] = mx => map(mx)(f)

}

object Functor {

  def apply[M[_]](implicit M: Functor[M]): Functor[M] = M


}
