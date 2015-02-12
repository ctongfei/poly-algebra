package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait HasZero[@specialized X] {
  def zero: X
  def zeroIdentity: HasIdentity[X] = new HasIdentity[X] {
    def id = zero
  }
}

object HasZero {
  def apply[X](implicit Z: HasZero[X]) = Z
  def create[X](zeroElem: X) = new HasZero[X] {
    def zero = zeroElem
  }
}