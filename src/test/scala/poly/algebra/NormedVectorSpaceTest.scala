package poly.algebra

import org.scalatest._
import poly.algebra.ops._
import poly.algebra.implicits._
/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
class NormedVectorSpaceTest extends FunSuite {

  test("L3 Space") {
    val n = 3
    implicit val space = VectorSpace.create[Array[Double], Double](
      (x, y) => Array.tabulate(n)(i => x(i) + y(i)),
      (k, x) => Array.tabulate(n)(i => k * x(i)),
      Array.fill(3)(0.0)
    )
    implicit val l3space = NormedVectorSpace.create[Array[Double], Double](
      x => math.cbrt(x(0) ** 3 + x(1) ** 3 + x(2) ** 3)
    )
    //assert(norm(Array(1.0, 1.0, 1.0)) == 1.0)

  }

}
