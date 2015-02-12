package xmath.algebra

/**
 * Typeclass for additive semigroups.
 * An additive semigroup is a semigroup with the operation is add (`+`).
 * @tparam X Type of element
 */
trait AdditiveSemigroup[@specialized X] {

  def add(x: X, y: X): X
  def sumN(x: X, n: Int): X = semigroupWithAdd.combineN(x, n)

  def semigroupWithAdd: Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X) = add(x, y)
  }
}

object AdditiveSemigroup {
  def apply[X](implicit S: AdditiveSemigroup[X]) = S
  def create[X](f: (X, X) => X) = new AdditiveSemigroup[X] {
    def add(x: X, y: X): X = f(x, y)
  }
}

