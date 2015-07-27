package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object implicits {

  implicit final val implicitIntStructure = std.IntStructure
  implicit final val implicitFloatStructure = std.FloatStructure
  implicit final val implicitDoubleStructure = std.DoubleStructure
  implicit final val implicitBooleanStructure = std.BooleanStructure
  implicit final val implicitStringStructure = std.StringStructure
  implicit final val implicitSeqStructure = std.SeqStructure

}
