package poly.algebra

/**
 * An additive semigroup that allows inplace addition (for performance when dealing with vectors/matrices).
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait InplaceAdditiveSemigroup[X] extends AdditiveSemigroup[X] {
  /** Adds ''y'' onto ''x'' in-place. */
  def inplaceAdd(x: X, y: X): Unit
}


/**
 * An additive monoid that allows inplace addition (for performance when dealing with vectors/matrices).
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait InplaceAdditiveMonoid[X] extends InplaceAdditiveSemigroup[X] with AdditiveMonoid[X] {
  /** Clears ''x'' in-place. */
  def inplaceClear(x: X): Unit
}


/**
 * An additive group that allows inplace addition and subtraction (for performance when dealing with vectors/matrices).
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait InplaceAdditiveGroup[X] extends InplaceAdditiveMonoid[X] with AdditiveGroup[X] {
  /** Subtracts ''y'' from ''x'' in-place. */
  def inplaceSub(x: X, y: X): Unit = inplaceAdd(x, neg(y))
}
