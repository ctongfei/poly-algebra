package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.algebra.specgroup._

/**
 * Typeclass for type-strict equivalence relations.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawEqReflexivity
 *  - $lawEqSymmetry
 *  - $lawEqTransitivity
 *
 * @define lawEqReflexivity '''Reflexivity''': ∀''a''∈X, ''a'' =~= ''a''.
 * @define lawEqSymmetry '''Symmetry''': ∀''a'', ''b''∈X, ''a'' =~= ''b'' implies ''b'' =~= ''a''.
 * @define lawEqTransitivity '''Transitivity''': ∀''a'', ''b'', ''c''∈X, ''a'' =~= ''b'' and ''b'' =~= ''c'' implies ''a'' =~= ''c''.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.1.0
 */
trait Equiv[@sp(fdib) -X] {

  /** Checks if two objects of the same type are equivalent under this equivalence relation. */
  def eq(x: X, y: X): Boolean

  /** Checks if two objects of the same type are not equivalent under this equivalence equation. */
  def ne(x: X, y: X): Boolean = !eq(x, y)

  /** Marks whether the `eq` method is derived from `java.lang.Object.equals`. */
  def fromJavaEquals = false

  def equivSameAs[X1 <: X](that: Equiv[X1]) = (this eq that) || (this.fromJavaEquals && that.fromJavaEquals)
}


object Equiv extends ImplicitGetter[Equiv] {

  def create[@sp(fdib) X](fEq: (X, X) => Boolean): Equiv[X] = new Equiv[X] {
    def eq(x: X, y: X) = fEq(x, y)
  }

  implicit def default[@sp X]: Equiv[X] = new Equiv[X] {
    def eq(x: X, y: X) = x == y
    override def ne(x: X, y: X) = x != y
    override def fromJavaEquals = true
  }

  def by[@sp Y, X](f: Y => X)(implicit ev: Equiv[X]): Equiv[Y] = new Equiv[Y] {
    def eq(x: Y, y: Y) = ev.eq(f(x), f(y))
    override def ne(x: Y, y: Y) = ev.ne(f(x), f(y))
  }

  /** Returns the equality-by-reference relation. */
  def byRef[X <: AnyRef]: Equiv[X] = new Equiv[X] {
    def eq(x: X, y: X) = x eq y
    override def ne(x: X, y: X) = x ne y
  }

  implicit object ContravariantFunctor extends ContravariantFunctor[Equiv] {
    def contramap[X, Y](ex: Equiv[X])(f: Y => X) = Equiv.by(f)(ex)
  }
}
