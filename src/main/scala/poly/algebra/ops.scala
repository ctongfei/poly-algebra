package poly.algebra

import scala.language.experimental.macros
import poly.algebra.macroimpl._

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

    def op(implicit ev: Semigroup[X]): X = macro MacroImpl.unaryOp[X, Semigroup[X]]

    def unary_-(implicit ev: AdditiveGroup[X]): X = macro MacroImpl.unaryOp[X, AdditiveGroup[X]]
    def +(y: X)(implicit ev: AdditiveSemigroup[X]): X = macro MacroImpl.binaryOp[X, X, AdditiveSemigroup[X]]
    def -(y: X)(implicit ev: AdditiveGroup[X]): X = macro MacroImpl.binaryOp[X, X, AdditiveGroup[X]]
    def *(y: X)(implicit ev: MultiplicativeSemigroup[X]): X = macro MacroImpl.binaryOp[X, X, MultiplicativeSemigroup[X]]
    def /(y: X)(implicit ev: EuclideanDomain[X]): X = macro MacroImpl.binaryOp[X, X, EuclideanDomain[X]]
    def %(y: X)(implicit ev: EuclideanDomain[X]): X = macro MacroImpl.binaryOp[X, X, EuclideanDomain[X]]

    def **(n: Int)(implicit ev: MultiplicativeSemigroup[X]): X = macro MacroImpl.ipowOp[X, MultiplicativeSemigroup[X]]
    def **(y: X)(implicit ev: PowerOps[X]): X = macro MacroImpl.binaryOp[X, X, PowerOps[X]]

    def unary_!(implicit ev: BooleanAlgebra[X]): X = macro MacroImpl.unaryOp[X, BooleanAlgebra[X]]
    def &(y: X)(implicit ev: BooleanAlgebra[X]): X = macro MacroImpl.binaryOp[X, X, BooleanAlgebra[X]]
    def |(y: X)(implicit ev: BooleanAlgebra[X]): X = macro MacroImpl.binaryOp[X, X, BooleanAlgebra[X]]
    def ^(y: X)(implicit ev: BooleanAlgebra[X]): X = macro MacroImpl.binaryOp[X, X, BooleanAlgebra[X]]

    def and(y: X)(implicit ev: BooleanAlgebra[X]): X = macro MacroImpl.binaryOp[X, X, BooleanAlgebra[X]]
    def or(y: X)(implicit ev: BooleanAlgebra[X]): X = macro MacroImpl.binaryOp[X, X, BooleanAlgebra[X]]
    def xor(y: X)(implicit ev: BooleanAlgebra[X]): X = macro MacroImpl.binaryOp[X, X, BooleanAlgebra[X]]

    def ∧(y: X)(implicit ev: BooleanAlgebra[X]) = ev.and(x, y)
    def ∨(y: X)(implicit ev: BooleanAlgebra[X]) = ev.or(x, y)

    def :*[R](k: R)(implicit ev: Module[X, R]) = ev.scale(k, x)
    def *:[R](k: R)(implicit ev: Module[X, R]) = ev.scale(k, x)

    def ===(y: X)(implicit ev: Eq[X]): Boolean = macro MacroImpl.binaryOp[X, X, Eq[X]]
    def =!=(y: X)(implicit ev: Eq[X]): Boolean = macro MacroImpl.binaryOp[X, X, Eq[X]]
    def <=(y: X)(implicit ev: PartialOrder[X]): Boolean = macro MacroImpl.binaryOp[X, X, PartialOrder[X]]
    def >=(y: X)(implicit ev: PartialOrder[X]): Boolean = macro MacroImpl.binaryOp[X, X, PartialOrder[X]]
    def <(y: X)(implicit ev: WeakOrder[X]): Boolean = macro MacroImpl.binaryOp[X, X, WeakOrder[X]]
    def >(y: X)(implicit ev: WeakOrder[X]): Boolean = macro MacroImpl.binaryOp[X, X, WeakOrder[X]]
    def =~=(y: X)(implicit ev: WeakOrder[X]): Boolean = macro MacroImpl.binaryOp[X, X, WeakOrder[X]]

    def ###(implicit ev: Hash[X]): Int = macro MacroImpl.unaryOp[X, Hash[X]]

    //TODO: macro
    def innerProduct[F](y: X)(implicit ev: InnerProductSpace[X, F]) = ev.dot(x, y)

  }

}
