package poly.algebra.mut

import poly.algebra._

/**
 * An additive semigroup that allows inplace addition (for performance when dealing with vectors/matrices).
 *
 * @author Tongfei Chen
 * @since 0.2.1
 */
trait InplaceAdditiveSemigroup[X] extends InplaceAdditiveAction[X, X] {
  /** Adds ''y'' onto ''x'' in-place. */
  def addInplace(x: X, y: X): Unit
}

/**
 * An additive monoid that allows inplace addition (for performance when dealing with vectors/matrices).
 *
 * @author Tongfei Chen
 */
trait InplaceAdditiveMonoid[X] extends InplaceAdditiveSemigroup[X] with AdditiveMonoid[X] {

  /** Clears ''x'' in-place. */
  def clear(x: X): Unit
}


/**
 * An additive group that allows inplace addition and subtraction (for performance when dealing with vectors/matrices).
 *
 * @author Tongfei Chen
 */
trait InplaceAdditiveGroup[X] extends InplaceAdditiveMonoid[X] with AdditiveGroup[X] {
  /** Subtracts ''y'' from ''x'' in-place. */
  def subInplace(x: X, y: X): Unit
}
