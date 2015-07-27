package poly.algebra

import org.scalatest._
import poly.algebra.implicits._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class TestAlgebraicStructures extends FunSuite {

  import ops._

  test("Boolean algebra and Boolean ring") {
    val B = BooleanAlgebra[Boolean]
    assert(B.and(true, false) == false)
    assert(B.nand(true, true) == false)
    assert(B.xor(true, false) == true)
    assert(B.xor(true, true) == false)
    assert(B.le(false, true))
    assert(B.ge(true, false))
    assert(B.le(true, true))
    assert(B.or(true, false) == true)
    assert(B.nor(true, false) == false)
  }

  test("Double field") {
    val R = Field[Double]
    assert(R.ipow(2.0, 10) == 1024.0)
    assert(R.sumN(3.0, 100) == 300.0)
    assert(R.div(20.0, 10.0) == 2.0)
  }


  test("Int GCD/LCM lattice") {
    val L = EuclideanDomain[Int].asLatticeWithGcdLcm
    assert(L.inf(60, 72) == 12)
    assert(L.inf(128, 243) == 1)
    assert(L.sup(12, 18) == 36)
  }

  test("Endofunction space") {
    val S = EndofunctionSpace.default[Double, Double]
    val f = S.composeN((x: Double) => x * x, 4)
    assert(f(2) == 65536)
  }

}
