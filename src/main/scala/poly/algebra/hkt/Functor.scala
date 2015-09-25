package poly.algebra.hkt

import poly.algebra.factory._

import scala.language.higherKinds

/**
 * Typeclass for functors.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.0
 */
trait Functor[F[_]] {

  def map[X, Y](mx: F[X])(f: X => Y): F[Y]

  def lift[X, Y](f: X => Y): F[X] => F[Y] = mx => map(mx)(f)

  def unzip[X, Y](mxy: F[(X, Y)]): (F[X], F[Y]) = (map(mxy)(_._1), map(mxy)(_._2))

}

object Functor extends ImplicitHktGetter[Functor]

