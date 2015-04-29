package poly.algebra.functions

import poly.algebra._
import poly.algebra.macroimpl._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait BigOps {

  def sum[X](n: Int)(f: Int => X)(implicit ev: AdditiveSemigroup[X]): X = macro MacroImpl.sumOp[X, AdditiveSemigroup[X]]

}
