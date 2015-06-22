package poly.algebra.hkt

import poly.algebra._
import scala.language.higherKinds
/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.1
 */
trait UniversalConcatenativeMonoid[M[_]] extends UniversalConcatenativeSemigroup[M] { self =>

  def empty[x]: M[x]

  implicit def concatenativeMonoid[x]: ConcatenativeMonoid[M[x]] = new ConcatenativeMonoid[M[x]] {
    def concat(mx: M[x], my: M[x]) = self.concat(mx, my)
    def empty: M[x] = self.empty[x]
  }

}

