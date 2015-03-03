package poly.algebra

/**
 * Typeclass for endofunction spaces.
 * An endofunction is a function whose domain equals its codomain.
 * @tparam X Type of domain and codomain
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait EndofunctionSpace[@miniboxed X, @miniboxed R] extends FunctionSpace[X, X, R] {

  /* TODO: Minibox bug!
  def composeN(f: X => X, n: Int) = asMonoidWithCompose.combineN(f, n)

  def asMonoidWithCompose: Monoid[X => X] = new Monoid[X => X] {
    def id: X => X = (x: X) => x
    def op(f: X => X, g: X => X): X => X = f compose g
  }
  */
}

object EndofunctionSpace {
  def apply[@miniboxed X, @miniboxed R](implicit S: EndofunctionSpace[X, R]) = S
}
