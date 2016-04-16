package poly.algebra.mut

import poly.algebra._

/**
 * @author Tongfei Chen
 * @since 0.3.0
 */
trait InplaceSemigroup[X] extends InplaceAction[X, X] {

  def opInplace(x: X, y: X)

  def actInplace(x: X, s: X) = opInplace(x, x)
}

trait InplaceMonoid[X] extends InplaceSemigroup[X] {

  def clear(x: X)

}

trait InplaceGroup[X] extends InplaceMonoid[X] {

  def invOpInplace(x: X, y: X)

}
