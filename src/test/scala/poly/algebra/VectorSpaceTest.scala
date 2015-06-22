package poly.algebra

import poly.algebra.ops._
import poly.algebra.functions._
import org.scalatest._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class VectorSpaceTest extends FunSuite {

  test("2D Point") {
    case class Point(x: Double, y: Double)
    implicit val vs1 = VectorSpace.create[Point, Double](
      (p1, p2) => Point(p1.x + p2.x, p1.y + p2.y),
      (k, p) => Point(k * p.x, k * p.y),
      Point(0, 0)
    )
    assert(Point(2, 3) + Point(4, 5) == Point(6, 8))
    assert(Point(5, 5) :* 2.0 == Point(10, 10))
    assert(zero[Point] == Point(0, 0))
  }

  test("Trivial") {
    implicit val vs2 = VectorSpace.trivial[Double]
    assert(3.0 :* 2.0 == 6.0)
    assert(4.0 *: 3.0 == 12.0)
  }


}
