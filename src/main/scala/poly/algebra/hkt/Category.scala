package poly.algebra.hkt

import poly.algebra._
import scala.language.higherKinds

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Category[⇾[_, _]] {

  def id[X]: X ⇾ X

  def map[X, Y, Z](f: X ⇾ Y)(g: Y ⇾ Z): X ⇾ Z
}

object Category {

  implicit val Function = std.FunctionStructure

}
