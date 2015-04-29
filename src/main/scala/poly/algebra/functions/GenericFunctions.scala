package poly.algebra.functions

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait GenericFunctions {

  /** Returns the identity element of a type. */
  def id[X](implicit ev: HasIdentity[X]) = ev.id

  /** Returns the zero element (additive identity) of a type. */
  def zero[X](implicit ev: HasZero[X]) = ev.zero

  /** Returns the one element (multiplicative identity) of a type. */
  def one[X](implicit ev: HasOne[X]) = ev.one

  /** Returns the multiplicative inverse of an element. */
  def inv[X](x: X)(implicit ev: MultiplicativeGroup[X]) = ev.inv(x)

  /** Returns the greatest common divisor of two elements. */
  def gcd[X](x: X, y: X)(implicit ev: EuclideanDomain[X]) = ev.gcd(x, y)

  /** Returns the least common multiplier of two elements. */
  def lcm[X](x: X, y: X)(implicit ev: EuclideanDomain[X]) = ev.lcm(x, y)

  /** Returns the distance between two vectors in a metric space. */
  def dist[V, F](x: V, y: V)(implicit ev: MetricSpace[V, F]) = ev.dist(x, y)

  /** Returns the norm of a vector in a normed vector space. */
  def norm[V, F](x: V)(implicit ev: NormedVectorSpace[V, F]) = ev.norm(x)

  /** Returns the supremum (join) of two elements in a lattice. */
  def sup[X](x: X, y: X)(implicit ev: UpperSemilattice[X]) = ev.sup(x, y)

  /** Returns the infimum (meet) of two elemeents in a lattice. */
  def inf[X](x: X, y: X)(implicit ev: LowerSemilattice[X]) = ev.inf(x, y)

}
