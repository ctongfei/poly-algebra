package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a Euclidean domain. 
 * @author Tongfei Chen
 */
trait EuclideanDomain[@sp(fdi) X] extends CRing[X] with OrderedRing[X] {

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
  }

}

object EuclideanDomain extends ImplicitGetter[EuclideanDomain] {
  def create[@sp(fdi) X](fQuot: (X, X) => X, fMod: (X, X) => X)(implicit X: OrderedRing[X]): EuclideanDomain[X] =
    new EuclideanDomain[X] {
      def cmp(x: X, y: X) = X.cmp(x, y)
      def add(x: X, y: X): X = X.add(x, y)
      def mul(x: X, y: X): X = X.mul(x, y)
      def neg(x: X): X = X.neg(x)
      def zero: X = X.zero
      def one: X = X.one
      def div(x: X, y: X): X = fQuot(x, y)
      def mod(x: X, y: X): X = fMod(x, y)
    }

}
