package poly.algebra.test

import poly.algebra._
import poly.algebra.syntax._
import org.scalatest._

/**
 * Contains tests for [[Semigroup]], [[Monoid]] and [[Group]] classes, as well as their Additive/Multiplicative
 * counterparts.
 * @author Tongfei Chen
 * @since 0.4.0
 */
class GroupTest extends FunSuite {

  test("Semigroups") {
    implicit val sg0 = Semigroup.create[Int => Int](_ compose _)

    val f = { x: Int => x * 2 }
    val g = { x: Int => x * x }
    assert(f(1) == 2)
    assert((f <> f)(1) == 4)
    assert((f <> g)(2) == 8)
    assert((f <> f <> f <> f)(1) == 16)
    assert(sg0.combineN(g, 4)(2) == 65536)
    assert(sg0.combineN(f, 3)(3) == 24)
    intercept[IllegalArgumentException] {
      sg0.combineN(f, 0)(0)
    }
  }

  test("Product semigroup") {
    val sg11 = Ring[Int].asSemigroupWithAdd
    val sg12 = Semigroup.create[String]((a, b) => a ++ b)
    implicit val sg1 = sg11 product sg12
    assert(((1, "a") <> (2, "b")) == (3, "ab"))
    assert(((3, "aa") <> (-1, "")) == (2, "aa"))
  }

  test("Monoids") {
    implicit val m0 = Monoid.create[String](_ + _, "")
    assert(m0.id == "")
    assert(("ab" <> "cd") == "abcd")
    assert(m0.combineN("a", 6) == "aaaaaa")
  }

  test("Product monoid") {
    implicit val m1 = Ring[Int].asMonoidWithAdd product Field[Double].asMonoidWithMul
    val a = (1, 2.2)
    val b = (2, 0.5)
    val c = (1, 2.0)
    assert((a <> b) == (3, 1.1))
    assert(m1.combineN(c, 5) == (5, 32.0))
    assert(m1.combineN(c, 0) == (0, 1.0))
  }

  test("Group") {
    val g0 = AdditiveGroup[Int].asGroupWithAdd

  }

}
