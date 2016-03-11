package poly.algebra

/**
 * An additive semigroup that allows inplace addition (for performance when dealing with vectors/matrices).
 * @author Tongfei Chen
 * @since 0.2.1
 */
trait InplaceAdditiveCSemigroup[X] extends AdditiveCSemigroup[X] {
  /** Adds ''y'' onto ''x'' in-place. */
  def addInplace(x: X, y: X): Unit
}

/**
 * An additive monoid that allows inplace addition (for performance when dealing with vectors/matrices).
 * @author Tongfei Chen
 */
trait InplaceAdditiveCMonoid[X] extends InplaceAdditiveCSemigroup[X] with AdditiveCMonoid[X] {
  /** Clears ''x'' in-place. */
  def clear(x: X): Unit
}


/**
 * An additive group that allows inplace addition and subtraction (for performance when dealing with vectors/matrices).
 * @author Tongfei Chen
 */
trait InplaceAdditiveCGroup[X] extends InplaceAdditiveCMonoid[X] with AdditiveCGroup[X] {
  /** Subtracts ''y'' from ''x'' in-place. */
  def subInplace(x: X, y: X): Unit
}
