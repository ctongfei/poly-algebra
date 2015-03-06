package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait PartialOrder[@specialized(Int, Double) X] extends Eq[X] {
  def le(x: X, y: X): Boolean
  def ge(x: X, y: X): Boolean = le(y, x)
  def pmax(x: X, y: X): Option[X] = {
    if (le(x, y)) Some(y)
    else if (le(y, x)) Some(x)
    else None
  }
  def pmin(x: X, y: X): Option[X] = {
    if (le(x, y)) Some(x)
    else if (le(y, x)) Some(y)
    else None
  }
}

object PartialOrder {
  def apply[@specialized(Int, Double) X](implicit O: PartialOrder[X]) = O
  def create[@specialized(Int, Double) X](fLe: (X, X) => Boolean) = new PartialOrder[X] {
    def eq(x: X, y: X) = x == y
    def le(x: X, y: X): Boolean = fLe(x, y)
  }
}
