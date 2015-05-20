package poly.algebra

import org.scalatest._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class RingTest extends FunSuite {

  test("Int ring") {
    val Z = Ring[Int]
    assert(Z.add(30, 20) == 50)
    assert(Z.sub(40, 10) == 30)
    assert(Z.sumN(2, 10) == 20)
    assert(Z.ipow(2, 10) == 1024)
    assert(Z.ipow(3, 0) == 1)
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
  }


}
