package poly.algebra

/**
 * Brings typeclass instances of default algebraic structures on system types into scope.
 * @author Tongfei Chen
 */
object implicits {

  implicit final val IntStructure = std.IntStructure
  implicit final val LongStructure = std.LongStructure
  implicit final val FloatStructure = std.FloatStructure
  implicit final val DoubleStructure = std.DoubleStructure
  implicit final val BooleanStructure = std.BooleanStructure
  implicit final val StringStructure = std.StringStructure

  implicit final val InstantStructure = std.InstantStructure
  implicit final val DurationStructure = std.DurationStructure

}
