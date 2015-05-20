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
  implicit class withHktOps[M[_], X](val mx: M[X]) extends AnyVal {

    def map[Y](f: X => Y)(implicit F: Functor[M]): M[Y] = F.map(mx)(f)

    def flatMap[Y](f: X => M[Y])(implicit F: Monad[M]): M[Y] = F.flatMap(mx)(f)

  }

}
