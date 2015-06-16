package poly.algebra

import org.scalatest._
import poly.algebra.ops._
/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class RefOpsTest extends FunSuite {

  test("Reference equality tests") {
    val a = Seq(1)
    val b = a
    val c = Seq(1)
    assert(a =@= b)
    assert(a === b)
    assert(a !@= c)
    assert(a === c)
  }

}
