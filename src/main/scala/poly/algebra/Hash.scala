package poly.algebra

/**
 * Typeclass for hashing functions.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Hash[@specialized(Int, Double) X] extends Eq[X] {

  /** Returns a user-defined hash code of an object. */
  def hash(x: X): Int

}

object Hash {

  def apply[X](implicit H: Hash[X]) = H

  def create[@specialized(Int, Double) X](fHash: X => Int)(implicit E: Eq[X]): Hash[X] = new Hash[X] {
    def eq(x: X, y: X) = E.eq(x, y)
    override def ne(x: X, y: X) = E.ne(x, y)
    def hash(x: X): Int = fHash(x)
  }

  implicit def default[@specialized(Int, Double) X]: Hash[X] = new Hash[X] {
    def eq(x: X, y: X) = x == y
    override def ne(x: X, y: X) = x != y
    def hash(x: X) = x.##
  }
}
