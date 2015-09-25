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

object ContravariantFunctor extends ImplicitHktGetter[ContravariantFunctor] {

  /** The default contravariant functor on functions. */
  implicit def functionContravariantFunctor[Z]: ContravariantFunctor[({type λ[-α] = α => Z})#λ] =
    new ContravariantFunctor[({type λ[-α] = α => Z})#λ] {
      def contramap[Y, X](g: Y => Z)(f: X => Y) = f andThen g
    }

}
