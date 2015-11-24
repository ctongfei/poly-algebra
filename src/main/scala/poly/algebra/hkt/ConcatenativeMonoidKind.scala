package poly.algebra.hkt

import poly.algebra._
import scala.language.higherKinds
/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.1
 */
trait ConcatenativeMonoidKind[M[_]] extends ConcatenativeSemigroupKind[M] { self =>

  def empty[X]: M[X]

  implicit def concatenativeMonoid[X]: ConcatenativeMonoid[M[X]] = new ConcatenativeMonoid[M[X]] {
    def concat(mx: M[X], my: M[X]) = self.concat(mx, my)
    def empty: M[X] = self.empty[X]
  }
  
}

