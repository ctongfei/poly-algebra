package poly.algebra.conversion

import poly.algebra._
import scala.language.implicitConversions

/**
 * This package contains implicit converters that converts Scala math typeclasses to Poly-algebra typeclasses.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object FromScala {

  implicit def scalaEquivAsPoly[X](s: scala.Equiv[X]): Equiv[X] = new Equiv[X] {
    def eq(x: X, y: X): Boolean = s.equiv(x, y)
  }

  implicit def scalaPartialOrderAsPoly[X](s: scala.PartialOrdering[X]): PartialOrder[X] = new PartialOrder[X] {
    def le(x: X, y: X) = s.lteq(x, y)
  }

  implicit def scalaOrderAsPoly[X](s: scala.Ordering[X]): WeakOrder[X] = new WeakOrder[X] {
    def cmp(x: X, y: X) = s.compare(x, y)
  }

  implicit def scalaNumericAsPoly[X](s: scala.Numeric[X]): OrderedRing[X] = new OrderedRing[X] {
    def zero = s.zero
    def one = s.one

    def add(x: X, y: X) = s.plus(x, y)
    def neg(x: X) = s.negate(x)
    override def sub(x: X, y: X) = s.minus(x, y)
    def mul(x: X, y: X) = s.times(x, y)

    def cmp(x: X, y: X) = s.compare(x, y)
  }

  implicit def scalaIntegralAsPoly[X](s: scala.Integral[X]): EuclideanDomain[X] = new EuclideanDomain[X] {
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

  implicit def scalaFractionalAsPoly[X](s: scala.Fractional[X]): OrderedField[X] = new OrderedField[X] {
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

  implicit def scalaHashingAsPoly[X](s: scala.util.hashing.Hashing[X]): IntHashing[X] = new IntHashing[X] {
    def hash(x: X) = s.hash(x)
    def eq(x: X, y: X) = x equals y
  }

}
