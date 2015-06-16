package poly.algebra.hkt

import poly.algebra._
import scala.language.higherKinds

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait UniversalConcatenativeSemigroup[S[_]] { self =>

  def concat[x](sx: S[x], sy: S[x]): S[x]

  implicit def concatenativeSemigroup[x] = new ConcatenativeSemigroup[S[x]] {
    def concat(a: S[x], b: S[x]): S[x] = self.concat(a, b)
  }
  
}
