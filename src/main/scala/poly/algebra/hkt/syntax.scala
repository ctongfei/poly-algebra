package poly.algebra.hkt

import poly.algebra._
import scala.language.higherKinds

/**
  * @author Tongfei Chen
  * @since 0.2.15
  */
object syntax extends HktImplicits {

  implicit final val IterableStructure = std.IterableStructure
  implicit final val SeqStructure = std.SeqStructure
  implicit final val FunctionStructure = std.FunctionStructure
  implicit final val EitherStructure = std.EitherStructure
  implicit final val TupleStructure = std.TupleStructure
  implicit final val OptionStructure = std.OptionStructure


  type =>>[A[_], B[_]] = NaturalTransformation[A, B]

}
