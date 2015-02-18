package xmath.algebra

/**
 * Typeclass for additive semigroups.
 * An additive semigroup is a semigroup with the binary operation `add` (`+`).
 * @tparam X Type of element
 */
trait AdditiveSemigroup[@specialized(Int, Float, Double) X] {

  /** The `+` operation of this semigroup. */
  def add(x: X, y: X): X

  /** Computes the sum `x + x + ... + x` with x repeated for ''n'' times. */
  def sumN(x: X, n: Int): X = asSemigroupWithAdd.combineN(x, n)

  /** Casts this structure as a symbol-agnostic semigroup. */
  def asSemigroupWithAdd: Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X) = add(x, y)
  }
}

object AdditiveSemigroup {

  /** Retrieves the implicit additive semigroup associated with a specific type. */
  def apply[X](implicit S: AdditiveSemigroup[X]) = S

  /** Creates an additive semigroup using the `add` operation provided. */
  def create[X](f: (X, X) => X) = new AdditiveSemigroup[X] {
    def add(x: X, y: X): X = f(x, y)
  }
}

