package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Represents a lower semilattice that has a specific bottom element.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.0
 */
trait BoundedLowerSemilattice[@sp(Boolean) X] extends LowerSemilattice[X] with HasBottom[X] { self =>
  def asMonoidWithInf: CMonoid[X] = new CMonoid[X] {
    def id: X = self.bot
    def op(x: X, y: X): X = self.inf(x, y)
  }
}

object BoundedLowerSemilattice extends ImplicitGetter[BoundedLowerSemilattice]
