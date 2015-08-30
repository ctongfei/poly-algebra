package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * Represents an upper semilattice that has a specific top element.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait BoundedUpperSemilattice[@sp(Boolean) X] extends UpperSemilattice[X] with HasTop[X] { self =>
  def asMonoidWithSup: CMonoid[X] = new CMonoid[X] {
    def id: X = self.top
    def op(x: X, y: X): X = self.sup(x, y)
  }
}

object BoundedUpperSemilattice extends ImplicitGetter[BoundedUpperSemilattice]
