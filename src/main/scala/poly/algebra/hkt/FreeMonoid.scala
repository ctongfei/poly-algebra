package poly.algebra.hkt

import poly.algebra._
import scala.language.higherKinds

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.2
 */
trait FreeMonoid[F[_], X] extends ConcatenativeMonoid[F[X]] with Monoid[X]

trait FreeSemigroup[F[_], X] extends ConcatenativeSemigroup[F[X]] with Semigroup[X]
