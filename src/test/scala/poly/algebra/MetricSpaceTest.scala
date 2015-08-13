package poly.algebra

import org.scalatest._
import poly.algebra.functions._
import poly.algebra.implicits._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class MetricSpaceTest extends FunSuite {

  test("MetricSpace 1: Absolute value on integers") {
    val ms1 = MetricSpace.create[Int, Int](
      (x, y) => math.abs(x - y)
    )
    assert(ms1.dist(2, 3) == 1)
    assert(ms1.dist(-4, 3) == 7)
    assert(ms1.dist(0, -5) == 5)
  }

  test("MetricSpace 2: Hamming distance on Boolean sequences") {
    val ms2 = MetricSpace.create[Array[Boolean], Int](
      (x, y) => Sum(x.length)(i => if (x(i) ^ y(i)) 1 else 0)
    )
    val a = Array(true, true, true, false)
    val b = Array(false, true, true, true)
    val c = Array(false, false, false, false)
    assert(ms2.dist(a, b) == 2)
    assert(ms2.dist(b, c) == 3)
    assert(ms2.dist(a, c) == 3)
  }

  test("MetricSpace 3: Levenshtein edit distance on strings") {
    val ms3 = MetricSpace.create[String, Int](
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
    assert(ms3.dist("kitten", "sitting") == 3)
    assert(ms3.dist("Saturday", "Sunday") == 3)
    assert(ms3.dist("ACGTACGT", "CGTACGG") == 2)
  }

}
