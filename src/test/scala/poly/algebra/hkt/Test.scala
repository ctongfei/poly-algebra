package poly.algebra.hkt

import poly.algebra._
import poly.algebra.syntax._
import poly.algebra.hkt.syntax._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object Test extends App {

  val f = (x: Int) => 1 + x

  val g = for {
    a ← (x: Int) => x * 2
    b ← (x: Int) => x + 10
  } yield a + b


  println(g(3))

  val f1 = (x: Int) => Array.fill(x)(x)
  val f2 = (x: Int) => Array.fill(x)(0)


  val StringByLength = Order[Int] contramap ((f: String) => f.length)

  println(StringByLength.cmp("ABC", "DEFG"))
  println(StringByLength.cmp("ABCEFG", "DEFG"))
  println(StringByLength.cmp("ABC", "DEF"))


  val Option = Functor[Option]

  val Function = Arrow[Function]

  val bp = 0

}

