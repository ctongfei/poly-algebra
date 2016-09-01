package poly.algebra

import poly.algebra.function._
import poly.algebra.implicits._
import poly.algebra.ops._
import org.scalatest._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class VectorSpaceTest extends FunSuite {

  test("2D Point") {
    case class Point(x: Double, y: Double)
    implicit val vs1 = new VectorSpace[Point, Double] {
      def add(p1: Point, p2: Point) = Point(p1.x + p2.x, p1.y + p2.y)
      def scale(p: Point, k: Double) = Point(k * p.x, k * p.y)
      def zero = Point(0, 0)
      def scalarField = Field[Double]
    }
    assert(Point(2, 3) + Point(4, 5) == Point(6, 8))
    assert(Point(4, 4) - Point(1, 2) == Point(3, 2))
    assert(Point(5, 5) :* 2.0 == Point(10, 10))
    assert(2.0 *: Point(4, 4) == Point(8, 8))
    assert(zero[Point] == Point(0, 0))
  }

  test("Trivial") {
    implicit val vs2 = VectorSpace.trivial[Double]
    assert(3.0 * 2.0 == 6.0)
    assert(4.0 * 3.0 == 12.0)
  }



}
