package poly.algebra

import poly.algebra.factory._

/**
 * Represents a magma, which is a set endowed with a binary operator.
 * No laws is specified with regards to the operator.
 *
 * @author Tongfei Chen
 * @since 0.4.0
 */
trait Magma[X] {

  def op(x: X, y: X): X

}

object Magma extends ImplicitGetter[Magma] {
  def create[X](fOp: (X, X) => X): Magma[X] = new Magma[X] {
    def op(x: X, y: X) = fOp(x, y)
  }
}
