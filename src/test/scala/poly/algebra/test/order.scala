package poly.algebra.test

import org.scalatest._
import poly.algebra._
import poly.algebra.syntax._

/**
 * Tests on [[PartialOrder]]s, [[Order]]s, [[SequentialOrder]]s, [[UpperSemilattice]]s, [[LowerSemilattice]]s,
 * [[Lattice]]s and their WithEq & Bounded variants.
 * @author Tongfei Chen
 * @since 0.4.0
 */
class OrderTest extends FunSuite {

  test("Partial order on Integers") {
    implicit val po0 = PartialOrder[Int]
    assert(po0.le(0, 0))
    assert(po0.le(0, 5))
    assert(!po0.le(3, 2))

    assert(po0.ge(1, 0))
    assert(po0.ge(2, 2))
    assert(!po0.ge(-4, -1))

    assert(!po0.lt(0, 0))
    assert(!po0.gt(5, 5))

    assert(po0.eq(7, 7))
    assert(po0.ne(8, 9))
  }

  test("Product partial orders") {
    implicit val po1 = PartialOrder[Int] product PartialOrder[Int]
    assert((0, 1) <= (1, 2))
    assert((1, 0) <= (1, 4))
    assert((2, 4) <= (3, 4))
    assert(!((2, 5) <= (1, 6)))

    assert((1, 0) >= (1, 0))
    assert((2, 0) >= (1, -1))

    assert((3, 3) === (3, 3))
    assert((2, 3) !== (2, 4))
  }

  test("Reversed & Contramapped partial orders") {
    case class Test(x: Int)
    implicit val po2 = PartialOrder.by{x: Test => x.x}.reverse
    assert(Test(3) >= Test(4))
    assert(Test(5) >= Test(5))
    assert(Test(10) <= Test(9))
    assert(!(Test(2) <= Test(3)))
    assert(po2.eq(Test(1), Test(1)))
  }

  test("Upper semilattices") {

  }

  test("Lower semilattices") {

  }

  test("Lattices") {

  }

  test("Orders") {

  }

  test("Sequential orders") {

  }

}
