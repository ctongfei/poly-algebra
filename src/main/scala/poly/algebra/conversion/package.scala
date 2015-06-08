package poly.algebra

import scala.language.implicitConversions

/**
 * This object contains implicit converters that converts Scala typeclasses to Poly-algebra typeclasses.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object conversion {

  implicit def scalaEquivAsPoly[X](s: scala.Equiv[X]): Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = s.equiv(x, y)
  }

  implicit def scalaPartialOrderAsPoly[X](s: scala.PartialOrdering[X]): PartialOrder[X] = new PartialOrder[X] {
    def le(x: X, y: X) = s.lteq(x, y)
  }

  implicit def scalaOrderAsPoly[X](s: scala.Ordering[X]): WeakOrder[X] = new WeakOrder[X] {
    def cmp(x: X, y: X) = s.compare(x, y)
  }

  implicit def scalaNumericAsPoly[X](s: scala.Numeric[X]): Ring[X] with WeakOrder[X] =
    new Ring[X] with WeakOrder[X] {
      def zero = s.zero
      def one = s.one
      def add(x: X, y: X) = s.plus(x, y)
      def neg(x: X) = s.negate(x)
      override def sub(x: X, y: X) = s.minus(x, y)
      def mul(x: X, y: X) = s.times(x, y)
      def cmp(x: X, y: X) = s.compare(x, y)
  }

}
