package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait BoundedLattice[@sp(Boolean) X] extends
  Lattice[X] with BoundedLowerSemilattice[X] with BoundedUpperSemilattice[X]

object BoundedLattice extends ImplicitGetter[BoundedLattice] {

  def create[X](fBot: X, fTop: X)(implicit L: Lattice[X]): BoundedLattice[X] =
    new BoundedLattice[X] {
      def sup(x: X, y: X): X = L.sup(x, y)
      def inf(x: X, y: X): X = L.inf(x, y)
      val bot: X = fBot
      val top: X = fTop
    }

}