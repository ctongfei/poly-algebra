package poly.algebra

import org.scalatest._
import poly.algebra.syntax._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class MetricSpaceTest extends FunSuite {

  test("MetricSpace 1: Absolute value on integers") {
    assert(dist(2, 3) == 1)
    assert(dist(-4, 3) == 7)
    assert(dist(0, -5) == 5)
  }

  test("MetricSpace 2: Hamming distance on Boolean sequences") {
    implicit val ms2 = MetricSpace.create[Array[Boolean], Int](
      (x, y) => Sum(x.length)(i => if (x(i) ^ y(i)) 1 else 0)
    )
    val a = Array(true, true, true, false)
    val b = Array(false, true, true, true)
    val c = Array(false, false, false, false)
    assert(dist(a, b) == 2)
    assert(dist(b, c) == 3)
    assert(dist(a, c) == 3)
  }

  test("MetricSpace 3: Levenshtein edit distance on strings") {
    implicit val ms3 = MetricSpace.create[String, Int](
      (x, y) => {
        val d = Array.ofDim[Int](x.length + 1, y.length + 1)
        for (i ← 0 to x.length) d(i)(0) = i
        for (j ← 0 to y.length) d(0)(j) = j
        for (j ← 1 to y.length; i ← 1 to x.length) {
          if (x(i - 1) == y(j - 1)) d(i)(j) = d(i - 1)(j - 1)
          else d(i)(j) = min(d(i - 1)(j), d(i)(j - 1), d(i - 1)(j - 1)) + 1
        }
        d(x.length)(y.length)
      }
    )
    assert(dist("kitten", "sitting") == 3)
    assert(dist("Saturday", "Sunday") == 3)
    assert(dist("ACGTACGT", "CGTACGG") == 2)
  }

}
