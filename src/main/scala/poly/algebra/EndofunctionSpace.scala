package poly.algebra

import poly.algebra.factory._
import poly.util.specgroup._

/**
 * Typeclass for endofunction spaces.
 * An endofunction is a function whose domain equals its codomain.
 * @tparam X Type of domain and codomain
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait EndofunctionSpace[X, @sp(fdi) R] extends FunctionSpace[X, X, R] {

  def composeN(f: X => X, n: Int) = asMonoidWithCompose.combineN(f, n)

  def asMonoidWithCompose: Monoid[X => X] = new Monoid[X => X] {
    def id: X => X = (x: X) => x
    def op(f: X => X, g: X => X): X => X = f compose g
  }
}

object EndofunctionSpace extends BinaryImplicitGetter[EndofunctionSpace] {

  implicit def default[X, @sp(fdi) R](implicit M: Module[X, R], r: Ring[R]): EndofunctionSpace[X, R] = new EndofunctionSpace[X, R] {
    def moduleOfCodomain = M
    def ringOfScalar = r
  }
}

