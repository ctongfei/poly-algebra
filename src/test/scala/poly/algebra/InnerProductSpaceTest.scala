package poly.algebra

import org.scalatest._
import poly.algebra.functions._
import poly.algebra.ops._
/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class InnerProductSpaceTest extends FunSuite {

  test("L2 space") {

    case class Point(x: Double, y: Double)

    implicit val L2 = new InnerProductSpace[Point, Double] {
      def dot(x: Point, y: Point): Double = x.x * y.x + x.y * y.y
      def powerOpsOfScalar: PowerOps[Double] = std.DoubleStructure
      def fieldOfScalar: Field[Double] = std.DoubleStructure
      def trigExpOpsOfScalar = std.DoubleStructure
      def scale(x: Point, k: Double): Point = Point(x.x * k, x.y * k)
      def zero: Point = Point(0.0, 0.0)
      def add(x: Point, y: Point): Point = Point(x.x + y.x, x.y + y.y)
    }

    val a = Point(1, 1)
    val b = Point(-1, 1)
    val c = Point(5, 6)
    assert((a dot b) == 0)
    assert((a dot c) == 11)
    assert((b dot c) == 1)
  }

  test("Reproducing kernel Hilbert space") {

  }

}
