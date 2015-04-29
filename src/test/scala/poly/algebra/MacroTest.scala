package poly.algebra

import poly.algebra.functions._
import org.scalatest._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class MacroTest extends FunSuite {

  test("sum") {
    val s = sum(100)(i => i * i)
    print(s)
  }

}
