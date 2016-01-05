package poly.algebra.hkt

import poly.algebra.factory._

import scala.language.higherKinds
/**
  * @author Tongfei Chen
  * @since 0.2.17
  */
trait NaturalTransformation[-A[_], +B[_]] { self =>

  import NaturalTransformation._

  def apply[X](a: A[X]): B[X]

  def andThen[C[_]](that: B ~~> C): A ~~> C = new (A ~~> C) {
    def apply[X](a: A[X]) = that(self(a))
  }

  def compose[C[_]](that: C ~~> A) = that andThen self

}

object NaturalTransformation extends Binary1TImplicitHktGetter[NaturalTransformation] {

  type ~~>[A[_], B[_]] = NaturalTransformation[A, B]

}
