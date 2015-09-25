package poly.algebra.hkt

import scala.language.higherKinds

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object ops {
  /**
   * Enriches any type with higher-kinded type operations if appropriate algebraic structures are implicitly provided.
   * @param mx Variable to be enriched
   * @tparam M Higher-kinded type
   * @tparam X Type of the variable
   */
  implicit class withHktOps[M[+_], X](val mx: M[X]) extends AnyVal {

    def map[Y](f: X => Y)(implicit F: Functor[M]): M[Y] = F.map(mx)(f)

    def flatMap[Y](f: X => M[Y])(implicit F: Monad[M]): M[Y] = F.flatMap(mx)(f)

    def filter(f: X => Boolean)(implicit F: ConcatenativeMonad[M]): M[X] = F.filter(mx)(f)

  }

  implicit class withContravariantHktOps[M[-_], X](val mx: M[X]) extends AnyVal {
    def contramap[Y](f: Y => X)(implicit F: ContravariantFunctor[M]): M[Y] = F.contramap(mx)(f)
  }
  
  implicit class withBiHktOps[H[+_, +_], X, Y](val x: H[X, Y]) extends AnyVal {
    def map1[Z](f: X => Z)(implicit H: Bifunctor[H]) = H.map1(x)(f)
    def map2[Z](f: Y => Z)(implicit H: Bifunctor[H]) = H.map2(x)(f)
  }

}
