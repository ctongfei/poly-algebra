package poly.algebra.function

import poly.algebra._
import poly.util.fastloop._
import poly.util.specgroup._
import scala.language.experimental.macros

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.0
 */
trait MapReduceMathOps {

  def Sum[@sp(fdi) X](n: Int)(f: Int => X)(implicit X: AdditiveSemigroup[X]): X =
    MapReduceOps.bySemigroup(n, f, X.add)
  
  def Product[@sp(fdi) X](n: Int)(f: Int => X)(implicit X: MultiplicativeSemigroup[X]): X =
    MapReduceOps.bySemigroup(n, f, X.mul)
  
  def Max[X](n: Int)(f: Int => X)(implicit X: WeakOrder[X]): X =
    MapReduceOps.bySemigroup(n, f, X.max[X])

  def Min[X](n: Int)(f: Int => X)(implicit X: WeakOrder[X]): X =
    MapReduceOps.bySemigroup(n, f, X.min[X])

  def Sup[X](n: Int)(f: Int => X)(implicit X: UpperSemilattice[X]): X =
    MapReduceOps.bySemigroup(n, f, X.sup)

  def Inf[X](n: Int)(f: Int => X)(implicit X: LowerSemilattice[X]): X =
    MapReduceOps.bySemigroup(n, f, X.inf)
  
}
