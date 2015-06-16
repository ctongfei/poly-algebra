package poly.algebra

import poly.algebra.specgroup._

/**
 * Typeclass for hashing functions.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Hashing[@sp(fdi) X, @sp(i) H] {

  /** Returns a user-defined hash code of an object. */
  def hash(x: X): H

}

object Hashing {

  def apply[X, H](implicit H: Hashing[X, H]) = H

  def create[@sp(fdi) X, H](fHash: X => H): Hashing[X, H] = new Hashing[X, H] {
    def hash(x: X): H = fHash(x)
  }

  implicit def default[@sp(fdi) X]: Hashing[X, Int] = new Hashing[X, Int] {
    def hash(x: X) = x.##
  }
}
