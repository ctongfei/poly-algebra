package poly.algebra.hkt

import poly.algebra._
import poly.algebra.factory._
import scala.language.higherKinds

/**
 * @author Tongfei Chen
 */
trait Category[⇾[_, _]] { self =>

  def id[X]: X ⇾ X

  def compose[X, Y, Z](g: Y ⇾ Z, f: X ⇾ Y): X ⇾ Z

  def andThen[X, Y, Z](f: X ⇾ Y, g: Y ⇾ Z): X ⇾ Z = compose(g, f)

  def asMonoid[X]: Monoid[X ⇾ X] = new Monoid[X ⇾ X] {
    def op(f: X ⇾ X, g: X ⇾ X) = compose(f, g)
    def id = self.id[X]
  }

}

object Category extends Unary2TImplicitHktGetter[Category]
