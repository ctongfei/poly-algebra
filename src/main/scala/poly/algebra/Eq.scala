package poly.algebra

/**
 * Typeclass for type-strict equivalence relation.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Eq[@specialized(Int, Double) X] {

  /** Checks if two objects of the same type are equivalent under this equivalence relation. */
  def eq(x: X, y: X): Boolean

  /** Checks if two objects of the same type are not equivalent under this equivalence equation. */
  def ne(x: X, y: X): Boolean = !eq(x, y)

}


object Eq {

  def create[@specialized(Int, Double) X](fEq: (X, X) => Boolean): Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = fEq(x, y)
  }

  implicit def default[@specialized(Int, Double) X]: Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = x == y
    override def ne(x: X, y: X) = x != y
  }
}
