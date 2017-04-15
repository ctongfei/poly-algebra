package poly.algebra.conversion

import poly.algebra._

/**
 * @author Tongfei Chen
 */

class JavaComparatorAsPoly[X](j: java.util.Comparator[X]) extends Order[X] {
  def cmp(x: X, y: X) = j.compare(x, y)
}

class ScalaEquivAsPoly[X](s: scala.Equiv[X]) extends Eq[X] {
  def eq(x: X, y: X) = s.equiv(x, y)
}

class ScalaPartialOrderingAsPoly[X](s: scala.PartialOrdering[X]) extends PartialOrder[X] {
  def le(x: X, y: X) = s.lteq(x, y)
}

class ScalaOrderingAsPoly[X](s: scala.Ordering[X]) extends Order[X] {
  def cmp(x: X, y: X) = s.compare(x, y)
}

class ScalaNumericAsPoly[X](s: scala.Numeric[X]) extends OrderedRing[X] {
  def zero = s.zero
  def one = s.one
  def add(x: X, y: X) = s.plus(x, y)
  def neg(x: X) = s.negate(x)
  override def sub(x: X, y: X) = s.minus(x, y)
  def mul(x: X, y: X) = s.times(x, y)
  def cmp(x: X, y: X) = s.compare(x, y)
}

class ScalaIntegralAsPoly[X](s: scala.Integral[X]) extends EuclideanDomain[X] {
  def zero = s.zero
  def one = s.one

  def add(x: X, y: X) = s.plus(x, y)
  override def sub(x: X, y: X) = s.minus(x, y)
  def neg(x: X) = s.negate(x)
  def mul(x: X, y: X) = s.times(x, y)
  def div(x: X, y: X) = s.quot(x, y)
  def mod(x: X, y: X) = s.rem(x, y)

  def cmp(x: X, y: X) = s.compare(x, y)
}

class ScalaFractionalAsPoly[X](s: scala.Fractional[X]) extends OrderedField[X] {
  def zero = s.zero
  def one = s.one

  def add(x: X, y: X) = s.plus(x, y)
  override def sub(x: X, y: X) = s.minus(x, y)
  def neg(x: X) = s.negate(x)
  def mul(x: X, y: X) = s.times(x, y)
  def inv(x: X) = s.div(s.one, x)
  override def div(x: X, y: X) = s.div(x, y)

  def cmp(x: X, y: X) = s.compare(x, y)
}

class ScalaHashingAsPoly[X](s: scala.util.hashing.Hashing[X]) extends Hashing[X] {
  def hash(x: X) = s.hash(x)
  def eq(x: X, y: X) = x equals y
}
