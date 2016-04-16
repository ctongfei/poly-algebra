package poly.algebra

import poly.algebra.std._

/**
 * Brings typeclass instances of default algebraic structures on system types into scope.
 *
 * @author Tongfei Chen
 */
object implicits extends ImplicitStructures

trait ImplicitStructures extends LowerPriorityImplicitStructures {

  implicit object IntHashing extends DefaultHashing[Int]
  implicit object DoubleHashing extends DefaultHashing[Double]
  implicit object FloatHashing extends DefaultHashing[Float]
  implicit object BooleanHashing extends DefaultHashing[Boolean]
  implicit object LongHashing extends DefaultHashing[Long]
  implicit object CharHashing extends DefaultHashing[Char]

}

trait LowerPriorityImplicitStructures {

  implicit final val IntStructure = std.IntStructure
  implicit final val LongStructure = std.LongStructure
  implicit final val FloatStructure = std.FloatStructure
  implicit final val DoubleStructure = std.DoubleStructure
  implicit final val BooleanStructure = std.BooleanStructure
  implicit final val StringStructure = std.StringStructure

  implicit final val InstantStructure = std.InstantStructure
  implicit final val DurationStructure = std.DurationStructure

}