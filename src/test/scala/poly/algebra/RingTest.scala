package poly.algebra

import org.scalatest._
import org.scalacheck._
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
import org.scalatest.prop._
import poly.algebra.implicits._
import poly.algebra.law._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class RingTest extends FunSuite with Checkers {

  test("Integer ring") {
    check(Law.ring[Int])

  }

  test("Int modular ring") {
    val Z5 = Ring.create[Int](
      (x: Int, y: Int) => (x + y) % 5,
      (x: Int, y: Int) => x * y % 5,
      0, 1, (x: Int) => 5 - x
    )
    assert(Z5.add(3, 4) == 2)
    assert(Z5.sumN(4, 8) == 2)
    assert(Z5.ipow(2, 10) == 4)
  }

  test("Boolean Ring") {
      val B = BooleanAlgebra[Boolean].asBooleanRing
      assert(B.negOne == true)
      assert(B.add(true, true) == false)
      assert(B.sub(false, true) == true)
      assert(B.mul(true, true) == true)
  }


}
