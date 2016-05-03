package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.algebra.specgroup._

/**
 * Typeclass for type-strict equivalence relations. This is equivalent to the Haskell typeclass `Eq`.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawEqReflexivity
 *  - $lawEqSymmetry
 *  - $lawEqTransitivity
 *
 * @define lawEqReflexivity '''Reflexivity''': ∀''a''∈X, ''a'' =~= ''a''.
 * @define lawEqSymmetry '''Symmetry''': ∀''a'', ''b''∈X, ''a'' =~= ''b'' implies ''b'' =~= ''a''.
 * @define lawEqTransitivity '''Transitivity''': ∀''a'', ''b'', ''c''∈X, ''a'' =~= ''b'' and ''b'' =~= ''c'' implies ''a'' =~= ''c''.
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait Eq[@sp(fdib) -X] { self =>

  /** Checks if two objects of the same type are equivalent under this equivalence relation. */
  def eq(x: X, y: X): Boolean

  /** Checks if two objects of the same type are not equivalent under this equivalence equation. */
  def ne(x: X, y: X): Boolean = !eq(x, y)

  def contramap[@sp(fdib) Y](f: Y => X): Eq[Y] = new Eq[Y] {
    def eq(x: Y, y: Y) = self.eq(f(x), f(y))
  }

  def product[@sp(fdib) Y](that: Eq[Y]): Eq[(X, Y)] = new Eq[(X, Y)] {
    def eq(x: (X, Y), y: (X, Y)) = self.eq(x._1, y._1) && that.eq(x._2, y._2)
  }
}

object Eq extends ImplicitGetter[Eq] {

  // CONSTRUCTORS

  def create[@sp(fdib) X](fEq: (X, X) => Boolean): Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = fEq(x, y)
  }

  def product[X, Y](implicit X: Eq[X], Y: Eq[Y]) = X product Y

  def by[@sp(fdib) Y, @sp(fdib) X](f: Y => X)(implicit X: Eq[X]) = X contramap f

  def default[@sp(fdib) X] = Hashing.default[X]

  def byRef[X <: AnyRef] = Hashing.byRef[X]

  // TYPECLASS INSTANCES

  implicit object ContravariantFunctor extends ContravariantFunctor[Eq] {
    def contramap[X, Y](ex: Eq[X])(f: Y => X) = Eq.by(f)(ex)
  }
}
