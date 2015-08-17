package poly.algebra

import poly.util.specgroup._

/**
 * Typeclass for function spaces.
 * @tparam X Type of domain
 * @tparam Y Type of codomain
 * @tparam R Type of the ring, over which the codomain is a module
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait FunctionSpaceOverRing[X, Y, @sp(di) R] extends Module[X => Y, R] {

  def moduleOfCodomain: Module[Y, R]
  def scale(f: (X) => Y, a: R) = (x: X) => moduleOfCodomain.scale(f(x), a)
  override def neg(f: X => Y) = (x: X) => moduleOfCodomain.neg(f(x))
  def zero = (x: X) => moduleOfCodomain.zero
  def add(f: X => Y, g: X => Y) = (x: X) => moduleOfCodomain.add(f(x), g(x))
  override def sub(f: X => Y, g: X => Y) = (x: X) => moduleOfCodomain.sub(f(x), g(x))

}

object FunctionSpaceOverRing {
  implicit def default[X, Y, @sp(di) R](implicit M: Module[Y, R]): FunctionSpaceOverRing[X, Y, R] = new FunctionSpaceOverRing[X, Y, R] {
    def moduleOfCodomain = M
    def ringOfScalar = M.ringOfScalar
  }
}
