package xmath.algebra

/**
 * Typeclass for type-strict equality checking.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Eq[X] {

  /** Checks if two objects of the same type are equivalent. */
  def eq(x: X, y: X): Boolean

  /** Checks if two objects of the same type are not equivalent. */
  def ne(x: X, y: X): Boolean = !eq(x, y)

}


object Eq {
  //def apply[X](implicit E: Eq[X]) = E
  implicit def default[X]: Eq[X] = new Eq[X] {
    def eq(x: X, y: X) = x == y
    override def ne(x: X, y: X) = x != y
  }
}
