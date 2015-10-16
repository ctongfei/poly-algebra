package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
trait MultiplicativeAction[X, @sp(fdi) S] {

  def scale(k: S, x: X): X

  def asActionWithScale: Action[X, S] = new Action[X, S] {
    def act(k: S, x: X) = scale(k, x)
  }
}

trait MultiplicativeSemigroupAction[X, @sp(fdi) S] extends MultiplicativeAction[X, S] {
  def semigroupOnScalar: MultiplicativeSemigroup[S]
}

trait MultiplicativeMonoidAction[X, @sp(fdi) S] extends MultiplicativeSemigroupAction[X, S] {
  def monoidOnScalar: MultiplicativeMonoid[S]
  def semigroupOnScalar = monoidOnScalar
}

trait MultiplicativeGroupAction[X, @sp(fdi) S] extends MultiplicativeMonoidAction[X, S] {
  def groupOnScalar: MultiplicativeGroup[S]
  def monoidOnScalar = groupOnScalar
}

object MultiplicativeAction extends BinaryImplicitGetter[MultiplicativeAction]
object MultiplicativeSemigroupAction extends BinaryImplicitGetter[MultiplicativeSemigroupAction]
object MultiplicativeMonoidAction extends BinaryImplicitGetter[MultiplicativeMonoidAction]
object MultiplicativeGroupAction extends BinaryImplicitGetter[MultiplicativeGroupAction]