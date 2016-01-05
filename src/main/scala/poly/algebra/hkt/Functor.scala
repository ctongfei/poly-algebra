package poly.algebra.hkt

import poly.algebra.factory._

import scala.language.higherKinds

/**
 * Represents a functor.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.0
 */
trait Functor[F[_]] {

  def map[X, Y](fx: F[X])(f: X => Y): F[Y]

  def lift[X, Y](f: X => Y): F[X] => F[Y] = mx => map(mx)(f)

  //def unzip[X, Y](fxy: F[(X, Y)]): (F[X], F[Y]) = (map(fxy)(_._1), map(fxy)(_._2))


}

object Functor extends ImplicitHktGetter[Functor]

