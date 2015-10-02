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

    def map      [Y](f: X => Y      )(implicit H: Functor[H]             ): H[Y]      = H.map(x)(f)
    def contramap[Y](f: Y => X      )(implicit H: ContravariantFunctor[H]): H[Y]      = H.contramap(x)(f)
    def flatMap  [Y](f: X => H[Y]   )(implicit H: Monad[H]               ): H[Y]      = H.flatMap(x)(f)
    def filter      (f: X => Boolean)(implicit H: ConcatenativeMonad[H]  ): H[X]      = H.filter(x)(f)
    def product  [Y](y: H[Y])        (implicit H: ApplicativeFunctor[H]  ): H[(X, Y)] = H.product(x)(y)

    def |>       [Y](f: X => Y)      (implicit H: Functor[H]             ): H[Y]      = H.map(x)(f)
    def |<       [Y](f: Y => X)      (implicit H: ContravariantFunctor[H]): H[Y]      = H.contramap(x)(f)
    def ||>      [Y](f: X => H[Y])   (implicit H: Monad[H]               ): H[Y]      = H.flatMap(x)(f)
    def |?          (f: X => Boolean)(implicit H: ConcatenativeMonad[H]  ): H[X]      = H.filter(x)(f)
    def ×        [Y](y: H[Y])        (implicit H: ApplicativeFunctor[H]  ): H[(X, Y)] = H.product(x)(y)

  }

  implicit class withBiHktOps[H[_, _], X, Y](val x: H[X, Y]) extends AnyVal {

    def map1     [Z](f: X => Z)      (implicit H: Bifunctor[H]         ) = H.map1(x)(f)
    def map2     [Z](f: Y => Z)      (implicit H: Bifunctor[H]         ) = H.map2(x)(f)
    def flatMap  [Z](f: Y => H[X, Z])(implicit H: Monad[({type λ[υ] = H[X, υ]})#λ]) = H.flatMap(x)(f)
    def map      [Z](f: Y => Z)      (implicit H: Profunctor[H]        ) = H.map(x)(f)
    def contramap[Z](f: Z => X)      (implicit H: Profunctor[H]        ) = H.contramap(x)(f)
    def andThen  [Z](f: H[Y, Z])     (implicit H: Category[H]          ) = H.andThen(x, f)
    def compose  [Z](f: H[Z, X])     (implicit H: Category[H]          ) = H.compose(x, f)

    def |>       [Z](f: Y => Z)      (implicit H: Profunctor[H]        ) = H.map(x)(f)
    def |<       [Z](f: Z => X)      (implicit H: Profunctor[H]        ) = H.contramap(x)(f)
    def ||>      [Z](f: Y => H[X, Z])(implicit H: Monad[({type λ[υ] = H[X, υ]})#λ]) = H.flatMap(x)(f)
    def >>>      [Z](f: H[Y, Z])     (implicit H: Category[H]          ) = H.andThen(x, f)
    def <<<      [Z](f: H[Z, X])     (implicit H: Category[H]          ) = H.compose(x, f)

  }

  implicit class KleisliOps[M[_], X, Y](val f: X => M[Y]) extends AnyVal {
    def kleisliAndThen[Z](g: Y => M[Z])(implicit M: Monad[M]) = M.Kleisli.andThen(f, g)
    def kleisliCompose[Z](g: Z => M[X])(implicit M: Monad[M]) = M.Kleisli.compose(f, g)

    def >=>           [Z](g: Y => M[Z])(implicit M: Monad[M]) = M.Kleisli.andThen(f, g)
    def <=<           [Z](g: Z => M[X])(implicit M: Monad[M]) = M.Kleisli.compose(f, g)
  }

  implicit class CoKleisliOps[W[_], X, Y](val f: W[X] => Y) extends AnyVal {
    def coKleisliAndThen[Z](g: W[Y] => Z)(implicit W: Comonad[W]) = W.CoKleisli.andThen(f, g)
    def coKleisliCompose[Z](g: W[Z] => X)(implicit W: Comonad[W]) = W.CoKleisli.compose(f, g)

    def >=>             [Z](g: W[Y] => Z)(implicit W: Comonad[W]) = W.CoKleisli.andThen(f, g)
    def <=<             [Z](g: W[Z] => X)(implicit W: Comonad[W]) = W.CoKleisli.compose(f, g)
  }

}
