package poly.algebra.factory

import poly.algebra.macroimpl._
import scala.language.experimental.macros
import scala.language.higherKinds

/**
 * @author Tongfei Chen
 */
trait ImplicitGetter[Ev[_]] {
  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[A](implicit ev: Ev[A]): Ev[A] = macro ImplicitSummoning.summon[Ev, A]
}


trait BinaryImplicitGetter[Ev[_, _]] {
  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[A, B](implicit ev: Ev[A, B]): Ev[A, B] = macro ImplicitSummoning.summonBinary[Ev, A, B]
}

trait TernaryImplicitGetter[Ev[_, _, _]] {
  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[A, B, C](implicit ev: Ev[A, B, C]): Ev[A, B, C] = macro ImplicitSummoning.summonTernary[Ev, A, B, C]
}

trait ImplicitHktGetter[Ev[_[_]]] {
  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[A[_]](implicit ev: Ev[A]): Ev[A] = macro ImplicitSummoning.summonHkt[Ev, A]
}

trait Unary2TImplicitHktGetter[Ev[_[_, _]]] {
  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[A[_, _]](implicit ev: Ev[A]): Ev[A] = macro ImplicitSummoning.summonUnary2T[Ev, A]
}

trait Binary1TImplicitHktGetter[Ev[_[_], _[_]]] {
  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[A[_], B[_]](implicit ev: Ev[A, B]): Ev[A, B] = macro ImplicitSummoning.summonBinary1T[Ev, A, B]
}
