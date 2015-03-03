package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Lattice[@miniboxed X] extends PartialOrder[X] {
  def sup(x: X, y: X): X
  def inf(x: X, y: X): X
  def le(x: X, y: X) = eq(x, inf(x, y))
  override def ge(x: X, y: X) = eq(x, sup(x, y))
}

object Lattice {
  def apply[@miniboxed X](implicit L: Lattice[X]) = L
  def create[@miniboxed X](fSup: (X, X) => X, fInf: (X, X) => X) = new Lattice[X] {
    def eq(x: X, y: X) = x == y
    def sup(x: X, y: X): X = fSup(x, y)
    def inf(x: X, y: X): X = fInf(x, y)
  }
}
