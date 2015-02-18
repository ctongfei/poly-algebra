package xmath.algebra

/**
 * Typeclass for semigroups.
 * A semigroup is a set equipped with an associative binary operation.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Semigroup[X] {

  /** The associative binary operation of this semigroup. */
  def op(x: X, y: X): X

  /** Combines an element with itself for ''n'' times using the binary exponentiation algorithm. */
  def combineN(x: X, n: Int): X = {
    require(n > 0)
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

object Semigroup {
  /** Retrieves the implicit semigroup associated with a specific type. */
  def apply[X](implicit S: Semigroup[X]) = S

  /** Creates a semigroup using the binary operation provided. */
  def create[X](f: (X, X) => X) = new Semigroup[X] {
    def op(x: X, y: X): X = f(x, y)
  }
}

