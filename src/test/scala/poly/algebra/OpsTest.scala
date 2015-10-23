package poly.algebra

import poly.algebra.ops._
import poly.algebra.implicits._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object OpsTest extends App {

  class Prob(val logProb: Double) extends AnyVal

  implicit object Prob extends Semiring[Prob] {
    def add(x: Prob, y: Prob) = new Prob(logProb = math.log(math.exp(x.logProb) + math.exp(y.logProb)))
    def zero = new Prob(logProb = Double.NegativeInfinity)
    def mul(x: Prob, y: Prob) = new Prob(logProb = x.logProb + y.logProb)
    def one = new Prob(logProb = 0.0)
  }

  val p0 = new Prob(-1.0)
  val p2 = new Prob(-3.0)
  val p3 = p0 * p2

  val bp = 0

}
