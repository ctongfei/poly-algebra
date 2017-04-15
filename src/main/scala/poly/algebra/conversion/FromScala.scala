package poly.algebra.conversion

import poly.algebra._

/**
 * @author Tongfei Chen
 */
object FromScala {

  implicit class scalaEquivAsPoly[X](s: scala.Equiv[X]) extends AnyVal {
    def asPoly: Eq[X] = new ScalaEquivAsPoly(s)
  }
  implicit class scalaPartialOrderAsPoly[X](val s: scala.PartialOrdering[X]) extends AnyVal {
    def asPoly: PartialOrder[X] = new ScalaPartialOrderingAsPoly(s)
  }
  implicit class scalaOrderAsPoly[X](val s: scala.Ordering[X]) extends AnyVal {
    def asPoly: Order[X] = new ScalaOrderingAsPoly(s)
  }
  implicit class scalaNumericAsPoly[X](val s: scala.Numeric[X]) extends AnyVal {
    def asPoly: OrderedRing[X] = new ScalaNumericAsPoly(s)
  }
  implicit class scalaIntegralAsPoly[X](val s: scala.Integral[X]) extends AnyVal {
    def asPoly: EuclideanDomain[X] = new ScalaIntegralAsPoly(s)
  }
  implicit class scalaFractionalAsPoly[X](val s: scala.Fractional[X]) extends AnyVal {
    def asPoly: OrderedField[X] = new ScalaFractionalAsPoly(s)
  }
  implicit class scalaHashingAsPoly[X](val s: scala.util.hashing.Hashing[X]) extends AnyVal {
    def asPoly: Hashing[X] = new ScalaHashingAsPoly(s)
  }

}
