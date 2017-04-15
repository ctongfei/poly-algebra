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
 * <ul>
 *  <li> S is a ring. </li>
 *  <li> $lawAdditiveAssociativity </li>
 *  <li> $lawAdditiveIdentity </li>
 *  <li> $lawAdditiveInvertibility </li>
 *  <li> $lawAdditiveCommutativity </li>
 *  <li> $lawCompatibility </li>
 *  <li> $lawScalingIdentity </li>
 *  <li> $lawDistributivitySV </li>
 *  <li> $lawDistributivitySS </li>
 * </ul>
 *
 * @define lawCompatibility '''Compatibility''': \(\forall k, l \in S, \forall a \in X, k(la) = (kl)a \).
 * @define lawScalingIdentity '''Scaling identity''': \(\forall a\in X, 1a = a\).
 * @define lawDistributivitySV '''Distributivity of scaling w.r.t. vector addition''': \(\forall k\in S, \forall a, b\in X, k(a+b) == ka + kb \).
 * @define lawDistributivitySS '''Distributivity of scaling w.r.t. scalar addition''': \(\forall k, l \in S, \forall a\in X, (k+l)a = ka + la. \).
 * @tparam X Type of vectors
 * @tparam S Type of scalars
 * @since 0.1.0
 * @author Tongfei Chen
 */
trait Module[X, @sp(fdi) S] extends MultiplicativeAction[X, S] with AdditiveCGroup[X] { self =>

  /** Returns the ring structure endowed on the type of scalars. */
  implicit def scalarRing: Ring[S]

  /** Scales a vector by a scalar. */
  def scale(x: X, k: S): X

  def neg(x: X): X = scale(x, scalarRing.negOne)

  /**
   * Returns the dual space of this module.
   * @since 0.2.7
   */
  def dual: Module[X => S, S] = new ModuleT.Dual(self)

}

object Module extends BinaryImplicitGetter[Module] {

  def create[X, @sp(fdi) R](fAdd: (X, X) => X, fScale: (X, R) => X, fZero: X)(implicit R: Ring[R]): Module[X, R] = new Module[X, R] {
    def scalarRing = R
    def zero = fZero
    def add(x: X, y: X) = fAdd(x, y)
    def scale(x: X, k: R) = fScale(x, k)
  }

  /** Constructs the trivial module of any ring over itself. */
  implicit def trivial[R](implicit R: Ring[R]): Module[R, R] = new ModuleT.Trivial(R)

  /** Returns the natural Z-module of an Abelian group. */
  implicit def ZModule[G](implicit G: AdditiveCGroup[G]): Module[G, Int] = new ModuleT.ZModule(G)
}

private[this] object ModuleT {

  class Dual[X, @sp(fdi) S](self: Module[X, S]) extends Module[X => S, S] {
    implicit def scalarRing = self.scalarRing
    def scale(f: X => S, k: S) = x => scalarRing.mul(f(x), k)
    def add(f: X => S, g: X => S) = x => scalarRing.add(f(x), g(x))
    def zero = x => scalarRing.zero
    override def neg(f: X => S) = x => scalarRing.neg(f(x))
    override def sub(f: X => S, g: X => S) = x => scalarRing.sub(f(x), g(x))
  }

  class Trivial[@sp(fdi) R](R: Ring[R]) extends Module[R, R] {
    def scalarRing = R
    def add(x: R, y: R) = R.add(x, y)
    override def neg(x: R) = R.neg(x)
    override def sub(x: R, y: R) = R.sub(x, y)
    def zero = R.zero
    def scale(x: R, y: R) = R.mul(x, y)
  }

  class ZModule[G](G: AdditiveCGroup[G]) extends Module[G, Int] {
    def scalarRing: Ring[Int] = std.IntStructure
    def scale(x: G, k: Int) = G.sumN(x, k)
    def zero = G.zero
    def add(x: G, y: G) = G.add(x, y)
  }
}