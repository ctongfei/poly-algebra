package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a lower-semilattice, i.e., a partially-ordered set in which every finite non-empty subset has a
 * greatest lower bound.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawInfimumAssociativity
 *  - $lawInfimumCommutativity
 *  - $lawInfimumIdempotency
 * @define lawInfimumAssociativity '''Infimum associativity''': ∀''a'', ''b'', ''c''∈X, inf(''a'', inf(''b'', ''c'')) == inf(inf(''a'', ''b''), ''c'').
 * @define lawInfimumCommutativity '''Infimum commutativity''': ∀''a'', ''b''∈X, inf(''a'', ''b'') == inf(''b'', ''a'').
 * @define lawInfimumIdempotency '''Infimum idempotency''': ∀''a''∈X, inf(''a'', ''a'') == a.
 * @author Tongfei Chen
 */
trait LowerSemilattice[@sp(Boolean) X] { self =>
  /** Returns the infimum (meet, a.k.a. greatest lower bound) of the two arguments. */
  def inf(x: X, y: X): X

  /** Casts this lower semilattice as a partial order if an implicit equivalence relation is present. */
  def asPartialOrder(implicit e: Eq[X]): PartialOrder[X] = new PartialOrder[X] {
    override def eq(x: X, y: X) = e.eq(x, y)
    override def ne(x: X, y: X) = e.ne(x, y)
    def le(x: X, y: X) = eq(x, inf(x, y))
    override def ge(x: X, y: X) = eq(y, inf(x, y))
  }

  def reverse: UpperSemilattice[X] = new UpperSemilattice[X] {
    override def reverse = self
    def sup(x: X, y: X) = inf(x, y)
  }

  /** Casts this structure as an symbol-agnostic semigroup with the infimum operation on this semilattice. */
  def asSemigroupWithInf: CSemigroup[X] = new CSemigroup[X] {
    def op(x: X, y: X) = inf(x, y)
  }
}

object LowerSemilattice extends ImplicitGetter[LowerSemilattice] {

  def by[@sp(Boolean) X](fInf: (X, X) => X): LowerSemilattice[X] = new LowerSemilattice[X] {
    def inf(x: X, y: X): X = fInf(x, y)
  }
}
