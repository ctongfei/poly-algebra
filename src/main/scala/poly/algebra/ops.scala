package poly.algebra

import poly.algebra.macroimpl._
import poly.algebra.factory._
import scala.language.experimental.macros

/**
 * Importing this object introduces efficient operator overloading through macros.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object ops {

  /**
   * Enriches any type with mathematical operators if appropriate algebraic structures are implicitly provided.
   * @param x Variable to be enriched
   * @tparam X Type of the variable
   */
  implicit class withOps[X](val x: X) extends AnyVal {

    def op(y: X)   (implicit ev: Semigroup[X])              : X       = macro OpsInliningMacroImpl.binaryOp[X, X, Semigroup[X]]

    def unary_-    (implicit ev: AdditiveGroup[X])          : X       = macro OpsInliningMacroImpl.unaryOp[X, AdditiveGroup[X]]
    def +(y: X)    (implicit ev: AdditiveSemigroup[X])      : X       = macro OpsInliningMacroImpl.binaryOp[X, X, AdditiveSemigroup[X]]
    def -(y: X)    (implicit ev: AdditiveGroup[X])          : X       = macro OpsInliningMacroImpl.binaryOp[X, X, AdditiveGroup[X]]
    def *(y: X)    (implicit ev: MultiplicativeSemigroup[X]): X       = macro OpsInliningMacroImpl.binaryOp[X, X, MultiplicativeSemigroup[X]]
    def /(y: X)    (implicit ev: MultiplicativeGroup[X])    : X       = macro OpsInliningMacroImpl.binaryOp[X, X, MultiplicativeGroup[X]]
    def %(y: X)    (implicit ev: EuclideanDomain[X])        : X       = macro OpsInliningMacroImpl.binaryOp[X, X, EuclideanDomain[X]]

    def **(n: Int) (implicit ev: MultiplicativeSemigroup[X]): X       = macro OpsInliningMacroImpl.ipowOp[X, MultiplicativeSemigroup[X]]
    def **(y: X)   (implicit ev: PowerOps[X])               : X       = macro OpsInliningMacroImpl.binaryOp[X, X, PowerOps[X]]

    def unary_!    (implicit ev: BooleanAlgebra[X])         : X       = macro OpsInliningMacroImpl.unaryOp[X, BooleanAlgebra[X]]
    def &(y: X)    (implicit ev: BooleanAlgebra[X])         : X       = macro OpsInliningMacroImpl.binaryOp[X, X, BooleanAlgebra[X]]
    def |(y: X)    (implicit ev: BooleanAlgebra[X])         : X       = macro OpsInliningMacroImpl.binaryOp[X, X, BooleanAlgebra[X]]
    def ^(y: X)    (implicit ev: BooleanAlgebra[X])         : X       = macro OpsInliningMacroImpl.binaryOp[X, X, BooleanAlgebra[X]]

    def :*[S](y: S)(implicit ev: MultiplicativeAction[X, S]): X       = macro OpsInliningMacroImpl.binaryOp[X, S, MultiplicativeAction[X, S]]
    def *:[S](y: S)(implicit ev: MultiplicativeAction[X, S]): X       = macro OpsInliningMacroImpl.binaryOp[X, S, MultiplicativeAction[X, S]]

    //def :+[S](y: S)
    //def +:[S](y: S)

    def >?<(y: X)  (implicit ev: WeakOrder[X])              : Int     = macro OpsInliningMacroImpl.binaryOp[X, X, WeakOrder[X]]
    def =~=(y: X)  (implicit ev: Equiv[X])                  : Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, Equiv[X]]
    def =!=(y: X)  (implicit ev: Equiv[X])                  : Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, Equiv[X]]
    def ≠(y: X)    (implicit ev: Equiv[X])                  : Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, Equiv[X]]
    def <=(y: X)   (implicit ev: PartialOrder[X])           : Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def ≤(y: X)    (implicit ev: PartialOrder[X])           : Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def ≥(y: X)    (implicit ev: PartialOrder[X])           : Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def >=(y: X)   (implicit ev: PartialOrder[X])           : Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def <(y: X)    (implicit ev: PartialOrder[X])           : Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def >(y: X)    (implicit ev: PartialOrder[X])           : Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]

    def ⋅[F](y: X) (implicit ev: InnerProductSpace[X, F])   : F       = macro OpsInliningMacroImpl.binaryOp[X, X, InnerProductSpace[X, F]]

    def ++(y: X)   (implicit ev: ConcatenativeSemigroup[X]) : X       = macro OpsInliningMacroImpl.binaryOp[X, X, ConcatenativeSemigroup[X]]

    def ###        (implicit ev: IntHashing[X])             : Int     = macro OpsInliningMacroImpl.unaryOp[X, IntHashing[X]]

  }


}
