package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object implicits {

  implicit final val IntStructure = std.IntStructure
  implicit final val FloatStructure = std.FloatStructure
  implicit final val DoubleStructure = std.DoubleStructure
  implicit final val BooleanStructure = std.BooleanStructure
  implicit final val StringStructure = std.StringStructure

  implicit final val SeqStructure = std.SeqStructure
  implicit final val FunctionStructure = std.FunctionStructure
  implicit final val EitherStructure = std.EitherStructure
  implicit final val TupleStructure = std.TupleStructure
  implicit final val OptionStructure = std.OptionStructure

}
