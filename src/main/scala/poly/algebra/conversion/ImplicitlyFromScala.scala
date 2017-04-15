package poly.algebra.conversion

import poly.algebra._
import scala.language.implicitConversions

/**
 * This package contains implicit converters that converts Scala math typeclasses to Poly-algebra typeclasses.
 * @author Tongfei Chen
 */
object ImplicitlyFromScala {

  implicit def scalaEquivAsPoly[X](s: scala.Equiv[X]): Eq[X] = new ScalaEquivAsPoly(s)
  implicit def scalaPartialOrderAsPoly[X](s: scala.PartialOrdering[X]): PartialOrder[X] = new ScalaPartialOrderingAsPoly(s)
  implicit def scalaOrderAsPoly[X](s: scala.Ordering[X]): Order[X] = new ScalaOrderingAsPoly(s)
  implicit def scalaNumericAsPoly[X](s: scala.Numeric[X]): OrderedRing[X] = new ScalaNumericAsPoly(s)
  implicit def scalaIntegralAsPoly[X](s: scala.Integral[X]): EuclideanDomain[X] = new ScalaIntegralAsPoly(s)
  implicit def scalaFractionalAsPoly[X](s: scala.Fractional[X]): OrderedField[X] = new ScalaFractionalAsPoly(s)
  implicit def scalaHashingAsPoly[X](s: scala.util.hashing.Hashing[X]): Hashing[X] = new ScalaHashingAsPoly(s)

}
