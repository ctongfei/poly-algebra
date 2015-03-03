package poly.algebra

/**
 * Typeclass for additive groups.
 * Additives groups are groups with the operation represented by `+`.
 * @tparam X Type
 */
trait AdditiveGroup[@miniboxed X] extends AdditiveMonoid[X] { self =>
  def neg(x: X): X
  def sub(x: X, y: X): X = add(x, neg(y))
  def asGroupWithAdd: Group[X] = new Group[X] {
    def inv(x: X) = self.neg(x)
    def op(x: X, y: X) = self.add(x, y)
    def id = self.zero
  }
}

object AdditiveGroup {
  def apply[@miniboxed X](implicit G: AdditiveGroup[X]) = G
  def create[@miniboxed X](f: (X, X) => X, zeroElem: X, fNeg: X => X) = new AdditiveGroup[X] {
    def neg(x: X): X = fNeg(x)
    def add(x: X, y: X): X = f(x, y)
    def zero: X = zeroElem
  }
}
