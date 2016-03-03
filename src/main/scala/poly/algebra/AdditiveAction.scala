package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an action in which its action operator is translation (`:+`).
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
trait AdditiveAction[X, @sp(fdi) S] {
  /** Translates an element ''x'' by ''k''. */
  def translate(x: X, k: S): X

  /** Casts this additive action as a general action with the operator `translate`. */
  def asActionWithTranslate: Action[X, S] = new Action[X, S] {
    def act(x: X, k: S) = translate(x, k)
  }
}

/** Represents an additive action where the actors form a semigroup. */
trait AdditiveSemigroupAction[X, @sp(fdi) S] extends AdditiveAction[X, S] {
  /** Returns the semigroup on actors. */
  def semigroupOnActor: AdditiveSemigroup[S]
}

/** Represents an additive action where the actors form a monoid. */
trait AdditiveMonoidAction[X, @sp(fdi) S] extends AdditiveSemigroupAction[X, S] {
  /** Returns the monoid on actors. */
  def monoidOnActor: AdditiveMonoid[S]
  def semigroupOnActor = monoidOnActor
}

/** Represents an additive action where the actors form a group. */
trait AdditiveGroupAction[X, @sp(fdi) S] extends AdditiveMonoidAction[X, S] {
  /** Returns the group on actors. */
  def groupOnActor: AdditiveGroup[S]
  def monoidOnActor = groupOnActor
}

object AdditiveAction extends BinaryImplicitGetter[AdditiveAction]
object AdditiveSemigroupAction extends BinaryImplicitGetter[AdditiveSemigroupAction]
object AdditiveMonoidAction extends BinaryImplicitGetter[AdditiveMonoidAction]
object AdditiveGroupAction extends BinaryImplicitGetter[AdditiveGroupAction]
