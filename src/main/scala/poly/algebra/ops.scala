package poly.algebra

import poly.algebra.macroimpl._
import poly.algebra.function._
import scala.language.experimental.macros

/**
 * Importing this object introduces efficient operator overloading through macros.
 * @author Tongfei Chen
 * @since 0.2.0
 */
object ops extends Priority1Implicits

trait Priority1Implicits extends Priority2Implicits {

  implicit class withSemigroupOps[X, Y >: X : Semigroup](x: X) {
    /** Applies the binary operator of an implicit semigroup on the two arguments. */
    def <>(y: Y): Y = macro OpsInlining.op2
  }

  implicit class withAdditiveSemigroupOps[X, Y >: X : AdditiveSemigroup](x: X) {
    /** Returns the sum of two elements given an implicit additive semigroup. */
    def +(y: Y): Y = macro OpsInlining.op2
  }

  implicit class withAdditiveGroupOps[X, Y >: X : AdditiveGroup](x: X) {
    /** Returns the negation of this element given an implicit additive group. */
    def unary_- : Y = macro OpsInlining.op1
    /** Returns the difference of two elements given an implicit additive group. */
    def -(y: Y): Y = macro OpsInlining.op2
  }

  implicit class withMultiplicativeSemigroupOps[X, Y >: X : MultiplicativeSemigroup](x: X) {
    /** Returns the product of two elements given an implicit multiplicative semigroup. */
    def *(y: Y): Y = macro OpsInlining.op2
    /** Returns the ''n''-th power of an element given an implicit multiplicative semigroup. */
    def **(n: Int): X = macro OpsInlining.ipowOp
  }

  implicit class withMultiplicativeGroupOps[X, Y >: X : MultiplicativeGroup](x: X) {
    def /(y: Y): Y = macro OpsInlining.op2
  }

  implicit class withEuclideanDomainOps[X, Y >: X : EuclideanDomain](x: X) {
    /** Returns the modulus of two elements given an implicit Euclidean domain. */
    def %(y: Y): Y = macro OpsInlining.op2
  }

  implicit class withInnerProductSpaceOps[X, Y >: X, S](x: X)(implicit Y: InnerProductSpace[Y, S]) {
    /** Returns the inner product of two elements given an implicit inner product space. */
    def ⋅(y: Y): S = macro OpsInlining.op2

    /** Returns the inner product of two elements given an implicit inner product space. */
    def dot(y: Y): S = macro OpsInlining.op2
  }

  implicit class withAdditiveActionXOps[X, Y >: X, S](x: X)(implicit Y: AdditiveAction[Y, S]) {
    /** Returns the result of an object (point) being acted (translated) by an element (vector) given an implicit
      * additive action. */
    def :+(y: S): Y = macro OpsInlining.op2
    /** Returns the result of an element (vector) acting (translating) on an object (point) given an implicit
      * additive action. */
    def +:(y: S): Y = macro OpsInlining.op2
  }

  implicit class withMultiplicativeActionXOps[X, Y >: X, S](x: X)(implicit Y: MultiplicativeAction[Y, S]) {
    /** Returns the result of an object (vector) being acted (scaled) by an element (scalar) given an implicit
      * multiplicative action. */
    def :*(y: S): Y = macro OpsInlining.op2

    /** Returns the result of an element (scalar) acting (scaling) on an object (vector) given an implicit
      * multiplicative action. */
    def *:(y: S): Y = macro OpsInlining.op2
  }

  implicit class withAdditiveGroupActionXOps[X, Y >: X, S](x: X)(implicit Y: AdditiveGroupAction[Y, S]) {
    def :-(y: S) = Y.translate(x, Y.groupOnActor.neg(y))
  }

  implicit class withBooleanAlgebraOps[X, Y >: X : BooleanAlgebra](x: X) {
    def unary_! : Y = macro OpsInlining.op1
    def &(y: Y): Y = macro OpsInlining.op2
    def |(y: Y): Y = macro OpsInlining.op2
    def ^(y: Y): Y = macro OpsInlining.op2
  }

  implicit class withEquivOps[X, Y >: X : Eq](x: X) {
    def ===(y: Y) : Boolean = macro OpsInlining.op2
    def !==(y: Y) : Boolean = macro OpsInlining.op2
    def ≠(y: Y) : Boolean = macro OpsInlining.op2
  }

  implicit class withPartialOrderOps[X, Y >: X : PartialOrder](x: X) {
    def <=(y: Y): Boolean = macro OpsInlining.op2
    def ≤(y: Y) : Boolean = macro OpsInlining.op2
    def ≥(y: Y) : Boolean = macro OpsInlining.op2
    def >=(y: Y): Boolean = macro OpsInlining.op2
    def <(y: Y) : Boolean = macro OpsInlining.op2
    def >(y: Y) : Boolean = macro OpsInlining.op2
  }

  implicit class withWeakOrderOps[X, Y >: X : Order](x: X) {
    def >?<(y: Y): Int = macro OpsInlining.op2
  }

  implicit class withConcatenativeSemigroupOps[X, Y >: X : ConcatenativeSemigroup](x: X) {
    def ++(y: Y): Y = macro OpsInlining.op2
  }

  implicit class withHashingOps[X: Hashing](x: X) {
    def ### : Int = macro OpsInlining.op1
  }
}


trait Priority2Implicits {

  implicit class withAffineSpaceSubOps[X, Y >: X, V, F](x: X)(implicit Y: AffineSpace[Y, V, F]) {
    def -(y: Y) = Y.sub(x, y)
  }

}
