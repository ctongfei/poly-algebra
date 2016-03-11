package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Typeclass for hashing functions.
 * @author Tongfei Chen
 */
trait Hashing[@sp -X, @sp(i) +H] extends Equiv[X] {

  /** Returns a user-defined hash code of an object. */
  def hash(x: X): H

}

object Hashing extends BinaryImplicitGetter[Hashing] {

  /** Creates a `Hashing` object from the specific hash function. */
  def create[@sp X, H](fHash: X => H)(implicit X: Equiv[X]): Hashing[X, H] = new Hashing[X, H] {
    def hash(x: X): H = fHash(x)
    def eq(x: X, y: X) = X.eq(x, y)
  }

}
