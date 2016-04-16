package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen
 */
trait IntHashing[@sp -X] extends Hashing[X, Int] { self =>

  override def contramap[@sp(fdib) Y](f: Y => X): IntHashing[Y] = new IntHashing[Y] {
    def hash(y: Y) = self.hash(f(y))
    def eq(x: Y, y: Y) = self.eq(f(x), f(y))
  }

}

object IntHashing extends ImplicitGetter[IntHashing] {

  /** Creates a `IntHashing` object from the specific hash function. */
  def create[@sp X](fHash: X => Int)(implicit X: Equiv[X]): IntHashing[X] = new IntHashing[X] {
    def hash(x: X): Int = fHash(x)
    def eq(x: X, y: X) = X.eq(x, y)
  }

  def by[Y, @sp X](f: Y => X)(implicit X: IntHashing[X]) = X contramap f

  /** Creates an `IntHashing` object from a type's inherent `hashCode`/`##` method. */
  implicit def default[@sp X]: IntHashing[X] = new IntHashing[X] {
    def hash(x: X) = x.##
    def eq(x: X, y: X) = x equals y
  }
}
