package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait BooleanAlgebra[@specialized(Int, Double) X] extends Lattice[X] with HasZero[X] with HasOne[X] { self =>

  def and(x: X, y: X): X
  def or(x: X, y: X): X
  def not(x: X): X
  def one: X
  def zero: X

  def xor(x: X, y: X): X = or(and(x, not(y)), and(not(x), y))
  def nand(x: X, y: X): X = not(and(x, y))
  def nor(x: X, y: X): X = not(or(x, y))

  def sup(x: X, y: X): X = or(x, y)
  def inf(x: X, y: X): X = and(x, y)

  /** Casts this Boolean algebra as a Boolean ring with "`xor`" as `+` and "`and`" as `*`. */
  def asBooleanRing: Ring[X] = new Ring[X] {
    def mul(x: X, y: X): X = and(x, y)
    def add(x: X, y: X): X = xor(x, y)
    def neg(x: X): X = x
    def one: X = self.one
    def zero: X = self.zero
  }

}

object BooleanAlgebra {
  def apply[@specialized(Int, Double) X](implicit B: BooleanAlgebra[X]) = B
  def create[@specialized(Int, Double) X](fAnd: (X, X) => X, fOr: (X, X) => X, fNot: X => X, fZero: X, fOne: X)(implicit E: Eq[X]) = new BooleanAlgebra[X] {
    def and(x: X, y: X) = fAnd(x, y)
    def or(x: X, y: X) = fOr(x, y)
    def not(x: X) = fNot(x)
    def zero = fZero
    def one = fOne
  }
}
