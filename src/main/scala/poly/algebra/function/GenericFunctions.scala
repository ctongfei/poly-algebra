package poly.algebra.function

import poly.algebra._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait GenericFunctions {

  /** Returns the identity element of a type. */
  def id[@sp(di) X](implicit ev: HasIdentity[X]) = ev.id

  /** Returns the zero element (additive identity) of a type. */
  def zero[@sp(di) X](implicit ev: HasZero[X]) = ev.zero

  /** Returns the one element (multiplicative identity) of a type. */
  def one[@sp(di) X](implicit ev: HasOne[X]) = ev.one

  /** Returns the multiplicative inverse of an element. */
  def inv[@sp(di) X](x: X)(implicit ev: MultiplicativeGroup[X]) = ev.inv(x)

  def sqr[@sp(di) X](x: X)(implicit ev: MultiplicativeSemigroup[X]) = ev.mul(x, x)

  def cube[@sp(di) X](x: X)(implicit ev: MultiplicativeSemigroup[X]) = ev.mul(ev.mul(x, x), x)

  /** Returns the distance between two vectors in a metric space. */
  def dist[V, @sp(fdi) F](x: V, y: V)(implicit ev: MetricSpace[V, F]) = ev.dist(x, y)

  /** Returns the norm of a vector in a normed vector space. */
  def norm[V, @sp(fdi) F](x: V)(implicit ev: NormedVectorSpace[V, F]) = ev.norm(x)

  /** Returns the greatest common divisor of two elements. */
  def gcd[@sp(i) X](x: X, y: X)(implicit ev: EuclideanDomain[X]) = ev.gcd(x, y)

  /** Returns the least common multiplier of two elements. */
  def lcm[@sp(i) X](x: X, y: X)(implicit ev: EuclideanDomain[X]) = ev.lcm(x, y)

  /** Returns the supremum (join) of two elements in a lattice. */
  def sup[@sp(di) X](x: X, y: X)(implicit ev: UpperSemilattice[X]) = ev.sup(x, y)

  /** Returns the infimum (meet) of two elemeents in a lattice. */
  def inf[@sp(di) X](x: X, y: X)(implicit ev: LowerSemilattice[X]) = ev.inf(x, y)

  def max[@sp(di) X](x: X, y: X)(implicit ev: WeakOrder[X]) = ev.max(x, y)

  def min[@sp(di) X](x: X, y: X)(implicit ev: WeakOrder[X]) = ev.min(x, y)

  def gcd[@sp(di) X](xs: X*)(implicit ev: EuclideanDomain[X]) = xs.reduce(ev.gcd)

  def lcm[@sp(di) X](xs: X*)(implicit ev: EuclideanDomain[X]) = xs.reduce(ev.lcm)

  def sup[@sp(di) X](xs: X*)(implicit ev: UpperSemilattice[X]) = xs.reduce(ev.sup)

  def inf[@sp(di) X](xs: X*)(implicit ev: LowerSemilattice[X]) = xs.reduce(ev.inf)

  def max[@sp(di) X](xs: X*)(implicit ev: WeakOrder[X]) = xs.reduce(ev.max)

  def min[@sp(di) X](xs: X*)(implicit ev: WeakOrder[X]) = xs.reduce(ev.min)




}
