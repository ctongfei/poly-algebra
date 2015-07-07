package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.2
 */
object implicits {

  implicit val implicitBooleanStructure = std.BooleanStructure
  implicit val implicitIntStructure = std.IntStructure
  implicit val implicitFloatStructure = std.FloatStructure
  implicit val implicitDoubleStructure = std.DoubleStructure
  implicit val implicitStringStructure = std.StringStructure
  implicit val implicitSeqStructure = std.SeqStructure

}
