package poly.algebra

import poly.algebra.macroimpl._
import poly.algebra.factory._
import scala.language.experimental.macros

/**
 * Importing this object introduces efficient operator overloading through macros.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object ops extends Priority0Implicits

trait Priority0Implicits extends Priority1Implicits {

  implicit class withSemigroupOps[X: Semigroup](x: X) {
    def op(y: X): X = macro OpsInliningMacroImpl.op2
  }

  implicit class withAdditiveSemigroupOps[X: AdditiveSemigroup](x: X) {
    /** Returns the sum of two elements given an implicit additive semigroup. */
    def +(y: X): X = macro OpsInliningMacroImpl.op2
  }

  implicit class withAdditiveGroupOps[X: AdditiveGroup](x: X) {
    /** Returns the negation of this element given an implicit additive group. */
    def unary_- : X = macro OpsInliningMacroImpl.op1
    /** Returns the difference of two elements given an implicit additive group. */
    def -(y: X): X = macro OpsInliningMacroImpl.op2
  }

  implicit class withMultiplicativeSemigroupOps[X: MultiplicativeSemigroup](x: X) {
    /** Returns the product of two elements given an implicit multiplicative semigroup. */
    def *(y: X): X = macro OpsInliningMacroImpl.op2
    /** Returns the ''n''-th power of an element given an implicit multiplicative semigroup. */
    def **(n: Int): X = macro OpsInliningMacroImpl.ipowOp
  }

  implicit class withMultiplicativeGroupOps[X: MultiplicativeGroup](x: X) {
    def /(y: X): X = macro OpsInliningMacroImpl.op2
  }

  implicit class withEuclideanDomainOps[X: EuclideanDomain](x: X) {
    /** Returns the modulus of two elements given an implicit Euclidean domain. */
    def %(y: X): X = macro OpsInliningMacroImpl.op2
  }

  implicit class withInnerProductSpaceOps[X, S](x: X)(implicit X: InnerProductSpace[X, S]) {
    /** Returns the inner product of two elements given an implicit inner product space. */
    def ⋅(y: X): S = macro OpsInliningMacroImpl.op2
  }

  implicit class withBooleanAlgebraOps[X: BooleanAlgebra](x: X) {
    def unary_! : X = macro OpsInliningMacroImpl.op1
    def &(y: X): X = macro OpsInliningMacroImpl.op2
    def |(y: X): X = macro OpsInliningMacroImpl.op2
    def ^(y: X): X = macro OpsInliningMacroImpl.op2
  }

  implicit class withEquivOps[X: Equiv](x: X) {
    def =~=(y: X) : Boolean = macro OpsInliningMacroImpl.op2
    def =!=(y: X) : Boolean = macro OpsInliningMacroImpl.op2
    def ≠(y: X) : Boolean = macro OpsInliningMacroImpl.op2
  }

  implicit class withPartialOrderOps[X: PartialOrder](x: X) {
    def <=(y: X): Boolean = macro OpsInliningMacroImpl.op2
    def ≤(y: X) : Boolean = macro OpsInliningMacroImpl.op2
    def ≥(y: X) : Boolean = macro OpsInliningMacroImpl.op2
    def >=(y: X): Boolean = macro OpsInliningMacroImpl.op2
    def <(y: X) : Boolean = macro OpsInliningMacroImpl.op2
    def >(y: X) : Boolean = macro OpsInliningMacroImpl.op2
  }

  implicit class withWeakOrderOps[X: WeakOrder](x: X) {
    def >?<(y: X): Int = macro OpsInliningMacroImpl.op2
  }

  implicit class withConcatenativeSemigroupOps[X: ConcatenativeSemigroup](x: X) {
    def ++(y: X): X = macro OpsInliningMacroImpl.op2
  }

  implicit class withHashingOps[X: IntHashing](x: X) {
    def ### : Int = macro OpsInliningMacroImpl.op1
  }
}


trait Priority1Implicits extends Priority2Implicits {

  implicit class withAffineSpaceSubOps[X, V, F](x: X)(implicit X: AffineSpace[X, V, F]) {
    def -(y: X) = X.sub(x, y)
    def -(y: V)(implicit d1: DummyImplicit) = X.translate(X.groupOnActor.neg(y), x)
  }

}

trait Priority2Implicits {


  implicit class withAdditiveActionXOps[X, S](x: X)(implicit X: AdditiveAction[X, S]) {
    /** Returns the result of an object (point) being acted (translated) by an element (vector) given an implicit
      * additive action. */
    def +(y: S) = X.translate(y, x)
  }

  implicit class withAdditiveActionSOps[X, S](s: S)(implicit X: AdditiveAction[X, S]) {
    /** Returns the result of an element (vector) acting (translating) on an object (point) given an implicit
      * additive action. */
    def +(x: X) = X.translate(s, x)
  }

  implicit class withAdditiveGroupActionXOps[X, S](x: X)(implicit X: AdditiveGroupAction[X, S]) {
    def -(y: S) = X.translate(X.groupOnActor.neg(y), x)
  }

  implicit class withMultiplicativeActionXOps[X, S](x: X)(implicit X: MultiplicativeAction[X, S]) {
    /** Returns the result of an object (vector) being acted (scaled) by an element (scalar) given an implicit
      * multiplicative action. */
    def *(y: S) = X.scale(y, x)
  }

  implicit class withMultiplicativeActionSOps[X, S](s: S)(implicit X: MultiplicativeAction[X, S]) {
    /** Returns the result of an element (scalar) acting (scaling) on an object (vector) given an implicit
      * multiplicative action. */
    def *(x: X) = X.scale(s, x)
  }


}
