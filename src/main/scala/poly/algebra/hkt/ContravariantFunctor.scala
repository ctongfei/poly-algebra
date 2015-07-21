package poly.algebra.hkt

import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * Typeclass for contravariant functors.
 * @since 0.2.0
 */
trait ContravariantFunctor[F[-_]] {

  def contramap[X, Y](mx: F[X])(f: Y => X): F[Y]

  def lift[X, Y](f: Y => X): F[X] => F[Y] = mx => contramap(mx)(f)

}

object ContravariantFunctor {

  def apply[M[_]](implicit M: ContravariantFunctor[M]): ContravariantFunctor[M] = M

  /** The default contravariant functor on functions. */
  implicit def functionContravariantFunctor[Z]: ContravariantFunctor[({type ¦Ë[¦Á] = ¦Á => Z})#¦Ë] =
    new ContravariantFunctor[({type ¦Ë[¦Á] = ¦Á => Z})#¦Ë] {
      def contramap[Y, X](g: Y => Z)(f: X => Y) = f andThen g
    }

}
