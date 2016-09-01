package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a upper-semilattice, i.e., a partially-ordered set in which every finite non-empty subset has a
 * least upper bound.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawSupremumAssociativity
 *  - $lawSupremumCommutativity
 *  - $lawSupremumIdempotency
 * @define lawSupremumAssociativity '''Supremum associativity''': ∀''a'', ''b'', ''c''∈X, sup(''a'', sup(''b'', ''c'')) == sup(sup(''a'', ''b''), ''c'').
 * @define lawSupremumCommutativity '''Supremum commutativity''': ∀''a'', ''b''∈X, sup(''a'', ''b'') == sup(''b'', ''a'').
 * @define lawSupremumIdempotency '''Supremum idempotency''': ∀''a''∈X, sup(''a'', ''a'') == a.
 * @author Tongfei Chen
 */
trait UpperSemilattice[@sp(fdib) X] { self =>

  /** Returns the supremum (join, a.k.a. least upper bound) of the two arguments. */
  def sup(x: X, y: X): X

  def reverse: LowerSemilattice[X] = new LowerSemilattice[X] {
    override def reverse = self
    def inf(x: X, y: X) = sup(x, y)
  }

  def asSemigroupWithSup: CSemigroup[X] = new CSemigroup[X] {
    def op(x: X, y: X) = sup(x, y)
  }
}

object UpperSemilattice extends ImplicitGetter[UpperSemilattice] {

  def create[@sp(Int, Boolean) X](fSup: (X, X) => X): UpperSemilattice[X] = new UpperSemilattice[X] {
    def sup(x: X, y: X): X = fSup(x, y)
  }
}

trait EqUpperSemilattice[@sp(Boolean) X] extends UpperSemilattice[X] with PartialOrder[X] { self =>

  override def reverse: EqLowerSemilattice[X] = new EqLowerSemilattice[X] {
    def le(x: X, y: X) = self.le(y, x)
    def inf(x: X, y: X) = self.sup(x, y)
    override def reverse = self
  }

}

object EqUpperSemilattice extends ImplicitGetter[EqUpperSemilattice]
