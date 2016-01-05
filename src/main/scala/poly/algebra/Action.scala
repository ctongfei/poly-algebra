package poly.algebra

import poly.algebra.factory._

/**
 * Represents an action.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
@scala.annotation.implicitNotFound("Cannot find an action of type ${X} onto ${S}.")
trait Action[X, S] {
  /** Acts an element onto a state. */
  def act(k: S, x: X): X
}

object Action extends BinaryImplicitGetter[Action] {

  /** Creates an action based on the function provided. */
  def create[X, S](fAct: (S, X) => X): Action[X, S] = new Action[X, S] {
    def act(k: S, x: X) = fAct(k, x)
  }
}
