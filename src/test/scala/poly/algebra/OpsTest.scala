package poly.algebra

import poly.algebra.syntax._
import poly.algebra.specgroup._

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

  def timesTwo[@sp(fd) R: IsReal](x: R): R = {
    x * generic[R](2.0)
  }

  def times[@sp(fd) R: IsReal](x: R, y: R): R = x * y

  val a = Order[Int]

  val bp = 0

}
