package poly.algebra

import org.scalatest._
import poly.algebra.implicits._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class SemigroupTest extends FunSuite {

  test("Semigroups") {
    val S1 = UpperSemilattice[Int].asSemigroupWithSup // (Z, max)
    assert(S1.op(2, 4) == 4)
    assert(S1.combineN(10, 10) == 10)

    val S2 = MultiplicativeSemigroup[Int].asSemigroupWithMul // (Z, *)
    assert(S2.op(3, 5) == 15)
    assert(S2.combineN(5, 4) == 625)
  }

}
