package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Monoid[X] extends Semigroup[X] with HasIdentity[X] {
  override def combineN(x: X, n: Int) = {
    if (n < 0) throw new IllegalArgumentException
    var p = id
    var y = x
    var m = n
    while (m != 0) {
      if (m % 2 == 1) p = op(p, y)
      y = op(y, y)
      m >>= 1
    }
    p
  }
}

object Monoid {
  def apply[X](implicit M: Monoid[X]) = M
  def create[X](f: (X, X) => X, idElem: X) = new Monoid[X] {
    def op(x: X, y: X): X = f(x, y)
    def id: X = idElem
  }
}

