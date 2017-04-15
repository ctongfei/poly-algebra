package poly.algebra.conversion

import java.util._
import poly.algebra._

/**
 * @author Tongfei Chen
 */
object ToJava {

  implicit class orderAsJava[T](val o: Order[T]) extends AnyVal {
    def asJavaComparator: Comparator[T] = new Comparator[T] {
      def compare(o1: T, o2: T): Int = o.cmp(o1, o2)
    }
  }

}
