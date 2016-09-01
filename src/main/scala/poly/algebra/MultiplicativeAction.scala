package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * @author Tongfei Chen
 * @since 0.2.10
 */
trait MultiplicativeAction[X, @sp(fdi) S] {

  def scale(x: X, k: S): X

  def asActionWithScale: Action[X, S] = new Action[X, S] {
    def act(x: X, k: S) = scale(x, k)
  }
}

trait MultiplicativeSemigroupAction[X, @sp(fdi) S] extends MultiplicativeAction[X, S] {
  def scalarSemigroup: MultiplicativeSemigroup[S]
  override def asActionWithScale: SemigroupAction[X, S] = new SemigroupAction[X, S] {
    def actorSemigroup = scalarSemigroup.asSemigroupWithMul
    def act(x: X, k: S) = scale(x, k)
  }
}

trait MultiplicativeMonoidAction[X, @sp(fdi) S] extends MultiplicativeSemigroupAction[X, S] {
  def scalarMonoid: MultiplicativeMonoid[S]
  def scalarSemigroup = scalarMonoid
  override def asActionWithScale: MonoidAction[X, S] = new MonoidAction[X, S] {
    def actorMonoid = scalarMonoid.asMonoidWithMul
    def act(x: X, k: S) = scale(x, k)
  }
}

trait MultiplicativeGroupAction[X, @sp(fdi) S] extends MultiplicativeMonoidAction[X, S] {
  def scalarGroup: MultiplicativeGroup[S]
  def scalarMonoid = scalarGroup
  override def scalarSemigroup = scalarGroup
  override def asActionWithScale: GroupAction[X, S] = new GroupAction[X, S] {
    def actorGroup = scalarGroup.asGroupWithMul
    def act(x: X, k: S) = scale(x, k)
  }
}

object MultiplicativeAction extends BinaryImplicitGetter[MultiplicativeAction]
object MultiplicativeSemigroupAction extends BinaryImplicitGetter[MultiplicativeSemigroupAction]
object MultiplicativeMonoidAction extends BinaryImplicitGetter[MultiplicativeMonoidAction]
object MultiplicativeGroupAction extends BinaryImplicitGetter[MultiplicativeGroupAction]
