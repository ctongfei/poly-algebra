package xmath.algebra

/**
 * Typeclass for semigroups.
 * A semigroup is a set equipped with an associative binary operation.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Semigroup[@specialized X] {

  /** The associative binary operation of this semigroup. */
  def op(x: X, y: X): X

  /** Combines an element with itself for ''n'' times using the binary exponentiation algorithm. */
  def combineN(x: X, n: Int): X = {
    if (n <= 0) throw new IllegalArgumentException()
    else if (n == 1) x
    else {
      var y = x
      var p = x
      var m = n
      while (m > 1) {
        if (m % 2 == 1) p = op(p, y)
        y = op(y, y)
        m >>= 1
      }
      p
    }
  }

}

object Semigroup {
  def apply[X](implicit S: Semigroup[X]) = S
  def create[X](f: (X, X) => X) = new Semigroup[X] {
    def op(x: X, y: X): X = f(x, y)
  }
}

