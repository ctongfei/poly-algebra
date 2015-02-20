package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Group[X] extends Monoid[X] {
  def inv(x: X): X
}

object Group {
  def apply[X](implicit G: Group[X]) = G
  def create[X](f: (X, X) => X, idElem: X, fInv: X => X) = new Group[X] {
    def inv(x: X): X = fInv(x)
    def op(x: X, y: X): X = f(x, y)
    def id: X = idElem
  }
}
