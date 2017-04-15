package poly.algebra.conversion

/**
 * @author Tongfei Chen
 */
object FromJava {

  implicit class javaComparatorAsPoly[X](val j: java.util.Comparator[X]) extends AnyVal {
    def asPoly = new JavaComparatorAsPoly(j)
  }

}
