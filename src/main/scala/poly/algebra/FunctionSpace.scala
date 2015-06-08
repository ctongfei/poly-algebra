package poly.algebra

import poly.algebra.specgroup._

/**
 * Typeclass for function spaces.
 * @tparam X Type of domain
 * @tparam Y Type of codomain
 * @tparam R Type of the ring, over which the codomain is a module
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait FunctionSpace[X, Y, @sp(di) R] extends Module[X => Y, R] {

  def moduleOfCodomain: Module[Y, R]
  def scale(a: R, f: X => Y) = (x: X) => moduleOfCodomain.scale(a, f(x))
  override def neg(f: X => Y) = (x: X) => moduleOfCodomain.neg(f(x))
  def zero = (x: X) => moduleOfCodomain.zero
  def add(f: X => Y, g: X => Y) = (x: X) => moduleOfCodomain.add(f(x), g(x))
  override def sub(f: X => Y, g: X => Y) = (x: X) => moduleOfCodomain.sub(f(x), g(x))

}

object FunctionSpace {
  implicit def default[X, Y, @sp(di) R](implicit M: Module[Y, R], r: Ring[R]): FunctionSpace[X, Y, R] = new FunctionSpace[X, Y, R] {
    def moduleOfCodomain = M
    def ringOfScalar = r
  }
}
