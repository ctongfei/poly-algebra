package poly.algebra

/**
 * Typeclass for type-strict equality checking.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Eq[@miniboxed X] {

  /** Checks if two objects of the same type are equivalent. */
  def eq(x: X, y: X): Boolean

  /** Checks if two objects of the same type are not equivalent. */
  def ne(x: X, y: X): Boolean = !eq(x, y)

}


object Eq {

  def create[@miniboxed X](fEq: (X, X) => Boolean): Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = fEq(x, y)
  }

  implicit def default[@miniboxed X]: Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = x == y
    override def ne(x: X, y: X) = x != y
  }
}
