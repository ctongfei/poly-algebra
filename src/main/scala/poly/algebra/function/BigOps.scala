package poly.algebra.function

import poly.algebra._
import poly.algebra.macroimpl._
import scala.language.experimental.macros

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait BigOps {

  def Sum[X](n: Int)(f: Int => X)(implicit ev: AdditiveMonoid[X]): X =
    macro MapReduceMacroImpl.sumOp[X, AdditiveMonoid[X]]
  
  def Product[X](n: Int)(f: Int => X)(implicit ev: MultiplicativeMonoid[X]): X =
    macro MapReduceMacroImpl.productOp[X, MultiplicativeMonoid[X]]

  def ForAll[X](n: Int)(f: Int => X)(implicit ev: BooleanAlgebra[X]): X =
    macro MapReduceMacroImpl.forAllOp[X, BooleanAlgebra[X]]

  def Exists[X](n: Int)(f: Int => X)(implicit ev: BooleanAlgebra[X]): X =
    macro MapReduceMacroImpl.existsOp[X, BooleanAlgebra[X]]

  def ∑[X](n: Int)(f: Int => X)(implicit ev: AdditiveMonoid[X]): X =
    macro MapReduceMacroImpl.sumOp[X, AdditiveMonoid[X]]

  def ∏[X](n: Int)(f: Int => X)(implicit ev: MultiplicativeMonoid[X]): X =
    macro MapReduceMacroImpl.productOp[X, MultiplicativeMonoid[X]]

  def ∀[X](n: Int)(f: Int => X)(implicit ev: BooleanAlgebra[X]): X =
    macro MapReduceMacroImpl.forAllOp[X, BooleanAlgebra[X]]

  def ∃[X](n: Int)(f: Int => X)(implicit ev: BooleanAlgebra[X]): X =
    macro MapReduceMacroImpl.existsOp[X, BooleanAlgebra[X]]

}
