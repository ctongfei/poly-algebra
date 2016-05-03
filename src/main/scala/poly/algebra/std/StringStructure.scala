package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen
 */
object StringStructure extends ConcatenativeMonoid[String] with OrderedHashing[String] {

  final def cmp(x: String, y: String) = x compareTo y
  final def hash(x: String) = x.##

  final val empty = ""
  def concat(x: String, y: String) = x + y

}
