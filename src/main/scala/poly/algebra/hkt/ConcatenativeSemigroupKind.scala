package poly.algebra.hkt

import poly.algebra._
import scala.language.higherKinds

/**
 * Represents a universally quantified concatenative semigroup.
 * @author Tongfei Chen
 * @since 0.2.1
 */
trait ConcatenativeSemigroupKind[S[_]] { self =>

  def concat[X](sx: S[X], sy: S[X]): S[X]

  implicit def concatenativeSemigroup[X] = new ConcatenativeSemigroup[S[X]] {
    def concat(a: S[X], b: S[X]): S[X] = self.concat(a, b)
  }
  
}
