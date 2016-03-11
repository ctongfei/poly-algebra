package poly.algebra.function

import poly.algebra._
import poly.macroutil._
import poly.algebra.specgroup._
import scala.language.experimental.macros
import scala.reflect.macros.blackbox._

/**
 * @author Tongfei Chen
 * @since 0.2.0
 */
trait MapReduceMathOps {

  def Sum[@sp(fdi) X](n: Int)(f: Int => X)(implicit X: AdditiveSemigroup[X]): X =
    MapReduceOps.bySemigroup(n, f, X.add)
  
  def Product[@sp(fdi) X](n: Int)(f: Int => X)(implicit X: MultiplicativeSemigroup[X]): X =
    MapReduceOps.bySemigroup(n, f, X.mul)
  
  def Max[@sp(fdi) X](n: Int)(f: Int => X)(implicit X: WeakOrder[X]): X =
    MapReduceOps.bySemigroup(n, f, X.max[X])

  def Min[@sp(fdi) X](n: Int)(f: Int => X)(implicit X: WeakOrder[X]): X =
    MapReduceOps.bySemigroup(n, f, X.min[X])

  def Sup[@sp(fdi) X](n: Int)(f: Int => X)(implicit X: UpperSemilattice[X]): X =
    MapReduceOps.bySemigroup(n, f, X.sup)

  def Inf[@sp(fdi) X](n: Int)(f: Int => X)(implicit X: LowerSemilattice[X]): X =
    MapReduceOps.bySemigroup(n, f, X.inf)

}
