package poly.algebra.functions

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait GenericFunctions {

  /** Returns a hashed value of the specific value. */
  def hash[X](x: X)(implicit ev: Hash[X]): Int = ev.hash(x)

  /** Returns the identity element of a type. */
  def id[X](implicit ev: HasIdentity[X]) = ev.id

  /** Returns the zero element (additive identity) of a type. */
  def zero[X](implicit ev: HasZero[X]) = ev.zero

  /** Returns the one element (multiplicative identity) of a type. */
  def one[X](implicit ev: HasOne[X]) = ev.one

  /** Returns the multiplicative inverse of an element. */
  def inv[X](x: X)(implicit ev: MultiplicativeGroup[X]) = ev.inv(x)

  /** Returns the distance between two vectors in a metric space. */
  def dist[V, F](x: V, y: V)(implicit ev: MetricSpace[V, F]) = ev.dist(x, y)

  /** Returns the norm of a vector in a normed vector space. */
  def norm[V, F](x: V)(implicit ev: NormedVectorSpace[V, F]) = ev.norm(x)

  /** Returns the greatest common divisor of two elements. */
  def gcd[X](x: X, y: X)(implicit ev: EuclideanDomain[X]) = ev.gcd(x, y)

  /** Returns the least common multiplier of two elements. */
  def lcm[X](x: X, y: X)(implicit ev: EuclideanDomain[X]) = ev.lcm(x, y)

  /** Returns the supremum (join) of two elements in a lattice. */
  def sup[X](x: X, y: X)(implicit ev: UpperSemilattice[X]) = ev.sup(x, y)

  /** Returns the infimum (meet) of two elemeents in a lattice. */
  def inf[X](x: X, y: X)(implicit ev: LowerSemilattice[X]) = ev.inf(x, y)

  def max[X](x: X, y: X)(implicit ev: WeakOrder[X]) = ev.max(x, y)

  def min[X](x: X, y: X)(implicit ev: WeakOrder[X]) = ev.min(x, y)

  def gcd[X](xs: X*)(implicit ev: EuclideanDomain[X]) = xs.reduce(ev.gcd)

  def lcm[X](xs: X*)(implicit ev: EuclideanDomain[X]) = xs.reduce(ev.lcm)

  def sup[X](xs: X*)(implicit ev: UpperSemilattice[X]) = xs.reduce(ev.sup)

  def inf[X](xs: X*)(implicit ev: LowerSemilattice[X]) = xs.reduce(ev.inf)

  def max[X](xs: X*)(implicit ev: WeakOrder[X]) = xs.reduce(ev.max)

  def min[X](xs: X*)(implicit ev: WeakOrder[X]) = xs.reduce(ev.min)




}
