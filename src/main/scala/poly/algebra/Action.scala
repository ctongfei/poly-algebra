package poly.algebra

import poly.algebra.factory._

/**
 * Represents an action.
 *
 * @author Tongfei Chen
 * @since 0.2.10
 */
trait Action[X, -S] {
  /** Acts an element onto a state. */
  def act(x: X, k: S): X
}

object Action extends BinaryImplicitGetter[Action] {

  /** Creates an action based on the function provided. */
  def create[X, S](fAct: (X, S) => X): Action[X, S] = new Action[X, S] {
    def act(x: X, k: S) = fAct(x, k)
  }
}

trait SemigroupAction[X, S] extends Action[X, S] {
  def actorSemigroup: Semigroup[S]
}

object SemigroupAction extends BinaryImplicitGetter[SemigroupAction] {

  def create[X, S](fAct: (X, S) => X)(implicit S: Semigroup[S]): SemigroupAction[X, S] = new SemigroupAction[X, S] {
    def actorSemigroup = S
    def act(x: X, k: S) = fAct(x, k)
  }

  /** Returns the trivial action if an implicit semigroup is present. */
  implicit def trivial[S](implicit S: Semigroup[S]): SemigroupAction[S, S] = new SemigroupAction[S, S] {
    def act(x: S, k: S) = S.op(x, k)
    def actorSemigroup = S
  }
}

trait MonoidAction[X, S] extends SemigroupAction[X, S] {
  def actorMonoid: Monoid[S]
  def actorSemigroup = actorMonoid
}

object MonoidAction extends BinaryImplicitGetter[MonoidAction] {
  implicit def trivial[S](implicit S: Monoid[S]): MonoidAction[S, S] = new MonoidAction[S, S] {
    def actorMonoid = S
    def act(x: S, k: S) = S.op(x, k)
  }
}

trait GroupAction[X, S] extends MonoidAction[X, S] {
  def actorGroup: Group[S]
  def actorMonoid = actorGroup
  override def actorSemigroup = actorGroup
}

object GroupAction extends BinaryImplicitGetter[GroupAction] {
  implicit def trivial[S](implicit S: Group[S]): GroupAction[S, S] = new GroupAction[S, S] {
    def actorGroup = S
    def act(x: S, k: S) = S.op(x, k)
  }
}
