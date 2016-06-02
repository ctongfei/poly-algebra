package poly.algebra.eq

import poly.algebra._
import poly.algebra.syntax._

/**
 * Specialization checks:
 *   For Eq/Hashing All AnyVal types should be specialized;
 *   For function/tuples types classes should be specialized according to the Scala source code listed below.
 * @see Scala source code: {{{
 *   trait Function0[@specialized(Specializable.Primitives) +R]
 *   trait Function1[@specialized(Int, Long, Float, Double) -T1, @specialized(Unit, Boolean, Int, Float, Long, Double) +R]
 *   trait Function2[@specialized(Int, Long, Double) -T1, @specialized(Int, Long, Double) -T2, @specialized(Unit, Boolean, Int, Float, Long, Double) +R]
 *   case class Tuple1[@specialized(Int, Long, Double) +T1]
 *   case class Tuple2[@specialized(Int, Long, Double, Char, Boolean/*, AnyRef*/) +T1, @specialized(Int, Long, Double, Char, Boolean/*, AnyRef*/) +T2]
 * }}}
 * @author Tongfei Chen
 */
object SpecializationTest extends App {

  val eqI = Eq[Int]

  val eqII = Eq[(Int, Int)] // check: type should be HashingT$Tuple2Hashing$mcII$sp

  val bp = 0

}
