package poly.algebra.function

import poly.algebra._
import poly.algebra.macroimpl._
import poly.algebra.specgroup._
import scala.language.experimental.macros

/**
 * @author Tongfei Chen
 */
trait GenericFunctions {

  def generic[X](x: Double)(implicit isReal: IsReal[X]): X = macro OpsInlining.genericImpl[X]
  def generic[X](x: Float)(implicit isReal: IsReal[X]): X = macro OpsInlining.genericImpl[X]
  def generic[X](x: Int)(implicit isReal: IsReal[X]): X = macro OpsInlining.genericImpl[X]

  /** Returns the sum of two elements. */
  def add[@sp(fdi) X](x: X, y: X)(implicit X: AdditiveSemigroup[X]) = X.add(x, y)

  /** Returns the difference of two elements. */
  def sub[@sp(fdi) X](x: X, y: X)(implicit X: AdditiveGroup[X]) = X.sub(x, y)

  /** Returns the product of two elements. */
  def mul[@sp(fdi) X](x: X, y: X)(implicit X: MultiplicativeSemigroup[X]) = X.mul(x, y)

  /** Returns the quotient of two elements. */
  def div[@sp(fdi) X](x: X, y: X)(implicit X: MultiplicativeGroup[X]) = X.div(x, y)

  /** Returns the remainder of two elements after the modulo operation. */
  def mod[@sp(Int) X](x: X, y: X)(implicit X: EuclideanDomain[X]) = X.mod(x, y)

  /** Returns the identity element of a type. */
  def id[X](implicit ev: HasIdentity[X]) = ev.id

  /** Returns the zero element (additive identity) of a type. */
  def zero[@sp(fdi) X](implicit ev: HasZero[X]) = ev.zero

  /** Returns the one element (multiplicative identity) of a type. */
  def one[@sp(fdi) X](implicit ev: HasOne[X]) = ev.one

  /** Returns the top element (maximum element) of a type. */
  def top[@sp(fdib) X](implicit ev: HasTop[X]) = ev.top

  /** Returns the bottom element (minimum element) of a type. */
  def bot[@sp(fdib) X](implicit ev: HasBottom[X]) = ev.bot

  /** Returns the multiplicative inverse of an element. */
  def inv[@sp(fdi) X](x: X)(implicit ev: MultiplicativeGroup[X]) = ev.inv(x)

  /** Returns the square of an element. */
  def sqr[@sp(fdi) X](x: X)(implicit ev: MultiplicativeSemigroup[X]) = ev.mul(x, x)

  /** Returns the cube of an element. */
  def cube[@sp(fdi) X](x: X)(implicit ev: MultiplicativeSemigroup[X]) = ev.mul(ev.mul(x, x), x)

  /** Returns the distance between two vectors in a metric space. */
  def dist[V, @sp(fdi) F](x: V, y: V)(implicit ev: MetricSpace[V, F]) = ev.dist(x, y)

  /** Returns the norm of a vector in a normed vector space. */
  def norm[V, @sp(fdi) F](x: V)(implicit ev: NormedVectorSpace[V, F]) = ev.norm(x)

  /** Returns the angle between two vectors in an inner product space. */
  def angle[V, @sp(fd) F](x: V, y: V)(implicit V: InnerProductSpace[V, F], F: TrigExpOps[F]) = V.angle(x, y)

  /** Returns the greatest common divisor of two elements. */
  def gcd[@sp(Int) X](x: X, y: X)(implicit ev: EuclideanDomain[X]) = ev.gcd(x, y)

  /** Returns the least common multiplier of two elements. */
  def lcm[@sp(Int) X](x: X, y: X)(implicit ev: EuclideanDomain[X]) = ev.lcm(x, y)

  /** Returns the supremum (join) of two elements in a lattice. */
  def sup[@sp(fdib) X](x: X, y: X)(implicit ev: UpperSemilattice[X]) = ev.sup(x, y)

  /** Returns the infimum (meet) of two elements in a lattice. */
  def inf[@sp(fdib) X](x: X, y: X)(implicit ev: LowerSemilattice[X]) = ev.inf(x, y)

  /** Returns the greater one of the two elements. */
  def max[@sp(fdib) X](x: X, y: X)(implicit ev: Order[X]) = ev.max(x, y)

  /** Returns the lesser one of the two elements. */
  def min[@sp(fdib) X](x: X, y: X)(implicit ev: Order[X]) = ev.min(x, y)

  /** Returns the greatest common divisor of the specified elements. */
  def gcd[@sp(Int) X](xs: X*)(implicit ev: EuclideanDomain[X]) = xs.reduce(ev.gcd)

  /** Returns the least common multiplier of the specified elements. */
  def lcm[@sp(Int) X](xs: X*)(implicit ev: EuclideanDomain[X]) = xs.reduce(ev.lcm)

  /** Returns the supremum (join) of the specified elements. */
  def sup[@sp(fdi) X](xs: X*)(implicit ev: UpperSemilattice[X]) = xs.reduce(ev.sup)

  /** Returns the infimum (meet) of the specified elements. */
  def inf[@sp(fdi) X](xs: X*)(implicit ev: LowerSemilattice[X]) = xs.reduce(ev.inf)

  /** Returns the maximum one of the specified elements. */
  def max[@sp(fdi) X](xs: X*)(implicit ev: Order[X]) = xs.reduce(ev.max[X])

  /** Returns the minimum one of the specified elements. */
  def min[@sp(fdi) X](xs: X*)(implicit ev: Order[X]) = xs.reduce(ev.min[X])

  def abs[@sp(fdi) X](x: X)(implicit ev: OrderedRing[X]) = ev.abs(x)

  def sgn[@sp(fdi) X](x: X)(implicit ev: OrderedRing[X]) = ev.sgn(x)

  def ipow[@sp(fdi) X](x: X, y: Int)(implicit ev: MultiplicativeSemigroup[X]) = ev.ipow(x, y)

  def pow[@sp(fd) X](x: X, y: X)(implicit ev: PowerOps[X]) = ev.pow(x, y)

  def sqrt[@sp(fd) X](x: X)(implicit ev: PowerOps[X]) = ev.sqrt(x)

  def cbrt[@sp(fd) X](x: X)(implicit ev: PowerOps[X]) = ev.cbrt(x)

  def sin[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.sin(x)

  def cos[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.cos(x)

  def tan[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.tan(x)

  def atan2[@sp(fd) X](y: X, x: X)(implicit ev: TrigExpOps[X]) = ev.atan2(y, x)

  def arcsin[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.arcsin(x)

  def arccos[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.arccos(x)

  def arctan[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.arctan(x)

  def sinh[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.sinh(x)

  def cosh[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.cosh(x)

  def tanh[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.tanh(x)

  def log[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.log(x)

  def log1p[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.log1p(x)

  def exp[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.exp(x)

  def expm1[@sp(fd) X](x: X)(implicit ev: TrigExpOps[X]) = ev.expm1(x)

  def e[@sp(fd) X](implicit ev: TrigExpOps[X]) = ev.e

  def pi[@sp(fd) X](implicit ev: TrigExpOps[X]) = ev.pi

  def succ[@sp(il) X](x: X)(implicit ev: SequentialOrder[X]) = ev.succ(x)

  def pred[@sp(il) X](x: X)(implicit ev: SequentialOrder[X]) = ev.pred(x)

}
