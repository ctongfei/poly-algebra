package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Monoid[X] extends Semigroup[X] with HasIdentity[X] {
  override def combineN(x: X, n: Int): X = {
    if (n == 0) id
    else super.combineN(x, n)
  }
}

object Monoid {
  def apply[X](implicit M: Monoid[X]) = M
  def create[X](f: (X, X) => X, idElem: X) = new Monoid[X] {
    def op(x: X, y: X): X = f(x, y)
    def id: X = idElem
  }
}

