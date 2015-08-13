package poly.algebra

import poly.algebra.macroimpl._
import poly.util.typeclass._
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

    def op(y: X)(implicit ev: Semigroup[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, Semigroup[X]]

    def unary_-(implicit ev: AdditiveGroup[X]): X = macro OpsInliningMacroImpl.unaryOp[X, AdditiveGroup[X]]
    def +(y: X)(implicit ev: AdditiveSemigroup[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, AdditiveSemigroup[X]]
    def -(y: X)(implicit ev: AdditiveGroup[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, AdditiveGroup[X]]
    def *(y: X)(implicit ev: MultiplicativeSemigroup[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, MultiplicativeSemigroup[X]]
    def /(y: X)(implicit ev: EuclideanDomain[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, EuclideanDomain[X]]
    def %(y: X)(implicit ev: EuclideanDomain[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, EuclideanDomain[X]]

    def **(n: Int)(implicit ev: MultiplicativeSemigroup[X]): X = macro OpsInliningMacroImpl.ipowOp[X, MultiplicativeSemigroup[X]]
    def **(y: X)(implicit ev: PowerOps[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, PowerOps[X]]

    def unary_!(implicit ev: BooleanAlgebra[X]): X = macro OpsInliningMacroImpl.unaryOp[X, BooleanAlgebra[X]]
    def &(y: X)(implicit ev: BooleanAlgebra[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, BooleanAlgebra[X]]
    def |(y: X)(implicit ev: BooleanAlgebra[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, BooleanAlgebra[X]]
    def ^(y: X)(implicit ev: BooleanAlgebra[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, BooleanAlgebra[X]]

    def ∧(y: X)(implicit ev: BooleanAlgebra[X]) = ev.and(x, y)
    def ∨(y: X)(implicit ev: BooleanAlgebra[X]) = ev.or(x, y)

    def :*[R](y: R)(implicit ev: Module[X, R]): X = macro OpsInliningMacroImpl.binaryOp[X, R, Module[X, R]]
    def *:[R](y: R)(implicit ev: Module[X, R]): X = macro OpsInliningMacroImpl.binaryOp[X, R, Module[X, R]]

    def <>?(y: X)(implicit ev: WeakOrder[X]): Int = macro OpsInliningMacroImpl.binaryOp[X, X, WeakOrder[X]]
    def =~=(y: X)(implicit ev: Eq[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, Eq[X]]
    def =!=(y: X)(implicit ev: Eq[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, Eq[X]]
    def <=(y: X)(implicit ev: PartialOrder[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def >=(y: X)(implicit ev: PartialOrder[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def <(y: X)(implicit ev: PartialOrder[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def >(y: X)(implicit ev: PartialOrder[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]

    //TODO: macro
    def dot[F](y: X)(implicit ev: InnerProductSpace[X, F]) = ev.dot(x, y)

    def ++(y: X)(implicit ev: ConcatenativeSemigroup[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, ConcatenativeSemigroup[X]]

    //def +=(y: X)(implicit ev: InplaceAdditiveSemigroup[X]): Unit = ev.inplaceAdd(x, y)
    //def -=(y: X)(implicit ev: InplaceAdditiveGroup[X]): Unit = ev.inplaceSub(x, y)

  }


}
