package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents a module over a ring.
 *
 * A module over a ring is an additive Abelian group ([[poly.algebra.AdditiveCGroup]])
 * on vectors that supports a linear distributive scaling function. It is a generalization
 * of a vector space because the scalars need only be a ring ([[poly.algebra.Ring]]).
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - S is a ring.
 *  - $lawAdditiveAssociativity
 *  - $lawAdditiveIdentity
 *  - $lawAdditiveInvertibility
 *  - $lawAdditiveCommutativity
 *  - $lawCompatibility
 *  - $lawScalingIdentity
 *  - $lawDistributivitySV
 *  - $lawDistributivitySS
 *
 * @define lawCompatibility '''Compatibility''': ∀''k'', ''l''∈S, ∀''a''∈X, ''k'' *: (''l'' *: ''a'') == (''k'' * ''l'') *: ''a''.
 * @define lawScalingIdentity '''Scaling identity''': ∀''a''∈X, 1 *: ''a'' == ''a''.
 * @define lawDistributivitySV '''Distributivity of scaling w.r.t. vector addition''': ∀''k''∈S, ∀''a'', ''b''∈X, ''k'' *: (''a'' + ''b'') == ''k'' *: ''a'' + ''k'' *: ''b''.
 * @define lawDistributivitySS '''Distributivity of scaling w.r.t. scalar addition''': ∀''k'', ''l''∈S, ∀''a''∈X, (''k'' + ''l'') *: ''a'' = ''k'' *: ''a'' + ''l'' *: ''a''.
 *
 * @tparam X Type of vectors
 * @tparam S Type of scalars
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Module[X, @sp(fdi) S] extends MultiplicativeAction[X, S] with AdditiveCGroup[X] { self =>

  /** Returns the ring structure endowed on the type of scalars. */
  implicit def ringOnScalar: Ring[S]

  /** Scales a vector by a scalar. */
  def scale(k: S, x: X): X

  def neg(x: X): X = scale(ringOnScalar.negOne, x)

  /**
   * Returns the dual space of this module.
   * @since 0.2.7
   */
  def dualSpace: Module[X => S, S] = new Module[X => S, S] {
    implicit def ringOnScalar = self.ringOnScalar
    def scale(k: S, f: X => S) = x => ringOnScalar.mul(f(x), k)
    def add(f: X => S, g: X => S) = x => ringOnScalar.add(f(x), g(x))
    def zero = x => ringOnScalar.zero
    override def neg(f: X => S) = x => ringOnScalar.neg(f(x))
    override def sub(f: X => S, g: X => S) = x => ringOnScalar.sub(f(x), g(x))
  }
}

object Module extends BinaryImplicitGetter[Module] {

  def create[X, @sp(fdi) R](fAdd: (X, X) => X, fScale: (R, X) => X, fZero: X)(implicit R: Ring[R]): Module[X, R] = new Module[X, R] {
    def ringOnScalar = R
    def zero = fZero
    def add(x: X, y: X) = fAdd(x, y)
    def scale(k: R, x: X) = fScale(k, x)
  }

  /**
   * Constructs the trivial module of any ring over itself.
   * @param R The ring
   * @return The trivial module of a ring over itself.
   */
  implicit def trivial[R](implicit R: Ring[R]): Module[R, R] = new Module[R, R] {
    def ringOnScalar = R
    def add(x: R, y: R) = R.add(x, y)
    override def neg(x: R) = R.neg(x)
    override def sub(x: R, y: R) = R.sub(x, y)
    def zero = R.zero
    def scale(x: R, y: R) = R.mul(x, y)
  }
}
