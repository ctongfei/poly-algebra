import poly.algebra._
import poly.algebra.implicits._
import poly.algebra.function._
import poly.algebra.ops._
import java.time._

// TEST 1
val t = Instant.now()
val d = Duration.ofHours(1)

t :+ d
t :- d
d +: t
(t :+ d) :-: (t :- d)
d :* 3l
4l *: d

// TEST 2
case class Celsius(t: Double)
case class Kelvin(t: Double)
implicit object Kelvin extends VectorSpace[Kelvin, Double] {
  implicit def fieldOnScalar = Field[Double]
  def scale(k: Double, x: Kelvin): Kelvin = Kelvin(x.t * k)
  def zero: Kelvin = Kelvin(0)
  def add(x: Kelvin, y: Kelvin): Kelvin = Kelvin(x.t + y.t)
}
implicit object Celsius extends AffineSpace[Celsius, Kelvin, Double] {
  def vectorSpaceOnVector = Kelvin
  def sub(x: Celsius, y: Celsius) = Kelvin(x.t - y.t)
  def translate(k: Kelvin, x: Celsius): Celsius = Celsius(k.t + x.t)
}
val room = Celsius(25)
val c = Kelvin(10)
room :+ c
room :- c
c + c + c + c - c + c
c :* 2
2.0 *: c
(room :+ c) :-: (room :- c)
