package poly.algebra.factory

import scala.language.higherKinds

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait ImplicitGetter[Ev[_]] {

  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[A](implicit ev: Ev[A]): Ev[A] = ev

}

trait BinaryImplicitGetter[Ev[_, _]] {

  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[A, B](implicit ev: Ev[A, B]): Ev[A, B] = ev

}

trait TernaryImplicitGetter[Ev[_, _, _]] {

  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[A, B, C](implicit ev: Ev[A, B, C]): Ev[A, B, C] = ev

}

trait ImplicitHktGetter[Ev[_[_]]] {
  def apply[A[_]](implicit ev: Ev[A]): Ev[A] = ev
}

trait BinaryImplicitHktGetter[Ev[_[_, _]]] {
  def apply[A[_, _]](implicit ev: Ev[A]): Ev[A] = ev
}