package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an equivalence relation endowed with a hashing function.
 * This is the typeclass that a HashMap requires for its keys.
 *
 * @author Tongfei Chen
 */
trait Hashing[@sp -X] extends Eq[X] { self =>

  /** Returns the hash value of the given input. */
  def hash(x: X): Int

  override def contramap[@sp(fdib) Y](f: Y => X): Hashing[Y] = new Hashing[Y] {
    def hash(y: Y) = self.hash(f(y))
    def eq(x: Y, y: Y) = self.eq(f(x), f(y))
  }

}

object Hashing extends ImplicitGetter[Hashing] {

  /** Creates a `IntHashing` object from the specific hash function. */
  def create[@sp X](fHash: X => Int)(implicit X: Eq[X]): Hashing[X] = new Hashing[X] {
    def hash(x: X): Int = fHash(x)
    def eq(x: X, y: X) = X.eq(x, y)
  }

  def by[Y, @sp X](f: Y => X)(implicit X: Hashing[X]) = X contramap f

  /** Creates an `IntHashing` object from a type's inherent `hashCode`/`##` method. */
  def default[@sp X]: Hashing[X] = new Hashing[X] {
    def hash(x: X) = x.##
    def eq(x: X, y: X) = x equals y
  }

  /** Creates an `IntHashing` object using the identity (by-reference) hashing function and identity equivalence relation. */
  def byRef[X <: AnyRef]: Hashing[X] = new Hashing[X] {
    def hash(x: X) = System.identityHashCode(x)
    def eq(x: X, y: X) = x eq y
  }
}
