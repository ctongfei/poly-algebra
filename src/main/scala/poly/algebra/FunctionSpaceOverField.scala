package poly.algebra

import poly.util.typeclass._
import poly.util.specgroup._

/**
 * Typeclass for function spaces over fields.
 * @tparam X Type of domain
 * @tparam Y Type of codomain
 * @tparam F Type of the field, over which the codomain is a vector space
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.5
 */
trait FunctionSpaceOverField[X, Y, @sp(fd) F] extends VectorSpace[X => Y, F] with FunctionSpaceOverRing[X, Y, F] {

  def moduleOfCodomain = vectorSpaceOfCodomain
  def vectorSpaceOfCodomain: VectorSpace[Y, F]
  override def scale(f: X => Y, a: F) = (x: X) => vectorSpaceOfCodomain.scale(f(x), a)
  override def neg(f: X => Y) = (x: X) => vectorSpaceOfCodomain.neg(f(x))
  override def zero = (x: X) => vectorSpaceOfCodomain.zero
  override def add(f: X => Y, g: X => Y) = (x: X) => vectorSpaceOfCodomain.add(f(x), g(x))
  override def sub(f: X => Y, g: X => Y) = (x: X) => vectorSpaceOfCodomain.sub(f(x), g(x))

}

object FunctionSpaceOverField extends TernaryImplicitGetter[FunctionSpaceOverField] {
  implicit def default[X, Y, @sp(fd) R](implicit M: VectorSpace[Y, R]): FunctionSpaceOverField[X, Y, R] = new FunctionSpaceOverField[X, Y, R] {
    def vectorSpaceOfCodomain = M
    def fieldOfScalar = M.fieldOfScalar
  }
}
