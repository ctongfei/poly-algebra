package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen
 */
object StringStructure extends ConcatenativeMonoid[String] {
  final val empty = ""
  def concat(x: String, y: String) = x + y
}
