package poly.algebra

/**
  * An object for all-in-one importing experience.
  * Once imported this `syntax` object,
  * `implicits` / `ops` / `functions` should '''NO LONGER''' be imported.
  * @author Tongfei Chen
  * @since 0.2.15
  */
object syntax extends function.GenericFunctions with function.MapReduceMathOps with Priority1Implicits {

  implicit final val IntStructure = std.IntStructure
  implicit final val LongStructure = std.LongStructure
  implicit final val FloatStructure = std.FloatStructure
  implicit final val DoubleStructure = std.DoubleStructure
  implicit final val BooleanStructure = std.BooleanStructure
  implicit final val StringStructure = std.StringStructure

  implicit final val InstantStructure = std.InstantStructure
  implicit final val DurationStructure = std.DurationStructure
  
}