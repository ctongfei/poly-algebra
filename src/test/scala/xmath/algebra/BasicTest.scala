package xmath.algebra
import org.scalatest._
/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class BasicTest extends FunSuite {

  test("Integer ring") {
    val Z = Ring[Int]
    assert(Z.add(30, 20) == 50)
    assert(Z.sub(40, 10) == 30)
    assert(Z.sumN(2, 10) == 20)
    assert(Z.ipow(2, 10) == 1024)
    assert(Z.ipow(3, 0) == 1)
  }



}
