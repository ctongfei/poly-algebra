package poly

import poly.algebra.monad._

/**
 * `Poly-algebra` contains typeclass abstractions over common algebraic structures.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object algebra {

  implicit val implicitBooleanStructure = std.BooleanStructure
  implicit val implicitIntStructure = std.IntStructure
  implicit val implicitFloatStructure = std.FloatStructure
  implicit val implicitDoubleStructure = std.DoubleStructure


  implicit class withOps[X](val x: X) extends AnyVal { // ensure static invocation

    def unary_-(implicit S: AdditiveGroup[X]) = S.neg(x)
    def +(y: X)(implicit S: AdditiveSemigroup[X]) = S.add(x, y)
    def -(y: X)(implicit S: AdditiveGroup[X]) = S.sub(x, y)
    def *(y: X)(implicit S: MultiplicativeSemigroup[X]) = S.mul(x, y)
    def /(y: X)(implicit S: EuclideanDomain[X]) = S.quot(x, y)
    def %(y: X)(implicit S: EuclideanDomain[X]) = S.mod(x, y)

    def **(n: Int)(implicit S: MultiplicativeSemigroup[X]) = S.ipow(x, n)
    def **(n: Int)(implicit S: MultiplicativeMonoid[X]) = S.ipow(x, n)
    def **(y: X)(implicit S: PowerOps[X]) = S.pow(x, y)

    def unary_!(implicit S: BooleanAlgebra[X]) = S.not(x)
    def &(y: X)(implicit S: BooleanAlgebra[X]) = S.and(x, y)
    def |(y: X)(implicit S: BooleanAlgebra[X]) = S.or(x, y)

    def and(y: X)(implicit S: BooleanAlgebra[X]) = S.and(x, y)
    def or(y: X)(implicit S: BooleanAlgebra[X]) = S.or(x, y)
    def xor(y: X)(implicit S: BooleanAlgebra[X]) = S.xor(x, y)

    def ∧(y: X)(implicit S: BooleanAlgebra[X]) = S.and(x, y)
    def ∨(y: X)(implicit S: BooleanAlgebra[X]) = S.or(x, y)

    def :*[R](k: R)(implicit S: Module[X, R]) = S.scale(k, x)
    def *:[R](k: R)(implicit S: Module[X, R]) = S.scale(k, x) // colon on the right side

    def ===(y: X)(implicit S: Eq[X]) = S.eq(x, y)
    def =!=(y: X)(implicit S: Eq[X]) = S.ne(x, y)
    def <=(y: X)(implicit S: PartialOrder[X]) = S.le(x, y)
    def >=(y: X)(implicit S: PartialOrder[X]) = S.ge(x, y)
    def <(y: X)(implicit S: WeakOrder[X]) = S.lt(x, y)
    def >(y: X)(implicit S: WeakOrder[X]) = S.gt(x, y)
    def =~=(y: X)(implicit S: WeakOrder[X]) = S.tied(x, y)

    def ###(implicit S: Hash[X]) = S.hash(x)

    def innerProduct[F](y: X)(implicit S: InnerProductSpace[X, F]) = S.dot(x, y)

  }

}
