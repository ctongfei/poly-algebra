package poly.algebra.hkt

import org.scalatest._
import poly.algebra.hkt.syntax._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class OpsTest extends FunSuite {

  test("HKT operators on functions (<<<, >>>)") {
    val f = (x: Int) => x * 2
    val g = (x: Int) => x + 3
    val fg = f <<< g
    val gf = f >>> g
    assert(fg(1) == 8)
    assert(gf(1) == 5)
  }

  test("HKT operators on Kleisli arrows (<=<, >=>)") {
    val f = (x: Int) => Seq.fill(x)(x)
    val g = (x: Int) => Seq.iterate(x, 10)(2 * _)
    val fg = f <=< g
    val gf = f >=> g

    val fg1 = for { i ← Seq(1); j ← g(i); k ← f(j) } yield k
    val gf1 = for { i ← Seq(1); j ← f(i); k ← g(j) } yield k

    assert(fg(1) == fg1)
    assert(gf(1) == gf1)
  }



}
