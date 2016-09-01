package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an equivalence relation endowed with a hashing function.
 * This is the typeclass that a HashMap requires for its keys.
 * @author Tongfei Chen
 * @since 0.2.0
 */
trait Hashing[@sp -X] extends Eq[X] { self =>

  /** Returns the hash value of the given input using this hashing instance. */
  def hash(x: X): Int

  override def contramap[@sp(Int) Y](f: Y => X): Hashing[Y] = new HashingT.Contramapped(self, f)

  @unsp def coproduct[Y](that: Hashing[Y]): Hashing[Either[X, Y]] = new HashingT.EitherHashing(self, that)
}

object Hashing extends ImplicitGetter[Hashing] {

  /** Creates a `IntHashing` object from the specific hash function. */
  def create[@sp X](fHash: X => Int)(implicit X: Eq[X]): Hashing[X] = new HashingT.OfHashFunc[X](fHash, X)

  def by[@sp(Int) X, @sp(Int) Y](f: Y => X)(implicit X: Hashing[X]) = new HashingT.Contramapped(X, f)

  /** Creates an `Hashing` object from a type's inherent `hashCode`/`##` method. */
  def default[@sp X]: Hashing[X] = new HashingT.Default[X]

  /** Creates an `Hashing` object using the identity (by-reference) hashing function and identity equivalence relation. */
  def byRef[X <: AnyRef]: Hashing[X] = new HashingT.ByRef[X]

  def onTuple1[@sp(spTuple1) A](implicit A: Hashing[A]): Hashing[Tuple1[A]] = new HashingT.Tuple1Hashing(A)
  def onTuple2[@sp(spTuple2) A, @sp(spTuple2) B](implicit A: Hashing[A], B: Hashing[B]): Hashing[(A, B)] = new HashingT.Tuple2Hashing(A, B)
  def onTuple3[A, B, C](implicit A: Hashing[A], B: Hashing[B], C: Hashing[C]): Hashing[(A, B, C)] = new HashingT.Tuple3Hashing(A, B, C)
  def onTuple4[A, B, C, D](implicit A: Hashing[A], B: Hashing[B], C: Hashing[C], D: Hashing[D]): Hashing[(A, B, C, D)] = new HashingT.Tuple4Hashing(A, B, C, D)
  def onTuple5[A, B, C, D, E](implicit A: Hashing[A], B: Hashing[B], C: Hashing[C], D: Hashing[D], E: Hashing[E]): Hashing[(A, B, C, D, E)] = new HashingT.Tuple5Hashing(A, B, C, D, E)
  def onEither[A, B](implicit A: Hashing[A], B: Hashing[B]): Hashing[Either[A, B]] = new HashingT.EitherHashing(A, B)

}

abstract class AbstractHashing[@sp -X] extends Hashing[X]

private[poly] object HashingT {
  class OfHashFunc[@sp(Int, Long, Float, Double) X](fHash: X ⇒ Int, X: Eq[X]) extends Hashing[X] {
    def hash(x: X) = fHash(x)
    def eq(x: X, y: X) = X.eq(x, y)
  }

  class Contramapped[@sp X, @sp(Int) Y](self: Hashing[X], f: Y ⇒ X) extends Hashing[Y] {
    def hash(y: Y) = self.hash(f(y))
    def eq(x: Y, y: Y) = self.eq(f(x), f(y))
  }

  class Default[@sp X] extends Hashing[X] {
    def hash(x: X) = x.##
    def eq(x: X, y: X) = x == y
  }

  class ByRef[X <: AnyRef] extends AbstractHashing[X] {
    def hash(x: X) = System.identityHashCode(x)
    def eq(x: X, y: X) = x eq y
  }

  // not inheriting EqT.Tuple1Eq because specialization wouldn't work
  class Tuple1Hashing[@sp(spTuple1) A](A: Hashing[A]) extends Hashing[Tuple1[A]] {
    def hash(x: Tuple1[A]) = Tuple1(A.hash(x._1)).##
    def eq(x: Tuple1[A], y: Tuple1[A]) = A.eq(x._1, y._1)
  }

  // not inheriting EqT.Tuple2Eq because specialization wouldn't work
  class Tuple2Hashing[@sp(spTuple2) A, @sp(spTuple2) B] (A: Hashing[A], B: Hashing[B]) extends Hashing[(A, B)] {
    def hash(x: (A, B)) = (A.hash(x._1), B.hash(x._2)).##
    def eq(x: (A, B), y: (A, B)) = A.eq(x._1, y._1) && B.eq(x._2, y._2)
  }

  class Tuple3Hashing[A, B, C](A: Hashing[A], B: Hashing[B], C: Hashing[C]) extends EqT.Tuple3Eq(A, B, C) with Hashing[(A, B, C)] {
    def hash(x: (A, B, C)) = (A.hash(x._1), B.hash(x._2), C.hash(x._3)).##
  }

  class Tuple4Hashing[A, B, C, D](A: Hashing[A], B: Hashing[B], C: Hashing[C], D: Hashing[D]) extends EqT.Tuple4Eq(A, B, C, D) with Hashing[(A, B, C, D)] {
    def hash(x: (A, B, C, D)) = (A.hash(x._1), B.hash(x._2), C.hash(x._3), D.hash(x._4)).##
  }

  class Tuple5Hashing[A, B, C, D, E](A: Hashing[A], B: Hashing[B], C: Hashing[C], D: Hashing[D], E: Hashing[E]) extends EqT.Tuple5Eq(A, B, C, D, E) with Hashing[(A, B, C, D, E)] {
    def hash(x: (A, B, C, D, E)) = (A.hash(x._1), B.hash(x._2), C.hash(x._3), D.hash(x._4), E.hash(x._5)).##
  }

  class EitherHashing[A, B](A: Hashing[A], B: Hashing[B]) extends EqT.EitherEq[A, B](A, B) with Hashing[Either[A, B]] {
    def hash(x: Either[A, B]) = x match {
      case Left(l)  => Left(A.hash(l)).hashCode
      case Right(r) => Right(B.hash(r)).hashCode
    }
  }
}