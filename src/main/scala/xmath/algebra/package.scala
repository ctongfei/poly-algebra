package xmath

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object algebra {

  def id[X](implicit I: HasIdentity[X]) = I.id

  def zero[X](implicit Z: HasZero[X]) = Z.zero

  def one[X](implicit O: HasOne[X]) = O.one

  implicit object BooleanStructure extends BooleanAlgebra[Boolean] with TotalOrder[Boolean] {
    def one = true
    def zero = false
    def not(x: Boolean) = !x
    def and(x: Boolean, y: Boolean) = x & y
    def or(x: Boolean, y: Boolean) = x | y
  }

  implicit object IntStructure extends EuclideanDomain[Int] with TotalOrder[Int] {
    override def le(x: Int, y: Int) = x <= y
    override def lt(x: Int, y: Int) = x < y
    override def ge(x: Int, y: Int) = x >= y
    override def gt(x: Int, y: Int) = x > y
    def sup(x: Int, y: Int) = if (x > y) x else y
    def inf(x: Int, y: Int) = if (x < y) x else y
    def zero = 0
    def one = 1
    def add(x: Int, y: Int) = x + y
    def neg(x: Int) = -x
    override def sub(x: Int, y: Int) = x - y
    def mul(x: Int, y: Int) = x * y
    def quot(x: Int, y: Int) = x / y
    def mod(x: Int, y: Int) = x % y
  }
  
  implicit object DoubleStructure extends Field[Double] with TotalOrder[Double] {
    override def le(x: Double, y: Double) = x <= y
    override def lt(x: Double, y: Double) = x < y
    override def ge(x: Double, y: Double) = x >= y
    override def gt(x: Double, y: Double) = x > y
    def sup(x: Double, y: Double) = if (x > y) x else y
    def inf(x: Double, y: Double) = if (x < y) x else y
    def zero = 0.0
    def one = 1.0
    def add(x: Double, y: Double) = x + y
    def neg(x: Double) = -x
    override def sub(x: Double, y: Double) = x - y
    def mul(x: Double, y: Double) = x * y
    def inv(x: Double) = 1.0 / x
    override def div(x: Double, y: Double) = x / y
    override def quot(x: Double, y: Double) = x / y
    override def mod(x: Double, y: Double) = 0.0
  }

  implicit class withOps[X](val x: X) extends AnyVal { // ensure static invocation
    def unary_-(implicit S: AdditiveGroup[X]) = S.neg(x)
    def +(y: X)(implicit S: AdditiveSemigroup[X]) = S.add(x, y)
    def -(y: X)(implicit S: AdditiveGroup[X]) = S.sub(x, y)
    def *(y: X)(implicit S: MultiplicativeSemigroup[X]) = S.mul(x, y)
    def /(y: X)(implicit S: MultiplicativeGroup[X]) = S.div(x, y)
    def %(y: X)(implicit S: EuclideanDomain[X]) = S.mod(x, y)
    def **(n: Int)(implicit S: MultiplicativeSemigroup[X]) = S.ipow(x, n)
    def unary_!(implicit S: BooleanAlgebra[X]) = S.not(x)
    def &(y: X)(implicit S: BooleanAlgebra[X]) = S.and(x, y)
    def |(y: X)(implicit S: BooleanAlgebra[X]) = S.or(x, y)

    def <(y: X)(implicit S: TotalOrder[X]) = S.lt(x, y)
    def <=(y: X)(implicit S: PartialOrder[X]) = S.le(x, y)
    def >(y: X)(implicit S: TotalOrder[X]) = S.gt(x, y)
    def >=(y: X)(implicit S: PartialOrder[X]) = S.ge(x, y)
  }
}
