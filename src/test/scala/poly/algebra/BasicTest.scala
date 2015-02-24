package poly.algebra
import org.scalatest._
/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class BasicTest extends FunSuite {

  test("Int ring") {
    val Z = Ring[Int]
    assert(Z.add(30, 20) == 50)
    assert(Z.sub(40, 10) == 30)
    assert(Z.sumN(2, 10) == 20)
    assert(Z.ipow(2, 10) == 1024)
    assert(Z.ipow(3, 0) == 1)
  }

    test("Int modular ring") {
      val Z5 = Ring.create[Int]((x, y) => (x + y) % 5, (x, y) => (x * y) % 5, 0, 1, 5 - _)
      assert(Z5.add(3, 4) == 2)
      assert(Z5.sumN(4, 8) == 2)
      assert(Z5.ipow(2, 10) == 4)
    }

  test("Double field") {
    val R = Field[Double]
    assert(R.ipow(2.0, 20) == 1048576.0)
    assert(R.sumN(3.0, 100) == 300.0)
    assert(R.div(20.0, 10.0) == 2.0)
  }

  test("Int GCD/LCM lattice") {
    val L = EuclideanDomain[Int].latticeWithGcdLcm
    assert(L.inf(60, 72) == 12)
    assert(L.inf(128, 243) == 1)
    assert(L.sup(12, 18) == 36)

  }
}
