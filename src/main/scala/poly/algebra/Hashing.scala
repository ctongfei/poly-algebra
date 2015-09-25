package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Typeclass for hashing functions.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Hashing[@sp -X, @sp(i) +H] extends Equiv[X] {

  /** Returns a user-defined hash code of an object. */
  def hash(x: X): H

  def fromJavaHashCode = false

  def hashingSameAs[X1 <: X, H1 >: H](that: Hashing[X1, H1]) = (this eq that) || (this.fromJavaHashCode && that.fromJavaHashCode)

}

object Hashing extends BinaryImplicitGetter[Hashing] {

  /** Creates a `Hashing` object from the specific hash function. */
  def create[@sp X, H](fHash: X => H)(implicit X: Equiv[X]): Hashing[X, H] = new Hashing[X, H] {
    def hash(x: X): H = fHash(x)
    def eq(x: X, y: X) = X.eq(x, y)
    override def fromJavaEquals = X.fromJavaEquals
  }

  /** Creates a `Hashing` object from a type's inherent `hashCode`/`##` method. */
  implicit def default[@sp X]: Hashing[X, Int] = new Hashing[X, Int] {
    def hash(x: X) = x.##
    def eq(x: X, y: X) = x equals y // from Java
    override def fromJavaHashCode = true
  }
}
