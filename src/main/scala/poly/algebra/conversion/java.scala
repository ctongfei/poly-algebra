package poly.algebra.conversion

import poly.algebra._
import scala.language.implicitConversions

/**
 * This object contains implicit converters that converts Java strategy objects to Poly-algebra typeclasses.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.11
 */
object Java {

  implicit def javaComparatorAsPoly[X](s: java.util.Comparator[X]): WeakOrder[X] = new WeakOrder[X] {
    def cmp(x: X, y: X) = s.compare(x, y)
  }

}
