package poly.algebra

import poly.algebra.function._

/**
  * An object for all-in-one importing experience.
  * Once imported this `syntax` object,
  * `implicits` / `ops` / `functions` should '''NO LONGER''' be imported.
  * @author Tongfei Chen
  * @since 0.2.15
  */
object syntax extends GenericFunctions with MapReduceMathOps with Priority1Implicits {

  implicit final val IntStructure = std.IntStructure
  implicit final val LongStructure = std.LongStructure
  implicit final val FloatStructure = std.FloatStructure
  implicit final val DoubleStructure = std.DoubleStructure
  implicit final val BooleanStructure = std.BooleanStructure
  implicit final val StringStructure = std.StringStructure

  implicit final val InstantStructure = std.InstantStructure
  implicit final val DurationStructure = std.DurationStructure

  /** ASCII-compliant symbolic alias for bijections. */
  type <=>[X, Y] = Bijection[X, Y]

  /** Symbolic alias for bijections. */
  type ⇔[X, Y] = Bijection[X, Y]

}
