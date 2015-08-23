package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait BoundedUpperSemilattice[@sp(Boolean) X] extends UpperSemilattice[X] with HasTop[X] { self =>
  def asMonoidWithSup: CMonoid[X] = new CMonoid[X] {
    def id: X = self.top
    def op(x: X, y: X): X = self.sup(x, y)
  }
}

object BoundedUpperSemilattice extends ImplicitGetter[BoundedUpperSemilattice]
