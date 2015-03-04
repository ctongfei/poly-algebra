package poly.algebra

/**
 * Typeclass for monoids.
 * A monoid is a semigroup with an identity element.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Monoid[@miniboxed X] extends Semigroup[X] with HasIdentity[X] {
  override def combineN(x: X, n: Int): X = {
    if (n == 0) return id
    var y = x
    var m = n
    while (m % 2 == 0) {
      m >>= 1
      y = op(y, y)
    }
    var r = y
    while (m > 1) {
      m >>= 1
      y = op(y, y)
      if (m % 2 == 1)
        r = op(r, y)
    }
    r
  }
}

object Monoid {
  /** Retrieves the implicit monoid associated with the specific type. */
  def apply[@miniboxed X](implicit M: Monoid[X]) = M

  /** Creates an monoid of the specific type using the binary operation and the identity element provided. */
  def create[@miniboxed X](f: (X, X) => X, idElem: X) = new Monoid[X] {
    def op(x: X, y: X): X = f(x, y)
    def id: X = idElem
  }
}

