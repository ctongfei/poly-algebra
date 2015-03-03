package poly.algebra

/**
 * Typeclass for rings.
 *
 * A ring is an algebraic structure that generalizes the arithmetic operations of addition
 * and multiplication.
 *
 * Formally, a ring is a set ''R'' equipped with `+` and `*` where (''R'', `+`) form an
 * Abelian additive group (associative, `0` as the additive identity, commutative, invertible),
 * (''R'', `*`) form a multiplicative monoid (associative, `1` as the multiplicative identity),
 * and `*` distributes over `+`.
 *
 * In order to create a ring, `add`, `mul`, `zero`, `one`, `neg` should be implemented. `sub`
 * should be overridden for performance.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Ring[@miniboxed X] extends Semiring[X] with AdditiveGroup[X] with MultiplicativeMonoid[X] {
  def negOne = neg(one)
}

object Ring {

  /** Retrieves the implicit ring associated with a specific type. */
  def apply[@miniboxed X](implicit R: Ring[X]) = R

  /** Creates a ring using the provided operations `add`, `mul`, `zero`, `one` and `neg`. */
  def create[@miniboxed X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X, fNeg: X => X) = new Ring[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def zero: X = zeroElem
    def one: X = oneElem
    def neg(x: X): X = fNeg(x)
  }
}
