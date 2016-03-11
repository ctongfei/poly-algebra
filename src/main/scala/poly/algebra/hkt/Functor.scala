package poly.algebra.hkt

import poly.algebra.factory._
import scala.language.reflectiveCalls
import scala.language.higherKinds

/**
 * Represents a functor.
 * @author Tongfei Chen
 * @since 0.2.0
 */
trait Functor[F[_]] { self =>

  def map[X, Y](fx: F[X])(f: X => Y): F[Y]

  def lift[X, Y](f: X => Y): F[X] => F[Y] = mx => map(mx)(f)

  def unzip[X, Y](fxy: F[(X, Y)]): (F[X], F[Y]) = (map(fxy)(_._1), map(fxy)(_._2))

  def compose[G[_]](that: Functor[G]) = new Functor.Composed(self, that)

  def andThen[G[_]](that: Functor[G]) = new Functor.Composed(that, self)
}

object Functor extends ImplicitHktGetter[Functor] {

  class Composed[F[_], G[_]](ff: Functor[F], fg: Functor[G]) extends Functor[({type λ[α] = F[G[α]]})#λ] {
    def map[X, Y](fgx: F[G[X]])(f: X => Y) = ff.map(fgx)(gx => fg.map(gx)(f))
  }

}
