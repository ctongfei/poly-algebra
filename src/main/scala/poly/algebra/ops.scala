package poly.algebra

import poly.algebra.macroimpl._
import scala.language.experimental.macros

/**
 * Importing this object introduces efficient operator overloading through macros.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object ops {

  implicit val implicitBooleanStructure = std.BooleanStructure
  implicit val implicitIntStructure = std.IntStructure
  implicit val implicitFloatStructure = std.FloatStructure
  implicit val implicitDoubleStructure = std.DoubleStructure

  /**
   * Enriches any type with mathematical operators if appropriate algebraic structures are implicitly provided.
   * @param x Variable to be enriched
   * @tparam X Type of the variable
   */
  implicit class withOps[X](val x: X) extends AnyVal {

    def op(implicit ev: Semigroup[X]): X = macro OpsInliningMacroImpl.unaryOp[X, Semigroup[X]]

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

    def and(y: X)(implicit ev: BooleanAlgebra[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, BooleanAlgebra[X]]
    def or(y: X)(implicit ev: BooleanAlgebra[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, BooleanAlgebra[X]]
    def xor(y: X)(implicit ev: BooleanAlgebra[X]): X = macro OpsInliningMacroImpl.binaryOp[X, X, BooleanAlgebra[X]]

    def ∧(y: X)(implicit ev: BooleanAlgebra[X]) = ev.and(x, y)
    def ∨(y: X)(implicit ev: BooleanAlgebra[X]) = ev.or(x, y)

    def :*[R](k: R)(implicit ev: Module[X, R]) = ev.scale(k, x)
    def *:[R](k: R)(implicit ev: Module[X, R]) = ev.scale(k, x)

    def =~=(y: X)(implicit ev: Eq[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, Eq[X]]
    def =!=(y: X)(implicit ev: Eq[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, Eq[X]]
    def <=(y: X)(implicit ev: PartialOrder[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def >=(y: X)(implicit ev: PartialOrder[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def <(y: X)(implicit ev: PartialOrder[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]
    def >(y: X)(implicit ev: PartialOrder[X]): Boolean = macro OpsInliningMacroImpl.binaryOp[X, X, PartialOrder[X]]

    def ###(implicit ev: Hash[X]): Int = macro OpsInliningMacroImpl.unaryOp[X, Hash[X]]

    //TODO: macro
    def innerProduct[F](y: X)(implicit ev: InnerProductSpace[X, F]) = ev.dot(x, y)

  }

  implicit class withRefEqOps[X <: AnyRef](val x: X) extends AnyVal {
    def =@=(y: X) = x eq y
    def !@=(y: X) = x ne y
  }

}
