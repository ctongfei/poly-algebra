package poly.algebra

/**
 * Brings typeclass instances of default algebraic structures on system types into scope.
 *
 * @author Tongfei Chen
 */
object implicits extends ImplicitStructures

trait ImplicitStructures extends LowerPriorityImplicitStructures {

  implicit final val IntIntHashing = IntHashing.default[Int]
  implicit final val DoubleHashing = IntHashing.default[Double]
  implicit final val FloatHashing = IntHashing.default[Float]
  implicit final val BooleanHashing = IntHashing.default[Boolean]
  implicit final val LongHashing = IntHashing.default[Long]
  implicit final val CharHashing = IntHashing.default[Char]
  implicit final val StringHashing = IntHashing.default[String]
  
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
