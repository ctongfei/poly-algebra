package poly.algebra.hkt

import poly.algebra.factory._

import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * Typeclass for contravariant functors.
 * @since 0.2.0
 */
trait ContravariantFunctor[F[_]] {

  def contramap[X, Y](mx: F[X])(f: Y => X): F[Y]

  def lift[X, Y](f: Y => X): F[X] => F[Y] = mx => contramap(mx)(f)

}

object ContravariantFunctor extends ImplicitHktGetter[ContravariantFunctor]
