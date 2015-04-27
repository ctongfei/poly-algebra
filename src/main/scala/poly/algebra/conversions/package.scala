package poly.algebra

/**
 * This object contains implicit converters that converts Scala typeclasses to Poly-algebra typeclasses.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object conversions {

  implicit def scalaEquivAsPoly[X](s: scala.Equiv[X]): Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = s.equiv(x, y)
  }

  implicit def scalaPartialOrderAsPoly[X](s: scala.PartialOrdering[X]): PartialOrder[X] = new PartialOrder[X] {
    def le(x: X, y: X) = s.lteq(x, y)
  }

  implicit def scalaOrderAsPoly[X](s: scala.Ordering[X]): WeakOrder[X] = new WeakOrder[X] {
    def cmp(x: X, y: X) = s.compare(x, y)
  }

}
