package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Represents an action in which its action operator is translation (`:+`).
 * @author Tongfei Chen
 * @since 0.2.10
 */
trait AdditiveAction[X, S] {
  /** Translates an element ''x'' by ''k''. */
  def translate(x: X, k: S): X

  /** Casts this additive action as a general action with the operator `translate`. */
  def asActionWithTranslate: Action[X, S] = new Action[X, S] {
    def act(x: X, k: S) = translate(x, k)
  }
}

/** Represents an additive action where the actors form a semigroup. */
trait AdditiveSemigroupAction[X, S] extends AdditiveAction[X, S] { self =>
  /** Returns the semigroup on actors. */
  def semigroupOnActor: AdditiveSemigroup[S]

  override def asActionWithTranslate: SemigroupAction[X, S] = new SemigroupAction[X, S] {
    def semigroupOnActor = self.semigroupOnActor.asSemigroupWithAdd
    def act(x: X, k: S) = self.translate(x, k)
  }
}

/** Represents an additive action where the actors form a monoid. */
trait AdditiveMonoidAction[X, S] extends AdditiveSemigroupAction[X, S] { self =>
  /** Returns the monoid on actors. */
  def monoidOnActor: AdditiveMonoid[S]
  def semigroupOnActor = monoidOnActor

  override def asActionWithTranslate: MonoidAction[X, S] = new MonoidAction[X, S] {
    def monoidOnActor = self.monoidOnActor.asMonoidWithAdd
    def act(x: X, k: S) = self.translate(x, k)
  }
}

/** Represents an additive action where the actors form a group. */
trait AdditiveGroupAction[X, S] extends AdditiveMonoidAction[X, S] { self =>
  /** Returns the group on actors. */
  def groupOnActor: AdditiveGroup[S]
  def monoidOnActor = groupOnActor
  override def semigroupOnActor = groupOnActor

  override def asActionWithTranslate: GroupAction[X, S] = new GroupAction[X, S] {
    def groupOnActor = self.groupOnActor.asGroupWithAdd
    def act(x: X, k: S) = self.translate(x, k)
  }
}

object AdditiveAction extends BinaryImplicitGetter[AdditiveAction]
object AdditiveSemigroupAction extends BinaryImplicitGetter[AdditiveSemigroupAction]
object AdditiveMonoidAction extends BinaryImplicitGetter[AdditiveMonoidAction]
object AdditiveGroupAction extends BinaryImplicitGetter[AdditiveGroupAction]
