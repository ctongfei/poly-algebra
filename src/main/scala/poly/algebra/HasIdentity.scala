package poly.algebra

/**
 * Represents the existence of an identity element.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait HasIdentity[@specialized X] {
  def id: X
}

object HasIdentity {
  def apply[X](implicit I: HasIdentity[X]) = I
  def create[X](idElem: X) = new HasIdentity[X] {
    def id = idElem
  }
}

