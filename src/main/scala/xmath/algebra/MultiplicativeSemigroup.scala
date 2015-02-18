package xmath.algebra

/**
 * Typeclass for multiplicative semigroups.
 * An multiplicative semigroup is a semigroup with the operation is multiply (`*`).
 * @tparam X Type of element
 */
trait MultiplicativeSemigroup[@specialized(Int, Double, Float) X] {

  def mul(x: X, y: X): X
  def ipow(x: X, n: Int): X = asSemigroupWithMul.combineN(x, n)

  def asSemigroupWithMul: Semigroup[X] = new Semigroup[X] {
    def op(x: X, y: X) = mul(x, y)
  }
}

object MultiplicativeSemigroup {
  def apply[X](implicit S: MultiplicativeSemigroup[X]) = S
  def create[X](f: (X, X) => X) = new MultiplicativeSemigroup[X] {
    def mul(x: X, y: X): X = f(x, y)
  }
}

