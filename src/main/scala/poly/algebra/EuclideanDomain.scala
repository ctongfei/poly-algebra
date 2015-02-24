package poly.algebra

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait EuclideanDomain[@specialized(Int, Double, Float) X] extends Ring[X] {

  def quot(x: X, y: X): X
  def mod(x: X, y: X): X
  def quotMod(x: X, y: X) : (X, X) = (quot(x, y), mod(x, y))

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

  def lcm(x: X, y: X): X = quot(mul(x, y), gcd(x, y))

  def latticeWithGcdLcm: Lattice[X] = new Lattice[X] {
    def eq(x: X, y: X) = implicitly[Eq[X]].eq(x, y)
    def sup(x: X, y: X) = lcm(x, y)
    def inf(x: X, y: X) = gcd(x, y)
    override def le(x: X, y: X) = mod(y, x) == zero
  }
}

object EuclideanDomain {
  def apply[X](implicit E: EuclideanDomain[X]) = E
  def create[X](R: Ring[X], fQuot: (X, X) => X, fMod: (X, X) => X) = new EuclideanDomain[X] {
    def add(x: X, y: X): X = R.add(x, y)
    def mul(x: X, y: X): X = R.mul(x, y)
    def neg(x: X): X = R.neg(x)
    def zero: X = R.zero
    def one: X = R.one
    def quot(x: X, y: X): X = fQuot(x, y)
    def mod(x: X, y: X): X = fMod(x, y)
  }

  def create[X](fAdd: (X, X) => X, fMul: (X, X) => X, zeroElem: X, oneElem: X, fNeg: X => X, fQuot: (X, X) => X, fMod: (X, X) => X) = new EuclideanDomain[X] {
    def add(x: X, y: X): X = fAdd(x, y)
    def mul(x: X, y: X): X = fMul(x, y)
    def neg(x: X): X = fNeg(x)
    def zero: X = zeroElem
    def one: X = oneElem
    def quot(x: X, y: X): X = fQuot(x, y)
    def mod(x: X, y: X): X = fMod(x, y)
  }
}