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

  /** Returns the product category of two categories. */
  def product[⇨[_, _]](that: Category[⇨]): Category[({type λ[α, β] = (α ⇾ β, α ⇨ β)})#λ] =
    new CategoryT.Product(self, that)

  def asMonoid[X]: Monoid[X ⇾ X] = new Monoid[X ⇾ X] {
    def op(f: X ⇾ X, g: X ⇾ X) = compose(f, g)
    def id = self.id[X]
  }

}

object Category extends Unary2TImplicitHktGetter[Category]

private[poly] object CategoryT {

  class Product[⇾[_, _], ⇨[_, _]](self: Category[⇾], that: Category[⇨]) extends Category[({type λ[α, β] = (α ⇾ β, α ⇨ β)})#λ] {
    def id[X] = (self.id[X], that.id[X])
    def compose[X, Y, Z](g: (Y ⇾ Z, Y ⇨ Z), f: (X ⇾ Y, X ⇨ Y)) = (self.compose(g._1, f._1), that.compose(g._2, f._2))
  }

}