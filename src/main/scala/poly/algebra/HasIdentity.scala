package poly.algebra

/**
 * Represents the existence of an identity element.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait HasIdentity[@miniboxed X] {
  /** The identity element of this type. */
  def id: X
}

object HasIdentity {
  /** Retrieves the implicit HasIdentity object of this type. */
  def apply[@miniboxed X](implicit I: HasIdentity[X]) = I

  /** Creates a `HasIdentity` object given an identity element of a type. */
  def create[@miniboxed X](idElem: X) = new HasIdentity[X] {
    def id = idElem
  }
}

