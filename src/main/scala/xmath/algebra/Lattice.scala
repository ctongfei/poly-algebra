package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Lattice[X] extends PartialOrder[X] {
  def sup(x: X, y: X): X
  def inf(x: X, y: X): X
  def le(x: X, y: X) = inf(x, y) == x
  override def ge(x: X, y: X) = sup(x, y) == x
}

object Lattice {
  def apply[X](implicit L: Lattice[X]) = L
  def create[X](fSup: (X, X) => X, fInf: (X, X) => X) = new Lattice[X] {
    def sup(x: X, y: X): X = fSup(x, y)
    def inf(x: X, y: X): X = fInf(x, y)
  }
}
