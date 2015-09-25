package poly.algebra

import poly.algebra.factory._
import poly.algebra.specgroup._

/**
 * Typeclass for function spaces over rings.
 * @tparam X Type of domain
 * @tparam Y Type of codomain
 * @tparam S Type of the ring, over which the codomain is a module
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.2.5
 */
trait FunctionSpaceOverRing[X, Y, @sp(fdi) S] extends Module[X => Y, S] {

  def moduleOfCodomain: Module[Y, S]
  def scale(f: X => Y, a: S) = (x: X) => moduleOfCodomain.scale(f(x), a)
  override def neg(f: X => Y) = (x: X) => moduleOfCodomain.neg(f(x))
  def zero = (x: X) => moduleOfCodomain.zero
  def add(f: X => Y, g: X => Y) = (x: X) => moduleOfCodomain.add(f(x), g(x))
  override def sub(f: X => Y, g: X => Y) = (x: X) => moduleOfCodomain.sub(f(x), g(x))

}

object FunctionSpaceOverRing extends TernaryImplicitGetter[FunctionSpaceOverRing] {
  implicit def default[X, Y, @sp(fdi) R](implicit Y: Module[Y, R]): FunctionSpaceOverRing[X, Y, R] = new FunctionSpaceOverRing[X, Y, R] {
    def moduleOfCodomain = Y
    def ringOfScalar = Y.ringOfScalar
  }
}
