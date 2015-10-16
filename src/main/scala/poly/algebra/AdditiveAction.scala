package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
trait AdditiveAction[X, @sp(fdi) S] {
  def translate(k: S, x: X): X

  /** Casts this additive action as a general action with the operator `translate`. */
  def asActionWithTranslate: Action[X, S] = new Action[X, S] {
    def act(k: S, x: X) = translate(k, x)
  }
}

trait AdditiveSemigroupAction[X, @sp(fdi) S] extends AdditiveAction[X, S] {
  def semigroupOnActor: AdditiveSemigroup[S]
}

trait AdditiveMonoidAction[X, @sp(fdi) S] extends AdditiveAction[X, S] {
  def monoidOnActor: AdditiveMonoid[S]
  def semigroupOnActor = monoidOnActor
}

trait AdditiveGroupAction[X, @sp(fdi) S] extends AdditiveMonoidAction[X, S] {
  def groupOnActor: AdditiveGroup[S]
  def monoidOnActor = groupOnActor
}

object AdditiveAction extends BinaryImplicitGetter[AdditiveAction]
object AdditiveSemigroupAction extends BinaryImplicitGetter[AdditiveSemigroupAction]
object AdditiveMonoidAction extends BinaryImplicitGetter[AdditiveMonoidAction]
object AdditiveGroupAction extends BinaryImplicitGetter[AdditiveGroupAction]
