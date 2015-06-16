package poly.algebra.hkt

import scala.language.higherKinds

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait ConcatenativeMonad[M[_]] extends Monad[M] with UniversalConcatenativeMonoid[M] {

  def filter[x](mx: M[x])(f: x => Boolean) = flatMap(mx)(e => if (f(e)) id(e) else empty[x])

}
