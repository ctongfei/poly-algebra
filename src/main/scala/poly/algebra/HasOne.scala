package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */

trait HasOne[@specialized X] {
  def one: X
  def oneIdentity: HasIdentity[X] = new HasIdentity[X] {
    def id = one
  }
}

object HasOne {
  def apply[X](implicit O: HasOne[X]) = O
  def create[X](oneElem: X) = new HasOne[X] {
    def one = oneElem
  }
}