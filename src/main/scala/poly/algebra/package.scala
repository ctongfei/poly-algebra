package poly

/**
 * `Poly-algebra` contains typeclass abstractions over common algebraic structures.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object algebra {

  implicit val implicitBooleanStructure = std.BooleanStructure
  implicit val implicitIntStructure = std.IntStructure
  implicit val implicitFloatStructure = std.FloatStructure
  implicit val implicitDoubleStructure = std.DoubleStructure

}
