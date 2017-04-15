package poly.algebra.conversion

import poly.algebra._
import scala.language.implicitConversions

/**
 * This object contains implicit converters that converts Java strategy objects to Poly-algebra typeclasses.
 * @author Tongfei Chen
 * @since 0.2.11
 */
object ImplicitlyFromJava {

  implicit def javaComparatorAsPoly[X](j: java.util.Comparator[X]) = new JavaComparatorAsPoly(j)

}
