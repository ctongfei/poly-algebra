package poly.algebra.hkt

import poly.algebra._

/**
  * @author Tongfei Chen
  */
object implicits {

  implicit final val IterableStructure = std.IterableStructure
  implicit final val SeqStructure = std.SeqStructure
  implicit final val FunctionStructure = std.FunctionStructure
  implicit final val EitherStructure = std.EitherStructure
  implicit final val TupleStructure = std.TupleStructure
  implicit final val OptionStructure = std.OptionStructure

}
