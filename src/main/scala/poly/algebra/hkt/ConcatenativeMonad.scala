package poly.algebra.hkt

import scala.language.higherKinds

/**
 * Typeclass for concatenative monads. This is analogous to the Haskell typeclass `MonadPlus`.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.1
 */
trait ConcatenativeMonad[M[+_]] extends Monad[M] with ConcatenativeMonoidKind[M] {

  def filter[X](mx: M[X])(f: X => Boolean) = flatMap(mx)(e => if (f(e)) id(e) else empty[X])

}
