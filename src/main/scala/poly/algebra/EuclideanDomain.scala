package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait EuclideanDomain[@sp(fdi) X] extends CRing[X] {

  /** Returns the quotient (Euclidean division) of two elements. */
  def div(x: X, y: X): X

  /** Returns the modulus of two elements. */
  def mod(x: X, y: X): X

  /** Simultaneously returns the quotient and the modulus of two elements. For performance, this function should be overridden. */
  def quotMod(x: X, y: X) : (X, X) = (div(x, y), mod(x, y))

  /** Computes the greatest common divisor of two elements using Euclidean algorithm. */
  def gcd(x: X, y: X): X = {
    var m = x
    var n = y
    while (n != zero) {
      val t = mod(m, n)
      m = n
      n = t
    }
    m
  }

  /** Computes the least common multiplier of two elements. */
  def lcm(x: X, y: X): X = div(mul(x, y), gcd(x, y))

  /** Casts this Euclidean domain as a lattice with `gcd` as its `inf` operator and `lcm` as its `sup` operator. */
  def asLatticeWithGcdLcm: Lattice[X] = new Lattice[X] {
    def sup(x: X, y: X) = lcm(x, y)
    def inf(x: X, y: X) = gcd(x, y)
    override def le(x: X, y: X) = mod(y, x) == zero
  }

}

object EuclideanDomain extends ImplicitGetter[EuclideanDomain] {
  def create[@sp(fdi) X](fQuot: (X, X) => X, fMod: (X, X) => X)(implicit R: Ring[X]): EuclideanDomain[X] =
    new EuclideanDomain[X] {
      def add(x: X, y: X): X = R.add(x, y)
      def mul(x: X, y: X): X = R.mul(x, y)
      def neg(x: X): X = R.neg(x)
      def zero: X = R.zero
      def one: X = R.one
      def div(x: X, y: X): X = fQuot(x, y)
      def mod(x: X, y: X): X = fMod(x, y)
    }

}
