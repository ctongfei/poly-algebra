package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Group[@miniboxed X] extends Monoid[X] {
  def inv(x: X): X
}

object Group {
  def apply[@miniboxed X](implicit G: Group[X]) = G
  def create[@miniboxed X](f: (X, X) => X, idElem: X, fInv: X => X) = new Group[X] {
    def inv(x: X): X = fInv(x)
    def op(x: X, y: X): X = f(x, y)
    def id: X = idElem
  }
}
