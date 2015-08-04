package poly.algebra.hkt

import poly.algebra._
import scala.language.higherKinds

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Category[⇨[_, _]] {

  def id[X]: X ⇨ X

  def map[X, Y, Z](f: X ⇨ Y)(g: Y ⇨ Z): X ⇨ Z
}

object Category {

  implicit object FunctionCategory extends Category[Function] {
    def id[X]: X => X = x => x
    def map[X, Y, Z](f: X => Y)(g: Y => Z): X => Z = g compose f
  }

  implicit object BijectionCategory extends Category[Bijection] {
    def id[X] = Bijection.create(x => x, x => x)
    def map[X, Y, Z](f: Bijection[X, Y])(g: Bijection[Y, Z]) = f andThen g
  }

}