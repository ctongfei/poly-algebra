package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */

trait Lattice[@sp(fdib) X] extends UpperSemilattice[X] with LowerSemilattice[X] { self =>
  override def le(x: X, y: X) = eq(x, inf(x, y))
  override def ge(x: X, y: X) = eq(x, sup(x, y))

  override def reverse: Lattice[X] = new Lattice[X] {
    override def reverse = self
    def sup(x: X, y: X) = self.inf(x, y)
    def inf(x: X, y: X) = self.sup(x, y)
  }
}

object Lattice extends ImplicitGetter[Lattice] {
  def create[@sp(fdib) X](fSup: (X, X) => X, fInf: (X, X) => X) = new Lattice[X] {
    def sup(x: X, y: X): X = fSup(x, y)
    def inf(x: X, y: X): X = fInf(x, y)
  }
}


