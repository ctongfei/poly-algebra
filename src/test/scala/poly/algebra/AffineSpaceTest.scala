package poly.algebra

import org.scalatest._
import poly.algebra.implicits._
import poly.algebra.ops._
import java.time._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class AffineSpaceTest extends FunSuite {

  test("Affine space on instants") {
    val t = Instant.now()
    val d = Duration.ofHours(1)
    val d2 = d + d
    val t1 = d + t
    val t2 = t - d
    assert(t1 - t2 == d2)
  }

}
