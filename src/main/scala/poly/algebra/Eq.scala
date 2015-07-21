package poly.algebra

import poly.algebra.PartialOrder._
import poly.algebra.hkt._
import poly.util.specgroup._

/**
 * Typeclass for type-strict equivalence relations.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.1.0
 */
trait Eq[@sp -X] {

  /** Checks if two objects of the same type are equivalent under this equivalence relation. */
  def eq(x: X, y: X): Boolean

  /** Checks if two objects of the same type are not equivalent under this equivalence equation. */
  def ne(x: X, y: X): Boolean = !eq(x, y)

}


object Eq {

  def create[@sp X](fEq: (X, X) => Boolean): Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = fEq(x, y)
  }

  implicit def default[@sp X]: Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = x == y
    override def ne(x: X, y: X) = x != y
  }

  def by[@sp Y, X](f: Y => X)(implicit ev: Eq[X]): Eq[Y] = new Eq[Y] {
    def eq(x: Y, y: Y) = ev.eq(f(x), f(y))
    override def ne(x: Y, y: Y) = ev.ne(f(x), f(y))
  }

  /** Returns the equality-by-reference relation. */
  def byRef[X <: AnyRef]: Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = x eq y
    override def ne(x: X, y: X) = x ne y
  }

  implicit object ContravariantFunctor extends ContravariantFunctor[Eq] {
    def contramap[X, Y](ex: Eq[X])(f: Y => X) = Eq.by(f)(ex)
  }
}
