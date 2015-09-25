package poly.algebra.hkt

import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object ops {
  /**
   * Enriches any type with higher-kinded type operations if appropriate algebraic structures are implicitly provided.
   * @param x Variable to be enriched
   * @tparam H Higher-kinded type
   * @tparam X Type of the variable
   */
  implicit class withHktOps[H[_], X](val x: H[X]) extends AnyVal {

    def map      [Y](f: X => Y      )(implicit H: Functor[H]             ): H[Y] = H.map(x)(f)
    def contramap[Y](f: Y => X      )(implicit H: ContravariantFunctor[H]): H[Y] = H.contramap(x)(f)
    def flatMap  [Y](f: X => H[Y]   )(implicit H: Monad[H]               ): H[Y] = H.flatMap(x)(f)
    def filter      (f: X => Boolean)(implicit H: ConcatenativeMonad[H]  ): H[X] = H.filter(x)(f)

    def |>       [Y](f: X => Y)      (implicit H: Functor[H]             ): H[Y] = H.map(x)(f)
    def |<       [Y](f: Y => X)      (implicit H: ContravariantFunctor[H]): H[Y] = H.contramap(x)(f)
    def ||>      [Y](f: X => H[Y])   (implicit H: Monad[H]               ): H[Y] = H.flatMap(x)(f)
    def |?          (f: X => Boolean)(implicit H: ConcatenativeMonad[H]  ): H[X] = H.filter(x)(f)

  }

  implicit class withBiHktOps[H[_, _], X, Y](val x: H[X, Y]) extends AnyVal {

    def map1     [Z](f: X => Z)      (implicit H: Bifunctor[H]         ) = H.map1(x)(f)
    def map2     [Z](f: Y => Z)      (implicit H: Bifunctor[H]         ) = H.map2(x)(f)
    def flatMap  [Z](f: Y => H[X, Z])(implicit H: Monad[({type λ[υ] = H[X, υ]})#λ]) = H.flatMap(x)(f)
    def map      [Z](f: Y => Z)      (implicit H: Profunctor[H]        ) = H.map(x)(f)
    def contramap[Z](f: Z => X)      (implicit H: Profunctor[H]        ) = H.contramap(x)(f)

    def |>       [Z](f: Y => Z)      (implicit H: Profunctor[H]        ) = H.map(x)(f)
    def |<       [Z](f: Z => X)      (implicit H: Profunctor[H]        ) = H.contramap(x)(f)
    def ||>      [Z](f: Y => H[X, Z])(implicit H: Monad[({type λ[υ] = H[X, υ]})#λ]) = H.flatMap(x)(f)

  }

}
