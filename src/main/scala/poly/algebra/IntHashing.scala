package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait IntHashing[@sp(i) -X] extends Hashing[X, Int]

object IntHashing extends ImplicitGetter[IntHashing] {

  /** Creates a `IntHashing` object from the specific hash function. */
  def create[@sp(i) X](fHash: X => Int)(implicit X: Equiv[X]): IntHashing[X] = new IntHashing[X] {
    def hash(x: X): Int = fHash(x)
    def eq(x: X, y: X) = X.eq(x, y)
  }

  /** Creates an `IntHashing` object from a type's inherent `hashCode`/`##` method. */
  implicit def default[@sp(i) X]: IntHashing[X] = new IntHashing[X] {
    def hash(x: X) = x.##
    def eq(x: X, y: X) = x equals y
  }
}
