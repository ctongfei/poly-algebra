package poly.algebra

import poly.algebra.specgroup._

/**
 * Typeclass for hashing functions.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Hash[@sp(fdi) X, @sp(i) H] {

  /** Returns a user-defined hash code of an object. */
  def hash(x: X): H

}

object Hash {

  def apply[X, H](implicit H: Hash[X, H]) = H

  def create[@sp(fdi) X, H](fHash: X => H): Hash[X, H] = new Hash[X, H] {
    def hash(x: X): H = fHash(x)
  }

  implicit def default[@sp(fdi) X]: Hash[X, Int] = new Hash[X, Int] {
    def hash(x: X) = x.##
  }
}
