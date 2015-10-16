package poly.algebra

import poly.algebra.factory._

/**
 * Represents an action.
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.10
 */
trait Action[X, S] {
  def act(k: S, x: X): X
}

object Action extends BinaryImplicitGetter[Action]
