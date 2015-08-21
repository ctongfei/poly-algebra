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
  def apply[X](implicit ev: Ev[X]): Ev[X] = ev

}

trait BinaryImplicitGetter[Ev[_, _]] {

  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[V, F](implicit ev: Ev[V, F]): Ev[V, F] = ev

}

trait TernaryImplicitGetter[Ev[_, _, _]] {

  /**
   * Retrieves the implicit algebraic structure implicitly defined in this scope.
   * @param ev The implicit object to be retrieved
   */
  def apply[X, Y, F](implicit ev: Ev[X, Y, F]): Ev[X, Y, F] = ev

}
