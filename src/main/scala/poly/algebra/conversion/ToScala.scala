package poly.algebra.conversion

import poly.{algebra => pa}
import scala.{math => sm}
import scala.util.{hashing => suh}

/**
  * Contains extension methods that converts Poly-algebra typeclasses to Scala typeclasses.
  *
  * @author Tongfei Chen
  * @since 0.2.18
  */
object ToScala {

  implicit class EquivToScalaOps[X](val p: pa.Equiv[X]) extends AnyVal {

    def asScalaEquiv: sm.Equiv[X] = new Equiv[X] {
      def equiv(x: X, y: X) = p.eq(x, y)
    }

  }

  implicit class PartialOrderToScalaOps[X](val p: pa.PartialOrder[X]) extends AnyVal {

    def asScalaPartialOrdering: sm.PartialOrdering[X] = new PartialOrdering[X] {
      def tryCompare(x: X, y: X) = {
        if (p.lt(x, y)) Some(-1)
        else if (p.gt(x, y)) Some(1)
        else if (p.eq(x, y)) Some(0)
        else None
      }
      def lteq(x: X, y: X) = p.le(x, y)
    }

  }


  implicit class WeakOrderToScalaOps[X](val p: pa.WeakOrder[X]) extends AnyVal {

    def asScalaOrdering: sm.Ordering[X] = new Ordering[X] {
      def compare(x: X, y: X) = p.cmp(x, y)
    }

  }

  implicit class IntHashingToScalaOps[X](val p: pa.IntHashing[X]) extends AnyVal {

    def asScalaHashing: suh.Hashing[X] = new suh.Hashing[X] {
      def hash(x: X) = p.hash(x)
    }

  }
}
