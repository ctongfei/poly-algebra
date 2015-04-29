package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait EuclideanDomain[@specialized(Int, Double) X] extends Ring[X] {

  /** Returns the quotient (Euclidean division) of two elements. */
  def div(x: X, y: X): X

  /** Returns the modulus of two elements. */
  def mod(x: X, y: X): X

  /** Simutaneously returns the quotient and the modulus of two elements. For performance, this function should be overridden. */
  def quotMod(x: X, y: X) : (X, X) = (div(x, y), mod(x, y))

  /** Computes the greatest common divisor of two elements using Euclidean algorithm. */
  def gcd(x: X, y: X): X = {
    var m = x
    var n = y
    while (n != zero) {
      val t = mod(m, n)
      m = n
      n = t
    }
    m
  }

  /** Computes the least common multiplier of two elements. */
  def lcm(x: X, y: X): X = div(mul(x, y), gcd(x, y))

  /** Casts this Euclidean domain as a lattice with `gcd` as its `inf` operator and `lcm` as its `sup` operator. */
  def asLatticeWithGcdLcm: Lattice[X] = new Lattice[X] {
    def sup(x: X, y: X) = lcm(x, y)
    def inf(x: X, y: X) = gcd(x, y)
    override def le(x: X, y: X) = mod(y, x) == zero
  }

  /** Casts this Euclidean domain as a partial order with the divisible operation as its `<=` operator. */
  def asPartialOrderWithDivisibility: PartialOrder[X] = new PartialOrder[X] {
    def le(x: X, y: X) = Eq.default[X].eq(mod(y, x), zero)
  }
}

object EuclideanDomain {
  def apply[@specialized(Int, Double) X](implicit E: EuclideanDomain[X]) = E
  def create[@specialized(Int, Double) X](R: Ring[X], fQuot: (X, X) => X, fMod: (X, X) => X) = new EuclideanDomain[X] {
    def add(x: X, y: X): X = R.add(x, y)
    def mul(x: X, y: X): X = R.mul(x, y)
    def neg(x: X): X = R.neg(x)
    def zero: X = R.zero
    def one: X = R.one
    def div(x: X, y: X): X = fQuot(x, y)
    def mod(x: X, y: X): X = fMod(x, y)
  }

  def create[@specialized(Int, Double) X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X, fNeg: X => X, fQuot: (X, X) => X, fMod: (X, X) => X) = new EuclideanDomain[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def neg(x: X): X = fNeg(x)
    def zero: X = zeroElem
    def one: X = oneElem
    def div(x: X, y: X): X = fQuot(x, y)
    def mod(x: X, y: X): X = fMod(x, y)
  }
}