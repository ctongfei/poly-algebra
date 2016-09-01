package poly.algebra

import poly.algebra.factory._
import poly.algebra.hkt._
import poly.algebra.specgroup._

/**
 * Typeclass for type-strict equivalence relations. This is equivalent to the Haskell typeclass `Eq`.
 *
 * `Eq` is contravariant with respect to its type parameter.
 *
 * An instance of this typeclass should satisfy the following axioms:
 *  - $lawEqReflexivity
 *  - $lawEqSymmetry
 *  - $lawEqTransitivity
 *
 * @define lawEqReflexivity '''Reflexivity''': ∀''a''∈X, ''a'' === ''a''.
 * @define lawEqSymmetry '''Symmetry''': ∀''a'', ''b''∈X, ''a'' === ''b'' implies ''b'' === ''a''.
 * @define lawEqTransitivity '''Transitivity''': ∀''a'', ''b'', ''c''∈X, ''a'' === ''b'' and ''b'' === ''c'' implies ''a'' === ''c''.
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait Eq[@sp -X] { self ⇒

  /** Checks if two objects of the same type are equivalent under this equivalence relation. */
  def eq(x: X, y: X): Boolean

  /** Checks if two objects of the same type are not equivalent under this equivalence equation. */
  def ne(x: X, y: X): Boolean = !eq(x, y)

  // if self is Hashing, return a Hashing instance by overriding
  /** Returns an equivalence relation that is defined by ''x'' == ''y'' ⇔ ''f''(''x'') ==,,this,, ''f''(''y''). */
  def contramap[@sp(Int) Y](f: Y => X): Eq[Y] = new EqT.Contramapped(self, f)

  // if both are Hashing, return a Hashing instance at runtime!
  // Why doesn't Scala have multiple dispatch :-(
  /**
   * Returns an equivalence relation on pairs that accepts two pairs (x,,1,,, x,,2,,) and (y,,1,,, y,,2,,) to be
   * equivalent if and only if x,,1,, == y,,1,, under this and x,,2,, == y,,2,, under that.
   */
  def product[@sp(Int, Long, Double, Char, Boolean) Y](that: Eq[Y]): Eq[(X, Y)] = Eq.onTuple2(self, that)

  @unsp def coproduct[Y](that: Eq[Y]): Eq[Either[X, Y]] = Eq.onEither(self, that)

  @unsp def intersect[Y <: X](that: Eq[Y]): Eq[Y] = new EqT.Intersection(self, that)

  @unsp def union[Y <: X](that: Eq[Y]): Eq[Y] = new EqT.Union(self, that)

}

object Eq extends ImplicitGetter[Eq] {

  def create[@sp X](fEq: (X, X) => Boolean): Eq[X] = new EqT.OfEqFunc[X](fEq)

  def product[@sp(Int, Long, Double, Char, Boolean) X, @sp(Int, Long, Double, Char, Boolean) Y]
  (X: Eq[X], Y: Eq[Y]) = Eq.onTuple2(X, Y)

  def by[@sp(Int) Y, @sp(fdib) X](f: Y => X)(implicit X: Eq[X]) = X contramap f

  /**
   * Constructs an equivalence relation based on the type's inherent `equals`/`hashCode` methods.
   * @note The user should make sure that the behavior of the `hashCode` method on the given type be consistent
   *       with its `equals` method.
   */
  def default[@sp(fdib) X] = Hashing.default[X]

  /**
   * Constructs an equivalence relation based on the identity of the reference (given type must be a subtype of [[AnyRef]]).
   */
  def byRef[X <: AnyRef] = Hashing.byRef[X]

  implicit def onTuple1[@sp(spTuple1) A](implicit A: Eq[A]) = A match {
    case ha: Hashing[A] ⇒ new HashingT.Tuple1Hashing(ha)
    case _ ⇒ new EqT.Tuple1Eq(A)
  }

  implicit def onTuple2[@sp(spTuple2) A, @sp(spTuple2) B]
  (implicit A: Eq[A], B: Eq[B]): Eq[(A, B)] = (A, B) match {
    case (ha: Hashing[A], hb: Hashing[B]) ⇒ new HashingT.Tuple2Hashing(ha, hb)
    case _ ⇒ new EqT.Tuple2Eq(A, B)
  }

  implicit def onTuple3[A, B, C](implicit A: Eq[A], B: Eq[B], C: Eq[C]): Eq[(A, B, C)] = (A, B, C) match {
    case (ha: Hashing[A], hb: Hashing[B], hc: Hashing[C]) ⇒ new HashingT.Tuple3Hashing(ha, hb, hc)
    case _ ⇒ new EqT.Tuple3Eq(A, B, C)
  }

