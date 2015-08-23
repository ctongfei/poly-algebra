package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait BoundedLowerSemilattice[@sp(Boolean) X] extends LowerSemilattice[X] with HasBottom[X] { self =>
  def asMonoidWithInf: CMonoid[X] = new CMonoid[X] {
    def id: X = self.bot
    def op(x: X, y: X): X = self.inf(x, y)
  }
}

object BoundedLowerSemilattice extends ImplicitGetter[BoundedLowerSemilattice]
