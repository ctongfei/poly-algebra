package xmath.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait BooleanAlgebra[@specialized(Boolean) X] extends Lattice[X] {

  def and(x: X, y: X): X
  def or(x: X, y: X): X
  def not(x: X): X
  def one: X
  def zero: X
  //TODO: xor
  def nand(x: X, y: X): X = not(and(x, y))
  def nor(x: X, y: X): X = not(or(x, y))

  def sup(x: X, y: X): X = or(x, y)
  def inf(x: X, y: X): X = and(x, y)

}

object BooleanAlgebra {
  def apply[X](B: BooleanAlgebra[X]) = B
  def create[X](fAnd: (X, X) => X, fOr: (X, X) => X, fNot: X => X, fZero: X, fOne: X) = new BooleanAlgebra[X] {
    def and(x: X, y: X) = fAnd(x, y)
    def or(x: X, y: X) = fOr(x, y)
    def not(x: X) = fNot(x)
    def zero = fZero
    def one = fOne

  }
}