  implicit def onTuple4[A, B, C, D](implicit A: Eq[A], B: Eq[B], C: Eq[C], D: Eq[D]): Eq[(A, B, C, D)] = (A, B, C, D) match {
    case (ha: Hashing[A], hb: Hashing[B], hc: Hashing[C], hd: Hashing[D]) ⇒ new HashingT.Tuple4Hashing(ha, hb, hc, hd)
    case _ ⇒ new EqT.Tuple4Eq(A, B, C, D)
  }

  implicit def onTuple5[A, B, C, D, E](implicit A: Eq[A], B: Eq[B], C: Eq[C], D: Eq[D], E: Eq[E]): Eq[(A, B, C, D, E)] = (A, B, C, D, E) match {
    case (ha: Hashing[A], hb: Hashing[B], hc: Hashing[C], hd: Hashing[D], he: Hashing[E]) ⇒ new HashingT.Tuple5Hashing(ha, hb, hc, hd, he)
    case _ ⇒ new EqT.Tuple5Eq(A, B, C, D, E)
  }

  implicit def onEither[A, B](implicit A: Eq[A], B: Eq[B]): Eq[Either[A, B]] = (A, B) match {
    case (ha: Hashing[A], hb: Hashing[B]) => new HashingT.EitherHashing(ha, hb)
    case _ => new EqT.EitherEq(A, B)
  }

  // TYPECLASS INSTANCES

  implicit object ContravariantFunctor extends ContravariantFunctor[Eq] {
    def contramap[X, Y](X: Eq[X])(f: Y => X) = X contramap f
  }
}

abstract class AbstractEq[-X] extends Eq[X]

private[poly] object EqT {

  // Because trait Function2[@specialized(Int, Long, Double) -T1, @specialized(Int, Long, Double) -T2, @specialized(Unit, Boolean, Int, Float, Long, Double) +R]
  class OfEqFunc[@sp(Int, Long, Double) X](fEq: (X, X) ⇒ Boolean) extends AbstractEq[X] {
    def eq(x: X, y: X) = fEq(x, y)
  }

  class Contramapped[@sp X, @sp(Int) Y](self: Eq[X], f: Y ⇒ X) extends Eq[Y] {
    def eq(x: Y, y: Y) = self.eq(f(x), f(y))
  }

  class Intersection[X](self: Eq[X], that: Eq[X]) extends AbstractEq[X] {
    def eq(x: X, y: X) = self.eq(x, y) && that.eq(x, y)
  }

  class Union[X](self: Eq[X], that: Eq[X]) extends AbstractEq[X] {
    def eq(x: X, y: X) = self.eq(x, y) || that.eq(x, y)
  }

  class Tuple1Eq[@sp(spTuple1) A](A: Eq[A]) extends Eq[Tuple1[A]] {
    def eq(x: Tuple1[A], y: Tuple1[A]) = A.eq(x._1, y._1)
  }

  class Tuple2Eq[@sp(spTuple2) A, @sp(spTuple2) B](A: Eq[A], B: Eq[B]) extends Eq[(A, B)] {
    def eq(x: (A, B), y: (A, B)) =
      A.eq(x._1, y._1) && B.eq(x._2, y._2)
  }

  class Tuple3Eq[A, B, C](A: Eq[A], B: Eq[B], C: Eq[C]) extends AbstractEq[(A, B, C)] {
    def eq(x: (A, B, C), y: (A, B, C)) =
      A.eq(x._1, y._1) && B.eq(x._2, y._2) && C.eq(x._3, y._3)
  }

  class Tuple4Eq[A, B, C, D](A: Eq[A], B: Eq[B], C: Eq[C], D: Eq[D]) extends AbstractEq[(A, B, C, D)] {
    def eq(x: (A, B, C, D), y: (A, B, C, D)) =
      A.eq(x._1, y._1) && B.eq(x._2, y._2) && C.eq(x._3, y._3) && D.eq(x._4, y._4)
  }

  class Tuple5Eq[A, B, C, D, E](A: Eq[A], B: Eq[B], C: Eq[C], D: Eq[D], E: Eq[E]) extends AbstractEq[(A, B, C, D, E)] {
    def eq(x: (A, B, C, D, E), y: (A, B, C, D, E)) =
      A.eq(x._1, y._1) && B.eq(x._2, y._2) && C.eq(x._3, y._3) && D.eq(x._4, y._4) && E.eq(x._5, y._5)
  }

  class EitherEq[A, B](A: Eq[A], B: Eq[B]) extends AbstractEq[Either[A, B]] {
    def eq(x: Either[A, B], y: Either[A, B]) = (x, y) match {
      case (Left(xl),  Left(yl) ) => A.eq(xl, yl)
      case (Right(xr), Right(yr)) => B.eq(xr, yr)
      case _ => false
    }
  }

}
