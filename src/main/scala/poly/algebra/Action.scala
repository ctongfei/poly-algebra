package poly.algebra

import poly.algebra.factory._

/**
 * Represents an action.
 *
 * @author Tongfei Chen
 * @since 0.2.10
 */
@implicitNotFound("Cannot find an action of type ${S} onto ${X}.")
trait Action[X, -S] {
  /** Acts an element onto a state. */
  def act(x: X, k: S): X
}

object Action extends BinaryImplicitGetter[Action] {

  /** Creates an action based on the function provided. */
  def create[X, S](fAct: (X, S) => X): Action[X, S] = new Action[X, S] {
    def act(x: X, k: S) = fAct(x, k)
  }

  /** Returns the trivial action if an implicit semigroup is present. */
  implicit def trivial[S](implicit S: Semigroup[S]): Action[S, S] = new Action[S, S] {
    def act(x: S, k: S) = S.op(x, k)
  }

}
