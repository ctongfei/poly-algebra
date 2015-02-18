package xmath.algebra

/**
 * Typeclass for endofunction spaces.
 * An endofunction is a function whose domain equals its codomain.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait EndofunctionSpace[X, R] extends FunctionSpace[X, X, R] {

  def composeN(f: X => X, n: Int) = monoidWithCompose.combineN(f, n)

  def monoidWithCompose: Monoid[X => X] = new Monoid[X => X] {
    def id: X => X = (x: X) => x
    def op(f: X => X, g: X => X): X => X = f compose g
  }

}

object EndofunctionSpace {
  def apply[X, R](implicit S: EndofunctionSpace[X, R]) = S

}