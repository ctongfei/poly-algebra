package poly.algebra.std

import poly.algebra._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
object StringStructure extends Monoid[String] {
  def id = ""
  def op(x: String, y: String) = x + y
}
