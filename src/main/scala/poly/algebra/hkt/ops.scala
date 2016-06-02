package poly.algebra.hkt

import scala.language.higherKinds
import scala.language.reflectiveCalls

/**
 * @author Tongfei Chen
 */
object ops extends HktImplicits

trait HktImplicits {

  /**
   * Enriches any type with higher-kinded type operations if appropriate algebraic structures are implicitly provided.
   * @param x Variable to be enriched
   * @tparam H Higher-kinded type
   * @tparam X Type of the variable
   */
  implicit class withHktOps[H[_], X](val x: H[X]) {

    def map      [Y](f: X => Y      )(implicit H: Functor[H]             ): H[Y]      = H.map(x)(f)
    def contramap[Y](f: Y => X      )(implicit H: ContravariantFunctor[H]): H[Y]      = H.contramap(x)(f)
    def flatMap  [Y](f: X => H[Y]   )(implicit H: Monad[H]               ): H[Y]      = H.flatMap(x)(f)
    def filter      (f: X => Boolean)(implicit H: ConcatenativeMonad[H]  ): H[X]      = H.filter(x)(f)
    def product  [Y](y: H[Y])        (implicit H: Idiom[H]               ): H[(X, Y)] = H.product(x)(y)

    def |>       [Y](f: X => Y)      (implicit H: Functor[H]             ): H[Y]      = H.map(x)(f)
    def <|       [Y](f: Y => X)      (implicit H: ContravariantFunctor[H]): H[Y]      = H.contramap(x)(f)
    def ||>      [Y](f: X => H[Y])   (implicit H: Monad[H]               ): H[Y]      = H.flatMap(x)(f)
    def |?          (f: X => Boolean)(implicit H: ConcatenativeMonad[H]  ): H[X]      = H.filter(x)(f)
    def |*|      [Y](y: H[Y])        (implicit H: Idiom[H]               ): H[(X, Y)] = H.product(x)(y)

  }

  implicit class withBiHktOps[H[_, _], X, Y](val x: H[X, Y]) {

    def map1     [Z](f: X => Z)      (implicit H: Bifunctor[H]         ) = H.mapFirst(x)(f)
    def map2     [Z](f: Y => Z)      (implicit H: Bifunctor[H]         ) = H.mapSecond(x)(f)
    def flatMap  [Z](f: Y => H[X, Z])(implicit H: Monad[({type λ[υ] = H[X, υ]})#λ]) = H.flatMap(x)(f)
    def map      [Z](f: Y => Z)      (implicit H: Profunctor[H]        ) = H.map(x)(f)
    def contramap[Z](f: Z => X)      (implicit H: Profunctor[H]        ) = H.contramap(x)(f)
    def andThen  [Z](f: H[Y, Z])     (implicit H: Category[H]          ) = H.andThen(x, f)
    def compose  [Z](f: H[Z, X])     (implicit H: Category[H]          ) = H.compose(x, f)

    def |>       [Z](f: Y => Z)      (implicit H: Profunctor[H]        ) = H.map(x)(f)
    def <|       [Z](f: Z => X)      (implicit H: Profunctor[H]        ) = H.contramap(x)(f)
    def ||>      [Z](f: Y => H[X, Z])(implicit H: Monad[({type λ[υ] = H[X, υ]})#λ]) = H.flatMap(x)(f)
    def >>>      [Z](f: H[Y, Z])     (implicit H: Category[H]          ) = H.andThen(x, f)
    def <<<      [Z](f: H[Z, X])     (implicit H: Category[H]          ) = H.compose(x, f)

  }

  implicit class FuncOps[X, Y](private val func: X => Y) {
    /**
     * Lifts this function (`X => Y`) to a functor application (`F[X] => F[Y]`).
     * @tparam F Functor
     */
    def liftF[F[_]](x: F[X])(implicit F: Functor[F]) = F.map(x)(func)

    /**
     * Lifts this function (`X => Y`) to a contravariant functor application (`F[Y] => F[X]`).
     * @tparam F Contravariant functor
     */
    def liftCF[F[_]](y: F[Y])(implicit F: ContravariantFunctor[F]) = F.contramap(y)(func)
  }

  implicit class Func2Ops[X, Y, Z](private val f: (X, Y) => Z) {
    /**
     * Lifts this function `(X, Y) => Z` to an idiomatic (a.k.a. applicative functor) application (`(F[X], F[Y]) => F[Z]`).
     * @tparam I Idiom
     */
    def liftI[I[_]](x: I[X], y: I[Y])(implicit I: Idiom[I]) = I.productMap(x, y)(f)
  }

  implicit class KleisliOps[M[_], X, Y](private val f: X => M[Y]) {

    /**
     * Lifts this function `X => M[Y]` to a monadic application (`M[X] => M[Y]`).
     * @param M Monad
     */
    def liftM(x: M[X])(implicit M: Monad[M]) = M.flatMap(x)(f)

    def kleisliAndThen[Z](g: Y => M[Z])(implicit M: Monad[M]) = M.Kleisli.andThen(f, g)
    def kleisliCompose[Z](g: Z => M[X])(implicit M: Monad[M]) = M.Kleisli.compose(f, g)

    /**
     * Composes two instances of Kleisli arrows into one. The left hand side arrow is applied first.
     * @param M `M` should be a monad
     */
    def >=>           [Z](g: Y => M[Z])(implicit M: Monad[M]) = M.Kleisli.andThen(f, g)

    /**
     * Composes two instances of Kleisli arrows into one. The right hand side arrow is applied first.
     * @param M `M` should be a monad
     */
    def <=<           [Z](g: Z => M[X])(implicit M: Monad[M]) = M.Kleisli.compose(f, g)
  }

  implicit class CoKleisliOps[W[_], X, Y](val f: W[X] => Y) {

    def liftCM(x: W[X])(implicit W: Comonad[W]) = W.extend(x)(f)

    def coKleisliAndThen[Z](g: W[Y] => Z)(implicit W: Comonad[W]) = W.CoKleisli.andThen(f, g)
    def coKleisliCompose[Z](g: W[Z] => X)(implicit W: Comonad[W]) = W.CoKleisli.compose(f, g)

    def >=>             [Z](g: W[Y] => Z)(implicit W: Comonad[W]) = W.CoKleisli.andThen(f, g)
    def <=<             [Z](g: W[Z] => X)(implicit W: Comonad[W]) = W.CoKleisli.compose(f, g)
  }

}
